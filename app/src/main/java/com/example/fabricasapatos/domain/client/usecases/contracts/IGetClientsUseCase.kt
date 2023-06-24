package com.example.fabricasapatos.domain.client.usecases.contracts

import com.example.fabricasapatos.domain.client.model.Client

interface IGetClientsUseCase {
  suspend operator fun invoke(): List<Client>
}