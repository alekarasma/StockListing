package com.example.cashapp.domain

import com.example.cashapp.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getStockList(): Flow<Result<List<StockData>>>
}