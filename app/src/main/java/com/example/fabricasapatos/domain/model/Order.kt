package com.example.fabricasapatos.domain.model

import java.util.Date

data class Order(
  var id: Int,
  var date: Date,
  var client: Client
)
