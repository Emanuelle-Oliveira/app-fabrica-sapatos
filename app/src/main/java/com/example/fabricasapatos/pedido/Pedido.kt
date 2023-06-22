package com.example.fabricasapatos.pedido

import com.example.fabricasapatos.cliente.Cliente
import java.util.Date

data class Pedido(
  var id: Int,
  var data: Date,
  var cliente: Cliente
)
