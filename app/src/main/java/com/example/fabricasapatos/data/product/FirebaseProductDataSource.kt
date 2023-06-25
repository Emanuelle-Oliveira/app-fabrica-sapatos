package com.example.fabricasapatos.data.product

import android.net.Uri
import com.example.fabricasapatos.domain.product.model.Product
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.gson.Gson
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseProductDataSource @Inject constructor(
  database: FirebaseDatabase,
  storage: FirebaseStorage
): IProductDataSource {

  private val databaseReference: DatabaseReference = database.getReference("product")
  private val storageReference: StorageReference = storage.reference

  override suspend fun createProduct(product: Product): Product {
    return suspendCoroutine { continuation ->
      databaseReference
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
      databaseReference
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

      databaseReference.get().addOnSuccessListener { snapshot ->
        val productsList = ArrayList<Product>()
        if (snapshot.exists()) {
          var gson = Gson()
          for (i in snapshot.children) {
            val json = gson.toJson(i.value)
            val product = gson.fromJson(json, Product::class.java)

            //Log.i("TESTE", client.toString())

            productsList.add(Product(product.id, product.description, product.value, product.imageUrl))
            //Log.i("Teste", "Array: $clientsList")
          }
        }
        continuation.resumeWith(Result.success(productsList))
      }

      databaseReference.get().addOnFailureListener{ exception ->
        continuation.resumeWith(Result.failure(exception))
      }
    }
  }

  override suspend fun deleteProduct(id: Int): Int {
    return suspendCoroutine { continuation ->
      databaseReference
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

  override suspend fun uploadProductImage(imageUri: Uri): String {
    return suspendCoroutine { continuation ->
      storageReference.putFile(imageUri)
        .addOnSuccessListener { taskSnapshot ->
          taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
            val path = uri.toString()
            continuation.resumeWith(Result.success(path))
          }
        }.addOnFailureListener { exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }
}