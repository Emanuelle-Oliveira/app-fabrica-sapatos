package com.example.fabricasapatos.domain.product.usecases.contracts

import java.util.UUID

interface IDeleteProductUseCase {
  suspend operator fun invoke(id: Int): Int
}