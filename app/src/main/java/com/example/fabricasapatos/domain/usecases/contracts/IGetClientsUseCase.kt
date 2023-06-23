package com.example.fabricasapatos.domain.usecases.contracts

import com.example.fabricasapatos.domain.model.Client

interface IGetClientsUseCase {
  suspend operator fun invoke(): List<Client>
}