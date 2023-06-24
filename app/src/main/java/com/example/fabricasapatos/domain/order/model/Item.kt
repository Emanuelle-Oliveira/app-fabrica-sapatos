package com.example.fabricasapatos.domain.order.model

import com.example.fabricasapatos.domain.product.model.Product

data class Item(
  var id: Int,
  var order: Order,
  var product: Product,
  var quantity: Int
)
