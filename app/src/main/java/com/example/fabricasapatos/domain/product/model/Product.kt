package com.example.fabricasapatos.domain.product.model
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
  var id: Int,
  var description: String,
  var value: Double,
  var imageUrl: String
): Parcelable