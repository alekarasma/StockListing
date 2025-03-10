package com.example.cashapp.domain

import kotlinx.serialization.Serializable

@Serializable
data class StockResponse(
    val stocks: List<StockData>
)
