package com.FixedWaterSystem.View;
import com.FixedWaterSystem.Tools.*;
import com.FixedWaterSystem.DateBase.SqlHelper;
import com.FixedWaterSystem.Model.*;

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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.FixedWaterSystem.Model.OrderModel;
import com.FixedWaterSystem.Tools.ImagePanel;
import com.FixedWaterSystem.Tools.Word;
import com.FixedWaterSystem.View.OrderManage.AddView;

//------��һ���ڲ���--ͷ--------------
public class BillingPane extends JDialog implements ActionListener,MouseListener{
	public static void main(String[] args){
		new BillingPane();
	}
	//�����ڲ�����������24��
	JPanel jpLogo,jpState,jpBilling;		
	
	//logo����
	JButton jpLogo_desktop,jpLogo_exit;
	
	//state����
	
	
	//billing����
	Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
	JSplitPane jsp1,jsp2,jsp3,jsp4,jsp5;
	JPanel jpBillingLeft,jpBillingRight,jpContent,jp1,jp1West,jp1East,jp1WestWest,jp1WestCenter,jp1EastWest,jp1EastCenter,jp2,jp3,jp23;
	JLabel add,edit,delete,orderby,detail,search,cancel,save;
	JScrollPane jscrollpane;
	OrderModel order;
	JComboBox jcbForOrderby,jcbForOrderby2;
	JTable jtable;
	JScrollPane jsp;
	ProductModel pm;
	String sqlJP3="select PID ��Ʒ���,Pname ��Ʒ����,specification ����ͺ�,unitPrice ����,quantity ����,biscount �ۿ�,monerySum ���,Remarks ��ע from ProductShipping where OID=(select top 1 OID from orders where CID= ? order by OID desc)";
	//String sqlCall="select PID ��Ʒ���,Pname ��Ʒ����,specification ����ͺ�,unitPrice ����,quantity ����,biscount �ۿ�,monerySum ���,Remarks ��ע from ProductShipping where OID=(select top 1 OID from orders where CallNumber= ? order by OID desc)";
	//String sqlCname="select PID ��Ʒ���,Pname ��Ʒ����,specification ����ͺ�,unitPrice ����,quantity ����,biscount �ۿ�,monerySum ���,Remarks ��ע from ProductShipping where OID=(select top 1 OID from orders where CID=(select CID from client where CName=?) ? order by OID desc)";
	String sqlCall="select top 1 CID from client where Phone=?";
	String sqlCname="select top 1 CID from client where CName=?";
	String[] orderStr=new String[10];
	String newOID;
	String cid;
	String[] oids=new String[1];
	String sqlOrder="insert into orders values(?,?,?,?,?,?,?,?,?,?)";//����¶���
	BillingModel bm=new BillingModel();
	String sqlOID="select top 1 OID from orders order by OID desc";
	JLabel clientHead,clientName;
		
	//��ʼ��¼������ұ߲���
	JPanel jpRight1,jpRight2,jpRight3,jpRight23,jpRLogo,jpSearch;		
	JTabbedPane jtpRight;
	JSplitPane jspRight1,jspRight2,jspRight3;
	JTable jtOrder,jtClient;
	JLabel jlMob,jlNam,jlRLogo;
	JButton jbNam,jbMob;
	JTextField jtfMob,jtfNam;
	ProductModel orderModel;
	ProductModel clientModel;
	
	int rowNum;
	String sqlROrder="select OID ҵ�񵥺�,CID �ͻ�����,SendTime �ͻ�ʱ��,OmonerySum ���,Warehouse �ֿ� from orders order by SendTime desc";
	String sqlRClient="select CID �ͻ�����,CName �ͻ�����,ContactPerson ��ϵ��,Adress ��ַ,Phone �ֻ����� from client order by CID asc";
	//������ť����ǰ����û�ù���
	//JComboBox jcbForOrderby,jcbForOrderby2;
	String jcbArray[]={"��ʱ��","������","��������"};
	String jcbArray2[]={"��С����","�Ӵ�С"};
	
	String jcb1Str,jcb2Str;
	 
	Timer p1_timer;
	JLabel timeNow;
	Image timebg,p1_yes;
	 
	JTextField searchJTF;
	JLabel searchJL,refresh2;
	JButton searchJB;
	
