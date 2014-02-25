/**
 * ���ߣ�������
 * ʱ�䣺1012.1.26/21:09
 * ���ܣ��ý��潫��ɵĹ��ܺܶ�
 * ��������ϵͳ�Ĺ��ܶ�����������һ����
 * ��־��11/27:16:27ImagePanel image����������治�ܳ�ʼ��������������ImagePanel image=null;
 *
 */
package com.FixedWaterSystem.View;
import com.FixedWaterSystem.Model.UserModel;
import com.FixedWaterSystem.Tools.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

import com.FixedWaterSystem.Tools.*;

public class Window1 extends JFrame implements ActionListener,MouseListener{
	//ȫ����ƾ�̬����
	public static int right=1;
	//public static String name="";
	//������Ҫ�����
	JPanel p1,p2,p2north,p2south,p3west,p3east,p3;
	//p1:����logo
	//p2:��߲���ѡ�����
	//p3���ұ߽�����
	//p4:����p2��p3�Ĳ�����
	JMenuBar jmb;//�˵���
	JMenuItem jmi[]=new JMenuItem[10];//�˵���
	JMenu jm[]=new JMenu[2];
	JLabel p2_jl1,p2_jl2,p2_jl3,p2_jl4,p2_jl5,p2_jl6,p2_list;//�����˵�
	JLabel p3east_jl1,p3east_jl2,p3east_jl3,p3east_jl4;//��ɾ�Ĳ�
	JLabel p3west_left,p3west_right;//������ť
	JButton p3_jb1,p3_jb2,p3_jb3;
	JSplitPane jspane,jsp,jspforp2;
	//���ù��߰��е�ImagePanel��
	ImagePanel p1_bg,p2_bg,p3east_bg;
	
	Image p1_Image,p2_Image,p3_Image;
	Image titleIcon;
	//��������
	OrderManage om;
	ClientManager cm;
	UserManager um;
	EmpManager em;
	ProManager pm;	
	ProSellManager mm;
	
	OrderPane orderp;
	ClientPane clientp;
	UserPane userp;
	EmpPane empp;
	ProPane prop;
	ProSellPane prosellp;
	MonPane monp;
	
	DistributionManagement dm;
	//��Ƭ
	CardLayout cardp3west,cardp3east;
	JButton p1_desktop,p1_refresh,p1_exit;
	//�����۲�ģʽ�ı��
	static boolean mode=true;
	//p2south�����
	JLabel jlMode;
	JComboBox jcbMode;
	JButton jbMode;
	static String cardName=""; 
	//��ʼ���˵�
	public void initMenuBar(){
		jmb=new JMenuBar();//����һ���˵���
		//�����˵�
		jm[0]=new JMenu("ϵͳ����");
		jm[1]=new JMenu("����");
		/*
		jm[2]=new JMenu("����");
		jm[3]=new JMenu("����");
		jm[4]=new JMenu("����");
		jm[5]=new JMenu("����");
		jm[6]=new JMenu("����");
		jm[7]=new JMenu("����");
		jm[8]=new JMenu("����");
		jm[9]=new JMenu("����");
		*/
	
		//�����˵���
		jmi[0]=new JMenuItem("����");
		jmi[1]=new JMenuItem("����");
		jmi[2]=new JMenuItem("����");
		jmi[3]=new JMenuItem("����");
		jmi[4]=new JMenuItem("����");
		jmi[5]=new JMenuItem("����");
		jmi[6]=new JMenuItem("����");
		jmi[7]=new JMenuItem("����");
		jmi[8]=new JMenuItem("����");
		jmi[9]=new JMenuItem("����");

		//���
		for(int i=0;i<jm.length;i++)
		{
			jmb.add(jm[i]);
			jm[i].setFont(Word.f4);
		}
		
		for(int i=0;i<jmi.length;i++)
		{
			jm[0].add(jmi[i]);
			jmi[i].setFont(Word.f4);
		}
		
		
		//���˵����������
		this.setJMenuBar(jmb);
		
	}

