package com.example.fabricasapatos.principal

//import com.fabricasapatos.navigationdrawercompose.ui.theme.NavigationDrawerComposeTheme
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.example.fabricasapatos.domain.client.usecases.contracts.ICreateClientUseCase
import com.example.fabricasapatos.domain.item.usecases.GetItemsByOrderUseCase
import com.example.fabricasapatos.domain.item.usecases.contracts.ICreateItemUseCase
import com.example.fabricasapatos.ui.activities.client.GetClientsActivity
import com.example.fabricasapatos.ui.activities.product.CreateProductActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var createItemUseCase: ICreateItemUseCase

  @Inject
  lateinit var createClientUseCase: ICreateClientUseCase

  @Inject
  lateinit var getItemsByOrderUseCase: GetItemsByOrderUseCase

  @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

      setContent {
          val scaffoldState = rememberScaffoldState()
          val scope = rememberCoroutineScope()
          Scaffold(
              scaffoldState = scaffoldState,
              topBar = {
                  com.example.fabricasapatos.principal.AppBar(
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

              Box(
                  modifier = Modifier
                      .fillMaxSize()
                      .padding(horizontal = 15.dp, vertical = 8.dp),
                  contentAlignment = Alignment.Center
              ) {
                  ImageFromUrl("https://images.pexels.com/photos/12484292/pexels-photo-12484292.jpeg")
              }

          }
      }


    lifecycleScope.launch {
      //val product = createProductUseCase("descrevendo o sandalia", 150.0, Uri.EMPTY)
      //val list = getDescriptionAndIdProductsUseCase()
      // val list = getItemsByOrderUseCase(10)
      //Log.i("TESTE", list.toString())
      //val order = createOrderUseCase("1")
      //val item = createItemUseCase(10, 0, 1)
      val client = createClientUseCase("567", "maria", "738948", "rua x", "@maria")
    }
  }
}

@Composable
fun ImageFromUrl(url: String) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        }
    )

    Image(
        painter = painter,
        contentDescription = "Descrição da imagem",
        contentScale = ContentScale.Fit // Ajuste o ContentScale conforme necessário
    )
}

@Composable
fun NavigationDrawer (){
  val context = LocalContext.current
  DrawerBody(
    items = listOf(
      MenuItem(
        id = "cliente",
        title = "Clientes",
        contentDescription = "Tela de Clientes",
        icon = Icons.Default.Person
      ),
      MenuItem(
        id = "produto",
        title = "Produtos",
        contentDescription = "Tela de Produtos",
        icon = Icons.Default.Star
      ),
      MenuItem(
        id = "pedido",
        title = "Pedidos",
        contentDescription = "Fazer Pedidos",
        icon = Icons.Default.ShoppingCart
      ),
    ),
    onItemClick = {
      when(it.id){
        "cliente" -> {context.startActivity(Intent(context, GetClientsActivity::class.java))}
        "produto" -> {context.startActivity(Intent(context, CreateProductActivity::class.java))}
      }
    }
  )
}

