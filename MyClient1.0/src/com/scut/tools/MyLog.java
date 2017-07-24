package com.scut.tools;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/*
 * 这是调试的日志模块
 * @version 1.0 
 * @date    2016-12-19
 * @author  linjiaqin
 */
public class MyLog {
	
	//静态变量只被初始化一次，静态代码块也是。
	//要全局唯一，就想到了静态变量，初始化操作也要全局唯一，就想到了静态代码块
	//为了不暴露变量，用一个接口去返回变量
	private static Logger log;
	
	static
	{
		log = Logger.getLogger("client");
		log.setLevel(Level.ALL);
		FileHandler fileHandler;
		try {
			
			fileHandler = new FileHandler("testlog.log");
			fileHandler.setLevel(Level.ALL);
			fileHandler.setFormatter(new LogFormatter());
			log.addHandler(fileHandler);
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("static");
		//log.info("static");
	}
	public MyLog()
	{
		System.out.println("struct");
		//log.info("struct");
	}
	
	public static Logger getMyLogLogger()
	{
		return log;
	}

}

class LogFormatter extends Formatter {
	
	@Override
	public String format(LogRecord record) {
		Date date = new Date();
		String sDate = date.toString();
		return "[" + sDate + "]" + "[" + record.getLevel() + "]" + record.getClass() + ":" + record.getMessage() + "\n";
	}

}