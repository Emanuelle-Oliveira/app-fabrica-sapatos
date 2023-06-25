package com.example.fabricasapatos.data.item

import com.example.fabricasapatos.domain.item.model.Item


interface IItemDataSource {
  suspend fun createItem(item: Item): Item

  suspend fun updateItem(item: Item): Item

  suspend fun getItemsByOrder(orderId: Int): List<Item>

  suspend fun deleteItem(id: Int): Int

  suspend fun getLastItemId(): Int

  suspend fun updateLastItemId(id: Int): Int

}