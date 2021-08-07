package com.zerogdev.easyshorturl.kotlin.presentation.list

sealed class ListFragmentViewEffects {
    data class Failure(val cause: Throwable): ListFragmentViewEffects()
}