package com.example.fabricasapatos.model

import com.example.fabricasapatos.model.Pedido
import com.example.fabricasapatos.model.Produto

data class Item(
  var id: Int,
  var pedido: Pedido,
  var produto: Produto,
  var quantidade: Int
)
