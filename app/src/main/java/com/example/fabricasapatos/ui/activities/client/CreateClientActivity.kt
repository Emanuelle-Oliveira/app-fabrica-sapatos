package com.example.fabricasapatos.ui.activities.client

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.usecases.contracts.ICreateClientUseCase
import com.example.fabricasapatos.ui.activities.client.ui.theme.FabricaSapatosTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KFunction1

@AndroidEntryPoint
class CreateClientActivity(): ComponentActivity() {

  @Inject
  lateinit var createClientUseCase: ICreateClientUseCase

  val cliente = Client(
  cpf = "",
  name = "",
  phone = "",
  address = "",
  instagram = ""
  )
  fun createClient(client: Client) {
    lifecycleScope.launch {
      createClientUseCase(client.cpf, client.name, client.phone, client.address, client.instagram)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      FabricaSapatosTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          //Greeting2("Criar cliente")
          //FuncaoListaPessoas()
          MyScreen(::createClient)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen(funcao: KFunction1<Client, Unit>) {
  // Campos de texto para armazenar as informações digitadas pelo usuário
  val textField1Value = remember { mutableStateOf("") }
  val textField2Value = remember { mutableStateOf("") }
  val textField3Value = remember { mutableStateOf("") }
  val textField4Value = remember { mutableStateOf("") }
  val textField5Value = remember { mutableStateOf("") }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    TextField(
      value = textField1Value.value,
      onValueChange = { textField1Value.value = it },
      label = { Text("Campo 1") },
      modifier = Modifier.fillMaxWidth()
    )

    TextField(
      value = textField2Value.value,
      onValueChange = { textField2Value.value = it },
      label = { Text("Campo 2") },
      modifier = Modifier.fillMaxWidth()
    )

    TextField(
      value = textField3Value.value,
      onValueChange = { textField3Value.value = it },
      label = { Text("Campo 3") },
      modifier = Modifier.fillMaxWidth()
    )

    TextField(
      value = textField4Value.value,
      onValueChange = { textField4Value.value = it },
      label = { Text("Campo 4") },
      modifier = Modifier.fillMaxWidth()
    )

    TextField(
      value = textField5Value.value,
      onValueChange = { textField5Value.value = it },
      label = { Text("Campo 5") },
      modifier = Modifier.fillMaxWidth()
    )

    // Botão "Salvar"
    Button(
      onClick = {
        // Ação do botão
                val client = Client (
                  textField1Value.value.toString(),
                  textField1Value.value.toString(),
                  textField1Value.value.toString(),
                  textField1Value.value.toString(),
                  textField1Value.value.toString()
                  )
        funcao(client)
      },
      colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor =  MaterialTheme.colorScheme.onErrorContainer ),
      modifier = Modifier.align(Alignment.End)
    ) {
      Text(text = "Salvar")
    }
  }
}




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuncaoListaPessoas() {
  val estadoDaLista = remember { mutableStateListOf<String>() }
  val estadoCampoDeTexto = remember { mutableStateOf(TextFieldValue()) }


  Scaffold(
    content = {
      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Conteúdo da coluna
        Row(
          Modifier
            .fillMaxWidth()
            .height(60.dp)
        ) {
          TextField(
            value = estadoCampoDeTexto.value,
            onValueChange = {
              estadoCampoDeTexto.value = it
            },
            placeholder = { Text(text = "Insira o novo item") },
            keyboardOptions = KeyboardOptions(
              capitalization = KeyboardCapitalization.None,//Sem restrições (letras/números).
              autoCorrect = true,
            ),
            textStyle = TextStyle(
              color = Color.Black,
              fontSize = TextUnit.Unspecified,
              fontFamily = FontFamily.SansSerif
            ),
            maxLines = 1,
          )
        }

        // Outros elementos da coluna
        Spacer(modifier = Modifier.height(5.dp))

        Button(
          onClick = {
            estadoDaLista.add(estadoCampoDeTexto.value.text)
            estadoCampoDeTexto.value = TextFieldValue("")
          },
          modifier = Modifier
            .height(48.dp) // Defina a altura desejada do botão
            .width(80.dp) // O botão preenche a largura total
            .background(color = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_errorContainer)
            .border(1.dp, Color.Black,RoundedCornerShape(12.dp))
            .padding(top = 64.dp)
        ) {
          Text(text = "Salvar",
                  style = TextStyle(
                  color = Color.White, // Defina a cor desejada aqui
             // Tamanho do texto
          )
          )
        }
      }
    }
  )



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