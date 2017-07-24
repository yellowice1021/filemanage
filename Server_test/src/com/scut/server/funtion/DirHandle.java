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
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	
	public static boolean deleteDirectory(String sPath) {  
	    
		boolean flag = true;
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}  
	
	/*
	 * 有问题，不再使用
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
	 * 移动文件
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
	 * 移动文件
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
//		if (destFile.exists()) { // 目标目录下已有同名文件，则重命名
//			String fileType = srcPath.substring(srcPath.indexOf('.') + 1);
//			newPath = destDir + File.separator + 			//新名称为原名加_old加文件类型
//					srcFile.getName().substring(0, srcFile.getName().indexOf('.')) 
//					+ "_old"+ '.' + fileType;
//			destFile = new File(newPath);
//
//		}
//
//		File destFileDir = new File(destDir);
//		if (!destFileDir.exists()) { // 目录不存在则创建该目录
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
