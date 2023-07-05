package com.example.fabricasapatos.domain.item.usecases

import android.util.Log
import com.example.fabricasapatos.data.item.ItemRepository
import com.example.fabricasapatos.domain.item.model.Item
import com.example.fabricasapatos.domain.item.usecases.contracts.IGetLastItemIdUseCase
import javax.inject.Inject

class GetLastItemIdUseCase @Inject constructor(
  private val itemRepository: ItemRepository,
): IGetLastItemIdUseCase {
  override suspend fun invoke(): Int {
    return try {
      val id = itemRepository.getLastItemId()
      id
    } catch (e: Exception) {
      throw e
    }
  }
}