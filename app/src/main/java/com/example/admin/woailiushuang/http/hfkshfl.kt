package com.example.admin.woailiushuang.http

import com.example.admin.woailiushuang.progress.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class hfkshfl {


    fun fuc() {
        val gson = Gson()
        val provinceList = gson.fromJson<List<Question>>("", object : TypeToken<List<Question>>() {

        }.type)
    }

}
