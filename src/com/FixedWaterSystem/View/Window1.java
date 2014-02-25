/**
 * 作者：孟庆申
 * 时间：1012.1.26/21:09
 * 功能：该界面将完成的功能很多
 * 整个管理系统的功能都将集中在这一界面
 * 日志：11/27:16:27ImagePanel image这样定义后面不能初始化，换成这样：ImagePanel image=null;
 *
 */
package com.FixedWaterSystem.View;
import com.FixedWaterSystem.Model.UserModel;
import com.FixedWaterSystem.Tools.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

import com.FixedWaterSystem.Tools.*;

public class Window1 extends JFrame implements ActionListener,MouseListener{
	//全乡控制静态变量
	public static int right=1;
	//public static String name="";
	//定义需要的组件
	JPanel p1,p2,p2north,p2south,p3west,p3east,p3;
	//p1:顶部logo
	//p2:左边操作选择面板
	//p3：右边结果面板
	//p4:包含p2和p3的拆分面板
	JMenuBar jmb;//菜单栏
	JMenuItem jmi[]=new JMenuItem[10];//菜单项
	JMenu jm[]=new JMenu[2];
	JLabel p2_jl1,p2_jl2,p2_jl3,p2_jl4,p2_jl5,p2_jl6,p2_list;//操作菜单
	JLabel p3east_jl1,p3east_jl2,p3east_jl3,p3east_jl4;//增删改查
	JLabel p3west_left,p3west_right;//收缩按钮
	JButton p3_jb1,p3_jb2,p3_jb3;
	JSplitPane jspane,jsp,jspforp2;
	//调用工具包中的ImagePanel类
	ImagePanel p1_bg,p2_bg,p3east_bg;
	
	Image p1_Image,p2_Image,p3_Image;
	Image titleIcon;
	//数据中心
	OrderManage om;
	ClientManager cm;
	UserManager um;
	EmpManager em;
	ProManager pm;	
	ProSellManager mm;
	
	OrderPane orderp;
	ClientPane clientp;
	UserPane userp;
	EmpPane empp;
	ProPane prop;
	ProSellPane prosellp;
	MonPane monp;
	
	DistributionManagement dm;
	//卡片
	CardLayout cardp3west,cardp3east;
	JButton p1_desktop,p1_refresh,p1_exit;
	//用来观察模式的标记
	static boolean mode=true;
	//p2south的组件
	JLabel jlMode;
	JComboBox jcbMode;
	JButton jbMode;
	static String cardName=""; 
	//初始化菜单
	public void initMenuBar(){
		jmb=new JMenuBar();//创建一个菜单栏
		//创建菜单
		jm[0]=new JMenu("系统设置");
		jm[1]=new JMenu("帮助");
		/*
		jm[2]=new JMenu("待定");
		jm[3]=new JMenu("待定");
		jm[4]=new JMenu("待定");
		jm[5]=new JMenu("待定");
		jm[6]=new JMenu("待定");
		jm[7]=new JMenu("待定");
		jm[8]=new JMenu("待定");
		jm[9]=new JMenu("待定");
		*/
	
		//创建菜单项
		jmi[0]=new JMenuItem("待定");
		jmi[1]=new JMenuItem("待定");
		jmi[2]=new JMenuItem("待定");
		jmi[3]=new JMenuItem("待定");
		jmi[4]=new JMenuItem("待定");
		jmi[5]=new JMenuItem("待定");
		jmi[6]=new JMenuItem("待定");
		jmi[7]=new JMenuItem("待定");
		jmi[8]=new JMenuItem("待定");
		jmi[9]=new JMenuItem("待定");

		//添加
		for(int i=0;i<jm.length;i++)
		{
			jmb.add(jm[i]);
			jm[i].setFont(Word.f4);
		}
		
		for(int i=0;i<jmi.length;i++)
		{
			jm[0].add(jmi[i]);
			jmi[i].setFont(Word.f4);
		}
		
		
		//将菜单栏加入界面
		this.setJMenuBar(jmb);
		
	}

