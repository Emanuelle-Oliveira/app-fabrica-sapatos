package com.example.fabricasapatos.data.order

import com.example.fabricasapatos.domain.order.model.Order
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseOrderDataSource @Inject constructor(
  database: FirebaseDatabase
): IOrderDataSource {

  private val databaseReference: DatabaseReference = database.getReference("order")
  private val idReference: DatabaseReference = database.getReference("lastOrderId")

  override suspend fun createOrder(order: Order): Order {
    return suspendCoroutine { continuation ->
      databaseReference
        .child(order.id.toString())
        .setValue(order)
        .addOnSuccessListener {
          continuation.resumeWith(Result.success(order))
        }
        .addOnFailureListener{exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }

  override suspend fun updateOrder(order: Order): Order {
    return suspendCoroutine { continuation ->
      databaseReference
        .child(order.id.toString())
        .setValue(order)
        .addOnSuccessListener {
          continuation.resumeWith(Result.success(order))
        }
        .addOnFailureListener{exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }

  override suspend fun getOrdersByClient(cpfClient: String): List<Order> {
    return suspendCoroutine { continuation ->
      databaseReference
        .orderByChild("cpfClient")
        .equalTo(cpfClient).get()
        .addOnSuccessListener { snapshot ->
          val ordersList = ArrayList<Order>()
          if (snapshot.exists()) {
            var gson = Gson()
            for (i in snapshot.children) {
              val json = gson.toJson(i.value)
              val order = gson.fromJson(json, Order::class.java)

              //Log.i("TESTE", client.toString())

              ordersList.add(
                Order(
                  order.id,
                  order.date,
                  order.cpfClient
                )
              )
              //Log.i("Teste", "Array: $clientsList")
            }
          }
          continuation.resumeWith(Result.success(ordersList))
        }.addOnFailureListener { exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }

  override suspend fun deleteOrder(id: Int): Int {
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

  override suspend fun getLastOrderId(): Int {
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

  override suspend fun updateLastOrderId(id: Int): Int {
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
}