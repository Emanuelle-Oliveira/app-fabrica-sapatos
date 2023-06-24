package com.example.fabricasapatos

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
  override fun onCreate() {
    super.onCreate()
    FirebaseApp.initializeApp(this)
  }
}