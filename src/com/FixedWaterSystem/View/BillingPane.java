package com.FixedWaterSystem.View;
import com.FixedWaterSystem.Tools.*;
import com.FixedWaterSystem.DateBase.SqlHelper;
import com.FixedWaterSystem.Model.*;

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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.FixedWaterSystem.Model.OrderModel;
import com.FixedWaterSystem.Tools.ImagePanel;
import com.FixedWaterSystem.Tools.Word;
import com.FixedWaterSystem.View.OrderManage.AddView;

//------做一个内部类--头--------------
public class BillingPane extends JDialog implements ActionListener,MouseListener{
	public static void main(String[] args){
		new BillingPane();
	}
	//定义内部类的组件，共24个
	JPanel jpLogo,jpState,jpBilling;		
	
	//logo部分
	JButton jpLogo_desktop,jpLogo_exit;
	
	//state部分
	
	
	//billing部分
	Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
	JSplitPane jsp1,jsp2,jsp3,jsp4,jsp5;
	JPanel jpBillingLeft,jpBillingRight,jpContent,jp1,jp1West,jp1East,jp1WestWest,jp1WestCenter,jp1EastWest,jp1EastCenter,jp2,jp3,jp23;
	JLabel add,edit,delete,orderby,detail,search,cancel,save;
	JScrollPane jscrollpane;
	OrderModel order;
	JComboBox jcbForOrderby,jcbForOrderby2;
	JTable jtable;
	JScrollPane jsp;
	ProductModel pm;
	String sqlJP3="select PID 商品编号,Pname 商品名称,specification 规格型号,unitPrice 单价,quantity 数量,biscount 折扣,monerySum 金额,Remarks 备注 from ProductShipping where OID=(select top 1 OID from orders where CID= ? order by OID desc)";
	//String sqlCall="select PID 商品编号,Pname 商品名称,specification 规格型号,unitPrice 单价,quantity 数量,biscount 折扣,monerySum 金额,Remarks 备注 from ProductShipping where OID=(select top 1 OID from orders where CallNumber= ? order by OID desc)";
	//String sqlCname="select PID 商品编号,Pname 商品名称,specification 规格型号,unitPrice 单价,quantity 数量,biscount 折扣,monerySum 金额,Remarks 备注 from ProductShipping where OID=(select top 1 OID from orders where CID=(select CID from client where CName=?) ? order by OID desc)";
	String sqlCall="select top 1 CID from client where Phone=?";
	String sqlCname="select top 1 CID from client where CName=?";
	String[] orderStr=new String[10];
	String newOID;
	String cid;
	String[] oids=new String[1];
	String sqlOrder="insert into orders values(?,?,?,?,?,?,?,?,?,?)";//添加新订单
	BillingModel bm=new BillingModel();
	String sqlOID="select top 1 OID from orders order by OID desc";
	JLabel clientHead,clientName;
		
	//初始化录入界面右边部分
	JPanel jpRight1,jpRight2,jpRight3,jpRight23,jpRLogo,jpSearch;		
	JTabbedPane jtpRight;
	JSplitPane jspRight1,jspRight2,jspRight3;
	JTable jtOrder,jtClient;
	JLabel jlMob,jlNam,jlRLogo;
	JButton jbNam,jbMob;
	JTextField jtfMob,jtfNam;
	ProductModel orderModel;
	ProductModel clientModel;
	
	int rowNum;
	String sqlROrder="select OID 业务单号,CID 客户卡号,SendTime 送货时间,OmonerySum 金额,Warehouse 仓库 from orders order by SendTime desc";
	String sqlRClient="select CID 客户卡号,CName 客户姓名,ContactPerson 联系人,Adress 地址,Phone 手机号码 from client order by CID asc";
	//下拉按钮（以前从来没用过）
	//JComboBox jcbForOrderby,jcbForOrderby2;
	String jcbArray[]={"按时间","按单价","按订单额"};
	String jcbArray2[]={"从小到大","从大到小"};
	
	String jcb1Str,jcb2Str;
	 
	Timer p1_timer;
	JLabel timeNow;
	Image timebg,p1_yes;
	 
	JTextField searchJTF;
	JLabel searchJL,refresh2;
	JButton searchJB;
	
