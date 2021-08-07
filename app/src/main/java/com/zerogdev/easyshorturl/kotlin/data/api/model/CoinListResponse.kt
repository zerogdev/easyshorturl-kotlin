package com.zerogdev.easyshorturl.kotlin.data.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinListResponse(val data: List<CoinResponse>?)