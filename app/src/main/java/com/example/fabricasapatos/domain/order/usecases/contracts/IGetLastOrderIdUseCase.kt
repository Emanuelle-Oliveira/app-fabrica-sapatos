package com.example.fabricasapatos.domain.item.usecases.contracts


interface IGetLastOrderIdUseCase {
  suspend operator fun invoke(): Int
}