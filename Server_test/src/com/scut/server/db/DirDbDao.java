package com.scut.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DirDbDao 
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
			log.debug("º”‘ÿmysql«˝∂Ø");
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
	
	public static boolean syncDb(String dirname)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("INSERT IGNORE INTO tb_dir(dir_name) values(?)");
			pstmt.setString(1, dirname);
			pstmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}
		
		try {
			pstmt = con.prepareStatement("INSERT IGNORE INTO tb_dir_keywords(dir_name) values(?)");
			pstmt.setString(1, dirname);
			pstmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public static boolean delDirapply(String Dirname)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("delete from tb_apply where dir_name = ?");
			pstmt.setString(1, Dirname);
			pstmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public static boolean addDirapply(String Dirname, 
			String powersattion, 
			String starttime, 
			String protecttime,
			String aimDate)
	{
		
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("insert into tb_apply(dir_name,powerstation,starttime,protecttime,endtime) "
					+ "values(?,?,?,?,?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pstmt.setString(1, Dirname);
			pstmt.setString(2, powersattion);
			pstmt.setString(3, starttime);
			pstmt.setString(4, protecttime);
			pstmt.setString(5, aimDate);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	public static String  getDirapply(String dirname)
	{
		String s ="";
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select powerstation,starttime,protecttime "
					+ "from tb_apply where dir_name = ?");
			pstmt.setString(1, dirname);
			res = pstmt.executeQuery();
			while (res.next())
			{
				for (int i = 1; i <= 3; i++)
				{
					s += res.getString(i) + ",";
				}
				s += "\t";
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}	
		return s;
	}
	
	public static String  getDirapplyEndTime(String dirname)
	{
		String s ="";
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select endtime "
					+ "from tb_apply where dir_name = ?");
			pstmt.setString(1, dirname);
			res = pstmt.executeQuery();
			while (res.next())
			{
				s += res.getString(1) + "#";
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}	
		return s;
	}
	
	public static boolean addDir(String dirname)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("insert into tb_dir(dir_name) values(?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pstmt.setString(1, dirname);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		
		try {
			pstmt = con.prepareStatement("insert into tb_dir_keywords(dir_name) values(?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pstmt.setString(1, dirname);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	public static boolean delDir(String dirname)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("delete from tb_dir where dir_name = ?");
			pstmt.setString(1, dirname);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		try {
			pstmt = con.prepareStatement("delete from tb_dir_keywords where dir_name = ?");
			pstmt.setString(1, dirname);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}

		return flag;
	}
	
	
	public static boolean isExist(String Dirname)
	{
		boolean flag = false;
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select dir_id from tb_dir where dir_name = ?");
			pstmt.setString(1, Dirname);
			res = pstmt.executeQuery();
			if ( res.next()) flag = true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}	
		return flag;
	}
	
	public static boolean updateDirInfo(String funtiondesc,
			String technicalFeature,
			String potentialcustomr,
			String projectstate,
			String Dirname)
	{
		boolean flag = true;
		log.debug(Dirname+"isexit"+DirDbDao.isExist(Dirname));
		try {
			pstmt = con.prepareStatement("update tb_dir "
					+ "set dir_funtiondesc = ?, "
					+ "dir_technicalFeature = ?, "
					+ "dir_projectstate = ?, "
					+ "dir_potentialcustomer = ? "
					+ "where dir_name = ?");
			pstmt.setString(1, funtiondesc);
			pstmt.setString(2, technicalFeature);

			pstmt.setString(4, potentialcustomr);
			pstmt.setString(3, projectstate);
			pstmt.setString(5, Dirname);
			pstmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}	
			
		return flag;
	}
	
	
	public static String getDirInfo(String Dirname)
	{
		String s = "";
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select dir_funtiondesc,dir_technicalFeature,dir_projectstate,dir_potentialcustomer"
					+ " from tb_dir where dir_name = ?");
			pstmt.setString(1, Dirname);
			res = pstmt.executeQuery();
			if (res.next())
			{
				for (int i = 1; i <= 4; i++)
				{
					s += res.getString(i) + ",";
				}
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}	
		return s;
	}
	
	public static String getDirKeyword(String Dirname)
	{
		String s = "";
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select file_powerstation,file_crew,file_projectname,file_keyword,file_projectid,file_manager,file_applysituation,"
					+ "file_potentialcustomers,file_projectstate from tb_dir_keywords where dir_name = ?");
			pstmt.setString(1, Dirname);
			res = pstmt.executeQuery();
			if (res.next())
			{
				for (int i = 1; i <= 9; i++)
				{
					s += res.getString(i) + ",";
				}
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}	
		return s;
	}
	
	public static boolean addDirKeyword(String filepath,
			String powerstation,
			String crew,
			String projectname,
			String keyword,
			String projectid,
			String manager,
			String applysituation,
			String potentialcustomers,
			String projectstate)
	{
		boolean flag = true;
		log.info(filepath+"isexit"+isExist(filepath));
		try {
			pstmt = con.prepareStatement("update tb_dir_keywords "
					+ "set file_powerstation = ?, "
					+ "file_crew = ?, "
					+ "file_projectname = ?, "
					+ "file_keyword = ?, "
					+ "file_projectid = ?, "
					+ "file_manager = ?, "
					+ "file_applysituation = ?, "
					+ "file_potentialcustomers = ?, "
					+ "file_projectstate = ? "
					+ "where dir_name = ?");
			pstmt.setString(1, powerstation);
			pstmt.setString(2, crew);
			pstmt.setString(3, projectname);
			pstmt.setString(4, keyword);
			pstmt.setString(5, projectid);
			pstmt.setString(6, manager);
			pstmt.setString(7, applysituation);
			pstmt.setString(8, potentialcustomers);
			pstmt.setString(9, projectstate);
			pstmt.setString(10, filepath);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	public static String test()
	{
		String s = "";
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select dir_funtiondesc,dir_technicalFeature,dir_applystituation,dir_potentialcustomer,"
					+ "dir_projectstate from tb_dir");
			//pstmt = con.prepareStatement("select * from tb_dir");
			res = pstmt.executeQuery();
			while (res.next())
			{
				for (int i = 1; i <= 5; i++)
				{
					s += res.getString(i) + ",";
				}
				log.debug(s);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}	
		return s;
	}
	
	public static String getAllDirPath()
	{
		String s = "";
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select dir_name from tb_dir where dir_recycle=0");
			res = pstmt.executeQuery();
			while (res.next())
			{
				s += res.getString(1) + ",";
			}
			log.debug(s);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}	
		return s;
	}
	
	public static String getDirPicPath(String dirname)
	{
		String s = "";
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select dir_picpath from tb_dir where dir_name=?");
			pstmt.setString(1, dirname);
			res = pstmt.executeQuery();
			while (res.next())
			{
				s += res.getString(1);
			}
			log.debug(s);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}	
		return s;
	}
	
	public static String addDirPic(String dirname,String picpath)
	{
		String s = getDirPicPath(dirname);
		String res = "yes";
		if (s.equals("NULL")||s == null) s= "";
		try 
		{
			pstmt = con.prepareStatement("update tb_dir set dir_picpath=? where dir_name=?");
			pstmt.setString(1, s+","+picpath);
			pstmt.setString(2, dirname);
			pstmt.execute();
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			res = "no";
		}	
		return res;
	}
	
	public static void main(String[] args)
	{
		test();
	}
}
