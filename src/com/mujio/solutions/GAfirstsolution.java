package com.mujio.solutions;

import java.util.ArrayList;
import java.util.Arrays;


public class GAfirstsolution {

    //计算距离
    public static double dist;
    //任务节点数
    static int taskNumber = 48;//任务节点数=n_data-配送中心数，48-4+1=44
    //车辆个数
    static int vehicleNumber = 5;
    //用于接收车辆的数据
    public Vehicle c_data[];
    //用于接收节点的数据
    public Node n_data[];
    //用于接收距离信息
    public double distance[][];//先声明
    //		double distance[][];
    //用于接收点之间行驶时间信息
    public double driveTime[][];
    //用于存储车辆路径的数据
    public int vehRoute[];
    //用于存储车辆编号的数据
    public int vehNo[];
    //用于接收染色体的数据（二维数组，存入路径和车辆编号）
    public int chrom[][];
    //车辆任务规划最终解
    ArrayList<ArrayList<Integer>> plan = new ArrayList<ArrayList<Integer>>();
    //用于暂时储存一辆车的解
    ArrayList<Integer> oneVehicleSolutionArrayList;
    //		double driveTime[][];
    //用于存储未执行任务点的链表，当链表为空，说明所有任务点被执行，每执行一个任务点后，将其从链表中删除
    ArrayList<Integer> point = new ArrayList<Integer>();
    //存放染色体，种群规模为100
    ArrayList<ArrayList<Integer>> population = new ArrayList<>();
    //用于暂时存放一辆车可能访问的客户节点所在顺序排列的位置的链表
    ArrayList<Integer> func1;
    //用于暂时存放一辆车可能访问的客户节点的链表
    ArrayList<Integer> func2;
    //用于暂时存储每一辆车可能执行的任务点的二维链表
    ArrayList<ArrayList<Integer>> func;
    //车辆离开i点的时刻点
    double leaveTime = 0;
    TDpiecewisefunction tDpiecewisefun = new TDpiecewisefunction();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GAfirstsolution gAfirstsolution = new GAfirstsolution();

        //计算程序运行时间
        long startTime = System.currentTimeMillis();//获取开始时间

        gAfirstsolution.receiveData();
        gAfirstsolution.randomRoute();
        gAfirstsolution.randomVehicle();
//		gAfirstsolution.Chromosome();
//		gAfirstsolution.Solution();
//		System.out.println("**************************************");
        gAfirstsolution.feasibleSol();

