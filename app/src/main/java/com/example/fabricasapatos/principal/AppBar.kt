package com.example.fabricasapatos.principal

import androidx.compose.foundation.layout.height
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterial3Api
@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .height(70.dp),
        title = {
            Text(text = "Sapatos Mandala",
                    style = TextStyle(fontSize = 20.sp)
            )
        },
        backgroundColor = MaterialTheme.colorScheme.inversePrimary,
        contentColor = MaterialTheme.colorScheme.inversePrimary,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer"
                )
            }
        }
    )
}