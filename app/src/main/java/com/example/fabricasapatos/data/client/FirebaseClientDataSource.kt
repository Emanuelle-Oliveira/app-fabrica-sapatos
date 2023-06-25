package com.example.fabricasapatos.data.client

import android.util.Log
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.types.NameAndCpfClient
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseClientDataSource @Inject constructor(
  database: FirebaseDatabase
): IClientDataSource {
  //private val database = FirebaseDatabase.getInstance()
  private val reference: DatabaseReference = database.getReference("client")
  //private val nameReference: DatabaseReference = database.getReference("client/name")

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

  override suspend fun updateClient(client: Client): Client {
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

            //Log.i("TESTE", client.toString())

            clientsList.add(Client(client.cpf, client.name, client.phone, client.address, client.instagram))
            //Log.i("Teste", "Array: $clientsList")
          }
        }
        continuation.resumeWith(Result.success(clientsList))
      }.addOnFailureListener{ exception ->
        continuation.resumeWith(Result.failure(exception))
      }
    }
  }

  override suspend fun getNameAndCpfClients(): List<NameAndCpfClient> {
    return suspendCoroutine { continuation ->

      reference.get().addOnSuccessListener { snapshot ->
        val clientsList = ArrayList<NameAndCpfClient>()
        if (snapshot.exists()) {
          for (i in snapshot.children) {
            val clientData = i.value as Map<*, *>
            val name = clientData["name"] as? String ?: ""
            val cpf = clientData["cpf"] as? String ?: ""

            val client = NameAndCpfClient(name, cpf)
            clientsList.add(client)
          }
        }
        //Log.i("TESTE", clientsList.toString())
        continuation.resumeWith(Result.success(clientsList))
      }.addOnFailureListener{ exception ->
        continuation.resumeWith(Result.failure(exception))
      }
    }
  }

  override suspend fun deleteClient(cpf: String): String {
    return suspendCoroutine { continuation ->
      reference
        .child(cpf)
        .removeValue()
        .addOnSuccessListener {
          continuation.resumeWith(Result.success(cpf))
        }
        .addOnFailureListener{exception ->
          continuation.resumeWith(Result.failure(exception))
        }
    }
  }
}