/**(1)
 * ���ߣ�������
 * ʱ�䣺2012.11.25/20:54
 * ���ܣ�ʵ�ֵ�¼���ڵ���ǰ��һ��������Ч��
 * ������¼��
 *20��55��������ƻ��ο�֮ǰ������¥������������иĽ�������������ʽ��
 *21:46�����߳������ַ�ʽ��1���̳�Thread�ࣻ��2��ʵ��Runnable�ӿڣ�������õڶ��֡���Ϊjava�ǵ�һ�̳�
 */
package com.FixedWaterSystem.View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.FixedWaterSystem.Tools.*;
public class Index extends JWindow implements Runnable{//�ø���ʵ��һ���̵߳Ľӿڣ�����߳�������������

	LoadPane loadpane;	//����
	int width,height;//�û���Ļ�ķֱ���

	//���캯��
	public Index()
	{
		loadpane=new LoadPane();//ʵ����LoadPane��ͬʱ�������̱߳�������
		
		//Ϊ����index���JWindow��λ����Ӧ��ͬ��С����ʾ����������Ҫ�õ��û���ʾ���ķֱ���
		width=Toolkit.getDefaultToolkit().getScreenSize().width;
		height=Toolkit.getDefaultToolkit().getScreenSize().height;
		//������
		this.add(loadpane);//��loadpane���JPanel����Index���JWindow��
		//����Index
		this.setSize(1002,637);
		this.setLocation(width/2-501,height/2-318);
		this.setVisible(true);//��ʾ
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Index index=new Index();
		Thread t=new Thread(index);
		t.start();//�������������������ֹ�̵߳��̣߳�������ûʲô���壬�Ǻǣ����е�֣�
	}
	public void run() {//����Index���̵߳�ִ�����
		// TODO Auto-generated method stub
		//�ȼ�����ɺ���ת�û���¼����
		while(true)
		{
			try {
				Thread.sleep(4700);//11.2�����ת
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//new Login();//��ת
			this.dispose();//�ر�Index���JWindow
			break;//���������̵߳��߳�
		}

	}

}
class LoadPane extends JPanel implements Runnable//ʵ�ּ��ع��������
{

	//int w=Toolkit.getDefaultToolkit().getScreenSize().width;
	//int h=Toolkit.getDefaultToolkit().getScreenSize().height;
	Thread t;
	int x=10;
	int i=0,j=40,u=10;
	//String gg[]={"ϵ","ͳ","��","��","��","��","��","��","��"};
//	for(int ii=0;ii<gg.length;ii++)
//	{
//		gg[ii].setFont(Word.f2);
//	}
	int k=0,tt=0;

	
	boolean ifok=true;
	//int width=180;
	//int height=0;
	//int dian=0;
	
	//���캯��
	public LoadPane()
	{
		t=new Thread(this);
		t.start();//�����߳�
	}
	
	//�����̵߳ĺ���
	public void run()
	{
		while(true)
		{
			if(x<=380) repaint();//�����paintComponent()
			
			try
			{
				Thread.sleep(20);
				i++;//ÿ0.07��i����һ������
				j=j-6;
				u=u+10;
				//if(tt==3) width=width-20;
				
				if(i==4)
				{
					//tt++;
					//if(ifok&&x>120+k*20) k++;
					//if(k>=gg.length-1) ifok=false;
					x=x+10;
					i=0;
					j=40;
					//u=10;
					//dian++;
					//if(dian>3)dian=0;
					
				}
			}catch(InterruptedException  e){
				System.out.println("�߳��жϣ�");;
				}finally{
					
				}
			
		}
	}
	public void paintComponent(Graphics g)
	{
		//super.paintComponent(g);
		//��������
		int fixX=301,fixY=100;
		int thick=10;
		//����ͼƬ
		Image image;
		image=Toolkit.getDefaultToolkit().getImage("images\\index.jpg");//��ñ���ͼƬ
		g.drawImage(image,0, 0, this.getWidth(), this.getHeight(), this);//�û��ʻ������ͼƬ
		
		int r=(int)(Math.random()*255);//���0-255֮��������
		int b=(int)(Math.random()*255);
		int y=(int)(Math.random()*255);
		
		g.setColor(new Color(253,250,250));//����������һ����ɫ
		//g.fillRect(x+fixX,210+fixY,390-x,30-thick);//�û��ʻ�һ��ʵ�ľ���
		g.fillRect(10+fixX,210+fixY,x,30-thick);//�û��ʻ�һ��ʵ�ľ���
		g.setColor(new Color(253,250,250));
		if(i>1) g.fillRect(x+fixX, 225-(j+20)/2+fixY,10,j+20-thick);
		if(j>25) g.setColor(new Color(r,b,y));
		else g.setColor(new Color(123,194,252));
		g.fillRect(x+fixX, 225-j/2+fixY,10,j-thick);
		//g.setColor(new Color(123,194,252));
		
		g.setColor(new Color(255,255,255));
		g.drawRect(10+fixX, 210+fixY, 380, 30-thick);//���ľ���
		//g.drawRect(500, 900, 380, 30);//���ľ���
		
	}
	
}
