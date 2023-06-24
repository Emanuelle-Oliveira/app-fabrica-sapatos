package com.example.fabricasapatos.domain.product.usecases

import com.example.fabricasapatos.data.product.ProductRepository
import com.example.fabricasapatos.domain.product.model.Product
import com.example.fabricasapatos.domain.product.usecases.contracts.IDeleteProductUseCase
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(
  private val productRepository: ProductRepository
): IDeleteProductUseCase {
  override suspend fun invoke(
    id: Int,
  ): Int {
    return productRepository.deleteProduct(id)
  }
}