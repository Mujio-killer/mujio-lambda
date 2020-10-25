package com.mujio.solutions;

import java.util.ArrayList;

public class Vehicle {
	public int id;//车辆编号
	public int start_node;//车辆起始配送中心点
	public int vehicle_load;//车辆载重容量限制
	public int start_tm;//车辆开始时间窗
	public int end_tm;//车辆最迟开始时间窗
	public int max_ml;//车辆行驶里程

	public static void main(String[] args) {
		ArrayList<Integer> objects = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			objects.add(new Integer(i));
		}
		/*int l = objects.size();
		for (int i = 0; i < l; i++) {
			objects.remove(new Integer(i));
		}*/
		/*for (int i = 0; i < objects.size(); i++) {
			for (int j = 0; j < objects.size(); j++) {
				objects.remove(new Integer(objects.get(i)));
			}
		}*/

		boolean b = objects.stream().allMatch(el -> el < 10);
		System.out.println(b);
	}
}
