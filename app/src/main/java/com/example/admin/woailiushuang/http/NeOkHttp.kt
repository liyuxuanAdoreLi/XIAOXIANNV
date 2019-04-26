package com.example.admin.woailiushuang.http

object NeOkHttp {


    fun <T, M> sendJsonRequest(url: String, requestDate: T, response: Class<M>, listener: IJsonDataListener<*>) {

        val httpRequest = JsonHttpRequest()

        val callbackListener = JsonCallbackListener(response, listener)

        val httpTask = HttpTask(url, requestDate, httpRequest, callbackListener)

        ThreadPoolManager.getInstance().addTask(httpTask)

    }

}
