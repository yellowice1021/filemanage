package com.scut.client.ui;


import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MyClientFilePopMenu {
	public JPopupMenu popupMenu = new JPopupMenu();

	public JMenuItem menuItem1 = new JMenuItem("���ļ������ļ���");
	//public JMenuItem menuItem2 = new JMenuItem("�鿴�ļ������Ϣ");

	public MyClientFilePopMenu()
	{
		popupMenu.add(menuItem1);
		//popupMenu.add(menuItem2);
	}
}
