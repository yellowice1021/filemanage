package com.scut.server.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.scut.server.conf.MyPath;


public class FileDbDao 
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
	
	/*
	 * 文件名称检索
	 */
	public static String search(String filename)
	{
		String s = "";
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select file_id, file_path from tb_file where file_name like ?");
			pstmt.setString(1, "%"+filename+"%");
			res = pstmt.executeQuery();
			while (res.next())
			{
				s += res.getInt(1) + "," + res.getString(2) + "\t";
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
	
	/*
	 * 或  检索
	 */
	public static String singleSearch(
			 String powerstation,
	         String crew,
	         String projectname,
	         String keyword,
	         String projectid,
	         String manager,
	         String applysituation,
	         String potentialcustomers,
	         String projectstate,
	         String onprotect)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
		 boolean first = true;
		 String s = "";
		 ResultSet rs = null;
         //String sql = "select tb_apply.dir_name,powerstation from tb_dir_keywords,tb_apply where tb_apply.dir_name=tb_dir_keywords.dir_name";
		 //String sql = "select distinct tb_dir_keywords.dir_name from tb_dir_keywords left join tb_apply on tb_dir_keywords.dir_name = tb_apply.dir_name"; 
		 String sql = "select distinct tb_dir_keywords.dir_name from tb_dir_keywords left join tb_apply on tb_apply.dir_name = tb_dir_keywords.dir_name";
		 //         sql+=" or file_powerstation like '%"+powerstation + "%'";
//         sql+=" or file_crew like '%" + crew  + "%'";
//         sql+=" or file_projectname like '%" + projectname + "%'";
//         sql+=" or file_keyword like '%" + keyword + "%'";
//         sql+=" or file_projectid like '%" + projectid + "%'"; 
//         sql+=" or file_manager like '%" + manager + "%'";
//         sql+=" or file_applysituation like '%"+applysituation+ "%'";
//         sql+=" or file_potentialcustomers like '%" + potentialcustomers+ "%'";
         if(!powerstation.equals(""))
         {
        	 if (first == true)
        	 {
        		 sql+=" where file_powerstation like '%"+powerstation + "%'";
                 first = false;
        	 }
        	 else 
             {
            	 sql+=" or file_powerstation like '%"+powerstation + "%'";
             }
             
         }
         
         if(!crew.equals("") )
         {
        	 if (first == true) 
        	 {
        		 sql+=" where file_crew like '%" + crew  + "%'";
        		 first = false;
        	 }
        	 else 
        	 {
        		 sql+=" or file_crew like '%" + crew  + "%'";
        	 }
         }
         if(!projectname.equals(""))
         {
        	 if (first == true) 
        	 {
        		 sql+=" where file_projectname like '%" + projectname + "%'";
        		 first = false;
        	 }
        	 else 
        	 {
        		 sql+=" or file_projectname like '%" + projectname + "%'";
        	 }
             
         }
         if(!keyword.equals(""))
         {
        	 if (first == true) 
        	 {
        		 sql+=" where file_keyword like '%" + keyword + "%'";
        		 first = false;
        	 }
        	 else 
        	 {
        		 sql+=" or file_keyword like '%" + keyword + "%'";
        	 }
         }
         if(!projectid.equals(""))           
         {
        	 if (first == true) 
        	 {
        		 sql+=" where file_projectid like '%" + projectid + "%'";
        		 first = false;
        	 }
        	 else 
        	 {
        		 sql+=" or file_projectid like '%" + projectid + "%'";

        	 }
            
         }
         if(!manager.equals(""))
         {
        	 if (first == true) 
        	 {
        		 sql+=" where file_manager like '%" + manager + "%'";
        		 first = false;
        	 }
        	 else 
        	 {
        		 sql+=" or file_manager like '%" + manager + "%'";
        	 }
             
         }
         if(!applysituation.equals(""))
         {
        	 if (first == true) 
        	 {
        		 sql+=" where file_applysituation like '%"+applysituation+ "%'";
        		 first = false;
        	 }
        	 else 
        	 {
        		 sql+=" or file_applysituation like '%"+applysituation+ "%'";
        	 }
             
         }
         if(!potentialcustomers.equals(""))
         {
        	 if (first == true) 
        	 {
        		 sql+=" where file_potentialcustomers like '%" + potentialcustomers+ "%'";
        		 first = false;
        	 }
        	 else 
        	 {
        		 sql+=" or file_potentialcustomers like '%" + potentialcustomers+ "%'";
        	 }
             
         }
         if(!projectstate.equals(""))
         {
        	 if (first == true) 
        	 {
        		 sql+=" where file_projectstate like '%" + projectstate + "%'";
        		 first = false;
        	 }
        	 else 
        	 {
        		 sql+=" or file_projectstate like '%" + projectstate + "%'";
        	 }
         }
         if (onprotect.equals("在保"))
         {
        	 //sql+=" or endtime > " + date;
        	 if (first == true) 
        	 {
        		 sql+=" where endtime > '" + date +"'";
        		 first = false;
        	 }
        	 else 
        	 {
        		 sql+=" or endtime > '" + date +"'";
        	 }
         }
         else if (onprotect.equals("过保"))
         {
        	 //sql+=" or endtime < " + date;
        	 if (first == true) 
        	 {
        		 sql+=" where endtime < '" + date +"'";
        		 first = false;
        	 }
        	 else 
        	 {
        		 sql+=" or endtime < '" + date +"'";
        	 }
         }
         //sql += " and file_recycle = 0";
         log.debug(sql);
         try {
        	stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
	        {
	             String dir_name=rs.getString("tb_dir_keywords.dir_name");
	             s += dir_name +"\t"; 
	        }   
			log.debug(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                      
		 return s;
	}
	
	
	public static String unionSearch(
			 String powerstation,
	         String crew,
	         String projectname,
	         String keyword,
	         String projectid,
	         String manager,
	         String applysituation,
	         String potentialcustomers,
	         String projectstate,
	         String onprotect)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
		 String s = "";
		 ResultSet rs = null;
         String sql = "select distinct tb_dir_keywords.dir_name from tb_dir_keywords left join tb_apply on tb_apply.dir_name=tb_dir_keywords.dir_name where  1=1";
         if(!powerstation.equals(""))
         {
             sql+=" and file_powerstation like '%"+powerstation + "%'";
         }
         if(!crew.equals(""))
         {
             sql+=" and file_crew like '%" + crew  + "%'";
         }
         if(!projectname.equals(""))
         {
             sql+=" and file_projectname like '%" + projectname + "%'";
         }
         if(!keyword.equals(""))
         {
             sql+=" and file_keyword like '%" + keyword + "%'";
         }
         if(!projectid.equals(""))           
         {
             sql+=" and file_projectid like '%" + projectid + "%'";
         }
         if(!manager.equals(""))
         {
             sql+=" and file_manager like '%" + manager + "%'";
         }
         if(!applysituation.equals(""))
         {
             sql+=" and  file_applysituation like '%"+applysituation+ "%'";
         }
         if(!potentialcustomers.equals(""))
         {
             sql+=" and file_potentialcustomers like '%" + potentialcustomers+ "%'";
         }
         if(!projectstate.equals(""))
         {
             sql+=" and file_projectstate like '%" + projectstate + "%'";
         }
         if (onprotect.equals("在保"))
         {
        	 sql+=" and endtime >= '" + date +"'";
         }
         else if(onprotect.equals("过保"))
         {
        	 sql+=" and endtime < '" + date +"'";
         }
         //sql += " and file_recycle = 0";
         log.debug(sql);
         try {
        	stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
	        {
	             //String id=rs.getString("dir_id");
	             String filename=rs.getString("tb_dir_keywords.dir_name");
	             s += filename+"\t";      
	        }   
			log.debug(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                      
		 return s;
	}
	
	/*
	 * 判断文件是否存在
	 */
	public static boolean isExist(String filePath)
	{
		boolean flag = true;
		log.debug(filePath);
		File file = new File(MyPath.serverRootPath + filePath);
		if (file.exists()) return true;
		else return false;
//		boolean flag = false;;
//		ResultSet res = null;
//		try 
//		{
//			pstmt = con.prepareStatement("select file_id from tb_file where file_path = ? and file_recycle = 0");
//			pstmt.setString(1, filePath);
//			res = pstmt.executeQuery();
//			if ( res.next()) flag = true;
//		} 
//		catch (SQLException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			log.warn(e.toString());
//		}	
//		return flag;
	}
	
	/*
	 * 判断文件的版本号
	 */
	public static int getFileVersion(String filePath)
	{
		log.debug(" getFileVersion" + "::  " +filePath);
		int endindex = filePath.lastIndexOf(".");
		if (endindex == -1)
		{
			endindex = filePath.length();
		}
		String filename = filePath.substring(0,endindex);
		log.debug(" getFileVersion" + "::  " +filename);
		int num = 0;
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select file_path from tb_file where file_path like ?");
			pstmt.setString(1, "%"+filename+"%");
			res = pstmt.executeQuery();
			while ( res.next()) 
			{
				log.debug(res.getString(1));
				num++;
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}	
		return num;
	}
	
	/*
	 * 插入文件，部分参数
	 */
	public static boolean insertFile(String filename, String filePath)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("insert into tb_file(file_name,file_path) values(?,?)");
			pstmt.setString(1, filename);
			pstmt.setString(2, filePath);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	public static boolean delFile(String filepath)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("delete from tb_file where file_name = ?");
			pstmt.setString(1, filepath);
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
		String s = "";
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select * from tb_file");
			
			res = pstmt.executeQuery();
			while (res.next())
			{
				s += res.getInt(1) + "," + res.getString(2) +"," + res.getString(3)+","
			+res.getString(6) +"," + res.getString(7) +"\n";
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			s = "no";
		}	
		log.debug(s);
	}
	
	
	public static boolean addFileInfo(String filepath,
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
			pstmt = con.prepareStatement("update tb_file "
					+ "set file_powerstation = ?, "
					+ "file_crew = ?, "
					+ "file_projectname = ?, "
					+ "file_keyword = ?, "
					+ "file_projectid = ?, "
					+ "file_manager = ?, "
					+ "file_applysituation = ?, "
					+ "file_potentialcustomers = ?, "
					+ "file_projectstate = ? "
					+ "where file_path = ?");
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
	
	public static String getFileInfo(String filepath)
	{
		String s = "";
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select file_powerstation,file_crew,file_projectname,file_keyword,file_projectid,file_manager,file_applysituation,file_potentialcustomers,file_projectstate from tb_file where file_path = ?");
			pstmt.setString(1, filepath);
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
	
	public static boolean updateRecycleFile(int file_id, String file_path)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("update tb_file set file_path = ?, file_recycle = 0 where file_id = ?");
			pstmt.setString(1, file_path);
			pstmt.setInt(2, file_id);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
//	public static void main(String[] args)
//	{
////		insertFile("第一核电站");
////		insertFile("第一水电站");
////		insertFile("账单");
////		insertFile("powestation");
//		test();
//		String powerstation = "广州电站";
//		String crew = "广州机组";
//		String projectname = "发电";
//		String keyword = "发电";
//		String projectid = "1";
//		String manager = "林佳钦";
//		String applysituation = "电厂";
//		String potentialcustomers = "电力公司";
//		String projectstate = "进行中";
//		String filepath =// "powestation";
//				"D:\\eclipse_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Server_test\\doc\\931420.doc";
//		addFileInfo(filepath, powerstation,
//				crew,
//				projectname,
//				 keyword,
//				 projectid,
//				 manager,
//				 applysituation,
//				 potentialcustomers,
//				 projectstate);
//		test();
//		//System.out.println(search("pow"));
//	}
	
	
	public static boolean addFileInfo(String filepath,String powerstation,String crew)
	{
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("update tb_file set file_powerstation = ?, file_crew = ? where file_path = ?");
			pstmt.setString(1, powerstation);
			pstmt.setString(2, crew);
			pstmt.setString(3, filepath);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}
		return flag;
	}
	
	
	public static String getAllFilePath()
	{
		String s = "";
		ResultSet res = null;
		try 
		{
			pstmt = con.prepareStatement("select file_path from tb_file where file_recycle=0");
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
	
	
	public static void main(String[] args)
	{

		test();
//		String powerstation = "广州电站";
//		String crew = "广州机组";
//	
//		String filepath = "D:haha";
//				//"D:\\eclipse_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Server_test\\doc\\931420.doc";
//		System.out.println(isExist(filepath) );
//		addFileInfo(filepath, powerstation,crew);
//		test();
	}

	public static boolean syncDb(String filename) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			pstmt = con.prepareStatement("INSERT IGNORE INTO tb_file(file_name) values(?)");
			pstmt.setString(1, filename);
			pstmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
