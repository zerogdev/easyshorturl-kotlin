package com.zerogdev.easyshorturl.kotlin.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.zerogdev.easyshorturl.kotlin.BuildConfig
import com.zerogdev.easyshorturl.kotlin.CopyToClipboardActivity
import com.zerogdev.easyshorturl.kotlin.R
import com.zerogdev.easyshorturl.kotlin.SingletonObject
import com.zerogdev.easyshorturl.kotlin.presentation.list.ListFragment
import com.zerogdev.easyshorturl.kotlin.util.DataManager

class MainActivity : AppCompatActivity() {

    //lateinit var 선언
    private lateinit var dataManager : DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ListFragment>(R.id.fragment_container)
            }
        }

        dataManager = DataManager()

        //version
        findViewById<TextView>(R.id.version_text).text = "ver " + BuildConfig.VERSION_NAME
        //생성 버튼
        var shortenBtn:Button = findViewById(R.id.shorten_btn)
        shortenBtn.setOnClickListener { it ->
            //람다 표현식
            //let 사용
            findViewById<TextView>(R.id.enter_url).text.toString().let { text ->
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
        View.OnClickListener { findViewById<EditText>(R.id.enter_url).text.clear() }.apply {
            findViewById<ImageView>(R.id.text_clear_btn).setOnClickListener(this)
        }


        //object
        SingletonObject.showText()
        SingletonObject.text = "changed text"
        SingletonObject.showText()

        kotlinFunc()
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


    fun kotlinFunc() {
        val numbers = arrayListOf<Int>(1,1721, 979, 366, 299, 675, 1456)
        val sum = 2020
        val complements = numbers.associateBy {
            Log.d("LYK", "${it}, ${ sum - it }")
            sum - it
        }
        Log.d("LYK", complements.toString())
        val pair = numbers.mapNotNull { number ->
            val c = complements[number]
            if (c != null) {
                Pair(number, complements)
            } else {
                null
            }
        }.first()
        Log.d("LYK", pair.toString())
        val pair2 =  numbers.firstNotNullOf { number ->
            val c = complements[number]
            if (c != null) {
                Pair(number, complements)
            } else {
                null
            }
        }
        Log.d("LYK", pair2.toString())
    }
}
