package com.scut.server.funtion;

import java.io.File;
import java.text.SimpleDateFormat;

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
	/*
	 * ������ʷ�汾�ļ�
	 */
	public static String getFileVersion(String filePath, String fileName, String fileType)
	{
		String fileVersion = "";
		String path = MyPath.serverRootPath + filePath.substring(0, filePath.lastIndexOf("\\"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(fileName.indexOf("_") != -1)
		{
			fileName = fileName.substring(0, fileName.lastIndexOf("_"));
		}

		File root = new File(path);
		File[] files = root.listFiles();
		for(File file:files)
		{   
			if(!file.isDirectory())
			{
				String fileNameEach = file.getName();
				if(fileNameEach.startsWith(fileName) && fileNameEach.endsWith(fileType))
				{
					fileVersion += fileNameEach + "#" + sf.format(file.lastModified());
					fileVersion += ",";
				}
			}
		}
		   
		return fileVersion;
	}
//	public static void main(String[] args)
//	{
//		getFileandDir(MyPath.serverRootPath, MyPath.serverRootPath.length());
//		System.out.println(filenames);
//	}
	
}
