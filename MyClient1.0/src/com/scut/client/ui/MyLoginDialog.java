package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.scut.client.funtion.MyLogin;
import com.scut.client.user.MyUser;
import com.scut.tools.MyImage;
import com.sun.star.sheet.Border;

//import com.sun.jna.examples.WindowUtils;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;


public class MyLoginDialog extends JPanel{
	public static  JPasswordField textField  = new JPasswordField(20);
	public static JTextField comboBox = new JTextField(20);
	public static JButton button_1 = new JButton("取消  ");
	public static JButton button = new JButton("登录  ");
	private JLabel label = new JLabel("  帐号   ");
	private JLabel label_1 = new JLabel(" 密码   ");
	private static Logger log = Logger.getLogger("client");
	private JPanel loginPanel;
	private JLabel loginres = new JLabel("", JLabel.CENTER);

	/**
	 * Create the dialog.
	 */
	public MyLoginDialog() {
		
		//this.setBounds(50, 50, 520, 520);
		//this.setBackground(Color.blue);
		//this.setBorder(BorderFactory.createTitledBorder("登录"));
		
		//this.setLocation(100, 100);
		this.setLayout(new FlowLayout());
		    loginPanel = new JPanel();
			Box b1 = Box.createHorizontalBox();  //横向box
			//b1.setBounds(20, 100, 100, 100);
			
			Box b2 = Box.createHorizontalBox();  //横向box
			//b2.setBounds(20, 200, 100, 100);
			
			Box b4 = Box.createHorizontalBox();  //横向box
			//b4.setBounds(20, 300, 100, 100);
			
			Box bv1 = Box.createVerticalBox();
			
			b1.add(label);
			b1.add(comboBox);
			b2.add(label_1);
			b2.add(textField);
			b4.add(button);
			b4.add(Box.createVerticalStrut(20));  // 添加宽度40的水平空间  
			b4.add(button_1);
			
			// 添加高度为200的垂直空间   
			
			bv1.add(Box.createVerticalStrut(20));  
			bv1.add(Box.createRigidArea(new Dimension(150, 20)) );// 添加宽度为100，高度为20的固定区域
			bv1.add(b1);
			bv1.add(Box.createRigidArea(new Dimension(150, 20)) );
			bv1.add(b2);
			bv1.add(Box.createRigidArea(new Dimension(150, 20)) );
			bv1.add(Box.createRigidArea(new Dimension(150, 20)) );
			bv1.add(b4);
			loginPanel.add(bv1);
		    javax.swing.border.Border lineBorder = BorderFactory.createLineBorder(Color.lightGray);
		    loginPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "登录"));
			loginPanel.setPreferredSize(new Dimension(380,200));
     		loginPanel.setOpaque(false);
			this.add(loginPanel);
//			
//		   loginPanel.add(bv1);
//           label.setBounds(50, 120, 100, 20);
//           textField.setBounds(100,120,100, 20);
//           
//           label_1.setBounds(150, 180, 100, 20);
//           comboBox.setBounds(200,180,100, 20);
//           
//           button.setBounds(100,240,100, 20);
//           button_1.setBounds(180,240,100, 20);
//		    
//           loginPanel.add(label);
//           loginPanel.add(comboBox);
//           
//           loginPanel.add(label_1);
//           loginPanel.add(textField);
//           
//
//           loginPanel.add(button);
//           loginPanel.add(button_1);
//           
//		    javax.swing.border.Border lineBorder = BorderFactory.createLineBorder(Color.lightGray);
//		   // TitledBorder titleBoprder = BorderFactory.createTitledBorder("登录");
//		    loginPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "登录"));
//			//loginPanel.setLayout(null);
//			loginPanel.setPreferredSize(new Dimension(380,150));
//			//loginPanel.setLocation(10, 150);
//			loginPanel.setOpaque(false);
//			this.add(loginPanel);

//		button_1.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				log.debug("Logout");
//				//dispose();
//			}
//		});
	
	}
//		button.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//
//				if (comboBox.getText() == null || textField.getText() == null || comboBox.getText().equals("") || textField.getText().equals(""))
//				{
//					JOptionPane.showMessageDialog(null, "用户名或密码不能为空",null, JOptionPane.INFORMATION_MESSAGE);
//					return;
//				}
//				log.debug(comboBox.getText().toString());
//				log.debug(textField.getText());
//				
//				String name = comboBox.getText().toString();
//				
//				
//				CountDownLatch mCountDownLatch = new CountDownLatch(1);
//				String res = MyLogin.login(comboBox.getText().toString(), textField.getText());
//				
//				int result = Integer.parseInt(res.trim());
//				System.out.println(result);
//				if (result >= 1 && result <= 3)
//				{
//					MyUser.userName = name;
//					MyUser.state = "在线";
//					MyUser.privilage = result;
//				}
//				
//				else
//				{
//					JOptionPane.showMessageDialog(null, MyLogin.getLoginString(result),null, JOptionPane.INFORMATION_MESSAGE);
//					//return;
//			//loginres.setText(MyLogin.getLoginString(result));
////					cardLayout.show(getContentPane(), "2");
//				}
//				//return;
//				//dispose();
////				mCountDownLatch.countDown();
////				
////				try {
////					Thread.sleep(4000);
////					mCountDownLatch.await();
////					dispose();
////				} catch (InterruptedException e1) {
////					// TODO Auto-generated catch block
////					e1.printStackTrace();
////				}
//				
//			}
//		});
//		
//		//setModal(true);
//		//this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		//this.setVisible(true);
//	}
	public void paintComponent(Graphics g) 
	{
		   int x = 0;// this.getX()
		   int y = 0;//this.getY();
		   //if (icon != null) 
		   g.drawImage(MyImage.dalouImage.getImage(), x, y, getSize().width, getSize().height, this);// 图片会自动缩放
	}
}
