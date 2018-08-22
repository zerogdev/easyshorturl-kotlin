package com.zerogdev.easyshorturl.kotlin

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import okhttp3.OkHttpClient

class CopyToClipboardActivity : AppCompatActivity() {

    //companion object: java의 static 기능
    //class 내부 property 접근 가능 -> factory method 가능
    companion object {

        fun createCopyToClipboardIntent(context: Context, url: String) : Intent {
            return Intent(context, CopyToClipboardActivity::class.java).apply { data = Uri.parse(url) }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.data?.let { ClipBoardFactory.makeClip(this, it.toString()) }
    }

//    private fun copyTextToClipboard(url:String) {
//        val manager : ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//        val clip : ClipData = ClipData.newPlainText("URL", url)
//        manager.primaryClip = clip
//    }
}