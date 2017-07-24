package com.scut.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;


public class RecycleDbDao 
{
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
			log.info("º”‘ÿmysql«˝∂Ø");
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
	
	public static boolean insertIntoRecy(String file_name)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("update tb_file set file_recycle = 1 where file_name = ?");
			pstmt.setString(1, file_name);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	public static boolean delFromRecy(int file_id)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("update tb_file set file_recycle = 0 where file_id = ?");
			pstmt.setInt(1, file_id);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	public static String getFileName(int file_id)
	{
		String s = "";
		ResultSet res = null;
		try {
			pstmt = con.prepareStatement("select file_name from tb_file where file_recycle=1 and file_id = ?");
			pstmt.setInt(1, file_id);
			res = pstmt.executeQuery();
			if (res.next()) {
				s += res.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			
		}
		log.debug(s);
		return s;
	}
	
	public static String getRecycle()
	{
		String s = "";
		ResultSet res = null;
		try {
			pstmt = con.prepareStatement("select file_id, file_name from tb_file where file_recycle=1");
//			pstmt = con.prepareStatement("select file_id, file_path from tb_file where file_id in "
//					+ "(select file_id from tb_recycle)");
			res = pstmt.executeQuery();
			while (res.next()) {
				s += res.getString(1) + "," + res.getString(2) + "\t";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			
		}
		log.debug(s);
		return s;
	}
}
