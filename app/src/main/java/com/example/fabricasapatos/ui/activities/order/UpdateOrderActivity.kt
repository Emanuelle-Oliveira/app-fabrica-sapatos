package com.example.fabricasapatos.ui.activities.order

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetClientsUseCase
import com.example.fabricasapatos.domain.order.model.Order
import com.example.fabricasapatos.domain.order.usecases.contracts.IGetOrdersByClientUseCase
import com.example.fabricasapatos.domain.order.usecases.contracts.IUpdateOrderUseCase
import com.example.fabricasapatos.principal.AppBar
import com.example.fabricasapatos.principal.DrawerHeader
import com.example.fabricasapatos.principal.NavigationDrawer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KFunction2

@AndroidEntryPoint
class UpdateOrderActivity : ComponentActivity() {
    @Inject
    lateinit var getOrdersByClientUseCase: IGetOrdersByClientUseCase

    @Inject
    lateinit var getClientsUseCase: IGetClientsUseCase

    @Inject
    lateinit var updateOrderUseCase: IUpdateOrderUseCase

    private var orderList = mutableStateOf(emptyList<Order>())
    private val clientsList = mutableStateOf(emptyList<Client>())

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

    fun updateOrder(idOrder : Int , clientCpf: String) {
        lifecycleScope.launch {
            updateOrderUseCase(idOrder, clientCpf)
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getClients()
        val order = intent.getParcelableExtra<Order>("order")
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
                TelaUpdatePedido(::updateOrder, clientsList, order)

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaUpdatePedido(funcao: KFunction2<Int, String, Unit>, clientList: MutableState<List<Client>>, order: Order?) {
    val textValueClient = remember { mutableStateOf("") }
    val textValueProduct = remember { mutableStateOf("") }

    var selectedClient by remember { mutableStateOf<Client?>(null) }
    var selectedClientCpf by remember { mutableStateOf("") }

    val pedidoList = remember { mutableStateListOf<String>() } // Lista para armazenar os pedidos

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
            OutlinedTextField(
                value = textValueProduct.value,
                onValueChange = { textValueProduct.value = it },
                label = { Text("Pedido") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    // Adicionar o valor do OutlinedTextField à lista de pedidos
                    pedidoList.add(textValueProduct.value)
                    // Limpar o campo de texto após adicionar o pedido
                    textValueProduct.value = ""
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
                PedidoCard(pedido = pedido, numero = index + 1)
            }
        }
        // Botão "Salvar"
        Button(
            onClick = {
                // Ação do botão
                if (order != null) {
                    funcao(order.id , selectedClientCpf)
                }
                // Limpar os campos de texto
                textValueClient.value = ""
                textValueProduct.value = ""

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
        Log.i("teste", client.cpf)
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
