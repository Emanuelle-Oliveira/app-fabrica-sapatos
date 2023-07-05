package com.example.fabricasapatos.ui.activities.client

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.usecases.contracts.ICreateClientUseCase
import com.example.fabricasapatos.principal.AppBar
import com.example.fabricasapatos.principal.DrawerHeader
import com.example.fabricasapatos.principal.NavigationDrawer
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

  @OptIn(ExperimentalMaterial3Api::class)
  @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

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
        MyScreen(::createClient)
      }
    }

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

  val context = LocalContext.current

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 15.dp, vertical = 16.dp),
    //verticalArrangement = Arrangement.SpaceBetween, // Ajuste o arranjo vertical aqui
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    OutlinedTextField(
      value = textField1Value.value,
      onValueChange = { textField1Value.value = it },
      label = { Text("CPF") },
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

    OutlinedTextField(
      value = textField2Value.value,
      onValueChange = { textField2Value.value = it },
      label = { Text("Nome") },
      modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
      value = textField3Value.value,
      onValueChange = { textField3Value.value = it },
      label = { Text("Telefone") },
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

    OutlinedTextField(
      value = textField4Value.value,
      onValueChange = { textField4Value.value = it },
      label = { Text("Endereço") },
      modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
      value = textField5Value.value,
      onValueChange = { textField5Value.value = it },
      label = { Text("Instagram") },
      modifier = Modifier.fillMaxWidth()
    )

    // Botão "Salvar"
    Button(
      onClick = {
        if (
          textField1Value.value.isNotBlank() &&
          textField2Value.value.isNotBlank() &&
          textField3Value.value.isNotBlank() &&
          textField4Value.value.isNotBlank() &&
          textField5Value.value.isNotBlank()
        ) {
          // Ação do botão
          val client = Client(
            textField1Value.value,
            textField2Value.value,
            textField3Value.value,
            textField4Value.value,
            textField5Value.value
          )
          funcao(client)

          // Limpar os campos de texto
          textField1Value.value = ""
          textField2Value.value = ""
          textField3Value.value = ""
          textField4Value.value = ""
          textField5Value.value = ""
        } else {
          Toast.makeText(context, "Todos os campos devem estar preenchidos!", Toast.LENGTH_SHORT).show()
        }
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