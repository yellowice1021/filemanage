package com.scut.server.conf;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class MyUser 
{
	private static Logger log = Logger.getLogger("server"); 
	public static Hashtable<String, Integer>mp = new Hashtable<String, Integer>();
	public static void Login(String username)
	{
		 synchronized(mp) 
		 {  
			 mp.put(username, new Integer(10));
         }  
		
	}
	public static void Logout(String username)
	{
		 synchronized(mp) 
		 {  
			 mp.remove(username);
         } 
		
	}
	public static boolean islogined(String username)
	{
		synchronized (mp) 
		{
			if (mp.containsKey(username)) return true;
			else return false;
		}	
	}
	
	public static void logoutUser()
	{
		synchronized (mp) 
		{
			Set set = mp.entrySet();
			Iterator<Map.Entry>i = set.iterator();
			while(i.hasNext())
			{
				Map.Entry me = i.next();
				int value = (int) me.getValue();
				String key = (String) me.getKey();
				value = value-1;
				if (value == 0) mp.remove(key);
				else mp.put(key, new Integer(value));
			}
		}
	}
	public static void timer() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, 5); // 控制时  
        calendar.set(Calendar.MINUTE, 30);       // 控制分  
        calendar.set(Calendar.SECOND, 0);       // 控制秒  
  
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的22：00：00  
  
        Timer timer = new Timer();  
        timer.scheduleAtFixedRate(new TimerTask() {  
            public void run() {  
                log.debug("定期减少用户登录时长"); 
                logoutUser();
            }  
        }, time, 1000 * 60 * 10);// 这里设定将延时每天固定执行  1000 * 60 * 60 * 24
    } 
	
}
