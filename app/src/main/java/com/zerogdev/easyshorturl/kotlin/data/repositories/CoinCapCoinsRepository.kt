package com.zerogdev.easyshorturl.kotlin.data.repositories

import com.zerogdev.easyshorturl.kotlin.data.api.CoinCapApi
import com.zerogdev.easyshorturl.kotlin.data.mappers.CoinMapper
import com.zerogdev.easyshorturl.kotlin.domain.Result
import com.zerogdev.easyshorturl.kotlin.domain.model.Coin
import com.zerogdev.easyshorturl.kotlin.domain.model.CoinHistory
import com.zerogdev.easyshorturl.kotlin.domain.repositories.CoinsRepository
import com.zerogdev.easyshorturl.kotlin.domain.requireValue
import com.zerogdev.easyshorturl.kotlin.util.DispatchersProvider
import kotlinx.coroutines.withContext

class CoinCapCoinsRepository(
    private val dispatchersProvider: DispatchersProvider,
    private val coinMapper: CoinMapper,
    private val coinCapApi: CoinCapApi
    ): CoinsRepository {

    override suspend fun getCoins(): Result<List<Coin>> = withContext(dispatchersProvider.io()){
        Result {
           coinCapApi.getCoins().data
               .orEmpty()
               .map { coinMapper.toDomain(it) }
               .map { it.requireValue() }
        }
    }

    override suspend fun getCoinHistory(coinId: String): Result<CoinHistory> {
        TODO("Not yet implemented")
    }

}