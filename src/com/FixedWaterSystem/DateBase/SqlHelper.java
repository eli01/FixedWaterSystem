/**
 * ���ߣ�������
 * ʱ�䣺2012.11.26/13:30
 * ���ܣ��ڵײ���ɶ����ݿ�Ĳ���
 * ��־�����Ǽ򵥵���ɾ�Ĳ飻����Ľ������Ӹ��ӵĹ���
 * 11.29��д��query
 */
package com.FixedWaterSystem.DateBase;
import java.sql.*;

import javax.swing.JOptionPane;
public class SqlHelper {

	//������Ҫ�Ķ���
	PreparedStatement ps=null;//����sql���Ľӿ�//http://www.hudong.com/wiki/preparedstatement?hf=youdaocitiao&pf=youdaocitiao
	ResultSet rs=null;//�����
	Connection ct=null;
	//����sql sever������
	String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://127.0.0.1:1433;databaseName=FixedWaterSystem";
	String user="sa";
	String passwd="truman";
	//������Ϣsql���
	//String sqlBasic="select ID,Name,Age,Sex,Address,QQNum,PhoneNum from FixedWaterSystem";
	//��ϸ��Ϣ
	//String sqlDetail="select * from FixedWaterSystem";
	//�޸����
	//String sqlUpdate="update";
	
	//���캯��
	public SqlHelper()
	{
		try {
			Class.forName(driver);//��������
			ct=DriverManager.getConnection(url,user,passwd);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���������쳣��");
			//JOptionPane.showMessageDialog(this,"���������쳣");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���������쳣��");
		}
	}
	//���ڲ�ѯ�ĺ���������Ҫparas[]��
	public ResultSet query(String sql)
	{
		try {
			System.out.println(sql);
			//����ע�뷽ʽ
			 //��ƽ����Ϊ�ɹ���
			//ps=ct.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=ct.prepareStatement(sql);
			//��sql��丳ֵ
			System.out.println("��һ��ִ��");
			rs=ps.executeQuery();//��ɲ�ѯ��������������ظ������
			System.out.println("query��Strng���������");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��sqlԤ����ʱ���ִ���");
		}
		//����û�����Ϲر����ӣ������޷����ؽ����
		//�رյĲ�������model��
		return rs;
	}
	
	
	
	//���ڲ�ѯ�ĺ������᷵�ز�ѯ�Ľ����
	public ResultSet query(String sql,String []paras)
	{
		try {
			//����ע�뷽ʽ
			System.out.println(sql+" "+paras[0]);
			ps=ct.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();//��ɲ�ѯ��������������ظ������
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��sqlԤ����ʱ���ִ���");
		}
		//����û�����Ϲر����ӣ������޷����ؽ����
		//�رյĲ�������model��
		return rs;
	}
	
	//��������ɾ���ĵĺ��������ز����Ƿ�ɹ���
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
			System.out.println("��sqlԤ����ʱ���ִ���");
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
			System.out.println("��sqlԤ����ʱ���ִ���");
		}finally{
			this.sqlClose();
		}
		return isOK;
	}
	
	//�رղ���
	public void sqlClose()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();

			if(ct!=null) ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�ر���Դ�쳣��");
		}	
	}	
}
