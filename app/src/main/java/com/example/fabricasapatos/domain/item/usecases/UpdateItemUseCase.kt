package com.example.fabricasapatos.domain.item.usecases

import com.example.fabricasapatos.data.item.ItemRepository
import com.example.fabricasapatos.domain.item.model.Item
import com.example.fabricasapatos.domain.item.usecases.contracts.IUpdateItemUseCase

import javax.inject.Inject

class UpdateItemUseCase @Inject constructor(
  private val itemRepository: ItemRepository
): IUpdateItemUseCase {

  override suspend fun invoke(orderId: Int, productId: Int, quantity: Int, id: Int): Item {
    val item = Item(id, orderId, productId, quantity)
    return itemRepository.updateItem(item)
  }
}