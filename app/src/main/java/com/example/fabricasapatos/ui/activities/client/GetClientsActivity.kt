package com.example.fabricasapatos.ui.activities.client

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
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.usecases.contracts.IDeleteClientUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetClientsUseCase
import com.example.fabricasapatos.ui.activities.principal.AppBar
import com.example.fabricasapatos.ui.activities.principal.DrawerHeader
import com.example.fabricasapatos.ui.activities.principal.NavigationDrawer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1



@AndroidEntryPoint
class GetClientsActivity : ComponentActivity() {

  @Inject
  lateinit var getClientsUseCase: IGetClientsUseCase

  @Inject
  lateinit var deleteClientUseCase: IDeleteClientUseCase

  private val clientsList = mutableStateOf(emptyList<Client>())

  fun deleteClient(cpf: String) {
    lifecycleScope.launch {
      deleteClientUseCase(cpf)
    }
  }

  fun getClients() {
    lifecycleScope.launch {
      clientsList.value = getClientsUseCase()
    }
  }

  @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
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
        Tela(clientsList, ::deleteClient, ::getClients)
      }

    }
  }
}


@Composable
fun Tela(clientsList: State<List<Client>>, deleteClient: KFunction1<String, Unit>, getClients: KFunction0<Unit>){
  Column(modifier = Modifier
    .fillMaxSize()
    .padding(horizontal = 15.dp, vertical = 8.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Elementos(clientsList, deleteClient, getClients)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Elementos(clientsList: State<List<Client>>, deleteClient: KFunction1<String, Unit>, getClients: KFunction0<Unit>){
  val listState = rememberLazyListState()
  //listClients.add(Client("123", "joana", "23445", "rua x", "@joana"))
  //listClients.add(Client("456", "maria", "685878", "rua z", "@maria"))

  // Altura do TopAppBar
  val AppBarHeight = 60.dp

  val context = LocalContext.current

  Column() {
    Scaffold(
      floatingActionButton = {
        FloatingActionButton(
          onClick = {
            //OnClick Method
            context.startActivity(Intent(context, CreateClientActivity::class.java))
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
        LazyColumn(
          state = listState,
          contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
          items(
            items = clientsList.value,
            itemContent = {
              ItemDaLista(clientsList, client = it, deleteClient, getClients)
            })
        }
      }
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDaLista(clientsList: State<List<Client>>, client: Client, deleteClient: KFunction1<String, Unit>, getClients: KFunction0<Unit>) {

  val context = LocalContext.current

  val showDialog = remember { mutableStateOf(false) }
  if (showDialog.value){
    DialogDeleteClient(client , deleteClient, getClients , setShowDialog = { showDialog.value = it })
  }

  Card(
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
        Text(
          text = client.name.toString(),
          style = typography.titleLarge
        )
        Text(
          text = "CPF: ${client.cpf.toString()}",
          style = typography.bodySmall
        )
      }

      Box(
        modifier = Modifier.wrapContentSize(Alignment.CenterEnd)
      ) {
        IconButton(
          onClick = {
            var mochila = Bundle()
            mochila.putParcelable("client", client)
            context.startActivity((Intent(context, UpdateClientActivity::class.java)).putExtras(mochila))
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
fun DialogDeleteClient( client: Client, deleteClient: KFunction1<String, Unit>, getClients: KFunction0<Unit>,setShowDialog: (Boolean) -> Unit = {}) {

    androidx.compose.material3.AlertDialog(
      onDismissRequest =  { setShowDialog(false) },//onClose,
      title = { Text("Excluir cliente") },
      text = { Text("Tem certeza de que deseja excluir este cliente?") },
      confirmButton = {
        androidx.compose.material3.Button(
          onClick = {
            deleteClient(client.cpf)
            getClients()
            setShowDialog(false)
          }
        ) {
          Text("Confirmar")
        }
      },
      dismissButton = {
        androidx.compose.material3.Button(
          onClick = {
            //showDialog = false
            setShowDialog(false)
          }
        ) {
          Text("Cancelar")
        }
      }
    )
}