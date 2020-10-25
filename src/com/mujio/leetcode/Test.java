package com.mujio.leetcode;

import java.util.Arrays;

/**
 * 每台机器生产1
 * D天内完成
 * 订单顺序按数组顺序
 * [1,2,3,4,5,6,7,8,9,10],5   15
 * 假设机器为n，将数组分成D份，每份和小于等于n
 * 求n值的最小值
 */

public class Test {

    public int shipWithinDays(int[] orders, int D) {
        int lo = 0, hi = Integer.MAX_VALUE;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canProduce(orders, D, mid)) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }
        return lo;
    }
    private boolean canProduce(int[] orders, int D, int K) {
        int cur = K; // cur 表示当前船的可用承载量
        for (int order: orders) {
            if (order > K) return false;
            if (cur < order) {
                cur = K;
                D--;
            }
            cur -= order;
        }
        return D > 0; // 能否在D天内干完
    }

}
