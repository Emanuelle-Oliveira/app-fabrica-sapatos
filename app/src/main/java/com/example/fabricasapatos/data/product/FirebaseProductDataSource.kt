package com.example.fabricasapatos.data.product

import com.example.fabricasapatos.domain.product.model.Product
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseProductDataSource @Inject constructor(
  database: FirebaseDatabase
): IProductDataSource {

  private val reference: DatabaseReference = database.getReference("product")

  override suspend fun createProduct(product: Product): Product {
    return suspendCoroutine { continuation ->
      reference
        .child(product.id.toString())
        .setValue(product)
        .addOnSuccessListener {
          continuation.resumeWith(Result.success(product))
        }
        .addOnFailureListener{exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }

  override suspend fun updateProduct(product: Product): Product {
    return suspendCoroutine { continuation ->
      reference
        .child(product.id.toString())
        .setValue(product)
        .addOnSuccessListener {
          continuation.resumeWith(Result.success(product))
        }
        .addOnFailureListener{exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }

  override suspend fun getProducts(): List<Product> {
    return suspendCoroutine { continuation ->

      reference.get().addOnSuccessListener { snapshot ->
        val productsList = ArrayList<Product>()
        if (snapshot.exists()) {
          var gson = Gson()
          for (i in snapshot.children) {
            val json = gson.toJson(i.value)
            val product = gson.fromJson(json, Product::class.java)

            //Log.i("TESTE", client.toString())

            productsList.add(Product(product.id, product.description, product.value))
            //Log.i("Teste", "Array: $clientsList")
          }
        }
        continuation.resumeWith(Result.success(productsList))
      }

      reference.get().addOnFailureListener{ exception ->
        continuation.resumeWith(Result.failure(exception))
      }
    }
  }

  override suspend fun deleteProduct(id: Int): Int {
    return suspendCoroutine { continuation ->
      reference
        .child(id.toString())
        .removeValue()
        .addOnSuccessListener {
          continuation.resumeWith(Result.success(id))
        }
        .addOnFailureListener{exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }
}