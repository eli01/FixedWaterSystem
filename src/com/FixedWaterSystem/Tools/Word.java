/**
 * 作者：孟庆申
 * 时间：2012.11.25/21. 37
 * 功能：创建字体格式类，这种封装方便后面字体格式的调整
 * 日记：用静态成员变量可在不创建实例的情况下访问，但有一定系统风险
 */
package com.FixedWaterSystem.Tools;
import java.awt.Font;
import java.awt.font.*;
public class Word {
	
	public static Font f1=new Font("幼圆",Font.ROMAN_BASELINE,16);
	public static Font f2=new Font("幼圆",Font.ROMAN_BASELINE,14);
	public static Font f3=new Font("幼圆",Font.ROMAN_BASELINE,12);
	public static Font f4=new Font("黑体",Font.TRUETYPE_FONT,12);
	public static Font f5=new Font("黑体",Font.PLAIN,20);
	public static String username="";
	public static String usergrade="";
	
}
