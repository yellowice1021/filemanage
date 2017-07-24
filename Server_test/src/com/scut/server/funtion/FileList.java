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
			      * 递归调用
			      */
			    	 getFileandDir(file.getAbsolutePath(), rootPathIndex);
				     dirnames += file.getAbsolutePath().substring(rootPathIndex) + ",";
				     //filelist.add(file.getAbsolutePath());
				     //System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getAbsolutePath());
				}
			    else
			    {
			    	filenames += file.getAbsolutePath().substring(rootPathIndex) + ",";
			    	//System.out.println("显示"+filePath+"下所有子目录"+file.getAbsolutePath());
			    }     
		   }
	}
	
	/*
	 * 通过递归得到某一路径下所有的目录及其文件
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
			      * 递归调用
			      */
				     getFiles(file.getAbsolutePath(), rootPathIndex);
				     s += file.getAbsolutePath().substring(rootPathIndex) + ",";
				     //filelist.add(file.getAbsolutePath());
				     //System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getAbsolutePath());
				}
			    else
			    {
			    	s += file.getAbsolutePath().substring(rootPathIndex) + ",";
			    	//System.out.println("显示"+filePath+"下所有子目录"+file.getAbsolutePath());
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
			     //System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getAbsolutePath());
		   }
		   return s;
	}
//	public static void main(String[] args)
//	{
//		getFileandDir(MyPath.serverRootPath, MyPath.serverRootPath.length());
//		System.out.println(filenames);
//	}
	
}
