package com.scut.client.ui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MyClientPopMenu {
	public JPopupMenu popupMenu = new JPopupMenu();
	//public JMenuItem menuItem1 = new JMenuItem("预览");
	//public JMenuItem menuItem2 = new JMenuItem("添加到任务列表");
	//public JMenuItem menuItem3 = new JMenuItem("设为下载路径");
	//public JMenuItem menuItem4 = new JMenuItem("新建文件夹");
	public JMenuItem menuItem5 = new JMenuItem("打开文件所在文件夹");
	public MyClientPopMenu()
	{
		//popupMenu.add(menuItem1);
		//popupMenu.add(menuItem2);
		//popupMenu.add(menuItem3);
		//popupMenu.add(menuItem4);
		popupMenu.add(menuItem5);
	}
}
