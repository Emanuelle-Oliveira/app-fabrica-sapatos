package com.example.fabricasapatos.item

import com.example.fabricasapatos.pedido.Pedido
import com.example.fabricasapatos.produto.Produto

data class Item(
  var id: Int,
  var pedido: Pedido,
  var produto: Produto,
  var quantidade: Int
)
