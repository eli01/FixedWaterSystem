package com.FixedWaterSystem.Tools;
import java.util.*;
public class DataFactory {
	//�Զ������¶�����
	public static String[] omSQL=new String[10];
	public static String[] cmSQL= new String[10];
	public static String[] umSQL= new String[10];
	public static String[] emSQL= new String[10];
	public static String[] pmSQL= new String[10];
	public static String[] mmSQL= new String[10];
	public DataFactory(){
		//��ʼ��jtable
		omSQL[0]="select OID ҵ�񵥺�,CID �ͻ�����,SendTime �ͻ�ʱ��,OmonerySum ���,Warehouse �ֿ� from orders order by SendTime desc";
		//���
		omSQL[1]="insert into orders values(?,?,?,?,?,?,?,?,?,?)";
		//�޸�
		omSQL[2]="update orders set ";
		//ɾ��
		omSQL[3]="";
		//����������
		omSQL[4]="";
		//����������
		omSQL[5]="";
		//��ʱ������
		omSQL[6]="";
		//��ʱ�併��
		omSQL[7]="";
		//����������
		omSQL[8]="";
		//�����۽���
		omSQL[9]="";
	}

	public static String getID(String strID,String letter,boolean b){
		//��ˮ������������ɣ����ں����к�������
		// 200805270000  200805270001  200805270002 
		Calendar c=Calendar.getInstance();
		c.setTime(new java.util.Date());
		int   year = c.get(Calendar.YEAR);   
		int   month = c.get(Calendar.MONTH)+1;   
		int   day   = c.get(Calendar.DAY_OF_MONTH);   
		int   hour = c.get(Calendar.HOUR_OF_DAY);   
		int   minute =c.get(Calendar.MINUTE);   
		int   second =c.get(Calendar.SECOND); 
		
		//ת��Ϊ�ַ���
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
		//ȡ�����5λ->ת����int->��һ
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
	//�Զ������³�����ˮ��
	//�Զ�����ע���û���ID�ţ����Թ����ͻ��ĵȼ���Ƭ������ʱ�����Ϣ�������Ȳ�������ô�ࣩ
public static String getCID(String strCID){
		//��ˮ������������ɣ����ں����к�������
		// 200805270000  200805270001  200805270002 
		Calendar c=Calendar.getInstance();
		c.setTime(new java.util.Date());
		int   year = c.get(Calendar.YEAR);   
		int   month = c.get(Calendar.MONTH)+1;   
		int   day   = c.get(Calendar.DAY_OF_MONTH);   
//		int   hour = c.get(Calendar.HOUR_OF_DAY);   
//		int   minute =c.get(Calendar.MINUTE);   
//		int   second =c.get(Calendar.SECOND); 
		
		//ת��Ϊ�ַ���
		String yearStr=String.valueOf(year);
		String monthStr=String.valueOf(month);
		String dayStr=String.valueOf(day);
//		String hourStr=String.valueOf(hour);
//		String minuteStr=String.valueOf(minute);
//		String secondStr=String.valueOf(second);
		String newOIDHead=yearStr+monthStr+dayStr;
		//ȡ�����5λ->ת����int->��һ
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
	//��ˮ������������ɣ����ں����к�������
	// 200805270000  200805270001  200805270002 
	Calendar c=Calendar.getInstance();
	c.setTime(new java.util.Date());
	int   year = c.get(Calendar.YEAR);   
	int   month = c.get(Calendar.MONTH)+1;   
	int   day   = c.get(Calendar.DAY_OF_MONTH);   
	int   hour = c.get(Calendar.HOUR_OF_DAY);   
//	int   minute =c.get(Calendar.MINUTE);   
//	int   second =c.get(Calendar.SECOND); 
	
	//ת��Ϊ�ַ���
	String yearStr=String.valueOf(year);
	String monthStr=String.valueOf(month);
	String dayStr=String.valueOf(day);
	String hourStr=String.valueOf(hour);
//	String minuteStr=String.valueOf(minute);
//	String secondStr=String.valueOf(second);
	String newOIDHead=yearStr+monthStr+dayStr+hourStr;
	//ȡ�����5λ->ת����int->��һ
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