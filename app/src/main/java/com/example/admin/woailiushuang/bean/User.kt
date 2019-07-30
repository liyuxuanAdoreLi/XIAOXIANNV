package com.example.admin.woailiushuang.bean

import androidx.databinding.Bindable
import androidx.databinding.BaseObservable as BaseObservable

class User: BaseObservable {
        @set:Bindable
    var name: String
    var age: Int

    @JvmOverloads
    constructor(name: String = "", age: Int = -1) {
        this.name = name
        this.age = age
    }

    var names:String
        get() = if (name.isEmpty()) "无" else name
        set(value) {

        }



}