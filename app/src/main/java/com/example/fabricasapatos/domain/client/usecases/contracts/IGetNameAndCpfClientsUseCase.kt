package com.example.fabricasapatos.domain.client.usecases.contracts

import com.example.fabricasapatos.domain.client.model.Client
import com.example.fabricasapatos.domain.client.types.NameAndCpfClient

interface IGetNameAndCpfClientsUseCase {
  suspend operator fun invoke(): List<NameAndCpfClient>
}