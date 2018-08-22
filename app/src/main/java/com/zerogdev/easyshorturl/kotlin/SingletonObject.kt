package com.zerogdev.easyshorturl.kotlin

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * object 는 Singleton 생성
 * 선언을 하면 객체가 생성 됨
 * - class 상속 가능
 * - interface 구현 가능
 * - class 내 object 가능
 */
object SingletonObject {

    //property 접근 가능
    var text : String = "singleton test"

    //메소드 접근 가능
    fun showText() {
        Log.d("TAG", "SingletonObject:"+ text)
    }
}