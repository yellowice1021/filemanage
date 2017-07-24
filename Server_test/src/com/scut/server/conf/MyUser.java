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
        calendar.set(Calendar.HOUR_OF_DAY, 5); // ����ʱ  
        calendar.set(Calendar.MINUTE, 30);       // ���Ʒ�  
        calendar.set(Calendar.SECOND, 0);       // ������  
  
        Date time = calendar.getTime();         // �ó�ִ�������ʱ��,�˴�Ϊ�����22��00��00  
  
        Timer timer = new Timer();  
        timer.scheduleAtFixedRate(new TimerTask() {  
            public void run() {  
                log.debug("���ڼ����û���¼ʱ��"); 
                logoutUser();
            }  
        }, time, 1000 * 60 * 10);// �����趨����ʱÿ��̶�ִ��  1000 * 60 * 60 * 24
    } 
	
}
