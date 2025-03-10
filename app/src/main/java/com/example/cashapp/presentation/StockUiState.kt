package com.example.cashapp.presentation

data class StockUiState(
    val ticker: String = "",
    val name: String = "",
    val currency: String = "",
    val currentPriceDollar: String = "",
    val quantity: Int = 0,
    val currentPriceStamp: Long = 0,
)