	//初始化p1（logo）的函数
	public void initLogo()
	{
		//顶层的面板（Logo）
		//创建面板
		p1=new JPanel(null);
		p1.setBackground(getBackground());
		
		try {
			 p1_Image=ImageIO.read(new File("images/logos1.jpg"));//得到图片
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.p1_bg=new ImagePanel(p1_Image);//得到背景面板
		this.p1_bg.setLayout(null);
		//创建组件
		p1_desktop=new JButton(new ImageIcon("images/returntodesktop.jpg"));
		p1_refresh=new JButton(new ImageIcon("images/refresh.jpg"));
		p1_exit=new JButton(new ImageIcon("images/tuichu.jpg"));
		//设置大小
		p1_desktop.setBounds(760, 10, 40, 40);
		p1_refresh.setBounds(830,10,40,40);
		p1_exit.setBounds(900,10,40,40);
		//注册监听
		p1_desktop.addActionListener(this);
		p1_refresh.addActionListener(this);
		p1_exit.addActionListener(this);
		//添加
		/*
		p1_bg.add(p1_desktop);
		p1_bg.add(p1_refresh);
		p1_bg.add(p1_exit);
		*/		
		p1.setLayout(new BorderLayout());
		p1.add(p1_bg);
	}
	
	//初始化左边的面板（菜单选择）的函数
	public void initMenu()
	{
		//左边的面板
		//创建面板
		p2=new JPanel(new BorderLayout());//将p2设置为边界布局
		p2north=new JPanel(new GridLayout());//乘装操作菜单
		p2south=new JPanel(null);//做成空布局
		//创建组件
		try {
			p2_Image=ImageIO.read(new File("images/left_bg.jpg"));//得到图片
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.p2_bg=new ImagePanel(p2_Image);
		this.p2_bg.setLayout(new GridLayout(7,1));//设置背景面板网格布局
		
		p2_jl1=new JLabel("订单管理",new ImageIcon("images/p2_jl1.jpg"),0);
		p2_jl2=new JLabel("客户管理",new ImageIcon("images/p2_jl2.jpg"),0);
		p2_jl3=new JLabel("用户设置",new ImageIcon("images/p2_jl3.jpg"),0);
		p2_jl4=new JLabel("雇员管理",new ImageIcon("images/p2_jl4.jpg"),0);
		p2_jl5=new JLabel("商品管理",new ImageIcon("images/p2_jl5.jpg"),0);
		p2_jl6=new JLabel("出货记录",new ImageIcon("images/p2_jl6.jpg"),0);
		p2_list=new JLabel(new ImageIcon("images/systemmanage.jpg"));
		//p2_list.setBackground(Color.DARK_GRAY);
		
		//设置label不能选中
		p2_jl1.setEnabled(false);
		p2_jl2.setEnabled(false);
		p2_jl3.setEnabled(false);
		p2_jl4.setEnabled(false);
		p2_jl5.setEnabled(false);
		p2_jl6.setEnabled(false);
		//p2_list.setEnabled(false);
		
		//注册监听
		p2_jl1.addMouseListener(this);
		p2_jl2.addMouseListener(this);
		p2_jl3.addMouseListener(this);
		p2_jl4.addMouseListener(this);
		p2_jl5.addMouseListener(this);
		p2_jl6.addMouseListener(this);
		
		//对标签设置字体
		p2_jl1.setFont(Word.f1);
		p2_jl2.setFont(Word.f1);
		p2_jl3.setFont(Word.f1);
		p2_jl4.setFont(Word.f1);
		p2_jl5.setFont(Word.f1);
		p2_jl6.setFont(Word.f1);
		
		//对标签设置手型
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
		p2_jl1.setCursor(myCursor);
		p2_jl2.setCursor(myCursor);
		p2_jl1.setCursor(myCursor);
		p2_jl4.setCursor(myCursor);
		p2_jl5.setCursor(myCursor);
		p2_jl6.setCursor(myCursor);
		
		//添加
		//将标签加入图片面板中
		p2_bg.add(p2_list);
		switch(right){
		case 1://系统管理员
		case 2://经理			
			p2_bg.add(p2_jl1);
			p2_bg.add(p2_jl2);
			p2_bg.add(p2_jl3);
			p2_bg.add(p2_jl4);
			p2_bg.add(p2_jl5);
			p2_bg.add(p2_jl6);
			break;
		case 3://会计
			p2_bg.add(p2_jl1);
			p2_bg.add(p2_jl4);
			p2_bg.add(p2_jl5);
			p2_bg.add(p2_jl6);
			break;
		case 4://仓库管理员
			break;
		case 5://接线员
			break;
			default:
			
		}
		
	
		
		//p2south的组件
		jlMode=new JLabel("当前模式：");
		jlMode.setFont(Word.f3);
		jlMode.setBackground(Color.gray);
		jcbMode=new JComboBox(new String[]{"浏览","编辑"});
		jcbMode.setFont(Word.f3);
		jbMode=new JButton("应用");
		jbMode.setFont(Word.f3);
		jbMode.setBackground(Color.gray);
				
		jlMode.setBounds((int)(10/SystemFigure.ResoFig), (int)(50/SystemFigure.ResoFig), (int)(80/SystemFigure.ResoFig), (int)(25/SystemFigure.ResoFig));
		jcbMode.setBounds((int)(100/SystemFigure.ResoFig), (int)(50/SystemFigure.ResoFig), (int)(80/SystemFigure.ResoFig), (int)(25/SystemFigure.ResoFig));
		jbMode.setBounds((int)(100/SystemFigure.ResoFig), (int)(100/SystemFigure.ResoFig), (int)(80/SystemFigure.ResoFig), (int)(25/SystemFigure.ResoFig));
		
		jbMode.addActionListener(this);//注册监听
	
		p2south.add(jlMode);
		p2south.add(jcbMode);
		p2south.add(jbMode);
		
		//将图片面板加入到p2这个面板中
		p2north.add(p2_bg);
		//创建一个拆分窗
		jspforp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,p2north,p2south);
		//指定左边的面板占多少
		jspforp2.setDividerLocation((int)(300/SystemFigure.ResoFig));
		//取消边界线
		jspforp2.setDividerSize(0);
		p2.add(jspforp2,"Center");				
	}
	
	
	//初始化结果(右边)面板（p3）
	public void initResult()
	{
		//创建组件
		this.cardp3west=new CardLayout();//卡片布局，会用作切换
		this.cardp3east=new CardLayout();
		
		p3west=new JPanel(this.cardp3west);
		p3east=new JPanel(this.cardp3east);
		
		p3=new JPanel(new BorderLayout());//包含p3west、p3east
		
		//得到图片
		p3west_left=new JLabel(new ImageIcon("images/leftArray.jpg"));
		p3west_right=new JLabel(new ImageIcon("images/rightArray.jpg"));
		
		//为两个按钮注册监听
		p3west_left.addMouseListener(this);
		p3west_right.addMouseListener(this);
		
		p3west.add(p3west_left,"0");
		cardName="0";
		p3west.add(p3west_right,"1");
		
		om=new OrderManage();
		cm=new ClientManager();
		um=new UserManager();
		em=new EmpManager();
		pm=new ProManager();		
		mm=new ProSellManager();
		
		orderp=new OrderPane();
		clientp=new ClientPane();
		userp=new UserPane();
		empp=new EmpPane();
		prop=new ProPane();
		prosellp=new ProSellPane();
		monp=new MonPane();
		
		p3east.add(om,"0");
		p3east.add(cm,"1");
		p3east.add(um,"2");
		p3east.add(em,"3");
		p3east.add(pm,"4");
		p3east.add(mm,"5");
		
		p3east.add(orderp,"6");
		p3east.add(clientp,"7");
		p3east.add(userp,"8");
		p3east.add(empp,"9");
		p3east.add(prop,"10");
		p3east.add(prosellp,"11");
		
		p3.add(p3west,"West");
		p3.add(p3east,"Center");
		//做一个查分窗口存放p2和p3
		jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,p2,p3);
		//指定左边的面板占多少
		jsp.setDividerLocation((int)(180/SystemFigure.ResoFig));
		//取消边界线
		jsp.setDividerSize(0);
		
	}
	//构造函数
	public Window1(String usergrade,String username)
	{
		this.right=Integer.parseInt(usergrade);
		Word.username=username;
		//图标
		try {
			titleIcon=ImageIO.read(new File("images/p2_jl5.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//调用初始化函数初始化界面
		this.initMenuBar();
		this.initLogo();
		this.initMenu();
		this.initResult();
		
		//做一个JTabbedPane
		JTabbedPane jtp=new JTabbedPane();		
		Home home=new Home(username);
		jtp.addTab("首页",new ImageIcon("images/returntodesktop.jpg"),home);
		jtp.setFont(Word.f2);
		if(right<4){
		jtp.addTab("查询管理",new ImageIcon("images/data.png"),jsp);
		}
		
		jtp.setFont(Word.f4);
		
		//做一个拆分窗口存放p1和jtp	
		jspane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,p1,jtp);
		//指定上边的面板占多少
		jspane.setDividerLocation(65);
		//取消边界线
		jspane.setDividerSize(0);
		
		//从JFrame中获得container
		Container ct=this.getContentPane();
		//ct.add(p1,"North");
		ct.add(jspane,"Center");		
		//设置窗口
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		//关闭窗口时退出系统
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setUndecorated(true);//不使用边框
		this.setLocation((int)((w/2-500)/SystemFigure.ResoFig),0);
		this.setIconImage(titleIcon);
		this.setSize((int)((1200/SystemFigure.ResoFig)),h-50);
		this.setVisible(true);
	}
	
	public static void main(String []args){
		Window1 w1=new Window1("2","neo");
	}
	
	//响应监听
	public void actionPerformed(ActionEvent e) {
		int move=0;
		if(e.getSource()==p1_desktop){
			this.cardp3east.show(p3east,"0");
		}else if(e.getSource()==p1_exit){
			System.exit(0);
		}else if(e.getSource()==jbMode){
			int intCardName=Integer.parseInt(cardName);
			if(jcbMode.getSelectedItem().equals("浏览")&&!mode){//想从编辑模式转为浏览模式
				mode=true;
				move=-6;
			}else if(jcbMode.getSelectedItem().equals("编辑")&&mode){//想从浏览模式转为编辑模式
				mode=false;
				move=6;
			}else{
				JOptionPane.showMessageDialog(this,"已选择该模式！");
			}
			//重新编排
			cardName=String.valueOf(intCardName+=move);
			cardp3east.show(p3east,cardName);			
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==p2_jl1){
			
			if(mode){
				cardName="0";
			}else{
				cardName="6";
			}			
			this.cardp3east.show(p3east, cardName);
			this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p2_jl2){
				if(mode){
					cardName="1";
				}else{
					cardName="7";
				}			
				this.cardp3east.show(p3east, cardName);
				this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p2_jl3){
			if(mode){
				cardName="2";
			}else{
				cardName="8";
			}			
			this.cardp3east.show(p3east, cardName);
			this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p2_jl4){
			if(mode){
				cardName="3";
			}else{
				cardName="9";
			}			
			this.cardp3east.show(p3east, cardName);
			this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p2_jl5){
			if(mode){
				cardName="4";
			}else{
				cardName="10";
			}			
			this.cardp3east.show(p3east, cardName);
			this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p2_jl6){
			if(mode){
				cardName="5";
			}else{
				cardName="11";
			}			
			this.cardp3east.show(p3east, cardName);
			this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p3west_left){
			this.jsp.setDividerLocation(0);//收缩窗口
			this.cardp3west.show(p3west, "1");
		}else if(e.getSource()==p3west_right){
			this.cardp3west.show(p3west, "0");
			cardName="0";
			this.jsp.setDividerLocation((int)(180/SystemFigure.ResoFig));//收缩窗口
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
		if(e.getSource()==p2_jl1)
		{
			p2_jl1.setEnabled(true);
		}else if(e.getSource()==p2_jl2)
		{
			p2_jl2.setEnabled(true);
		}else if(e.getSource()==p2_jl3)
		{
			p2_jl3.setEnabled(true);
		}else if(e.getSource()==p2_jl4) 
		{
			p2_jl4.setEnabled(true);
		}else if(e.getSource()==p2_jl5)
		{
			p2_jl5.setEnabled(true);
		}else if(e.getSource()==p2_jl6)
		{
			p2_jl6.setEnabled(true);
		}
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==p2_jl1)
		{
			p2_jl1.setEnabled(false);
		}else if(e.getSource()==p2_jl2)
		{
			p2_jl2.setEnabled(false);
		}else if(e.getSource()==p2_jl3)
		{
			p2_jl3.setEnabled(false);
		}else if(e.getSource()==p2_jl4)
		{
			p2_jl4.setEnabled(false);
		}else if(e.getSource()==p2_jl5)
		{
			p2_jl5.setEnabled(false);
		}else if(e.getSource()==p2_jl6)
		{
			p2_jl6.setEnabled(false);
		}
	}

}
