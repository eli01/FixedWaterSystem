/**
 * ���ߣ�������
 * ʱ�䣺2012.11.28/9:01
 * ���ܣ����˿�ѡ�ж�������ʱ��p3east����ת�������Ƭ��������һ��JPanel��
 * ��־��10:13����Ҫһ��Ĭ�Ͻ��棬��ת��window1ʱ�ᱻĬ����ʾ�ģ�
 * ��ʱ��ȥ������һ�������ȴ���
 * 18:44:����JLabelû��addActionListener()������������addMouseListener
 * 11.19/12.38:����û�и��£�ԭ����refresh���������⣨sql����ǲ�����ģ�����sqlOrderby���ɳ�Ա��������дrefresh(String)����֮
 * 
 */

package com.FixedWaterSystem.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.FixedWaterSystem.Model.OrderModel;
import com.FixedWaterSystem.Model.UseMode;
import com.FixedWaterSystem.Model.UserModel;
import com.FixedWaterSystem.Tools.ImagePanel;
import com.FixedWaterSystem.Tools.Word;

public class UserManager extends JPanel implements ActionListener,MouseListener
{
	//�������
	JSplitPane jsp1,jsp2,jsp;
	JPanel p1,p2,p3,p4,p12,p34;//���������ĸ�����
	JLabel add,edit,delete,orderby,detail,search;
	JTextField jtf;
	ImagePanel imagepanel=null;
	Image background;
	JTable userTable;
	JScrollPane jscrollpane;
	UseMode user;
	int rowNum;
	//������ť����ǰ����û�ù���
	JComboBox jcbForOrderby,jcbForOrderby2;
	String jcbArray[]={"��ʱ��","������","��������"};
	String jcbArray2[]={"��С����","�Ӵ�С"};
	
	String jcb1Str,jcb2Str;
	 String sqlOrderby="select userid �û����,username �û���,userpasswd �û�����,usergrade �û�����,usersex �Ա�,userbg ��������,userjob �û�ְλ,userage �û�����,useradress �־ӵ�ַ,useraser ���õ�����,useranswer ��,userremind ������ʾ from users order by userid desc";
	 
	 Timer p1_timer;
	 JLabel timeNow;
	 Image timebg,p1_yes;
	 
