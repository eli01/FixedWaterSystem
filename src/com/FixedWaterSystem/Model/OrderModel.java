/**
 * ���ߣ�������
 * ʱ�䣺2012.11.28/11:17
 * ���ܣ�
 * ��־�����������һЩ���⣬Ϊ��ʵ��������дquery�������������˴��븴�ýϸߵ������δ�����
 */
package com.FixedWaterSystem.Model;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;

import com.FixedWaterSystem.DateBase.*;
public class OrderModel extends AbstractTableModel{
	
	Vector<String> colums;//����ȡ������Vector
	Vector<Vector> rows;//����ȡÿһ�����ݵ�Vector�����Կ���һ����λ�Ķ�̬���飩
	
	//������ɾ�ĵĺ���
	public boolean update(String sql,String []paras){
		SqlHelper sqlhelper=new SqlHelper();
		return sqlhelper.update(sql, paras);
	}
	//����ǲ���paras[]�����ķ���
	public void query(String sql)
	{
		//��ʼ������Vector
		this.colums=new Vector<String>();
		//��ʼ����������
		this.rows=new Vector<Vector>();
		//�����ݿ���ȡ������
		SqlHelper sqlhelper=new SqlHelper();
		ResultSet rs=sqlhelper.query(sql);
		//���õ������ݴ���colums��rows
		try {
			//ͨ��rsmt���󲻵��ܵõ����������ܵõ���Ӧ������
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
			//Ϊ��ȡ���ݣ�û����query()�йر���Դ��Ϊ����������һ��ر�֮
			sqlhelper.sqlClose();
		}
	}
	//���ڲ�ѯ��ʾ��Ϣ�ķ���,û�з���ֵ����ѯ�Ľ���Ѿ�������Vector�У���������дAbstractTableModel���еķ�����ʵ�����ݵķ���
	public void query(String sql,String []paras)
	{
		//��ʼ������Vector
		this.colums=new Vector<String>();
		//��ʼ����������
		this.rows=new Vector<Vector>();
		//�����ݿ���ȡ������
		SqlHelper sqlhelper=new SqlHelper();
		ResultSet rs=sqlhelper.query(sql, paras);
		//���õ������ݴ���colums��rows
		try {
			//ͨ��rsmt���󲻵��ܵõ����������ܵõ���Ӧ������
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
			//Ϊ��ȡ���ݣ�û����query()�йر���Դ��Ϊ����������һ��ر�֮
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
		return (String)this.colums.get(column);//����õ��Ľ������String,����ǿ��ת��֮���������ݿ�Ĺ������������͵�ѡ�������
	}

}
