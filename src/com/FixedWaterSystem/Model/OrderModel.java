/**
 * 作者：孟庆申
 * 时间：2012.11.28/11:17
 * 功能：
 * 日志：这里出现了一些问题，为了实现排序，重写query方法，但出现了代码复用较高的情况（未解决）
 */
package com.FixedWaterSystem.Model;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;

import com.FixedWaterSystem.DateBase.*;
public class OrderModel extends AbstractTableModel{
	
	Vector<String> colums;//用来取列名的Vector
	Vector<Vector> rows;//用来取每一行数据的Vector（可以看做一个二位的动态数组）
	
	//用于增删改的函数
	public boolean update(String sql,String []paras){
		SqlHelper sqlhelper=new SqlHelper();
		return sqlhelper.update(sql, paras);
	}
	//这个是不带paras[]参数的方法
	public void query(String sql)
	{
		//初始化列名Vector
		this.colums=new Vector<String>();
		//初始化各行数据
		this.rows=new Vector<Vector>();
		//到数据库中取得数据
		SqlHelper sqlhelper=new SqlHelper();
		ResultSet rs=sqlhelper.query(sql);
		//将得到的数据传给colums和rows
		try {
			//通过rsmt对象不但能得到列数，还能得到对应的列名
			ResultSetMetaData rsmd=rs.getMetaData();
			for(int i=0;i<rsmd.getColumnCount();i++)
			{
				this.colums.add(rsmd.getColumnName(i+1));
				//System.out.println(rsmd.getCatalogName(i+1));
			}
			
			while(rs.next())
			{
				Vector<String> eachRow=new Vector<String>();
				for(int i=0;i<rsmd.getColumnCount();i++){
					eachRow.add(rs.getString(i+1));
					//System.out.println(rs.getString(i+1));
				}
				rows.add(eachRow);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//为了取数据，没有在query()中关闭资源，为此我们在这一层关闭之
			sqlhelper.sqlClose();
		}
	}
	//用于查询显示信息的方法,没有返回值，查询的结果已经在两个Vector中，我们用重写AbstractTableModel类中的方法来实现数据的访问
	public void query(String sql,String []paras)
	{
		//初始化列名Vector
		this.colums=new Vector<String>();
		//初始化各行数据
		this.rows=new Vector<Vector>();
		//到数据库中取得数据
		SqlHelper sqlhelper=new SqlHelper();
		ResultSet rs=sqlhelper.query(sql, paras);
		//将得到的数据传给colums和rows
		try {
			//通过rsmt对象不但能得到列数，还能得到对应的列名
			ResultSetMetaData rsmd=rs.getMetaData();
			for(int i=0;i<rsmd.getColumnCount();i++)
			{
				this.colums.add(rsmd.getColumnName(i+1));
				//System.out.println(rsmd.getCatalogName(i+1));
			}
			
			while(rs.next())
			{
				Vector<String> eachRow=new Vector<String>();
				for(int i=0;i<rsmd.getColumnCount();i++){
					eachRow.add(rs.getString(i+1));
					//System.out.println(rs.getString(i+1));
				}
				rows.add(eachRow);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//为了取数据，没有在query()中关闭资源，为此我们在这一层关闭之
			sqlhelper.sqlClose();
		}
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colums.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return (String)((Vector)this.rows.get(rowIndex)).get(columnIndex);
	}
	public String getColumnName(int column){
		return (String)this.colums.get(column);//如果得到的结果不是String,我们强制转换之，这样数据库的构建中数据类型的选择更自由
	}

}
