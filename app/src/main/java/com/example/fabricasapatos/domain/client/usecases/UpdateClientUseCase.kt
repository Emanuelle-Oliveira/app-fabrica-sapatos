package com.example.fabricasapatos.domain.client.usecases

import com.example.fabricasapatos.data.client.ClientRepository
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.usecases.contracts.IUpdateClientUseCase
import javax.inject.Inject

class UpdateClientUseCase @Inject constructor(
  private val clientRepository: ClientRepository
): IUpdateClientUseCase {
  override suspend fun invoke(
    cpf: String,
    name: String,
    phone: String,
    address: String,
    instagram: String
  ): Client {
    val client = Client(cpf, name, phone, address, instagram)
    return clientRepository.updateClient(client)
  }
}