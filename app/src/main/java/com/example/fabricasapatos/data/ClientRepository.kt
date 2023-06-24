package com.example.fabricasapatos.data

import com.example.fabricasapatos.domain.model.Client
import javax.inject.Inject

class ClientRepository @Inject constructor(
  private val dataSource: IClientDataSource
  ) {

  suspend fun createClient( client: Client): Client
  = dataSource.createClient(client)

  suspend fun getClients(): List<Client> = dataSource.getClients()
}