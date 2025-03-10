package com.example.cashapp

import android.util.Log
import com.example.cashapp.data.StockListApi
import com.example.cashapp.data.StockRepositoryImpl
import com.example.cashapp.domain.StockData
import com.example.cashapp.domain.StockResponse
import com.example.cashapp.domain.util.Result
import com.google.gson.JsonSyntaxException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class StockRepositoryTest {

    private val mockApi: StockListApi = mockk()

    @Test
    fun test_successful_response() = runBlocking {

        val fakeResponse = Response.success(
            StockResponse(
                stocks = (listOf(StockData("AAPL", "Apple", "USD", 14500, 5, 1681845832)))
            )
        )
        coEvery { mockApi.getStockLists() } returns fakeResponse
        val repository = StockRepositoryImpl(mockApi)
        val result = repository.getStockList().toList()


        assertTrue(result[0] is Result.Loading)
        assertTrue(result[1] is Result.Success)
        assertTrue((result[1] as Result.Success).data.isNotEmpty())
    }

    @Test
    fun test_empty_list_response() = runBlocking {

        val fakeResponse = Response.success(StockResponse(stocks = emptyList()))
        coEvery { mockApi.getStockLists() } returns fakeResponse
        val repository = StockRepositoryImpl(mockApi)
        val result = repository.getStockList().toList()

        assertTrue(result[0] is Result.Loading)
        assertTrue(result[1] is Result.Error)
        assertEquals("No stock data available. Please try again later", (result[1] as Result.Error).message)
    }

    @Test
    fun test_network_failure() = runBlocking {

        coEvery { mockApi.getStockLists() } throws IOException("Failed to connect")
        val repository = StockRepositoryImpl(mockApi)
        val result = repository.getStockList().toList()

        assertTrue(result[0] is Result.Loading)
        assertTrue(result[1] is Result.Error)
        assertEquals("No internet connection. Please check your network.", (result[1] as Result.Error).message)
    }

    //Check your error codes
    @Test
    fun test_http_exception() = runBlocking {

        coEvery { mockApi.getStockLists() } throws HttpException(Response.error<StockResponse>(
            404, "Not Found".toResponseBody("application/json".toMediaTypeOrNull())
        ))
        val repository = StockRepositoryImpl(mockApi)
        val result = repository.getStockList().toList()

        assertTrue(result[0] is Result.Loading)
        assertTrue(result[1] is Result.Error)
        assertEquals("HTTP 404: Requested data not found.", (result[1] as Result.Error).message)
    }

    @Test
    fun test_malformed_json(): Unit = runBlocking {

        coEvery { mockApi.getStockLists() } throws JsonSyntaxException("Malformed JSON")

        val repository = StockRepositoryImpl(mockApi)
        val result = repository.getStockList().toList()

        assertTrue(result[0] is Result.Loading)
        assertTrue(result[1] is Result.Error)
        assertEquals("Oops! Something went wrong. Please try again later.", (result[1] as Result.Error).message)
    }

}