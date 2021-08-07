package com.zerogdev.easyshorturl.kotlin.domain.model

import com.zerogdev.easyshorturl.kotlin.domain.Result
import java.math.BigDecimal
import java.time.Instant

class CoinHistory private constructor(
    val pricesOverTime: Map<Instant, BigDecimal>
){
    companion object {
        fun of(mapping: Map<String?, String?>) = Result {
            CoinHistory(
                mapping
                    .filter { it.key != null && it.value != null }
                    .map {  Instant.parse(it.key!!) to BigDecimal(it.value)}.toMap()
            )
        }
    }
}