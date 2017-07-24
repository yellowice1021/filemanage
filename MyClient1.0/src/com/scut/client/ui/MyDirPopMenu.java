package com.scut.client.ui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.scut.client.user.MyUser;

public class MyDirPopMenu {
	public JPopupMenu popupMenu = new JPopupMenu();
	public JMenuItem menuItem1 = new JMenuItem("增加目录");
	public JMenuItem menuItem2 = new JMenuItem("删除目录");
	//public JMenuItem menuItem3 = new JMenuItem("修改目录名");
	public JMenuItem menuItem4 = new JMenuItem("编辑目录标签");
	public JMenuItem menuItem5 = new JMenuItem("查看目录标签");
	public JMenuItem menuItem6 = new JMenuItem("上传文件到此文件夹");
	public JMenuItem menuItem7 = new JMenuItem("查看目录关键字");
	public JMenuItem menuItem8 = new JMenuItem("编辑目录关键字");
	public JMenuItem menuItem9 = new JMenuItem("查找此目录下文件");
	public MyDirPopMenu()
	{
		if(MyUser.privilage == 1) 
		{
			popupMenu.add(menuItem1);
			popupMenu.add(menuItem2);
			//popupMenu.add(menuItem3);
			popupMenu.add(menuItem4);
			popupMenu.add(menuItem5);
			popupMenu.add(menuItem6);
			popupMenu.add(menuItem7);
			popupMenu.add(menuItem8);
			popupMenu.add(menuItem9);
		}
		else if(MyUser.privilage == 2 || MyUser.privilage == 3) 
		{
			popupMenu.add(menuItem5);
			popupMenu.add(menuItem7);
		}
	}
}
