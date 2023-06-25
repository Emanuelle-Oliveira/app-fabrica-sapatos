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
import com.example.fabricasapatos.domain.product.usecases.GetProductsUseCase
import com.example.fabricasapatos.domain.product.usecases.UpdateProductUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.ICreateProductUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.IDeleteProductUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.IGetProductsUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.IUpdateProductUseCase
import com.example.fabricasapatos.domain.product.usecases.contracts.IUploadProductImageUseCase
import com.example.fabricasapatos.domain.product.usecases.UploadProductImageUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface IDomainModule {

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

  @Binds
  fun bindCreateProductUseCase(useCase: CreateProductUseCase): ICreateProductUseCase

  @Binds
  fun bindUpdateProductUseCase(useCase: UpdateProductUseCase): IUpdateProductUseCase

  @Binds
  fun bindGetProductsUseCase(useCase: GetProductsUseCase): IGetProductsUseCase

  @Binds
  fun bindDeleteProductUseCase(useCase: DeleteProductUseCase): IDeleteProductUseCase

  @Binds
  fun bindUploadProductImageUseCase(useCase: UploadProductImageUseCase): IUploadProductImageUseCase

  @Binds
  fun bindCreateOrderUseCase(useCase: CreateOrderUseCase): ICreateOrderUseCase

  @Binds
  fun bindUpdateOrderUseCase(useCase: UpdateOrderUseCase): IUpdateOrderUseCase

  @Binds
  fun bindGetOrdersByClientUseCase(useCase: GetOrdersByClientUseCase): IGetOrdersByClientUseCase

  @Binds
  fun bindDeleteOrderUseCase(useCase: DeleteOrderUseCase): IDeleteOrderUseCase
}