package com.scut.server.funtion;

import java.io.File;

import org.apache.log4j.Logger;

import com.scut.server.conf.MyPath;

public class FileList 
{
	public static String s = "";
	private static Logger log = Logger.getLogger("server");
	
	public static String filenames = "";
	public static String dirnames = "";
	
	public static void getFileandDir(String filePath, int rootPathIndex)
	{
		   File root = new File(filePath);
		   File[] files = root.listFiles();
		   for(File file:files)
		   {     
			    if(file.isDirectory())
			    {
			     /*
			      * �ݹ����
			      */
			    	 getFileandDir(file.getAbsolutePath(), rootPathIndex);
				     dirnames += file.getAbsolutePath().substring(rootPathIndex) + ",";
				     //filelist.add(file.getAbsolutePath());
				     //System.out.println("��ʾ"+filePath+"��������Ŀ¼�����ļ�"+file.getAbsolutePath());
				}
			    else
			    {
			    	filenames += file.getAbsolutePath().substring(rootPathIndex) + ",";
			    	//System.out.println("��ʾ"+filePath+"��������Ŀ¼"+file.getAbsolutePath());
			    }     
		   }
	}
	
	/*
	 * ͨ���ݹ�õ�ĳһ·�������е�Ŀ¼�����ļ�
	 */
	public static void getFiles(String filePath, int rootPathIndex)
	{
		   File root = new File(filePath);
		   File[] files = root.listFiles();
		   for(File file:files)
		   {     
			    if(file.isDirectory())
			    {
			     /*
			      * �ݹ����
			      */
				     getFiles(file.getAbsolutePath(), rootPathIndex);
				     s += file.getAbsolutePath().substring(rootPathIndex) + ",";
				     //filelist.add(file.getAbsolutePath());
				     //System.out.println("��ʾ"+filePath+"��������Ŀ¼�����ļ�"+file.getAbsolutePath());
				}
			    else
			    {
			    	s += file.getAbsolutePath().substring(rootPathIndex) + ",";
			    	//System.out.println("��ʾ"+filePath+"��������Ŀ¼"+file.getAbsolutePath());
			    }     
		   }
	}
	public static String getFileInfo(String filePath, int rootPathIndex)
	{
		   log.debug(MyPath.serverRootPath + filePath);
		   File root = new File(MyPath.serverRootPath + filePath);
		   File[] files = root.listFiles();
		   String s ="";
		   for(File file:files)
		   {     
			     s += file.getAbsolutePath().substring(rootPathIndex) + ":" +(file.length()) + "KB,";
			     //filelist.add(file.getAbsolutePath());
			     //System.out.println("��ʾ"+filePath+"��������Ŀ¼�����ļ�"+file.getAbsolutePath());
		   }
		   return s;
	}
//	public static void main(String[] args)
//	{
//		getFileandDir(MyPath.serverRootPath, MyPath.serverRootPath.length());
//		System.out.println(filenames);
//	}
	
}
