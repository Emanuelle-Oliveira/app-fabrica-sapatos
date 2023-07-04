package com.example.fabricasapatos.domain.order.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
  var id: Int,
  var date: String,
  var clientCpf: String
): Parcelable
