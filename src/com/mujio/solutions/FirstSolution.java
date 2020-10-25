package com.mujio.solutions;
import java.util.ArrayList;
import java.util.Set;

import javax.print.attribute.Size2DSyntax;

public class FirstSolution
{

	public static void main(String[] args) {
		FirstSolution firstSolution = new FirstSolution();
		firstSolution.receiveData();
//		firstSolution.test();
		firstSolution.addPointData();
//		firstSolution.countDistance();
		firstSolution.Solution();
//		firstSolution.countLoad();
//		firstSolution.countTotalDistance();
		
		
//		System.out.println(n_data.length);		
//		System.out.println(tData.c_date.length+"{}");
//		firstSolution.receiveData(tData.c_date);
//		System.out.println(firstSolution.c_data.length);
//		for(int i=0 ;i<c_data.length;i++) {
//			System.out.println(c_data[i].id);
		
		
//		}
	}
	
	 //车辆任务规划最终解
	ArrayList<ArrayList<Integer>> plan =new ArrayList<ArrayList<Integer>>();
	//用于暂时储存一辆车的解
	ArrayList<Integer> oneVehicleSolutionArrayList;
	//用于存储未执行任务点的链表，当链表为空，说明所有任务点被执行，每执行一个任务点后，将其从链表中删除
	ArrayList<Integer> point=new ArrayList<Integer>();
	//用于储存经过选择准则后排序的未执行任务点的链表，当链表为空，说明所有任务点被执行，每执行一个任务点后，将其从链表中删除
//	ArrayList<Integer> sortNode=new ArrayList<Integer>();
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
	
	TDpiecewisefunction tDpiecewisefun = new TDpiecewisefunction();

	
	
	
	//用于接收数据
//	public void receiveData(Vehicle[] inputVehicles) 
//	{
//		ReadData read = new ReadData();
//		this.c_data=read.c_date;
//		this.n_data=read.n_date;
//		this.distance=read.Distance;
////		System.out.println(inputVehicles.length+"###########");
////		c_data = new Vehicle[inputVehicles.length];
////		System.out.println(c_data.length+"!!!!!!!!");
////		for(int i=0;i<inputVehicles.length;i++){
////			c_data[i]=inputVehicles[i];
//		}
//  }
	
	/**
	 * 用于接收数据
	 */
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
	
	//总载重
	public int countLoad() {
		int count=0;
		for (int i = 0; i < taskNumber; i++) {
			
			count+=n_data[i].node_demand;
		}
		System.out.println("***"+count);
		return count;
	}
	
	
	//将客户点存入point链表中
	/**
	 * 将任务节点装入存储任务节点的point链表中
	 * 配送中心和车辆的起始点不在其中
	 */
	public void addPointData() 
	{
		for (int i = 1; i < taskNumber; i++)
		{
			point.add(i);//只是给point链表中装了未执行任务的任务点的id，就是个数
		}
	}

	
	
	//判断节点时间窗是否满足要求
	//计算离开时间
	/**
	 * @param node1
	 * @param sol
	 * @return
	 */
	public boolean ckeckTime(int node1,ArrayList<Integer> sol)//sol就是个形参
	{
		boolean b1=true;
		//i点到j点的行驶时间
		double driveTime=0;
		int node2=sol.get(sol.size()-1);
//	    System.out.println("node2="+node2+"**"+"node1="+node1);
//	    System.out.println(distance.length);
//	    System.out.println(distance[1].length);
		dist=distance[node2][node1];
//		TDpiecewisefunction tDpiecewisefun = new TDpiecewisefunction();
		driveTime=tDpiecewisefun.calcaulateTravelTime(dist, leaveTime);
		if (leaveTime+driveTime<=n_data[node1].earliest_tm)
		{	
			//依据分段函数计算两点之间的行驶时间
			int time=n_data[node1].earliest_tm;
//			System.out.println(node1+"driveTime="+driveTime);
			leaveTime=time+n_data[node1].service_duration;
//			System.out.println(node1+"leaveTime="+leaveTime);	
		}
		else if (leaveTime+driveTime>n_data[node1].earliest_tm&&leaveTime+driveTime<=n_data[node1].latest_tm) 
		{
			//依据分段函数计算两点之间的行驶时间
//			System.out.println(node1+"driveTime="+driveTime);
			leaveTime=leaveTime+n_data[node1].service_duration+driveTime;
//			System.out.println(node1+"leaveTime="+leaveTime);
		}
		else 
		{
			b1=false;
		}
//		System.out.println(b1);
		return b1;
	}
	
	
	
