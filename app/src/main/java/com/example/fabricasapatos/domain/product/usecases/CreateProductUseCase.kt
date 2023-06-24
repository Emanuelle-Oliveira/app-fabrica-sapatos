package com.example.fabricasapatos.domain.product.usecases

import com.example.fabricasapatos.data.product.ProductRepository
import com.example.fabricasapatos.domain.product.model.Product
import com.example.fabricasapatos.domain.product.usecases.contracts.ICreateProductUseCase
import javax.inject.Inject

class CreateProductUseCase @Inject constructor(
  private val productRepository: ProductRepository
): ICreateProductUseCase {
  override suspend fun invoke(
    id: Int,
    description: String,
    value: Double
  ): Product {
    val product = Product(id, description, value)
    return productRepository.createProduct(product)
  }
}