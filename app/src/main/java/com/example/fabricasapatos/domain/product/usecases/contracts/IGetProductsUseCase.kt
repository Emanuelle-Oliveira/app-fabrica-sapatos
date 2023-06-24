package com.example.fabricasapatos.domain.product.usecases.contracts

import com.example.fabricasapatos.domain.product.model.Product

interface IGetProductsUseCase {
  suspend operator fun invoke(): List<Product>
}