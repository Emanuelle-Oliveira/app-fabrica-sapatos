package com.example.fabricasapatos.domain.order.usecases.contracts

import com.example.fabricasapatos.domain.order.model.Order
import java.util.Date

interface ICreateOrderUseCase {
  suspend operator fun invoke(clientCpf: String): Order
}