package com.example.fabricasapatos.domain.order.usecases

import android.R.attr.data
import com.example.fabricasapatos.data.order.OrderRepository
import com.example.fabricasapatos.domain.order.model.Order
import com.example.fabricasapatos.domain.order.usecases.contracts.ICreateOrderUseCase
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject


class CreateOrderUseCase @Inject constructor(
  private val orderRepository: OrderRepository
): ICreateOrderUseCase {
  override suspend fun invoke(
    cpfClient: String
  ): Order {

    return try {
      val date = Date()
      val formatDate = SimpleDateFormat("dd-MM-yyyy")
      val dateString: String = formatDate.format(date)

      val id = orderRepository.getLastOrderId()
      val newId = orderRepository.updateLastOrderId(id)

      val order = Order(id, dateString, cpfClient)
      orderRepository.createOrder(order)

    } catch (e: Exception) {
      throw e
    }
  }
}