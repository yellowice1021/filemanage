package com.scut.server.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.scut.server.conf.MyPath;
import com.scut.server.funtion.FileList;

public class HisDbDao {

		private static String user;
		private static String passwd;
		private static String url;
		private static String driver;
		private static Connection con;
		private static Statement stmt;
		private static java.sql.PreparedStatement pstmt;
		private static String sql;

		private static Logger log = Logger.getLogger("server");

		static
		{
			user = "scut";
			passwd = "scscut90";
			url = "jdbc:mysql://127.0.0.1:3306/db_file?useUnicode=true&characterEncoding=utf-8";
			driver = "com.mysql.jdbc.Driver";
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, passwd);
				log.debug("加载mysql驱动");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.warn(e.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.warn(e.toString());
			}
		}
		///////////////////////////////////////
		/*
		 * 文件名称检索
		 */
		public static int  searchVersion(String serverPath,String filename)
		{
			String s = "";
			int num = 0;
			int maxVersionNumber = 0;
			log.debug("serachVersion");
			File file = new File(MyPath.serverRootPath + serverPath);
			File[] files = file.listFiles();
			for(File e:files)
			{
				if(e.getName().indexOf(filename)>-1)
				{	
					num++;
					if(num > 1)
					{
						String fileNames = e.getName();																// 文件名有后缀
						String names = fileNames.substring(0, fileNames.lastIndexOf("."));							// 文件名没有后缀
						char version[] = (names.substring(names.length()-1, names.length())).toCharArray();	// 文件版本号
						int versionNumber = (int)(version[0]-97);
						if(versionNumber > maxVersionNumber)
						{
							maxVersionNumber = versionNumber;
						}
						log.debug(e.getName()+":"+filename+":" + versionNumber + ":" + maxVersionNumber);
						num++;
					}
				}
				log.debug(e.getName()+":"+filename);
			}
			if(num == 1)
			{
				return num;
			}
			log.debug(num);
			return maxVersionNumber+2;
			
//			ResultSet res = null;
//			try 
//			{
//				pstmt = con.prepareStatement("select file_oldpath from tb_history_file where file_oldpath=?");
//				pstmt.setString(1, filename);
//				res = pstmt.executeQuery();
//				while (res.next())
//				{
//					num++;
//				}
//				
//			} 
//			catch (SQLException e) 
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				log.warn(e.toString());
//				s = "no";
//			}	
//			return num;
		}
		
		public static void insertVersion(String oldpath, String newpath, String times)
		{
			log.debug("insertVersion");
			String s = "";
			ResultSet res = null;
			try 
			{
				pstmt = con.prepareStatement("insert into tb_history_file(file_oldpath,file_newpath, uploadtime) values(?,?,?)");
				pstmt.setString(1, oldpath);
				pstmt.setString(2, newpath);
				pstmt.setString(3, times);
				pstmt.execute();	
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.warn(e.toString());
				s = "no";
			}	
		}
		
		public static String getVersionFile(String oldpath)
		{
			String s = "";
			int num = 0;
			ResultSet res = null;
			try 
			{
				pstmt = con.prepareStatement("select file_newpath,uploadtime from tb_history_file where file_oldpath=?");
				pstmt.setString(1, oldpath);
				res = pstmt.executeQuery();
				while (res.next())
				{
					s += res.getString(1) + "," + res.getString(2) + "\t";
					num++;
				}
				
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.warn(e.toString());
				s = "no";
			}	
			return s;
		}
		
//		public static void main(String[] args)
//		{
//			insertVersion("11","22");
//		}
}
