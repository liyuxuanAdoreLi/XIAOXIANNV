package com.example.admin.woailiushuang.manager

import com.example.admin.woailiushuang.consts.EventConsts
import org.greenrobot.eventbus.EventBus

/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/3/7 17:11
 */
class EventManager private constructor(){

    private var eventBus = EventBus.getDefault()


//    fun GetInstance(): EventManager {
////        if (instance == null) {
////            synchronized(EventManager::class.java) {
////                if (instance == null) {
////                    instance = EventManager()
////                }
////            }
////        }
////        return instance
////    }

    companion object {
        val instnce:EventManager by lazy { EventManager() }
    }

    fun getEventBus(): EventBus {
        return eventBus
    }

    fun post(event: EventConsts.BaseEvent) {
        eventBus.post(event)
    }
}