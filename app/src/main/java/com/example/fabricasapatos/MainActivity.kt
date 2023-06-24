package com.example.fabricasapatos

import android.content.Intent
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
import com.example.fabricasapatos.ui.activities.client.CreateClientActivity
import com.example.fabricasapatos.ui.activities.client.GetClientsActivity
import com.example.fabricasapatos.ui.activities.client.UpdateClientActivity
import com.example.fabricasapatos.ui.theme.FabricaSapatosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      FabricaSapatosTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Greeting("Android")
        }
      }
    }
    val intent = Intent(applicationContext, UpdateClientActivity::class.java)
    startActivity(intent)
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  FabricaSapatosTheme {
    Greeting("Android")
  }
}