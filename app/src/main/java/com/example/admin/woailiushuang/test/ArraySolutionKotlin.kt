package com.example.admin.woailiushuang.test

import android.util.Log

import java.util.HashMap

class ArraySolutionKotlin {

    //循环输出
    fun MuiltPrint(){
        println("" + System.currentTimeMillis())
        val time1 = System.currentTimeMillis()
        var i = 0
        i = 0
        while (i < 1000) {
            println("i = $i")
            i++
        }
        val time2 = System.currentTimeMillis()
        println("时间差：" + (time2 - time1))
    }


    //两数之和
    fun test_twoSum() {
        val nums = intArrayOf(1,8,6,2,5,4,8,3,7)
        val target = 11

        this.twoSum(nums, target)
    }








    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------


    //两数之和
    fun twoSum(nums: IntArray, target: Int): IntArray {
        println("tests_Time" + System.currentTimeMillis())
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement)) {
                println("tests_Time" + System.currentTimeMillis())
                return intArrayOf(map[complement]!!, i)
            }
            map[nums[i]] = i
        }
        println("tests_Error" + System.currentTimeMillis())
        throw IllegalArgumentException("No two sum solution")
    }


    /**
     * @description 拓展：如果输出最大面积的的两个坐标怎么办
     * @param height
     * @return maxArea
     */
    //坐标，计算最大面积
    private fun maxArea(height: IntArray): Int {
        //当在数组中写指针相关算法，用变量模拟指针
        var l = 0
        var maxArea = 0
        var r = height.size - 1//记得边界值
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[r], height[l]) * (r - l))          //善于利用Math函数
            if (height[r] < height[l]) {
                r--
            } else {
                l++
            }
        }
        return maxArea
    }


}
