package com.example.fabricasapatos.data.item

import com.example.fabricasapatos.domain.item.model.Item
import javax.inject.Inject

class ItemRepository @Inject constructor(
  private val dataSource: IItemDataSource
) {

  suspend fun createItem(item: Item): Item
      = dataSource.createItem(item)

  suspend fun updateItem(item: Item): Item
      = dataSource.updateItem(item)

  suspend fun getItemsByOrder(orderId: Int): List<Item> = dataSource.getItemsByOrder(orderId)

  suspend fun deleteItem(id: Int): Int
      = dataSource.deleteItem(id)

  suspend fun getLastItemId(): Int = dataSource.getLastItemId()

  suspend fun updateLastItemId(id: Int): Int = dataSource.updateLastItemId(id)
}