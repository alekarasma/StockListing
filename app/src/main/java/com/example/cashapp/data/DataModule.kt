package com.example.cashapp.data

import com.example.cashapp.domain.StockRepository
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType


val DataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(StockListApi.BASE_URL)
            .addConverterFactory(
                Json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()))
            .build()
    }

    single {
        get<Retrofit>().create(StockListApi::class.java)
    }


    single<StockRepository> {
        StockRepositoryImpl(get())
    }
}