package com.example.fabricasapatos.domain.client.usecases

import com.example.fabricasapatos.data.client.ClientRepository
import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.types.NameAndCpfClient
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetClientsUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetNameAndCpfClientsUseCase
import javax.inject.Inject

class GetNameAndCpfClientsUseCase @Inject constructor(
  private val clientRepository: ClientRepository
): IGetNameAndCpfClientsUseCase {
  override suspend fun invoke(): List<NameAndCpfClient> {
    return clientRepository.getNameAndCpfClients()
  }
}