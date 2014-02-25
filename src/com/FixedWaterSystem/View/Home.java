/*时间：2013.2.26
 * 功能：系统的home界面，体现系统的特色，把开单的功能放在这里
 * 日志：在利用拆分布局时，发现编译去对待嵌套的拆分布局理解力非常有限，必须从大到小、从整体到局部一点一点来，否则效果莫名其妙
 *如果一个panel中嵌套拆分布局，那么这个panel设置为BorderLayout()会好一些，不设置的话出了很多莫名其妙的错误
 * 出现一些莫名其妙的错误时我总是首先想代码没问题，可能是eclipse有问题，事实是肯定代码有问题，而我应当仔细一点
 * GridLayout是无法完全满足要求的因此打算将其换位更加灵活的GridBagLayout，不过这个管理器相当的复杂！
 * */
package com.FixedWaterSystem.View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.FixedWaterSystem.Model.OrderModel;
import com.FixedWaterSystem.Tools.*;
public class Home extends JPanel implements ActionListener,MouseListener{

	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Home(username);
	}*/
	//Color arrColor[]=new Color[]{Color.blue,Color.black,Color.red,Color.yellow,Color.cyan};
	//组件
	JLabel jb1,jb2,jb3,jb4;
	JPanel jp1,jp2,jp3,jp23,jpHome,jpCard;
	JSplitPane jsp1,jsp2;
	//ImagePanel p2_bg;
	Image timebg;
	Image[] p2_Image=new Image[Warehouse.imageFile.length];
	ImagePanel[] p2_bg=new ImagePanel[Warehouse.imageFile.length];
	JLabel clientHead,clientName,timeNow;
	Timer p1_timer;
	String username="";
	CardLayout card=new CardLayout();
	int intImg=1;
	//构造函数
	public Home(String username){
		
		this.username=username;
		
		jp1=new JPanel(new BorderLayout());
		jp2=new JPanel(null);
		jp3=new JPanel(null);
		jp3.setBackground(Color.gray);
		jpHome=new JPanel(new BorderLayout());
		//创建拆分
		jp23=new JPanel(new BorderLayout());
		jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp1,jp23);
		jpHome.add(jsp1);
		jsp1.setDividerLocation((int)(50/SystemFigure.ResoFig));
		jsp1.setDividerSize(0);
		
		jsp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp2,jp3);
		jp23.add(jsp2);
		jsp2.setDividerLocation((int)(625/SystemFigure.ResoFig));
		jsp2.setDividerSize(0);
		this.initJp1();
		this.initJp2();
		//创建即时开单按钮
		jb1=new JLabel("开单",new ImageIcon("images/Billing.jpg"),0);
		jb2=new JLabel("注册",new ImageIcon("images/clientR.jpg"),0);
		jb3=new JLabel("退出",new ImageIcon("images/tuichu.jpg"),0);
		jb4=new JLabel(new 	ImageIcon("images/book.png"));
		
		jb4.setBounds((int)(1110/SystemFigure.ResoFig), (int)(570/SystemFigure.ResoFig), 36, 36);
		jb1.setBounds((int)(900/SystemFigure.ResoFig), (int)(250/SystemFigure.ResoFig), (int)(136/SystemFigure.ResoFig), (int)(36/SystemFigure.ResoFig));
		jb2.setBounds((int)(900/SystemFigure.ResoFig), (int)(300/SystemFigure.ResoFig), (int)(136/SystemFigure.ResoFig), (int)(36/SystemFigure.ResoFig));
		jb3.setBounds((int)(900/SystemFigure.ResoFig), (int)(350/SystemFigure.ResoFig), (int)(136/SystemFigure.ResoFig), (int)(36/SystemFigure.ResoFig));
		
		jb1.setFont(Word.f5);
		jb2.setFont(Word.f5);
		jb3.setFont(Word.f5);
		
		jb1.setEnabled(false);
		jb2.setEnabled(false);
		jb3.setEnabled(false);
		jb4.setEnabled(false);
		
		jb1.addMouseListener(this);
		jb2.addMouseListener(this);
		jb3.addMouseListener(this);
		jb4.addMouseListener(this);
		//设置空布局
		jp2.add(jb1);
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		this.setLayout(new BorderLayout());
		//添加组件
		this.add(jpHome);
		//this.add(jb2);
	}
	public void initJp1(){
		//jpState=new JPanel(null);								
		clientHead=new JLabel(new ImageIcon("images/p2_jl4.jpg"));
		clientName=new JLabel("欢迎你，"+username+"。");
		clientName.setFont(Word.f5);
		
		//设置手型
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
		clientHead.setCursor(myCursor);				
		
		//添加jpState的组件
		p1_timer=new Timer(1000,this);//每隔一秒触发一个ActionEvent
		p1_timer.start();//开始线程
		//获得当前时间
		timeNow=new JLabel("当前时间： "+Calendar.getInstance().getTime().toLocaleString()+"   ",new ImageIcon("images/Time.jpg"),0);//将时间本地化
		timeNow.setFont(Word.f3);
		//为显示时间的JPanel设置背景
		try {
			timebg=ImageIO.read(new File("images/timebg.jpg"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JLabel CurrrentClient=new JLabel("当前客户：");
		ImagePanel timeIP=new ImagePanel(timebg);//用timeBg做背景的一个面板
		timeIP.setLayout(new BorderLayout());
		timeIP.add(timeNow);//在面板的西部加入时间显示		
		JPanel jpCustomerState=new JPanel(new FlowLayout(FlowLayout.LEFT));
		jpCustomerState.add(clientHead);
		jpCustomerState.add(clientName);
		jp1.add(timeIP,"East");//在jpState这个面板上加一个带有时间的背景面板
		jp1.add(jpCustomerState,"West");
		//jpState.add(CurrrentClient);
		
	}
	public void initJp2(){
		//顶层的面板（Logo）
		//创建面板	
		jpCard=new JPanel(card);
		try {
			for(int i=0;i<p2_Image.length;i++){
				 p2_Image[i]=ImageIO.read(Warehouse.imageFile[i]).getScaledInstance(700, 542, 1);//得到图片
				 
				 p2_bg[i]=new ImagePanel(p2_Image[i]);
				 p2_bg[i].setLayout(null);
				jpCard.add(p2_bg[i],String.valueOf(i));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpCard.setBounds(0, 0, 700, 542);
		//创建组件	
		jp2.add(jpCard);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.timeNow.setText("当前时间： "+Calendar.getInstance().getTime().toLocaleString()+"   ");
		
	}
	


	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			BillingPane bp=new BillingPane();
		}else if(e.getSource()==jb2){
			CusRegPane crp=new CusRegPane();
		}else if(e.getSource()==jb3){
			System.exit(0);
		}else if(e.getSource()==jb4){
			intImg++;
			card.show(jpCard,String.valueOf(intImg%Warehouse.imageFile.length));
		}	
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			jb1.setEnabled(true);
		}else if(e.getSource()==jb2){
			jb2.setEnabled(true);
		}else if(e.getSource()==jb3){
			jb3.setEnabled(true);
		}else if(e.getSource()==jb4){
			jb4.setEnabled(true);
		}			
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			jb1.setEnabled(false);
		}else if(e.getSource()==jb2){
			jb2.setEnabled(false);
		}else if(e.getSource()==jb3){
			jb3.setEnabled(false);
		}else if(e.getSource()==jb4){
			jb4.setEnabled(false);
		}
	}
}