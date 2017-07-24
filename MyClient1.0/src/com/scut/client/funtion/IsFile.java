package com.scut.client.funtion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsFile 
{
	/*
	 * 判断是文件还是文件夹,根据文件名中含有“.",所以在上传的时候要判断，不能让文件名含有"."
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
//		String fileName = s.substring(s.lastIndexOf("\\") + 1);// 获得上传文件的文件名  
//		System.out.println(fileName);
//		if(fileName.indexOf(".")!=-1) return true;
//	    else return false;
	}
	
//	public static void main(String[] args)
//	{
//		System.out.println(isFile("D:\\a\\btxt"));
//	}
}
