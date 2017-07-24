package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class MyWaitLoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JButton button;
	
	private JLabel label ;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			MyWaitLoginDialog dialog = new MyWaitLoginDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public MyWaitLoginDialog() 
	{
		setBounds(100, 100, 318, 181);
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel);
		
		button = new JButton("\u5173\u95ED");
		button.setBounds(96, 110, 93, 23);
		contentPanel.add(button);
		
		label = new JLabel("\u767B\u5F55\u4E2D...");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		label.setBounds(96, 37, 178, 41);
		contentPanel.add(label);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void setLableText(String text)
	{
		label.setText(text);
	}
}
