package com.example.fabricasapatos.domain.product.usecases.contracts

import com.example.fabricasapatos.domain.product.types.DescriptionAndIdProduct

interface IGetDescriptionAndIdProductsUseCase {
  suspend operator fun invoke(): List<DescriptionAndIdProduct>
}