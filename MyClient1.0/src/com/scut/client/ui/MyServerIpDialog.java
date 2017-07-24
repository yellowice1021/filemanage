package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.scut.tools.MyImage;
import com.scut.tools.MyParameters;

public class MyServerIpDialog extends JDialog {
	private JTextField textField1;
	private JTextField textField2;
	private JButton button_1;
	private JButton button;
	private JLabel label;
	private JLabel label_1;
	private static Logger log = Logger.getLogger("client");
	
	private JLabel frameHost;
	private JLabel framePort;
	/**
	 * Launch the application.
	 */
//	public static void main(Strin g[] args) 
//	{
//		try {
//			MyServerDialog dialog = new MyServerDialog();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public MyServerIpDialog(JFrame frame) 
	{
		super(frame);
		
		init();
		//取消更改
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				log.info("您取消更改服务器设置");
			}
		});
		//确认更改
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String ip = textField1.getText().toString();
				String port = textField2.getText();
				MyParameters.setPort(port);
				MyParameters.setIp(ip);
				dispose();
			}
		});
		
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public MyServerIpDialog(JFrame frame, JLabel frameHost, JLabel framePort) 
	{
		super();
		this.frameHost = frameHost;
		this.framePort = framePort;
		this.setIconImage(MyImage.frametitle.getImage());
		
		init();
		frameHost.setText(MyParameters.getIp());
		framePort.setText(MyParameters.getPort());
		//取消更改
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				log.info("取消更改服务器设置");
			}
		});
		//确认更改
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String ip = textField1.getText().toString();
				String port = textField2.getText();
				MyParameters.setPort(port);
				MyParameters.setIp(ip);
				MyParameters.setIppath("");
				frameHost.setText(ip);
				framePort.setText(port);
				dispose();
			}
		});
		
		//设置模态必须得按这个顺序（事件，模态，可见）
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void init()
	{
		Toolkit kit=Toolkit.getDefaultToolkit();

        Dimension screenSize=kit.getScreenSize();  //系统对象获取工具

        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        int width = 360;
        int height = 220;
		
		setBounds(screenWidth/2-width/2, screenHeight/2-height/2, width, height);
		setTitle("修改服务器ip");
		//setBounds(100, 100, 296, 237);
		getContentPane().setLayout(null);
		
		label = new JLabel("服务器ip");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(38, 27, 64, 41);
		getContentPane().add(label);
		
		label_1 = new JLabel("端口");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(38, 79, 64, 34);
		getContentPane().add(label_1);
		
		button = new JButton("确定");
		button.setBounds(66, 134, 79, 23);
		getContentPane().add(button);
		
		button_1 = new JButton("取消");
		button_1.setBounds(196, 134, 79, 23);
		getContentPane().add(button_1);
		
		textField1 = new JTextField();
		textField1.setBounds(119, 36, 170, 23);
		
		textField1.setColumns(15);
		
		textField2 = new JTextField();
		textField2.setBounds(119, 86, 170, 23);
		textField2.setEditable(true);
		getContentPane().add(textField2);
		getContentPane().add(textField1);
		
	}
}
