package com.zerogdev.easyshorturl.kotlin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CoinsSharedViewModel: ViewModel() {
    private val _sharedViewEffects = MutableSharedFlow<SharedViewEffects>()
    val sharedViewEffects: SharedFlow<SharedViewEffects> = _sharedViewEffects.asSharedFlow()

    init {
        getPriceVariations()
    }

    private fun getPriceVariations() {
        viewModelScope.launch {
            for (i in 1..100) {
                delay(5000)
                ensureActive()
                _sharedViewEffects.emit(SharedViewEffects.PriceVariation(i))
            }
        }
    }
}