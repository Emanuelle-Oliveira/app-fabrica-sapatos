package com.example.fabricasapatos.domain.product.usecases

import com.example.fabricasapatos.data.product.ProductRepository
import com.example.fabricasapatos.domain.product.types.DescriptionAndIdProduct
import com.example.fabricasapatos.domain.product.usecases.contracts.IGetDescriptionAndIdProductsUseCase
import javax.inject.Inject

class GetDescriptionAndIdProductsUseCase @Inject constructor(
  private val productRepository: ProductRepository
): IGetDescriptionAndIdProductsUseCase {
  override suspend fun invoke(): List<DescriptionAndIdProduct> {
    return productRepository.getDescriptionAndIdProducts()
  }
}