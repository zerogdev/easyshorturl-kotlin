package com.zerogdev.easyshorturl.kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.zerogdev.easyshorturl.kotlin.service.NaverService
import com.zerogdev.easyshorturl.kotlin.util.DataManager

//apply plugin: 'kotlin-android-extensions' 을 추가하고 import 하면 R.id에 바로 접근이 가능
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //lateinit var 선언
    private lateinit var dataManager : DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        dataManager = DataManager()

        //version
        version_text.text = "ver " + BuildConfig.VERSION_NAME
        //생성 버튼
        var shortenBtn:Button = findViewById(R.id.shorten_btn)
        shortenBtn.setOnClickListener { it ->
            //람다 표현식
            //let 사용
            enter_url.text.toString().let { text ->
                if (text.isNotEmpty()) {
                    //람다 표현식으로 콜백 사용하기
                    dataManager.loadShorturl(
                            text,
                            {
                                Toast.makeText(this, it.url, Toast.LENGTH_SHORT).show()
                                share(it.url)
                            },
                            { _, t ->
                                Toast.makeText(this, "error: " + t.message, Toast.LENGTH_SHORT).show()
                            })
                }
            }
        }



        //edittext clear X 버튼
        //apply 를 사용해서 클릭 리스너 설정
        View.OnClickListener { enter_url.text.clear() }.apply {
            text_clear_btn.setOnClickListener(this)
        }


        //object
        SingletonObject.showText()
        SingletonObject.text = "changed text"
        SingletonObject.showText()
    }

    private fun share(url: String) {
        var shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, url)
        }
        val clipboardIntent = CopyToClipboardActivity.createCopyToClipboardIntent(this, url)
        val title = getString(R.string.share) + " " + url
        val chooserIntent = Intent.createChooser(shareIntent, title)
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, clipboardIntent)

        startActivity(chooserIntent)
    }
}
