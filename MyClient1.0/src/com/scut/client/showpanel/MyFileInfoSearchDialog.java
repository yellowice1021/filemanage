package com.scut.client.showpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFileInfoSearchDialog extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	JLabel lblNewLabel = new JLabel("电站");
	JLabel lblNewLabel_1 = new JLabel("机组");
	JLabel lblNewLabel_2 = new JLabel("项目名称");
	JLabel lblNewLabel_3 = new JLabel("关键字");
	JLabel lblNewLabel_4 = new JLabel("项目立项号");
	JLabel lblNewLabel_5 = new JLabel("负责人");
	JLabel lblNewLabel_6 = new JLabel("应用场合");
	JLabel lblNewLabel_7 = new JLabel("潜在客户");
	JLabel lblNewLabel_8 = new JLabel("项目状态");
	JButton btnNewButton = new JButton("当个查询");
	JButton btnNewButton_1 = new JButton("组合查询");
	JButton btnNewButton_2 = new JButton("取消");
	Box b1 = Box.createHorizontalBox();  //横向box
	Box b2 = Box.createHorizontalBox();  //横向box
	Box b3 = Box.createHorizontalBox();  //横向box
	Box b4 = Box.createHorizontalBox();  //横向box
	Box b5 = Box.createHorizontalBox();  //横向box
	Box b6 = Box.createHorizontalBox();  //横向box
	Box b7 = Box.createHorizontalBox();  //横向box
	Box b8 = Box.createHorizontalBox();  //横向box
	Box b10 = Box.createHorizontalBox();  //横向box
	Box b11 = Box.createHorizontalBox();  //横向box
	
	Box b9 = Box.createVerticalBox();  //纵向box
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			MyFileInfoSearchDialog dialog = new MyFileInfoSearchDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public MyFileInfoSearchDialog() 
	{
		setBounds(100, 100, 413, 516);
		getContentPane().setLayout(new BorderLayout());
		
		textField = new JTextField();
		
		textField.setColumns(10);
		
	
		
		textField_1 = new JTextField();
		
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		
		textField_7.setColumns(10);
		textField_8 = new JTextField();
		
		textField_8.setColumns(10);
		
		b1.add(lblNewLabel);
		b1.add(Box.createVerticalStrut(20));  
		b1.add(textField);
		b1.add(Box.createVerticalStrut(20));  
		b2.add(lblNewLabel_1);
		b2.add(Box.createVerticalStrut(20));  
		b2.add(textField_1);
		b2.add(Box.createVerticalStrut(20));  
		b3.add(lblNewLabel_2);
		b3.add(Box.createVerticalStrut(20));  
		b3.add(textField_2);
		b3.add(Box.createVerticalStrut(20));  
		b4.add(lblNewLabel_3);
		b4.add(Box.createVerticalStrut(20));  
		b4.add(textField_3);
		b4.add(Box.createVerticalStrut(20));  
		b5.add(lblNewLabel_4);
		b5.add(Box.createVerticalStrut(20));  
		b5.add(textField_4);
		b5.add(Box.createVerticalStrut(20));  
		b6.add(lblNewLabel_5);
		b6.add(Box.createVerticalStrut(20));  
		b6.add(textField_5);
		b6.add(Box.createVerticalStrut(20));  
		b7.add(lblNewLabel_6);
		b7.add(Box.createVerticalStrut(20));  
		b7.add(textField_6);
		b7.add(Box.createVerticalStrut(20));  
		
		b10.add(lblNewLabel_7);
		b10.add(Box.createVerticalStrut(20));  
		b10.add(textField_7);
		b10.add(Box.createVerticalStrut(20));  
		
		b11.add(lblNewLabel_8);
		b11.add(Box.createVerticalStrut(20));  
		b11.add(textField_8);
		b11.add(Box.createVerticalStrut(20));  
		
		b8.add(btnNewButton);
		b8.add(btnNewButton_1);
		b8.add(btnNewButton_2);
		
		b9.add(b1);
		b9.add(Box.createRigidArea(new Dimension(100, 30)) );
		b9.add(b2);
		b9.add(Box.createRigidArea(new Dimension(100, 30)) );
		b9.add(b3);
		b9.add(Box.createRigidArea(new Dimension(100, 30)) );
		b9.add(b4);
		b9.add(Box.createRigidArea(new Dimension(100, 30)) );
		b9.add(b5);
		b9.add(Box.createRigidArea(new Dimension(100, 30)) );
		b9.add(b6);
		b9.add(Box.createRigidArea(new Dimension(100, 30)) );
		b9.add(b7);
		
		b9.add(b10);
		b9.add(b11);
		b9.add(Box.createRigidArea(new Dimension(100, 30)) );
		b9.add(b8);
		
		getContentPane().add(b9, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
