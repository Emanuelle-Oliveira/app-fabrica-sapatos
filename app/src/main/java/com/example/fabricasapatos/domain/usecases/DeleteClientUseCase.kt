package com.example.fabricasapatos.domain.usecases

import com.example.fabricasapatos.data.ClientRepository
import com.example.fabricasapatos.domain.model.Client
import com.example.fabricasapatos.domain.usecases.contracts.ICreateClientUseCase
import com.example.fabricasapatos.domain.usecases.contracts.IDeleteClientUseCase
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