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
public class CusRegPane extends JDialog implements ActionListener,MouseListener{
	public static void main(String[] args){
		new CusRegPane();
	}
	//�����ڲ�����������24��
	JPanel jpLogo,jpState,jpClient;			
	//logo����	
	//state����		
	//billing����
	Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
	JSplitPane jsp1,jsp2,jsp3,jsp4,jsp5;
	JPanel jpBillingLeft,jpContent,jp1,jp1West,jp1East,jp1WestWest,jp1WestCenter,jp1EastWest,jp1EastCenter,jp2,jp3,jp23;
	JLabel add,edit,delete,orderby,detail,search,cancel,save;
	JScrollPane jscrollp1,jscrollp2,jscrollp3;
	OrderModel order;
	JComboBox jcbForOrderby,jcbForOrderby2;
	JTable jtable;
	ProductModel pm;
	String sqlJP3="select CID �ͻ�����,CName �ͻ�����,Phone ��ϵ�绰,Adress ��ϵ��ַ,ContactPerson ��ϵ��,JoinTime ����ʱ�� from client";
	String[] orderStr=new String[22];
	String newCID;
	String cid;
	String[] oids=new String[1];
	String sqlOrder="insert into orders values(?,?,?,?,?,?,?,?,?,?)";//����¶���
	BillingModel bm=new BillingModel();
	String sqlCID="select top 1 CID from client order by CID desc";
	JLabel clientHead,clientName;			
	
	int rowNum,columnNum;
	//������ť����ǰ����û�ù���
	//JComboBox jcbForOrderby,jcbForOrderby2;
	String jcbArray[]={"��ʱ��","������","��������"};
	String jcbArray2[]={"��С����","�Ӵ�С"};
	
	String jcb1Str,jcb2Str;
	String sqlOrderby="select*from orderForm order by OrderTime desc";
	 
	Timer p1_timer;
	JLabel timeNow;
	Image timebg,p1_yes;
	 
	JTextField searchJTF;
	JLabel searchJL,refresh2;
	JButton searchJB;
	
	JLabel jlLogo=null;
	String[] jlabelString={"�ͻ�����","�ͻ�����","��ϵ�绰","��ϵ��ַ","��ϵ��","��������","��������","����ʱ��","�ͻ�����","�ͻ�����","Ƭ������","��������","��������","�ͻ�����","�ͻ�¥��","�Ƿ���Ч","���㵥λ","�������","��Ʒ�ͺ�","���ò�Ʒ����","��������","��ע"};
	JLabel[] jlabel=new JLabel[22];
	JTextField jtf[]=new JTextField[22];
	//��һ��ȫ�ֱ����ж��Ƿ����ڱ༭
	boolean isEdit=false;
	public CusRegPane(){		
		//���ø������ֵĳ�ʼ������
		initLogo();
		initState();
		initBilling();
		
		//----���²�ֵĹ��̺��й��ɣ�������־������˼������֣����ϵĴ���֤����������һ�����ˣ���ֻ�������������������----------
		
		//LOGO������
		jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpLogo,jpContent);
		jsp1.setDividerLocation(54);//ָ��logo�Ŀ��																	
		jsp1.setDividerSize(0);			
		
