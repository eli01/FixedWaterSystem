package com.FixedWaterSystem.Tools;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
public class ImagePanel extends JPanel{

	/**
	 * @param args
	 */
	Image im;
	//���캯��ָ����panel�Ĵ�С
	public ImagePanel(Image im)
	{
		this.im=im;
		//Ĭ�ϱ���ͼ�Ĵ�С������Ӧ��
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h);
	}
	public void paintComponent(Graphics g)
	{
		//����
		super.paintComponents(g);
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(),this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
