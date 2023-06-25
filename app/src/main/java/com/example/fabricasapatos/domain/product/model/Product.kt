package com.example.fabricasapatos.domain.product.model

data class Product(
  var id: Int,
  var description: String,
  var value: Double,
  var imageUrl: String
)