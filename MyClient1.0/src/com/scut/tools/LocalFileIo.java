package com.scut.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import org.apache.log4j.Logger;

public class LocalFileIo 
{
	public static final String path = "log/FileInfo.log"; 
	private static Logger log = Logger.getLogger("client");
	
	/*
	 * 插入一条记录
	 */
	public static boolean writeLine(String line)
	{
		PrintWriter pw = null;
		boolean flag = true;
		//设置追加
		FileWriter fw = null;

		try 
		{
			fw = new FileWriter(path, true); //  throw IOException
			pw = new PrintWriter(fw);
			pw.println(line);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
			flag = false;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1);
			flag = false;
		}
		finally
		{
			if (pw != null)
			{
				pw.close();
			}
			if (fw != null)
			{
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.warn(e);
				}
			}
		}

		return flag;
	}

	
	public static String getFileInfo(String path)
	{
		String res = "";
		Scanner sc = null;
		String line = "";
		String tmpPath  ="";
		try {
			sc = new Scanner(new File("log/FileInfo.log"));
			while(sc.hasNextLine())
			{
				line = sc.nextLine();
				System.out.println(line);
				tmpPath = line.split(",")[0];
				System.out.println(path+":"+tmpPath);
				if (tmpPath.equals(path)) 
				{
					
					res = line.substring(path.length()+1);
					System.out.println(tmpPath +"," +res);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		}
		finally
		{
			if (sc != null)
			{
				sc.close();
			}
		}
		return res;
	}
	
	public static List<Object[]> getObject()
	{
		//Object[] object = null;
		List<Object[]> list = new Vector<Object[]>();
		Scanner sc = null;
		String line;
		try {
			sc = new Scanner(new File("log/FileInfo.log"));
			while(sc.hasNextLine())
			{
				line = sc.nextLine();
				System.out.println(line);
				Object[] object = line.split(",");
				list.add(object);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		}
		finally
		{
			if (sc != null)
			{
				sc.close();
			}
		}
		return list;
	}

	public static List<Object[]> unionSearch(String[] s)
	{
		List<Object[]> res = new ArrayList<Object[]>();
		Scanner sc = null;
		String line = "";
		
		String[] tmp;
		int j = 0;
		boolean flag;
		try {
			sc = new Scanner(new File("log/FileInfo.log"));
			while(sc.hasNextLine())
			{
				line = sc.nextLine();
				//System.out.println(line);
				String lines[] = {"","","","","","","","","",""};
				tmp = line.split(",");
				j = 0;
				while(j < tmp.length)
				{
					lines[j] = tmp[j];
					j++;
				}
				flag = false;
				for (int i = 0; i < s.length; i++)
				{
					//log.debug(lines[i+1]+"    "+s[i]);
					if (s[i].equals("")||s[i].equals(" ") || s[i] == null || s[i].equals("NULL") ) continue;			
					if (lines[i+1].indexOf(s[i]) == -1) {
						flag = true;
						break;
					}
				}
				if (flag == false) res.add(lines);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		}
		finally
		{
			if (sc != null)
			{
				sc.close();
			}
		}
		return res;
		
	}

	public static List<Object[]> singleSearch(String[] s) {
		// TODO Auto-generated method stub
		List<Object[]> res = new ArrayList<Object[]>();
		Scanner sc = null;
		String line = "";
		
		String[] tmp;
		int j = 0;
		try {
			sc = new Scanner(new File("log/FileInfo.log"));
			while(sc.hasNextLine())
			{
				line = sc.nextLine();
				//System.out.println(line);
				String lines[] = {"","","","","","","","","",""};
				tmp = line.split(",");
				j = 0;
				while(j < tmp.length)
				{
					lines[j] = tmp[j];
					j++;
				}
				for (int i = 0; i < s.length; i++)
				{
					//System.out.println(lines[i+1]+"    "+s[i]);
					if (s[i].equals("")||s[i].equals(" ") || s[i] == null || lines[i+1].equals("")) continue;			
					if (lines[i+1].indexOf(s[i]) != -1) {
						//System.out.println(line+"  is"+s[i]+":"+ line.indexOf(s[i]));
						res.add(lines);
						break;
					}
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		}
		finally
		{
			if (sc != null)
			{
				sc.close();
			}
		}
		return res;
		
	}
}
