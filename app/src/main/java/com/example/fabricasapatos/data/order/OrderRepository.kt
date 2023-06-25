package com.example.fabricasapatos.data.order

import com.example.fabricasapatos.domain.order.model.Order
import java.util.UUID
import javax.inject.Inject

class OrderRepository @Inject constructor(
  private val dataSource: IOrderDataSource
) {

  suspend fun createOrder(order: Order): Order
      = dataSource.createOrder(order)

  suspend fun updateOrder(order: Order): Order
      = dataSource.updateOrder(order)

  suspend fun getOrdersByClient(cpfClient: String): List<Order> = dataSource.getOrdersByClient(cpfClient)

  suspend fun deleteOrder(id: Int): Int
      = dataSource.deleteOrder(id)

  suspend fun getLastOrderId(): Int = dataSource.getLastOrderId()

  suspend fun updateLastOrderId(id: Int): Int = dataSource.updateLastOrderId(id)
}