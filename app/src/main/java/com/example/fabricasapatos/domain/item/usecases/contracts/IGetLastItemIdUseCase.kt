package com.example.fabricasapatos.domain.item.usecases.contracts

import com.example.fabricasapatos.domain.item.model.Item

interface IGetLastItemIdUseCase {
  suspend operator fun invoke(): Int
}