	/**
	 * 计算任务节点载重是否满足要求
	 * @param load
	 * @param node1 计算任务节点
	 * @param sol
	 * @return
	 */
	public boolean checkLoad(int load,int node1,ArrayList<Integer> sol) 
	{
		boolean b1=true;
		//当前任务节点需求
		int load1=n_data[node1].node_demand;
		//载重计算
		for (int i = 0; i < sol.size(); i++) 
		{
			int load3=n_data[sol.get(i)].node_demand;
			load-=load3;
//			System.out.println("load="+load);
		}
		if (load<load1) 
		{
			b1=false;
		}
		return b1;
	}
	
	
	public void test()
	{
		addPointData();
		leaveTime=0;
		taskSort(6);
		for(int i=0;i<point.size();i++)
		{
			double dista=distance[6][point.get(i)];
			double tijj=tDpiecewisefun.calcaulateTravelTime(dista, leaveTime);
			System.out.println(point.get(i)+"tij"+tijj);
		}
	}
	
	
	//对任务节点进行排序
	public void taskSort(int node1)
	{
		for (int i = 0; i < point.size()-1; i++) 
		{
			for (int j = 0; j < point.size()-1-i; j++) 
			{
//				System.out.println(point.size()-1-i);
				int node2=point.get(j);
//				System.out.println("node2="+node2+"node1="+node1);
				double dis1=distance[node1][node2];
//				TDpiecewisefunction tDpiecewisefun = new TDpiecewisefunction();
				double ti=leaveTime;
//				System.out.println("ti="+ti);	
				double tij=tDpiecewisefun.calcaulateTravelTime(dis1, leaveTime);
//				System.out.println("tij="+tij);
				double tj=ti+tij;
//				System.out.println("tj="+tj);
				double max1=n_data[node2].earliest_tm-tj;
//				System.out.println(node2+"n="+n_data[node1].earliest_tm+"@@@@tj="+tj);
//				System.out.println("max1="+max1);
				if (max1<0)
				{
					max1=0;
				}
				double j1=tij+max1;
				int node3=point.get(j+1);
//				System.out.println("node2="+node2+"node1="+node1+"node3="+node3);
				double dis2=distance[node1][node3];	
				double tij1=tDpiecewisefun.calcaulateTravelTime(dis2, leaveTime);
//				System.out.println("tij1="+tij1);
				double tj1=ti+tij1;
//				System.out.println("tj1="+tj1);
				double max2=n_data[node3].earliest_tm-tj1;
				if (max2<0)
				{
					max2=0;
				}
//				System.out.println(node3+"n="+n_data[node3].earliest_tm+"@@@@tj="+tj1);
//				System.out.println("max2="+max2);
				double j2=tij1+max2;
//				System.out.println("j1="+j1+"######j2="+j2);
				if (j1>j2)
				{

					point.set(j, node3);
					point.set(j+1, node2);
//					System.out.println("j="+point.get(j)+"j+1="+point.get(j+1));
				}
			}
		}
	}
	
	
	
	//计算总距离
	/**
	 * 所有车辆的总行驶距离
	 * @return
	 */
	public  double countTotalDistance() 
	{
		double totalDiatance=0;
		for (int i = 0; i < plan.size(); i++) 
		{
			for (int j = 0; j < plan.get(i).size(); j++)
			{
				totalDiatance=totalDiatance+ReadData.Distance[plan.get(i).get(j)][plan.get(i).get(j)-1];
			}
		}
		System.out.println("总行驶距离="+totalDiatance);
		return totalDiatance;
	}
	
	
	
	//初始解
	public void Solution()
	{
		for (int i = 1; i < c_data.length; i++)
		{
			//用于存储任务点
			ArrayList<Integer> solu = new ArrayList<Integer>();
//			capacityLimitation=c_data[i].vehicle_load;
			solu.add(c_data[i].start_node);
			boolean b=true;
			leaveTime=0;	
			while (b) 
			{
				int count=0;//用于判断是否进行任务点的插入
				int node4=solu.get(solu.size()-1);
//				System.out.println("leavetime="+leaveTime);
				taskSort(node4);
				print(point);
				for (int j = 0; j < point.size(); j++) 
				{
					int node3=point.get(j);
					int load4=c_data[i].vehicle_load;
//					System.out.println("node3="+node3);
					boolean b2=ckeckTime(node3, solu);
					boolean b3=checkLoad(load4, node3, solu);
//					System.out.println("node3="+node3+"b2="+b2+"b3="+b3);
					if (b2&&b3==true) 
					{
						
						count++;
						solu.add(node3);
						//将已完成的任务点移除
						point.remove(new Integer(node3));
						break;
					}
				}
				
				
				System.out.println();
				if (count==0)
				{
					b=false;
				}
	
			}
			System.out.println(i+"车最后离开时间="+leaveTime);
			plan.add(solu);//一辆车的结果		
		}
		print1(plan);
		print(point);
	}
	
	
	


	//打印一维链表
	public void print(ArrayList<Integer> pri) 
	{
//		System.out.println("&&&");
		for (int i = 0; i < pri.size(); i++)
		{		
			System.out.print(pri.get(i)+"-");
		}
	}
	
	//打印二维链表，为之后方便处理
	public void print1(ArrayList<ArrayList<Integer>> prin)
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
	
	public void print2(int print[][]) 
	{
		for (int i = 0; i < vehicleNumber; i++) 
		{
			for (int j = 1; j < taskNumber; j++) //任务点0点是虚拟的
			{
				if (print[i][j]!=0) 
				{
					System.out.println();
					System.out.print(i+"-"+j+"-"+print[i][j]);
				}
			}
		}
	}
	
}