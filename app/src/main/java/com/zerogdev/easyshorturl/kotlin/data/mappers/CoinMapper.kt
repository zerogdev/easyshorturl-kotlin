package com.zerogdev.easyshorturl.kotlin.data.mappers

import com.zerogdev.easyshorturl.kotlin.data.api.model.CoinResponse
import com.zerogdev.easyshorturl.kotlin.domain.Result
import com.zerogdev.easyshorturl.kotlin.domain.model.Coin

class CoinMapper {

    fun toDomain(coinResponse: CoinResponse): Result<Coin> {
        val (
            id,
            _,
            symbol,
            name,
            supply,
            _,
            marketCapUsd,
            _,
            priceUsd,
            changePercent24Hr
        ) = coinResponse

        return Coin.of(id, symbol, name, supply, marketCapUsd, priceUsd, changePercent24Hr)
    }

}