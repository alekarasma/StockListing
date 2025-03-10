package com.example.cashapp.presentation.util

import com.example.cashapp.domain.StockData
import com.example.cashapp.domain.util.Result
import com.example.cashapp.presentation.StockUiState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun StockData.toStockUiState(): StockUiState {

    return StockUiState(
        ticker = ticker,
        name = name,
        currency = currency,
        currentPriceDollar = currentPriceInCents.toDollarString(),
        quantity = quantity ?: 0,
        currentPriceStamp = currentPriceStampUtc,
    )
}

fun Int.toDollarString(): String {
    return "$%.2f".format(this / 100.0)
}

fun Long.toFormattedDateParts(): Pair<String, String> {
    val date = Date(this * 1000)
    val timeZone = TimeZone.getDefault()
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).apply {
        this.timeZone = timeZone
    }

    val timeFormat = SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).apply {
        this.timeZone = timeZone
    }

    return Pair(dateFormat.format(date), timeFormat.format(date))
}


/*
 * Transform the data when Result is Success else returns as it is
 */
fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> {
    return when (this) {
        is Result.Success -> Result.Success(transform(data))
        is Result.Error -> this
        is Result.Loading -> this
    }
}