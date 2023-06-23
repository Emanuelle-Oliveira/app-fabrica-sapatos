package com.example.fabricasapatos.domain.model

data class Item(
  var id: Int,
  var order: Order,
  var product: Product,
  var quantity: Int
)
