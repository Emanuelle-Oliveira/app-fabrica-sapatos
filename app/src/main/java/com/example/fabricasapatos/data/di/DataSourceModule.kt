package com.example.fabricasapatos.data.di

import com.example.fabricasapatos.data.client.FirebaseClientDataSource
import com.example.fabricasapatos.data.client.IClientDataSource
import com.example.fabricasapatos.data.order.FirebaseOrderDataSource
import com.example.fabricasapatos.data.order.IOrderDataSource
import com.example.fabricasapatos.data.product.FirebaseProductDataSource
import com.example.fabricasapatos.data.product.IProductDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

  @Singleton
  @Binds
  fun bindClientDataSource(dataSource: FirebaseClientDataSource): IClientDataSource

  @Singleton
  @Binds
  fun bindProductDataSource(dataSource: FirebaseProductDataSource): IProductDataSource

  @Singleton
  @Binds
  fun bindOrderDataSource(dataSource: FirebaseOrderDataSource): IOrderDataSource
}