	//��ʼ��p1��logo���ĺ���
	public void initLogo()
	{
		//�������壨Logo��
		//�������
		p1=new JPanel(null);
		p1.setBackground(getBackground());
		
		try {
			 p1_Image=ImageIO.read(new File("images/logos1.jpg"));//�õ�ͼƬ
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.p1_bg=new ImagePanel(p1_Image);//�õ��������
		this.p1_bg.setLayout(null);
		//�������
		p1_desktop=new JButton(new ImageIcon("images/returntodesktop.jpg"));
		p1_refresh=new JButton(new ImageIcon("images/refresh.jpg"));
		p1_exit=new JButton(new ImageIcon("images/tuichu.jpg"));
		//���ô�С
		p1_desktop.setBounds(760, 10, 40, 40);
		p1_refresh.setBounds(830,10,40,40);
		p1_exit.setBounds(900,10,40,40);
		//ע�����
		p1_desktop.addActionListener(this);
		p1_refresh.addActionListener(this);
		p1_exit.addActionListener(this);
		//���
		/*
		p1_bg.add(p1_desktop);
		p1_bg.add(p1_refresh);
		p1_bg.add(p1_exit);
		*/		
		p1.setLayout(new BorderLayout());
		p1.add(p1_bg);
	}
	
	//��ʼ����ߵ���壨�˵�ѡ�񣩵ĺ���
	public void initMenu()
	{
		//��ߵ����
		//�������
		p2=new JPanel(new BorderLayout());//��p2����Ϊ�߽粼��
		p2north=new JPanel(new GridLayout());//��װ�����˵�
		p2south=new JPanel(null);//���ɿղ���
		//�������
		try {
			p2_Image=ImageIO.read(new File("images/left_bg.jpg"));//�õ�ͼƬ
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.p2_bg=new ImagePanel(p2_Image);
		this.p2_bg.setLayout(new GridLayout(7,1));//���ñ���������񲼾�
		
		p2_jl1=new JLabel("��������",new ImageIcon("images/p2_jl1.jpg"),0);
		p2_jl2=new JLabel("�ͻ�����",new ImageIcon("images/p2_jl2.jpg"),0);
		p2_jl3=new JLabel("�û�����",new ImageIcon("images/p2_jl3.jpg"),0);
		p2_jl4=new JLabel("��Ա����",new ImageIcon("images/p2_jl4.jpg"),0);
		p2_jl5=new JLabel("��Ʒ����",new ImageIcon("images/p2_jl5.jpg"),0);
		p2_jl6=new JLabel("������¼",new ImageIcon("images/p2_jl6.jpg"),0);
		p2_list=new JLabel(new ImageIcon("images/systemmanage.jpg"));
		//p2_list.setBackground(Color.DARK_GRAY);
		
		//����label����ѡ��
		p2_jl1.setEnabled(false);
		p2_jl2.setEnabled(false);
		p2_jl3.setEnabled(false);
		p2_jl4.setEnabled(false);
		p2_jl5.setEnabled(false);
		p2_jl6.setEnabled(false);
		//p2_list.setEnabled(false);
		
		//ע�����
		p2_jl1.addMouseListener(this);
		p2_jl2.addMouseListener(this);
		p2_jl3.addMouseListener(this);
		p2_jl4.addMouseListener(this);
		p2_jl5.addMouseListener(this);
		p2_jl6.addMouseListener(this);
		
		//�Ա�ǩ��������
		p2_jl1.setFont(Word.f1);
		p2_jl2.setFont(Word.f1);
		p2_jl3.setFont(Word.f1);
		p2_jl4.setFont(Word.f1);
		p2_jl5.setFont(Word.f1);
		p2_jl6.setFont(Word.f1);
		
		//�Ա�ǩ��������
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
		p2_jl1.setCursor(myCursor);
		p2_jl2.setCursor(myCursor);
		p2_jl1.setCursor(myCursor);
		p2_jl4.setCursor(myCursor);
		p2_jl5.setCursor(myCursor);
		p2_jl6.setCursor(myCursor);
		
		//���
		//����ǩ����ͼƬ�����
		p2_bg.add(p2_list);
		switch(right){
		case 1://ϵͳ����Ա
		case 2://����			
			p2_bg.add(p2_jl1);
			p2_bg.add(p2_jl2);
			p2_bg.add(p2_jl3);
			p2_bg.add(p2_jl4);
			p2_bg.add(p2_jl5);
			p2_bg.add(p2_jl6);
			break;
		case 3://���
			p2_bg.add(p2_jl1);
			p2_bg.add(p2_jl4);
			p2_bg.add(p2_jl5);
			p2_bg.add(p2_jl6);
			break;
		case 4://�ֿ����Ա
			break;
		case 5://����Ա
			break;
			default:
			
		}
		
	
		
		//p2south�����
		jlMode=new JLabel("��ǰģʽ��");
		jlMode.setFont(Word.f3);
		jlMode.setBackground(Color.gray);
		jcbMode=new JComboBox(new String[]{"���","�༭"});
		jcbMode.setFont(Word.f3);
		jbMode=new JButton("Ӧ��");
		jbMode.setFont(Word.f3);
		jbMode.setBackground(Color.gray);
				
		jlMode.setBounds((int)(10/SystemFigure.ResoFig), (int)(50/SystemFigure.ResoFig), (int)(80/SystemFigure.ResoFig), (int)(25/SystemFigure.ResoFig));
		jcbMode.setBounds((int)(100/SystemFigure.ResoFig), (int)(50/SystemFigure.ResoFig), (int)(80/SystemFigure.ResoFig), (int)(25/SystemFigure.ResoFig));
		jbMode.setBounds((int)(100/SystemFigure.ResoFig), (int)(100/SystemFigure.ResoFig), (int)(80/SystemFigure.ResoFig), (int)(25/SystemFigure.ResoFig));
		
		jbMode.addActionListener(this);//ע�����
	
		p2south.add(jlMode);
		p2south.add(jcbMode);
		p2south.add(jbMode);
		
		//��ͼƬ�����뵽p2��������
		p2north.add(p2_bg);
		//����һ����ִ�
		jspforp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,p2north,p2south);
		//ָ����ߵ����ռ����
		jspforp2.setDividerLocation((int)(300/SystemFigure.ResoFig));
		//ȡ���߽���
		jspforp2.setDividerSize(0);
		p2.add(jspforp2,"Center");				
	}
	
	
	//��ʼ�����(�ұ�)��壨p3��
	public void initResult()
	{
		//�������
		this.cardp3west=new CardLayout();//��Ƭ���֣��������л�
		this.cardp3east=new CardLayout();
		
		p3west=new JPanel(this.cardp3west);
		p3east=new JPanel(this.cardp3east);
		
		p3=new JPanel(new BorderLayout());//����p3west��p3east
		
		//�õ�ͼƬ
		p3west_left=new JLabel(new ImageIcon("images/leftArray.jpg"));
		p3west_right=new JLabel(new ImageIcon("images/rightArray.jpg"));
		
		//Ϊ������ťע�����
		p3west_left.addMouseListener(this);
		p3west_right.addMouseListener(this);
		
		p3west.add(p3west_left,"0");
		cardName="0";
		p3west.add(p3west_right,"1");
		
		om=new OrderManage();
		cm=new ClientManager();
		um=new UserManager();
		em=new EmpManager();
		pm=new ProManager();		
		mm=new ProSellManager();
		
		orderp=new OrderPane();
		clientp=new ClientPane();
		userp=new UserPane();
		empp=new EmpPane();
		prop=new ProPane();
		prosellp=new ProSellPane();
		monp=new MonPane();
		
		p3east.add(om,"0");
		p3east.add(cm,"1");
		p3east.add(um,"2");
		p3east.add(em,"3");
		p3east.add(pm,"4");
		p3east.add(mm,"5");
		
		p3east.add(orderp,"6");
		p3east.add(clientp,"7");
		p3east.add(userp,"8");
		p3east.add(empp,"9");
		p3east.add(prop,"10");
		p3east.add(prosellp,"11");
		
		p3.add(p3west,"West");
		p3.add(p3east,"Center");
		//��һ����ִ��ڴ��p2��p3
		jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,p2,p3);
		//ָ����ߵ����ռ����
		jsp.setDividerLocation((int)(180/SystemFigure.ResoFig));
		//ȡ���߽���
		jsp.setDividerSize(0);
		
	}
	//���캯��
	public Window1(String usergrade,String username)
	{
		this.right=Integer.parseInt(usergrade);
		Word.username=username;
		//ͼ��
		try {
			titleIcon=ImageIO.read(new File("images/p2_jl5.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//���ó�ʼ��������ʼ������
		this.initMenuBar();
		this.initLogo();
		this.initMenu();
		this.initResult();
		
		//��һ��JTabbedPane
		JTabbedPane jtp=new JTabbedPane();		
		Home home=new Home(username);
		jtp.addTab("��ҳ",new ImageIcon("images/returntodesktop.jpg"),home);
		jtp.setFont(Word.f2);
		if(right<4){
		jtp.addTab("��ѯ����",new ImageIcon("images/data.png"),jsp);
		}
		
		jtp.setFont(Word.f4);
		
		//��һ����ִ��ڴ��p1��jtp	
		jspane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,p1,jtp);
		//ָ���ϱߵ����ռ����
		jspane.setDividerLocation(65);
		//ȡ���߽���
		jspane.setDividerSize(0);
		
		//��JFrame�л��container
		Container ct=this.getContentPane();
		//ct.add(p1,"North");
		ct.add(jspane,"Center");		
		//���ô���
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		//�رմ���ʱ�˳�ϵͳ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setUndecorated(true);//��ʹ�ñ߿�
		this.setLocation((int)((w/2-500)/SystemFigure.ResoFig),0);
		this.setIconImage(titleIcon);
		this.setSize((int)((1200/SystemFigure.ResoFig)),h-50);
		this.setVisible(true);
	}
	
	public static void main(String []args){
		Window1 w1=new Window1("2","neo");
	}
	
	//��Ӧ����
	public void actionPerformed(ActionEvent e) {
		int move=0;
		if(e.getSource()==p1_desktop){
			this.cardp3east.show(p3east,"0");
		}else if(e.getSource()==p1_exit){
			System.exit(0);
		}else if(e.getSource()==jbMode){
			int intCardName=Integer.parseInt(cardName);
			if(jcbMode.getSelectedItem().equals("���")&&!mode){//��ӱ༭ģʽתΪ���ģʽ
				mode=true;
				move=-6;
			}else if(jcbMode.getSelectedItem().equals("�༭")&&mode){//������ģʽתΪ�༭ģʽ
				mode=false;
				move=6;
			}else{
				JOptionPane.showMessageDialog(this,"��ѡ���ģʽ��");
			}
			//���±���
			cardName=String.valueOf(intCardName+=move);
			cardp3east.show(p3east,cardName);			
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==p2_jl1){
			
			if(mode){
				cardName="0";
			}else{
				cardName="6";
			}			
			this.cardp3east.show(p3east, cardName);
			this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p2_jl2){
				if(mode){
					cardName="1";
				}else{
					cardName="7";
				}			
				this.cardp3east.show(p3east, cardName);
				this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p2_jl3){
			if(mode){
				cardName="2";
			}else{
				cardName="8";
			}			
			this.cardp3east.show(p3east, cardName);
			this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p2_jl4){
			if(mode){
				cardName="3";
			}else{
				cardName="9";
			}			
			this.cardp3east.show(p3east, cardName);
			this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p2_jl5){
			if(mode){
				cardName="4";
			}else{
				cardName="10";
			}			
			this.cardp3east.show(p3east, cardName);
			this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p2_jl6){
			if(mode){
				cardName="5";
			}else{
				cardName="11";
			}			
			this.cardp3east.show(p3east, cardName);
			this.p2_jl1.setBackground(Color.BLACK);
		}else if(e.getSource()==p3west_left){
			this.jsp.setDividerLocation(0);//��������
			this.cardp3west.show(p3west, "1");
		}else if(e.getSource()==p3west_right){
			this.cardp3west.show(p3west, "0");
			cardName="0";
			this.jsp.setDividerLocation((int)(180/SystemFigure.ResoFig));//��������
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
		if(e.getSource()==p2_jl1)
		{
			p2_jl1.setEnabled(true);
		}else if(e.getSource()==p2_jl2)
		{
			p2_jl2.setEnabled(true);
		}else if(e.getSource()==p2_jl3)
		{
			p2_jl3.setEnabled(true);
		}else if(e.getSource()==p2_jl4) 
		{
			p2_jl4.setEnabled(true);
		}else if(e.getSource()==p2_jl5)
		{
			p2_jl5.setEnabled(true);
		}else if(e.getSource()==p2_jl6)
		{
			p2_jl6.setEnabled(true);
		}
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==p2_jl1)
		{
			p2_jl1.setEnabled(false);
		}else if(e.getSource()==p2_jl2)
		{
			p2_jl2.setEnabled(false);
		}else if(e.getSource()==p2_jl3)
		{
			p2_jl3.setEnabled(false);
		}else if(e.getSource()==p2_jl4)
		{
			p2_jl4.setEnabled(false);
		}else if(e.getSource()==p2_jl5)
		{
			p2_jl5.setEnabled(false);
		}else if(e.getSource()==p2_jl6)
		{
			p2_jl6.setEnabled(false);
		}
	}

}
