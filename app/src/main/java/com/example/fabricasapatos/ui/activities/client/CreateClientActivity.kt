package com.example.fabricasapatos.ui.activities.client

import android.os.Bundle
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
import com.example.fabricasapatos.domain.usecases.CreateClientUseCase
import com.example.fabricasapatos.domain.usecases.contracts.ICreateClientUseCase
import com.example.fabricasapatos.ui.activities.client.ui.theme.FabricaSapatosTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CreateClientActivity(): ComponentActivity() {

  @Inject
  lateinit var createClientUseCase: ICreateClientUseCase

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      FabricaSapatosTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Greeting2("Criar cliente")
        }
      }
    }
    val cpf = "3"
    val name = "maria"
    val phone = "67890"
    val address = "Rua C, n 3"
    val instagram = "@maria"

    /*
    lifecycleScope.launch {
      val client = createClientUseCase(cpf, name, phone, address, instagram)
    }
    */
  }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
  Text(
    text = name,
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
  FabricaSapatosTheme {
    Greeting2("Criar cliente")
  }
}