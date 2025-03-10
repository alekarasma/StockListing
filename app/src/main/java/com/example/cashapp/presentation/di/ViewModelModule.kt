package com.example.cashapp.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import com.example.cashapp.presentation.StockViewModel

val ViewModelModule = module {
    viewModelOf(::StockViewModel)
}