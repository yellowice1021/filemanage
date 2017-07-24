package com.scut.client.user;

public class MyUser 
{
	public static String userName;
	public static String state;
	public static int privilage;
	static
	{
		userName = "游客";       //游客只能做本地操作
		state = "离线";
		privilage = 5;
	}
	
	public static boolean scanServer()
	{
		if (privilage <=3 ) return true;
		else return false;
	}
	
	public static boolean download()
	{
		if (privilage <= 2) return true;
		else return false;
	}
	public static boolean upload()
	{
		if (privilage <= 2) return true;
		else return false;
	}
	public static boolean scanLog()
	{
		if (privilage <=3 ) return true;
		else return false;
	}
	
	public static boolean userHanel()
	{
		if (privilage <= 1 ) return true;
		else return false;
	}
	
	public static boolean searchFile()
	{
		if (privilage <= 3 ) return true;
		else return false;
	}
}
