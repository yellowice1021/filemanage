package com.scut.client.funtion;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

public class MyOpenClientFile {
	private static Logger log = Logger.getLogger("client"); 
	/*
	 * 打开文件所在目录
	 */
	public static void openDir(String dirName)
	{
		
		File file = new File(dirName);  
		log.debug(dirName);
        if (!file.exists()) {  
            return;  
        }  
        Runtime runtime = null;  
        try 
        {  
            runtime = Runtime.getRuntime();       
            runtime.exec("cmd /c start explorer " + dirName);    
        } 
        catch (IOException ex)
        {  
            ex.printStackTrace();  
            log.warn(ex);
        } 
        finally 
        {  
            if (null != runtime) 
            {  
                runtime.runFinalization();  
            }  
        }  
//		try {
//			Runtime.getRuntime().exec("explorer.exe" + dirName);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			log.warn(e);
//		}
	}
}
