package com.example.fabricasapatos.principal

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fabricasapatos.principal.ui.theme.FabricaSapatosTheme
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import com.example.fabricasapatos.ui.activities.client.CreateClientActivity
import com.example.fabricasapatos.ui.activities.client.DeleteClientActivity
//import com.fabricasapatos.navigationdrawercompose.ui.theme.NavigationDrawerComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
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
                                    "cliente" -> {val intent = Intent(applicationContext, CreateClientActivity::class.java)
                                    startActivity(intent)}
                                }
                            }
                        )
                    }
                ) {

                }
            }
        }
    }
}