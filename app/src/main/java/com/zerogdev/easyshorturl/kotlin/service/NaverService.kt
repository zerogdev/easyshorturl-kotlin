package com.zerogdev.easyshorturl.kotlin.service

import com.zerogdev.easyshorturl.kotlin.data.ShortUrlResult
import com.zerogdev.easyshorturl.kotlin.util.DataManager
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverService {

    //static final
    companion object  {
        const val ENDPOINT = "https://openapi.naver.com"
    }

    @Headers("X-Naver-Client-Id: ${NaverConsts.CLIENT_ID}", "X-Naver-Client-Secret: ${NaverConsts.CLIENT_SECRET}")
    @GET("/v1/util/shorturl")
    fun getShortUrl(@Query("url") url: String): Call<ShortUrlResult>





}