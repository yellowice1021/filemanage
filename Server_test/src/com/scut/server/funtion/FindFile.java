package com.scut.server.funtion;

import java.io.File;

import org.apache.log4j.Logger;

import com.scut.server.conf.MyPath;

public class FindFile 
{
	private static Logger log = Logger.getLogger("server"); 
	public static String res = "";
	public static void dfs_findname(String filePath, String pattern, int rootPathIndex)
	{
		   File root = new File(filePath);
		   File[] files = root.listFiles();
		   for(File file:files)
		   {     
			    if(file.isDirectory())
			    {
			    	dfs_findname(file.getAbsolutePath(), pattern, rootPathIndex);   
				}
			    else
			    {
			    	log.debug(file.getAbsolutePath().substring(rootPathIndex)+":"+pattern);
			    	if(file.getAbsolutePath().substring(rootPathIndex).indexOf(pattern) > 0)
			    	{
			    		res += file.getAbsolutePath().substring(rootPathIndex) +":"+ file.length()+",";
			    	}
			    	//System.out.println("显示"+filePath+"下所有子目录"+file.getAbsolutePath());
			    }     
		   }
	}
	public static String findFileName(String aimPath, String pattern)
	{
		String path = MyPath.serverRootPath;
		res = "";
		dfs_findname(path + aimPath, pattern, MyPath.serverRootPath.length());
		return res;
		
	}
//	public static void main(String[] args)
//	{
//		log.debug(findFileName("doc\\new2", "lantern"));
//	}
}
