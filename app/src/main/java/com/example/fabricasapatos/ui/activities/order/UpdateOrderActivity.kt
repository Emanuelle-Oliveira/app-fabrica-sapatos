package com.example.fabricasapatos.ui.activities.order

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetClientsUseCase
import com.example.fabricasapatos.domain.item.model.Item
import com.example.fabricasapatos.domain.item.usecases.contracts.*
import com.example.fabricasapatos.domain.order.model.Order
import com.example.fabricasapatos.domain.order.usecases.contracts.IUpdateOrderUseCase
import com.example.fabricasapatos.domain.product.model.Product
import com.example.fabricasapatos.domain.product.usecases.contracts.IGetProductsUseCase
import com.example.fabricasapatos.ui.activities.principal.AppBar
import com.example.fabricasapatos.ui.activities.principal.DrawerHeader
import com.example.fabricasapatos.ui.activities.principal.NavigationDrawer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction4

data class ItemUpdate (
    val productId: Int,
    var quantity: Int,
    val id: Int?
)

@AndroidEntryPoint
class UpdateOrderActivity : ComponentActivity() {

    @Inject
    lateinit var getClientsUseCase: IGetClientsUseCase

    @Inject
    lateinit var updateOrderUseCase: IUpdateOrderUseCase

    @Inject
    lateinit var updateItemUseCase: IUpdateItemUseCase

    @Inject
    lateinit var getProductsUseCase: IGetProductsUseCase

    @Inject
    lateinit var getItemsByOrderUseCase: IGetItemsByOrderUseCase

    @Inject
    lateinit var updateLastItemIdUseCase2: IUpdateLastItemIdUseCase2

    @Inject
    lateinit var getLastItemIdUseCase: IGetLastItemIdUseCase

    @Inject
    lateinit var deleteItemUseCase : IDeleteItemUseCase

    private var orderList = mutableStateOf(emptyList<Order>())
    private val clientsList = mutableStateOf(emptyList<Client>())
    private val productList = mutableStateOf(emptyList<Product>())
    private val itemsList = mutableStateOf(emptyList<Item>())

    var lastItemId = mutableStateOf(0)

    fun getProducts() {
        lifecycleScope.launch {
            productList.value = getProductsUseCase()
        }
    }

    fun getClients() {
        lifecycleScope.launch {
            clientsList.value = getClientsUseCase()
        }
    }

    fun updateItem(idOrder : Int , productId: Int, quantity: Int, id: Int) {
        lifecycleScope.launch {
            updateItemUseCase(idOrder, productId, quantity, id)
        }
    }

    fun updateOrder(idOrder : Int , clientCpf: String) {
        lifecycleScope.launch {
            updateOrderUseCase(idOrder, clientCpf)
        }
    }

    fun getItems(orderId: Int) {
        lifecycleScope.launch {
            itemsList.value = getItemsByOrderUseCase(orderId)
        }
    }

    fun updateLastItemId(id: Int, size : Int) {
        lifecycleScope.launch {
            updateLastItemIdUseCase2(id, size)
        }
    }

    fun getLastItemId() {
        lifecycleScope.launch {
            lastItemId.value = getLastItemIdUseCase()
        }
    }

