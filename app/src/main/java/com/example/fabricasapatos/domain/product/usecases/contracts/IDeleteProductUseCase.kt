package com.example.fabricasapatos.domain.product.usecases.contracts

interface IDeleteProductUseCase {
  suspend operator fun invoke(id: Int): Int
}