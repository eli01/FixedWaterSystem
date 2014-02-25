/**(1)
 * 作者：孟庆申
 * 时间：2012.11.25/20:54
 * 功能：实现登录窗口弹出前的一个闪屏的效果
 * 工作记录：
 *20：55该闪屏类计划参考之前的满汉楼案例并对其进行改进（进度条的样式）
 *21:46创建线程有两种方式（1）继承Thread类；（2）实现Runnable接口，这里采用第二种。因为java是单一继承
 */
package com.FixedWaterSystem.View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.FixedWaterSystem.Tools.*;
public class Index extends JWindow implements Runnable{//让该类实现一个线程的接口，这个线程用来结束闪屏

	LoadPane loadpane;	//闪屏
	int width,height;//用户屏幕的分辨率

	//构造函数
	public Index()
	{
		loadpane=new LoadPane();//实例化LoadPane的同时闪屏的线程被启动了
		
		//为了让index这个JWindow的位置适应不同大小的显示器，我们需要得到用户显示器的分辨率
		width=Toolkit.getDefaultToolkit().getScreenSize().width;
		height=Toolkit.getDefaultToolkit().getScreenSize().height;
		//添加组件
		this.add(loadpane);//将loadpane这个JPanel放入Index这个JWindow中
		//设置Index
		this.setSize(1002,637);
		this.setLocation(width/2-501,height/2-318);
		this.setVisible(true);//显示
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Index index=new Index();
		Thread t=new Thread(index);
		t.start();//在主函数中启动这个终止线程的线程（听起来没什么意义，呵呵，是有点怪）
	}
	public void run() {//这是Index的线程的执行情况
		// TODO Auto-generated method stub
		//等加载完成后，跳转用户登录界面
		while(true)
		{
			try {
				Thread.sleep(4700);//11.2秒后跳转
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//new Login();//跳转
			this.dispose();//关闭Index这个JWindow
			break;//结束结束线程的线程
		}

	}

}
class LoadPane extends JPanel implements Runnable//实现加载滚动的面板
{

	//int w=Toolkit.getDefaultToolkit().getScreenSize().width;
	//int h=Toolkit.getDefaultToolkit().getScreenSize().height;
	Thread t;
	int x=10;
	int i=0,j=40,u=10;
	//String gg[]={"系","统","正","在","加","载","请","稍","侯"};
//	for(int ii=0;ii<gg.length;ii++)
//	{
//		gg[ii].setFont(Word.f2);
//	}
	int k=0,tt=0;

	
	boolean ifok=true;
	//int width=180;
	//int height=0;
	//int dian=0;
	
	//构造函数
	public LoadPane()
	{
		t=new Thread(this);
		t.start();//启动线程
	}
	
	//运行线程的函数
	public void run()
	{
		while(true)
		{
			if(x<=380) repaint();//会调用paintComponent()
			
			try
			{
				Thread.sleep(20);
				i++;//每0.07秒i增加一个像素
				j=j-6;
				u=u+10;
				//if(tt==3) width=width-20;
				
				if(i==4)
				{
					//tt++;
					//if(ifok&&x>120+k*20) k++;
					//if(k>=gg.length-1) ifok=false;
					x=x+10;
					i=0;
					j=40;
					//u=10;
					//dian++;
					//if(dian>3)dian=0;
					
				}
			}catch(InterruptedException  e){
				System.out.println("线程中断！");;
				}finally{
					
				}
			
		}
	}
	public void paintComponent(Graphics g)
	{
		//super.paintComponent(g);
		//调整参数
		int fixX=301,fixY=100;
		int thick=10;
		//背景图片
		Image image;
		image=Toolkit.getDefaultToolkit().getImage("images\\index.jpg");//获得背景图片
		g.drawImage(image,0, 0, this.getWidth(), this.getHeight(), this);//用画笔会出背景图片
		
		int r=(int)(Math.random()*255);//获得0-255之间的随机数
		int b=(int)(Math.random()*255);
		int y=(int)(Math.random()*255);
		
		g.setColor(new Color(253,250,250));//给画笔设置一种颜色
		//g.fillRect(x+fixX,210+fixY,390-x,30-thick);//用画笔画一个实心矩形
		g.fillRect(10+fixX,210+fixY,x,30-thick);//用画笔画一个实心矩形
		g.setColor(new Color(253,250,250));
		if(i>1) g.fillRect(x+fixX, 225-(j+20)/2+fixY,10,j+20-thick);
		if(j>25) g.setColor(new Color(r,b,y));
		else g.setColor(new Color(123,194,252));
		g.fillRect(x+fixX, 225-j/2+fixY,10,j-thick);
		//g.setColor(new Color(123,194,252));
		
		g.setColor(new Color(255,255,255));
		g.drawRect(10+fixX, 210+fixY, 380, 30-thick);//空心矩形
		//g.drawRect(500, 900, 380, 30);//空心矩形
		
	}
	
}
