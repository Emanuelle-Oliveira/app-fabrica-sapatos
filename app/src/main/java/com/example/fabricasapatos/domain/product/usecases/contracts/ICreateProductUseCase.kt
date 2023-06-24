package com.example.fabricasapatos.domain.product.usecases.contracts

import com.example.fabricasapatos.domain.product.model.Product

interface ICreateProductUseCase {
  suspend operator fun invoke(id: Int, description: String, value: Double): Product
}