/*
package com.mujio.test;

import com.mujio.test.entity.Vehicle;
import com.mujio.test.util.TDpiecewisefunction;

import java.util.ArrayList;


public class FSolution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FSolution firstSolution=new FSolution();
		firstSolution.setPointData();
		firstSolution.countDistance();
		firstSolution.Solution();

	}
	
	    //车辆任务规划最终解
		ArrayList<ArrayList<Integer>> plan =new ArrayList<ArrayList<Integer>>();
		//用于暂时储存一辆车的解
		ArrayList<Integer> oneVehicleSolutionArrayList=new ArrayList<Integer>();
		//用于存储未执行任务点的链表，当链表为空，说明所有任务点被执行，每执行一个任务点后，将其从链表中删除
		ArrayList<Integer> point=new ArrayList<Integer>();
		//用于接收车辆的数据
		Vehicle c_data[]=new Vehicle[2];
		//用于接收节点的数据
		Node n_data[]=new Node[7];
		//用于接收距离信息
		double distance[][]=new double[n_data.length][n_data.length];
		//车辆离开i点的时刻点
		double leaveTime=0;
		
		//设置客户点和配送中心的数据
		public void setdata()
		{
			Vehicle c1 = new Vehicle();
			c1.vehicle_load=8;
			c_data[0]=c1;
			Vehicle c2 = new Vehicle();
			c2.vehicle_load=10;
			c_data[1]=c2;
			Node o1 = new Node();
			o1.x_coordinate=0;
			o1.y_coordinate=0;
			o1.node_demand=0;
			o1.service_duration=0;
			o1.earliest_tm=0;
			o1.latest_tm=999;
			n_data[0]=o1;
			Node s1 = new Node();
			s1.x_coordinate=3;
			s1.y_coordinate=5;
			s1.node_demand=2;
			s1.service_duration=1;
			s1.earliest_tm=0;
			s1.latest_tm=2;
			n_data[1]=s1;
			Node s2 = new Node();
			s2.x_coordinate=8;
			s2.y_coordinate=10;
			s2.node_demand=3;
			s2.service_duration=1;
			s2.earliest_tm=5;
			s2.latest_tm=7;
			n_data[2]=s2;
			Node s3 = new Node();
			s3.x_coordinate=6;
			s3.y_coordinate=15;
			s3.node_demand=2;
			s3.service_duration=2;
			s3.earliest_tm=11;
			s3.latest_tm=13;
			n_data[3]=s3;
			Node s4 = new Node();
			s4.x_coordinate=12;
			s4.y_coordinate=10;
			s4.node_demand=2;
			s4.service_duration=1;
			s4.earliest_tm=0;
			s4.latest_tm=1;
			n_data[4]=s4;
			Node s5 = new Node();
			s5.x_coordinate=15;
			s5.y_coordinate=12;
			s5.node_demand=2;
			s5.service_duration=1;
			s5.earliest_tm=14;
			s5.latest_tm=17;
			n_data[5]=s5;
			Node s6 = new Node();
			s6.x_coordinate=16;
			s6.y_coordinate=7;
			s6.node_demand=2;
			s6.service_duration=1;
			s6.earliest_tm=11;
			s6.latest_tm=13;
			n_data[6]=s6;
		}
		
		//将客户点存入point链表中
		public void setPointData() 
		{
			setdata();
			for (int i = 1; i < n_data.length; i++)
			{
				point.add(i);//只是给point链表中装了未执行任务的任务点的id，就是个数
			}
		}
		
		//计算两个节点之间的距离
		public void countDistance() 
		{
			for (int i = 0; i < n_data.length; i++) 
			{
				for (int j = 0; j < n_data.length; j++) 
				{
					double Dx=n_data[i].x_coordinate-n_data[j].x_coordinate;
					double Dy=n_data[i].y_coordinate-n_data[j].y_coordinate;
				    double dis=Math.sqrt((Dx*Dx)+(Dy*Dy));
				    TDpiecewisefunction function = new TDpiecewisefunction();
				    distance[i][j]=function.calcaulateTravelTime(dis, leaveTime);			
			    }
			}
		}
		
		
		//判断节点时间窗是否满足要求
		*/
/**
		 * @param node1
		 * @param sol
		 * @return
		 *//*

		public boolean ckeckTime(int node1,ArrayList<Integer> sol)//sol就是个形参
		{
			boolean b1=true;
			int node2=sol.get(sol.size()-1);
			//System.out.println(node2+"**"+node1);
			if (leaveTime+distance[node2][node1]<=n_data[node1].earliest_tm)
			{			
				leaveTime=n_data[node1].earliest_tm+distance[node2][node1]+n_data[node1].service_duration;
			}
			else if (leaveTime+distance[node2][node1]>n_data[node1].earliest_tm&&leaveTime+distance[node2][node1]<n_data[node1].latest_tm) 
			{
				//这里没有加服务时间注意
				*/
/***********注意*************//*

				leaveTime+=distance[node2][node1];
			}
			else 
			{
				b1=false;
			}
			System.out.println(b1);
			return b1;
		}
		
		//初始解
		public void Solution()
		{
			for (int i = 0; i < c_data.length; i++)
			{
				//用于存储任务点
				ArrayList<Integer> solu = new ArrayList<Integer>();
				solu.add(0);
				boolean b=true;
				leaveTime=0;
				while (b) 
				{
					int count=0;//用于判断是否进行任务点的插入
					for (int j = 0; j < point.size(); j++) 
					{
						int node3=point.get(j);
						boolean b2=ckeckTime(node3, solu);
						if (b2==true) 
						{
							count++;
							solu.add(node3);
							//将已完成的任务点移除
							point.remove(new Integer(node3));
							break;
						}
					}
					if (count==0)
					{
						b=false;
					}
				}
				plan.add(solu);//一辆车的结果
			}
			print(plan);
		}
		
		//打印二维链表，为之后方便处理
		public void print(ArrayList<ArrayList<Integer>> prin)
		{
			for (int i = 0; i < prin.size(); i++) 
			{
				for (int j = 0; j < prin.get(i).size(); j++) 
				{
					System.out.print(prin.get(i).get(j)+"-");
				}
				System.out.println();
			}
		}
		
}
*/
