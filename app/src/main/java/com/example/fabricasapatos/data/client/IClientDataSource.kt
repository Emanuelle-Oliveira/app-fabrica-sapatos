package com.example.fabricasapatos.data.client

import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.types.NameAndCpfClient

interface IClientDataSource {

  suspend fun createClient(client: Client): Client

  suspend fun updateClient(client: Client): Client

  suspend fun getClients(): List<Client>

  suspend fun getNameAndCpfClients(): List<NameAndCpfClient>

  suspend fun deleteClient(cpf: String): String
}