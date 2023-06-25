package com.example.fabricasapatos.data.product

import android.net.Uri
import com.example.fabricasapatos.domain.product.model.Product
import java.util.UUID
import javax.inject.Inject

class ProductRepository @Inject constructor(
  private val dataSource: IProductDataSource
  ) {

  suspend fun createProduct(product: Product): Product
  = dataSource.createProduct(product)

  suspend fun updateProduct(product: Product): Product
      = dataSource.updateProduct(product)

  suspend fun getProducts(): List<Product> = dataSource.getProducts()

  suspend fun deleteProduct(id: Int): Int
      = dataSource.deleteProduct(id)

  suspend fun uploadProductImage(imageUri: Uri): String =
    dataSource.uploadProductImage(imageUri)

  suspend fun getLastProductId(): Int = dataSource.getLastProductId()

  suspend fun updateLastProductId(id: Int): Int = dataSource.updateLastProductId(id)
}