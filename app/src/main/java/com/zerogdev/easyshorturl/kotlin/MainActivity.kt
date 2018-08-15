package com.zerogdev.easyshorturl.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        version_text.text = "ver " + BuildConfig.VERSION_NAME

        var shortenBtn:Button = findViewById(R.id.shorten_btn)
        shortenBtn.setOnClickListener {
            var enterUrl : String = enter_url.text.toString();
            if (!enterUrl.isEmpty()) {
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
            }
        }

        text_clear_btn.setOnClickListener {
            enter_url.text.clear()
        }

    }
}
