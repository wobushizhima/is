package com.zhima.codegen;

import com.google.common.collect.Sets;

import java.util.Random;
import java.util.Set;

/**
 * Created by superz on 2018/8/23.
 */
public class Test {
    public static void main(String[] args) {
        int value = 10000000;
        //int类型最大值：2的32次方 - 1 = Integer.MAX_VALUE = 2147483647，二十亿多,真够啦 。
        Set<Integer> result = Sets.newHashSetWithExpectedSize(value);
        Random random = new Random();
        long a = System.currentTimeMillis();
        while (result.size() < value + 1) {
            int i = random.nextInt(value + 1);
            result.add(i);
        }
        System.out.println("\r<br> 执行耗时 : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
        System.out.println("完了，集合大小为" + result.size());

    }
}
