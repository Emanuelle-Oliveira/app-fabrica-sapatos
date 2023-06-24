package com.example.fabricasapatos.domain.di

import androidx.activity.ComponentActivity
import com.example.fabricasapatos.domain.client.usecases.CreateClientUseCase
import com.example.fabricasapatos.domain.client.usecases.DeleteClientUseCase
import com.example.fabricasapatos.domain.client.usecases.GetClientsUseCase
import com.example.fabricasapatos.domain.client.usecases.UpdateClientUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.ICreateClientUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.IDeleteClientUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.IGetClientsUseCase
import com.example.fabricasapatos.domain.client.usecases.contracts.IUpdateClientUseCase
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
  fun bindDeleteClientUseCase(useCase: DeleteClientUseCase): IDeleteClientUseCase
}