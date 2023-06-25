package com.example.fabricasapatos.principal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.lifecycleScope
import com.example.fabricasapatos.domain.item.usecases.contracts.ICreateItemUseCase
import com.example.fabricasapatos.ui.activities.client.CreateClientActivity
import com.example.fabricasapatos.ui.activities.client.GetClientsActivity
import dagger.hilt.android.AndroidEntryPoint
//import com.fabricasapatos.navigationdrawercompose.ui.theme.NavigationDrawerComposeTheme
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var createItemUseCase: ICreateItemUseCase

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.example.fabricasapatos.principal.ui.theme.FabricaSapatosTheme {
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
                                    "cliente" -> {val intent = Intent(applicationContext, GetClientsActivity::class.java)
                                    startActivity(intent)}
                                }
                            }
                        )
                    }
                ) {}
            }
        }
        lifecycleScope.launch {
            //val product = createProductUseCase("descrevendo o sandalia", 150.0, Uri.EMPTY)
            //val list = getDescriptionAndIdProductsUseCase()
            //val list = getOrdersByClientUseCase("3")
            //Log.i("TESTE", list.toString())
            //val order = createOrderUseCase("1")
            //val item = createItemUseCase(12, 1, 2)
        }
    }
}