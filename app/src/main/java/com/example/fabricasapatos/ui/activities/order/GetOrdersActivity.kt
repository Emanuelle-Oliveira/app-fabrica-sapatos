package com.example.fabricasapatos.ui.activities.order

//import androidx.compose.ui.Modifier
//import androidx.compose.material.icons.Icons
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetClientsUseCase
import com.example.fabricasapatos.domain.order.model.Order
import com.example.fabricasapatos.domain.order.usecases.contracts.IDeleteOrderUseCase
import com.example.fabricasapatos.domain.order.usecases.contracts.IGetOrdersByClientUseCase
import com.example.fabricasapatos.principal.AppBar
import com.example.fabricasapatos.principal.DrawerHeader
import com.example.fabricasapatos.principal.NavigationDrawer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KFunction1

@AndroidEntryPoint
class GetOrdersActivity : ComponentActivity() {

    @Inject
    lateinit var getOrdersByClientUseCase: IGetOrdersByClientUseCase

    @Inject
    lateinit var getClientsUseCase: IGetClientsUseCase

    @Inject
    lateinit var deleteOrderUseCase: IDeleteOrderUseCase

    private val orderList = mutableStateOf(emptyList<Order>())

    private val clientsList = mutableStateOf(emptyList<Client>())

    fun deleteOrder(id: Int) {
        lifecycleScope.launch {
            deleteOrderUseCase(id)
        }
    }

    fun getClientsOrder(clientCpf : String) {
        lifecycleScope.launch {
            orderList.value = getOrdersByClientUseCase(clientCpf)
        }
    }

    fun getClients() {
        lifecycleScope.launch {
            clientsList.value = getClientsUseCase()
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
               TelaPedido(clientsList , ::getClientsOrder, orderList , ::deleteOrder)
            }

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPedido(
    clientList: MutableState<List<Client>>,
    getOrderByCpf: KFunction1<String, Unit>,
    orderList: MutableState<List<Order>>,
    deleteOrder: KFunction1<Int, Unit>
) {
    val listState = rememberLazyListState()
    //val orderList = mutableStateOf(emptyList<Order>())


    var selectedClient by remember { mutableStateOf<Client?>(null) }
    var selectedClientCpf by remember { mutableStateOf("") }

    val textValueClientOrder = remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //OnClick Method
                    context.startActivity(Intent(context, CreateOrderActivity::class.java))
                },
                containerColor = MaterialTheme.colorScheme.errorContainer,
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add FAB",
                    tint = Color.White,
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ClientSelect(
                        getOrderByCpf = getOrderByCpf,
                        clientList = clientList,
                        selectedClientCpf = selectedClientCpf
                    ) { client ->
                        selectedClient = client
                    }
                    Button(
                        onClick = {
                            getOrderByCpf(selectedClientCpf)
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Pesquisar",
                            tint = Color.White
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    state = listState,
                ) {
                    items(
                        items = orderList.value,
                        itemContent = {
                            CadaPedido( orderList , order = it , deleteOrder , getOrderByCpf )
                        })

                }



            }
        }
    )
}

@Composable
fun CadaPedido(orderList: MutableState<List<Order>>, order: Order, deleteOrder: KFunction1<Int, Unit>, getOrderByCpf: KFunction1<String, Unit>) {
    val context = LocalContext.current


    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value){
        DialogDeleteOrder(order , deleteOrder, getOrderByCpf) { showDialog.value = it }
    }

    androidx.compose.material.Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        backgroundColor = MaterialTheme.colorScheme.inversePrimary
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                androidx.compose.material3.Text(
                    text = order.id.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                androidx.compose.material3.Text(
                    text = order.date.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Box(
                modifier = Modifier.wrapContentSize(Alignment.CenterEnd)
            ) {
                IconButton(
                    onClick = {
                        var mochila = Bundle()
                        mochila.putParcelable("order", order)
                        context.startActivity((Intent(context, UpdateOrderActivity::class.java)).putExtras(mochila))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Lápis"
                    )
                }
            }

            Box(
                modifier = Modifier.wrapContentSize(Alignment.CenterEnd)
            ) {
                IconButton(

                    onClick = {
                        showDialog.value = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Lixeira"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogDeleteOrder(order: Order, deleteOrder: KFunction1<Int, Unit>, getOrderByCpf: KFunction1<String, Unit>, setShowDialog: (Boolean) -> Unit = {}) {

    androidx.compose.material3.AlertDialog(
        onDismissRequest =  { setShowDialog(false) },//onClose,
        title = { androidx.compose.material3.Text("Excluir cliente") },
        text = { androidx.compose.material3.Text("Tem certeza de que deseja excluir este cliente?") },
        confirmButton = {
            androidx.compose.material3.Button(
                onClick = {
                    deleteOrder(order.id)
                    getOrderByCpf(order.clientCpf)
                    setShowDialog(false)
                }
            ) {
                androidx.compose.material3.Text("Confirmar")
            }
        },
        dismissButton = {
            androidx.compose.material3.Button(
                onClick = {
                    //showDialog = false
                    setShowDialog(false)
                }
            ) {
                androidx.compose.material3.Text("Cancelar")
            }
        }
    )
}


@Composable
fun ClientItem(client: Client) {
    Text(text = client.name)
}


@Composable
fun ClientSelect(
    getOrderByCpf: KFunction1<String, Unit>,
    clientList: MutableState<List<Client>>,
    selectedClientCpf: String,
    onClientSelected: (Client) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var cpf by remember { mutableStateOf(selectedClientCpf) }

    fun getSelectedClientName(): String {
        val selectedClient = clientList.value.find { it.cpf == cpf }
        return selectedClient?.name ?: "Selecione um cliente"
    }

    Box(modifier = Modifier.wrapContentSize()) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.width(250.dp)
        ) {
            Text(getSelectedClientName())
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
                            cpf = client.cpf
                            onClientSelected(client)
                            getOrderByCpf(client.cpf)
                        },
                        modifier = Modifier
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        ClientItem(client)
                    }
                }
            }
        )
    }
}






