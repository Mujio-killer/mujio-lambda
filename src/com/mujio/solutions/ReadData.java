package com.mujio.solutions;

import java.nio.charset.Charset;
import java.util.ArrayList;
import com.csvreader.CsvReader;


public class ReadData 
{
	public static Node[] n_date; 
	public static Vehicle[] c_date;
	public static double Distance[][];
	public static double time[][];
	public static double dis;
	
	/**
	 * 一个包只可以有一个主方法
	 */
//	public static void main(String[] args) 
//	{
//		ReadData readdata = new ReadData();
//		readdata.readTask();
//		readdata.readVehicle();
//		readdata.countDistance();
//		System.out.println("n_date.length="+n_date.length);
//  }

		
		//找时间窗的最大最小值
//		int max=n_date[0].latest_tm;
//		for (int i = 0; i < n_date.length-6; i++) {
//			if (max>n_date[i].latest_tm) 
//			{
//				max=n_date[i].latest_tm;
//			}
//		}
//		System.out.println("max="+max);
	
	
	//打印
//	public void show()
//	{
//		for (int i = 0; i < n_date.length; i++) {
//			System.out.println(n_date[i].id+"%%");
//		}
//	}
	
	
	public ReadData()
	{
		readTask();
		readVehicle();
		countDistance();
		
	}
	
	
	//读取任务节点csv文件
	public void readTask()
	{
		/*********************CSV文件读入*********************/
		try 
		{
			ArrayList<String[]> csvFileList = new ArrayList<String[]>();
			String csvFilePath = "D:\\MDOVRPTW\\pr01\\N_pr01_48.csv";
            CsvReader ReaderNode = new CsvReader(csvFilePath,',', Charset.forName("UTF-8"));
            while (ReaderNode.readRecord())
            {
                csvFileList.add(ReaderNode.getValues());                     
            }
            ReaderNode.close();
            //在点的数据中，已经有零点，不用再造零点，所以储存数组的长度减一，对应66行编号也要减一
            n_date=new Node[csvFileList.size()];
            //注意在节点的数据中，没用零点，所以为了ID和下标对应，所以制造了一个零点
            Node n1 = new Node();
            n1.id=0;
            n1.x_coordinate=0;
            n1.y_coordinate=0;
            n1.service_duration=0;
            n1.node_demand=0;
            n1.earliest_tm=0;
            n1.latest_tm=0;
            n_date[0]=n1;//要加上这一步，不然0点会出现空指针
            for (int i =1; i<csvFileList.size(); i++)
            {
            	String[] strData = csvFileList.get(i);
            	Node n = new Node();
                //每一次读一列然后task类型的对象里
                n.id=Integer.parseInt(strData[0]);
                n.x_coordinate=Double.parseDouble(strData[1]);
                n.y_coordinate= Double.parseDouble(strData[2]);
                n.service_duration =Integer.parseInt(strData[3]);
                n.node_demand =Integer.parseInt(strData[4]);              
                n.earliest_tm =Integer.parseInt(strData[5]);
                n.latest_tm =Integer.parseInt(strData[6]);
                //在把对象塞到数组里，这个数组相当于二维数组  
                n_date[i]=n;
            }
        } catch (Exception e) 
        {
        	 e.printStackTrace();
        }
		//测试读取csv文件是否成功
      //System.out.println(n_date[17].service_duration);
		}
	
	//读取车辆信息的csv文件
	public  void readVehicle()
	{
		 try {
	            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
	            String csvFilePath ="D:\\MDOVRPTW\\pr01\\C_pr01_2.csv";
	            CsvReader ReaderNode = new CsvReader(csvFilePath,',', Charset.forName("UTF-8"));
	            while (ReaderNode.readRecord())
	            {
	                csvFileList.add(ReaderNode.getValues());                     
	            }
	            ReaderNode.close();
	            c_date=new Vehicle[csvFileList.size()];
	            //注意在车的数据中，没用零点，所以为了ID和下标对应，所以制造了一个零点
	            Vehicle c0 = new Vehicle();           
	            c0.id=0;
	            c0.start_node=0;	            
	            c0.vehicle_load=0;           
	            c0.start_tm=0;
	            c0.end_tm=0;
	            c0.max_ml=0;
	            c_date[0]=c0;                        
	            for (int i = 1; i<csvFileList.size(); i++)
	            {
	            	String[] s = csvFileList.get(i);
	                Vehicle c=new Vehicle();
	            	c.id= Integer.parseInt(s[0]);
	            	c.start_node= Integer.parseInt(s[1]);
	            	c.vehicle_load = Integer.parseInt(s[2]);	            	
	            	c.start_tm= Integer.parseInt(s[3]);           	
	            	c.end_tm=Integer.parseInt(s[4]);
	            	c.max_ml=Integer.parseInt(s[5]);
	                c_date[i]=c;
	            }
	        } catch (Exception e) 
		 {
	            e.printStackTrace();
	        }	
//		 System.out.println(c_date[1].id);
//	     System.out.println(c_date[2].end_tm);//pr01测试时，数据集中只有两辆车，师兄的测试集中c_date[4]就会出现异常
//		 System.out.println(c_date.length);
	}
	
	/**
	 * 计算两点间距离
	 */
	public void countDistance() 
	{
//		int num=n_date.length;
		//用于接收距离信息
		Distance=new double[n_date.length][n_date.length];
		for (int i = 0; i < n_date.length; i++) 
		{
			for (int j = 0; j < n_date.length; j++) 
			{
				double Dx=n_date[i].x_coordinate-n_date[j].x_coordinate;
				double Dy=n_date[i].y_coordinate-n_date[j].y_coordinate;
				dis=Math.sqrt((Dx*Dx)+(Dy*Dy));
				Distance[i][j]=Math.sqrt((Dx*Dx)+(Dy*Dy));
//			   System.out.print(Distance[i][j]+"&&"); 
		    }
		}
	}
	
	
}
