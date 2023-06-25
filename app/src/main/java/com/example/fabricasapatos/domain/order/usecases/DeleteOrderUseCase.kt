package com.example.fabricasapatos.domain.order.usecases

import com.example.fabricasapatos.data.order.OrderRepository
import com.example.fabricasapatos.domain.order.model.Order
import com.example.fabricasapatos.domain.order.usecases.contracts.IDeleteOrderUseCase
import java.util.UUID
import javax.inject.Inject

class DeleteOrderUseCase @Inject constructor(
  private val orderRepository: OrderRepository
): IDeleteOrderUseCase {
  override suspend fun invoke(
    id: Int
  ): Int {

    return orderRepository.deleteOrder(id)
  }
}