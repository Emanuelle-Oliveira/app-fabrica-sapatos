package com.example.fabricasapatos.domain.di

import com.example.fabricasapatos.domain.client.usecases.CreateClientUseCase
import com.example.fabricasapatos.domain.client.usecases.DeleteClientUseCase
import com.example.fabricasapatos.domain.client.usecases.GetClientsUseCase
import com.example.fabricasapatos.domain.client.usecases.GetNameAndCpfClientsUseCase
import com.example.fabricasapatos.domain.client.usecases.UpdateClientUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.ICreateClientUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.IDeleteClientUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetClientsUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetNameAndCpfClientsUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.IUpdateClientUseCase
import com.example.fabricasapatos.domain.item.usecases.CreateItemUseCase
import com.example.fabricasapatos.domain.item.usecases.DeleteItemUseCase
import com.example.fabricasapatos.domain.item.usecases.UpdateItemUseCase
import com.example.fabricasapatos.domain.item.usecases.GetItemsByOrderUseCase
import com.example.fabricasapatos.domain.item.usecases.GetLastItemIdUseCase
import com.example.fabricasapatos.domain.item.usecases.UpdateLastItemIdUseCase2
import com.example.fabricasapatos.domain.item.usecases.contracts.ICreateItemUseCase
import com.example.fabricasapatos.domain.item.usecases.contracts.IDeleteItemUseCase
import com.example.fabricasapatos.domain.item.usecases.contracts.IGetItemsByOrderUseCase
import com.example.fabricasapatos.domain.item.usecases.contracts.IGetLastItemIdUseCase
import com.example.fabricasapatos.domain.item.usecases.contracts.IUpdateItemUseCase
import com.example.fabricasapatos.domain.item.usecases.contracts.IUpdateLastItemIdUseCase2
import com.example.fabricasapatos.domain.order.usecases.CreateOrderUseCase
import com.example.fabricasapatos.domain.order.usecases.DeleteOrderUseCase
import com.example.fabricasapatos.domain.order.usecases.GetOrdersByClientUseCase
import com.example.fabricasapatos.domain.order.usecases.contracts.ICreateOrderUseCase
import com.example.fabricasapatos.domain.order.usecases.contracts.IUpdateOrderUseCase
import com.example.fabricasapatos.domain.order.usecases.UpdateOrderUseCase
import com.example.fabricasapatos.domain.order.usecases.contracts.IDeleteOrderUseCase
import com.example.fabricasapatos.domain.order.usecases.contracts.IGetOrdersByClientUseCase
import com.example.fabricasapatos.domain.product.usecases.CreateProductUseCase
import com.example.fabricasapatos.domain.product.usecases.DeleteProductUseCase
import com.example.fabricasapatos.domain.product.usecases.GetDescriptionAndIdProductsUseCase
import com.example.fabricasapatos.domain.product.usecases.GetProductsUseCase
import com.example.fabricasapatos.domain.product.usecases.UpdateProductUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.ICreateProductUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.IDeleteProductUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.IGetProductsUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.IUpdateProductUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.IUploadProductImageUseCase
import com.example.fabricasapatos.domain.product.usecases.UploadProductImageUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.IGetDescriptionAndIdProductsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface IDomainModule {

  // CLIENT
  @Binds
  fun bindCreateClientUseCase(useCase: CreateClientUseCase): ICreateClientUseCase

  @Binds
  fun bindUpdateClientUseCase(useCase: UpdateClientUseCase): IUpdateClientUseCase

  @Binds
  fun bindGetClientsUseCase(useCase: GetClientsUseCase): IGetClientsUseCase

  @Binds
  fun bindGetNameAndCpfClientsUseCase(useCase: GetNameAndCpfClientsUseCase): IGetNameAndCpfClientsUseCase

  @Binds
  fun bindDeleteClientUseCase(useCase: DeleteClientUseCase): IDeleteClientUseCase

  // PRODUCT
  @Binds
  fun bindCreateProductUseCase(useCase: CreateProductUseCase): ICreateProductUseCase

  @Binds
  fun bindUpdateProductUseCase(useCase: UpdateProductUseCase): IUpdateProductUseCase

  @Binds
  fun bindGetProductsUseCase(useCase: GetProductsUseCase): IGetProductsUseCase

  @Binds
  fun bindGetDescriptionAndIdProductsUseCase(useCase: GetDescriptionAndIdProductsUseCase): IGetDescriptionAndIdProductsUseCase

  @Binds
  fun bindDeleteProductUseCase(useCase: DeleteProductUseCase): IDeleteProductUseCase

  @Binds
  fun bindUploadProductImageUseCase(useCase: UploadProductImageUseCase): IUploadProductImageUseCase

  // ORDER
  @Binds
  fun bindCreateOrderUseCase(useCase: CreateOrderUseCase): ICreateOrderUseCase

  @Binds
  fun bindUpdateOrderUseCase(useCase: UpdateOrderUseCase): IUpdateOrderUseCase

  @Binds
  fun bindGetOrdersByClientUseCase(useCase: GetOrdersByClientUseCase): IGetOrdersByClientUseCase

  @Binds
  fun bindDeleteOrderUseCase(useCase: DeleteOrderUseCase): IDeleteOrderUseCase

  // ITEM
  @Binds
  fun bindCreateItemUseCase(useCase: CreateItemUseCase): ICreateItemUseCase

  @Binds
  fun bindUpdateItemUseCase(useCase: UpdateItemUseCase): IUpdateItemUseCase

  @Binds
  fun bindGetItemsByOrderUseCase(useCase: GetItemsByOrderUseCase): IGetItemsByOrderUseCase

  @Binds
  fun bindDeleteItemUseCase(useCase: DeleteItemUseCase): IDeleteItemUseCase

  @Binds
  fun bindGetLastItemIdUseCase(useCase: GetLastItemIdUseCase): IGetLastItemIdUseCase

  @Binds
  fun bindUpdateLastItemIdUseCase2(useCase: UpdateLastItemIdUseCase2): IUpdateLastItemIdUseCase2
}