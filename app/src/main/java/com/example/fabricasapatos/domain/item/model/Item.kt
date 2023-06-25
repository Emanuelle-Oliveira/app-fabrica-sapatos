package com.example.fabricasapatos.domain.item.model

import com.example.fabricasapatos.domain.order.model.Order
import com.example.fabricasapatos.domain.product.model.Product

data class Item(
  var id: Int,
  var orderId: Int,
  var productId: Int,
  var quantity: Int
)
