package com.example.fabricasapatos.data.order

import com.example.fabricasapatos.domain.order.model.Order
import java.util.UUID

interface IOrderDataSource {
  suspend fun createOrder(order: Order): Order

  suspend fun updateOrder(order: Order): Order

  suspend fun getOrdersByClient(clientCpf: String): List<Order>

  suspend fun deleteOrder(id: Int): Int

  suspend fun getLastOrderId(): Int

  suspend fun updateLastOrderId(id: Int): Int
}