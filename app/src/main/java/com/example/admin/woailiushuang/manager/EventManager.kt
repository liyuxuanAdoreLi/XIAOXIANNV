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

    /*
    * 型变，即类型自动变化；分为 协变 ，逆变
    *
    * 协变
    * 1.out Any 类似于 ？ extends Object
    * 2.out T 说明 子类以a:T为参数的实例化引用可以付给如此父类b:T，，并且能作为返回值传出
    *    即：Obj(b)=Obj(a)
    *   in T 说明 子类以b:T为参数的实例化引用可以付给如此的子类a:T，，不能作为返回值
    *    即：Obj(a)=Obj(b)
    *
    * */

    // 定义一个支持协变的类
    class Runoob<out A>(val a: A) {
        fun foo(): A {
            return a
        }
    }

    fun main(args: Array<String>) {
        var strCo: Runoob<String> = Runoob("a")
        var anyCo: Runoob<Any> = Runoob<Any>("b")
        anyCo = strCo
        println(anyCo.foo())   // 输出 a
    }

    // 定义一个支持逆变的类
    class RunIob<in A>(a: A) {
        fun foo(a: A) {
        }
    }

    fun mainI(args: Array<String>) {
        var strDCo = RunIob("A")
        var anyDCo = RunIob<Any>("b")
        strDCo = anyDCo
    }




}