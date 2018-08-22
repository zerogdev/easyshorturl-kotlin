package com.zerogdev.easyshorturl.kotlin.listener

import com.zerogdev.easyshorturl.kotlin.data.ShortUrlData
import org.jetbrains.annotations.Nullable

interface ShortUrlCallBack {
    fun onShortUrlSuccess(shortUrlData: ShortUrlData)
    fun onShortUrlFail()
}