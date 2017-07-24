package com.scut.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

/*
 * 数据库操作
 */
public class DbDao {
	private String user;
	private String passwd;
	private String url;
	// String dbName; 鍖呭惈鍦╱rl涓�
	private String driver;
	private Connection con;
	private Statement stmt;
	private java.sql.PreparedStatement pstmt;
	private String sql;

	private static Logger log = Logger.getLogger("server");

	public DbDao() {
		// TODO Auto-generated constructor stub

		// user = "root";
		// passwd = "";
		// url = "jdbc:mysql://127.0.0.1:3306/example";

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
			log.warning(e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warning(e.toString());
		}
	}

	public ResultSet query(String sql) {
		ResultSet res = null;
		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warning(e.toString());
		}

		return res;
	}

	public boolean isUser(String userName, String passwd) {
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

	public int getPrivilage(String userName) {
		int privilage = 0;
		sql = "select user_privillage from tb_user where user_name = '" + userName + "'";
		ResultSet res = query(sql);
		try {
			while (res.next()) {
				privilage = res.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.warning(e.toString());
		}
		return privilage;
	}

	/*
	 * 杩欏彧鏄竴涓祴璇曚緥瀛�
	 */
	public void test() {
		ResultSet res = null;
		try {
			stmt = con.createStatement();
			res = stmt.executeQuery("select * from tb_user");
			while (res.next()) {
				log.info(res.getInt(1) + " " + res.getString(2) + " " + res.getInt(3) + " " + res.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (res != null)
					res.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean insert() {
		sql = "insert into tb_user(user_name, user_privillage, user_passwd) values('林佳钦',2,'123');";
		boolean flag = true;
		try {
			stmt = con.createStatement();
			flag = stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warning(e.toString());
		}
		return flag;
	}
	
	//插入数据
	public boolean insert(String name, int privillege, String passwd)
	{
		try {
			pstmt = con.prepareStatement("insert into tb_user(user_name, user_privillage, user_passwd) values(?,?,?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean flag = true;
		try {
			pstmt.setString(1, name);
			pstmt.setInt(2, privillege);
			pstmt.setString(3, passwd);
			flag = !pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warning(e.toString());
		}
		System.out.println(flag);
		return flag;
	}
	
	//修改用户

//	public static void main(String[] args) {
//		DbDao db = new DbDao();
//		System.out.println(db.getPrivilage("林佳钦"));
//		System.out.println(db.insert("ljqsss",2,"123"));
//		db.test();
//
//	}
}
