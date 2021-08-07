package com.zerogdev.easyshorturl.kotlin.presentation

import com.zerogdev.easyshorturl.kotlin.util.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultDispatchersProvider: DispatchersProvider {
    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun main(): CoroutineDispatcher = Dispatchers.Main
}