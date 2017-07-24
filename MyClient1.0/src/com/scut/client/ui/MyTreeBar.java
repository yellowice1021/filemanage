package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class MyTreeBar extends JPanel{
	public JLabel label = new JLabel("当前路径");
	public JTextField textField = new JTextField();
	JToolBar bar = new JToolBar();
	public MyTreeBar(String buttonText)
	{
		//button = new JButton(buttonText);
		setLayout(new BorderLayout());
		bar.add(label);
		bar.add(textField);
		label.setFont(new Font("微软雅黑",Font.PLAIN,14));//设置字体
		textField.setFont(new Font("微软雅黑",Font.PLAIN,14));//设置字体
		
		this.add(bar, BorderLayout.CENTER);
		textField.setEditable(false);
	}
	

}
