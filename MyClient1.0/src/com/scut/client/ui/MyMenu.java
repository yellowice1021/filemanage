package com.scut.client.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.itextpdf.awt.geom.Dimension;
import com.itextpdf.text.log.SysoCounter;

public class MyMenu {
	private Logger log = Logger.getLogger("client");
	
	public static JMenuBar menuBar;            //菜单
	
	public static JMenu loginMenu = new JMenu("登录");
	//public static JMenu loginMenu = new JMenu("主页");
	public JMenuItem loginItem = new JMenuItem("登录");
	public JMenuItem quitItem = new JMenuItem("退出登录");
	//public  JMenuItem quitItem = new JMenuItem("回到主页");
	
	public static JMenu setMenu = new JMenu("设置");
	public JMenuItem setUiItem = new JMenuItem("界面主题");
	public JMenuItem setServerItem = new JMenuItem("修改服务器");
	public JMenuItem setServerIpItem = new JMenuItem("修改服务器ip");//新增修改服务器ip的功能
	public JMenuItem setSavePathItem = new JMenuItem("修改默认保存路径");
	
	
	public MyMainFrame myMainframe;

	public static  JMenu userManage = new JMenu("用户管理");
	public JMenuItem lookUserItem = new JMenuItem("查看用户信息");
	public JMenuItem addUserItem = new JMenuItem("添加用户");
	
	public static JMenu logManage = new JMenu("日志管理");
	public JMenuItem lookLogItem = new JMenuItem("查看日志信息");
	public JMenuItem setLogDateItem = new JMenuItem("设置日志保存期限");
	
	public static JMenu fileManage = new JMenu("文件管理");
	public JMenuItem filesearch = new JMenuItem("文件名检索");
	public JMenuItem fileinfosearch = new JMenuItem("文件夹/目录关键词检索");
	public static JMenuItem recycle = new JMenuItem("回收站");
	public JMenuItem local = new JMenuItem("本地文件信息");
	public static JMenuItem syn = new JMenuItem("同步服务器");
	public static JMenuItem localMessage = new JMenuItem("同步本地关键字标签");
	
	public static JMenu fileManageClient = new JMenu("文件管理");
	public JMenuItem filesearchClient = new JMenuItem("文件名检索");
	public JMenuItem 	fileinfoSearchClient = new JMenuItem("文件夹/目录关键词检索");

	
	public static JMenu personalCenter = new JMenu("个人中心");
	public JMenuItem updatePwd = new JMenuItem("修改密码");
	public JMenuItem history = new JMenuItem("操作历史");
	
	public static JMenu helpMenu = new JMenu("使用帮助");
	public  JMenuItem helpItem = new JMenuItem("查看使用帮助文档");////////////////////////////////////zhr modified
	
	public MyMenu(MyMainFrame myMainframe)
	{
		this.myMainframe = myMainframe;
		menuBar = new JMenuBar();
		
		setMenu.setFont(new Font("微软雅黑",Font.PLAIN,14));
		setMenu.add(setUiItem);
		setMenu.add(setServerItem);
		setMenu.add(setServerIpItem);//服务器ip
		setMenu.add(setSavePathItem);
		//setMenu.addSeparator();
		
		loginMenu.setFont(new Font("微软雅黑",Font.PLAIN,14));
		loginMenu.add(loginItem);
		loginMenu.add(quitItem);
		//loginMenu.addSeparator();
		
		userManage.setFont(new Font("微软雅黑",Font.PLAIN,14));
		userManage.add(addUserItem);
		userManage.add(lookUserItem);
		//userManage.addSeparator();
		
		logManage.setFont(new Font("微软雅黑",Font.PLAIN,14));
		logManage.add(lookLogItem);
		logManage.add(setLogDateItem);
		//logManage.addSeparator();
		
		fileManage.setFont(new Font("微软雅黑",Font.PLAIN,14));
		fileManage.add(filesearch);
		fileManage.add(fileinfosearch);
		fileManage.add(recycle);
		//fileManage.add(local);
		fileManage.add(syn);
		//fileManage.addSeparator();
		fileManage.add(localMessage);
		
		fileManageClient.setFont(new Font("微软雅黑",Font.PLAIN,14));
		fileManageClient.add(filesearchClient);
		fileManageClient.add(fileinfoSearchClient);
		//fileManageClient.addSeparator();
		
		helpMenu.setFont(new Font("微软雅黑",Font.PLAIN,14));
		helpMenu.add(helpItem);////////////////////////////////////zhr modified
		//helpMenu.addSeparator();
		
		personalCenter.setFont(new Font("微软雅黑",Font.PLAIN,14));
		personalCenter.add(updatePwd);
		personalCenter.add(history);
	//	personalCenter.addSeparator();
		
		menuBar.add(loginMenu);
		//menuBar.add(quitItem);
		menuBar.add(fileManageClient);
	
//		menuBar.add(logManage);
//		menuBar.add(userManage);
		menuBar.add(setMenu);
//		menuBar.add(personalCenter);
		menuBar.add(helpMenu);
		//menuBar.add(helpItem);
		
		/*尝试设置menu的背景、菜单选项之间的间距
		 * **/
//		menuBar.setBackground(Color.blue);
//		menuBar.setBorderPainted(true);
		
		this.myMainframe.getFrame().setJMenuBar(menuBar);
		log.info("initmenu");
	}
}
