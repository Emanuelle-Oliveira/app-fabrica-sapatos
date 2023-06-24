package com.example.fabricasapatos.data.di

import com.example.fabricasapatos.data.client.FirebaseClientDataSource
import com.example.fabricasapatos.data.client.IClientDataSource
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
}