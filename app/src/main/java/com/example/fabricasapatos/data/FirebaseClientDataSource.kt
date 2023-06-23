package com.example.fabricasapatos.data

import android.util.Log
import com.example.fabricasapatos.domain.model.Client
import com.google.firebase.database.DatabaseReference
import com.google.gson.Gson
import kotlin.coroutines.suspendCoroutine

class FirebaseClientDataSource(
  reference: DatabaseReference
): IClientDataSource {

  private val reference = reference

  override suspend fun createClient(client: Client): Client {
    return suspendCoroutine { continuation ->

      reference
        .child(client.cpf)
        .setValue(client)
        .addOnSuccessListener {
          continuation.resumeWith(Result.success(client))
        }
        .addOnFailureListener{exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }

  override suspend fun getClients(): List<Client> {
    return suspendCoroutine { continuation ->

      reference.get().addOnSuccessListener { snapshot ->
        val clientsList = ArrayList<Client>()
        if (snapshot.exists()) {
          var gson = Gson()
          for (i in snapshot.children) {
            val json = gson.toJson(i.value)
            val client = gson.fromJson(json, Client::class.java)

            Log.i("TESTE", client.toString())

            clientsList.add(Client(client.cpf, client.name, client.phone, client.address, client.instagram))
            Log.i("Teste", "Array: $clientsList")
          }
        }
        continuation.resumeWith(Result.success(clientsList))
      }

      reference.get().addOnFailureListener{ exception ->
        continuation.resumeWith(Result.failure(exception))
      }
    }
  }
}