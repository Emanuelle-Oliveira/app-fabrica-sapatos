package com.example.fabricasapatos.ui.activities.client

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      FabricaSapatosTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Greeting3("Buscar clientes")
        }
      }
    }

    /*lifecycleScope.launch {
      val clientsList = getClientsUseCase()
      Log.i("TESTE", clientsList.toString())
    }*/
  }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
  Text(
    text = name,
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
  FabricaSapatosTheme {
    Greeting3("Buscar clientes")
  }
}