package com.FixedWaterSystem.Tools;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
public class ImagePanel extends JPanel{

	/**
	 * @param args
	 */
	Image im;
	//构造函数指定该panel的大小
	public ImagePanel(Image im)
	{
		this.im=im;
		//默认背景图的大小是自适应的
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h);
	}
	public void paintComponent(Graphics g)
	{
		//清屏
		super.paintComponents(g);
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(),this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
