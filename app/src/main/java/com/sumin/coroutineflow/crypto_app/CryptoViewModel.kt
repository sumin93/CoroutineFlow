package com.sumin.coroutineflow.crypto_app

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

    val state: Flow<State> = repository.getCurrencyList()
        .filter { it.isNotEmpty() }
        .map { State.Content(currencyList = it) as State }
        .onStart {
            Log.d("CryptoViewModel", "Started")
            emit(State.Loading)
        }
        .onEach {
            Log.d("CryptoViewModel", "OnEach")
        }
        .onCompletion {
            Log.d("CryptoViewModel", "Complete")
        }
}
