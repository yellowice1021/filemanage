package com.scut.client.ui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.scut.client.user.MyUser;

public class MyServerPopMenu {
	public JPopupMenu popupMenu = new JPopupMenu();
	public JMenuItem menuItem1 = new JMenuItem("Ԥ��");
	public JMenuItem menuItem2 = new JMenuItem("����");
//	public static JMenuItem menuItem3 = new JMenuItem("��ӵ������б�");
	//public JMenuItem menuItem4 = new JMenuItem("ɾ�����ļ�");
//	public static JMenuItem menuItem5 = new JMenuItem("�鿴�ļ������Ϣ");
//	public static JMenuItem menuItem6 = new JMenuItem("�޸��ļ������Ϣ");
	public JMenuItem menuItem7 = new JMenuItem("ɾ�����ļ�");
	public JMenuItem menuItem8 = new JMenuItem("�鿴��ʷ�汾");
	//public JMenuItem menuItem3 = new JMenuItem("��Ϊ����·��");
	//public JMenuItem menuItem4 = new JMenuItem("�½��ļ���");
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
