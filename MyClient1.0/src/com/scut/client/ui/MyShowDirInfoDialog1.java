//package com.scut.client.ui;
//
//
//import java.awt.BorderLayout;
//
//import java.awt.Color;
//import java.awt.Cursor;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.logging.Level;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.border.EmptyBorder;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.SimpleAttributeSet;
//import javax.swing.text.Style;
//import javax.swing.text.StyleConstants;
//import javax.swing.text.StyledDocument;
//
//import org.apache.log4j.Logger;
//
//import com.scut.client.funtion.MyDirHandle;
//import com.scut.client.funtion.UpDownFile;
//import com.scut.client.user.MyPath;
//import com.scut.tools.MyImage;
//import com.suct.client.print.ImagePrint;
//
//import javax.swing.JLabel;
//import javax.swing.JTextArea;
//import javax.swing.JTextPane;
//import javax.swing.ScrollPaneConstants;
//
//public class MyShowDirInfoDialog1 extends JDialog implements ActionListener
//{
//
//	int pic_sum;
//	int pic_num;
//	String[] pic;         //����Ŀ¼�µ�����ͼƬ
//	String serverPath;    //������Ŀ¼��·��
//	private static Logger log = Logger.getLogger("client");
//	
//	JLabel photo = new JLabel("ͼƬ", JLabel.CENTER);
//	JTextArea textArea = new JTextArea();
//	//JLabel textArea = new JLabel();
//	JButton pre = new JButton("��һ��");
//	JButton next = new JButton("��һ��");
//	JLabel picname = new JLabel("ͼƬ��:");
//	JLabel picnumber = new JLabel("��/��");
//	JButton print = new JButton("��ӡ����");
//	ImageIcon image;
//	String picpaths = "";
//	String pic_name ; 
//	
//	String s; //��¼��������Ŀ¼�����Ϣ
//	JTextPane textPane = new JTextPane();
//	
//	int width = 1100;		
//	int height = 650;	
//	int screenWidth;
//	int screenHeight;
//	
//	/**
//	 * Launch the application.
//	 */
////	public static void main(String[] args) {
////		try {
////			MyShowDirInfoDialog1 dialog = new MyShowDirInfoDialog1("");
////			
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
//	
//
//	/**
//	 * Create the dialog.
//	 */
//	public MyShowDirInfoDialog1(String serverPath, String res) {
//		String title = serverPath.substring(4);
//				//serverPath.substring(serverPath.lastIndexOf("\\") + 1);
//		setTitle(title);
//		this.s = res;
//		this.serverPath = serverPath;
//		//setBounds(100, 100, 1100, 650);     //100
//		
//		getContentPane().setLayout(null);
//		// ���ô���߶ȿ��
//				this.setSize(width, height);
//				
//				// ���ô������
//		Toolkit kit = Toolkit.getDefaultToolkit();
//		Dimension screenSize = kit.getScreenSize();
//		screenWidth = screenSize.width;
//		screenHeight = screenSize.height;
//		this.setLocation(screenWidth/2-width/2, screenHeight/2-height/2);
//		
//		photo.setBounds(39, 10, 600, 530);    //560
//		getContentPane().add(photo);
//		photo.setBackground(new Color(255, 255, 224));
//		
//		
//		pre.setBounds(39, 560, 93, 23);
//		getContentPane().add(pre);
//		
//		
//		next.setBounds(470, 560, 93, 23);
//		getContentPane().add(next);
//		
//		
//		picname.setBounds(157, 560, 221, 15);
//		getContentPane().add(picname);
//		
//		picnumber.setBounds(420, 560, 56, 15);
//		getContentPane().add(picnumber);
//		
//		print.setBounds(877, 560, 93, 25);
//		getContentPane().add(print);
//		
//		//textArea.setOpaque(false);
//		//textArea.setBorder(null);
//		//JScrollPane scrollPane = new JScrollPane(textArea);                //������������
//		textPane.setEditable(false);
//		textPane.setOpaque(false);
//		textPane.setBorder(null);
//		JScrollPane scrollPane = new JScrollPane(textPane);                //������������
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scrollPane.setCursor(new Cursor(Cursor.HAND_CURSOR));     //���������״
//		scrollPane.setBounds(650, 10, 400, 560);
//		scrollPane.setOpaque(false);
//		scrollPane.getViewport().setOpaque(false);
//		scrollPane.setBorder(null);
//		getContentPane().add(scrollPane);
//
//		
////		pre.setIcon(MyImage.left);
////		next.setIcon(MyImage.right);
//		
//		//textArea.setBounds(511, 76, 310, 479);
//		//getContentPane().add(textArea);
//		
//		textArea.setLineWrap(true);
//		textArea.setFont(new Font("΢���ź�", Font.BOLD, 15));
//		//textArea.setFont(new Font("����", Font.PLAIN, 16));
//		textArea.setForeground(Color.RED);
//		String text = "";
//		String info[] = {"","","","","","","","",""};
//		String tmp[] = s.split(",");
//		for(int i = 0; i < tmp.length; i++)
//		{
//			info[i] = tmp[i];
//		}
//		for(int i = 0; i < 5; i++)
//		{
//			if (!"".equals(info[i]) && i == 0)
//			{
//				text += "��������:\n"+info[i] + "\n\n"; 
//				insertMessage(true, Color.RED, 16, "΢���ź�", "��������:\n");
//				insertMessage(false, Color.BLACK, 15, "΢���ź�", "\t"+ info[i] + "\n\n");
//			}
//			if (!"".equals(info[i]) && i == 1)
//			{
//				text += "�����ص�:\n"+info[i] + "\n\n"; 
//				insertMessage(true, Color.RED, 16, "΢���ź�", "�����ص�:\n");
//				insertMessage(false, Color.BLACK, 15, "΢���ź�", "\t"+info[i] + "\n\n");
//			}
////			if (!"".equals(info[i]) && i == 2)
////			{
////				text += "Ӧ�ó���:"+info[i] + "\n"; 
////			}
//			if (!"".equals(info[i]) && i == 3)
//			{
//				text += "Ǳ�ڿͻ�:\n"+info[i] + "\n\n"; 
//				insertMessage(true, Color.RED, 16, "΢���ź�", "Ǳ�ڿͻ�:\n");
//				insertMessage(false, Color.BLACK, 15, "΢���ź�", "\t"+info[i] + "\n\n");
//			}
//			if (!"".equals(info[i]) && i == 4)
//			{
//				text += "��Ŀ״̬:\n"+info[i] + "\n\n"; 
//				insertMessage(true, Color.RED, 16, "΢���ź�", "��Ŀ״̬:\n");
//				insertMessage(false, Color.BLACK, 15, "΢���ź�", "\t"+info[i] + "\n\n");
//			}
//		}
//		
//		String apply = MyDirHandle.getDirapply(serverPath);
//		String tmps[] = apply.split(",");
//		String result = "\t";       //"Ӧ�õ�վ    ��������    �ʱ�ʱ��\n";
//		insertMessage(true, Color.RED, 16, "΢���ź�", "Ӧ�õ�վ\\����:\n");
//		insertMessage(false, Color.BLACK, 15, "΢���ź�", "\tӦ�õ�վ  | ��������   | �ʱ�ʱ�ޣ���λ:��)\n");
//		for(int i = 0; i < tmps.length; i++)
//		{
//			result += tmps[i] + "\t";
//			if ((i+1)%3 == 0)  result += "\n\t";
//		}
//		insertMessage(false, Color.BLACK, 15, "΢���ź�", result);
//		textArea.setText(text + result);
//		
//		pre.addActionListener(this);
//		next.addActionListener(this);
//		print.addActionListener(this);
//		
//		String s = MyDirHandle.getDirPicPath(serverPath);
//		log.debug(s);
//		
//		pic = s.split(";");
//		pic_sum = pic.length;
//		pic_num = 0;
//		for(String e:pic)
//		{
//			System.out.println(e);
//		}
//		if (!s.equals(""))
//		{
//			pic_name = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// ����ϴ��ļ����ļ���  
//			String serverRootPath = "D:\\filemanage\\";
//			String clientpath = MyPath.DirPicPath + "\\" + pic_name;
//			UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
//			ImageIcon tmpimage = new ImageIcon(clientpath);
//			image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
//	                Image.SCALE_DEFAULT)); 
//			photo.setIcon(image); 
//			
//			picnumber.setText("��"+(pic_num+1)+"/" + pic_sum +"��");
//			picname.setText("ͼƬ��:" + pic_name);
//		}
//		
//		
//		setModal(true);
//		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		setVisible(true);
//	}
//	
//	public void insertMessage(boolean isBold, Color color, int size, String fontName,String s) {
//        StyledDocument doc = textPane.getStyledDocument();
//        
//        SimpleAttributeSet attr = new SimpleAttributeSet();
//        StyleConstants.setBold(attr, isBold);
//        StyleConstants.setForeground(attr, color);
//        StyleConstants.setFontFamily(attr,fontName);  // ����
//        StyleConstants.setFontSize(attr, size);
//
//        try {
//            doc.insertString(doc.getLength(), s,attr);
//        } catch (BadLocationException ex) {
//            
//        }
//    }
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getSource() == pre)
//		{
//			if(pic_num > 0) 
//			{
//				pic_num--;
//				String picname = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// ����ϴ��ļ����ļ���  
//				String serverRootPath = "D:\\filemanage\\";
//				String clientpath = MyPath.DirPicPath + "\\" + picname;
//				//���ص�����
//				UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
//				ImageIcon tmpimage = new ImageIcon(clientpath);
//				image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
//		                Image.SCALE_DEFAULT)); 
//				photo.setIcon(image); 
//				picnumber.setText("��"+(pic_num+1)+"/" + pic_sum +"��");
//				this.picname.setText("ͼƬ��:" + picname);
//			}
//			
//			
//		}
//		else if (e.getSource() == next)
//		{
//			if (pic_num < pic_sum-1) 
//			{
//				pic_num++;
//				String picname = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// ����ϴ��ļ����ļ���  
//				String serverRootPath = "D:\\filemanage\\";
//				String clientpath = MyPath.DirPicPath + "\\" + picname;
//				//���ص�����
//				UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
//				ImageIcon tmpimage = new ImageIcon(clientpath);
//				image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
//		                Image.SCALE_DEFAULT)); 
//				photo.setIcon(image); 
//				picnumber.setText("��"+(pic_num+1)+"/" + pic_sum +"��");
//				this.picname.setText("ͼƬ��:" + picname);
//			}
//		}
//		else if (e.getSource() == print)
//		{
//			ImagePrint.screenToImage(screenWidth/2-width/2+10, screenHeight/2-height/2+30, width-40, height-100);
//		}
//	}
//}

