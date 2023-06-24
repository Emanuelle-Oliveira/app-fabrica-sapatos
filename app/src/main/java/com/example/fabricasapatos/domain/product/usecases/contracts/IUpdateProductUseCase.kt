package com.example.fabricasapatos.domain.product.usecases.contracts

import com.example.fabricasapatos.domain.product.model.Product

interface IUpdateProductUseCase {
  suspend operator fun invoke(id: Int, description: String, value: Double): Product
}