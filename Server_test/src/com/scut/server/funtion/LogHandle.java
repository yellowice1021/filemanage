package com.scut.server.funtion;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;


import com.scut.server.conf.Conf;

public class LogHandle 
{
	private static Logger log = Logger.getLogger("server");
	
	private static String user;
	private static String passwd;
	private static String url;
	private static String driver;
	private static Connection con;
	private static Statement stmt;
	private static java.sql.PreparedStatement pstmt;
	private static String sql;


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
	
	public static String getDate()
	{
		Scanner s = null;
		try {
			s = new Scanner(new File(Conf.logDatePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String date = s.nextLine();
		return date;
		
	}
	
    
	public static void delLogOnTime()
	{
		int dateDis = Integer.parseInt(getDate());
		System.out.println("date: " + dateDis);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
		
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -dateDis); 
        String aimDate = df.format(cal.getTime());
        System.out.println("aimDate" + aimDate);
        
        boolean flag = true;
		try {
			pstmt = con.prepareStatement("delete from tb_log where log_date < ?");
			pstmt.setString(1, aimDate);

			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
			flag = false;
		}

	}
	
//	public static void main(String[] args)
//	{
//		delLogOnTime();
//	}
	public static void timer() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, 7); // 控制时  
        calendar.set(Calendar.MINUTE, 30);       // 控制分  
        calendar.set(Calendar.SECOND, 0);       // 控制秒  
  
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的22：00：00  
  
        Timer timer = new Timer();  
        timer.scheduleAtFixedRate(new TimerTask() {  
            public void run() {  
                System.out.println("-------设定要指定任务--------");  
                delLogOnTime();
            }  
        }, time, 1000 * 60 * 60 * 12);// 这里设定将延时每天固定执行  1000 * 60 * 60 * 24
    }  
	public static boolean writeDate(String date)
	{
		boolean flag = true;
		PrintWriter pw = null;
		try {
		    pw = new PrintWriter(Conf.logDatePath);
			pw.println(date);
			pw.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		finally
		{
			if (pw != null)
			{
				pw.close();
			}
		}
		return flag;
	}
	
	public static String getAlllog()
	{
		String res = "";
		File dir = new File("D://log");
		File[] files = dir.listFiles();
		Scanner s = null;
		for(File f:files)
		{
			try {
				s = new Scanner(f);
				while(s.hasNextLine())
				{
					res += s.nextLine().trim() + "\t";
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.warn(e);
			}
		}
		System.out.println("日志内容："+ res);
		return res;
	}
	
	public static void delLog(String linenum)
	{
		String a[] = linenum.split(" ");
		int b[] = new int[linenum.length()];
		for (int i = 0; i < a.length; i++)
		{
			b[i] = Integer.parseInt(a[i]);
		}
		
		File dir = new File("D://log");
		File[] files = dir.listFiles();
		Scanner s = null;
		String res ="";
		int num = 0;
		int i = 0;
		for(File f:files)
		{
			try {
				s = new Scanner(f);
				while(s.hasNextLine())
				{
					res = s.nextLine().trim();
					if (res.equals("aaaaa")) continue;  //这句在前
					if (num == b[i])
					{
						
					}
					num++;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.warn(e);
			}
		}
	}
	public static void test()
	{
		System.out.println(getAlllog());
	}
}
