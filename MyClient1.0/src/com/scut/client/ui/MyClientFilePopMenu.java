package com.scut.client.ui;


import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MyClientFilePopMenu {
	public JPopupMenu popupMenu = new JPopupMenu();

	public JMenuItem menuItem1 = new JMenuItem("打开文件所在文件夹");
	//public JMenuItem menuItem2 = new JMenuItem("查看文件相关信息");

	public MyClientFilePopMenu()
	{
		popupMenu.add(menuItem1);
		//popupMenu.add(menuItem2);
	}
}
