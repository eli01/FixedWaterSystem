/**
 * 作者：孟庆申
 * 时间：2012.11.28/9:01
 * 功能：当顾客选中订单管理时，p3east会跳转到这个卡片（本质是一个JPanel）
 * 日志：10:13：需要一个默认界面，跳转到window1时会被默认显示的，
 * 暂时不去处理，拿一个背景先代替
 * 18:44:发现JLabel没有addActionListener()方法，可以用addMouseListener
 * 11.19/12.38:排序没有更新，原来是refresh（）的问题（sql语句是不排序的）：把sqlOrderby做成成员变量，重写refresh(String)调用之
 * 
 */

package com.FixedWaterSystem.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.FixedWaterSystem.Model.OrderModel;
import com.FixedWaterSystem.Model.UseMode;
import com.FixedWaterSystem.Model.UserModel;
import com.FixedWaterSystem.Tools.ImagePanel;
import com.FixedWaterSystem.Tools.Word;

public class UserManager extends JPanel implements ActionListener,MouseListener
{
	//定义组件
	JSplitPane jsp1,jsp2,jsp;
	JPanel p1,p2,p3,p4,p12,p34;//从上至下四个部分
	JLabel add,edit,delete,orderby,detail,search;
	JTextField jtf;
	ImagePanel imagepanel=null;
	Image background;
	JTable userTable;
	JScrollPane jscrollpane;
	UseMode user;
	int rowNum;
	//下拉按钮（以前从来没用过）
	JComboBox jcbForOrderby,jcbForOrderby2;
	String jcbArray[]={"按时间","按单价","按订单额"};
	String jcbArray2[]={"从小到大","从大到小"};
	
	String jcb1Str,jcb2Str;
	 String sqlOrderby="select userid 用户编号,username 用户名,userpasswd 用户密码,usergrade 用户级别,usersex 性别,userbg 个性设置,userjob 用户职位,userage 用户年龄,useradress 现居地址,useraser 设置的问题,useranswer 答案,userremind 问题提示 from users order by userid desc";
	 
	 Timer p1_timer;
	 JLabel timeNow;
	 Image timebg,p1_yes;
	 
