package com.zerogdev.easyshorturl.kotlin.util

import com.zerogdev.easyshorturl.kotlin.data.ShortUrlData
import com.zerogdev.easyshorturl.kotlin.data.ShortUrlResult
import com.zerogdev.easyshorturl.kotlin.service.NaverService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataManager{

    private var naverService : NaverService ?= null
    private val mClient: OkHttpClient


    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        mClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        createNaverApi()
    }


    private fun createNaverApi() {
        naverService = Retrofit.Builder()
                .baseUrl(NaverService.ENDPOINT)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverService::class.java) //자바코드의 class 함수 사용 방법
    }

    /**
     * 단축URL 생성 요청
     */
    fun loadShorturl(url:String, success: (ShortUrlData) -> Unit, error: (Call<ShortUrlResult>, Throwable) -> Unit) {
        val call = naverService!!.getShortUrl(url)
        call.enqueue(object:Callback<ShortUrlResult> {

            override fun onResponse(call: Call<ShortUrlResult>?, response: Response<ShortUrlResult>?) {
                val data = response?.body()?.result
                data?.let { success(data) }
            }

            override fun onFailure(call: Call<ShortUrlResult>?, t: Throwable?) {
                call?.let {
                    if (t != null) {
                        error(it, t)
                    }
                }
            }
        })
    }

}