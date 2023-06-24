package com.example.fabricasapatos.domain.usecases

import com.example.fabricasapatos.data.ClientRepository
import com.example.fabricasapatos.domain.model.Client
import com.example.fabricasapatos.domain.usecases.contracts.IGetClientsUseCase
import javax.inject.Inject

class GetClientsUseCase @Inject constructor(
  private val clientRepository: ClientRepository
): IGetClientsUseCase {
  override suspend fun invoke(): List<Client> {
    return clientRepository.getClients()
  }
}