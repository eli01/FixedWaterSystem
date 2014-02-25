/**
 * 作者：孟庆申
 * 时间：2012.11.26/13:30
 * 功能：在底层完成对数据库的操作
 * 日志：先是简单的增删改查；后面改进是增加复杂的功能
 * 11.29重写了query
 */
package com.FixedWaterSystem.DateBase;
import java.sql.*;

import javax.swing.JOptionPane;
public class SqlHelper {

	//定义需要的对象
	PreparedStatement ps=null;//包含sql语句的接口//http://www.hudong.com/wiki/preparedstatement?hf=youdaocitiao&pf=youdaocitiao
	ResultSet rs=null;//结果集
	Connection ct=null;
	//链接sql sever的驱动
	String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://127.0.0.1:1433;databaseName=FixedWaterSystem";
	String user="sa";
	String passwd="truman";
	//基本信息sql语句
	//String sqlBasic="select ID,Name,Age,Sex,Address,QQNum,PhoneNum from FixedWaterSystem";
	//详细信息
	//String sqlDetail="select * from FixedWaterSystem";
	//修改语句
	//String sqlUpdate="update";
	
	//构造函数
	public SqlHelper()
	{
		try {
			Class.forName(driver);//加载驱动
			ct=DriverManager.getConnection(url,user,passwd);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("加载驱动异常！");
			//JOptionPane.showMessageDialog(this,"加载驱动异常");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("建立连接异常！");
		}
	}
	//用于查询的函数（不需要paras[]）
	public ResultSet query(String sql)
	{
		try {
			System.out.println(sql);
			//采用注入方式
			 //设计结果集为可滚动
			//ps=ct.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=ct.prepareStatement(sql);
			//对sql语句赋值
			System.out.println("下一部执行");
			rs=ps.executeQuery();//完成查询操作并将结果返回给结果集
			System.out.println("query（Strng）操作完成");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("对sql预处理时发现错误！");
		}
		//这里没有马上关闭连接，否则无法返回结果集
		//关闭的操作交给model层
		return rs;
	}
	
	
	
	//用于查询的函数（会返回查询的结果）
	public ResultSet query(String sql,String []paras)
	{
		try {
			//采用注入方式
			System.out.println(sql+" "+paras[0]);
			ps=ct.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();//完成查询操作并将结果返回给结果集
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("对sql预处理时发现错误！");
		}
		//这里没有马上关闭连接，否则无法返回结果集
		//关闭的操作交给model层
		return rs;
	}
	
	//用于增、删、改的函数（返回操作是否成功）
	public boolean update(String sql)
	{
		boolean isOK=true;
		try {
			ps=ct.prepareStatement(sql);

			if(ps.executeUpdate()!=1)
			{
				isOK=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isOK=false;
			e.printStackTrace();
			System.out.println("对sql预处理时发现错误！");
		}finally{
			this.sqlClose();
		}
		return isOK;
	}
	
	public boolean update(String sql,String paras[])
	{
		boolean isOK=true;
		try {
			ps=ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			if(ps.executeUpdate()!=1)
			{
				isOK=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isOK=false;
			e.printStackTrace();
			System.out.println("对sql预处理时发现错误！");
		}finally{
			this.sqlClose();
		}
		return isOK;
	}
	
	//关闭操作
	public void sqlClose()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();

			if(ct!=null) ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("关闭资源异常！");
		}	
	}	
}
