package com.example.fabricasapatos.domain.usecases.contracts

import com.example.fabricasapatos.domain.model.Client

interface IDeleteClientUseCase {
  suspend operator fun invoke(cpf: String): String
}