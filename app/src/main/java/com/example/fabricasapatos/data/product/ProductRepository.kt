package com.example.fabricasapatos.data.product

import com.example.fabricasapatos.domain.product.model.Product
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
}