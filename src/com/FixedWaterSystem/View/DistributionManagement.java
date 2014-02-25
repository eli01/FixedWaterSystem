/**
 * ���ߣ�������
 * ʱ�䣺2012.12.2
 * ���ܣ����͹���ģ���������
 * 
 * 
 * ��־��
 * 2012.12.2��ģ���ж�����ģ��δȷ��
 */
package com.FixedWaterSystem.View;
import com.FixedWaterSystem.Tools.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
public class DistributionManagement extends JPanel implements MouseListener,ActionListener{
	
	
	JSplitPane jsp_jp_controlMenu,jsp_this;//��ִ���
	JPanel jp_controlMenu,jp_array,jp_table,jp_right,jp_north,jp_south;//ǰ�����ǿ�Ƭ���֣����һ���ǰ���jp_array,jp_table�Ĳ�ִ����ұ߲���
	JLabel jl_VehicleTracking,jl_leftArray,jl_rightArray,jl_dm;//�������ٹ�
	ImagePanel controlMenu_bg_panel;//�������ı���
	Image controlMenu_bg,leftArray,rightArray;
	CardLayout cardForArray,cardForTable;//Ϊ��ť����ʾ�ֱ�����Ƭ����
	
	//��ʼ�����͹���ģ��˵����ĺ���
	public void init_controlMenu(){			
		//�������
		jp_controlMenu=new JPanel(new BorderLayout());//�߽粼��
		try {
			controlMenu_bg=ImageIO.read(new File("images/left_bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlMenu_bg_panel=new ImagePanel(controlMenu_bg);
		controlMenu_bg_panel.setLayout(new GridLayout(2,1));//���ñ������Ϊ�ղ���
		//������ǩ
		jl_dm=new JLabel("�������ģ��",new ImageIcon("images/dm_jlabel.jpg"),0);
		jl_VehicleTracking=new JLabel("�������ٹ���",new ImageIcon("images/jl_VehicleTracking.jpg"),0);
		
		//���ñ�ǩ
		//jl_dm.setBounds(5, 5, 170, 40);
		//jl_VehicleTracking.setBounds(40, 50, 100, 30);
		
		//���
		controlMenu_bg_panel.add(jl_dm);	
		controlMenu_bg_panel.add(jl_VehicleTracking);
		//��һ�����
		jp_north=new JPanel(new BorderLayout());
		jp_south=new JPanel(null);
		jp_north.add(controlMenu_bg_panel);
		jsp_jp_controlMenu=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp_north,jp_south);
		jsp_jp_controlMenu.setDividerLocation(100);
		jsp_jp_controlMenu.setDividerSize(20);
		this.jp_controlMenu.add(jsp_jp_controlMenu);
	}
	
	//��ʼ����ͷ���ĺ���	
	public void init_array()
	{
		//�������
		jl_leftArray=new JLabel(new ImageIcon("images/leftArray.jpg"));
		jl_rightArray=new JLabel(new ImageIcon("images/rightArray.jpg"));
		jp_array=new JPanel(cardForArray);
		//ע�����
		jl_rightArray.addMouseListener(this);
		jl_leftArray.addMouseListener(this);
		//������
		jp_array.add(jl_leftArray,"0");
		jp_array.add(jl_rightArray,"1");
		
	}
	
	//��ʼ��������ĺ���
	public void init_jp_table(){
		jp_table=new JPanel(cardForTable);
		//��ʱ��
		OrderManage om=new OrderManage();
		jp_table.add(om,"0");
	}
	//���캯��
	public DistributionManagement()
	{
		//���ø�����ʼ��������ʼ������
		this.setLayout(new BorderLayout());
		this.init_controlMenu();
		this.init_array();
		this.init_jp_table();
	
		//��ϳ�ʼ����ĸ�������
		jp_right=new JPanel(new BorderLayout());//����jp_array��jp_table
		jp_right.add(jp_array,"West");
		jp_right.add(jp_table,"East");
	
		jsp_this=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jp_controlMenu,jp_right);
		jsp_this.setDividerLocation(180);
		jsp_this.setDividerSize(0);
	
		//��ӵ�this
		this.add(jsp_this);
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jl_leftArray){
			this.cardForArray.show(jp_array, "1");
			this.jsp_this.setDividerLocation(0);
		}else if(e.getSource()==jl_rightArray){
			this.cardForArray.show(jp_array, "0");
			this.jsp_this.setDividerLocation(180);
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
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
