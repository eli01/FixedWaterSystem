//������ģ�͸����������ṩ���ݲ���֧��
package com.FixedWaterSystem.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.FixedWaterSystem.DateBase.*;
public class BillingModel {
	//���캯��

	//���Ӽ�������
	public boolean update(String sql){
		SqlHelper sh=new SqlHelper();
		return sh.update(sql);
	}
	//����update()�������
	public boolean update(String sql,String []paras){
		SqlHelper sqlhelper=new SqlHelper();
		return sqlhelper.update(sql, paras);
	}
	//���ڲ�ѯ�ĺ������᷵�ز�ѯ�Ľ����
	public String query(String sql)
	{
		String str="��֪��";
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
		//����û�����Ϲر����ӣ������޷����ؽ����
		//�رյĲ�������model��
		return str;
	}
	public String query(String sql,String []paras)
	{
		SqlHelper sh=new SqlHelper();
		String str="��֪��";
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
		//����û�����Ϲر����ӣ������޷����ؽ����
		//�رյĲ�������model��
		return str;
	}


	
}
