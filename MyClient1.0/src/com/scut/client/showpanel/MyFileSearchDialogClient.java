package com.scut.client.showpanel;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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

public class MyFileSearchDialogClient extends JDialog implements ActionListener{

//	private final JPanel contentPanel = new MyPanel(MyImage.showfile);
	private JTextField textField;
	JButton btnNewButton = new JButton("确定");
	JButton btnNewButton_1 = new JButton("取消");
	
	String aimPath =MyParameters.getSavePath();
	
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
	public MyFileSearchDialogClient(String aimPath) {
		
		Toolkit kit=Toolkit.getDefaultToolkit();

        Dimension screenSize=kit.getScreenSize();  //系统对象获取工具

        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        
        int width = 360;
        int height = 220;
        
		setTitle("文件名检索");
		this.aimPath = aimPath;
		this.setIconImage(MyImage.frametitle.getImage());
		setBounds(screenWidth/2-width/2, screenHeight/2-height/2, width, height);
//		setContentPane(contentPanel);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("文件名称");
		label.setBounds(39, 54, 70, 15);
		label.setFont(new java.awt.Font("微软雅黑", 0, 15));
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(120, 51, 169, 23);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		btnNewButton.setBounds(70, 111, 77, 27);
		getContentPane().add(btnNewButton);
		
		
		btnNewButton_1.setBounds(199, 111, 77, 27);
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
			MyFileSearchPanelClient.addFileClient(aimPath,textField.getText().trim());
			this.dispose();
		}
		else if (e.getSource() == btnNewButton_1)
		{
			this.dispose();
		}
	}

}
