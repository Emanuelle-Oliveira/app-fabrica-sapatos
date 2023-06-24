package com.example.fabricasapatos.domain.product.usecases

import com.example.fabricasapatos.data.product.ProductRepository
import com.example.fabricasapatos.domain.product.model.Product
import com.example.fabricasapatos.domain.product.usecases.contracts.IGetProductsUseCase
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
  private val productRepository: ProductRepository
): IGetProductsUseCase {
  override suspend fun invoke(): List<Product> {
    return productRepository.getProducts()
  }
}