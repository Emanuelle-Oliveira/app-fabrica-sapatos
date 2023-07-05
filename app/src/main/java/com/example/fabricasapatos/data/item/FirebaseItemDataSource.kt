package com.example.fabricasapatos.data.item

import com.example.fabricasapatos.domain.item.model.Item
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseItemDataSource @Inject constructor(
  database: FirebaseDatabase
): IItemDataSource {

  private val databaseReference: DatabaseReference = database.getReference("item")
  private val idReference: DatabaseReference = database.getReference("lastItemId")

  override suspend fun createItem(item: Item): Item {
    return suspendCoroutine { continuation ->
      databaseReference
        .child(item.id.toString())
        .setValue(item)
        .addOnSuccessListener {
          continuation.resumeWith(Result.success(item))
        }
        .addOnFailureListener{exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }

  override suspend fun updateItem(item: Item): Item {
    return suspendCoroutine { continuation ->
      databaseReference
        .child(item.id.toString())
        .setValue(item)
        .addOnSuccessListener {
          continuation.resumeWith(Result.success(item))
        }
        .addOnFailureListener{exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }

  override suspend fun getItemsByOrder(orderId: Int): List<Item> {
    return suspendCoroutine { continuation ->
      databaseReference
        .orderByChild("orderId")
        .equalTo(orderId.toDouble()).get()
        .addOnSuccessListener { snapshot ->
          val itemsList = ArrayList<Item>()
          if (snapshot.exists()) {
            var gson = Gson()
            for (i in snapshot.children) {
              val json = gson.toJson(i.value)
              val item = gson.fromJson(json, Item::class.java)

              //Log.i("TESTE", client.toString())

              itemsList.add(
               Item(
                 item.id,
                 item.orderId,
                 item.productId,
                 item.quantity
               )
              )
              //Log.i("Teste", "Array: $clientsList")
            }
          }
          continuation.resumeWith(Result.success(itemsList))
        }.addOnFailureListener { exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }

  override suspend fun deleteItem(id: Int): Int {
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

  override suspend fun getLastItemId(): Int {
    return suspendCoroutine { continuation ->

      idReference.get().addOnSuccessListener { snapshot ->
        val idString = snapshot.value.toString()
        val id = idString.toInt()

        continuation.resumeWith(Result.success(id))
      }.addOnFailureListener{ exception ->
        continuation.resumeWith(Result.failure(exception))
      }
    }
  }

  override suspend fun updateLastItemId(id: Int): Int {
    return suspendCoroutine { continuation ->
      idReference
        .setValue(id+1)
        .addOnSuccessListener {
          continuation.resumeWith(Result.success(id+1))
        }
        .addOnFailureListener{exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }

  override suspend fun updateLastItemId2(id: Int, size: Int): Int {
    return suspendCoroutine { continuation ->
      idReference
        .setValue(id+size)
        .addOnSuccessListener {
          continuation.resumeWith(Result.success(id+size))
        }
        .addOnFailureListener{exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }
}