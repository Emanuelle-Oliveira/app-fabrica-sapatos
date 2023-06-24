package com.example.fabricasapatos.domain.product.usecases

import com.example.fabricasapatos.data.product.ProductRepository
import com.example.fabricasapatos.domain.product.model.Product
import com.example.fabricasapatos.domain.product.usecases.contracts.IUpdateProductUseCase
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
  private val productRepository: ProductRepository
): IUpdateProductUseCase {
  override suspend fun invoke(
    id: Int,
    description: String,
    value: Double
  ): Product {
    val product = Product(id, description, value)
    return productRepository.updateProduct(product)
  }
}