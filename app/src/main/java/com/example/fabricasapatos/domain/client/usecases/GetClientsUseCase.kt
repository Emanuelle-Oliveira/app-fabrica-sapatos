package com.example.fabricasapatos.domain.client.usecases

import com.example.fabricasapatos.data.client.ClientRepository
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetClientsUseCase
import javax.inject.Inject

class GetClientsUseCase @Inject constructor(
  private val clientRepository: ClientRepository
): IGetClientsUseCase {
  override suspend fun invoke(): List<Client> {
    return clientRepository.getClients()
  }
}