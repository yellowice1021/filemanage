package com.scut.server.conf;

import java.io.File;
import java.io.IOException;

import com.scut.server.funtion.LogHandle;

public class Conf 
{
	public static String DirPicPath =  MyPath.serverRootPath + "pic";     //���Ŀ¼ͼƬ
	public static String DirPicPaths =  "pic";     //���Ŀ¼ͼƬ
	public static String recyclePath = MyPath.serverRootPath + "recycle"; //��Ż����ļ�
	public static String tmpPath = MyPath.serverRootPath + "tmp";
	public static String cache = MyPath.serverRootPath + "cache";         //cache
	public static String logDatePath = MyPath.serverRootPath + "logDate.txt";
	
	public static String docpath = MyPath.serverRootPath + "doc";
	
	public static void init()
	{
		   System.out.println("conf inital");
		   File file0 = new File(cache);
		   file0.mkdirs();


	        File file = new File(DirPicPath);
	        file.mkdirs();
 
	        
	      //�ڱ������½����ļ�
	        File file1 = new File(recyclePath);
	        file1.mkdirs();

	        
	      //�ڱ������½����ļ�
	        File file2 = new File(tmpPath);
	        file2.mkdirs();
	        
	        File file4 = new File(docpath);
	        file4.mkdirs();

	        
	        File file3 = new File(logDatePath);
	        //���ļ��ĸ�Ŀ¼�����ڣ��ȴ���
	        if (!file3.getParentFile().exists())  
			{
				file3.getParentFile().mkdirs();
			}
	        //���ļ������ڣ����½�
	        if( !file3.exists() )     
	    	{
	        	try {
					file3.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	LogHandle.writeDate("100");                     //Ĭ������Ϊһ����
	        }
	        
	        
	}
}
