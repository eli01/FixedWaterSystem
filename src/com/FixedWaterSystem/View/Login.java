/**(2)
 * ���ߣ�������
 * ʱ�䣺2012.11.26/10:49
 * ���ܣ���ʾ��¼���棻��֤�û���ݣ���ת����
 * ��־������һ���ǳ����Ľ��棬������������ϵͳ��ԭ�򣡣���
 * 20:34�˳���ȡ���İ�ť��û���
 */
package com.FixedWaterSystem.View;
import javax.imageio.ImageIO;
import javax.swing.*;

import com.FixedWaterSystem.Model.*;
import com.FixedWaterSystem.Tools.Word;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Login extends JDialog implements ActionListener{//z����̳е��ǶԻ���
	
	int w=Toolkit.getDefaultToolkit().getScreenSize().width;
	int h=Toolkit.getDefaultToolkit().getScreenSize().height;
	//������Ҫ�����
	JTextField jUser;
	JPasswordField jPasswd;
	JButton jbLogin,jbExit;
	BackImage backimage;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}
	//���캯��
	public Login()
	{
		//�������
		jUser=new JTextField(10);
		jPasswd=new JPasswordField(10);
		jbLogin=new JButton(new ImageIcon("images/login_button.jpg"));
		//jbExit=new JButton(new ImageIcon("images/exit_button.jpg"));
		backimage=new BackImage();
	
		jUser.setBounds(148,53,146,23);
		jPasswd.setBounds(148, 97, 146, 23);
		jbLogin.setBounds(343, 52, 80, 71);
		//jbExit.setBounds(480, 560, 70, 40);
		backimage.setBounds(0, 0, 471, 180);
		//���ð���
		jUser.setBorder(BorderFactory.createLoweredBevelBorder());
		jPasswd.setBorder(BorderFactory.createLoweredBevelBorder());
		//���ü���
		jbLogin.addActionListener(this);
		//jbExit.addActionListener(this);
		
		//����������������
		Container ct=this.getContentPane();//��һ��JDialog������
		ct.add(jUser);
		ct.add(jPasswd);
		ct.add(jbLogin);
		//ct.add(jbExit);
		ct.add(backimage);
		
		//���ò��ֹ�������Ĭ�������֣�
		this.setLayout(null);
		
		//����JDialog
		this.setLocation(w/2-230,h/2-90);
		//this.setUndecorated(true);//��ʹ�ñ߿�
		this.setSize(471,180);
		this.setResizable(false);
		this.setVisible(true);
		
	
	}
	
	
	//��һ���ڲ���  ���ܣ����뱳��ͼƬ��������
	class BackImage extends JPanel
	{
		Image image;
		//���캯��
		public BackImage()
		{
			try {
				image=ImageIO.read(new File("images/login_1.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("��½�������뱳��ͼƬ���쳣��");
			}
		}
		
		//��дpaintComponent()
		public void paintComponent(Graphics g)
		{
			g.drawImage(image, 0, 0, 471, 180,this);
		}
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jbLogin)//����½��ť
		{
			
			String naOrID=jUser.getText().trim();//��ȡԱ��������ID
			String password=new String(this.jPasswd.getPassword());//��ȡ���룬��߰�ȫ��
			
			UserModel um=new UserModel();
			if(um.checkUser(naOrID, password)){
				this.dispose();
				//��ת������ҳ��
				/*
				if(um.getPosition(naOrID).equals(anObject)){
					Window1 w1=new Window1(naOrID);
				}
				*/
				String gradeStr=um.getContent(naOrID,"select usergrade from users where userid=?");
				String name=um.getContent(naOrID, "select username from users where userid=?");				
				//new Index();
				Window1 w1=new Window1(gradeStr,name);
				
			}else{
				jUser.setText("");
				jPasswd.setText("");
				JOptionPane.showMessageDialog(this,"��¼�����������");
			}
	
		}
	}
}
