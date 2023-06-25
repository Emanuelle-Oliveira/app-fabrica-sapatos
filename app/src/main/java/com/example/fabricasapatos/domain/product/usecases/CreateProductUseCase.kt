package com.example.fabricasapatos.domain.product.usecases

import android.net.Uri
import android.util.Log
import com.example.fabricasapatos.data.product.ProductRepository
import com.example.fabricasapatos.domain.product.model.Product
import com.example.fabricasapatos.domain.product.usecases.contracts.ICreateProductUseCase
import javax.inject.Inject

class CreateProductUseCase @Inject constructor(
  private val uploadProductImageUseCase: UploadProductImageUseCase,
  private val productRepository: ProductRepository
): ICreateProductUseCase {
  override suspend fun invoke(
    id: Int,
    description: String,
    value: Double,
    imageUri: Uri
  ): Product {

    return try {
      val imageUrl = uploadProductImageUseCase(imageUri)
      Log.i("TESTE", imageUrl.toString())
      val product = Product(id, description, value, imageUrl)
      productRepository.createProduct(product)
    } catch (e: Exception) {
      throw e
    }
  }
}