	JLabel jlLogo=null;
	String[] jlabelString={"业务单号","客户卡号","联系地址","出生日期","客户级别","关联片区","客户备注","来电状态","接线员","送货车辆","送货人","送货方式","客户名称","联系电话","联系人","客户加入时间","片区名称","客户类型","来电号码","来电时间","送货时间","出货仓库","区域类型","备注"};
	JLabel[] jlabel=new JLabel[24];
	JTextField jtf[]=new JTextField[24];
	public BillingPane(){		
		//调用各个部分的初始化函数
		this.initLogo();
		this.initState();
		this.initBilling();
		this.initSearch();
		
		//----以下拆分的过程很有规律，正是日志中那种思想的体现，不断的错误证明（折腾了一晚上了！）只有这样编译器才能理解----------
		
		//LOGO和其它
		jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpLogo,jpContent);
		jsp1.setDividerLocation((int)(54/SystemFigure.ResoFig));//指定logo的宽度																	
		jsp1.setDividerSize(0);			
		
		//state和其它（除logo）
		jsp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpState,jpBilling);
		jpContent.add(jsp2);
		jsp2.setDividerSize(0);
		jsp2.setDividerLocation((int)(40/SystemFigure.ResoFig));//指定面板state的高
		
		//jpBillingLeft和其它（除logo、state）
		jsp3=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jpBillingLeft,jpBillingRight);
		jpBilling.add(jsp3);			
		//jsp3.setLastDividerLocation(700);
		jsp3.setDividerSize(0);
		jsp3.setDividerLocation((int)(800/SystemFigure.ResoFig));//指定左边的
		
		//jp1和其它（除logo、state、jpBillingLeft）
		jsp4=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp1,jp23);
		jpBillingLeft.add(jsp4);
		jsp4.setDividerLocation((int)(500/SystemFigure.ResoFig));
		jsp4.setDividerSize(0);
		
		//jp2和其它（除logo、state、jpBillingLeftjp1即jp3）
		jsp5=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp2,jp3);
		jp23.add(jsp5);
		jsp5.setDividerLocation((int)(50/SystemFigure.ResoFig));
		jsp5.setDividerSize(0);
		
		//为了对空间合理利用决定将jp1改造成
		
		//---------------------拆分末---------------------------
		
		//添加
		this.add(jsp1);
		//设置窗口
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		//关闭窗口时退出系统
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//this.setUndecorated(true);//不使用边框
		this.setLocation(w/2-501,h/2-350);
		this.setTitle("订单登记界面");
		this.setSize((int)(1400/SystemFigure.ResoFig),(int)(850/SystemFigure.ResoFig));
		//设置显示
		this.setVisible(true);
	}
	
	//初始化登记界面的logo_panel
	public void initLogo(){
		Image jpLogo_Image=null;
		ImagePanel jpLogo_bg;

		//顶层的面板（Logo）

		jpLogo=new JPanel(null);
		jpLogo.setBackground(getBackground());
		
		try {
			jpLogo_Image=ImageIO.read(new File("images/BillingLogo.jpg"));//得到图片
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpLogo_bg=new ImagePanel(jpLogo_Image);//得到背景面板
		jpLogo_bg.setLayout(null);
		//创建组件
		jpLogo_desktop=new JButton(new ImageIcon("images/returntodesktop.jpg"));

		jpLogo_exit=new JButton(new ImageIcon("images/tuichu.jpg"));
		//设置大小
		jpLogo_desktop.setBounds((int)(760/SystemFigure.ResoFig),(int)(5/SystemFigure.ResoFig), (int)(40/SystemFigure.ResoFig), (int)(40/SystemFigure.ResoFig));

		jpLogo_exit.setBounds((int)(900/SystemFigure.ResoFig),(int)(5/SystemFigure.ResoFig),(int)(40/SystemFigure.ResoFig),(int)(40/SystemFigure.ResoFig));
		//注册监听
		jpLogo_desktop.addActionListener(this);

		jpLogo_exit.addActionListener(this);
		//添加
		/*
		jpLogo_bg.add(jpLogo_desktop);

		jpLogo_bg.add(jpLogo_exit);		
		*/						
		jpLogo.setLayout(new BorderLayout());
		jpLogo.add(jpLogo_bg);					
	}
	//初始化当前客户信息（或时间状态信息）
	public void initState(){
		//jpState=new JPanel(null);
		jpState=new JPanel(new BorderLayout());
		
		searchJTF=new JTextField(10);		
		searchJL=new JLabel(new ImageIcon("images/search.jpg"));
		searchJTF.setBorder(BorderFactory.createLoweredBevelBorder());
		clientHead=new JLabel(new ImageIcon("images/head.jpg"));
		clientName=new JLabel("欢迎你，"+Word.username+"。");
		//设置手型
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
		searchJL.setCursor(myCursor);
		clientHead.setCursor(myCursor);
		
		//设置监听
		searchJL.addMouseListener(this);
		clientHead.addMouseListener(this);
		
		
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
		jpCustomerState.add(searchJL);
		jpCustomerState.add(searchJTF);
		jpCustomerState.add(clientHead);
		jpCustomerState.add(clientName);
		jpState.add(timeIP,"East");//在jpState这个面板上加一个带有时间的背景面板
		jpState.add(jpCustomerState,"West");
		//jpState.add(CurrrentClient);
		
	}
	public void initSearch(){
		//左边查阅部分
		//创建组件
		jpRLogo=new JPanel(null);
		jpRLogo.setBackground(Color.gray);
		jpSearch=new JPanel(null);
		jtpRight=new JTabbedPane();
		jpRight1=new JPanel(new BorderLayout());
		jpRight2=new JPanel(new BorderLayout());
		jpRight3=new JPanel(new BorderLayout());
		jpRight23=new JPanel(new BorderLayout());
						
		orderModel=new ProductModel();
		clientModel=new ProductModel();
		
		orderModel.query(sqlROrder);
		clientModel.query(sqlRClient);
		
		jtOrder=new JTable(orderModel);
		jtClient=new JTable(clientModel);
		
		jlMob=new JLabel("手机号",new ImageIcon("images/search.jpg"),0);
		jlNam=new JLabel("客户名",new ImageIcon("images/search.jpg"),0);
		jlRLogo=new JLabel("信息查询栏");
		jlRLogo.setBackground(Color.white);
		
		jtfMob=new JTextField(20);
		jtfNam=new JTextField(20);
		
		jtfMob.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfNam.setBorder(BorderFactory.createLoweredBevelBorder());
		//设置搜索组件的位置和大小
		jlRLogo.setBounds((int)(60/SystemFigure.ResoFig), (int)(5/SystemFigure.ResoFig), (int)(150/SystemFigure.ResoFig), (int)(30/SystemFigure.ResoFig));
		jtfMob.setBounds((int)(30/SystemFigure.ResoFig), (int)(60/SystemFigure.ResoFig), (int)(120/SystemFigure.ResoFig),(int)(20/SystemFigure.ResoFig));
		jtfNam.setBounds((int)(30/SystemFigure.ResoFig), (int)(30/SystemFigure.ResoFig), (int)(120/SystemFigure.ResoFig),(int)(20/SystemFigure.ResoFig));
		jlMob.setBounds((int)(160/SystemFigure.ResoFig), (int)(60/SystemFigure.ResoFig), (int)(100/SystemFigure.ResoFig), (int)(20/SystemFigure.ResoFig));
		jlNam.setBounds((int)(160/SystemFigure.ResoFig), (int)(30/SystemFigure.ResoFig), (int)(100/SystemFigure.ResoFig), (int)(20/SystemFigure.ResoFig));
		
		
		jlMob.setEnabled(false);
		jlNam.setEnabled(false);
		
		//设置字体
		jlRLogo.setFont(Word.f5);
		jtfNam.setFont(Word.f3);
		jtfMob.setFont(Word.f3);
		jlNam.setFont(Word.f3);
		jlMob.setFont(Word.f3);
		//设置手型
		
		jlNam.setCursor(myCursor);
		jlMob.setCursor(myCursor);
		//注册监听
		
		jlNam.addMouseListener(this);
		jlMob.addMouseListener(this);
		//添加组件
		jtpRight.add(jtOrder,"订单统计表");
		jtpRight.add(jtClient,"客户统计表");
		
		
		jpSearch.add(jtfNam);
		jpSearch.add(jlNam);
		jpSearch.add(jtfMob);
		jpSearch.add(jlMob);
		
		jpRLogo.add(jlRLogo);
		
		jpRight2.add(jtpRight);
		
		jspRight1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpRight1,jpRight23);
		jpBillingRight.add(jspRight1);
		jspRight1.setDividerLocation(150);
		jspRight1.setDividerSize(0);
		
		jspRight2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpRight2,jpRight3);
		jpRight23.add(jspRight2);
		jspRight2.setDividerLocation(300);
		jspRight2.setDividerSize(0);
		
		jspRight3=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpRLogo,jpSearch);
		jpRight1.add(jspRight3);
		jspRight3.setDividerLocation(70);
		jspRight3.setDividerSize(0);
	}

	public void initBilling(){

		jpContent=new JPanel(new BorderLayout());
		jpBilling=new JPanel(new BorderLayout());
		//该panel中又嵌套两个panel，不打算用边界布局（东、西），拆分布局更加灵活一些
		jpBillingLeft=new JPanel(new BorderLayout());
		this.initJp1();			
		this.initJp2();
		this.initJp3();
		jp23=new JPanel(new BorderLayout());			
		jpBillingRight=new JPanel(new BorderLayout());
		jpBillingRight.setBackground(Color.gray);				
		
	}
	
	public void initJp1(){
		//初始化标签和文本区
		for(int i=0;i<jlabelString.length;i++){
			jlabel[i]=new JLabel(jlabelString[i]);
			jlabel[i].setFont(Word.f4);
			jtf[i]=new JTextField(20);
			jtf[i].setBorder(BorderFactory.createLoweredBevelBorder());

		}
		
		//初始化某些文本区的内容	
		//到数据库中取出
		BillingModel bm=new BillingModel();
		newOID=DataFactory.getID(bm.query(sqlOID),"O",true);
		jtf[0].setText(newOID);//精确时间、O打头
		
		//jp1=new JPanel(new GridLayout(12,4,10,5));//将jp1设置成为网格布局，用来登记订单信息	
		jp1=new JPanel(new BorderLayout(5,5));
		jp1West=new JPanel(new BorderLayout(10,10));
		jp1WestWest=new JPanel(new GridLayout(12,1,10,10));
		jp1WestCenter=new JPanel(new GridLayout(12,1,10,10));
		jp1East=new JPanel(new BorderLayout(5,5));
		jp1EastCenter=new JPanel(new GridLayout(12,1,10,10));
		jp1EastWest=new JPanel(new GridLayout(12,1,10,10));
		
		//jp1.setVisible(true);
		for(int i=0;i<12;i++){//添加标签和录入文本框
			jp1WestWest.add(jlabel[i]);
			jp1WestCenter.add(jtf[i]);
			jp1EastWest.add(jlabel[i+12]);
			jp1EastCenter.add(jtf[i+12]);
		}
		//对jp1各个panel组件进行组合
		jp1West.add(jp1WestWest,"West");
		jp1West.add(jp1WestCenter,"Center");
		jp1East.add(jp1EastWest,"West");
		jp1East.add(jp1EastCenter,"Center");
		
		jp1.add(jp1West,"West");
		jp1.add(jp1East,"East");
	}
	//对jp2进行初始化
	public void initJp2(){
		jp2=new JPanel(new FlowLayout(FlowLayout.LEFT));			
		
		add=new JLabel("新增",new ImageIcon("images/add.jpg"),0);
		cancel=new JLabel("取消",new ImageIcon("images/cancel.jpg"),0);
		save=new JLabel("保存",new ImageIcon("images/save.jpg"),0);
		edit=new JLabel("编辑",new ImageIcon("images/edit.jpg"),0);
		delete=new JLabel("删除",new ImageIcon("images/delete.jpg"),0);
		jcbForOrderby=new JComboBox(jcbArray);//创建一个下拉列表
		jcbForOrderby2=new JComboBox(jcbArray2);//创建一个下拉列表

		//设置标签的样式
		add.setBorder(BorderFactory.createRaisedBevelBorder());
		save.setBorder(BorderFactory.createRaisedBevelBorder());
		edit.setBorder(BorderFactory.createRaisedBevelBorder());
		cancel.setBorder(BorderFactory.createRaisedBevelBorder());
		delete.setBorder(BorderFactory.createRaisedBevelBorder());
		jcbForOrderby2.setBorder(BorderFactory.createRaisedBevelBorder());
		//设置字体
		add.setFont(Word.f3);
		save.setFont(Word.f3);
		edit.setFont(Word.f3);
		cancel.setFont(Word.f3);
		delete.setFont(Word.f3);
		//jcbForOrderby.setFont(Word.f3);
		jcbForOrderby2.setFont(Word.f3);
		
		//设置手型
		
		add.setCursor(myCursor);
		save.setCursor(myCursor);
		edit.setCursor(myCursor);
		cancel.setCursor(myCursor);
		delete.setCursor(myCursor);
		searchJL.setCursor(myCursor);
		//refresh2.setCursor(myCursor);
		
		//设置高亮状态
		add.setEnabled(false);
		save.setEnabled(false);
		edit.setEnabled(false);
		cancel.setEnabled(false);
		delete.setEnabled(false);
		searchJL.setEnabled(false);
		
		//设置监听
		add.addMouseListener(this);
		save.addMouseListener(this);
		edit.addMouseListener(this);
		cancel.addMouseListener(this);
		delete.addMouseListener(this);
		searchJL.addMouseListener(this);
		//refresh2.addMouseListener(this);
		
		//添加p3的组件
		//jp2.add(refresh2);
		jp2.add(add);
		jp2.add(delete);
		jp2.add(cancel);
		jp2.add(save);		
		jp2.add(edit);
		
		//jp2.add(orderby);
		jp2.add(jcbForOrderby);
		jp2.add(jcbForOrderby2);
	}
	public void initJp3(){
		jp3=new JPanel(new BorderLayout());
		jp3.setBackground(Color.gray);
		pm=new ProductModel();
		
		String[] paras={"C201212210000"};		
		pm.query(sqlJP3,paras);
		jtable=new JTable(pm);
		jsp=new JScrollPane(jtable);
		jp3.add(jsp);			
	}
	public void refresh(String sql){
		//重新获得新的数据模型
		ProductModel pm=new ProductModel();
		pm.query(sql);
		jtable.setModel(pm);//更新ordeTable
	}
	public void refresh(String sql,String paras[]){
		//重新获得新的数据模型
		pm=new ProductModel();
		pm.query(sql,paras);
		jtable.setModel(pm);//更新ordeTable
	}
	
	//刷新客户信息区的函数
	public void refreshClient(String sql,String[] paras){
		SqlHelper sh=new SqlHelper();
		int colum=0;
		try{						
			ResultSet rs=sh.query(sql, paras);
			colum=rs.getMetaData().getColumnCount();
			String[] cInfo=new String[colum];
			if(rs.next()){
				for(int i=0;i<cInfo.length;i++){
					cInfo[i]=rs.getString(i+1);
				}				
			}
			//用得到的数组刷新客户信息
			jtf[1].setText(cInfo[0]);//客户卡号
			jtf[12].setText(cInfo[1]);//名称
			jtf[3].setText(cInfo[6]);//生日
			jtf[4].setText(cInfo[8]);//级别
			jtf[5].setText(cInfo[10]);//片区
			jtf[9].setText(cInfo[13]);//送货车辆
			jtf[11].setText(cInfo[13]);//送货方式
			jtf[14].setText(cInfo[4]);//联系人
			jtf[15].setText(cInfo[7]);//加入时间
			jtf[2].setText(cInfo[3]);//地址
			jtf[17].setText(cInfo[9]);//客户类型
			jtf[22].setText(cInfo[11]);//地区类型
			jtf[13].setText(cInfo[2]);//手机号码
			jtf[23].setText(cInfo[21]);//备注
			
			//来电状态(简单化处理，后面改成下拉菜单)
			jtf[7].setText("0");//默认未接听
			
			//送水工编号（暂时简单处理，后面可以改成下拉菜单）
			jtf[10].setText("H201331");//默认
			//刷新来电时间
			jtf[19].setText(Calendar.getInstance().getTime().toLocaleString());
			//刷新送货时间（送货时间应当由接线员手工完成，这里先做这样的简化处理）
			jtf[20].setText(Calendar.getInstance().getTime().toLocaleString());
			jtf[21].setText("仓库一");
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sh.sqlClose();
		}
	
	}
	//用该客户最近一次的订购创建本次订购情况
	public void createPBilling(){
		//1.读取当前的订单c出货物信息(订单的金额需要计算)
		
		orderStr[0]=jtf[0].getText().trim();//1业务单号
		orderStr[1]=jtf[1].getText().trim();//2客户卡号
		orderStr[2]=jtf[10].getText().trim();//3送水工编号
		//orderStr[3]=jtf[19].getText().trim();//4来电时间
		orderStr[3]=jtf[19].getText().trim();//4来电时间
		orderStr[4]=jtf[7].getText().trim();//5来电状态
		orderStr[5]=jtf[18].getText().trim();//6来电号码
		orderStr[6]=jtf[20].getText().trim();//7送货时间
		orderStr[7]=jtf[21].getText().trim();//8出货仓库
		orderStr[8]="0";//9金额(默认为0)
		orderStr[9]=jtf[23].getText().trim();//10订单备注
		//添加订单		
		
		bm.update(sqlOrder, orderStr);//完成添加一份初始订单的任务
		//获得原来的最后一个SID
		String sqlSID="select top 1 SID from ProductShipping order by SID desc";
		//得到新的SID	
		String[] sidStr=new String[3];
		sidStr[0]=DataFactory.getID(bm.query(sqlSID),"S",true);//精确时间、S打头	
		sidStr[1]=DataFactory.getID(sidStr[0],"S",true);
		sidStr[2]=DataFactory.getID(sidStr[1],"S",true);
		//System.out.println(sidStr[0]+" "+sidStr[1]+" "+sidStr[2]);
		
		//添加新的出货数据（初始数据）
		String sqlPro="insert into ProductShipping values(?,?,?,?,?,?,?,?,?,?)";
		Vector<Vector> v=BillingPane.this.pm.rows;
		String[][] setSqlPro=new String[3][10];
		//设置发货号和订单号
		
		for(int i=0;i<3;i++){
			setSqlPro[i][0]=sidStr[i];
			setSqlPro[i][1]=jtf[0].getText().trim();
		}
							
		//设置发货号和订单号以外的部分
		//System.out.println(pm.getRowCount()+" "+pm.getColumnCount());
		for(int i=0;i<BillingPane.this.pm.getRowCount();i++){
			for(int j=0;j<BillingPane.this.pm.getColumnCount();j++){
				setSqlPro[i][j+2]=(String) pm.getValueAt(i, j);
			}
		}
		//到数据库中完成
		for(int i=0;i<setSqlPro.length;i++){
			pm.update(sqlPro, setSqlPro[i]);						
		}
		
	}
	////创建新的订单和出货单到数据库
	public void createOrder(){
		//创建新的订单和出货单到数据库
		this.createPBilling();
		//1.获得searchJTF中的文本（客户ID）
		cid=searchJTF.getText().trim();
		//System.out.println(cid);
		String paras[]={cid};
		//2.调用函数用数据库中新的出货单刷新jtable
		this.refresh(sqlJP3,paras);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.timeNow.setText("当前时间： "+Calendar.getInstance().getTime().toLocaleString()+"   ");
		if(e.getSource()==jpLogo_desktop){
			this.dispose();
		}else if(e.getSource()==jpLogo_exit){
			System.exit(0);
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//如果用户点击了新增按钮
		if(e.getSource()==add){	
			if(searchJTF.getText().equals("")){
				JOptionPane.showMessageDialog(this,"请先在查找框内输入客户卡号并查询！");
				return;
			}else{
				Object[] possibleValues={"新增","取消"};
				Object selectValue=JOptionPane.showInputDialog(null, "确定按客户最近一次消费开单吗？", "新增订单对话框", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
				//2.读取货物的信息
				if(selectValue.equals(possibleValues[0])){
					this.createOrder();
					JOptionPane.showMessageDialog(this,"完成！");
				}else if(selectValue.equals(possibleValues[1])){
					return;
				}
				
			}			 			 
		}else if(e.getSource()==cancel){
			
			//还原该用户的默认数据，修改数据需要先拿到原来的数据，和将当前订单的信息记录全部删除再按默认凡事重新创建一样麻烦，暂时决定用后一种方法
			//删除新的出货单、再删除新的订单		

			oids[0]=newOID;
			String sqlDelProBill="delete  from ProductShipping where OID=?";
			String sqlDelOrdBill="delete from orders where OID=?";
			bm.update(sqlDelProBill,oids);
			bm.update(sqlDelOrdBill,oids);	
			
			//默认方式创建新的订单和出货单（先创建出货单再创建订单）                     
			newOID=DataFactory.getID(bm.query(sqlOID),"O",true);
			jtf[0].setText(newOID);//精确时间、O打头			
			orderStr[0]=newOID;//1业务单号
			//删除记录后重建一个出货单的数据模型
			pm=new ProductModel();
			String paras[]={cid};
			pm.query(sqlJP3, paras);
			
			//用新的模型创建订单和出货单
			this.createPBilling();

			//刷新货物显示界面（此时不需按新增数据，已经是由上一次消费信息得到的新数据了，按add的话会因为订单号重复报错）
			this.refresh(sqlJP3,paras);//又刷新了一次

			
		}else if(e.getSource()==save){
			Object[] possibleValues={"保存","取消"};
			Object selectValue=JOptionPane.showInputDialog(null, "确定保存这份订单吗？", "保存订单对话框", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
			//读取货物的信息
			if(selectValue.equals(possibleValues[0])){
				JOptionPane.showMessageDialog(this,"订单已保存！");
			}else{
				return;
			}
			//连接数据库完成任务
			//String
		}else if(e.getSource()==delete){//排序
			Object[] possibleValues={"确定","取消"};
			Object selectValue=JOptionPane.showInputDialog(null, "确定删除这份订单吗？", "删除订单对话框", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
			//读取货物的信息
			if(selectValue.equals(possibleValues[0])){
				//将已创建（保存或没保存，只要按了add）的订单数据删除
				String delSell="delete from ProductShipping where OID=(select top 1 OID from orders where CID= 'C201212210000' order by OID desc)";
				String delOrder="delete from orders where OID=(select top 1 OID from orders where CID= 'C201212210000' order by OID desc);";
				bm.update(delSell);
				bm.update(delOrder);
				String[] paras={cid}; 
				this.refresh(sqlJP3,paras);
				JOptionPane.showMessageDialog(this,"订单已删除！");
			}else{
				return;
			}
			
		}else if(e.getSource()==edit){
			ProductEditor pe=new ProductEditor(true);
	
		}else if(e.getSource()==searchJL){
			//刷新客户上次订单的出货数据
			
			//1.获得searchJTF中的文本（客户ID）
			cid=searchJTF.getText().trim();
			//System.out.println(cid);
			String paras[]={cid};
			//2.调用函数刷新jtable
			this.refresh(sqlJP3,paras);
			
			//刷新当前客户的信息
			//1.获得当前客户的id号（已完成）
			//2.到数据库中取数据
			String sqlClientInfo="select * from client where CID=?";
			this.refreshClient(sqlClientInfo, paras);
			//刷新头像
			clientName.setText(bm.query("select CName from client where CID=?",paras));
			
		}else if(e.getSource()==jlMob){
			//通过手机号查询
			String mobNum=jtfMob.getText().trim();
			String[] mobNums={mobNum};
			searchJTF.setText(bm.query(sqlCall, mobNums));//刷新搜索框
			//更新数据模型
			String sqlMob="select OID 业务单号,CID 客户卡号,SendTime 送货时间,OmonerySum 金额,Warehouse 仓库 from orders where CallNumber=? order by SendTime desc";
			orderModel.query(sqlMob, mobNums);
			clientModel.query(sqlMob, mobNums);
			//更新表格
			jtOrder.setModel(orderModel);
			jtClient.setModel(clientModel);
			
		}else if(e.getSource()==jlNam){
			//通过手机号查询
			String namNum=jtfNam.getText().trim();
			String[] namNums={namNum};
			searchJTF.setText(bm.query(sqlCname, namNums));//刷新搜索框
			//更新数据模型
			String sqlNam="select CID 客户卡号,CName 客户姓名,ContactPerson 联系人,Adress 地址,Phone 手机号码 from client where CName=?  order by CID asc";
			orderModel.query(sqlNam, namNums);
			clientModel.query(sqlNam, namNums);
			//更新表格
			jtOrder.setModel(orderModel);
			jtClient.setModel(clientModel);
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
		if(e.getSource()==add)
		{
			add.setEnabled(true);
		}else if(e.getSource()==cancel)
		{
			cancel.setEnabled(true);
		}else if(e.getSource()==save)
		{
			save.setEnabled(true);
		}else if(e.getSource()==delete) 
		{
			delete.setEnabled(true);
		}else if(e.getSource()==edit)
		{
			edit.setEnabled(true);
		}else if(e.getSource()==searchJL)
		{
			searchJL.setEnabled(true);
		}else if(e.getSource()==jlMob)
		{
			jlMob.setEnabled(true);
		}else if(e.getSource()==jlNam)
		{
			jlNam.setEnabled(true);
		}
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==add)
		{
			add.setEnabled(false);
		}else if(e.getSource()==cancel)
		{
			cancel.setEnabled(false);
		}else if(e.getSource()==save)
		{
			save.setEnabled(false);
		}else if(e.getSource()==delete) 
		{
			delete.setEnabled(false);
		}else if(e.getSource()==edit)
		{
			edit.setEnabled(false);
		}else if(e.getSource()==searchJL)
		{
			searchJL.setEnabled(false);
		}else if(e.getSource()==jlMob)
		{
			jlMob.setEnabled(false);
		}else if(e.getSource()==jlNam)
		{
			jlNam.setEnabled(false);
		}
	}
	//////////////////////////////子类//////////////////////////////////////
	public class ProductEditor extends JDialog implements ActionListener{
		
		//定义需要的组件
		JLabel []jlabel;
		JTextField []jtf=null;
		JButton confirm,cancel;
		OrderModel ordermodel;
		JPanel jp11,jp22,jp33;
		int columnNum,rowNum;
		String sql;
		Boolean isJtf=true;//是否初始化JTextField,默认是
		//为了完成修改的功能，下面加一个方法，手动调用是这个方法会初始化文本框，这是和删除操作唯一不通过的地方
		public int initJTextField()
		{
			rowNum=BillingPane.this.jtable.getSelectedRow();//获得选中的行
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "请选中要编辑的商品！");
				return 0;
			}
			
			for(int i=0;i<this.columnNum;i++){
				this.jtf[i].setText(BillingPane.this.pm.getValueAt(rowNum, i).toString());
			}
			jtf[0].setEditable(false);//商品id设置为不可修改
			return 1;
		}
		public ProductEditor(boolean isJtf)
		{
			this.isJtf=isJtf;
			//先通过orderModel获得需要的列名
			pm=new ProductModel();
			//String sqlfirst="select*from orderForm";
			//String parasfirst[]={"1"};
			String sqlEditor="select PID 商品编号,Pname 商品名称,specification 规格型号,unitPrice 单价,quantity 数量,biscount 折扣,monerySum 金额,Remarks 备注 from ProductShipping where OID=(select top 1 OID from orders where CID= ? order by OID desc)";
			String cids[]={cid};
			pm.query(sqlEditor,cids);
			//创建组件
			columnNum=pm.getColumnCount();
			jlabel=new JLabel[columnNum];
			jtf=new JTextField[columnNum];
			
			//System.out.println("获得列数"+columnNum);
			for(int i=0;i<columnNum;i++)//获得列数
			{
				jtf[i]=new JTextField();
				jtf[i].setBorder(BorderFactory.createLoweredBevelBorder());
				jlabel[i]=new JLabel(pm.getColumnName(i));
		
			}
		
			confirm=new JButton("确定");
			cancel=new JButton("取消");
			
			//设置监听
			confirm.addActionListener(this);
			cancel.addActionListener(this);
			
			jp11=new JPanel();
			jp22=new JPanel();
			jp33=new JPanel();
			//设置布局管理器,jp3默认为流式布局
			jp11.setLayout(new GridLayout(columnNum,1,10,10));
			jp22.setLayout(new GridLayout(columnNum,1,10,10));
			
			//添加组件
			for(int i=0;i<columnNum;i++)
			{
				
				jp11.add(jlabel[i]);
				jp22.add(jtf[i]);
			}		
			//添加组件
			jp33.add(confirm);//默认流布局
			jp33.add(cancel);
			
			//用来判断是添加还是修改，如果是修改判断是否选中一行
			if(this.isJtf) {
				if(this.initJTextField()==0){
					return;
				}
			}
			
			//添加到对话框
			this.add(jp11,"West");
			this.add(jp22,"Center");
			this.add(jp33,"South");
			
			//添加到对话框
			this.add(jp11,"West");
			this.add(jp22,"Center");
			this.add(jp33,"South");
			
			//设置显示
			this.setSize(250,350);
			this.setLocation(400,100);
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}


		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String[] editorInfo=new String[9];
			JLabel[] jlEditor=new JLabel[8];
			JTextField[] jtfEditor=new JTextField[8];
			if(e.getSource()==confirm){//如果在Dialog中点击了确认按钮
				BillingPane.this.pm=new ProductModel();//内部类访问外部类成员
				if(this.isJtf){//修改																						
					
					//String sqlJP3="select PID 商品编号,Pname 商品名称,specification 规格型号,unitPrice 单价,quantity 数量,biscount 折扣,monerySum 金额,Remarks 备注 from ProductShipping where OID=(select top 1 OID from orders where CID= ? order by OID desc)";
					sql="update ProductShipping  set Pname=?,specification=?,unitPrice=?,quantity=?,biscount=?,monerySum=?,Remarks=? where PID=? and OID=?";
					for(int i=0;i<editorInfo.length-2;i++){
						editorInfo[i]=jtf[i+1].getText();
					}
					editorInfo[editorInfo.length-2]=jtf[0].getText();
					editorInfo[editorInfo.length-1]=BillingPane.this.newOID;
				}else{//添加(这个区域代码暂时还用不上)
					
				}
				if(BillingPane.this.pm.update(this.sql, editorInfo)){//在这里完成操作
					JOptionPane.showMessageDialog(this,"完成！");
				}else{
					JOptionPane.showMessageDialog(this,"抱歉，操作未能完成！");
				}
				this.dispose();//关闭添加对话框					
			}else if(e.getSource()==cancel){
				this.dispose();
			}
			String[] cid2={BillingPane.this.cid};
			BillingPane.this.refresh(BillingPane.this.sqlJP3,cid2);//在内部类中访问外围类
		}

	}
	/////////////////////////AddView///////////////////////////////
	
}
//------内部类--末-------------------
