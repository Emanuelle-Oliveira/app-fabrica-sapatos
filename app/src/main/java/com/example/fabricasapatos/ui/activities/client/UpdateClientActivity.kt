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
import com.example.fabricasapatos.domain.client.usecases.contracts.IUpdateClientUseCase
import com.example.fabricasapatos.ui.activities.client.ui.theme.FabricaSapatosTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UpdateClientActivity : ComponentActivity() {

  @Inject
  lateinit var updateClientUseCase: IUpdateClientUseCase

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      FabricaSapatosTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Greeting4("Atualizar cliente")
        }
      }
    }

    val cpf = "1"
    val name = "Emanuelle"
    val phone = "988595208"
    val address = "Rua Anibal, n 299"
    val instagram = "@manuoliveira"

    /*lifecycleScope.launch {
      val client = updateClientUseCase(cpf, name, phone, address, instagram)
    }*/
  }
}

@Composable
fun Greeting4(name: String, modifier: Modifier = Modifier) {
  Text(
    text = name,
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
  FabricaSapatosTheme {
    Greeting4("Atualizar cliente")
  }
}