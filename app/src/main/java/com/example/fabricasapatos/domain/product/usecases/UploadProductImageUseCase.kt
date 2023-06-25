package com.example.fabricasapatos.domain.product.usecases

import android.net.Uri
import com.example.fabricasapatos.data.product.ProductRepository
import com.example.fabricasapatos.domain.product.usecases.contracts.IUploadProductImageUseCase
import javax.inject.Inject

class UploadProductImageUseCase @Inject constructor(
  private val productRepository: ProductRepository
): IUploadProductImageUseCase {
  override suspend fun invoke(imageUri: Uri): String {
    return productRepository.uploadProductImage(imageUri)
  }
}