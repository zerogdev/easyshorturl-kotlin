package com.zerogdev.easyshorturl.kotlin.service

import com.zerogdev.easyshorturl.kotlin.data.ShortUrlResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverService {

    @Headers("X-Naver-Client-Id: S6tVBFMJX60O9gQTzkM3", "X-Naver-Client-Secret: 42K5qKKhyg")
    @GET("/v1/util/shorturl")
    fun getShortUrl(@Query("url") url: String): Call<ShortUrlResult>



    //static final
    companion object  {
        const val ENDPOINT = "https://openapi.naver.com"
    }

}