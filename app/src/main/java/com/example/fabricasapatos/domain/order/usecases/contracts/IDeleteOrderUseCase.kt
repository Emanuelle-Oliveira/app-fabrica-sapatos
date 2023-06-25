package com.example.fabricasapatos.domain.order.usecases.contracts

import com.example.fabricasapatos.domain.order.model.Order
import java.util.UUID

interface IDeleteOrderUseCase {
  suspend operator fun invoke(id: Int): Int
}