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
import com.example.fabricasapatos.domain.client.usecases.contracts.IDeleteClientUseCase
import com.example.fabricasapatos.ui.activities.client.ui.theme.FabricaSapatosTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DeleteClientActivity : ComponentActivity() {

  @Inject
  lateinit var deleteClientUseCase: IDeleteClientUseCase

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      FabricaSapatosTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Greeting5("Deletar cliente")
        }
      }
    }

    val cpf = "2"

    /*lifecycleScope.launch {
      val cpf = deleteClientUseCase(cpf)
    }*/
  }
}

@Composable
fun Greeting5(name: String, modifier: Modifier = Modifier) {
  Text(
    text = name,
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
  FabricaSapatosTheme {
    Greeting5("Deletar cliente")
  }
}