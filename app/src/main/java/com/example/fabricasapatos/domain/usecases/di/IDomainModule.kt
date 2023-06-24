package com.example.fabricasapatos.domain.usecases.di

import androidx.activity.ComponentActivity
import com.example.fabricasapatos.domain.usecases.CreateClientUseCase
import com.example.fabricasapatos.domain.usecases.GetClientsUseCase
import com.example.fabricasapatos.domain.usecases.contracts.ICreateClientUseCase
import com.example.fabricasapatos.domain.usecases.contracts.IGetClientsUseCase
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
  fun bindGetClientsUseCase(useCase: GetClientsUseCase): IGetClientsUseCase
}