package com.example.fabricasapatos.domain.item.usecases.contracts

interface IDeleteItemUseCase {
  suspend operator fun invoke(id: Int): Int
}