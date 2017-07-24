package com.scut.client.ui;

import java.util.HashMap;

public class MyHashServerTree {
	public static HashMap<String, Integer>mp = new HashMap<String, Integer>();
	public static void setVisit(String name)
	{
		mp.put(name, 1);
	}
	public static void setUnVisit(String name)
	{
		mp.put(name, 0);
	}
	public static boolean isVisited(String name)
	{
		if (mp.containsKey(name)) return true;
		else return false;
	}
	
//	public static void main(String[] args)
//	{
//		String a ="aa";
//		String b ="aa";
//		String c = "bb";
//		setVisit(a);
//		setUnVisit(b);
//		System.out.println(mp.size());
//	}
}
