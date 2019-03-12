package com.example.admin.woailiushuang.consts

import java.util.*

/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/3/7 17:31
 */
class EventConsts {

    open class MessageEvent  constructor(data : String? = null) : BaseEvent<String>(data)


    open class BaseEvent<Data> @JvmOverloads  constructor(date: Data? = null) {
        var data:Data? =null
        init {
            this.data = date
        }
    }

    companion object {
        fun test(){
            MessageEvent().data =""
        }
    }

}