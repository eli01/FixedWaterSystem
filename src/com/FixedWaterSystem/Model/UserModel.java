/**
 * ���ߣ�������
 * ʱ�䣺2012.11.26/18:47
 * ���ܣ����������ɶ��û���Ϣ�ĸ��ֲ������漰������
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
				position=rs.getString(1);//��ȡ��ְλ

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
				position=rs.getString(1);//��ȡ��ְλ

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sh.sqlClose();
		}
		return position;
		
	}

	//����½�ߵ������Ϣ������ְλ��Ϣ
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
			//System.out.println("��model�еõ���rs");
			if(rs.next())
			{	//System.out.println("^^^");
				String dbpasswd=rs.getString(1);//��ȡ������
				//System.out.println("���ݿ��е����룺"+dbpasswd);
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
