package com.example.fabricasapatos.domain.item.usecases

import com.example.fabricasapatos.data.item.ItemRepository
import com.example.fabricasapatos.domain.item.model.Item
import com.example.fabricasapatos.domain.item.usecases.contracts.IGetItemsByOrderUseCase
import javax.inject.Inject

class GetItemsByOrderUseCase @Inject constructor(
  private val itemRepository: ItemRepository
): IGetItemsByOrderUseCase {
  override suspend fun invoke(orderId: Int): List<Item> {
    return itemRepository.getItemsByOrder(orderId)
  }
}