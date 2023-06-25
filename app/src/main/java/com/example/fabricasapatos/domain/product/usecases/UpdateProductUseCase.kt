package com.example.fabricasapatos.domain.product.usecases

import android.net.Uri
import com.example.fabricasapatos.data.product.ProductRepository
import com.example.fabricasapatos.domain.product.model.Product
import com.example.fabricasapatos.domain.product.usecases.contracts.IUpdateProductUseCase
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
  private val uploadProductImageUseCase: UploadProductImageUseCase,
  private val productRepository: ProductRepository
): IUpdateProductUseCase {
  override suspend fun invoke(
    id: Int,
    description: String,
    value: Double,
    imageUri: Uri
  ): Product {

    return try {
      val imageUrl = uploadProductImageUseCase(imageUri)
      val product = Product(id, description, value, imageUrl)
      productRepository.updateProduct(product)
    } catch (e: Exception) {
      throw e
    }
  }
}