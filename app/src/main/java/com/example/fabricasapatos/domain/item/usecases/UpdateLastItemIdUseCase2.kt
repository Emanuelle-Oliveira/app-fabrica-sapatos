package com.example.fabricasapatos.domain.item.usecases

import com.example.fabricasapatos.data.item.ItemRepository
import com.example.fabricasapatos.domain.item.model.Item
import com.example.fabricasapatos.domain.item.usecases.contracts.IUpdateLastItemIdUseCase2

import javax.inject.Inject

class UpdateLastItemIdUseCase2 @Inject constructor(
  private val itemRepository: ItemRepository
): IUpdateLastItemIdUseCase2 {

  override suspend fun invoke(id: Int, size: Int): Int {
    return itemRepository.updateLastItemId2(id, size)
  }
}