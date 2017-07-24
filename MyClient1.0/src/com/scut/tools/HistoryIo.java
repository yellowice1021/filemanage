package com.scut.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.scut.client.user.MyUser;

/*
 * 对本地格式化文件进行操作
 */
public class HistoryIo 
{
	public static final String path = "log/db.log"; 
	private static Logger log = Logger.getLogger("client");
	
	/*
	 * 插入一条记录
	 */
	public static boolean writeLine(Object[] object)
	{
		PrintWriter pw = null;
		boolean flag = true;
		String line = "";
		//设置追加
		FileWriter fw = null;

		try 
		{
			fw = new FileWriter(path, true); //  throw IOException
			pw = new PrintWriter(fw);
			for(Object e: object)
			{
				line += e.toString() + "\t";
			}
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
	
	/*
	 * 一次性独读出所以记录
	 */
	public static List<Object[]> getObject()
	{
		//Object[] object = null;
		List<Object[]> list = new Vector<Object[]>();
		Scanner sc = null;
		String line;
		try {
			sc = new Scanner(new File("log/db.log"));
			while(sc.hasNextLine())
			{
				line = sc.nextLine();
				System.out.println(line);
				Object[] object = line.split("\t");
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
	
//	public static void main(String[] args)
//	{
//		System.out.println(HistoryIo.getObject());
//	}
}
