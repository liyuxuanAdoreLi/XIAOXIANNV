package com.example.admin.woailiushuang.consts

/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/3/7 17:31
 */
class EventConsts {

        open class MessageEvent(str: String) : BaseEvent() {
            private var str = str
            fun getMessage() = str
        }


    open class BaseEvent constructor() {
//        companion object {
//             lateinit  var mydata:T
//        }


    }

}