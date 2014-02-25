//该数据模型给开单界面提供数据操作支持
package com.FixedWaterSystem.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.FixedWaterSystem.DateBase.*;
public class BillingModel {
	//构造函数

	//增加几条数据
	public boolean update(String sql){
		SqlHelper sh=new SqlHelper();
		return sh.update(sql);
	}
	//重载update()这个函数
	public boolean update(String sql,String []paras){
		SqlHelper sqlhelper=new SqlHelper();
		return sqlhelper.update(sql, paras);
	}
	//用于查询的函数（会返回查询的结果）
	public String query(String sql)
	{
		String str="不知道";
		SqlHelper sh=new SqlHelper();
		try {			
			ResultSet rs=sh.query(sql);
			if(rs.next()){
				str=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sh.sqlClose();
		}
		//这里没有马上关闭连接，否则无法返回结果集
		//关闭的操作交给model层
		return str;
	}
	public String query(String sql,String []paras)
	{
		SqlHelper sh=new SqlHelper();
		String str="不知道";
		try {			
			ResultSet rs=sh.query(sql,paras);
			if(rs.next()){
				str=rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}finally{
			sh.sqlClose();
		}
		//这里没有马上关闭连接，否则无法返回结果集
		//关闭的操作交给model层
		return str;
	}


	
}
