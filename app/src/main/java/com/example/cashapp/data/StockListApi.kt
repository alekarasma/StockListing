package com.example.cashapp.data


import com.example.cashapp.domain.StockResponse
import retrofit2.Response
import retrofit2.http.GET

interface StockListApi {

    @GET("portfolio.json")
    suspend fun getStockLists():Response<StockResponse>

    companion object {
        const val BASE_URL = "https://storage.googleapis.com/cash-homework/cash-stocks-api/"
    }
}