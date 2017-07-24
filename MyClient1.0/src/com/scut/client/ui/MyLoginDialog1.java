package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;

import com.scut.client.funtion.MyLogin;
import com.scut.client.user.MyUser;

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


public class MyLoginDialog1 extends JDialog {
	private JPasswordField textField;
	private JTextField comboBox;
	private JButton button_1;
	private JButton button;
	private JLabel label;
	private JLabel label_1;
	private static Logger log = Logger.getLogger("client");
	private boolean first;
	
	private JPanel loginPanel = new JPanel();    
	private JPanel loginresPanel = new JPanel();
	private CardLayout cardLayout; 
	
	private JLabel loginres = new JLabel("", JLabel.CENTER);


	/**
	 * Create the dialog.
	 */
	public MyLoginDialog1(JFrame frame) {
		super(frame);
		Toolkit kit=Toolkit.getDefaultToolkit();

        Dimension screenSize=kit.getScreenSize();  //ϵͳ�����ȡ����

        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        int width = 296;
        int height = 237;
        
        this.setLocation(screenWidth/2-width/2, screenHeight/2-height/2);
		first = true;
		cardLayout = new CardLayout();
		loginPanel.setLayout(null);
		loginresPanel.setLayout(new BorderLayout());
		loginresPanel.add(loginres, BorderLayout.CENTER);
		loginres.setForeground(Color.RED);
		loginres.setFont(new Font("����",Font.PLAIN,20)); 
		setSize(width, height);
		setTitle("�û���¼");
		getContentPane().setLayout(null);
		//setUndecorated(true);   //����ʾ������
		
		//System.setProperty("sun.java2d.noddraw", "true");	//����ϵͳ������  �����������ᱨ��֧��͸���Ĺ��ܵ��쳣
		//WindowUtils.setWindowAlpha(this, (float)0.75);
		
		label = new JLabel("�ʺ�");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		label.setBounds(46, 32, 64, 41);
		loginPanel.add(label);
		
		label_1 = new JLabel("����");
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		label_1.setBounds(46, 86, 64, 34);
		loginPanel.add(label_1);
		
		button = new JButton("��¼");
		button.setBounds(44, 144, 80, 27);
		loginPanel.add(button);
		
		button_1 = new JButton("ȡ��");
		button_1.setBounds(166, 144, 80, 27);
		loginPanel.add(button_1);
		
		textField = new JPasswordField();
		textField.setBounds(102, 90, 144, 27);
		loginPanel.add(textField);
		textField.setColumns(15);
		textField.setEchoChar('*');
		
		comboBox = new JTextField();
		comboBox.setBounds(102, 39, 144, 27);
		comboBox.setEditable(true);
		loginPanel.add(comboBox);
			
		getContentPane().setLayout(cardLayout);
		getContentPane().add("1", loginPanel);
		getContentPane().add("2", loginresPanel);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				log.debug("�رյ�¼�Ի���");
				dispose();
			}
		});
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBox.getText() == null || textField.getText() == null || comboBox.getText().equals("") || textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ��",null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				log.debug(comboBox.getText().toString());
				log.debug(textField.getText());
				
				String name = comboBox.getText().toString();
				
				
				CountDownLatch mCountDownLatch = new CountDownLatch(1);
				String res = MyLogin.login(comboBox.getText().toString(), textField.getText());
				
				
				int result = Integer.parseInt(res.trim());
				System.out.println(result);
				if (result >= 1 && result <= 3)
				{
					MyUser.userName = name;
					MyUser.state = "����";
					MyUser.privilage = result;
				}
				else
				{
					JOptionPane.showMessageDialog(null, MyLogin.getLoginString(result),null, JOptionPane.INFORMATION_MESSAGE);
					//return;
			//loginres.setText(MyLogin.getLoginString(result));
//					cardLayout.show(getContentPane(), "2");
				}
				//return;
				dispose();
//				mCountDownLatch.countDown();
//				
//				try {
//					Thread.sleep(4000);
//					mCountDownLatch.await();
//					dispose();
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
			}
		});
		
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
