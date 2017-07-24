package com.scut.server.funtion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DirHandle 
{
	public static boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	
	public static boolean deleteDirectory(String sPath) {  
	    
		boolean flag = true;
	    File dirFile = new File(sPath);  
	    //���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    flag = true;  
	    //ɾ���ļ����µ������ļ�(������Ŀ¼)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //ɾ�����ļ�  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //ɾ����Ŀ¼  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //ɾ����ǰĿ¼  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}  
	
	/*
	 * �����⣬����ʹ��
	 */
	@Deprecated
	public static boolean moveFile(String sourcePath, String aimPath)
	{
		boolean flag = true;
		File sourceFile = new File(sourcePath); 
		File aimFile = new File(aimPath);
		if (!aimFile.getParentFile().exists())  
		{
			aimFile.getParentFile().mkdirs();
		}
		if (!aimFile.exists())
		{
			try {
				aimFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        if (sourceFile.renameTo(aimFile) )
        {  
            System.out.println("File is moved successful!");  
        } else {  
        	flag = false;
            System.out.println("File is failed to move!");  
        }  
        return flag;
	}
	
	/**
	 * �ƶ��ļ�
	 */
	public static boolean moveToRecycle(String srcPath, String destPath) {
		boolean flag = false;
		File srcFile = new File(srcPath);
		File destFile = new File(destPath);

		if (!destFile.getParentFile().exists())  
		{
			destFile.getParentFile().mkdirs();
		}
		if (!destFile.exists())
		{
			try {
				destFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileInputStream fis = new FileInputStream(srcFile);
			FileOutputStream fos = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int c;
			while ((c = fis.read(buf)) != -1) {
				fos.write(buf, 0, c);
			}
			fos.flush();
			fis.close();
			fos.close();

			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		srcFile.delete();
		return flag;
	}
	/**
	 * �ƶ��ļ�
	 */
	public static boolean moveBackRecycle(String srcPath, String destPath) {
		
		boolean flag = false;
		File srcFile = new File(srcPath);
		File destFile = new File(destPath);
		
		if (!destFile.getParentFile().exists())  
		{
			destFile.getParentFile().mkdirs();
		}
		if (!destFile.exists())
		{
			try {
				destFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileInputStream fis = new FileInputStream(srcFile);
			FileOutputStream fos = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int c;
			while ((c = fis.read(buf)) != -1) {
				fos.write(buf, 0, c);
			}
			fos.flush();
			fis.close();
			fos.close();

			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		srcFile.delete();
//		return flag;
//		boolean flag = false;
//		File srcFile = new File(srcPath);
//		String fileName = srcFile.getName();
//		String destPath = destDir + File.separator + fileName;
//		File destFile = new File(destPath);
//		String newPath = "";
//
//		if (destFile.exists()) { // Ŀ��Ŀ¼������ͬ���ļ�����������
//			String fileType = srcPath.substring(srcPath.indexOf('.') + 1);
//			newPath = destDir + File.separator + 			//������Ϊԭ����_old���ļ�����
//					srcFile.getName().substring(0, srcFile.getName().indexOf('.')) 
//					+ "_old"+ '.' + fileType;
//			destFile = new File(newPath);
//
//		}
//
//		File destFileDir = new File(destDir);
//		if (!destFileDir.exists()) { // Ŀ¼�������򴴽���Ŀ¼
//			destFileDir.mkdirs();
//		}
//		try {
//			FileInputStream fis = new FileInputStream(srcFile);
//			FileOutputStream fos = new FileOutputStream(destFile);
//			byte[] buf = new byte[1024];
//			int c;
//			while ((c = fis.read(buf)) != -1) {
//				fos.write(buf, 0, c);
//			}
//			fos.flush();
//			fis.close();
//			fos.close();
//
//			flag = true;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
		return flag;
	}
	
	public static boolean isExist(String filename)
	{
		File file = new File(filename);
		if (file.exists()) return true;
		else return false;
	}
}