    fun deleteItem(id: Int) {
        lifecycleScope.launch {
            deleteItemUseCase(id)
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getClients()
        getProducts()
        getLastItemId()
        val order = intent.getParcelableExtra<Order>("order")
        if (order != null) {
            getItems(order.id)
        }
        setContent {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            androidx.compose.material.Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    AppBar(
                        onNavigationIconClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    )
                },
                drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                drawerContent = {
                    DrawerHeader()
                    NavigationDrawer()
                }
            ) {
                TelaUpdatePedido(::updateOrder, ::updateItem, clientsList, productList, itemsList, order, lastItemId, ::deleteItem, ::updateLastItemId)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaUpdatePedido(
    updateOrder: KFunction2<Int, String, Unit>,
    updateItem: KFunction4<Int, Int, Int, Int, Unit>,
    clientList: MutableState<List<Client>>,
    productList: MutableState<List<Product>>,
    itemsList: MutableState<List<Item>>,
    order: Order?,
    lastItemId: MutableState<Int>,
    deleteItem: KFunction1<Int, Unit>,
    updateLastItemId: (Int, Int) -> Unit
) {
    val textValueClient = remember { mutableStateOf("") }
    val textValueProduct = remember { mutableStateOf("") }

    var selectedClient by remember { mutableStateOf<Client?>(null) }
    var selectedClientCpf by remember { mutableStateOf(order!!.clientCpf) }

    var selectedProduct by remember  { mutableStateOf<Product?>(null) }
    var selectedProductId by remember { mutableStateOf(null) }

    var quantity : Int = 1
    val pedidoList = remember { mutableStateListOf<ItemUpdate>() } // Lista para armazenar os pedidos

    LaunchedEffect(itemsList.value) {
        pedidoList.clear()
        itemsList.value.forEach {
            pedidoList.add(ItemUpdate(it.productId, it.quantity, it.id))
        }
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        if (order != null) {
            ClientSelect3(
                orderClientCpf = order.clientCpf,
                clientList = clientList,
                selectedClientCpf = selectedClientCpf
            ) { client ->
                selectedClientCpf = client.cpf
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ProductSelect(
                productList = productList,
                selectedProduct = selectedProduct
            ) { product ->
                selectedProduct = product
            }
            Button(
                onClick = {
                    // Adicionar o valor do OutlinedTextField à lista de pedidos
                    selectedProduct?.let {
                        pedidoList.add(ItemUpdate(it.id.toInt(), quantity, null))
                    }
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add FAB",
                    tint = Color.White,
                )
            }
        }

        // LazyColumn para exibir os valores da lista de pedidos
        LazyColumn {
            itemsIndexed(pedidoList) { index, pedido ->
                PedidoCard2(
                    pedido = pedido,
                    productList = productList.value,
                    numero = index + 1,
                    pedidoList = pedidoList,
                    itemsList = itemsList,
                    index = index,
                    deleteItem = deleteItem,
                    quantidade = pedido.quantity
                ) { novaQuantidade ->
                    pedido.quantity = novaQuantidade
                }
            }
        }
        // Botão "Salvar"
        Button(
            onClick = {
                // Ação do botão
                if (order != null) {
                    updateOrder(order.id , selectedClientCpf)
                }

                updateLastItemId(lastItemId.value, pedidoList.size - itemsList.value.size)

                pedidoList.forEach { item ->
                    if(item.id != null) {
                        updateItem(order!!.id, item.productId, item.quantity, item.id)
                    } else {
                        updateItem(order!!.id, item.productId, item.quantity, lastItemId.value+1)
                        lastItemId.value += 1
                    }
                }

                context.startActivity(Intent(context, GetOrdersActivity::class.java))
            },
            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = MaterialTheme.colorScheme.errorContainer),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start) // Ajuste o alinhamento vertical aqui
                .padding(2.dp)
        ) {
            Text(text = "Salvar")
        }
    }
}

@Composable
fun ClientSelect3(orderClientCpf : String, clientList: MutableState<List<Client>>, selectedClientCpf: String, onClientSelected: (Client) -> Unit) {

    var clientName = "aa"
    for(client in clientList.value) {
        //Log.i("teste", client.cpf)
        if(client.cpf == orderClientCpf ) {
            clientName = client.name
        }
    }

    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize()) {
        val selectedClient = clientList.value.find { it.cpf == selectedClientCpf }
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.width(250.dp)
        ) {
            Text(selectedClient?.name ?: clientName)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(),
            content = {
                clientList.value.forEachIndexed { index, client ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onClientSelected(client)
                        },
                        modifier = Modifier
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        ClientItem2(client)
                    }
                }
            }
        )
    }
}

@Composable
fun PedidoCard2(
    pedido: ItemUpdate,
    productList: List<Product>,
    numero: Int,
    quantidade: Int,
    pedidoList: SnapshotStateList<ItemUpdate>,
    itemsList: MutableState<List<Item>>,
    index: Int,
    deleteItem: KFunction1<Int, Unit>,
    onQuantityChanged: (Int) -> Unit
) {
    var quantidadeAtual by remember { mutableStateOf(quantidade) }
    val product = productList.find { it.id == pedido.productId.toInt() }

    Card (
        modifier = Modifier.padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = " $numero: ${product?.description}",
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = {
                    quantidadeAtual -= 1
                    onQuantityChanged(quantidadeAtual)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Remover"
                )
            }
            Text(text = quantidadeAtual.toString())
            IconButton(
                onClick = {
                    quantidadeAtual += 1
                    onQuantityChanged(quantidadeAtual)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Adicionar"
                )
            }
            Box(
                modifier = Modifier.wrapContentSize(Alignment.CenterEnd)
            ) {
                IconButton(
                    onClick = {
                        if(pedidoList[index].id == null) {
                            pedidoList.removeAt(index)
                        } else {
                            deleteItem(pedidoList[index].id!!)
                            val newItemList = itemsList.value.toMutableList().apply {
                                removeAll { it.id == pedidoList[index].id }
                            }
                            itemsList.value = newItemList
                            pedidoList.removeAt(index)
                        }
                    }
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Lixeira"
                    )
                }
            }
        }
    }
}
