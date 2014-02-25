package com.FixedWaterSystem.Tools;
import java.util.*;
public class DataFactory {
	//自动产生新订单号
	public static String[] omSQL=new String[10];
	public static String[] cmSQL= new String[10];
	public static String[] umSQL= new String[10];
	public static String[] emSQL= new String[10];
	public static String[] pmSQL= new String[10];
	public static String[] mmSQL= new String[10];
	public DataFactory(){
		//初始化jtable
		omSQL[0]="select OID 业务单号,CID 客户卡号,SendTime 送货时间,OmonerySum 金额,Warehouse 仓库 from orders order by SendTime desc";
		//添加
		omSQL[1]="insert into orders values(?,?,?,?,?,?,?,?,?,?)";
		//修改
		omSQL[2]="update orders set ";
		//删除
		omSQL[3]="";
		//按数量降序
		omSQL[4]="";
		//按数量升序
		omSQL[5]="";
		//按时间升序
		omSQL[6]="";
		//按时间降序
		omSQL[7]="";
		//按单价升序
		omSQL[8]="";
		//按单价降序
		omSQL[9]="";
	}

	public static String getID(String strID,String letter,boolean b){
		//流水号由两部分组成：日期和序列号像这样
		// 200805270000  200805270001  200805270002 
		Calendar c=Calendar.getInstance();
		c.setTime(new java.util.Date());
		int   year = c.get(Calendar.YEAR);   
		int   month = c.get(Calendar.MONTH)+1;   
		int   day   = c.get(Calendar.DAY_OF_MONTH);   
		int   hour = c.get(Calendar.HOUR_OF_DAY);   
		int   minute =c.get(Calendar.MINUTE);   
		int   second =c.get(Calendar.SECOND); 
		
		//转化为字符串
		String yearStr=String.valueOf(year);
		String monthStr=String.valueOf(month);
		String dayStr=String.valueOf(day);
		String hourStr=String.valueOf(hour);
		String minuteStr=String.valueOf(minute);
		String secondStr=String.valueOf(second);
		
		String newIDHead=yearStr+monthStr+dayStr+hourStr+minuteStr+secondStr;
		if(!b){
			newIDHead=yearStr+monthStr+dayStr;
		}
		//取得最后5位->转换成int->加一
		int ii=Integer.parseInt(strID.substring(strID.length()-4))+1;
		int len=String.valueOf(ii).length();
		String strTail="";
		switch (len){   
		case 1:
		strTail="000"+String.valueOf(ii);
		break;
		case 2:
		strTail="00"+String.valueOf(ii);
		break;
		case 3:
		strTail="0"+String.valueOf(ii);
		break;
		case 4:
		strTail=String.valueOf(ii);
		break;
		default:
		}
		
		String newID=letter+newIDHead+strTail;
		return newID;
	}
	/*
}
	//自动产生新出货流水号
	//自动生成注册用户的ID号（可以关联客户的等级、片区加入时间等信息，这里先不考虑那么多）
public static String getCID(String strCID){
		//流水号由两部分组成：日期和序列号像这样
		// 200805270000  200805270001  200805270002 
		Calendar c=Calendar.getInstance();
		c.setTime(new java.util.Date());
		int   year = c.get(Calendar.YEAR);   
		int   month = c.get(Calendar.MONTH)+1;   
		int   day   = c.get(Calendar.DAY_OF_MONTH);   
//		int   hour = c.get(Calendar.HOUR_OF_DAY);   
//		int   minute =c.get(Calendar.MINUTE);   
//		int   second =c.get(Calendar.SECOND); 
		
		//转化为字符串
		String yearStr=String.valueOf(year);
		String monthStr=String.valueOf(month);
		String dayStr=String.valueOf(day);
//		String hourStr=String.valueOf(hour);
//		String minuteStr=String.valueOf(minute);
//		String secondStr=String.valueOf(second);
		String newOIDHead=yearStr+monthStr+dayStr;
		//取得最后5位->转换成int->加一
		int ii=Integer.parseInt(strCID.substring(strCID.length()-4))+1;
		int len=String.valueOf(ii).length();
		String strTail="";
		switch (len){   
		case 1:
		strTail="000"+String.valueOf(ii);
		break;
		case 2:
		strTail="00"+String.valueOf(ii);
		break;
		case 3:
		strTail="0"+String.valueOf(ii);
		break;
		case 4:
		strTail=String.valueOf(ii);
		break;
		default:
		}
		String newOID="C"+newOIDHead+strTail;
		return newOID;
	 }
/*
public static String getPID(String strPID){
	//流水号由两部分组成：日期和序列号像这样
	// 200805270000  200805270001  200805270002 
	Calendar c=Calendar.getInstance();
	c.setTime(new java.util.Date());
	int   year = c.get(Calendar.YEAR);   
	int   month = c.get(Calendar.MONTH)+1;   
	int   day   = c.get(Calendar.DAY_OF_MONTH);   
	int   hour = c.get(Calendar.HOUR_OF_DAY);   
//	int   minute =c.get(Calendar.MINUTE);   
//	int   second =c.get(Calendar.SECOND); 
	
	//转化为字符串
	String yearStr=String.valueOf(year);
	String monthStr=String.valueOf(month);
	String dayStr=String.valueOf(day);
	String hourStr=String.valueOf(hour);
//	String minuteStr=String.valueOf(minute);
//	String secondStr=String.valueOf(second);
	String newOIDHead=yearStr+monthStr+dayStr+hourStr;
	//取得最后5位->转换成int->加一
	int ii=Integer.parseInt(strPID.substring(strPID.length()-4))+1;
	int len=String.valueOf(ii).length();
	String strTail="";
	switch (len){   
	case 1:
	strTail="000"+String.valueOf(ii);
	break;
	case 2:
	strTail="00"+String.valueOf(ii);
	break;
	case 3:
	strTail="0"+String.valueOf(ii);
	break;
	case 4:
	strTail=String.valueOf(ii);
	break;
	default:
	}
	String newOID="P"+newOIDHead+strTail;
	return newOID;
 }*/
}