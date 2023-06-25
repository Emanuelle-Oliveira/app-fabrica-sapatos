package com.example.fabricasapatos.domain.order.usecases

import com.example.fabricasapatos.data.order.OrderRepository
import com.example.fabricasapatos.domain.order.model.Order
import com.example.fabricasapatos.domain.order.usecases.contracts.IGetOrdersByClientUseCase
import javax.inject.Inject

class GetOrdersByClientUseCase @Inject constructor(
  private val orderRepository: OrderRepository
): IGetOrdersByClientUseCase {

  override suspend fun invoke(clientCpf: String): List<Order> {
    return orderRepository.getOrdersByClient(clientCpf)
  }
}