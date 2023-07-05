package com.example.fabricasapatos.ui.activities.product


//import androidx.car.app.sample.pickimagefromgalleryinjetpackcompose.ui.theme.PickImageFromGalleryInJetpackComposeTheme
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.example.fabricasapatos.domain.product.model.Product
import com.example.fabricasapatos.domain.product.usecases.contracts.IDeleteProductUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.IGetProductsUseCase
import com.example.fabricasapatos.principal.AppBar
import com.example.fabricasapatos.principal.DrawerHeader
import com.example.fabricasapatos.principal.NavigationDrawer
import com.example.fabricasapatos.ui.activities.client.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

@AndroidEntryPoint
class GetProductsActivity : ComponentActivity() {

    @Inject
    lateinit var getProductsUseCase: IGetProductsUseCase

    @Inject
    lateinit var deleteProductUseCase: IDeleteProductUseCase

    private val productList = mutableStateOf(emptyList<Product>())

    fun deleteProduct(id: Int) {
        lifecycleScope.launch {
            deleteProductUseCase(id)
        }
    }

    fun getProducts() {
        lifecycleScope.launch {
            productList.value = getProductsUseCase()
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
                },
                content = {
                    Elementos(productList, ::deleteProduct, ::getProducts)
                }
            )
        }

        lifecycleScope.launch {
            productList.value = getProductsUseCase()
            //println("Tamanho da lista de produtos: ${productList.value.size}")
            Log.i("TESTE", productList.value.size.toString())
        }
    }
}

@Composable
fun ImageFromUrl2(url: String) {
    val painter = rememberImagePainter(url)
    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(percent = 50)) // Define o formato redondo
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Tela(productList: State<List<Product>>, deleteProduct: KFunction1<Int, Unit>, getProducts: KFunction0<Unit>){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 15.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Elementos(productList, deleteProduct, getProducts)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Elementos(productList: State<List<Product>>, deleteProduct: KFunction1<Int, Unit>, getProducts: KFunction0<Unit>){
    val listState = rememberLazyListState()
    //listClients.add(Client("123", "joana", "23445", "rua x", "@joana"))
    //listClients.add(Client("456", "maria", "685878", "rua z", "@maria"))

    // Altura do TopAppBar
    val AppBarHeight = 60.dp

    val context = LocalContext.current

    Column() {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        //OnClick Method
                        context.startActivity(Intent(context, CreateProductActivity::class.java))
                    },
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add FAB",
                        tint = Color.White,
                    )
                }
            },
            content = {
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(
                        items = productList.value,
                        itemContent = {
                            ItemDaLista(productList, product = it, deleteProduct, getProducts)
                        })
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDaLista(productList: State<List<Product>>, product: Product, deleteProduct: KFunction1<Int, Unit>, getProducts: KFunction0<Unit>) {

    val context = LocalContext.current
    val valueString = String.format("%.2f", product.value)

    /*var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
      DialogDeleteClient(client, deleteClient, getClients, onClose = { showDialog = false })
    }*/

    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value){
        DialogDeleteClient(product , deleteProduct, getProducts , setShowDialog = { showDialog.value = it })
    }

    androidx.compose.material.Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        backgroundColor = MaterialTheme.colorScheme.inversePrimary
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start),
                ) {
                    ImageFromUrl2(product.imageUrl)
                }
                Text(
                    text = product.description.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "R$ $valueString",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Box(
                modifier = Modifier.wrapContentSize(Alignment.CenterEnd)
            ) {
                IconButton(
                    onClick = {
                        //context.startActivity(Intent(context, CreateClientActivity::class.java))
                        /*context.startActivity( Intent(context, UpdateClientActivity::class.java).let {
                           it.putStringArrayListExtra("5", client)
                           register.launch(it)
                         })*/

                        var mochila = Bundle()
                        mochila.putParcelable("product", product)
                        context.startActivity((Intent(context, UpdateProductActivity::class.java)).putExtras(mochila))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Lápis"
                    )
                }
            }

            Box(
                modifier = Modifier.wrapContentSize(Alignment.CenterEnd)
            ) {
                IconButton(

                    onClick = {
                        showDialog.value = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Lixeira"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogDeleteClient( product: Product, deleteProduct: KFunction1<Int, Unit>, getProducts: KFunction0<Unit>,setShowDialog: (Boolean) -> Unit = {}) {

    androidx.compose.material3.AlertDialog(
        onDismissRequest =  { setShowDialog(false) },//onClose,
        title = { Text("Excluir produto") },
        text = { Text("Tem certeza de que deseja excluir este produto?") },
        confirmButton = {
            androidx.compose.material3.Button(
                onClick = {
                    deleteProduct(product.id)
                    getProducts()
                    setShowDialog(false)
                }
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            androidx.compose.material3.Button(
                onClick = {
                    //showDialog = false
                    setShowDialog(false)
                }
            ) {
                Text("Cancelar")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {

    var selectImages by remember { mutableStateOf(listOf<Uri>()) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            selectImages = it
        }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { galleryLauncher.launch("image/*") },
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp)
        ) {
            Text(text = "Selecionar imagem na galeria")
        }

        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(selectImages) { uri ->
                /*Image(
                    painter = rememberImagePainter(uri),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp, 8.dp)
                        .size(100.dp)
                        .clickable {

                        }
                )*/
            }
        }

    }
}
