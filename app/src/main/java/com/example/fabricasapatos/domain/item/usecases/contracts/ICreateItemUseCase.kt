package com.example.fabricasapatos.domain.item.usecases.contracts

import com.example.fabricasapatos.domain.item.model.Item

interface ICreateItemUseCase {
  suspend operator fun invoke(orderId: Int, productId: Int, quantity: Int): Item
}