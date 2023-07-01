package com.example.fabricasapatos.domain.client.model
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Client(
  var cpf: String,
  var name: String,
  var phone: String,
  var address: String,
  var instagram: String
): Parcelable
