package com.zerogdev.easyshorturl.kotlin.domain.repositories

import com.zerogdev.easyshorturl.kotlin.domain.model.Coin
import com.zerogdev.easyshorturl.kotlin.domain.model.CoinHistory
import com.zerogdev.easyshorturl.kotlin.domain.Result

interface CoinsRepository {
    suspend fun getCoins(): Result<List<Coin>>
    suspend fun getCoinHistory(coinId: String): Result<CoinHistory>
}