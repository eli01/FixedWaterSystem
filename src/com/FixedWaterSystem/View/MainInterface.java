/**
 * ���ߣ�������
 * ʱ�䣺2012.12.1/22.13
 * ���ܣ������ı�ԭ����ģʽ�������ģʽ�����ش��ڣ�����һ��ѡ�ģʽ��
 * ��־�����ڵļƻ��������ģ�����ܿ��ܻ���ģ���Ϊ��ӭ�ϲ�ͬ�û���ʹ��ϰ�ߣ������в���ƽ̨���ֱ��ǲ˵�����������塢ѡ���������Ϊѡ����������
 * 22:18����11��ģ�飬���ſ����Ľ�չ��һ��һ��������
 * 12.2:����Ҫ�޸ģ���Ϊ���ڵ�����ǽ����������Ӧ����һ����Ƭ���֣���ѡ����ֺ���һ��ͬ�����µķ����ǲ˵����ͽ��������Ϊ��Ƭ���ֵ���ģ��
 * ���ѡ���Ϊ��ʹ��
 */
package com.FixedWaterSystem.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainInterface extends JPanel{
	//������һ��������ڷ������Ч��
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainInterface();
	}
	
	//���
	JTabbedPane jtp;//ѡ�
	JPanel jp[]=new JPanel[1];//ѡ�����
	JLabel jl[];//ÿ��ѡ��ı�ǩ
	public MainInterface()
	{
		//��ʼ�������ڣ���ʵû�к��潫����ѡ���JPanel��,������ʲô��û��
		JTabbedPane jtp=new JTabbedPane();
		//��orderManage��Ϊһ��ѡ�����
		OrderManage om=new OrderManage();
		//��CustormerManage��Ϊһ��ѡ�����
		CustormerManage cm=new CustormerManage();
		//��ӵ�ѡ�
		jtp.add("�˿͹���",om);
		jtp.add("��������",cm);
		
	}
}
