package com.example.fabricasapatos.data.client

import com.example.fabricasapatos.domain.client.model.Client

interface IClientDataSource {

  suspend fun createClient(client: Client): Client

  suspend fun updateClient(client: Client): Client

  suspend fun getClients(): List<Client>

  suspend fun deleteClient(cpf: String): String
}