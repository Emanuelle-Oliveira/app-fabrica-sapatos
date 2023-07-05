package com.example.fabricasapatos.ui.activities.order

//import androidx.compose.material.icons.Icons
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetClientsUseCase
import com.example.fabricasapatos.domain.item.usecases.contracts.ICreateItemUseCase
import com.example.fabricasapatos.domain.order.model.Order
import com.example.fabricasapatos.domain.order.usecases.contracts.ICreateOrderUseCase
import com.example.fabricasapatos.domain.order.usecases.contracts.IGetOrdersByClientUseCase
import com.example.fabricasapatos.domain.product.model.Product
import com.example.fabricasapatos.domain.product.usecases.contracts.IGetProductsUseCase
import com.example.fabricasapatos.principal.AppBar
import com.example.fabricasapatos.principal.DrawerHeader
import com.example.fabricasapatos.principal.NavigationDrawer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2

@AndroidEntryPoint
class CreateOrderActivity : ComponentActivity() {

    @Inject
    lateinit var getOrdersByClientUseCase: IGetOrdersByClientUseCase

    @Inject
    lateinit var getClientsUseCase: IGetClientsUseCase

    @Inject
    lateinit var createOrderUseCase: ICreateOrderUseCase

    @Inject
    lateinit var createItemUseCase: ICreateItemUseCase

    @Inject
    lateinit var getProductsUseCase: IGetProductsUseCase

    private var orderList = mutableStateOf(emptyList<Order>())
    private val clientsList = mutableStateOf(emptyList<Client>())
    private val productList = mutableStateOf(emptyList<Product>())

    fun getOrders(clientCpf : String){
        lifecycleScope.launch {
            orderList.value = getOrdersByClientUseCase(clientCpf)
        }
    }

    fun getClients() {
        lifecycleScope.launch {
            clientsList.value = getClientsUseCase()
        }
    }

    fun createOrder(clientCpf : String) {
        lifecycleScope.launch {
            createOrderUseCase(clientCpf)
        }
    }

    fun createItem(productId : Int , quantity : Int) {
        lifecycleScope.launch {
            createItemUseCase(productId, quantity)
        }
    }

    fun getProducts() {
        lifecycleScope.launch {
            productList.value = getProductsUseCase()
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getClients()
        getProducts()
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
                // DropdownMenuExample(clientsList , ::getClients)
                TelaNovoPedido(::createOrder, clientsList , productList , ::createItem)

            }
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaNovoPedido(
    createOrder: KFunction1<String, Unit>,
    clientList: MutableState<List<Client>>,
    productList: MutableState<List<Product>>,
    createItem: KFunction2<Int, Int, Unit>
) {
    val textValueClient = remember { mutableStateOf("") }
    val textValueProduct = remember { mutableStateOf("") }

    var selectedClient by remember { mutableStateOf<Client?>(null) }
    var selectedClientCpf by remember { mutableStateOf("") }
    var selectedProduct by remember  { mutableStateOf<Product?>(null) }
    var selectedProductId by remember { mutableStateOf(null) }
    var quantity : Int = 0
    val pedidoList = remember { mutableStateListOf<String>() } // Lista para armazenar os pedidos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ClientSelect2(
            clientList = clientList,
            selectedClientCpf = selectedClientCpf
        ) { client ->
            selectedClientCpf = client.cpf
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
                    pedidoList.add(selectedProduct?.description.toString())
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
                PedidoCard(pedido = pedido, numero = index + 1, quantidade = quantity) { novaQuantidade ->
                    quantity = novaQuantidade
                }
            }
        }

        // Botão "Salvar"
        Button(
            onClick = {
                // Ação do botão
                createOrder(selectedClientCpf)
                selectedProduct?.id?.let { createItem(it.toInt() , quantity) }

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
fun ProductSelect(
    productList: MutableState<List<Product>>,
    selectedProduct: Product?,
    onProductSelected: (Product) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize()) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.width(250.dp)
        ) {
            Text(selectedProduct?.description ?: "Selecione um produto")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(),
            content = {
                productList.value.forEachIndexed { index, product ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onProductSelected(product)
                        },
                        modifier = Modifier
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        ProductItem(product)
                    }
                }
            }
        )
    }
}

@Composable
fun ProductItem(product: Product) {
    Text(text = product.description)
}


@Composable
fun ClientItem2(client: Client) {
    Text(text = client.name)
}


@Composable
fun ClientSelect2(clientList: MutableState<List<Client>>, selectedClientCpf: String, onClientSelected: (Client) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize()) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.width(250.dp)
        ) {
            val selectedClient = clientList.value.find { it.cpf == selectedClientCpf }
            Text(selectedClient?.name ?: "Selecione um cliente")
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
fun PedidoCard(
    pedido: String,
    numero: Int,
    quantidade: Int,
    onQuantidadeChanged: (Int) -> Unit
) {
    var quantidadeAtual by remember { mutableStateOf(quantidade) }

    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = " $numero: $pedido",
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = {
                    quantidadeAtual -= 1
                    onQuantidadeChanged(quantidadeAtual)
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
                    onQuantidadeChanged(quantidadeAtual)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Adicionar"
                )
            }
        }
    }
}


