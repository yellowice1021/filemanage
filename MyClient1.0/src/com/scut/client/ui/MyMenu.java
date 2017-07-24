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
	
	public static JMenuBar menuBar;            //�˵�
	
	public static JMenu loginMenu = new JMenu("��¼");
	//public static JMenu loginMenu = new JMenu("��ҳ");
	public JMenuItem loginItem = new JMenuItem("��¼");
	public JMenuItem quitItem = new JMenuItem("�˳���¼");
	//public  JMenuItem quitItem = new JMenuItem("�ص���ҳ");
	
	public static JMenu setMenu = new JMenu("����");
	public JMenuItem setUiItem = new JMenuItem("��������");
	public JMenuItem setServerItem = new JMenuItem("�޸ķ�����");
	public JMenuItem setServerIpItem = new JMenuItem("�޸ķ�����ip");//�����޸ķ�����ip�Ĺ���
	public JMenuItem setSavePathItem = new JMenuItem("�޸�Ĭ�ϱ���·��");
	
	
	public MyMainFrame myMainframe;

	public static  JMenu userManage = new JMenu("�û�����");
	public JMenuItem lookUserItem = new JMenuItem("�鿴�û���Ϣ");
	public JMenuItem addUserItem = new JMenuItem("����û�");
	
	public static JMenu logManage = new JMenu("��־����");
	public JMenuItem lookLogItem = new JMenuItem("�鿴��־��Ϣ");
	public JMenuItem setLogDateItem = new JMenuItem("������־��������");
	
	public static JMenu fileManage = new JMenu("�ļ�����");
	public JMenuItem filesearch = new JMenuItem("�ļ�������");
	public JMenuItem fileinfosearch = new JMenuItem("�ļ���/Ŀ¼�ؼ��ʼ���");
	public static JMenuItem recycle = new JMenuItem("����վ");
	public JMenuItem local = new JMenuItem("�����ļ���Ϣ");
	public static JMenuItem syn = new JMenuItem("ͬ��������");
	public static JMenuItem localMessage = new JMenuItem("ͬ�����عؼ��ֱ�ǩ");
	
	public static JMenu fileManageClient = new JMenu("�ļ�����");
	public JMenuItem filesearchClient = new JMenuItem("�ļ�������");
	public JMenuItem 	fileinfoSearchClient = new JMenuItem("�ļ���/Ŀ¼�ؼ��ʼ���");

	
	public static JMenu personalCenter = new JMenu("��������");
	public JMenuItem updatePwd = new JMenuItem("�޸�����");
	public JMenuItem history = new JMenuItem("������ʷ");
	
	public static JMenu helpMenu = new JMenu("ʹ�ð���");
	public  JMenuItem helpItem = new JMenuItem("�鿴ʹ�ð����ĵ�");////////////////////////////////////zhr modified
	
	public MyMenu(MyMainFrame myMainframe)
	{
		this.myMainframe = myMainframe;
		menuBar = new JMenuBar();
		
		setMenu.setFont(new Font("΢���ź�",Font.PLAIN,14));
		setMenu.add(setUiItem);
		setMenu.add(setServerItem);
		setMenu.add(setServerIpItem);//������ip
		setMenu.add(setSavePathItem);
		//setMenu.addSeparator();
		
		loginMenu.setFont(new Font("΢���ź�",Font.PLAIN,14));
		loginMenu.add(loginItem);
		loginMenu.add(quitItem);
		//loginMenu.addSeparator();
		
		userManage.setFont(new Font("΢���ź�",Font.PLAIN,14));
		userManage.add(addUserItem);
		userManage.add(lookUserItem);
		//userManage.addSeparator();
		
		logManage.setFont(new Font("΢���ź�",Font.PLAIN,14));
		logManage.add(lookLogItem);
		logManage.add(setLogDateItem);
		//logManage.addSeparator();
		
		fileManage.setFont(new Font("΢���ź�",Font.PLAIN,14));
		fileManage.add(filesearch);
		fileManage.add(fileinfosearch);
		fileManage.add(recycle);
		//fileManage.add(local);
		fileManage.add(syn);
		//fileManage.addSeparator();
		fileManage.add(localMessage);
		
		fileManageClient.setFont(new Font("΢���ź�",Font.PLAIN,14));
		fileManageClient.add(filesearchClient);
		fileManageClient.add(fileinfoSearchClient);
		//fileManageClient.addSeparator();
		
		helpMenu.setFont(new Font("΢���ź�",Font.PLAIN,14));
		helpMenu.add(helpItem);////////////////////////////////////zhr modified
		//helpMenu.addSeparator();
		
		personalCenter.setFont(new Font("΢���ź�",Font.PLAIN,14));
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
		
		/*��������menu�ı������˵�ѡ��֮��ļ��
		 * **/
//		menuBar.setBackground(Color.blue);
//		menuBar.setBorderPainted(true);
		
		this.myMainframe.getFrame().setJMenuBar(menuBar);
		log.info("initmenu");
	}
}
