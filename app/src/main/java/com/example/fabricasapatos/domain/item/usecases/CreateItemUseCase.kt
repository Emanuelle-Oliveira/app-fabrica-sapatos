package com.example.fabricasapatos.domain.item.usecases

import com.example.fabricasapatos.data.item.ItemRepository
import com.example.fabricasapatos.domain.item.model.Item
import com.example.fabricasapatos.domain.item.usecases.contracts.ICreateItemUseCase
import javax.inject.Inject

class CreateItemUseCase @Inject constructor(
  private val itemRepository: ItemRepository
): ICreateItemUseCase {
  override suspend fun invoke(orderId: Int, productId: Int, quantity: Int): Item {

    return try {
      val id = itemRepository.getLastItemId()
      val newId = itemRepository.updateLastItemId(id)

      val item = Item(newId, orderId, productId, quantity)
      itemRepository.createItem(item)
    } catch (e: Exception) {
      throw e
    }
  }
}