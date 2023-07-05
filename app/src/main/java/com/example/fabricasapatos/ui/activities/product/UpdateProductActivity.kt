package com.example.fabricasapatos.ui.activities.product

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.example.fabricasapatos.domain.product.model.Product
import com.example.fabricasapatos.domain.product.usecases.contracts.IUpdateProductUseCase
import com.example.fabricasapatos.principal.AppBar
import com.example.fabricasapatos.principal.DrawerHeader
import com.example.fabricasapatos.principal.NavigationDrawer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KFunction4

@AndroidEntryPoint
class UpdateProductActivity : ComponentActivity() {

    @Inject
    lateinit var updateProductUseCase: IUpdateProductUseCase

     fun updateProduct(id : Int , description : String , value : Double , imagem : Uri) {
       lifecycleScope.launch {
            updateProductUseCase(id,description,value,imagem)
         }
     }
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         val product = intent.getParcelableExtra<Product>("product")
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
                 if (product != null) {
                    MyScreen3(product,::updateProduct)
                 }
            }

        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen3(product: Product, funcao: KFunction4<Int, String, Double, Uri, Unit>) {
    // Campos de texto para armazenar as informações digitadas pelo usuário
    val textField1Value = remember { mutableStateOf(product.id.toString()) }
    val textField2Value = remember { mutableStateOf(product.description) }
    val textField3Value = remember { mutableStateOf(product.value.toString()) }

    val enabled = remember { mutableStateOf(false) }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    var imageUrl by remember { mutableStateOf(product.imageUrl) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }

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
            label = { Text("Id") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled.value
        )

        OutlinedTextField(
            value = textField2Value.value,
            onValueChange = { textField2Value.value = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth(),
            )
        OutlinedTextField(
            value = textField3Value.value,
            onValueChange = { textField3Value.value = it },
            label = { Text("Valor") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(
            onClick = { galleryLauncher.launch("image/*") },
            colors =  ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = MaterialTheme.colorScheme.surfaceTint),
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        ) {
            Text(text = "Selecione a imagem da galeria")
        }

        selectedImageUri?.let { uri ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 15.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberImagePainter(uri),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp, 8.dp)
                        .size(100.dp)
                )
            }
        }

        // Botão "Salvar"
        Button(
            onClick = {
                if (
                    textField1Value.value.isNotBlank() &&
                    textField2Value.value.isNotBlank() &&
                    selectedImageUri != null
                ) {
                    // Ação do botão
                    selectedImageUri?.let { uri ->
                        funcao(
                            textField1Value.value.toInt(),
                            textField2Value.value,
                            textField3Value.value.toDouble(),
                            uri
                        )
                    }
                    // Limpar os campos de texto
                    textField1Value.value = ""
                    textField2Value.value = ""
                    textField3Value.value = ""
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



