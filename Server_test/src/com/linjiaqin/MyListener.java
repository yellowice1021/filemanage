package com.linjiaqin;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.scut.server.conf.Conf;
import com.scut.server.conf.MyPath;
import com.scut.server.conf.MyUser;
import com.scut.server.funtion.LogHandle;

/**
 * Application Lifecycle Listener implementation class MyListener
 *
 */
@WebListener
public class MyListener  implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyListener() {
        // TODO Auto-generated constructor stub
    }
       


	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	Conf.init();         //·���������ļ��ȵĳ�ʼ��
    	MyPath.init();
    	LogHandle.timer();   //����ɾ����־
    	MyUser.timer();      //ÿ���û�ֻ�ܴ���100���ӣ���ʱ�����µ�¼
    }
	
}
