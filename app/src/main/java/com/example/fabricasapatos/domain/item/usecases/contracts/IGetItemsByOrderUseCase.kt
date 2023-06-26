package com.example.fabricasapatos.domain.item.usecases.contracts

import com.example.fabricasapatos.domain.item.model.Item

interface IGetItemsByOrderUseCase {
  suspend operator fun invoke(orderId: Int): List<Item>
}