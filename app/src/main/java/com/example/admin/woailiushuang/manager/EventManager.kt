package com.example.admin.woailiushuang.manager

import com.example.admin.woailiushuang.consts.EventConsts
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/3/7 17:11
 */
class EventManager private constructor(){

    private var eventBus = EventBus.getDefault()

    companion object {
        val instnce:EventManager by lazy { EventManager() }
    }
    fun getEventBus(): EventBus {
        return eventBus
    }

    fun post(event: EventConsts.BaseEvent<out Any>) {
        eventBus.post(event)
    }
}