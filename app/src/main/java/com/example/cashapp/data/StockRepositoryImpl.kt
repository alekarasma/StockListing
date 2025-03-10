package com.example.cashapp.data

import android.util.Log
import com.example.cashapp.domain.StockData
import com.example.cashapp.domain.StockRepository
import com.example.cashapp.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


class StockRepositoryImpl(private val api: StockListApi) : StockRepository {

    override suspend fun getStockList(): Flow<Result<List<StockData>>> = flow {
        try {
            emit(Result.Loading)
            val response = api.getStockLists()
            if (response.isSuccessful) {
                val stockList = response.body()?.stocks ?: emptyList()
                if (stockList.isEmpty()) {
                   Timber.e("Empty response received")
                    emit(Result.Error("No stock data available. Please try again later"))
                } else {
                   Timber.d("Success: $stockList")
                    emit(Result.Success(stockList))
                }

            } else {
               Timber.e("Unknown error: ${response.errorBody()}")
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                emit(Result.Error(errorMessage))
            }
        } catch (e: IOException) {
           Timber.e("Network Error: ${e.message}")
            emit(Result.Error("No internet connection. Please check your network."))
        } catch (e: HttpException) {
           Timber.e("HttpException: ${e.message}")
            emit(Result.Error(e.code().toErrorMessage(e.response()?.errorBody()?.string())))
        } catch (e: Exception) {
           Timber.e("Unexpected Error: ${e.message}")
            emit(Result.Error("Oops! Something went wrong. Please try again later."))
        }
    }

}