	 JTextField searchJTF;
	 JLabel searchJL,refresh2;
	 JButton searchJB;
	//���캯��
	public UserManager()
	{
		
		//�������
		
		try {
			background=ImageIO.read(new File("images/background2.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imagepanel=new ImagePanel(background); 
		imagepanel.setLayout(new BorderLayout());
		imagepanel.setBounds(0, 0, 790, 505);
		
		//p1
		p1=new JPanel(new BorderLayout());
		p1_timer=new Timer(1000,this);//ÿ��һ�봥��һ��ActionEvent
		p1_timer.start();//��ʼ�߳�
		//��õ�ǰʱ��
		timeNow=new JLabel("��ǰʱ�䣺 "+Calendar.getInstance().getTime().toLocaleString()+"   ",new ImageIcon("images/Time.jpg"),0);//��ʱ�䱾�ػ�
		timeNow.setFont(Word.f3);
		p1.add(timeNow);//����ʾʱ����������ұ�
		//Ϊ��ʾʱ���JPanel���ñ���
		try {
			timebg=ImageIO.read(new File("images/timebg.jpg"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JLabel orderManager=new JLabel("");
		JLabel yes=new JLabel("�û�����",new ImageIcon("images/yes.jpg"),0);
		yes.setFont(Word.f1);
		ImagePanel timeIP=new ImagePanel(timebg);//��timeBg��������һ�����
		timeIP.setLayout(new BorderLayout());
		yes.setLayout(new BorderLayout());
		timeIP.setBackground(Color.white);//����Ϊ
		timeIP.add(yes,"West");
		timeIP.add(timeNow,"East");//��������������ʱ����ʾ
		p1.add(timeIP);//��p1�������ϼ�һ������ʱ��ı������
		//p1.add(yes,"West");
		
		
		//p2
		p2=new JPanel(new BorderLayout());
		user=new UseMode();
		String sql="select userid �û����,username �û���,userpasswd �û�����,usergrade �û�����,usersex �Ա�,userbg ��������,userjob �û�ְλ,userage �û�����,useradress �־ӵ�ַ,useraser ���õ�����,useranswer ��,userremind ������ʾ from users where 1=?";
		String paras[]={"1"};
		user.query(sql, paras);
		userTable=new JTable(user);
		jscrollpane=new JScrollPane(userTable);
		p2.add(jscrollpane);
		//p3
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));//����Ϊ�����������
		add=new JLabel("���",new ImageIcon("images/add.jpg"),0);
		delete=new JLabel("ɾ��",new ImageIcon("images/delete.jpg"),0);
		edit=new JLabel("�༭",new ImageIcon("images/edit.jpg"),0);
		orderby=new JLabel("����",new ImageIcon("images/orderby.jpg"),0);
		jcbForOrderby=new JComboBox(jcbArray);//����һ�������б�
		jcbForOrderby2=new JComboBox(jcbArray2);//����һ�������б�
		searchJTF=new JTextField(10);
		searchJL=new JLabel(new ImageIcon("images/search.jpg"));
		refresh2=new JLabel(new ImageIcon("images/refresh2.jpg"));
		
		
		//searchJB=new JButton();
		
		//��������
		add.setFont(Word.f3);
		delete.setFont(Word.f3);
		edit.setFont(Word.f3);
		orderby.setFont(Word.f3);
		jcbForOrderby.setFont(Word.f3);
		jcbForOrderby2.setFont(Word.f3);
		
		//��������
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
		add.setCursor(myCursor);
		delete.setCursor(myCursor);
		edit.setCursor(myCursor);
		orderby.setCursor(myCursor);
		searchJL.setCursor(myCursor);
		refresh2.setCursor(myCursor);
		//���ø���״̬
		add.setEnabled(false);
		orderby.setEnabled(false);
		edit.setEnabled(false);
		refresh2.setEnabled(false);
		delete.setEnabled(false);
		searchJL.setEnabled(false);
		//���ü���
		add.addMouseListener(this);
		delete.addMouseListener(this);
		edit.addMouseListener(this);
		orderby.addMouseListener(this);
		searchJL.addMouseListener(this);
		refresh2.addMouseListener(this);
		
		//���p3�����
		p3.add(refresh2);
		p3.add(add);
		p3.add(delete);
		p3.add(edit);
		p3.add(orderby);
		p3.add(jcbForOrderby);
		p3.add(jcbForOrderby2);
		p3.add(searchJL);
		p3.add(searchJTF);
				
		p4=new JPanel(null);
		p12=new JPanel(new BorderLayout());
		p34=new JPanel(new BorderLayout());
		//���
		
		jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,p1,p2);
		//ָ���ϱߵ����ռ����
		jsp1.setDividerLocation(30);
		//ȡ���߽���
		jsp1.setDividerSize(0);
		
		jsp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,p3,p4);
		jsp2.setDividerLocation(30);
		jsp2.setDividerSize(0);
		
		p12.add(jsp1);
		p34.add(jsp2);
		jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,p12,p34);
		jsp.setDividerLocation(500);
		jsp.setDividerSize(0);
		//this.setForeground(background);
		imagepanel.setOpaque(false);
		this.imagepanel.add(jsp);
		this.setLayout(new BorderLayout());
		this.add(imagepanel);
	
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		//����û��������Ӱ�ť
//		if(e.getSource()==add){
//			//�����봴��������Χ��������ĳ����ϵ�������ܷ�����Χ������г�Ա�������κ�����������
//			AddView addDialog=new AddView();//���ⲿ�ഴ������ڲ������
//		}
		this.timeNow.setText("��ǰʱ�䣺 "+Calendar.getInstance().getTime().toLocaleString()+"   ");
	}
	//����ˢ��JTabel�ĺ���
	public void refresh(String sql){
		//���»���µ�����ģ��
		user=new UseMode();
		user.query(sql);
		userTable.setModel(user);//����ordeTable
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//����û��������Ӱ�ť
		if(e.getSource()==add){
			//�����봴��������Χ��������ĳ����ϵ�������ܷ�����Χ������г�Ա�������κ�����������
			AddView addDialog=new AddView(false);//���ⲿ�ഴ������ڲ������,����ʼ��
		}else if(e.getSource()==delete){
			//�õ�Ҫɾ���������ˮ��
			rowNum=this.userTable.getSelectedRow();//���ѡ�е���
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ���Ķ�����");
				return;
			}
			String SequenceNumber=(String)user.getValueAt(rowNum, 0);
			String sql="delete from users where userid=?";
			String[] paras={SequenceNumber};
			OrderModel om=new OrderModel();
			om.update(sql, paras);
			this.refresh(sqlOrderby);//��������ģ���Լ�JTabel
		}else if(e.getSource()==edit){
			AddView addDialog=new AddView(true);//���ⲿ�ഴ������ڲ������
		}else if(e.getSource()==orderby){//����
			
			 jcb1Str=jcbForOrderby.getSelectedItem().toString();//ѡ�������ģʽ
			 jcb2Str=jcbForOrderby2.getSelectedItem().toString();//ѡ�������ʽ
			//�����û���ͬ�����ù���SQL����
			 if(jcb1Str.equals("��ʱ��")){
				 if(jcb2Str.equals("��С����")){
					sqlOrderby="select userid �û����,username �û���,userpasswd �û�����,usergrade �û�����,usersex �Ա�,userbg ��������,userjob �û�ְλ,userage �û�����,useradress �־ӵ�ַ,useraser ���õ�����,useranswer ��,userremind ������ʾ from users by userid asc"; //����
				 }else if(jcb2Str.equals("�Ӵ�С")){
					sqlOrderby="select userid �û����,username �û���,userpasswd �û�����,usergrade �û�����,usersex �Ա�,userbg ��������,userjob �û�ְλ,userage �û�����,useradress �־ӵ�ַ,useraser ���õ�����,useranswer ��,userremind ������ʾ from users order by userid desc"; //����
				 }
			 }else if(jcb1Str.equals("������")){
				 if(jcb2Str.equals("��С����")){
					sqlOrderby="select userid �û����,username �û���,userpasswd �û�����,usergrade �û�����,usersex �Ա�,userbg ��������,userjob �û�ְλ,userage �û�����,useradress �־ӵ�ַ,useraser ���õ�����,useranswer ��,userremind ������ʾ from users order by userid asc"; //����
				 }else if(jcb2Str.equals("�Ӵ�С")){
					sqlOrderby="select userid �û����,username �û���,userpasswd �û�����,usergrade �û�����,usersex �Ա�,userbg ��������,userjob �û�ְλ,userage �û�����,useradress �־ӵ�ַ,useraser ���õ�����,useranswer ��,userremind ������ʾ from users order by userid desc"; //���� 
				 }
				 
			 }else if(jcb1Str.equals("��������")){
				 if(jcb2Str.equals("��С����")){
					sqlOrderby="select userid �û����,username �û���,userpasswd �û�����,usergrade �û�����,usersex �Ա�,userbg ��������,userjob �û�ְλ,userage �û�����,useradress �־ӵ�ַ,useraser ���õ�����,useranswer ��,userremind ������ʾ from users order by userid asc"; //����				
					this.refresh(sqlOrderby);//��������ģ���Լ�JTabel
				 }else if(jcb2Str.equals("�Ӵ�С")){
					sqlOrderby="select userid �û����,username �û���,userpasswd �û�����,usergrade �û�����,usersex �Ա�,userbg ��������,userjob �û�ְλ,userage �û�����,useradress �־ӵ�ַ,useraser ���õ�����,useranswer ��,userremind ������ʾ from users order by userid desc "; //����
				 }
			 }
			 OrderModel om=new OrderModel();
			 om.query(sqlOrderby);
			this.refresh(sqlOrderby);//��������ģ���Լ�JTabel
		}else if(e.getSource()==searchJL){
			String sql="select userid �û����,username �û���,userpasswd �û�����,usergrade �û�����,usersex �Ա�,userbg ��������,userjob �û�ְλ,userage �û�����,useradress �־ӵ�ַ,useraser ���õ�����,useranswer ��,userremind ������ʾ from users where userid=?";
			String paras[]={searchJTF.getText().trim()};
			 OrderModel om=new OrderModel();
			 om.query(sql,paras);
			this.userTable.setModel(om);//��������ģ���Լ�JTabel
		}else if(e.getSource()==refresh2){
			this.refresh(sqlOrderby);
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
		}else if(e.getSource()==delete) 
		{
			delete.setEnabled(true);
		}else if(e.getSource()==edit)
		{
			edit.setEnabled(true);
		}else if(e.getSource()==searchJL)
		{
			searchJL.setEnabled(true);
		}else if(e.getSource()==orderby)
		{
			edit.setEnabled(true);
		}else if(e.getSource()==refresh2)
		{
			searchJL.setEnabled(true);
		}
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==add)
		{
			add.setEnabled(false);
		}else if(e.getSource()==delete) 
		{
			delete.setEnabled(false);
		}else if(e.getSource()==edit)
		{
			edit.setEnabled(false);
		}else if(e.getSource()==searchJL)
		{
			searchJL.setEnabled(false);
		}else if(e.getSource()==orderby)
		{
			edit.setEnabled(false);
		}else if(e.getSource()==refresh2)
		{
			searchJL.setEnabled(false);
		}
	}
	///////////////////////AddView///////////////////////////////////////////////////////////////////////////////
	/**
	 * ���ߣ�mengqingshen
	 * ʱ�䣺2012.11.28/14:51
	 * ���ܣ����û������Ӳ���ʱ���öԻ������������Ӳ���
	 * ��־����Ϊ�öԻ����߼�����orderManager���panel���ӶԻ����ڣ������췽����ȴ��������һ��panel��һ��dialog��
	 * �����ڣ�Ϊ������������ڸ���orderManager�е�table,�����һ��ȳ�����orderManager����������ڵġ���ӡ���ť
	 * 
	 * 20:03����ķ����в�ͨ������������ڲ���ķ�ʽ�����������ǣ����ڲ����з�����Χ��ĳ�Ա���Ӷ��ﵽ����JLabel��Ŀ��
	 * ���䣺�ڲ��෽�����У��ɲ����ڴ��븴�ã����õĽ������Ѱ���С���
	 * ����������ر�����Ϊ��Vector�����⣬��Ϊ���˿�ָ����쳣��Խ������⵽ʱ��Ĵ��ڣ��������ˣ������ĵ�����Ѿ�û��Vector�ˣ���ʵVector���Ǻ���Ч�ģ������淢��ԭ��ܼ򵥣���ʵ��û�г�ʼ��JPanel�Ĺ�ϵ
	 */

	public class AddView extends JDialog implements ActionListener{
		
		//������Ҫ�����
		JLabel []jlabel;
		JTextField []jtf=null;
		JButton confirm,cancel;
		OrderModel ordermodel;
		JPanel jp1,jp2,jp3;
		int columnNum,rowNum;
		String sql;
		String paras[]=new String[12];//�����������һ�����⣬�����ں���������ݿ�
		Boolean isJtf=true;//�Ƿ��ʼ��JTextField,Ĭ����
		//Ϊ������޸ĵĹ��ܣ������һ���������ֶ�����������������ʼ���ı������Ǻ�ɾ������Ψһ��ͨ���ĵط�
		public int initJTextField()
		{
			rowNum=UserManager.this.userTable.getSelectedRow();//���ѡ�е���
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�༭�Ķ�����");
				return 0;
			}
			
			for(int i=0;i<this.columnNum;i++){
				this.jtf[i].setText(this.ordermodel.getValueAt(rowNum, i).toString());
			}
			jtf[0].setEditable(false);//��������Ϊ�����޸�
			return 1;
		}
		public AddView(boolean isJtf)
		{
			this.isJtf=isJtf;
			//��ͨ��orderModel�����Ҫ������
			ordermodel=new OrderModel();
			String sqlfirst="select userid �û����,username �û���,userpasswd �û�����,usergrade �û�����,userjob �û�ְλ,userage �û�����,useradress �־ӵ�ַ,useranswer ���õ�����,useranswer ��,userremind ������ʾ from users";
			//String parasfirst[]={"1"};
			ordermodel.query(sqlfirst);//���Ƕ���orderby�Ѿ������õ�������
			//�������
			columnNum=ordermodel.getColumnCount();
			jlabel=new JLabel[columnNum];
			jtf=new JTextField[columnNum];
			
			//System.out.println("�������"+columnNum);
			for(int i=0;i<columnNum;i++)//�������
			{
				jtf[i]=new JTextField();
				jlabel[i]=new JLabel(ordermodel.getColumnName(i));
		
			}
		
			confirm=new JButton("ȷ��");
			cancel=new JButton("ȡ��");
			
			//���ü���
			confirm.addActionListener(this);
			cancel.addActionListener(this);
			
			jp1=new JPanel();
			jp2=new JPanel();
			jp3=new JPanel();
			//���ò��ֹ�����,jp3Ĭ��Ϊ��ʽ����
			jp1.setLayout(new GridLayout(columnNum,1));
			jp2.setLayout(new GridLayout(columnNum,1));
			
			//������
			for(int i=0;i<columnNum;i++)
			{
				
				jp1.add(jlabel[i]);
				jp2.add(jtf[i]);
			}		
			//������
			jp3.add(confirm);//Ĭ��������
			jp3.add(cancel);
			
			//�����ж�����ӻ����޸ģ�������޸��ж��Ƿ�ѡ��һ��
			if(this.isJtf) {
				if(this.initJTextField()==0){
					return;
				}
			}
			
			//��ӵ��Ի���
			this.add(jp1,"West");
			this.add(jp2,"Center");
			this.add(jp3,"South");
			
			//��ӵ��Ի���
			this.add(jp1,"West");
			this.add(jp2,"Center");
			this.add(jp3,"South");
			
			//������ʾ
			this.setSize(400,400);
			this.setLocation(400,300);
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==confirm){//�����Dialog�е����ȷ�ϰ�ť
				UserManager.this.user=new UseMode();//�ڲ�������ⲿ���Ա
				if(this.isJtf){//�޸�
					
					sql="update username=?,userpasswd=?,usergrade=?,usersex=?,userbg=?,userjob=?,userage=?,useradress=?,useraser=?,useranswer=?,userremind=? from users where userid=?";
					for(int i=0;i<paras.length-1;i++){
						paras[i]=jtf[i+1].getText();
					}
					paras[paras.length-1]=jtf[0].getText();
					
				}else{//���
					sql="insert into users values(?,?,?,?,?,?,?,?,?,?,?,?)";
					for(int i=0;i<jtf.length;i++){
						paras[i]=jtf[i].getText();
					}
					//paras[]={jtf[0].getText(),jtf[1].getText(),jtf[2].getText(),jtf[3].getText(),jtf[4].getText(),jtf[5].getText(),jtf[6].getText()};
				}
				if(UserManager.this.user.update(this.sql, this.paras)){//��������ɲ���
					JOptionPane.showMessageDialog(this,"��ɣ�");
				}else{
					JOptionPane.showMessageDialog(this,"��Ǹ������δ����ɣ�");
				}
				this.dispose();//�ر���ӶԻ���					
			}else if(e.getSource()==cancel){
				this.dispose();
			}
			UserManager.this.refresh(UserManager.this.sqlOrderby);//���ڲ����з�����Χ��
		}

	}
	/////////////////////////AddView///////////////////////////////
	//��Χ

}



