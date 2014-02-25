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
public class CusRegPane extends JDialog implements ActionListener,MouseListener{
	public static void main(String[] args){
		new CusRegPane();
	}
	//定义内部类的组件，共24个
	JPanel jpLogo,jpState,jpClient;			
	//logo部分	
	//state部分		
	//billing部分
	Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
	JSplitPane jsp1,jsp2,jsp3,jsp4,jsp5;
	JPanel jpBillingLeft,jpContent,jp1,jp1West,jp1East,jp1WestWest,jp1WestCenter,jp1EastWest,jp1EastCenter,jp2,jp3,jp23;
	JLabel add,edit,delete,orderby,detail,search,cancel,save;
	JScrollPane jscrollp1,jscrollp2,jscrollp3;
	OrderModel order;
	JComboBox jcbForOrderby,jcbForOrderby2;
	JTable jtable;
	ProductModel pm;
	String sqlJP3="select CID 客户卡号,CName 客户名称,Phone 联系电话,Adress 联系地址,ContactPerson 联系人,JoinTime 加入时间 from client";
	String[] orderStr=new String[22];
	String newCID;
	String cid;
	String[] oids=new String[1];
	String sqlOrder="insert into orders values(?,?,?,?,?,?,?,?,?,?)";//添加新订单
	BillingModel bm=new BillingModel();
	String sqlCID="select top 1 CID from client order by CID desc";
	JLabel clientHead,clientName;			
	
	int rowNum,columnNum;
	//下拉按钮（以前从来没用过）
	//JComboBox jcbForOrderby,jcbForOrderby2;
	String jcbArray[]={"按时间","按单价","按订单额"};
	String jcbArray2[]={"从小到大","从大到小"};
	
	String jcb1Str,jcb2Str;
	String sqlOrderby="select*from orderForm order by OrderTime desc";
	 
	Timer p1_timer;
	JLabel timeNow;
	Image timebg,p1_yes;
	 
	JTextField searchJTF;
	JLabel searchJL,refresh2;
	JButton searchJB;
	
	JLabel jlLogo=null;
	String[] jlabelString={"客户卡号","客户名称","联系电话","联系地址","联系人","电子邮箱","出生日期","加入时间","客户级别","客户类型","片区名称","地区类型","关联区域","送货类型","客户楼层","是否有效","结算单位","最近服务","产品型号","常用产品名称","常送数量","备注"};
	JLabel[] jlabel=new JLabel[22];
	JTextField jtf[]=new JTextField[22];
	//做一个全局变量判断是否是在编辑
	boolean isEdit=false;
	public CusRegPane(){		
		//调用各个部分的初始化函数
		initLogo();
		initState();
		initBilling();
		
		//----以下拆分的过程很有规律，正是日志中那种思想的体现，不断的错误证明（折腾了一晚上了！）只有这样编译器才能理解----------
		
		//LOGO和其它
		jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpLogo,jpContent);
		jsp1.setDividerLocation(54);//指定logo的宽度																	
		jsp1.setDividerSize(0);			
		
