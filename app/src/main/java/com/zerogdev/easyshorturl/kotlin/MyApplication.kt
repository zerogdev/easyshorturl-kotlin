package com.zerogdev.easyshorturl.kotlin

import android.app.Application
import com.zerogdev.easyshorturl.kotlin.data.api.CoinCapApi
import com.zerogdev.easyshorturl.kotlin.data.mappers.CoinMapper
import com.zerogdev.easyshorturl.kotlin.data.repositories.CoinCapCoinsRepository
import com.zerogdev.easyshorturl.kotlin.domain.repositories.CoinsRepository
import com.zerogdev.easyshorturl.kotlin.presentation.CoinsSharedViewModelFactory
import com.zerogdev.easyshorturl.kotlin.presentation.DefaultDispatchersProvider
import com.zerogdev.easyshorturl.kotlin.presentation.list.ListFragmentViewModelFactory

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val repository = createRepository()

        CoinsSharedViewModelFactory.inject(repository)
        ListFragmentViewModelFactory.inject(repository)

    }

    private fun createRepository(): CoinsRepository {
        return CoinCapCoinsRepository(
            DefaultDispatchersProvider(),
            CoinMapper(),
            CoinCapApi.create(this)
        )
    }

}