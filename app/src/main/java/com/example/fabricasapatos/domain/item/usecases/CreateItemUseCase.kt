package com.example.fabricasapatos.domain.item.usecases

import android.util.Log
import com.example.fabricasapatos.data.item.ItemRepository
import com.example.fabricasapatos.data.order.OrderRepository
import com.example.fabricasapatos.domain.item.model.Item
import com.example.fabricasapatos.domain.item.usecases.contracts.ICreateItemUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CreateItemUseCase @Inject constructor(
  private val itemRepository: ItemRepository,
  private val orderRepository: OrderRepository
): ICreateItemUseCase {
  override suspend fun invoke(id: Int, productId: Int, quantity: Int): Item {
    return try {
      //val id = itemRepository.getLastItemId()
      //itemRepository.updateLastItemId(id)

      val orderId = orderRepository.getLastOrderId()

      val item = Item(id, orderId, productId, quantity)
      Log.i("teste", item.toString())
      itemRepository.createItem(item)
    } catch (e: Exception) {
      throw e
    }
  }
}