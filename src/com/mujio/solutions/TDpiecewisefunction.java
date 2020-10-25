package com.mujio.solutions;

public class TDpiecewisefunction {
	//基于现实情况近似下的时间依赖型速度时间分段函数
	
		//横坐标轴
		public double a;
		public double b;
		public double c;
		public double d;
		public double e;
		public double f;
		public double g;
		public double h;
		public double i;
		//纵坐标轴
		public double v2;
		public double v3;
		public double v4;

//		public static void main(String[] args)
//		{
//			// TODO Auto-generated method stub		
//			TDpiecewisefunction function = new TDpiecewisefunction();
//			//给坐标轴赋值
//			function.setdata(5.5, 7.5, 9.5, 11.5, 14, 17, 19, 21, 24, 2, 3, 4);
//			function.calcaulateTravelTime(2, 9);
//			
//
//		}
		//只需要一个主方法
		
		public TDpiecewisefunction()
		{
			setdata(165, 225, 285, 345, 420, 510, 570, 630, 720, 2, 4, 6);
		}
		
		
		//设置坐标轴的值
		public void setdata(double a1,double b1,double c1,double d1,double e1,double f1,double g1,double h1,double i1,double v21,double v31,double v41) 
		{
			a=a1;
			b=b1;
			c=c1;
			d=d1;
			e=e1;
			f=f1;
			g=g1;
			h=h1;
			i=i1;
			v2=v21;
			v3=v31;
			v4=v41;
		}
		
		//计算i点的行驶速度
		public double countSpeed(double ti) 
		{
			double vi=0;
			if (0<=ti&&ti<a)
			{
				vi=v4;
			}
			else if (a<=ti&&ti<b) 
			{
				vi=((v2-v4)/(b-a))*ti+v4-((a*v2-a*v4)/(b-a));
			}
			else if (b<=ti&&ti<c) 
			{
				vi=v2;
			}
			else if (c<=ti&&ti<d) 
			{
				vi=((v3-v2)/(d-c))*ti+v2-((c*v3-c*v2)/(d-c));
			}
			else if (d<=ti&&ti<e)
			{
				vi=v3;
			}
			else if (e<=ti&&ti<f)
			{
				vi=((v2-v3)/(f-e))*ti+v3-((e*v2-e*v3)/(f-e));
			}
			else if (f<=ti&&ti<g)
			{
				vi=v2;
			}
			else if (g<=ti&&ti<h)
			{
				vi=((v4-v2)/(h-g))*ti+v2-((g*v4-g*v2)/(h-g));
			}
			else if (h<=ti&&ti<i)
			{
				vi=v4;
			}
			return vi;
		}
		
		//判断当前时间间隔行驶的加速度
		public double countAcceleratedspeed(double ta)
		{
			//acceleratedSpeed行驶时间间隔内的加速度
			double acceleratedSpeed=0;
			if (0<=ta&&ta<a)
			{
				acceleratedSpeed=0;
			}
			else if (a<=ta&&ta<b) 
			{
				acceleratedSpeed=(v3-v4)/(b-a);
			}
			else if (b<=ta&&ta<c) 
			{
				acceleratedSpeed=0;
			}
			else if (c<=ta&&ta<d) 
			{
				acceleratedSpeed=(v4-v2)/(d-c);
			}
			else if (d<=ta&&ta<e)
			{
				acceleratedSpeed=0;
			}
			else if (e<=ta&&ta<f)
			{
				acceleratedSpeed=(v2-v3)/(f-e);
			}
			else if (f<=ta&&ta<g)
			{
				acceleratedSpeed=0;
			}
			else if (g<=ta&&ta<h)
			{
				acceleratedSpeed=(v4-v2)/(h-g);
			}
			else if (h<=ta&&ta<i)
			{
				acceleratedSpeed=0;
			}
			return acceleratedSpeed;
		}
		
