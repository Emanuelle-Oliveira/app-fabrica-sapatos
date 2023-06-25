package com.example.fabricasapatos.domain.order.model

import com.example.fabricasapatos.domain.client.model.Client
import java.util.Date
import java.util.UUID

data class Order(
  var id: Int,
  var date: String,
  var clientCpf: String
)
