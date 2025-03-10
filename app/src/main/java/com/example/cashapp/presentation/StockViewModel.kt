package com.example.cashapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cashapp.domain.StockRepository
import com.example.cashapp.domain.util.Result
import com.example.cashapp.presentation.util.map
import com.example.cashapp.presentation.util.toStockUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StockViewModel(private val stockRepository: StockRepository) : ViewModel() {

    private val _uiStockState = MutableStateFlow<Result<List<StockUiState>>>(Result.Success(emptyList()))
    val uiStockState = _uiStockState.asStateFlow()


    init {
        fetchStockList()
    }

    private fun fetchStockList() {
        viewModelScope.launch {
            stockRepository.getStockList()
                .collectLatest { result ->
                    _uiStockState.value = result.map { stockList ->
                        stockList.map { it.toStockUiState() }
                    }
                }
        }
    }
}