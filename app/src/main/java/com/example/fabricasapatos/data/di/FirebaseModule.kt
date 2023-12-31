package com.example.fabricasapatos.data.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

  @Singleton
  @Provides
  fun provideDatabase(): FirebaseDatabase {
    return FirebaseDatabase.getInstance()
  }

  @Singleton
  @Provides
  fun provideStorage(): FirebaseStorage {
    return FirebaseStorage.getInstance()
  }
}