		//判断当前时刻tcur所在时间间隔下限
		public double countTimeIntervalLowerLimit(double tcurr)
		{
			//tres当前时间间隔剩余时间
			double tres=0;
			//求得当前所在时间间隔的下限
			if (0<=tcurr&&tcurr<a)
			{
				tres=a;
			}
			else if (a<=tcurr&&tcurr<b) 
			{
				tres=b;
			}
			else if (b<=tcurr&&tcurr<c) 
			{
				tres=c;
			}
			else if (c<=tcurr&&tcurr<d) 
			{
				tres=d;
			}
			else if (d<=tcurr&&tcurr<e)
			{
				tres=e;
			}
			else if (e<=tcurr&&tcurr<f)
			{
				tres=f;
			}
			else if (f<=tcurr&&tcurr<g)
			{
				tres=g;
			}
			else if (g<=tcurr&&tcurr<h)
			{
				tres=h;
			}
			else if (h<=tcurr&&tcurr<i)
			{
				tres=i;
			}
			return tres;
		}
		
		//计算arc(i,j)对应的离开时间tj和行驶时间tij
		/*
		 * dj:arc(i,j)的距离
		 * tcur:当前从i点离开去往j点的时间点
		 */
		//车辆经过arc(i,j)的行驶时间tij
		double tij=0;
		//车辆经过arc(i,j),到达j点的时刻点
		double tj=0;
		public double calcaulateTravelTime(double dj,double tcur) 
		{
			//tres当前时间间隔下限
			//求得当前所在时间间隔的下限
			double tres=countTimeIntervalLowerLimit(tcur);
//			System.out.println("当前离开i点的时间点为tcur："+tcur);
//			System.out.println("当前时间间隔下限时间为tres："+tres);
			
			//vtcur:当前时刻的速度
			double vtcur=countSpeed(tcur);
//			System.out.println("当前时刻速度为vtcur："+vtcur);
			//vtres：当前时间间隔下限的速度
			double vtres=countSpeed(tres);
//			System.out.println("当前时间间隔下限速度为vtres："+vtres);
			//dres当前时间间隔内剩余距离
			//求得当前时间间隔内剩余距离
			double dres=((vtcur+vtres)*(tres-tcur))/2;
//			System.out.println("当前时间间隔剩余距离为dres："+dres);
			
			//计算车辆经过arc(i,j)的行驶时间tij
			//当前时间间隔的剩余距离大于arc(i,j)的距离
			if (dres>=dj) 
			{
				//a1为arc(i,j)进入的第一个时间间隔的加速度
				double a1=countAcceleratedspeed(tcur);
				//计算arc(i,j)的行驶时间tij
				if (a1==0) //加速度为0
				{
					tij=dj/vtcur;
				}
				else if (a1!=0) //加速度不为0
				{
					double v0ax=(vtcur*vtcur)+2*a1*dj;
					tij=((Math.sqrt(v0ax)-vtcur)/a1);
				}
				tj=tcur+tij;
			}
			
			//当前时间间隔的剩余距离小于arc(i,j)的距离
			if (dres<dj) 
			{
				//djres车辆经过arc(i,j)第一个时间间隔后的剩余距离
				double djres=dj-dres;
				//a2为arc(i,j)进入的第二个时间间隔的加速度
//				System.out.println("进入第二个时间间隔的时刻点tres="+tres);
				double a2=countAcceleratedspeed(tres);
				//计算arc(i,j)的行驶时间tij
				if (a2==0) //加速度为0
				{
					tij=dj/vtcur;
				}
				else if (a2!=0) //加速度不为0
				{
					double v0ax=(vtcur*vtcur)+2*a2*dj;
					tij=((Math.sqrt(v0ax)-vtcur)/a2);
				}
				tj=tcur+tij;
			}
			
//			System.out.println("车辆经过arc(i,j)的行驶时间tij="+tij);
//			System.out.println("车辆经过arc(i,j)到达j点的时刻点tj="+tj);
		
			return tij;
		}
		
	}