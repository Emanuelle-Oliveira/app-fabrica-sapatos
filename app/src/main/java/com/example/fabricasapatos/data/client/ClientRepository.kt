package com.example.fabricasapatos.data.client

import com.example.fabricasapatos.domain.client.model.Client
import javax.inject.Inject

class ClientRepository @Inject constructor(
  private val dataSource: IClientDataSource
  ) {

  suspend fun createClient(client: Client): Client
  = dataSource.createClient(client)

  suspend fun updateClient(client: Client): Client
      = dataSource.updateClient(client)

  suspend fun getClients(): List<Client> = dataSource.getClients()

  suspend fun deleteClient(cpf: String): String
      = dataSource.deleteClient(cpf)
}