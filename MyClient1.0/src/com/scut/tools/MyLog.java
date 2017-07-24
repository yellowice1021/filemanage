package com.scut.tools;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/*
 * ���ǵ��Ե���־ģ��
 * @version 1.0 
 * @date    2016-12-19
 * @author  linjiaqin
 */
public class MyLog {
	
	//��̬����ֻ����ʼ��һ�Σ���̬�����Ҳ�ǡ�
	//Ҫȫ��Ψһ�����뵽�˾�̬��������ʼ������ҲҪȫ��Ψһ�����뵽�˾�̬�����
	//Ϊ�˲���¶��������һ���ӿ�ȥ���ر���
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