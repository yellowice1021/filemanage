package com.scut.client.showpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.scut.client.funtion.UserHandle;
import com.scut.tools.MyImage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyUpdateUserDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	JButton btnNewButton = new JButton("确定修改");
	JButton btnNewButton_1 = new JButton("取消");
	JRadioButton radioButton1 = new JRadioButton("\u7BA1\u7406\u5458");
	JRadioButton radioButton2 = new JRadioButton("\u666E\u901A\u7528\u6237");
	ButtonGroup group = new ButtonGroup();// 创建单选按钮组
	String id;
//	String username;
//	String passwd;
//	int privillege;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			MyUpdateUserDialog dialog = new MyUpdateUserDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public MyUpdateUserDialog(String id, String username, String passwd,int privillege) {
		
		Toolkit kit=Toolkit.getDefaultToolkit();

        Dimension screenSize=kit.getScreenSize();  //系统对象获取工具

        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        int width = 379;
        int height = 260;
		
		this.id = id;
		setBounds(screenWidth/2-width/2, screenHeight/2-height/2, width, height);
		setTitle("修改用户信息");
		setIconImage(MyImage.frametitle.getImage());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("\u5E10\u53F7\uFF1A");
		label.setBounds(59, 40, 66, 15);
		label.setFont(new java.awt.Font("微软雅黑", 0, 15));
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(59, 86, 66, 15);
		label_1.setFont(new java.awt.Font("微软雅黑", 0, 15));
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u7B49\u7EA7:");
		label_2.setBounds(59, 130, 66, 15);
		label_2.setFont(new java.awt.Font("微软雅黑", 0, 15));
		contentPanel.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(132, 37, 166, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		textField.setText(username);
		
		textField_1 = new JTextField();
		textField_1.setBounds(132, 83, 166, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(passwd);
		
		radioButton1.setFont(new java.awt.Font("微软雅黑", 0, 15));
		radioButton2.setFont(new java.awt.Font("微软雅黑", 0, 15));
		
		radioButton1.setBounds(130, 126, 77, 23);
		contentPanel.add(radioButton1);
		
		
		radioButton2.setBounds(210, 126, 96, 23);
		contentPanel.add(radioButton2);
		
		group.add(radioButton1);// 将radioButton1增加到单选按钮组中
	    group.add(radioButton2);// 将radioButton2增加到单选按钮组中
	    if (privillege == 2) radioButton1.setSelected(true);
	    else if (privillege == 3) radioButton2.setSelected(true);
		
		btnNewButton.setBounds(76, 176, 89, 23);
		btnNewButton.setFont(new java.awt.Font("微软雅黑", 0, 12));
		contentPanel.add(btnNewButton);
		
		
		btnNewButton_1.setBounds(195, 176, 89, 23);
		btnNewButton_1.setFont(new java.awt.Font("微软雅黑", 0, 12));
		contentPanel.add(btnNewButton_1);
		
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnNewButton)
		{
//			int answer = JOptionPane.showConfirmDialog(null, "确定修改该用户？",null, JOptionPane.OK_CANCEL_OPTION);
//			if (answer == JOptionPane.CANCEL_OPTION) return;
			int privillege;
			if (radioButton1.isSelected())
			{
				privillege = 2;
			}
			else privillege = 3;
			String res = UserHandle.updateUser(String.valueOf(id), textField.getText().trim(), textField_1.getText().trim(), String.valueOf(privillege) );	
			if (res.equals("yes"))
			{
				JOptionPane.showMessageDialog(null, "修改成功",null, JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "修改失败",null, JOptionPane.INFORMATION_MESSAGE);
			}
			MyLookUserPanel.getAllUser();
			
		}
		else if (e.getSource() == btnNewButton_1)
		{
			
		}
		this.dispose();
	}
}
