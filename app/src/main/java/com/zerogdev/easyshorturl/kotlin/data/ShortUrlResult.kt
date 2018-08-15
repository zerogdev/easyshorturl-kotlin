package com.zerogdev.easyshorturl.kotlin.data

data class ShortUrlResult(
        override val message: String,
        override val code: String,
        val result:ShortUrlData) : ResponseData