        long endTime = System.currentTimeMillis();//获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");//输出程序运行时间


    }

    /**
     * 用于接收数据
     */
    public void receiveData() {
        ReadData read = new ReadData();
        this.c_data = read.c_date;
        this.n_data = read.n_date;
        this.distance = read.Distance;
    }


    //随机生成一条车辆可能访问的任务节点
    public int[] randomRoute() {
        int n1 = taskNumber;
        int result[] = new int[n1];
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 1; i < n1 + 1; i++) //注意i的范围，出现空指针报错
        {
            arr.add(i);
        }
        for (int i = 0; i < result.length; i++) {
            int num = 0;
            if (arr.size() != 0) {
                num = (int) (Math.random() * (arr.size()));
            }
            result[i] = arr.get(num);
            arr.remove(num);//移除arr链表的索引是num的元素
            //arr.remove(new Integer(result[i]);//移除的是arr链表中元素result[i]
        }
        return result;
    }


    //随机生成车辆
    public int[] randomVehicle() {
        int n1 = taskNumber;
        int n2 = vehicleNumber;//车辆数为5
        int result[] = new int[n1];
        for (int i = 0; i < result.length; i++) {
            result[i] = (int) (Math.random() * n2) + 1;
        }
        return result;
    }


    //随机生成
    public void Chromosome() {
        vehRoute = randomRoute();
//			for (int i = 0; i < vehRoute.length; i++) {
//				System.out.print(vehRoute[i]+"-");
//			}
//			System.out.println();
        vehNo = randomVehicle();
//			System.out.println(vehNo.length);
//			for (int i = 0; i < vehNo.length; i++) {
//				System.out.print(vehNo[i]+"-");
//			}
//			System.out.println();
        /**
         * 测试时将客户点设置为5，需要修改
         */
        chrom = new int[2][48];//二维数组，2个一维数组，每个数组48个元素
        for (int i = 0; i < vehRoute.length; i++) {
            chrom[0][i] = vehRoute[i];
            chrom[1][i] = vehNo[i];
//				System.out.print(chrom[0][i]+"-");
        }
        func = new ArrayList<ArrayList<Integer>>();
        for (int i = 1; i < vehicleNumber + 1; i++)     //5为车辆数
        {
            func1 = new ArrayList<Integer>();//位置
            func2 = new ArrayList<Integer>();//可能访问的任务点
            for (int k = 0; k < chrom[1].length; k++) {
                if (chrom[1][k] == i) {
                    func1.add(k);
                }
            }
            if (func1.size() != 0) {
                for (int j = 0; j < func1.size(); j++) {
                    func2.add(vehRoute[func1.get(j)]);
                }
            }
            func.add(func2);
        }
//			print1(func);
    }

    //对任务节点进行排序,冒泡排序
    public void taskSort(int node1, ArrayList<Integer> sol) {
        for (int i = 0; i < sol.size() - 1; i++) {
            for (int j = 0; j < sol.size() - 1 - i; j++) {
//					System.out.println(point.size()-1-i);
                int node2 = sol.get(j);
//					System.out.println("node2="+node2+"node1="+node1);
                double dis1 = distance[node1][node2];
//					TDpiecewisefunction tDpiecewisefun = new TDpiecewisefunction();
                double ti = leaveTime;
//					System.out.println("ti="+ti);	
                double tij = tDpiecewisefun.calcaulateTravelTime(dis1, leaveTime);
//					System.out.println("tij="+tij);
                double tj = ti + tij;
//					System.out.println("tj="+tj);
                double max1 = n_data[node2].earliest_tm - tj;
//					System.out.println(node2+"n="+n_data[node1].earliest_tm+"@@@@tj="+tj);
//					System.out.println("max1="+max1);
                if (max1 < 0) {
                    max1 = 0;
                }
                double j1 = tij + max1;
                int node3 = sol.get(j + 1);
//					System.out.println("node2="+node2+"node1="+node1+"node3="+node3);
                double dis2 = distance[node1][node3];
                double tij1 = tDpiecewisefun.calcaulateTravelTime(dis2, leaveTime);
//					System.out.println("tij1="+tij1);
                double tj1 = ti + tij1;
//					System.out.println("tj1="+tj1);
                double max2 = n_data[node3].earliest_tm - tj1;
                if (max2 < 0) {
                    max2 = 0;
                }
//					System.out.println(node3+"n="+n_data[node3].earliest_tm+"@@@@tj="+tj1);
//					System.out.println("max2="+max2);
                double j2 = tij1 + max2;
//					System.out.println("j1="+j1+"######j2="+j2);
                if (j1 > j2) {

                    sol.set(j, node3);
                    sol.set(j + 1, node2);
//						System.out.println("j="+point.get(j)+"j+1="+point.get(j+1));
                }
            }
        }
    }

    //判断节点时间窗是否满足要求
    //计算离开时间

    /**
     * @param node1
     * @param sol
     * @return
     */
    public boolean ckeckTime(int node1, ArrayList<Integer> sol)//sol就是个形参
    {
        boolean b1 = true;
        //i点到j点的行驶时间
        double driveTime = 0;
        int node2 = sol.get(sol.size() - 1);
//		    System.out.println("node2="+node2+"**"+"node1="+node1);
//		    System.out.println(distance.length);
//		    System.out.println(distance[1].length);
        dist = distance[node2][node1];
        driveTime = tDpiecewisefun.calcaulateTravelTime(dist, leaveTime);
        if (leaveTime + driveTime <= n_data[node1].earliest_tm) {
            //依据分段函数计算两点之间的行驶时间
            int time = n_data[node1].earliest_tm;
//				System.out.println(node1+"driveTime="+driveTime);
            leaveTime = time + n_data[node1].service_duration;
//				System.out.println(node1+"leaveTime="+leaveTime);	
        } else if (leaveTime + driveTime > n_data[node1].earliest_tm && leaveTime + driveTime <= n_data[node1].latest_tm) {
            //依据分段函数计算两点之间的行驶时间
//				System.out.println(node1+"driveTime="+driveTime);
            leaveTime = leaveTime + n_data[node1].service_duration + driveTime;
//				System.out.println(node1+"leaveTime="+leaveTime);
        } else {
            b1 = false;
        }
//			System.out.println(b1);
        return b1;
    }

    /**
     * 计算任务节点载重是否满足要求
     *
     * @param load
     * @param node1 计算任务节点
     * @param sol
     * @return
     */
    public boolean checkLoad(int load, int node1, ArrayList<Integer> sol) {
        boolean b1 = true;
        //当前任务节点需求
        int load1 = n_data[node1].node_demand;
        //载重计算
        for (int i = 0; i < sol.size(); i++) {
            int load3 = n_data[sol.get(i)].node_demand;
            load -= load3;
//				System.out.println("load="+load);
        }
        if (load < load1) {
            b1 = false;
        }
        return b1;
    }


    //生成一个可行解
    public void feasibleSol() {
        //生成一个解
        for (int k = 0; k < 1; k++) {
            boolean bb = true;
            while (bb) {
                int countt = 0;
                Chromosome();
                System.out.println("车辆访问的客户点");
                print1(func);
                System.out.println("计数=" + countt);
                System.out.println("bb=" + bb);
                int funclengt = func.size();
                int cdataLength = c_data.length;
                for (int i = 1; i < cdataLength; i++) {
                    //存储任务点
                    ArrayList<Integer> solu = new ArrayList<Integer>();
                    solu.add(c_data[i].start_node);
                    boolean b = true;
                    leaveTime = 0;
                    while (b) {
                        System.out.println("b=" + b);
                        int count = 0;
                        int node4 = solu.get(solu.size() - 1);
//							System.out.println("func.get(i-1).size()="+func.get(i-1).size());
                        //list用于暂时存储客户点，进行排序时使用
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        for (int w = 0; w < func.get(i - 1).size(); w++) {
                            list.add(func.get(i - 1).get(w));
                        }
                        System.out.println("初始顺序");
                        print(list);
                        taskSort(node4, list);
                        System.out.println();
                        System.out.println("排序后顺序");
                        print(list);
                        int length = list.size();
                        for (int j = 0; j < length; j++) {
                            int node3 = list.get(j);
                            int load4 = c_data[i].vehicle_load;
                            boolean b2 = ckeckTime(node3, solu);
                            boolean b3 = checkLoad(load4, node3, solu);
                            if (b2 && b3 == true) {
                                count++;
                                solu.add(node3);
                                //将已完成的任务点移除
                                func.get(i - 1).remove(new Integer(node3));
                                break;
                            }
                            //判断链表是否为空
                            if (func.get(i - 1) == null || func.get(i - 1).size() == 0) {
                                System.out.println("func.get(i-1)为空");
                                func.remove(func.get(i - 1));
                            } else {
                                print(func.get(i - 1));
                            }
                        }
                        if (count == 0) {
                            b = false;
                        }
                    }
                    plan.add(solu);//一辆车的结果
                }

                // todo: 死循环主要问题： 这里 func == null && func.size() == 0判断条件恒为 false
                if (func == null || func.size() == 0 || func.stream().allMatch(el -> el == null || el.size() == 0)) {
                    Arrays.stream(vehRoute).forEach(el -> System.out.print(el + "-"));
                    Arrays.stream(vehNo).forEach(el -> System.out.print(el + "-"));
                    plan.stream().forEach(el -> population.add(el));
                    bb = false;
                }
                countt++;
            }
        }
        print1(population);
    }


    //初始解
		/*
		public void Solution()
		{
			for (int i = 1; i < c_data.length; i++)	//c_data.length=车辆数+1（0点是自己加进去的）
			{
				//用于存储任务点
				ArrayList<Integer> solu = new ArrayList<Integer>();
				solu.add(c_data[i].start_node);
				boolean b=true;
				leaveTime=0;	
				while (b) 
				{
					int count=0;//用于判断是否进行任务点的插入
					int node4=solu.get(solu.size()-1);
					for (int j = 0; j < func.get(i-1).size(); j++) 
					{
						int node3=func.get(i-1).get(j);
//					    System.out.println("node3="+node3);
						int load4=c_data[i].vehicle_load;
//						System.out.println("node3="+node3);
						boolean b2=ckeckTime(node3, solu);
						boolean b3=checkLoad(load4, node3, solu);
//						System.out.println("node3="+node3+"b2="+b2+"b3="+b3);
						if (b2&&b3==true) 
						{
							
							count++;
							solu.add(node3);
							//将已完成的任务点移除
							func.get(i-1).remove(new Integer(node3));
							break;
						}
					}
					
					
//					System.out.println();
					if (count==0)
					{
						b=false;
					}
		
				}
//				System.out.println(i+"车最后离开时间="+leaveTime);
				plan.add(solu);//一辆车的结果		
			}
			System.out.println();
			print1(plan);
//			print(point);
		}
		*/

    //打印一维链表
    public void print(ArrayList<Integer> pri) {
//			System.out.println("&&&");
        for (int i = 0; i < pri.size(); i++) {
            System.out.print(pri.get(i) + "-");
        }
    }

    //打印二维链表，为之后方便处理
    public void print1(ArrayList<ArrayList<Integer>> prin) {
            for (int i = 0; i < prin.size(); i++) {
                for (int j = 0; j < prin.get(i).size(); j++) {
                    System.out.print(prin.get(i).get(j) + "-");
                }
                System.out.println();
            }
    }

}