	 JTextField searchJTF;
	 JLabel searchJL,refresh2;
	 JButton searchJB;
	//构造函数
	public UserManager()
	{
		
		//创建组件
		
		try {
			background=ImageIO.read(new File("images/background2.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imagepanel=new ImagePanel(background); 
		imagepanel.setLayout(new BorderLayout());
		imagepanel.setBounds(0, 0, 790, 505);
		
		//p1
		p1=new JPanel(new BorderLayout());
		p1_timer=new Timer(1000,this);//每隔一秒触发一个ActionEvent
		p1_timer.start();//开始线程
		//获得当前时间
		timeNow=new JLabel("当前时间： "+Calendar.getInstance().getTime().toLocaleString()+"   ",new ImageIcon("images/Time.jpg"),0);//将时间本地化
		timeNow.setFont(Word.f3);
		p1.add(timeNow);//将显示时间的面板放在右边
		//为显示时间的JPanel设置背景
		try {
			timebg=ImageIO.read(new File("images/timebg.jpg"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JLabel orderManager=new JLabel("");
		JLabel yes=new JLabel("用户设置",new ImageIcon("images/yes.jpg"),0);
		yes.setFont(Word.f1);
		ImagePanel timeIP=new ImagePanel(timebg);//用timeBg做背景的一个面板
		timeIP.setLayout(new BorderLayout());
		yes.setLayout(new BorderLayout());
		timeIP.setBackground(Color.white);//设置为
		timeIP.add(yes,"West");
		timeIP.add(timeNow,"East");//在面板的西部加入时间显示
		p1.add(timeIP);//在p1这个面板上加一个带有时间的背景面板
		//p1.add(yes,"West");
		
		
		//p2
		p2=new JPanel(new BorderLayout());
		user=new UseMode();
		String sql="select userid 用户编号,username 用户名,userpasswd 用户密码,usergrade 用户级别,usersex 性别,userbg 个性设置,userjob 用户职位,userage 用户年龄,useradress 现居地址,useraser 设置的问题,useranswer 答案,userremind 问题提示 from users where 1=?";
		String paras[]={"1"};
		user.query(sql, paras);
		userTable=new JTable(user);
		jscrollpane=new JScrollPane(userTable);
		p2.add(jscrollpane);
		//p3
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));//设置为靠左的流布局
		add=new JLabel("添加",new ImageIcon("images/add.jpg"),0);
		delete=new JLabel("删除",new ImageIcon("images/delete.jpg"),0);
		edit=new JLabel("编辑",new ImageIcon("images/edit.jpg"),0);
		orderby=new JLabel("排序",new ImageIcon("images/orderby.jpg"),0);
		jcbForOrderby=new JComboBox(jcbArray);//创建一个下拉列表
		jcbForOrderby2=new JComboBox(jcbArray2);//创建一个下拉列表
		searchJTF=new JTextField(10);
		searchJL=new JLabel(new ImageIcon("images/search.jpg"));
		refresh2=new JLabel(new ImageIcon("images/refresh2.jpg"));
		
		
		//searchJB=new JButton();
		
		//设置字体
		add.setFont(Word.f3);
		delete.setFont(Word.f3);
		edit.setFont(Word.f3);
		orderby.setFont(Word.f3);
		jcbForOrderby.setFont(Word.f3);
		jcbForOrderby2.setFont(Word.f3);
		
		//设置手型
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
		add.setCursor(myCursor);
		delete.setCursor(myCursor);
		edit.setCursor(myCursor);
		orderby.setCursor(myCursor);
		searchJL.setCursor(myCursor);
		refresh2.setCursor(myCursor);
		//设置高亮状态
		add.setEnabled(false);
		orderby.setEnabled(false);
		edit.setEnabled(false);
		refresh2.setEnabled(false);
		delete.setEnabled(false);
		searchJL.setEnabled(false);
		//设置监听
		add.addMouseListener(this);
		delete.addMouseListener(this);
		edit.addMouseListener(this);
		orderby.addMouseListener(this);
		searchJL.addMouseListener(this);
		refresh2.addMouseListener(this);
		
		//添加p3的组件
		p3.add(refresh2);
		p3.add(add);
		p3.add(delete);
		p3.add(edit);
		p3.add(orderby);
		p3.add(jcbForOrderby);
		p3.add(jcbForOrderby2);
		p3.add(searchJL);
		p3.add(searchJTF);
				
		p4=new JPanel(null);
		p12=new JPanel(new BorderLayout());
		p34=new JPanel(new BorderLayout());
		//添加
		
		jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,p1,p2);
		//指定上边的面板占多少
		jsp1.setDividerLocation(30);
		//取消边界线
		jsp1.setDividerSize(0);
		
		jsp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,p3,p4);
		jsp2.setDividerLocation(30);
		jsp2.setDividerSize(0);
		
		p12.add(jsp1);
		p34.add(jsp2);
		jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,p12,p34);
		jsp.setDividerLocation(500);
		jsp.setDividerSize(0);
		//this.setForeground(background);
		imagepanel.setOpaque(false);
		this.imagepanel.add(jsp);
		this.setLayout(new BorderLayout());
		this.add(imagepanel);
	
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		//如果用户点击了添加按钮
//		if(e.getSource()==add){
//			//它会与创造它的外围对象有了某种联系，于是能访问外围类的所有成员，不需任何特殊条件。
//			AddView addDialog=new AddView();//在外部类创建这个内部类对象
//		}
		this.timeNow.setText("当前时间： "+Calendar.getInstance().getTime().toLocaleString()+"   ");
	}
	//用于刷新JTabel的函数
	public void refresh(String sql){
		//重新获得新的数据模型
		user=new UseMode();
		user.query(sql);
		userTable.setModel(user);//更新ordeTable
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//如果用户点击了添加按钮
		if(e.getSource()==add){
			//它会与创造它的外围对象有了某种联系，于是能访问外围类的所有成员，不需任何特殊条件。
			AddView addDialog=new AddView(false);//在外部类创建这个内部类对象,不初始化
		}else if(e.getSource()==delete){
			//得到要删除对象的流水号
			rowNum=this.userTable.getSelectedRow();//获得选中的行
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "请选中要删除的订单！");
				return;
			}
			String SequenceNumber=(String)user.getValueAt(rowNum, 0);
			String sql="delete from users where userid=?";
			String[] paras={SequenceNumber};
			OrderModel om=new OrderModel();
			om.update(sql, paras);
			this.refresh(sqlOrderby);//更新数据模型以及JTabel
		}else if(e.getSource()==edit){
			AddView addDialog=new AddView(true);//在外部类创建这个内部类对象
		}else if(e.getSource()==orderby){//排序
			
			 jcb1Str=jcbForOrderby.getSelectedItem().toString();//选择的排序模式
			 jcb2Str=jcbForOrderby2.getSelectedItem().toString();//选择的排序方式
			//根据用户不同的设置构建SQL命令
			 if(jcb1Str.equals("按时间")){
				 if(jcb2Str.equals("从小到大")){
					sqlOrderby="select userid 用户编号,username 用户名,userpasswd 用户密码,usergrade 用户级别,usersex 性别,userbg 个性设置,userjob 用户职位,userage 用户年龄,useradress 现居地址,useraser 设置的问题,useranswer 答案,userremind 问题提示 from users by userid asc"; //升序
				 }else if(jcb2Str.equals("从大到小")){
					sqlOrderby="select userid 用户编号,username 用户名,userpasswd 用户密码,usergrade 用户级别,usersex 性别,userbg 个性设置,userjob 用户职位,userage 用户年龄,useradress 现居地址,useraser 设置的问题,useranswer 答案,userremind 问题提示 from users order by userid desc"; //降序
				 }
			 }else if(jcb1Str.equals("按单价")){
				 if(jcb2Str.equals("从小到大")){
					sqlOrderby="select userid 用户编号,username 用户名,userpasswd 用户密码,usergrade 用户级别,usersex 性别,userbg 个性设置,userjob 用户职位,userage 用户年龄,useradress 现居地址,useraser 设置的问题,useranswer 答案,userremind 问题提示 from users order by userid asc"; //升序
				 }else if(jcb2Str.equals("从大到小")){
					sqlOrderby="select userid 用户编号,username 用户名,userpasswd 用户密码,usergrade 用户级别,usersex 性别,userbg 个性设置,userjob 用户职位,userage 用户年龄,useradress 现居地址,useraser 设置的问题,useranswer 答案,userremind 问题提示 from users order by userid desc"; //降序 
				 }
				 
			 }else if(jcb1Str.equals("按订单额")){
				 if(jcb2Str.equals("从小到大")){
					sqlOrderby="select userid 用户编号,username 用户名,userpasswd 用户密码,usergrade 用户级别,usersex 性别,userbg 个性设置,userjob 用户职位,userage 用户年龄,useradress 现居地址,useraser 设置的问题,useranswer 答案,userremind 问题提示 from users order by userid asc"; //升序				
					this.refresh(sqlOrderby);//更新数据模型以及JTabel
				 }else if(jcb2Str.equals("从大到小")){
					sqlOrderby="select userid 用户编号,username 用户名,userpasswd 用户密码,usergrade 用户级别,usersex 性别,userbg 个性设置,userjob 用户职位,userage 用户年龄,useradress 现居地址,useraser 设置的问题,useranswer 答案,userremind 问题提示 from users order by userid desc "; //降序
				 }
			 }
			 OrderModel om=new OrderModel();
			 om.query(sqlOrderby);
			this.refresh(sqlOrderby);//更新数据模型以及JTabel
		}else if(e.getSource()==searchJL){
			String sql="select userid 用户编号,username 用户名,userpasswd 用户密码,usergrade 用户级别,usersex 性别,userbg 个性设置,userjob 用户职位,userage 用户年龄,useradress 现居地址,useraser 设置的问题,useranswer 答案,userremind 问题提示 from users where userid=?";
			String paras[]={searchJTF.getText().trim()};
			 OrderModel om=new OrderModel();
			 om.query(sql,paras);
			this.userTable.setModel(om);//更新数据模型以及JTabel
		}else if(e.getSource()==refresh2){
			this.refresh(sqlOrderby);
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
		}else if(e.getSource()==delete) 
		{
			delete.setEnabled(true);
		}else if(e.getSource()==edit)
		{
			edit.setEnabled(true);
		}else if(e.getSource()==searchJL)
		{
			searchJL.setEnabled(true);
		}else if(e.getSource()==orderby)
		{
			edit.setEnabled(true);
		}else if(e.getSource()==refresh2)
		{
			searchJL.setEnabled(true);
		}
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==add)
		{
			add.setEnabled(false);
		}else if(e.getSource()==delete) 
		{
			delete.setEnabled(false);
		}else if(e.getSource()==edit)
		{
			edit.setEnabled(false);
		}else if(e.getSource()==searchJL)
		{
			searchJL.setEnabled(false);
		}else if(e.getSource()==orderby)
		{
			edit.setEnabled(false);
		}else if(e.getSource()==refresh2)
		{
			searchJL.setEnabled(false);
		}
	}
	///////////////////////AddView///////////////////////////////////////////////////////////////////////////////
	/**
	 * 作者：mengqingshen
	 * 时间：2012.11.28/14:51
	 * 功能：当用户点击添加操作时，该对话框跳出完成添加操作
	 * 日志：因为该对话框逻辑上是orderManager这个panel的子对话窗口，但构造方法中却不允许让一个panel作一个dialog的
	 * 父窗口，为了能在这个窗口更新orderManager中的table,下面我会先尝试让orderManager监听这个窗口的“添加”按钮
	 * 
	 * 20:03上面的方法行不通，后面采用了内部类的方式解决，具体就是，在内部类中访问外围类的成员，从而达到更新JLabel的目的
	 * 补充：内部类方法可行，可不利于代码复用，更好的解决方法寻找中……
	 * 后面出现严重报错，以为是Vector的问题，因为报了空指针的异常（越界的问题到时真的存在，后面解决了，不过改到最后已经没用Vector了，其实Vector还是很有效的），后面发现原因很简单，其实是没有初始化JPanel的关系
	 */

	public class AddView extends JDialog implements ActionListener{
		
		//定义需要的组件
		JLabel []jlabel;
		JTextField []jtf=null;
		JButton confirm,cancel;
		OrderModel ordermodel;
		JPanel jp1,jp2,jp3;
		int columnNum,rowNum;
		String sql;
		String paras[]=new String[12];//这样定义存在一定问题，不便于后面更改数据库
		Boolean isJtf=true;//是否初始化JTextField,默认是
		//为了完成修改的功能，下面加一个方法，手动调用是这个方法会初始化文本框，这是和删除操作唯一不通过的地方
		public int initJTextField()
		{
			rowNum=UserManager.this.userTable.getSelectedRow();//获得选中的行
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "请选中要编辑的订单！");
				return 0;
			}
			
			for(int i=0;i<this.columnNum;i++){
				this.jtf[i].setText(this.ordermodel.getValueAt(rowNum, i).toString());
			}
			jtf[0].setEditable(false);//主键设置为不可修改
			return 1;
		}
		public AddView(boolean isJtf)
		{
			this.isJtf=isJtf;
			//先通过orderModel获得需要的列名
			ordermodel=new OrderModel();
			String sqlfirst="select userid 用户编号,username 用户名,userpasswd 用户密码,usergrade 用户级别,userjob 用户职位,userage 用户年龄,useradress 现居地址,useranswer 设置的问题,useranswer 答案,userremind 问题提示 from users";
			//String parasfirst[]={"1"};
			ordermodel.query(sqlfirst);//这是对象orderby已经可以拿到列名了
			//创建组件
			columnNum=ordermodel.getColumnCount();
			jlabel=new JLabel[columnNum];
			jtf=new JTextField[columnNum];
			
			//System.out.println("获得列数"+columnNum);
			for(int i=0;i<columnNum;i++)//获得列数
			{
				jtf[i]=new JTextField();
				jlabel[i]=new JLabel(ordermodel.getColumnName(i));
		
			}
		
			confirm=new JButton("确定");
			cancel=new JButton("取消");
			
			//设置监听
			confirm.addActionListener(this);
			cancel.addActionListener(this);
			
			jp1=new JPanel();
			jp2=new JPanel();
			jp3=new JPanel();
			//设置布局管理器,jp3默认为流式布局
			jp1.setLayout(new GridLayout(columnNum,1));
			jp2.setLayout(new GridLayout(columnNum,1));
			
			//添加组件
			for(int i=0;i<columnNum;i++)
			{
				
				jp1.add(jlabel[i]);
				jp2.add(jtf[i]);
			}		
			//添加组件
			jp3.add(confirm);//默认流布局
			jp3.add(cancel);
			
			//用来判断是添加还是修改，如果是修改判断是否选中一行
			if(this.isJtf) {
				if(this.initJTextField()==0){
					return;
				}
			}
			
			//添加到对话框
			this.add(jp1,"West");
			this.add(jp2,"Center");
			this.add(jp3,"South");
			
			//添加到对话框
			this.add(jp1,"West");
			this.add(jp2,"Center");
			this.add(jp3,"South");
			
			//设置显示
			this.setSize(400,400);
			this.setLocation(400,300);
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==confirm){//如果在Dialog中点击了确认按钮
				UserManager.this.user=new UseMode();//内部类访问外部类成员
				if(this.isJtf){//修改
					
					sql="update username=?,userpasswd=?,usergrade=?,usersex=?,userbg=?,userjob=?,userage=?,useradress=?,useraser=?,useranswer=?,userremind=? from users where userid=?";
					for(int i=0;i<paras.length-1;i++){
						paras[i]=jtf[i+1].getText();
					}
					paras[paras.length-1]=jtf[0].getText();
					
				}else{//添加
					sql="insert into users values(?,?,?,?,?,?,?,?,?,?,?,?)";
					for(int i=0;i<jtf.length;i++){
						paras[i]=jtf[i].getText();
					}
					//paras[]={jtf[0].getText(),jtf[1].getText(),jtf[2].getText(),jtf[3].getText(),jtf[4].getText(),jtf[5].getText(),jtf[6].getText()};
				}
				if(UserManager.this.user.update(this.sql, this.paras)){//在这里完成操作
					JOptionPane.showMessageDialog(this,"完成！");
				}else{
					JOptionPane.showMessageDialog(this,"抱歉，操作未能完成！");
				}
				this.dispose();//关闭添加对话框					
			}else if(e.getSource()==cancel){
				this.dispose();
			}
			UserManager.this.refresh(UserManager.this.sqlOrderby);//在内部类中访问外围类
		}

	}
	/////////////////////////AddView///////////////////////////////
	//外围

}



