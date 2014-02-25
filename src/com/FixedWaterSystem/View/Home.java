/*ʱ�䣺2013.2.26
 * ���ܣ�ϵͳ��home���棬����ϵͳ����ɫ���ѿ����Ĺ��ܷ�������
 * ��־�������ò�ֲ���ʱ�����ֱ���ȥ�Դ�Ƕ�׵Ĳ�ֲ���������ǳ����ޣ�����Ӵ�С�������嵽�ֲ�һ��һ����������Ч��Ī������
 *���һ��panel��Ƕ�ײ�ֲ��֣���ô���panel����ΪBorderLayout()���һЩ�������õĻ����˺ܶ�Ī������Ĵ���
 * ����һЩĪ������Ĵ���ʱ���������������û���⣬������eclipse�����⣬��ʵ�ǿ϶����������⣬����Ӧ����ϸһ��
 * GridLayout���޷���ȫ����Ҫ�����˴��㽫�任λ��������GridBagLayout����������������൱�ĸ��ӣ�
 * */
package com.FixedWaterSystem.View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.FixedWaterSystem.Model.OrderModel;
import com.FixedWaterSystem.Tools.*;
public class Home extends JPanel implements ActionListener,MouseListener{

	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Home(username);
	}*/
	//Color arrColor[]=new Color[]{Color.blue,Color.black,Color.red,Color.yellow,Color.cyan};
	//���
	JLabel jb1,jb2,jb3,jb4;
	JPanel jp1,jp2,jp3,jp23,jpHome,jpCard;
	JSplitPane jsp1,jsp2;
	//ImagePanel p2_bg;
	Image timebg;
	Image[] p2_Image=new Image[Warehouse.imageFile.length];
	ImagePanel[] p2_bg=new ImagePanel[Warehouse.imageFile.length];
	JLabel clientHead,clientName,timeNow;
	Timer p1_timer;
	String username="";
	CardLayout card=new CardLayout();
	int intImg=1;
	//���캯��
	public Home(String username){
		
		this.username=username;
		
		jp1=new JPanel(new BorderLayout());
		jp2=new JPanel(null);
		jp3=new JPanel(null);
		jp3.setBackground(Color.gray);
		jpHome=new JPanel(new BorderLayout());
		//�������
		jp23=new JPanel(new BorderLayout());
		jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp1,jp23);
		jpHome.add(jsp1);
		jsp1.setDividerLocation((int)(50/SystemFigure.ResoFig));
		jsp1.setDividerSize(0);
		
		jsp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp2,jp3);
		jp23.add(jsp2);
		jsp2.setDividerLocation((int)(625/SystemFigure.ResoFig));
		jsp2.setDividerSize(0);
		this.initJp1();
		this.initJp2();
		//������ʱ������ť
		jb1=new JLabel("����",new ImageIcon("images/Billing.jpg"),0);
		jb2=new JLabel("ע��",new ImageIcon("images/clientR.jpg"),0);
		jb3=new JLabel("�˳�",new ImageIcon("images/tuichu.jpg"),0);
		jb4=new JLabel(new 	ImageIcon("images/book.png"));
		
		jb4.setBounds((int)(1110/SystemFigure.ResoFig), (int)(570/SystemFigure.ResoFig), 36, 36);
		jb1.setBounds((int)(900/SystemFigure.ResoFig), (int)(250/SystemFigure.ResoFig), (int)(136/SystemFigure.ResoFig), (int)(36/SystemFigure.ResoFig));
		jb2.setBounds((int)(900/SystemFigure.ResoFig), (int)(300/SystemFigure.ResoFig), (int)(136/SystemFigure.ResoFig), (int)(36/SystemFigure.ResoFig));
		jb3.setBounds((int)(900/SystemFigure.ResoFig), (int)(350/SystemFigure.ResoFig), (int)(136/SystemFigure.ResoFig), (int)(36/SystemFigure.ResoFig));
		
		jb1.setFont(Word.f5);
		jb2.setFont(Word.f5);
		jb3.setFont(Word.f5);
		
		jb1.setEnabled(false);
		jb2.setEnabled(false);
		jb3.setEnabled(false);
		jb4.setEnabled(false);
		
		jb1.addMouseListener(this);
		jb2.addMouseListener(this);
		jb3.addMouseListener(this);
		jb4.addMouseListener(this);
		//���ÿղ���
		jp2.add(jb1);
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		this.setLayout(new BorderLayout());
		//������
		this.add(jpHome);
		//this.add(jb2);
	}
	public void initJp1(){
		//jpState=new JPanel(null);								
		clientHead=new JLabel(new ImageIcon("images/p2_jl4.jpg"));
		clientName=new JLabel("��ӭ�㣬"+username+"��");
		clientName.setFont(Word.f5);
		
		//��������
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
		clientHead.setCursor(myCursor);				
		
		//���jpState�����
		p1_timer=new Timer(1000,this);//ÿ��һ�봥��һ��ActionEvent
		p1_timer.start();//��ʼ�߳�
		//��õ�ǰʱ��
		timeNow=new JLabel("��ǰʱ�䣺 "+Calendar.getInstance().getTime().toLocaleString()+"   ",new ImageIcon("images/Time.jpg"),0);//��ʱ�䱾�ػ�
		timeNow.setFont(Word.f3);
		//Ϊ��ʾʱ���JPanel���ñ���
		try {
			timebg=ImageIO.read(new File("images/timebg.jpg"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JLabel CurrrentClient=new JLabel("��ǰ�ͻ���");
		ImagePanel timeIP=new ImagePanel(timebg);//��timeBg��������һ�����
		timeIP.setLayout(new BorderLayout());
		timeIP.add(timeNow);//��������������ʱ����ʾ		
		JPanel jpCustomerState=new JPanel(new FlowLayout(FlowLayout.LEFT));
		jpCustomerState.add(clientHead);
		jpCustomerState.add(clientName);
		jp1.add(timeIP,"East");//��jpState�������ϼ�һ������ʱ��ı������
		jp1.add(jpCustomerState,"West");
		//jpState.add(CurrrentClient);
		
	}
	public void initJp2(){
		//�������壨Logo��
		//�������	
		jpCard=new JPanel(card);
		try {
			for(int i=0;i<p2_Image.length;i++){
				 p2_Image[i]=ImageIO.read(Warehouse.imageFile[i]).getScaledInstance(700, 542, 1);//�õ�ͼƬ
				 
				 p2_bg[i]=new ImagePanel(p2_Image[i]);
				 p2_bg[i].setLayout(null);
				jpCard.add(p2_bg[i],String.valueOf(i));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpCard.setBounds(0, 0, 700, 542);
		//�������	
		jp2.add(jpCard);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.timeNow.setText("��ǰʱ�䣺 "+Calendar.getInstance().getTime().toLocaleString()+"   ");
		
	}
	


	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			BillingPane bp=new BillingPane();
		}else if(e.getSource()==jb2){
			CusRegPane crp=new CusRegPane();
		}else if(e.getSource()==jb3){
			System.exit(0);
		}else if(e.getSource()==jb4){
			intImg++;
			card.show(jpCard,String.valueOf(intImg%Warehouse.imageFile.length));
		}	
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			jb1.setEnabled(true);
		}else if(e.getSource()==jb2){
			jb2.setEnabled(true);
		}else if(e.getSource()==jb3){
			jb3.setEnabled(true);
		}else if(e.getSource()==jb4){
			jb4.setEnabled(true);
		}			
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			jb1.setEnabled(false);
		}else if(e.getSource()==jb2){
			jb2.setEnabled(false);
		}else if(e.getSource()==jb3){
			jb3.setEnabled(false);
		}else if(e.getSource()==jb4){
			jb4.setEnabled(false);
		}
	}
}