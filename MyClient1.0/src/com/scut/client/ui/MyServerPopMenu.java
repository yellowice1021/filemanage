package com.scut.client.ui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.scut.client.user.MyUser;

public class MyServerPopMenu {
	public JPopupMenu popupMenu = new JPopupMenu();
	public JMenuItem menuItem1 = new JMenuItem("预览");
	public JMenuItem menuItem2 = new JMenuItem("下载");
//	public static JMenuItem menuItem3 = new JMenuItem("添加到任务列表");
	//public JMenuItem menuItem4 = new JMenuItem("删除该文件");
//	public static JMenuItem menuItem5 = new JMenuItem("查看文件相关信息");
//	public static JMenuItem menuItem6 = new JMenuItem("修改文件相关信息");
	public JMenuItem menuItem7 = new JMenuItem("删除该文件");
	public JMenuItem menuItem8 = new JMenuItem("查看历史版本");
	//public JMenuItem menuItem3 = new JMenuItem("设为下载路径");
	//public JMenuItem menuItem4 = new JMenuItem("新建文件夹");
	public MyServerPopMenu()
	{
		if(MyUser.privilage == 1) 
		{
			popupMenu.add(menuItem1);
			popupMenu.add(menuItem2);
			popupMenu.add(menuItem7);
			popupMenu.add(menuItem8);
		}
		else if(MyUser.privilage == 2 ){
			popupMenu.add(menuItem1);
			popupMenu.add(menuItem8);
			popupMenu.add(menuItem2);
		}
		else if( MyUser.privilage == 3)
		{
			popupMenu.add(menuItem1);
			popupMenu.add(menuItem8);
		}
	}
}
