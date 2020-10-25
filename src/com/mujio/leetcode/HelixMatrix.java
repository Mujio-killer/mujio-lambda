package com.mujio.leetcode;

/**
 * @Description: 螺旋矩阵
 * 在R行C列的矩阵上，我们从(r0, c0)面朝东面开始
 * <p>
 * 这里，网格的西北角位于第一行第一列，网格的东南角位于最后一行最后一列。
 * <p>
 * 现在，我们以顺时针按螺旋状行走，访问此网格中的每个位置。
 * <p>
 * 每当我们移动到网格的边界之外时，我们会继续在网格之外行走（但稍后可能会返回到网格边界）。
 * <p>
 * 最终，我们到过网格的所有R * C个空间。
 * <p>
 * 按照访问顺序返回表示网格位置的坐标列表。
 * <p>
 * 输入：R = 1, C = 4, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1],[0,2],[0,3]]
 * @Date: 2020/10/6
 */

public class HelixMatrix {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        // 存储结果
        int[][] res = new int[R * C][2];
        // 起始位置
        res[0][0] = r0;
        res[0][1] = c0;
        // 每次步长变化n，下面的m步的横坐标或纵坐标都是一样的

        // 步数
        int step = 0;
        int idx = 1;
        // 最多R*C个元素
        while (idx < R * C) {
            // 步长加一
            step += 1;
            for (int i = 1; i <= step; i++) {
                // 下移
                c0 += 1;
                // 边界条件，
                if (c0 < C && c0 >= 0 && r0 < R && r0 >= 0) {
                    res[idx][0] = r0;
                    res[idx++][1] = c0;
                }
            }
            for (int i = 1; i <= step; ++i) {
                // 右移
                r0 += 1;
                if (c0 < C && c0 >= 0 && r0 < R && r0 >= 0) {
                    res[idx][0] = r0;
                    res[idx++][1] = c0;
                }
            }
            step += 1;
            for (int i = 1; i <= step; i++) {
                // 上移
                c0 -= 1;
                if (c0 < C && c0 >= 0 && r0 < R && r0 >= 0) {
                    res[idx][0] = r0;
                    res[idx++][1] = c0;
                }
            }
            for (int i = 1; i <= step; i++) {
                // 左移
                r0 -= 1;
                if (c0 < C && c0 >= 0 && r0 < R && r0 >= 0) {
                    res[idx][0] = r0;
                    res[idx++][1] = c0;
                }
            }
        }
        return res;
    }

}
