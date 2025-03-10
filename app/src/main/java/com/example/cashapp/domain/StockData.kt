package com.example.cashapp.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockData(
    val ticker: String,
    val name: String,
    val currency: String,
    @SerialName("current_price_cents")
    val currentPriceInCents: Int,
    val quantity: Int?,
    @SerialName("current_price_timestamp")
    val currentPriceStampUtc: Long,
)