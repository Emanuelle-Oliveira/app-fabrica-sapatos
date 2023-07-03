package com.example.fabricasapatos.ui.activities.product

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.example.fabricasapatos.domain.product.usecases.contracts.ICreateProductUseCase
import com.example.fabricasapatos.principal.AppBar
import com.example.fabricasapatos.principal.DrawerHeader
import com.example.fabricasapatos.principal.NavigationDrawer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KFunction3

@AndroidEntryPoint
class CreateProductActivity : ComponentActivity() {

    @Inject
    lateinit var createProductUseCase: ICreateProductUseCase


    fun createProduct(description : String , value : Double , imagem : Uri) {
        lifecycleScope.launch {
            createProductUseCase(description,value,imagem)
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

                MyScreen(::createProduct)
            }

        }
    }

}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen(funcao: KFunction3<String, Double, Uri, Unit>) {
    // Campos de texto para armazenar as informações digitadas pelo usuário
    val textField1Value = remember { mutableStateOf("") }
    val textField2Value = remember { mutableStateOf("") }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }




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
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = textField2Value.value,
            onValueChange = { textField2Value.value = it },
            label = { Text("Valor") },
            modifier = Modifier.fillMaxWidth(),

            )
        Button(
            onClick = { galleryLauncher.launch("image/*") },
            colors =  ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = MaterialTheme.colorScheme.surfaceTint),
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        ) {
            Text(text = "Pick Image From Gallery")
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
                // Ação do botão
                selectedImageUri?.let { uri ->
                    funcao(textField1Value.value, textField2Value.value.toDouble(), uri)
                }
                // Limpar os campos de texto
                textField1Value.value = ""
                textField2Value.value = ""

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

