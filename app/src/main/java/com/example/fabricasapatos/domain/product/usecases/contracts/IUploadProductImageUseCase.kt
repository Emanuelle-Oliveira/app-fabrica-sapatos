package com.example.fabricasapatos.domain.product.usecases.contracts

import android.net.Uri

interface IUploadProductImageUseCase {
  suspend operator fun invoke(imageUri: Uri): String
}