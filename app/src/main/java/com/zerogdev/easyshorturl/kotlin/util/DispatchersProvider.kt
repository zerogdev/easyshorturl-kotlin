package com.zerogdev.easyshorturl.kotlin.util

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}