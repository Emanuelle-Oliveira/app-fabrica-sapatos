package com.example.fabricasapatos.domain.item.usecases

import com.example.fabricasapatos.data.order.OrderRepository
import com.example.fabricasapatos.domain.item.usecases.contracts.IGetLastItemIdUseCase
import com.example.fabricasapatos.domain.item.usecases.contracts.IGetLastOrderIdUseCase
import javax.inject.Inject

class GetLastOrderIdUseCase @Inject constructor(
  private val orderRepository: OrderRepository,
): IGetLastOrderIdUseCase {
  override suspend fun invoke(): Int {
    return try {
      val id = orderRepository.getLastOrderId()
      id
    } catch (e: Exception) {
      throw e
    }
  }
}