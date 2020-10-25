/*
package com.mujio.test;


import com.mujio.test.entity.Node;
import com.mujio.test.entity.ReadData;
import com.mujio.test.entity.Vehicle;
import com.mujio.test.util.TDpiecewisefunction;

import java.util.ArrayList;

public class TestSort {
	public static void main(String[] args) {
		TestSort testSort = new TestSort();
		testSort.receiveData();
		testSort.addPointData();
//		System.out.println("排序");
//		firstSolution.countDistance();
		testSort.countDistance();

	}
	
	 //车辆任务规划最终解
	ArrayList<ArrayList<Integer>> plan =new ArrayList<ArrayList<Integer>>();
	//用于暂时储存一辆车的解
	ArrayList<Integer> oneVehicleSolutionArrayList;
	//=new ArrayList<Integer>();
	//用于存储未执行任务点的链表，当链表为空，说明所有任务点被执行，每执行一个任务点后，将其从链表中删除
	ArrayList<Integer> point=new ArrayList<Integer>();
	//用于储存经过选择准则后排序的未执行任务点的链表，当链表为空，说明所有任务点被执行，每执行一个任务点后，将其从链表中删除
	ArrayList<Integer> sortNode=new ArrayList<Integer>();
	//用于接收车辆的数据
	public Vehicle c_data[];
	//用于接收节点的数据
	public Node n_data[];
	//用于接收距离信息
	public  double distance[][];//先声明
//	double distance[][];
	//用于接收点之间行驶时间信息
	public  double driveTime[][];
//	double driveTime[][];
	//车辆离开i点的时刻点
	double leaveTime=0;
	//任务节点数
	static int taskNumber=49;//任务节点数=n_data-配送中心数，48-4+1=44
	//车辆个数
	static int vehicleNumber=2;
	//计算距离
	public static double dist;
	
	
	*/
/**
	 * 用于接收数据
	 *//*

	public void receiveData() 
	{
		ReadData read = new ReadData();
		this.c_data=read.c_date;
		this.n_data=read.n_date;
		this.distance=read.Distance;
//		测试数据是否传入
//		System.out.println("n_data.length="+n_data.length);
//		System.out.println("c_data.length="+c_data.length);
	}
	
	public void countDistance() 
	{
//		int num=n_date.length;
		//用于接收距离信息

		
				double Dx=n_data[6].x_coordinate-n_data[5].x_coordinate;
				double Dy=n_data[6].y_coordinate-n_data[5].y_coordinate;
				double dis=Math.sqrt((Dx*Dx)+(Dy*Dy));
				
		   System.out.print(dis+"&&"); 
		    }

	
	
	//将客户点存入point链表中
	*/
/**
	 * 将任务节点装入存储任务节点的point链表中
	 * 配送中心和车辆的起始点不在其中
	 *//*

	public void addPointData() 
	{
		for (int i = 1; i < 6; i++)
		{
			point.add(i);//只是给point链表中装了未执行任务的任务点的id，就是个数
		}
	}
	
	
	//对任务节点进行排序
	public void taskSort(int node2)
	{
		for (int i = 0; i < point.size()-1; i++) 
		{
			for (int j = 0; j < point.size()-1-i; j++) 
			{
				int node1=point.get(j);
				double dis1=distance[node1][node2];
				TDpiecewisefunction tDpiecewisefun = new TDpiecewisefunction();
				double ti=leaveTime;
				double tij=tDpiecewisefun.calcaulateTravelTime(dis1, leaveTime);
			    System.out.println("tij="+tij);
				double tj=ti+tij;
				double max1=n_data[node1].earliest_tm-tj;
				if (max1<0)
				{
					max1=0;
				}
				double j1=tij+max1;
				int node3=point.get(j+1);
				double dis2=distance[node2][node3];
				double tij1=tDpiecewisefun.calcaulateTravelTime(dis2, leaveTime);
				System.out.println("tij1="+tij1);
				double tj1=ti+tij1;
				double max2=n_data[node3].earliest_tm-tj1;
				if (max2<0)
				{
					max2=0;
				}
				double j2=tij1+max2;			
				if (j1>j2)
				{
					point.set(j, node3);
					point.set(j+1, node1);	
				}
			}
		}
	}
	
	
	//初始解
	public void Solution1()
	{
//		System.out.println("44444");
		leaveTime=0;
//		boolean a1=taskSort( 0,point);
		taskSort(6);
		print(point);

//		print(sortNode);
	}
	
	
	


	//打印一维链表
	public void print(ArrayList<Integer> pri) 
	{
		for (int i = 0; i < pri.size(); i++)
		{
			System.out.print(pri.get(i)+"-");
		}
	}
	
	
}
*/
