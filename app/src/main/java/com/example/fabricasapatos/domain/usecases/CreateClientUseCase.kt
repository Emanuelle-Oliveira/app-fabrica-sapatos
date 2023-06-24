package com.example.fabricasapatos.domain.usecases

import com.example.fabricasapatos.data.ClientRepository
import com.example.fabricasapatos.domain.model.Client
import com.example.fabricasapatos.domain.usecases.contracts.ICreateClientUseCase
import javax.inject.Inject

class CreateClientUseCase @Inject constructor(
  private val clientRepository: ClientRepository
): ICreateClientUseCase {
  override suspend fun invoke(
    cpf: String,
    name: String,
    phone: String,
    address: String,
    instagram: String
  ): Client {
    val client = Client(cpf, name, phone, address, instagram)
    return clientRepository.createClient(client)
  }
}