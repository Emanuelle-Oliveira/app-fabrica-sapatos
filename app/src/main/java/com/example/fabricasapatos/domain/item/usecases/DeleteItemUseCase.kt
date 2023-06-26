package com.example.fabricasapatos.domain.item.usecases

import com.example.fabricasapatos.data.item.ItemRepository
import com.example.fabricasapatos.domain.item.usecases.contracts.IDeleteItemUseCase
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(
  private val itemRepository: ItemRepository
): IDeleteItemUseCase {
  override suspend fun invoke(id: Int): Int {
    return itemRepository.deleteItem(id)
  }
}