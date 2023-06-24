package com.example.fabricasapatos.domain.order.model

import com.example.fabricasapatos.domain.client.model.Client
import java.util.Date

data class Order(
  var id: Int,
  var date: Date,
  var client: Client
)
