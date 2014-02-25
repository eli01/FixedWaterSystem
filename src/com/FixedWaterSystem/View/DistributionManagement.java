/**
 * 作者：孟庆申
 * 时间：2012.12.2
 * 功能：配送管理模块的主界面
 * 
 * 
 * 日志：
 * 2012.12.2该模块有多少子模块未确定
 */
package com.FixedWaterSystem.View;
import com.FixedWaterSystem.Tools.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
public class DistributionManagement extends JPanel implements MouseListener,ActionListener{
	
	
	JSplitPane jsp_jp_controlMenu,jsp_this;//拆分窗口
	JPanel jp_controlMenu,jp_array,jp_table,jp_right,jp_north,jp_south;//前三个是卡片布局，最后一个是包含jp_array,jp_table的拆分窗口右边部分
	JLabel jl_VehicleTracking,jl_leftArray,jl_rightArray,jl_dm;//车辆跟踪管
	ImagePanel controlMenu_bg_panel;//操作面板的背景
	Image controlMenu_bg,leftArray,rightArray;
	CardLayout cardForArray,cardForTable;//为按钮和显示分别作卡片布局
	
	//初始化配送管理模块菜单面板的函数
	public void init_controlMenu(){			
		//创建组件
		jp_controlMenu=new JPanel(new BorderLayout());//边界布局
		try {
			controlMenu_bg=ImageIO.read(new File("images/left_bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlMenu_bg_panel=new ImagePanel(controlMenu_bg);
		controlMenu_bg_panel.setLayout(new GridLayout(2,1));//设置背景面板为空布局
		//创建标签
		jl_dm=new JLabel("分配管理模块",new ImageIcon("images/dm_jlabel.jpg"),0);
		jl_VehicleTracking=new JLabel("车辆跟踪管理",new ImageIcon("images/jl_VehicleTracking.jpg"),0);
		
		//设置标签
		//jl_dm.setBounds(5, 5, 170, 40);
		//jl_VehicleTracking.setBounds(40, 50, 100, 30);
		
		//添加
		controlMenu_bg_panel.add(jl_dm);	
		controlMenu_bg_panel.add(jl_VehicleTracking);
		//做一个拆分
		jp_north=new JPanel(new BorderLayout());
		jp_south=new JPanel(null);
		jp_north.add(controlMenu_bg_panel);
		jsp_jp_controlMenu=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp_north,jp_south);
		jsp_jp_controlMenu.setDividerLocation(100);
		jsp_jp_controlMenu.setDividerSize(20);
		this.jp_controlMenu.add(jsp_jp_controlMenu);
	}
	
	//初始化箭头面板的函数	
	public void init_array()
	{
		//创建组件
		jl_leftArray=new JLabel(new ImageIcon("images/leftArray.jpg"));
		jl_rightArray=new JLabel(new ImageIcon("images/rightArray.jpg"));
		jp_array=new JPanel(cardForArray);
		//注册监听
		jl_rightArray.addMouseListener(this);
		jl_leftArray.addMouseListener(this);
		//添加组件
		jp_array.add(jl_leftArray,"0");
		jp_array.add(jl_rightArray,"1");
		
	}
	
	//初始化结果面板的函数
	public void init_jp_table(){
		jp_table=new JPanel(cardForTable);
		//临时的
		OrderManage om=new OrderManage();
		jp_table.add(om,"0");
	}
	//构造函数
	public DistributionManagement()
	{
		//调用各个初始化函数初始化界面
		this.setLayout(new BorderLayout());
		this.init_controlMenu();
		this.init_array();
		this.init_jp_table();
	
		//组合初始化后的各个构件
		jp_right=new JPanel(new BorderLayout());//包含jp_array和jp_table
		jp_right.add(jp_array,"West");
		jp_right.add(jp_table,"East");
	
		jsp_this=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jp_controlMenu,jp_right);
		jsp_this.setDividerLocation(180);
		jsp_this.setDividerSize(0);
	
		//添加到this
		this.add(jsp_this);
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jl_leftArray){
			this.cardForArray.show(jp_array, "1");
			this.jsp_this.setDividerLocation(0);
		}else if(e.getSource()==jl_rightArray){
			this.cardForArray.show(jp_array, "0");
			this.jsp_this.setDividerLocation(180);
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
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
