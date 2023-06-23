package com.example.fabricasapatos.data

import com.example.fabricasapatos.domain.model.Client

interface IClientDataSource {
  suspend fun createClient(client: Client): Client

  suspend fun getClients(): List<Client>
}