package com.zerogdev.easyshorturl.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.zerogdev.easyshorturl.kotlin.data.ShortUrlData
import com.zerogdev.easyshorturl.kotlin.listener.ShortUrlCallBack
import com.zerogdev.easyshorturl.kotlin.util.DataManager

//apply plugin: 'kotlin-android-extensions' 을 추가하고 import 하면 R.id에 바로 접근이 가능
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var dataManager : DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //version
        version_text.text = "ver " + BuildConfig.VERSION_NAME

        dataManager = DataManager();

        //생성 버튼
        var shortenBtn:Button = findViewById(R.id.shorten_btn)
        shortenBtn.setOnClickListener {
            var enterUrl : String = enter_url.text.toString();
            if (!enterUrl.isEmpty()) {
//                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
                val enterUrl = enter_url.text
                if (!TextUtils.isEmpty(enterUrl)) {
                    dataManager.loadShorturl(enterUrl.toString(), success = {it ->
                        Toast.makeText(this, it.url, Toast.LENGTH_SHORT).show()
                    }, error = { _, t ->
                        Toast.makeText(this, "error: "+t.message, Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }

        //edittext clear X 버튼
        text_clear_btn.setOnClickListener {
            enter_url.text.clear()
        }


    }
}
