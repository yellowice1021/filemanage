package com.scut.client.showpanel;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.scut.client.ui.MyPanel;
import com.scut.tools.MyImage;
import com.scut.tools.MyParameters;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFileSearchDialogClient1 extends JDialog implements ActionListener{

	//private final JPanel contentPanel = new MyPanel(MyImage.showfile);
	private JTextField textField;
	JButton btnNewButton = new JButton("确定");
	JButton btnNewButton_1 = new JButton("取消");
	
	String aimPath ="doc";
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			MyFileSearchDialog dialog = new MyFileSearchDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public MyFileSearchDialogClient1(String aimPath) {
		setTitle("文件名检索");
		this.aimPath = aimPath; 
		setBounds(100, 100, 416, 252);
		//setContentPane(contentPanel);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("文件名称");
		label.setBounds(78, 54, 100, 15);
		getContentPane().add(label);
		label.setFont(new Font("微软雅黑",Font.PLAIN,14));
		
		textField = new JTextField();
		textField.setBounds(182, 51, 112, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		btnNewButton.setBounds(78, 111, 93, 23);
		getContentPane().add(btnNewButton);
		
		
		btnNewButton_1.setBounds(201, 111, 93, 23);
		getContentPane().add(btnNewButton_1);
		
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if (e.getSource() == btnNewButton)
		{
			MyFileSearchPanelClient1.addFile1(aimPath,textField.getText().trim());
			//MyFileSearchPanel.addFileClient(aimPath,textField.getText().trim());
			this.dispose();
		}
		else if (e.getSource() == btnNewButton_1)
		{
			this.dispose();
		}
	}

}