	JLabel jlLogo=null;
	String[] jlabelString={"ҵ�񵥺�","�ͻ�����","��ϵ��ַ","��������","�ͻ�����","����Ƭ��","�ͻ���ע","����״̬","����Ա","�ͻ�����","�ͻ���","�ͻ���ʽ","�ͻ�����","��ϵ�绰","��ϵ��","�ͻ�����ʱ��","Ƭ������","�ͻ�����","�������","����ʱ��","�ͻ�ʱ��","�����ֿ�","��������","��ע"};
	JLabel[] jlabel=new JLabel[24];
	JTextField jtf[]=new JTextField[24];
	public BillingPane(){		
		//���ø������ֵĳ�ʼ������
		this.initLogo();
		this.initState();
		this.initBilling();
		this.initSearch();
		
		//----���²�ֵĹ��̺��й��ɣ�������־������˼������֣����ϵĴ���֤����������һ�����ˣ���ֻ�������������������----------
		
		//LOGO������
		jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpLogo,jpContent);
		jsp1.setDividerLocation((int)(54/SystemFigure.ResoFig));//ָ��logo�Ŀ��																	
		jsp1.setDividerSize(0);			
		
		//state����������logo��
		jsp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpState,jpBilling);
		jpContent.add(jsp2);
		jsp2.setDividerSize(0);
		jsp2.setDividerLocation((int)(40/SystemFigure.ResoFig));//ָ�����state�ĸ�
		
		//jpBillingLeft����������logo��state��
		jsp3=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jpBillingLeft,jpBillingRight);
		jpBilling.add(jsp3);			
		//jsp3.setLastDividerLocation(700);
		jsp3.setDividerSize(0);
		jsp3.setDividerLocation((int)(800/SystemFigure.ResoFig));//ָ����ߵ�
		
		//jp1����������logo��state��jpBillingLeft��
		jsp4=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp1,jp23);
		jpBillingLeft.add(jsp4);
		jsp4.setDividerLocation((int)(500/SystemFigure.ResoFig));
		jsp4.setDividerSize(0);
		
		//jp2����������logo��state��jpBillingLeftjp1��jp3��
		jsp5=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp2,jp3);
		jp23.add(jsp5);
		jsp5.setDividerLocation((int)(50/SystemFigure.ResoFig));
		jsp5.setDividerSize(0);
		
		//Ϊ�˶Կռ�������þ�����jp1�����
		
		//---------------------���ĩ---------------------------
		
		//���
		this.add(jsp1);
		//���ô���
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		//�رմ���ʱ�˳�ϵͳ
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//this.setUndecorated(true);//��ʹ�ñ߿�
		this.setLocation(w/2-501,h/2-350);
		this.setTitle("�����Ǽǽ���");
		this.setSize((int)(1400/SystemFigure.ResoFig),(int)(850/SystemFigure.ResoFig));
		//������ʾ
		this.setVisible(true);
	}
	
	//��ʼ���Ǽǽ����logo_panel
	public void initLogo(){
		Image jpLogo_Image=null;
		ImagePanel jpLogo_bg;

		//�������壨Logo��

		jpLogo=new JPanel(null);
		jpLogo.setBackground(getBackground());
		
		try {
			jpLogo_Image=ImageIO.read(new File("images/BillingLogo.jpg"));//�õ�ͼƬ
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpLogo_bg=new ImagePanel(jpLogo_Image);//�õ��������
		jpLogo_bg.setLayout(null);
		//�������
		jpLogo_desktop=new JButton(new ImageIcon("images/returntodesktop.jpg"));

		jpLogo_exit=new JButton(new ImageIcon("images/tuichu.jpg"));
		//���ô�С
		jpLogo_desktop.setBounds((int)(760/SystemFigure.ResoFig),(int)(5/SystemFigure.ResoFig), (int)(40/SystemFigure.ResoFig), (int)(40/SystemFigure.ResoFig));

		jpLogo_exit.setBounds((int)(900/SystemFigure.ResoFig),(int)(5/SystemFigure.ResoFig),(int)(40/SystemFigure.ResoFig),(int)(40/SystemFigure.ResoFig));
		//ע�����
		jpLogo_desktop.addActionListener(this);

		jpLogo_exit.addActionListener(this);
		//���
		/*
		jpLogo_bg.add(jpLogo_desktop);

		jpLogo_bg.add(jpLogo_exit);		
		*/						
		jpLogo.setLayout(new BorderLayout());
		jpLogo.add(jpLogo_bg);					
	}
	//��ʼ����ǰ�ͻ���Ϣ����ʱ��״̬��Ϣ��
	public void initState(){
		//jpState=new JPanel(null);
		jpState=new JPanel(new BorderLayout());
		
		searchJTF=new JTextField(10);		
		searchJL=new JLabel(new ImageIcon("images/search.jpg"));
		searchJTF.setBorder(BorderFactory.createLoweredBevelBorder());
		clientHead=new JLabel(new ImageIcon("images/head.jpg"));
		clientName=new JLabel("��ӭ�㣬"+Word.username+"��");
		//��������
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
		searchJL.setCursor(myCursor);
		clientHead.setCursor(myCursor);
		
		//���ü���
		searchJL.addMouseListener(this);
		clientHead.addMouseListener(this);
		
		
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
		jpCustomerState.add(searchJL);
		jpCustomerState.add(searchJTF);
		jpCustomerState.add(clientHead);
		jpCustomerState.add(clientName);
		jpState.add(timeIP,"East");//��jpState�������ϼ�һ������ʱ��ı������
		jpState.add(jpCustomerState,"West");
		//jpState.add(CurrrentClient);
		
	}
	public void initSearch(){
		//��߲��Ĳ���
		//�������
		jpRLogo=new JPanel(null);
		jpRLogo.setBackground(Color.gray);
		jpSearch=new JPanel(null);
		jtpRight=new JTabbedPane();
		jpRight1=new JPanel(new BorderLayout());
		jpRight2=new JPanel(new BorderLayout());
		jpRight3=new JPanel(new BorderLayout());
		jpRight23=new JPanel(new BorderLayout());
						
		orderModel=new ProductModel();
		clientModel=new ProductModel();
		
		orderModel.query(sqlROrder);
		clientModel.query(sqlRClient);
		
		jtOrder=new JTable(orderModel);
		jtClient=new JTable(clientModel);
		
		jlMob=new JLabel("�ֻ���",new ImageIcon("images/search.jpg"),0);
		jlNam=new JLabel("�ͻ���",new ImageIcon("images/search.jpg"),0);
		jlRLogo=new JLabel("��Ϣ��ѯ��");
		jlRLogo.setBackground(Color.white);
		
		jtfMob=new JTextField(20);
		jtfNam=new JTextField(20);
		
		jtfMob.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfNam.setBorder(BorderFactory.createLoweredBevelBorder());
		//�������������λ�úʹ�С
		jlRLogo.setBounds((int)(60/SystemFigure.ResoFig), (int)(5/SystemFigure.ResoFig), (int)(150/SystemFigure.ResoFig), (int)(30/SystemFigure.ResoFig));
		jtfMob.setBounds((int)(30/SystemFigure.ResoFig), (int)(60/SystemFigure.ResoFig), (int)(120/SystemFigure.ResoFig),(int)(20/SystemFigure.ResoFig));
		jtfNam.setBounds((int)(30/SystemFigure.ResoFig), (int)(30/SystemFigure.ResoFig), (int)(120/SystemFigure.ResoFig),(int)(20/SystemFigure.ResoFig));
		jlMob.setBounds((int)(160/SystemFigure.ResoFig), (int)(60/SystemFigure.ResoFig), (int)(100/SystemFigure.ResoFig), (int)(20/SystemFigure.ResoFig));
		jlNam.setBounds((int)(160/SystemFigure.ResoFig), (int)(30/SystemFigure.ResoFig), (int)(100/SystemFigure.ResoFig), (int)(20/SystemFigure.ResoFig));
		
		
		jlMob.setEnabled(false);
		jlNam.setEnabled(false);
		
		//��������
		jlRLogo.setFont(Word.f5);
		jtfNam.setFont(Word.f3);
		jtfMob.setFont(Word.f3);
		jlNam.setFont(Word.f3);
		jlMob.setFont(Word.f3);
		//��������
		
		jlNam.setCursor(myCursor);
		jlMob.setCursor(myCursor);
		//ע�����
		
		jlNam.addMouseListener(this);
		jlMob.addMouseListener(this);
		//������
		jtpRight.add(jtOrder,"����ͳ�Ʊ�");
		jtpRight.add(jtClient,"�ͻ�ͳ�Ʊ�");
		
		
		jpSearch.add(jtfNam);
		jpSearch.add(jlNam);
		jpSearch.add(jtfMob);
		jpSearch.add(jlMob);
		
		jpRLogo.add(jlRLogo);
		
		jpRight2.add(jtpRight);
		
		jspRight1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpRight1,jpRight23);
		jpBillingRight.add(jspRight1);
		jspRight1.setDividerLocation(150);
		jspRight1.setDividerSize(0);
		
		jspRight2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpRight2,jpRight3);
		jpRight23.add(jspRight2);
		jspRight2.setDividerLocation(300);
		jspRight2.setDividerSize(0);
		
		jspRight3=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpRLogo,jpSearch);
		jpRight1.add(jspRight3);
		jspRight3.setDividerLocation(70);
		jspRight3.setDividerSize(0);
	}

	public void initBilling(){

		jpContent=new JPanel(new BorderLayout());
		jpBilling=new JPanel(new BorderLayout());
		//��panel����Ƕ������panel���������ñ߽粼�֣�������������ֲ��ָ������һЩ
		jpBillingLeft=new JPanel(new BorderLayout());
		this.initJp1();			
		this.initJp2();
		this.initJp3();
		jp23=new JPanel(new BorderLayout());			
		jpBillingRight=new JPanel(new BorderLayout());
		jpBillingRight.setBackground(Color.gray);				
		
	}
	
	public void initJp1(){
		//��ʼ����ǩ���ı���
		for(int i=0;i<jlabelString.length;i++){
			jlabel[i]=new JLabel(jlabelString[i]);
			jlabel[i].setFont(Word.f4);
			jtf[i]=new JTextField(20);
			jtf[i].setBorder(BorderFactory.createLoweredBevelBorder());

		}
		
		//��ʼ��ĳЩ�ı���������	
		//�����ݿ���ȡ��
		BillingModel bm=new BillingModel();
		newOID=DataFactory.getID(bm.query(sqlOID),"O",true);
		jtf[0].setText(newOID);//��ȷʱ�䡢O��ͷ
		
		//jp1=new JPanel(new GridLayout(12,4,10,5));//��jp1���ó�Ϊ���񲼾֣������ǼǶ�����Ϣ	
		jp1=new JPanel(new BorderLayout(5,5));
		jp1West=new JPanel(new BorderLayout(10,10));
		jp1WestWest=new JPanel(new GridLayout(12,1,10,10));
		jp1WestCenter=new JPanel(new GridLayout(12,1,10,10));
		jp1East=new JPanel(new BorderLayout(5,5));
		jp1EastCenter=new JPanel(new GridLayout(12,1,10,10));
		jp1EastWest=new JPanel(new GridLayout(12,1,10,10));
		
		//jp1.setVisible(true);
		for(int i=0;i<12;i++){//��ӱ�ǩ��¼���ı���
			jp1WestWest.add(jlabel[i]);
			jp1WestCenter.add(jtf[i]);
			jp1EastWest.add(jlabel[i+12]);
			jp1EastCenter.add(jtf[i+12]);
		}
		//��jp1����panel����������
		jp1West.add(jp1WestWest,"West");
		jp1West.add(jp1WestCenter,"Center");
		jp1East.add(jp1EastWest,"West");
		jp1East.add(jp1EastCenter,"Center");
		
		jp1.add(jp1West,"West");
		jp1.add(jp1East,"East");
	}
	//��jp2���г�ʼ��
	public void initJp2(){
		jp2=new JPanel(new FlowLayout(FlowLayout.LEFT));			
		
		add=new JLabel("����",new ImageIcon("images/add.jpg"),0);
		cancel=new JLabel("ȡ��",new ImageIcon("images/cancel.jpg"),0);
		save=new JLabel("����",new ImageIcon("images/save.jpg"),0);
		edit=new JLabel("�༭",new ImageIcon("images/edit.jpg"),0);
		delete=new JLabel("ɾ��",new ImageIcon("images/delete.jpg"),0);
		jcbForOrderby=new JComboBox(jcbArray);//����һ�������б�
		jcbForOrderby2=new JComboBox(jcbArray2);//����һ�������б�

		//���ñ�ǩ����ʽ
		add.setBorder(BorderFactory.createRaisedBevelBorder());
		save.setBorder(BorderFactory.createRaisedBevelBorder());
		edit.setBorder(BorderFactory.createRaisedBevelBorder());
		cancel.setBorder(BorderFactory.createRaisedBevelBorder());
		delete.setBorder(BorderFactory.createRaisedBevelBorder());
		jcbForOrderby2.setBorder(BorderFactory.createRaisedBevelBorder());
		//��������
		add.setFont(Word.f3);
		save.setFont(Word.f3);
		edit.setFont(Word.f3);
		cancel.setFont(Word.f3);
		delete.setFont(Word.f3);
		//jcbForOrderby.setFont(Word.f3);
		jcbForOrderby2.setFont(Word.f3);
		
		//��������
		
		add.setCursor(myCursor);
		save.setCursor(myCursor);
		edit.setCursor(myCursor);
		cancel.setCursor(myCursor);
		delete.setCursor(myCursor);
		searchJL.setCursor(myCursor);
		//refresh2.setCursor(myCursor);
		
		//���ø���״̬
		add.setEnabled(false);
		save.setEnabled(false);
		edit.setEnabled(false);
		cancel.setEnabled(false);
		delete.setEnabled(false);
		searchJL.setEnabled(false);
		
		//���ü���
		add.addMouseListener(this);
		save.addMouseListener(this);
		edit.addMouseListener(this);
		cancel.addMouseListener(this);
		delete.addMouseListener(this);
		searchJL.addMouseListener(this);
		//refresh2.addMouseListener(this);
		
		//���p3�����
		//jp2.add(refresh2);
		jp2.add(add);
		jp2.add(delete);
		jp2.add(cancel);
		jp2.add(save);		
		jp2.add(edit);
		
		//jp2.add(orderby);
		jp2.add(jcbForOrderby);
		jp2.add(jcbForOrderby2);
	}
	public void initJp3(){
		jp3=new JPanel(new BorderLayout());
		jp3.setBackground(Color.gray);
		pm=new ProductModel();
		
		String[] paras={"C201212210000"};		
		pm.query(sqlJP3,paras);
		jtable=new JTable(pm);
		jsp=new JScrollPane(jtable);
		jp3.add(jsp);			
	}
	public void refresh(String sql){
		//���»���µ�����ģ��
		ProductModel pm=new ProductModel();
		pm.query(sql);
		jtable.setModel(pm);//����ordeTable
	}
	public void refresh(String sql,String paras[]){
		//���»���µ�����ģ��
		pm=new ProductModel();
		pm.query(sql,paras);
		jtable.setModel(pm);//����ordeTable
	}
	
	//ˢ�¿ͻ���Ϣ���ĺ���
	public void refreshClient(String sql,String[] paras){
		SqlHelper sh=new SqlHelper();
		int colum=0;
		try{						
			ResultSet rs=sh.query(sql, paras);
			colum=rs.getMetaData().getColumnCount();
			String[] cInfo=new String[colum];
			if(rs.next()){
				for(int i=0;i<cInfo.length;i++){
					cInfo[i]=rs.getString(i+1);
				}				
			}
			//�õõ�������ˢ�¿ͻ���Ϣ
			jtf[1].setText(cInfo[0]);//�ͻ�����
			jtf[12].setText(cInfo[1]);//����
			jtf[3].setText(cInfo[6]);//����
			jtf[4].setText(cInfo[8]);//����
			jtf[5].setText(cInfo[10]);//Ƭ��
			jtf[9].setText(cInfo[13]);//�ͻ�����
			jtf[11].setText(cInfo[13]);//�ͻ���ʽ
			jtf[14].setText(cInfo[4]);//��ϵ��
			jtf[15].setText(cInfo[7]);//����ʱ��
			jtf[2].setText(cInfo[3]);//��ַ
			jtf[17].setText(cInfo[9]);//�ͻ�����
			jtf[22].setText(cInfo[11]);//��������
			jtf[13].setText(cInfo[2]);//�ֻ�����
			jtf[23].setText(cInfo[21]);//��ע
			
			//����״̬(�򵥻���������ĳ������˵�)
			jtf[7].setText("0");//Ĭ��δ����
			
			//��ˮ����ţ���ʱ�򵥴���������Ըĳ������˵���
			jtf[10].setText("H201331");//Ĭ��
			//ˢ������ʱ��
			jtf[19].setText(Calendar.getInstance().getTime().toLocaleString());
			//ˢ���ͻ�ʱ�䣨�ͻ�ʱ��Ӧ���ɽ���Ա�ֹ���ɣ��������������ļ򻯴���
			jtf[20].setText(Calendar.getInstance().getTime().toLocaleString());
			jtf[21].setText("�ֿ�һ");
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sh.sqlClose();
		}
	
	}
	//�øÿͻ����һ�εĶ����������ζ������
	public void createPBilling(){
		//1.��ȡ��ǰ�Ķ���c��������Ϣ(�����Ľ����Ҫ����)
		
		orderStr[0]=jtf[0].getText().trim();//1ҵ�񵥺�
		orderStr[1]=jtf[1].getText().trim();//2�ͻ�����
		orderStr[2]=jtf[10].getText().trim();//3��ˮ�����
		//orderStr[3]=jtf[19].getText().trim();//4����ʱ��
		orderStr[3]=jtf[19].getText().trim();//4����ʱ��
		orderStr[4]=jtf[7].getText().trim();//5����״̬
		orderStr[5]=jtf[18].getText().trim();//6�������
		orderStr[6]=jtf[20].getText().trim();//7�ͻ�ʱ��
		orderStr[7]=jtf[21].getText().trim();//8�����ֿ�
		orderStr[8]="0";//9���(Ĭ��Ϊ0)
		orderStr[9]=jtf[23].getText().trim();//10������ע
		//��Ӷ���		
		
		bm.update(sqlOrder, orderStr);//������һ�ݳ�ʼ����������
		//���ԭ�������һ��SID
		String sqlSID="select top 1 SID from ProductShipping order by SID desc";
		//�õ��µ�SID	
		String[] sidStr=new String[3];
		sidStr[0]=DataFactory.getID(bm.query(sqlSID),"S",true);//��ȷʱ�䡢S��ͷ	
		sidStr[1]=DataFactory.getID(sidStr[0],"S",true);
		sidStr[2]=DataFactory.getID(sidStr[1],"S",true);
		//System.out.println(sidStr[0]+" "+sidStr[1]+" "+sidStr[2]);
		
		//����µĳ������ݣ���ʼ���ݣ�
		String sqlPro="insert into ProductShipping values(?,?,?,?,?,?,?,?,?,?)";
		Vector<Vector> v=BillingPane.this.pm.rows;
		String[][] setSqlPro=new String[3][10];
		//���÷����źͶ�����
		
		for(int i=0;i<3;i++){
			setSqlPro[i][0]=sidStr[i];
			setSqlPro[i][1]=jtf[0].getText().trim();
		}
							
		//���÷����źͶ���������Ĳ���
		//System.out.println(pm.getRowCount()+" "+pm.getColumnCount());
		for(int i=0;i<BillingPane.this.pm.getRowCount();i++){
			for(int j=0;j<BillingPane.this.pm.getColumnCount();j++){
				setSqlPro[i][j+2]=(String) pm.getValueAt(i, j);
			}
		}
		//�����ݿ������
		for(int i=0;i<setSqlPro.length;i++){
			pm.update(sqlPro, setSqlPro[i]);						
		}
		
	}
	////�����µĶ����ͳ����������ݿ�
	public void createOrder(){
		//�����µĶ����ͳ����������ݿ�
		this.createPBilling();
		//1.���searchJTF�е��ı����ͻ�ID��
		cid=searchJTF.getText().trim();
		//System.out.println(cid);
		String paras[]={cid};
		//2.���ú��������ݿ����µĳ�����ˢ��jtable
		this.refresh(sqlJP3,paras);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.timeNow.setText("��ǰʱ�䣺 "+Calendar.getInstance().getTime().toLocaleString()+"   ");
		if(e.getSource()==jpLogo_desktop){
			this.dispose();
		}else if(e.getSource()==jpLogo_exit){
			System.exit(0);
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//����û������������ť
		if(e.getSource()==add){	
			if(searchJTF.getText().equals("")){
				JOptionPane.showMessageDialog(this,"�����ڲ��ҿ�������ͻ����Ų���ѯ��");
				return;
			}else{
				Object[] possibleValues={"����","ȡ��"};
				Object selectValue=JOptionPane.showInputDialog(null, "ȷ�����ͻ����һ�����ѿ�����", "���������Ի���", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
				//2.��ȡ�������Ϣ
				if(selectValue.equals(possibleValues[0])){
					this.createOrder();
					JOptionPane.showMessageDialog(this,"��ɣ�");
				}else if(selectValue.equals(possibleValues[1])){
					return;
				}
				
			}			 			 
		}else if(e.getSource()==cancel){
			
			//��ԭ���û���Ĭ�����ݣ��޸�������Ҫ���õ�ԭ�������ݣ��ͽ���ǰ��������Ϣ��¼ȫ��ɾ���ٰ�Ĭ�Ϸ������´���һ���鷳����ʱ�����ú�һ�ַ���
			//ɾ���µĳ���������ɾ���µĶ���		

			oids[0]=newOID;
			String sqlDelProBill="delete  from ProductShipping where OID=?";
			String sqlDelOrdBill="delete from orders where OID=?";
			bm.update(sqlDelProBill,oids);
			bm.update(sqlDelOrdBill,oids);	
			
			//Ĭ�Ϸ�ʽ�����µĶ����ͳ��������ȴ����������ٴ���������                     
			newOID=DataFactory.getID(bm.query(sqlOID),"O",true);
			jtf[0].setText(newOID);//��ȷʱ�䡢O��ͷ			
			orderStr[0]=newOID;//1ҵ�񵥺�
			//ɾ����¼���ؽ�һ��������������ģ��
			pm=new ProductModel();
			String paras[]={cid};
			pm.query(sqlJP3, paras);
			
			//���µ�ģ�ʹ��������ͳ�����
			this.createPBilling();

			//ˢ�»�����ʾ���棨��ʱ���谴�������ݣ��Ѿ�������һ��������Ϣ�õ����������ˣ���add�Ļ�����Ϊ�������ظ�����
			this.refresh(sqlJP3,paras);//��ˢ����һ��

			
		}else if(e.getSource()==save){
			Object[] possibleValues={"����","ȡ��"};
			Object selectValue=JOptionPane.showInputDialog(null, "ȷ��������ݶ�����", "���涩���Ի���", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
			//��ȡ�������Ϣ
			if(selectValue.equals(possibleValues[0])){
				JOptionPane.showMessageDialog(this,"�����ѱ��棡");
			}else{
				return;
			}
			//�������ݿ��������
			//String
		}else if(e.getSource()==delete){//����
			Object[] possibleValues={"ȷ��","ȡ��"};
			Object selectValue=JOptionPane.showInputDialog(null, "ȷ��ɾ����ݶ�����", "ɾ�������Ի���", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
			//��ȡ�������Ϣ
			if(selectValue.equals(possibleValues[0])){
				//���Ѵ����������û���棬ֻҪ����add���Ķ�������ɾ��
				String delSell="delete from ProductShipping where OID=(select top 1 OID from orders where CID= 'C201212210000' order by OID desc)";
				String delOrder="delete from orders where OID=(select top 1 OID from orders where CID= 'C201212210000' order by OID desc);";
				bm.update(delSell);
				bm.update(delOrder);
				String[] paras={cid}; 
				this.refresh(sqlJP3,paras);
				JOptionPane.showMessageDialog(this,"������ɾ����");
			}else{
				return;
			}
			
		}else if(e.getSource()==edit){
			ProductEditor pe=new ProductEditor(true);
	
		}else if(e.getSource()==searchJL){
			//ˢ�¿ͻ��ϴζ����ĳ�������
			
			//1.���searchJTF�е��ı����ͻ�ID��
			cid=searchJTF.getText().trim();
			//System.out.println(cid);
			String paras[]={cid};
			//2.���ú���ˢ��jtable
			this.refresh(sqlJP3,paras);
			
			//ˢ�µ�ǰ�ͻ�����Ϣ
			//1.��õ�ǰ�ͻ���id�ţ�����ɣ�
			//2.�����ݿ���ȡ����
			String sqlClientInfo="select * from client where CID=?";
			this.refreshClient(sqlClientInfo, paras);
			//ˢ��ͷ��
			clientName.setText(bm.query("select CName from client where CID=?",paras));
			
		}else if(e.getSource()==jlMob){
			//ͨ���ֻ��Ų�ѯ
			String mobNum=jtfMob.getText().trim();
			String[] mobNums={mobNum};
			searchJTF.setText(bm.query(sqlCall, mobNums));//ˢ��������
			//��������ģ��
			String sqlMob="select OID ҵ�񵥺�,CID �ͻ�����,SendTime �ͻ�ʱ��,OmonerySum ���,Warehouse �ֿ� from orders where CallNumber=? order by SendTime desc";
			orderModel.query(sqlMob, mobNums);
			clientModel.query(sqlMob, mobNums);
			//���±��
			jtOrder.setModel(orderModel);
			jtClient.setModel(clientModel);
			
		}else if(e.getSource()==jlNam){
			//ͨ���ֻ��Ų�ѯ
			String namNum=jtfNam.getText().trim();
			String[] namNums={namNum};
			searchJTF.setText(bm.query(sqlCname, namNums));//ˢ��������
			//��������ģ��
			String sqlNam="select CID �ͻ�����,CName �ͻ�����,ContactPerson ��ϵ��,Adress ��ַ,Phone �ֻ����� from client where CName=?  order by CID asc";
			orderModel.query(sqlNam, namNums);
			clientModel.query(sqlNam, namNums);
			//���±��
			jtOrder.setModel(orderModel);
			jtClient.setModel(clientModel);
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
		if(e.getSource()==add)
		{
			add.setEnabled(true);
		}else if(e.getSource()==cancel)
		{
			cancel.setEnabled(true);
		}else if(e.getSource()==save)
		{
			save.setEnabled(true);
		}else if(e.getSource()==delete) 
		{
			delete.setEnabled(true);
		}else if(e.getSource()==edit)
		{
			edit.setEnabled(true);
		}else if(e.getSource()==searchJL)
		{
			searchJL.setEnabled(true);
		}else if(e.getSource()==jlMob)
		{
			jlMob.setEnabled(true);
		}else if(e.getSource()==jlNam)
		{
			jlNam.setEnabled(true);
		}
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==add)
		{
			add.setEnabled(false);
		}else if(e.getSource()==cancel)
		{
			cancel.setEnabled(false);
		}else if(e.getSource()==save)
		{
			save.setEnabled(false);
		}else if(e.getSource()==delete) 
		{
			delete.setEnabled(false);
		}else if(e.getSource()==edit)
		{
			edit.setEnabled(false);
		}else if(e.getSource()==searchJL)
		{
			searchJL.setEnabled(false);
		}else if(e.getSource()==jlMob)
		{
			jlMob.setEnabled(false);
		}else if(e.getSource()==jlNam)
		{
			jlNam.setEnabled(false);
		}
	}
	//////////////////////////////����//////////////////////////////////////
	public class ProductEditor extends JDialog implements ActionListener{
		
		//������Ҫ�����
		JLabel []jlabel;
		JTextField []jtf=null;
		JButton confirm,cancel;
		OrderModel ordermodel;
		JPanel jp11,jp22,jp33;
		int columnNum,rowNum;
		String sql;
		Boolean isJtf=true;//�Ƿ��ʼ��JTextField,Ĭ����
		//Ϊ������޸ĵĹ��ܣ������һ���������ֶ�����������������ʼ���ı������Ǻ�ɾ������Ψһ��ͨ���ĵط�
		public int initJTextField()
		{
			rowNum=BillingPane.this.jtable.getSelectedRow();//���ѡ�е���
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�༭����Ʒ��");
				return 0;
			}
			
			for(int i=0;i<this.columnNum;i++){
				this.jtf[i].setText(BillingPane.this.pm.getValueAt(rowNum, i).toString());
			}
			jtf[0].setEditable(false);//��Ʒid����Ϊ�����޸�
			return 1;
		}
		public ProductEditor(boolean isJtf)
		{
			this.isJtf=isJtf;
			//��ͨ��orderModel�����Ҫ������
			pm=new ProductModel();
			//String sqlfirst="select*from orderForm";
			//String parasfirst[]={"1"};
			String sqlEditor="select PID ��Ʒ���,Pname ��Ʒ����,specification ����ͺ�,unitPrice ����,quantity ����,biscount �ۿ�,monerySum ���,Remarks ��ע from ProductShipping where OID=(select top 1 OID from orders where CID= ? order by OID desc)";
			String cids[]={cid};
			pm.query(sqlEditor,cids);
			//�������
			columnNum=pm.getColumnCount();
			jlabel=new JLabel[columnNum];
			jtf=new JTextField[columnNum];
			
			//System.out.println("�������"+columnNum);
			for(int i=0;i<columnNum;i++)//�������
			{
				jtf[i]=new JTextField();
				jtf[i].setBorder(BorderFactory.createLoweredBevelBorder());
				jlabel[i]=new JLabel(pm.getColumnName(i));
		
			}
		
			confirm=new JButton("ȷ��");
			cancel=new JButton("ȡ��");
			
			//���ü���
			confirm.addActionListener(this);
			cancel.addActionListener(this);
			
			jp11=new JPanel();
			jp22=new JPanel();
			jp33=new JPanel();
			//���ò��ֹ�����,jp3Ĭ��Ϊ��ʽ����
			jp11.setLayout(new GridLayout(columnNum,1,10,10));
			jp22.setLayout(new GridLayout(columnNum,1,10,10));
			
			//������
			for(int i=0;i<columnNum;i++)
			{
				
				jp11.add(jlabel[i]);
				jp22.add(jtf[i]);
			}		
			//������
			jp33.add(confirm);//Ĭ��������
			jp33.add(cancel);
			
			//�����ж�����ӻ����޸ģ�������޸��ж��Ƿ�ѡ��һ��
			if(this.isJtf) {
				if(this.initJTextField()==0){
					return;
				}
			}
			
			//��ӵ��Ի���
			this.add(jp11,"West");
			this.add(jp22,"Center");
			this.add(jp33,"South");
			
			//��ӵ��Ի���
			this.add(jp11,"West");
			this.add(jp22,"Center");
			this.add(jp33,"South");
			
			//������ʾ
			this.setSize(250,350);
			this.setLocation(400,100);
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}


		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String[] editorInfo=new String[9];
			JLabel[] jlEditor=new JLabel[8];
			JTextField[] jtfEditor=new JTextField[8];
			if(e.getSource()==confirm){//�����Dialog�е����ȷ�ϰ�ť
				BillingPane.this.pm=new ProductModel();//�ڲ�������ⲿ���Ա
				if(this.isJtf){//�޸�																						
					
					//String sqlJP3="select PID ��Ʒ���,Pname ��Ʒ����,specification ����ͺ�,unitPrice ����,quantity ����,biscount �ۿ�,monerySum ���,Remarks ��ע from ProductShipping where OID=(select top 1 OID from orders where CID= ? order by OID desc)";
					sql="update ProductShipping  set Pname=?,specification=?,unitPrice=?,quantity=?,biscount=?,monerySum=?,Remarks=? where PID=? and OID=?";
					for(int i=0;i<editorInfo.length-2;i++){
						editorInfo[i]=jtf[i+1].getText();
					}
					editorInfo[editorInfo.length-2]=jtf[0].getText();
					editorInfo[editorInfo.length-1]=BillingPane.this.newOID;
				}else{//���(������������ʱ���ò���)
					
				}
				if(BillingPane.this.pm.update(this.sql, editorInfo)){//��������ɲ���
					JOptionPane.showMessageDialog(this,"��ɣ�");
				}else{
					JOptionPane.showMessageDialog(this,"��Ǹ������δ����ɣ�");
				}
				this.dispose();//�ر���ӶԻ���					
			}else if(e.getSource()==cancel){
				this.dispose();
			}
			String[] cid2={BillingPane.this.cid};
			BillingPane.this.refresh(BillingPane.this.sqlJP3,cid2);//���ڲ����з�����Χ��
		}

	}
	/////////////////////////AddView///////////////////////////////
	
}
//------�ڲ���--ĩ-------------------
