package com.example.fabricasapatos.ui.activities.client

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.usecases.contracts.ICreateClientUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetClientsUseCase
import com.example.fabricasapatos.ui.activities.client.ui.theme.FabricaSapatosTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GetClientsActivity : ComponentActivity() {

  @Inject
  lateinit var getClientsUseCase: IGetClientsUseCase

  private val clientsList = mutableStateOf(emptyList<Client>())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycleScope.launch {
      clientsList.value = getClientsUseCase()
      Log.i("TESTE", clientsList.toString())
    }
    setContent {
      Tela(clientsList)
    }
  }
}

@Composable
fun Tela(clientsList: State<List<Client>>){
  Column(modifier = Modifier.fillMaxSize().padding(15.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Elementos(clientsList)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Elementos(clientsList: State<List<Client>>){
  val listState = rememberLazyListState()
  //listClients.add(Client("123", "joana", "23445", "rua x", "@joana"))
  //listClients.add(Client("456", "maria", "685878", "rua z", "@maria"))

  val contexto = LocalContext.current

  Column() {
    Scaffold(
      content = {
        LazyColumn(
          state = listState,
          contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
          items(
            items = clientsList.value,
            itemContent = {
              ItemDaLista(client = it)
            })
        }
      }
    )
  }
}

@Composable
fun ItemDaLista(client: Client) {
  Card(
    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
    shape = RoundedCornerShape(corner = CornerSize(16.dp))
  ) {
    Row {
      Column(
        modifier = Modifier
          .padding(16.dp)
          .fillMaxWidth()
          .align(Alignment.CenterVertically)) {
        Text(text = client.name.toString(), style = typography.titleLarge)
        Text(text = client.address, style = typography.bodySmall)
      }
    }
  }
}