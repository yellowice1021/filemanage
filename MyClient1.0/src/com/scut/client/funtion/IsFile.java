package com.scut.client.funtion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsFile 
{
	/*
	 * �ж����ļ������ļ���,�����ļ����к��С�.",�������ϴ���ʱ��Ҫ�жϣ��������ļ�������"."
	 */
	public static boolean isFile(String s)
	{
		String res = FileList.isfile(s);
		if (res.equals("dir"))
		{
			return false;
		}
		else if (res.equals("file"))return true;
		else return false;
		
//		boolean flag = true;
//		String fileName = s.substring(s.lastIndexOf("\\") + 1);// ����ϴ��ļ����ļ���  
//		System.out.println(fileName);
//		if(fileName.indexOf(".")!=-1) return true;
//	    else return false;
	}
	
//	public static void main(String[] args)
//	{
//		System.out.println(isFile("D:\\a\\btxt"));
//	}
}
