package com.example.fabricasapatos.domain.client.usecases.contracts

interface IDeleteClientUseCase {
  suspend operator fun invoke(cpf: String): String
}