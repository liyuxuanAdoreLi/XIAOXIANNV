package com.example.admin.woailiushuang.test;

import java.util.HashMap;
import java.util.Map;

public class ArraySolution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    //两数之和
    public int[] test_twoSum() {
        int[] nums = {16, 34, 4, 5, 99, 0, 3, 5, 9, 8};
        int target = 11;

        return this.twoSum(nums, target);
    }

    //坐标，计算最大面积
    void testArea() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = this.maxArea(height);
        System.out.println("最大面积：" + result);
    }

    // 链表逆序和
    public void testListAdd() {
        ArraySolution.ListNode l1 = new ArraySolution.ListNode(1);
        ArraySolution.ListNode l2 = new ArraySolution.ListNode(3);
        l1.next = new ArraySolution.ListNode(8);

        ArraySolution.ListNode Lnd = new ArraySolution().addTwoNumbers(l1, l2);
        System.out.println(Lnd.toString());
    }

    /*----------------------------\----------------------------\----------------------------\----------------------------\----------------------------\
    ----------------------------\----------------------------\----------------------------\----------------------------\----------------------------\
    * */


    /**
     * 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] twoSum(int[] nums, int target) {
        System.out.println("tests_Time" + System.currentTimeMillis());
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                System.out.println("tests_Time" + System.currentTimeMillis());
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        System.out.println("tests_Error" + System.currentTimeMillis());
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * @param height
     * @return maxArea
     * @description 拓展：如果输出最大面积的的两个坐标怎么办
     */
    //坐标，计算最大面积
    private int maxArea(int[] height) {
        //当在数组中写指针相关算法，用变量模拟指针
        int l = 0;
        int maxArea = 0;
        int r = height.length - 1;//记得边界值
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[r], height[l]) * (r - l));          //善于利用Math函数
            if (height[r] < height[l]) {
                r--;
            } else {
                l++;
            }
        }
        return maxArea;
    }


    /**
     * @param l1 链表 逆序数字
     * @param l2 链表
     * @return 链表之和
     */
    ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;//进位
        ListNode result = new ListNode(0);  //逆序操作想到哑节点记录初始位置
        ListNode p = l1, q = l2, curr = result;
        while (p != null || q != null) {
            int sum = 0;
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return result.next;
    }
}