package com.scut.client.ui;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.log4j.Logger;

import com.scut.client.funtion.MyDirHandle;
import com.scut.client.funtion.UpDownFile;
import com.scut.client.user.MyPath;
import com.scut.tools.MyImage;
import com.suct.client.print.ImagePrint;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class MyShowDirInfoDialog1 extends JDialog implements ActionListener, MouseListener
{

	int pic_sum;
	int pic_num;
	String[] pic;         //����Ŀ¼�µ�����ͼƬ
	String serverPath;    //������Ŀ¼��·��
	private static Logger log = Logger.getLogger("client");
	
	JLabel photo = new JLabel("ͼƬ", JLabel.CENTER);
	JTextArea textArea = new JTextArea();
	//JLabel textArea = new JLabel();
	JButton pre = new JButton("");
	JButton next = new JButton("");
	JLabel picname = new JLabel("ͼƬ��:");
	JLabel picnumber = new JLabel("��/��");
	JButton print = new JButton("��ӡ����");
	ImageIcon image;
	String picpaths = "";
	String pic_name ; 
	
	String s; //��¼��������Ŀ¼�����Ϣ
	JTextPane textPane = new JTextPane();
	
	int width = 1100;		
	int height = 650;	
	int screenWidth;
	int screenHeight;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			MyShowDirInfoDialog1 dialog = new MyShowDirInfoDialog1("");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	

	/**
	 * Create the dialog.
	 */
	public MyShowDirInfoDialog1(String serverPath, String res) {
		String title = serverPath.substring(4);
				//serverPath.substring(serverPath.lastIndexOf("\\") + 1);
		setTitle(title);
		setIconImage(MyImage.frametitle.getImage());
		this.s = res;
		this.serverPath = serverPath;
		//setBounds(100, 100, 1100, 650);     //100
		
		getContentPane().setLayout(null);
		// ���ô���߶ȿ��
				this.setSize(width, height);
				
				// ���ô������
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		screenWidth = screenSize.width;
		screenHeight = screenSize.height;
		this.setLocation(screenWidth/2-width/2, screenHeight/2-height/2);
		
		photo.setBounds(39, 10, 600, 530);    //560
		getContentPane().add(photo);
		photo.setBackground(new Color(255, 255, 224));
		photo.addMouseListener(this);
		
		pre.setContentAreaFilled(false);
		pre.setIcon(new ImageIcon("image\\leftone.png"));
		pre.setBounds(48, 260, 40, 40);
		getContentPane().add(pre, 0);
		pre.setVisible(false);
		pre.addMouseListener(this);
		
		next.setContentAreaFilled(false);
		next.setIcon(new ImageIcon("image\\rightone.png"));
		next.setBounds(574, 260, 40, 40);
		getContentPane().add(next, 0);
		next.setVisible(false);
		next.addMouseListener(this);
		
		
		picname.setBounds(40, 564, 221, 15);
		getContentPane().add(picname, -1);
		
		picnumber.setBounds(586, 564, 56, 15);
		getContentPane().add(picnumber, -1);
		
		print.setBounds(968, 560, 93, 25);
		getContentPane().add(print, -1);
		
		//textArea.setOpaque(false);
		//textArea.setBorder(null);
		//JScrollPane scrollPane = new JScrollPane(textArea);                //������������
		textPane.setEditable(false);
		textPane.setOpaque(false);
		textPane.setBorder(null);
		JScrollPane scrollPane = new JScrollPane(textPane);                //������������
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setCursor(new Cursor(Cursor.HAND_CURSOR));     //���������״
		scrollPane.setBounds(650, 10, 400, 560);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		getContentPane().add(scrollPane);

		
//		pre.setIcon(MyImage.left);
//		next.setIcon(MyImage.right);
		
		//textArea.setBounds(511, 76, 310, 479);
		//getContentPane().add(textArea);
		
		textArea.setLineWrap(true);
		textArea.setFont(new Font("΢���ź�", Font.BOLD, 15));
		//textArea.setFont(new Font("����", Font.PLAIN, 16));
		textArea.setForeground(Color.RED);
		String text = "";
		String info[] = {"","","","","",""};
		String tmp[] = s.split(",");
		for(int i = 0; i < tmp.length; i++)
		{
			info[i] = tmp[i];
		}
		for(int i = 0; i < 5; i++)
		{
			log.debug(info[i]);
			if (!"".equals(info[i]) && i == 0 && !info[i].equals("null"))
			{
				//log.debug(info[i]);
				text += "�豸����:\n"+info[i] + "\n\n"; 
				insertMessage(true, Color.RED, 16, "΢���ź�", "��������:\n");
				insertMessage(false, Color.BLACK, 15, "΢���ź�", "\t"+ info[i] + "\n\n");
			}
			if (!"".equals(info[i]) && i == 1 && !info[i].equals("null"))
			{
				text += "�����ص�:\n"+info[i] + "\n\n"; 
				insertMessage(true, Color.RED, 16, "΢���ź�", "�����ص�:\n");
				insertMessage(false, Color.BLACK, 15, "΢���ź�", "\t"+info[i] + "\n\n");
			}
			if (!"".equals(info[i]) && i == 2 && !info[i].equals("null"))
			{
				text += "��Ŀ״̬:\n"+info[i] + "\n\n"; 
				insertMessage(true, Color.RED, 16, "΢���ź�", "��Ŀ״̬:\n");
				insertMessage(false, Color.BLACK, 15, "΢���ź�", "\t"+info[i] + "\n\n");
//				text += "Ӧ�ó���:"+info[i] + "\n"; 
			}
			if (!"".equals(info[i]) && i == 3 && !info[i].equals("null"))
			{
				text += "Ǳ�ڿͻ�:\n"+info[i] + "\n\n"; 
				insertMessage(true, Color.RED, 16, "΢���ź�", "Ǳ�ڿͻ�:\n");
				insertMessage(false, Color.BLACK, 15, "΢���ź�", "\t"+info[i] + "\n\n");
			}
//			if (!"".equals(info[i]) && i == 4)
//			{
//				
//			}
		}
		
		String apply = MyDirHandle.getDirapply(serverPath);
		if(!apply.equals(""))
		{
			insertMessage(true, Color.RED, 16, "΢���ź�", "Ӧ�õ�վ\\����:\n");
			insertMessage(false, Color.BLACK, 15, "΢���ź�", "\tӦ�õ�վ     | ��������   | �ʱ�ʱ�ޣ���λ:��)\n");
			
			String result = "\t";       //"Ӧ�õ�վ    ��������    �ʱ�ʱ��\n";
//			log.debug(apply + ":" + tmps.length);
			
			String tmps[] = apply.split("\t");
			
			for(int i = 0; i < tmps.length; i++)
			{
				String data[] = tmps[i].split(",");
				result += data[0] + "\t" + data[1] + "\t" + data[2] + "\n\t";
			}
			
			insertMessage(false, Color.BLACK, 15, "΢���ź�", result);
			textArea.setText(text + result);
		}
		
		pre.addActionListener(this);
		next.addActionListener(this);
		print.addActionListener(this);
		
		String s = MyDirHandle.getDirPicPath(serverPath);
		log.debug(s);
		
		pic = s.split(";");
		pic_sum = pic.length;
		pic_num = 0;
		for(String e:pic)
		{
			System.out.println(e);
		}
		if (!s.equals(""))
		{
			pic_name = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// ����ϴ��ļ����ļ���  
			String serverRootPath = "D:\\filemanage\\";
			String clientpath = MyPath.DirPicPath + "\\" + pic_name;
			UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
			ImageIcon tmpimage = new ImageIcon(clientpath);
			image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
	                Image.SCALE_DEFAULT)); 
			photo.setIcon(image); 
			
			picnumber.setText("��"+(pic_num+1)+"/" + pic_sum +"��");
			picname.setText("ͼƬ��:" + pic_name);
		}
		
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void insertMessage(boolean isBold, Color color, int size, String fontName,String s) {
        StyledDocument doc = textPane.getStyledDocument();
        
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBold(attr, isBold);
        StyleConstants.setForeground(attr, color);
        StyleConstants.setFontFamily(attr,fontName);  // ����
        StyleConstants.setFontSize(attr, size);

        try {
            doc.insertString(doc.getLength(), s,attr);
        } catch (BadLocationException ex) {
            
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == pre)
		{
			if(pic_num > 0) 
			{
				pic_num--;
				String picname = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// ����ϴ��ļ����ļ���  
				String serverRootPath = "D:\\filemanage\\";
				String clientpath = MyPath.DirPicPath + "\\" + picname;
				//���ص�����
				UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
				ImageIcon tmpimage = new ImageIcon(clientpath);
				image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
		                Image.SCALE_DEFAULT)); 
				photo.setIcon(image); 
				picnumber.setText("��"+(pic_num+1)+"/" + pic_sum +"��");
				this.picname.setText("ͼƬ��:" + picname);
			}
			
			
		}
		else if (e.getSource() == next)
		{
			if (pic_num < pic_sum-1) 
			{
				pic_num++;
				String picname = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// ����ϴ��ļ����ļ���  
				String serverRootPath = "D:\\filemanage\\";
				String clientpath = MyPath.DirPicPath + "\\" + picname;
				//���ص�����
				UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
				ImageIcon tmpimage = new ImageIcon(clientpath);
				image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
		                Image.SCALE_DEFAULT)); 
				photo.setIcon(image); 
				picnumber.setText("��"+(pic_num+1)+"/" + pic_sum +"��");
				this.picname.setText("ͼƬ��:" + picname);
			}
		}
		else if (e.getSource() == print)
		{
			ImagePrint.screenToImage(this.getX()+10, this.getY()+30, width-40, height-100);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		pre.setVisible(true);
		next.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		pre.setVisible(false);
		next.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

