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
    	Conf.init();         //路径和配置文件等的初始化
    	MyPath.init();
    	LogHandle.timer();   //定期删除日志
    	MyUser.timer();      //每个用户只能存在100分钟，到时得重新登录
    }
	
}
