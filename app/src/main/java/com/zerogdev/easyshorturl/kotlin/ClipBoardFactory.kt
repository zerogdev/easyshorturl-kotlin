package com.zerogdev.easyshorturl.kotlin

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log

class ClipBoardFactory private constructor(val context: Context, val url:String) {

    companion object {
        fun makeClip(context: Context, url : String) : ClipBoardFactory = ClipBoardFactory(context, url)
    }

    fun clip() {
        Log.d("TAG", url)
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val clip = ClipData.newPlainText("URL", url)
        clipboard!!.primaryClip = clip
    }
}