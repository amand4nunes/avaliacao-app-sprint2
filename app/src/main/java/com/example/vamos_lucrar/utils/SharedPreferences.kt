package com.example.vamos_lucrar.utils

import android.content.Context

class SharedPreferences(contexto: Context) {
    private val sharedPreferences =
        contexto.getSharedPreferences("app", Context.MODE_PRIVATE)

    fun storeId(key: String, value: Int){
        sharedPreferences.edit().putInt(key,value).apply()
    }
    fun getId(key:String) :Int{

        return sharedPreferences.getInt(key, 0)
    }
}