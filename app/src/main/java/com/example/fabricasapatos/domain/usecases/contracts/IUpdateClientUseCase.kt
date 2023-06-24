package com.example.fabricasapatos.domain.usecases.contracts

import com.example.fabricasapatos.domain.model.Client

interface IUpdateClientUseCase {
  suspend operator fun invoke(cpf: String, name: String, phone: String, address: String, instagram: String): Client
}