package com.example.fabricasapatos.domain.item.usecases.contracts

interface IUpdateLastItemIdUseCase2 {
  suspend operator fun invoke(id: Int, size: Int): Int
}