package com.zerogdev.easyshorturl.kotlin.presentation.list.mappers

import com.zerogdev.easyshorturl.kotlin.domain.model.Coin
import com.zerogdev.easyshorturl.kotlin.presentation.list.model.UiCoin
import com.zerogdev.easyshorturl.kotlin.util.NumberFormatter

class UiCoinMapper(private val formatter: NumberFormatter) {
    companion object {
        private const val EMPTY_VALUE = "N/A"
    }

    fun toUi(coin: Coin): UiCoin {
        val supply = if (coin.supply == Coin.EMPTY_SUPPLY) {
            EMPTY_VALUE
        } else {
            formatter.compressedFormattedValueOf(coin.supply)
        }

        val marketCap = if (coin.marketCapUsd == Coin.EMTPY_MARKET_CAP) {
            EMPTY_VALUE
        } else {
            "$${formatter.compressedFormattedValueOf(coin.marketCapUsd)}"
        }

        val changePercentage = if (coin.changePercent24Hr == Coin.EMPTY_CHANGE_PERCENT) {
            EMPTY_VALUE
        } else {
            "%.2f%%".format(coin.changePercent24Hr)
        }

        return UiCoin(
            id = coin.id,
            symbol = coin.symbol,
            name = coin.name,
            supply = supply,
            marketCapUsd = marketCap,
            priceUsd = "$${formatter.extendedFormattedValueOf(coin.priceUsd)}",
            changePercent24Hr = changePercentage,
            image = coin.image
        )
    }

}