		//state和其它（除logo）
		jsp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpState,jpClient);
		jpContent.add(jsp2);
		jsp2.setDividerSize(0);
		jsp2.setDividerLocation(40);//指定面板state的高
		
		
		//jp1和其它（除logo、state、jpBillingLeft）
		jsp4=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp1,jp23);
		jpClient.add(jsp4);
		jsp4.setDividerLocation(400);
		jsp4.setDividerSize(0);
		
		//jp2和其它（除logo、state、jpBillingLeftjp1即jp3）
		jsp5=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp2,jp3);
		jp23.add(jsp5);
		jsp5.setDividerLocation(30);
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
		this.setLocation(w/2-601,0);
		this.setTitle("客户注册界面");
		this.setSize(1002,750);
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
			jpLogo_Image=ImageIO.read(new File("images/c.jpg"));//得到图片
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpLogo_bg=new ImagePanel(jpLogo_Image);//得到背景面板
		jpLogo_bg.setLayout(null);
		//创建组件

		//添加
						
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
		clientName=new JLabel(" 未设置");
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
	public void initBilling(){

		jpContent=new JPanel(new BorderLayout());
		jpClient=new JPanel(new BorderLayout());
		//该panel中又嵌套两个panel，不打算用边界布局（东、西），拆分布局更加灵活一些
		jpBillingLeft=new JPanel(new BorderLayout());
		this.initJp1();			
		this.initJp2();
		this.initJp3();
		jp23=new JPanel(new BorderLayout());					
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
		newCID=DataFactory.getID(bm.query(sqlCID),"C",false);
		jtf[0].setText(newCID);//精确时间、C打头
		
		//jp1=new JPanel(new GridLayout(12,4,10,5));//将jp1设置成为网格布局，用来登记订单信息	
		jp1=new JPanel(new BorderLayout(5,5));
		jp1West=new JPanel(new BorderLayout(10,10));
		jp1WestWest=new JPanel(new GridLayout(12,1,10,10));
		jp1WestCenter=new JPanel(new GridLayout(12,1,10,10));
		jp1East=new JPanel(new BorderLayout(5,5));
		jp1EastCenter=new JPanel(new GridLayout(12,1,10,10));
		jp1EastWest=new JPanel(new GridLayout(12,1,10,10));
		
		//jp1.setVisible(true);
		for(int i=0;i<11;i++){//添加标签和录入文本框
			jp1WestWest.add(jlabel[2*i]);
			jp1WestCenter.add(jtf[2*i]);
			jp1EastWest.add(jlabel[2*i+1]);
			jp1EastCenter.add(jtf[2*i+1]);
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
		
		//searchJB=new JButton();
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
		
		add.setEnabled(false);
		save.setEnabled(false);
		edit.setEnabled(false);
		cancel.setEnabled(false);
		delete.setEnabled(false);
		searchJL.setEnabled(false);
		//refresh2.setCursor(myCursor);
		
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
		
		pm.query(sqlJP3);
		jtable=new JTable(pm);
		jscrollp1=new JScrollPane(jtable);
		jp3.add(jscrollp1);			
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
			for(int i=0;i<cInfo.length;i++){
				jtf[i].setText(cInfo[i]);
			}
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sh.sqlClose();
		}
	
	}
	public int initJTextField()
	{
		columnNum=pm.getColumnCount();
		rowNum=this.jtable.getSelectedRow();//获得选中的行
		
		if(rowNum==-1){
			JOptionPane.showMessageDialog(this, "请选中要编辑的商品！");
			return 0;
		}
		
		for(int i=0;i<this.columnNum;i++){
			this.jtf[i].setText(this.pm.getValueAt(rowNum, i).toString());
		}
		jtf[0].setEditable(false);//商品id设置为不可修改
		return 1;
	}
	
	////创建新的客户信息到数据库
	public void createClient(){
		//创建新的订单和出货单到数据库
		//1.获得searchJTF中的文本（客户ID）
		cid=searchJTF.getText().trim();
		//2.调用函数用数据库中新的出货单刷新jtable
		this.refresh(sqlJP3);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.timeNow.setText("当前时间： "+Calendar.getInstance().getTime().toLocaleString()+"   ");

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//如果用户点击了新增按钮
		if(e.getSource()==cancel){			
			for(int i=1;i<jlabel.length;i++){
				jtf[i].setText("");
			}
			if(isEdit){
				isEdit=false;
			}
			JOptionPane.showMessageDialog(this,"取消成功！");
			
		}else if(e.getSource()==save||e.getSource()==add){
			Object[] possibleValues={"保存","取消"};
			Object selectValue=JOptionPane.showInputDialog(null, "确定保存这位订单吗？", "保存客户对话框", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
			//读取货物的信息
			if(selectValue.equals(possibleValues[0])){
				String[] cInfo=new String[22];
				for(int i=0;i<cInfo.length;i++){
					cInfo[i]=jtf[i].getText().trim();
				}
				String sql="insert into client values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				if(isEdit){
					cInfo[cInfo.length-1]=cInfo[0];
					for(int i=1;i<cInfo.length;i++){
						cInfo[i-1]=cInfo[i];
					}					
					sql="update client set CName=?,Phone=?,Adress=?,ContactPerson=?,Email=?,Birthday=?,JoinTime=?,Grade=?,CType=?,Area=?,AreaType=?,ARegion=?,CarType=?,CFloor=?,Effective=?,Denominated=?,RecentServer=?,ProductModel=?,OftenPro=?,OftenQuantity=?,Remarks=? where CID=?";
					isEdit=false;
				}
				bm.update(sql,cInfo);
				JOptionPane.showMessageDialog(this,"订单已保存！");
				this.refresh(sqlJP3);
			}else{
				return;
			}
			//连接数据库完成任务
			//String
		}else if(e.getSource()==delete){//排序
			
			rowNum=jtable.getSelectedRow();//获得选中的行
			//System.out.println("选中行数："+rowNum);
			if(rowNum==-1){//客户未选择一行并点击删除按钮
				Object[] possibleValues={"是","否","取消"};
				Object selectValue=JOptionPane.showInputDialog(null, "确定删除当前客户吗？", "客户删除对话框", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
								
				if(selectValue.equals(possibleValues[0])){
					//删除刚刚保存的客户信息
					String[] paras={jtf[0].getText().trim()};
					if(bm.update("delete from client where CID=?", paras)){
						JOptionPane.showMessageDialog(this, "当前客户删除成功！");
					}else{
						JOptionPane.showMessageDialog(this, "删除失败，请查看客户卡号是否正确！");
					}
				}else if(selectValue.equals(possibleValues[2])){
					//取消这次操作
					return;
				}else{
					//提示接线员选中后再操作
					JOptionPane.showMessageDialog(this, "请选中要删除的订单！");
					return;
				}						
			}else{
				String[] paras={(String)pm.getValueAt(rowNum, 0)};
				BillingModel bm=new BillingModel();
				bm.update("delete from client where CID=?", paras);
				this.refresh(sqlJP3);//更新数据模型以及JTabel
			}	
					
		}else if(e.getSource()==edit){
			rowNum=jtable.getSelectedRow();//获得选中的行
			if(rowNum==-1){//客户未选择一行并点击删除按钮
				Object[] possibleValues={"是","取消"};
				Object selectValue=JOptionPane.showInputDialog(null, "确定要选择一个客户进行编辑吗？", "编辑对话框", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
								
				if(selectValue.equals(possibleValues[0])){
					
					JOptionPane.showMessageDialog(this, "请选中要编辑的客户信息！");
					return;
				}else{
					//提示接线员选中后再操作					
					return;
				}						
			}else{
				//刷新编辑区
				String[] cids={(String)jtable.getValueAt(rowNum, 0)};
				this.refreshClient("select * from client where CID=?", cids);	
				isEdit=true;
			}	
			
		}else if(e.getSource()==searchJL){
			//刷新客户上次订单的出货数据
			
			//1.获得searchJTF中的文本（客户ID）
			cid=searchJTF.getText().trim();
			//System.out.println(cid);
			String paras[]={cid};
			//2.调用函数刷新jtable
			this.refresh(sqlJP3+" where CID=?",paras);
			
			//刷新当前客户的信息
			//1.获得当前客户的id号（已完成）
			//2.到数据库中取数据
			String sqlClientInfo="select * from client where CID=?";
			this.refreshClient(sqlClientInfo, paras);
			//刷新头像
			clientName.setText(bm.query("select CName from client where CID=?",paras));					
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
		}
	}
}
	
