package com.scut.server.conf;

import java.io.File;
import java.io.IOException;

public class MyPath 
{
	public static final String serverRootPath = "D:\\filemanage\\";
	public static void init()
	{
		//�ڱ������½����ļ�
        File file = new File(serverRootPath);
        file.mkdirs();
        //���ļ��ĸ�Ŀ¼�����ڣ��ȴ���
//        if (!file.getParentFile().exists())  
//		{
//			file.getParentFile().mkdirs();
//		}
//       
//        //���ļ������ڣ����½�
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
