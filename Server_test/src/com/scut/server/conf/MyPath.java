package com.scut.server.conf;

import java.io.File;
import java.io.IOException;

public class MyPath 
{
	public static final String serverRootPath = "D:\\filemanage\\";
	public static void init()
	{
		//在本地先新建该文件
        File file = new File(serverRootPath);
        file.mkdirs();
        //该文件的父目录不存在，先创建
//        if (!file.getParentFile().exists())  
//		{
//			file.getParentFile().mkdirs();
//		}
//       
//        //该文件不存在，则新建
//        if( !file.exists() )     
//    	{
//        	try {
//				file.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
	}
}
