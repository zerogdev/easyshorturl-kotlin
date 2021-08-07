package com.zerogdev.easyshorturl.kotlin.presentation

sealed class SharedViewEffects {
    data class PriceVariation(val variation: Int): SharedViewEffects()
}