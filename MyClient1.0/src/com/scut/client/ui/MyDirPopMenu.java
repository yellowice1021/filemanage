package com.scut.client.ui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.scut.client.user.MyUser;

public class MyDirPopMenu {
	public JPopupMenu popupMenu = new JPopupMenu();
	public JMenuItem menuItem1 = new JMenuItem("����Ŀ¼");
	public JMenuItem menuItem2 = new JMenuItem("ɾ��Ŀ¼");
	//public JMenuItem menuItem3 = new JMenuItem("�޸�Ŀ¼��");
	public JMenuItem menuItem4 = new JMenuItem("�༭Ŀ¼��ǩ");
	public JMenuItem menuItem5 = new JMenuItem("�鿴Ŀ¼��ǩ");
	public JMenuItem menuItem6 = new JMenuItem("�ϴ��ļ������ļ���");
	public JMenuItem menuItem7 = new JMenuItem("�鿴Ŀ¼�ؼ���");
	public JMenuItem menuItem8 = new JMenuItem("�༭Ŀ¼�ؼ���");
	public JMenuItem menuItem9 = new JMenuItem("���Ҵ�Ŀ¼���ļ�");
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
