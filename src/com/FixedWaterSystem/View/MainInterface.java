/**
 * 作者：孟庆申
 * 时间：2012.12.1/22.13
 * 功能：决定改变原来的模式，这就是模式的主控窗口，她是一个选项卡模式的
 * 日志：现在的计划是这样的（后面很可能还会改），为了迎合不同用户的使用习惯，我们有操作平台，分别是菜单栏、控制面板、选项卡，个人认为选项卡是最优秀的
 * 22:18共有11个模块，随着开发的进展会一个一个的增加
 * 12.2:方案要修改，因为现在的情况是结果返回区域应经是一个卡片布局，和选项卡布局很难一起同步，新的方案是菜单栏和结果栏将作为卡片布局的子模块
 * 这个选项卡还为被使用
 */
package com.FixedWaterSystem.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainInterface extends JPanel{
	//这里做一个程序入口方便测试效果
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainInterface();
	}
	
	//组件
	JTabbedPane jtp;//选项卡
	JPanel jp[]=new JPanel[1];//选项卡个数
	JLabel jl[];//每个选项卡的标签
	public MainInterface()
	{
		//初始化主窗口：其实没有后面将做得选项卡（JPanel）,主窗口什么有没有
		JTabbedPane jtp=new JTabbedPane();
		//让orderManage作为一个选项卡存在
		OrderManage om=new OrderManage();
		//让CustormerManage作为一个选项卡存在
		CustormerManage cm=new CustormerManage();
		//添加到选项卡
		jtp.add("顾客管理",om);
		jtp.add("订单管理",cm);
		
	}
}
