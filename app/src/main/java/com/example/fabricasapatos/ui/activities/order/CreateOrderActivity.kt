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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
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
import com.example.fabricasapatos.domain.order.usecases.contracts.ICreateOrderUseCase
import com.example.fabricasapatos.domain.order.usecases.contracts.IGetOrdersByClientUseCase
import com.example.fabricasapatos.principal.AppBar
import com.example.fabricasapatos.principal.DrawerHeader
import com.example.fabricasapatos.principal.NavigationDrawer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

@AndroidEntryPoint
class CreateOrderActivity : ComponentActivity() {

    @Inject
    lateinit var getOrdersByClientUseCase: IGetOrdersByClientUseCase

    @Inject
    lateinit var getClientsUseCase: IGetClientsUseCase

    @Inject
    lateinit var createOrderUseCase: ICreateOrderUseCase

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

    fun createOrder(clientCpf : String) {
        lifecycleScope.launch {
            createOrderUseCase(clientCpf)
        }
    }
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getClients()
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
                TelaNovoPedido(::createOrder)

            }
        }


    }
}

@Composable
fun DropdownMenuExample(orderList: MutableState<List<Client>>, clientList: KFunction0<Unit>) {

    var expanded by remember { mutableStateOf(false) }
    var selectedClient by remember { mutableStateOf<Client?>(null) }

    Column {
        Text(
            text = "Selected client: ${selectedClient?.name ?: "None"}",
            modifier = Modifier.padding(16.dp)
        )
        TextButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = selectedClient?.name ?: "Select a client")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaNovoPedido(funcao: KFunction1<String, Unit>) {
    val textValueClient = remember { mutableStateOf("") }
    val textValueProduct = remember { mutableStateOf("") }

    val pedidoList = remember { mutableStateListOf<String>() } // Lista para armazenar os pedidos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        OutlinedTextField(
            value = textValueClient.value,
            onValueChange = { textValueClient.value = it },
            label = { Text("Cliente") },
            modifier = Modifier.fillMaxWidth()
        )

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
                funcao(textValueClient.value)

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
fun PedidoCard(pedido: String, numero: Int) {
    var quantidade by remember { mutableStateOf(1) }
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$numero",
                modifier = Modifier.width(40.dp)
            )
            Text(text = pedido, modifier = Modifier.weight(1f))
            IconButton(
                onClick = { quantidade-- },
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(Icons.Default.Remove, contentDescription = "Diminuir quantidade")
            }
            Text(text = quantidade.toString())
            IconButton(
                onClick = { quantidade++ },
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(Icons.Rounded.Add, contentDescription = "Aumentar quantidade")
            }
        }
    }
}