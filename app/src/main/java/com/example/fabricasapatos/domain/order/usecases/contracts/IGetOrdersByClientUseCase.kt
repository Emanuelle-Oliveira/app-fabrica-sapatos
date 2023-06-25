package com.example.fabricasapatos.domain.order.usecases.contracts

import com.example.fabricasapatos.domain.order.model.Order

interface IGetOrdersByClientUseCase {
  suspend operator fun invoke(cpfClient: String): List<Order>
}