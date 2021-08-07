package com.zerogdev.easyshorturl.kotlin.data.api

import android.content.Context
import android.util.Log
import com.zerogdev.easyshorturl.kotlin.data.api.ApiConstants.ASSETS
import com.zerogdev.easyshorturl.kotlin.data.api.ApiConstants.BASE_ENDPOINT
import com.zerogdev.easyshorturl.kotlin.data.api.ApiConstants.HISTORY
import com.zerogdev.easyshorturl.kotlin.data.api.interceptors.NetworkStatusInterceptor
import com.zerogdev.easyshorturl.kotlin.data.api.model.CoinListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinCapApi {

    @GET("$BASE_ENDPOINT$ASSETS")
    suspend fun getCoins(@Query("limit") limit: Int = 50): CoinListResponse



    companion object {

        fun create(context: Context): CoinCapApi {
            return Retrofit.Builder()
                .baseUrl(BASE_ENDPOINT)
                .client(createOkHttpClient(context))
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(CoinCapApi::class.java)
        }

        private fun createOkHttpClient(context: Context): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(NetworkStatusInterceptor(ConnectionManager(context)))
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }

        private val httpLoggingInterceptor: HttpLoggingInterceptor
            get() = HttpLoggingInterceptor { message ->
                Log.i("Network", message)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
    }

}