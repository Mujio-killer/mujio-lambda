/*
package com.mujio.test.rebuild;

import com.mujio.test.entity.Node;
import com.mujio.test.entity.ReadData;
import com.mujio.test.entity.Vehicle;
import com.mujio.test.util.TDpiecewisefunction;

import java.util.ArrayList;
import java.util.Arrays;

*/
/**
 * @Description: Solution
 * @Author: GZY
 * @Date: 2020/9/17
 *//*


public class Solution {


    public static void main(String[] args) {

        // 1. 读取数据
        ReadData read = new ReadData();
        Vehicle c_data[] = read.getC_date();
        Node n_data[] = read.getN_date();
        double distance[][] = read.getDistance();
        // 测试是否正确读取数据
        Arrays.stream(c_data).forEach(System.out::println);

        // 2. 任务计数
        int taskNumber = 49;//任务节点数=n_data-配送中心数，48-4+1=44
        //用于存储未执行任务点的链表，当链表为空，说明所有任务点被执行，每执行一个任务点后，将其从链表中删除
        ArrayList<Integer> point = new ArrayList<Integer>();
        for (int i = 0; i < taskNumber; i++) {
            point.add(i++);
        }

        // 3. 初始解
        TDpiecewisefunction tDpiecewisefun = new TDpiecewisefunction();
        Arrays.stream(c_data).forEach(cdata->{
            ArrayList<Integer> solu = new ArrayList<>();
            solu.add(cdata.start_node);

            boolean flag = true;
            int leavetime = 0;

            while (flag) {
                int count = 0;// 用于判断是否进行任务点插入
                int node4 = solu.get(solu.size() - 1);// 获取最后一个解
                taskSort(node4, point, distance,leavetime, tDpiecewisefun, n_data);

            }

        });

    }


    //对任务节点进行排序
    public void taskSort(int node1, ArrayList<Integer> point,
                         double distance[][],
                         double leaveTime,
                         TDpiecewisefunction tDpiecewisefun,
                         Node n_data[]) {

        for (int i = 0; i < point.size() - 1; i++) {
            for (int j = 0; j < point.size() - 1 - i; j++) {
                int node2 = point.get(j);
                double dis1 = distance[node1][node2];
                double ti = leaveTime;
                double tij = tDpiecewisefun.calcaulateTravelTime(dis1, leaveTime);
                double tj = ti + tij;
                double max1 = n_data[node2].earliest_tm - tj;
                if (max1 < 0) {
                    max1 = 0;
                }
                double j1 = tij + max1;
                int node3 = point.get(j + 1);
                double dis2 = distance[node1][node3];
                double tij1 = tDpiecewisefun.calcaulateTravelTime(dis2, leaveTime);
                double tj1 = ti + tij1;
                double max2 = n_data[node3].earliest_tm - tj1;
                if (max2 < 0) {
                    max2 = 0;
                }
                double j2 = tij1 + max2;
                if (j1 > j2) {
                    point.set(j, node3);
                    point.set(j + 1, node2);
                }
            }
        }
    }

}
*/
