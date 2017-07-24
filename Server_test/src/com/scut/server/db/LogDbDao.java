package com.scut.server.db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


public class LogDbDao 
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
	
	public static String getAllLog()
	{
		ResultSet res = null;
		String s = "";
		try {
			pstmt = con.prepareStatement("select * from tb_log");
			res = pstmt.executeQuery();
			while (res.next()) {
				s += res.getInt(1) +" " + res.getString(2) + " " + res.getString(3) + " " + res.getString(4) + "\t";
				//log.info(res.getInt(1) + " " + res.getString(2) + " " + res.getInt(3) + " " + res.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		} 
		return s;
	}
	
	public static boolean insertLog(String date, String user_name, String log_info)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("insert into tb_log(log_date, user_name, log_info) values(?,?,?)");
			pstmt.setString(1, date);
			pstmt.setString(2, user_name);
			pstmt.setString(3, log_info);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	public static boolean delLog(int id)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("delete from tb_log where log_id = ?");
			pstmt.setInt(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	public static void test()
	{
		ResultSet res = null;
		try {
			pstmt = con.prepareStatement("select * from tb_log");
			res = pstmt.executeQuery();
			while (res.next()) {
				System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}
	
//	public static void main(String[] args)
//	{
//		test();
//		delLog(7);
//		test();
//	}
} 
