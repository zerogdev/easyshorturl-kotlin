package com.zerogdev.easyshorturl.kotlin.data.api.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CoinResponse(
    val id: String?,
    val rank: String?,
    val symbol: String?,
    val name: String?,
    val supply: String?,
    val maxSupply: String?,
    val marketCapUsd: String?,
    val volumeUsd24Hr: String?,
    val priceUsd: String?,
    val changePercent24Hr: String?,
    val vwap24Hr: String?
)