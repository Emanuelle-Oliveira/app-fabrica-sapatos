package com.example.fabricasapatos.domain.client.usecases

import com.example.fabricasapatos.data.client.ClientRepository
import com.example.fabricasapatos.domain.client.usecases.contracts.IDeleteClientUseCase
import javax.inject.Inject

class DeleteClientUseCase @Inject constructor(
  private val clientRepository: ClientRepository
): IDeleteClientUseCase {
  override suspend fun invoke(
    cpf: String,
  ): String {
    return clientRepository.deleteClient(cpf)
  }
}