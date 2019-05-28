package com.example.admin.woailiushuang.coroutine

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.admin.woailiushuang.R

import kotlinx.coroutines.*
/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/5/28 18:31
 */
class test : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.xiecheng_test)
        Log.i("协程---", "开始 " + System.currentTimeMillis())//-----1
        main5()
        Log.i("协程---", "结束 " + System.currentTimeMillis())//-----1
    }

    fun main1() {
        GlobalScope.launch {
            // 在后台启动一个新的协程并继续
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            Log.i("协程---", "G.launch.delay(1000L) " + System.currentTimeMillis())//-----1
        }
        Log.i("协程---Start,", "协程" + System.currentTimeMillis())//主线程的代码会立即执行-----0
        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
        Log.i("协程---", "Thread.sleep(2000L)" + System.currentTimeMillis())//-----2
        runBlocking {
            // 但是这个表达式阻塞了主线程
            delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
            Log.i("协程---", "runBlocking(2000L) " + System.currentTimeMillis())//-----4
        }
    }

    // 桥接阻塞与非阻塞，里面的delay都是看起来是顺序执行，阻塞了彼此
    fun main2() = runBlocking<Unit> {
       GlobalScope.launch {
            // 在后台启动一个新的协程并继续
            delay(1000L)
            Log.i("协程---", "G.launch.delay(1000L) " + System.currentTimeMillis())//-----
        }
        Log.i("协程---Start", "协程" + System.currentTimeMillis()) // 主协程在这里会立即执行

        delayTime()
        delayTime()
    }

    suspend fun delayTime(){
//        delay(2000L)      // 延迟 2 秒来保证 JVM 存活
        Thread.sleep(2000L)
        Log.i("协程---", "delay（2000）" + System.currentTimeMillis())//-----

    }

    // join 等待作业 有job.join
    // 作业执行完毕后 才走 job。join之后的代码
    fun main3() = runBlocking {
        val job = GlobalScope.launch {
            // 启动一个新协程并保持对这个作业的引用
            delay(1000L)
            Log.i("协程---", "G.launch.delay(1000L) " + System.currentTimeMillis())//-----1
        }
        Log.i("协程---", "start " + System.currentTimeMillis())//-----0
        job.join() // 等待直到子协程执行结束
        Log.i("协程---", "job.join(2000L）join后" + System.currentTimeMillis())//-----1
    }

    //结构化并发，记住与main3做比较
    // 这里注意，lauch中耗时操作 执行后 runBlocking 才会结束 而global.lauch不属于当前block作用域
    //也就是说，外面调用什么东西的话 完全可以等到当前耗时操作获取到结果后获取，这样就不用写join了

    // runBlocking所包裹到代码中，才可以直接有lauch{}，但是这时候也就限制了只有lauch挂起执行完毕，此blocking块才能结束，才能继续走block以外的代码
    fun main4() = runBlocking {

        //        val job = GlobleScope.launch {//的话 直接就走blocking以外的代码了
        val job = launch {
            // 启动一个新协程并保持对这个作业的引用

            main4lauch()
        }
        Log.i("协程---", "Start " + System.currentTimeMillis())//-----2
    }

    suspend fun main4lauch() {
        delay(1000L)
        Log.i("协程---mai4(1000L", "函数重构" + System.currentTimeMillis())//-----3
    }


    //作用域构建器
    /* 协程作用域 执行完毕后外面线程才会执行
    *   不加协程作用域，外面runblock会先执行
    *   如果有内嵌coroutineScope，则runblock中初层代码在内嵌scope之后执行，否则直接最先就执行了
    *
    *   1。有coroutineScope，： 先（lauch或coroutinescope.lauch, -> 初层代码）
    *
    *   2。无coroutineScope ： 先 初层代码 -》 lauch（或同时）
    *
    * */
    fun main5() = runBlocking {
        // this: CoroutineScope

        launch {
            Log.i("协程---( lauch", "执行lunch" + System.currentTimeMillis())// 这一行会在内嵌 launch 之前输------0
            delay(20000L)
            Log.i("协程---( lauch", "执行完了lunch" + System.currentTimeMillis())// 这一行会在内嵌 launch 之前输------2
        }

        coroutineScope{
            // 创建一个协程作用域
            launch {
                Log.i("协程---( coroutinelauch", "执行coroutinelauch" + System.currentTimeMillis())// 这一行会在内嵌 launch 之前输------0
                delay(5000L)
                Log.i("协程---( Coroutine lauch", "coroutinelauch" + System.currentTimeMillis())//----5
            }

            delay(1000L)
            Log.i("协程---( coroutineScope", "launch前" + System.currentTimeMillis())// 这一行会在内嵌 launch 之前输出-----1
        }

        Log.i("协程---( Tasklaunch", "launch后" + System.currentTimeMillis()) // 这一行在 内嵌 launch 执行完毕后才输出----5

    }

    // 守护线程
    fun main6() = runBlocking {
        //sampleStart
        val job = launch {
            repeat(1000) { i ->
                delay(500L)
                Log.i("协程---( im slepping $i", "500l后" + System.currentTimeMillis()) //
            }
        }
        Log.i("协程---( im slepping", "延迟1300前" + System.currentTimeMillis()) //
        delay(1300L) // just quit after delay
        Log.i("协程---( im slepping", "延迟1300l" + System.currentTimeMillis()) //
        //sampleEnd

        Log.i("协程---","main: I'm tired of waiting!")
        job.cancel() // 取消该任务
        Log.i("协程---","main: I'm canceled!")
        job.join() // 等待任务执行结束
        Log.i("协程---","main: Now I can quit.")
    }


}