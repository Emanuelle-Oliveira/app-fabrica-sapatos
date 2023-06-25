package com.example.fabricasapatos.data.product

import android.net.Uri
import com.example.fabricasapatos.domain.product.model.Product

interface IProductDataSource {

  suspend fun createProduct(product: Product): Product

  suspend fun updateProduct(product: Product): Product

  suspend fun getProducts(): List<Product>

  suspend fun deleteProduct(id: Int): Int

  suspend fun uploadProductImage(imageUri: Uri): String
}