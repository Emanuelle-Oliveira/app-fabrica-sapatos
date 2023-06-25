package com.example.fabricasapatos.domain.order.usecases

import com.example.fabricasapatos.data.order.OrderRepository
import com.example.fabricasapatos.domain.order.model.Order
import com.example.fabricasapatos.domain.order.usecases.contracts.IUpdateOrderUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class UpdateOrderUseCase @Inject constructor(
  private val orderRepository: OrderRepository
): IUpdateOrderUseCase {
  override suspend fun invoke(
    id: Int,
    cpfClient: String
  ): Order {

    val date = Date()
    val formatDate = SimpleDateFormat("dd-MM-yyyy")
    val dateString: String = formatDate.format(date)

    val order = Order(id, dateString, cpfClient)
    //Log.i("TESTE", order.toString())
    return orderRepository.updateOrder(order)
  }
}