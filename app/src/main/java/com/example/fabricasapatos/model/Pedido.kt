package com.example.fabricasapatos.model

import java.util.Date

data class Pedido(
  var id: Int,
  var data: Date,
  var cliente: Cliente
)
