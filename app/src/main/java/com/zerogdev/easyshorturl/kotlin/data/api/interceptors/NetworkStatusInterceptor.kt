package com.zerogdev.easyshorturl.kotlin.data.api.interceptors

import com.zerogdev.easyshorturl.kotlin.data.api.ConnectionManager
import com.zerogdev.easyshorturl.kotlin.domain.model.NetworkUnavailable
import okhttp3.Interceptor
import okhttp3.Response

class NetworkStatusInterceptor(private val connectionManager: ConnectionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected) {
            chain.proceed(chain.request())
        } else {
            throw NetworkUnavailable()
        }
    }
}