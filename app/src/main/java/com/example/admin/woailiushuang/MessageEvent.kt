package com.example.admin.woailiushuang

/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/3/7 16:48
 */
class MessageEvent(message: String) {
    private var message =message

    fun getMessage(): String = this.message

}