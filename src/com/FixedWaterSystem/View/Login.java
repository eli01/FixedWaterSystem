/**(2)
 * 作者：孟庆申
 * 时间：2012.11.26/10:49
 * 功能：显示登录界面；验证用户身份；跳转界面
 * 日志：这是一个非常简洁的界面，力求简洁是整套系统的原则！！！
 * 20:34退出或取消的按钮还没设计
 */
package com.FixedWaterSystem.View;
import javax.imageio.ImageIO;
import javax.swing.*;

import com.FixedWaterSystem.Model.*;
import com.FixedWaterSystem.Tools.Word;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Login extends JDialog implements ActionListener{//z这里继承的是对话框
	
	int w=Toolkit.getDefaultToolkit().getScreenSize().width;
	int h=Toolkit.getDefaultToolkit().getScreenSize().height;
	//定义需要的组件
	JTextField jUser;
	JPasswordField jPasswd;
	JButton jbLogin,jbExit;
	BackImage backimage;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}
	//构造函数
	public Login()
	{
		//创建组件
		jUser=new JTextField(10);
		jPasswd=new JPasswordField(10);
		jbLogin=new JButton(new ImageIcon("images/login_button.jpg"));
		//jbExit=new JButton(new ImageIcon("images/exit_button.jpg"));
		backimage=new BackImage();
	
		jUser.setBounds(148,53,146,23);
		jPasswd.setBounds(148, 97, 146, 23);
		jbLogin.setBounds(343, 52, 80, 71);
		//jbExit.setBounds(480, 560, 70, 40);
		backimage.setBounds(0, 0, 471, 180);
		//设置凹陷
		jUser.setBorder(BorderFactory.createLoweredBevelBorder());
		jPasswd.setBorder(BorderFactory.createLoweredBevelBorder());
		//设置监听
		jbLogin.addActionListener(this);
		//jbExit.addActionListener(this);
		
		//创建容器并添加组件
		Container ct=this.getContentPane();//做一个JDialog的容器
		ct.add(jUser);
		ct.add(jPasswd);
		ct.add(jbLogin);
		//ct.add(jbExit);
		ct.add(backimage);
		
		//设置布局管理器（默认流布局）
		this.setLayout(null);
		
		//设置JDialog
		this.setLocation(w/2-230,h/2-90);
		//this.setUndecorated(true);//不使用边框
		this.setSize(471,180);
		this.setResizable(false);
		this.setVisible(true);
		
	
	}
	
	
	//做一个内部类  功能：导入背景图片，并画出
	class BackImage extends JPanel
	{
		Image image;
		//构造函数
		public BackImage()
		{
			try {
				image=ImageIO.read(new File("images/login_1.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("登陆窗口载入背景图片是异常！");
			}
		}
		
		//重写paintComponent()
		public void paintComponent(Graphics g)
		{
			g.drawImage(image, 0, 0, 471, 180,this);
		}
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jbLogin)//按登陆按钮
		{
			
			String naOrID=jUser.getText().trim();//获取员工姓名或ID
			String password=new String(this.jPasswd.getPassword());//获取密码，提高安全性
			
			UserModel um=new UserModel();
			if(um.checkUser(naOrID, password)){
				this.dispose();
				//跳转到管理页面
				/*
				if(um.getPosition(naOrID).equals(anObject)){
					Window1 w1=new Window1(naOrID);
				}
				*/
				String gradeStr=um.getContent(naOrID,"select usergrade from users where userid=?");
				String name=um.getContent(naOrID, "select username from users where userid=?");				
				//new Index();
				Window1 w1=new Window1(gradeStr,name);
				
			}else{
				jUser.setText("");
				jPasswd.setText("");
				JOptionPane.showMessageDialog(this,"登录名或密码错误！");
			}
	
		}
	}
}
