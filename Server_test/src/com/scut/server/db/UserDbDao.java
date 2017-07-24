package com.scut.server.db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;


public class UserDbDao 
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
			log.info("加载mysql驱动");
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
	public UserDbDao() 
	{
		// TODO Auto-generated constructor stub

	}

	/*
	 * 获取查询结果的功能函数
	 */
	public static ResultSet query(String sql) {
		ResultSet res = null;
		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}

		return res;
	}

	/*
	 * 判断用户是否在数据表中存在
	 */
	public static boolean isUser(String userName, String passwd) {
		boolean flag = false;
		sql = "select user_passwd from tb_user where user_name='" + userName + "'";
		ResultSet res = query(sql);
		try {
			while (res.next()) {
				if (res.getString(1).equals(passwd)) {
					flag = true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public static boolean hasUser(String userName) {
		boolean flag = false;
		sql = "select user_passwd from tb_user where user_name='" + userName + "'";
		ResultSet res = query(sql);
		try {
			while (res.next()) {
					flag = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * 获取用户的权限
	 */
	public static int getPrivilage(String userName) {
		int privilage = 0;
		sql = "select user_privillage from tb_user where user_name = '" + userName + "'";
		ResultSet res = query(sql);
		try {
			while (res.next()) {
				privilage = res.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.warn(e.toString());
		}
		return privilage;
	}

	/*
	 * 测试用
	 */
	public static void test() {
		ResultSet res = null;
		try {
			stmt = con.createStatement();
			res = stmt.executeQuery("select * from tb_user");
			while (res.next()) {
				System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getInt(3) + " " + res.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	/*
	 * 测试用
	 */
	public boolean insert() {
		sql = "insert into tb_user(user_name, user_privillage, user_passwd) values('林佳钦',2,'123');";
		boolean flag = true;
		try {
			stmt = con.createStatement();
			stmt.execute(sql);             //对这个理解有点错误，是没有返回结果就返回false。不是插入成功就放回
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	/*
	 * 删除用户,根据用户名
	 */
	public static boolean del(String name)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("delete from tb_user where user_name = ?");
			pstmt.setString(1, name);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;	
	}
	/*
	 * 删除用户,根据用户id
	 */
	public static boolean del(int id)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("delete from tb_user where user_id = ?");
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
	
	/*
	 * 修改密码
	 */
	public static boolean updatePwd(String username, String newpasswd, String oldpasswd)
	{
		try {
			pstmt = con.prepareStatement("update tb_user set user_passwd = ? where user_name = ? and user_passwd = ?");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean flag = true;
		try {
			pstmt.setString(2, username);
			pstmt.setString(1, newpasswd);
			pstmt.setString(3, oldpasswd);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	/*
	 * 增加用户
	 */
	public static boolean insert(String name, int privillege, String passwd, String userrealname)
	{
		try {
			pstmt = con.prepareStatement("insert into tb_user(user_name, user_privillage, user_passwd, user_realname) values(?,?,?,?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean flag = true;
		try {
			pstmt.setString(1, name);
			pstmt.setInt(2, privillege);
			pstmt.setString(3, passwd);
			pstmt.setString(4, userrealname);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	/*
	 * 关闭数据库连接
	 */
	public static void close()
	{
		try 
		{
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}
	}
	
	/*
	 * 修改用户
	 */
	public static boolean update(int id, String userName, int privillege, String passwd)
	{
		try {
			pstmt = con.prepareStatement("update tb_user set user_privillage = ?, user_passwd = ?, user_name = ? where user_id = ?");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean flag = true;
		try {
			pstmt.setString(3, userName);
			pstmt.setInt(1, privillege);
			pstmt.setString(2, passwd);
			pstmt.setInt(4, id);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	public static String getUser()
	{
		ResultSet res = null;
		String s = "";
		try {
			stmt = con.createStatement();
			res = stmt.executeQuery("select * from tb_user");
			while (res.next()) {
				s += res.getInt(1) + "," + res.getString(2) + "," + res.getInt(3) + "," + res.getString(4) + "," + res.getString(5) + "\t";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public static boolean isUserExist(String username)
	{
		try {
			pstmt = con.prepareStatement("select user_id from tb_user where user_name = ?");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet res = null;
		boolean flag = false;
		try {
			pstmt.setString(1, username);
			res = pstmt.executeQuery();
			if (res.next()) 
			{
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}
		return flag;
	}


//	public static void main(String[] args) {
//		//DbDao db = new DbDao();
//		//System.out.println(UserDbDao.getPrivilage("林佳钦"));
//		//System.out.println(db.insert("ljqsss",2,"123"));
//		//UserDbDao.test();
//		//UserDbDao.update("tests",2,"1234");
//		//UserDbDao.test();
//		System.out.println(getUser());
//		close();
//	}
}