		//state����������logo��
		jsp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jpState,jpClient);
		jpContent.add(jsp2);
		jsp2.setDividerSize(0);
		jsp2.setDividerLocation(40);//ָ�����state�ĸ�
		
		
		//jp1����������logo��state��jpBillingLeft��
		jsp4=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp1,jp23);
		jpClient.add(jsp4);
		jsp4.setDividerLocation(400);
		jsp4.setDividerSize(0);
		
		//jp2����������logo��state��jpBillingLeftjp1��jp3��
		jsp5=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp2,jp3);
		jp23.add(jsp5);
		jsp5.setDividerLocation(30);
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
		this.setLocation(w/2-601,0);
		this.setTitle("�ͻ�ע�����");
		this.setSize(1002,750);
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
			jpLogo_Image=ImageIO.read(new File("images/c.jpg"));//�õ�ͼƬ
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpLogo_bg=new ImagePanel(jpLogo_Image);//�õ��������
		jpLogo_bg.setLayout(null);
		//�������

		//���
						
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
		clientName=new JLabel(" δ����");
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
	public void initBilling(){

		jpContent=new JPanel(new BorderLayout());
		jpClient=new JPanel(new BorderLayout());
		//��panel����Ƕ������panel���������ñ߽粼�֣�������������ֲ��ָ������һЩ
		jpBillingLeft=new JPanel(new BorderLayout());
		this.initJp1();			
		this.initJp2();
		this.initJp3();
		jp23=new JPanel(new BorderLayout());					
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
		newCID=DataFactory.getID(bm.query(sqlCID),"C",false);
		jtf[0].setText(newCID);//��ȷʱ�䡢C��ͷ
		
		//jp1=new JPanel(new GridLayout(12,4,10,5));//��jp1���ó�Ϊ���񲼾֣������ǼǶ�����Ϣ	
		jp1=new JPanel(new BorderLayout(5,5));
		jp1West=new JPanel(new BorderLayout(10,10));
		jp1WestWest=new JPanel(new GridLayout(12,1,10,10));
		jp1WestCenter=new JPanel(new GridLayout(12,1,10,10));
		jp1East=new JPanel(new BorderLayout(5,5));
		jp1EastCenter=new JPanel(new GridLayout(12,1,10,10));
		jp1EastWest=new JPanel(new GridLayout(12,1,10,10));
		
		//jp1.setVisible(true);
		for(int i=0;i<11;i++){//��ӱ�ǩ��¼���ı���
			jp1WestWest.add(jlabel[2*i]);
			jp1WestCenter.add(jtf[2*i]);
			jp1EastWest.add(jlabel[2*i+1]);
			jp1EastCenter.add(jtf[2*i+1]);
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
		
		//searchJB=new JButton();
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
		
		add.setEnabled(false);
		save.setEnabled(false);
		edit.setEnabled(false);
		cancel.setEnabled(false);
		delete.setEnabled(false);
		searchJL.setEnabled(false);
		//refresh2.setCursor(myCursor);
		
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
		
		pm.query(sqlJP3);
		jtable=new JTable(pm);
		jscrollp1=new JScrollPane(jtable);
		jp3.add(jscrollp1);			
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
			for(int i=0;i<cInfo.length;i++){
				jtf[i].setText(cInfo[i]);
			}
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sh.sqlClose();
		}
	
	}
	public int initJTextField()
	{
		columnNum=pm.getColumnCount();
		rowNum=this.jtable.getSelectedRow();//���ѡ�е���
		
		if(rowNum==-1){
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�༭����Ʒ��");
			return 0;
		}
		
		for(int i=0;i<this.columnNum;i++){
			this.jtf[i].setText(this.pm.getValueAt(rowNum, i).toString());
		}
		jtf[0].setEditable(false);//��Ʒid����Ϊ�����޸�
		return 1;
	}
	
	////�����µĿͻ���Ϣ�����ݿ�
	public void createClient(){
		//�����µĶ����ͳ����������ݿ�
		//1.���searchJTF�е��ı����ͻ�ID��
		cid=searchJTF.getText().trim();
		//2.���ú��������ݿ����µĳ�����ˢ��jtable
		this.refresh(sqlJP3);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.timeNow.setText("��ǰʱ�䣺 "+Calendar.getInstance().getTime().toLocaleString()+"   ");

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//����û������������ť
		if(e.getSource()==cancel){			
			for(int i=1;i<jlabel.length;i++){
				jtf[i].setText("");
			}
			if(isEdit){
				isEdit=false;
			}
			JOptionPane.showMessageDialog(this,"ȡ���ɹ���");
			
		}else if(e.getSource()==save||e.getSource()==add){
			Object[] possibleValues={"����","ȡ��"};
			Object selectValue=JOptionPane.showInputDialog(null, "ȷ��������λ������", "����ͻ��Ի���", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
			//��ȡ�������Ϣ
			if(selectValue.equals(possibleValues[0])){
				String[] cInfo=new String[22];
				for(int i=0;i<cInfo.length;i++){
					cInfo[i]=jtf[i].getText().trim();
				}
				String sql="insert into client values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				if(isEdit){
					cInfo[cInfo.length-1]=cInfo[0];
					for(int i=1;i<cInfo.length;i++){
						cInfo[i-1]=cInfo[i];
					}					
					sql="update client set CName=?,Phone=?,Adress=?,ContactPerson=?,Email=?,Birthday=?,JoinTime=?,Grade=?,CType=?,Area=?,AreaType=?,ARegion=?,CarType=?,CFloor=?,Effective=?,Denominated=?,RecentServer=?,ProductModel=?,OftenPro=?,OftenQuantity=?,Remarks=? where CID=?";
					isEdit=false;
				}
				bm.update(sql,cInfo);
				JOptionPane.showMessageDialog(this,"�����ѱ��棡");
				this.refresh(sqlJP3);
			}else{
				return;
			}
			//�������ݿ��������
			//String
		}else if(e.getSource()==delete){//����
			
			rowNum=jtable.getSelectedRow();//���ѡ�е���
			//System.out.println("ѡ��������"+rowNum);
			if(rowNum==-1){//�ͻ�δѡ��һ�в����ɾ����ť
				Object[] possibleValues={"��","��","ȡ��"};
				Object selectValue=JOptionPane.showInputDialog(null, "ȷ��ɾ����ǰ�ͻ���", "�ͻ�ɾ���Ի���", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
								
				if(selectValue.equals(possibleValues[0])){
					//ɾ���ոձ���Ŀͻ���Ϣ
					String[] paras={jtf[0].getText().trim()};
					if(bm.update("delete from client where CID=?", paras)){
						JOptionPane.showMessageDialog(this, "��ǰ�ͻ�ɾ���ɹ���");
					}else{
						JOptionPane.showMessageDialog(this, "ɾ��ʧ�ܣ���鿴�ͻ������Ƿ���ȷ��");
					}
				}else if(selectValue.equals(possibleValues[2])){
					//ȡ����β���
					return;
				}else{
					//��ʾ����Աѡ�к��ٲ���
					JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ���Ķ�����");
					return;
				}						
			}else{
				String[] paras={(String)pm.getValueAt(rowNum, 0)};
				BillingModel bm=new BillingModel();
				bm.update("delete from client where CID=?", paras);
				this.refresh(sqlJP3);//��������ģ���Լ�JTabel
			}	
					
		}else if(e.getSource()==edit){
			rowNum=jtable.getSelectedRow();//���ѡ�е���
			if(rowNum==-1){//�ͻ�δѡ��һ�в����ɾ����ť
				Object[] possibleValues={"��","ȡ��"};
				Object selectValue=JOptionPane.showInputDialog(null, "ȷ��Ҫѡ��һ���ͻ����б༭��", "�༭�Ի���", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);			
								
				if(selectValue.equals(possibleValues[0])){
					
					JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�༭�Ŀͻ���Ϣ��");
					return;
				}else{
					//��ʾ����Աѡ�к��ٲ���					
					return;
				}						
			}else{
				//ˢ�±༭��
				String[] cids={(String)jtable.getValueAt(rowNum, 0)};
				this.refreshClient("select * from client where CID=?", cids);	
				isEdit=true;
			}	
			
		}else if(e.getSource()==searchJL){
			//ˢ�¿ͻ��ϴζ����ĳ�������
			
			//1.���searchJTF�е��ı����ͻ�ID��
			cid=searchJTF.getText().trim();
			//System.out.println(cid);
			String paras[]={cid};
			//2.���ú���ˢ��jtable
			this.refresh(sqlJP3+" where CID=?",paras);
			
			//ˢ�µ�ǰ�ͻ�����Ϣ
			//1.��õ�ǰ�ͻ���id�ţ�����ɣ�
			//2.�����ݿ���ȡ����
			String sqlClientInfo="select * from client where CID=?";
			this.refreshClient(sqlClientInfo, paras);
			//ˢ��ͷ��
			clientName.setText(bm.query("select CName from client where CID=?",paras));					
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
		}
	}
}
	
