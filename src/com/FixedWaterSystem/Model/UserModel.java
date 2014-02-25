/**
 * 作者：孟庆申
 * 时间：2012.11.26/18:47
 * 功能：用来间接完成对用户信息的各种操作（涉及两个表）
 */
package com.FixedWaterSystem.Model;

import com.FixedWaterSystem.DateBase.SqlHelper;
import java.sql.*;
public class UserModel {

	/**
	 * @param args
	 */
	public String getName(String userid){
		String position="";
		SqlHelper sh=null;
		//String sqlCheck="select empInfo.Position from userLogin,empInfo where userLogin.ID=empInfo.ID " +
				//"and userLogin.ID=? and userLogin.Password=?";
		String sqlCheck="select username from users where userid=?";
		try {
			String paras[]={userid};
			sh=new SqlHelper();
			ResultSet rs=sh.query(sqlCheck, paras);
			if(rs.next())
			{
				position=rs.getString(1);//则取出职位

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sh.sqlClose();
		}
		return position;
		
	}


	public String getContent(String userid,String sqlCheck){
		
		String position="";
		SqlHelper sh=null;
		//String sqlCheck="select empInfo.Position from userLogin,empInfo where userLogin.ID=empInfo.ID " +
				//"and userLogin.ID=? and userLogin.Password=?";
		//String sqlCheck="select userjob from users where userid=?";
		try {
			String paras[]={userid};
			sh=new SqlHelper();
			ResultSet rs=sh.query(sqlCheck, paras);
			if(rs.next())
			{
				position=rs.getString(1);//则取出职位

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sh.sqlClose();
		}
		return position;
		
	}

	//检查登陆者的身份信息，返回职位信息
	public boolean checkUser(String id,String passwd)
	{
		System.out.println(id+" "+passwd);
		boolean b=false;
		SqlHelper sh=null;
		//String sqlCheck="select empInfo.Position from userLogin,empInfo where userLogin.ID=empInfo.ID " +
				//"and userLogin.ID=? and userLogin.Password=?";
		//String sqlCheck="select userpasswd from users where userid=?";
		String sqlCheck="select userpasswd from users where userid='"+id+"'";
		try {
			String paras[]={id};
			sh=new SqlHelper();
			//ResultSet rs=sh.query(sqlCheck, paras);
			ResultSet rs=sh.query(sqlCheck);
			//System.out.println("在model中得到了rs");
			if(rs.next())
			{	//System.out.println("^^^");
				String dbpasswd=rs.getString(1);//则取出密码
				//System.out.println("数据库中的密码："+dbpasswd);
				if(dbpasswd.equals(passwd)){
					b=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sh.sqlClose();
		}
		return b;
	}
}
