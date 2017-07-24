package com.scut.client.showpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.scut.client.funtion.UserHandle;
import com.scut.client.user.MyUser;
import com.scut.tools.MyImage;

public class MyAddUserPanel extends JPanel {
	JLabel label = new JLabel("用户名  ");
	JLabel label1 = new JLabel("帐号    ");
	JLabel label2 = new JLabel("密码    ");
	
	JLabel label3 = new JLabel("用户等级  ");
	JTextField field = new JTextField(15);
	JTextField field1 = new JTextField(15);
	JPasswordField field2 = new JPasswordField(15);
	JRadioButton radioButton1 = new JRadioButton("管理员");// 创建单选按钮
    JRadioButton radioButton2 = new JRadioButton("普通用户");// 创建单选按钮
    ButtonGroup group = new ButtonGroup();// 创建单选按钮组
    JButton button1 = new JButton("确定");
    JButton button2 = new JButton("重置");
	/**
	 * Create the panel.
	 */
	public MyAddUserPanel() {
		
		this.setLayout(new FlowLayout());
		
        group.add(radioButton1);// 将radioButton1增加到单选按钮组中
        group.add(radioButton2);// 将radioButton2增加到单选按钮组中
        
        Box b = Box.createHorizontalBox();  //横向box
		Box b1 = Box.createHorizontalBox();  //横向box
		Box b2 = Box.createHorizontalBox();  //横向box
		Box b3 = Box.createHorizontalBox();  //横向box
		Box b4 = Box.createHorizontalBox();  //横向box
		Box bv1 = Box.createVerticalBox();
		
		b.add(label);
		b.add(field);
		b1.add(label1);
		b1.add(field1);
		b2.add(label2);
		b2.add(field2);
		b3.add(label3);
		b3.add(radioButton1);
		b3.add(radioButton2);
		b4.add(button1);
		b4.add(Box.createVerticalStrut(40));  // 添加宽度40的水平空间  
		b4.add(button2);
		
		// 添加高度为200的垂直空间   
		
		bv1.add(Box.createVerticalStrut(20));  
		bv1.add(b);  
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );// 添加宽度为100，高度为20的固定区域
		bv1.add(b1);
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );
		bv1.add(b2);
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );
		bv1.add(b3);
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );
		bv1.add(b4);
	
		this.add(bv1);
		
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				if(MyUser.privilage!=1){
					JOptionPane.showMessageDialog(null, "当前用户等级没有权限",null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				String userName = field1.getText().toString().trim();
				String passwd = field2.getText().toString().trim();
				String privillege = null;
				String realname = field.getText().toString().trim();
				
				boolean flag = true;
				String warns= "您未输入";
				if (realname.equals(""))
				{
					warns += "用户名,";
					flag = false;
				}
				if (passwd.equals(""))
				{
					warns += "密码,";
					flag = false;
				}
				if (userName.equals(""))
				{
					warns += "帐号";
					flag = false;
					
				}
				if (flag == false)
				{
					JOptionPane.showMessageDialog(null, warns,null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if (radioButton1.isSelected())
				{
					privillege = "2";
					System.out.println("pri"+privillege);
				}
				else if (radioButton2.isSelected())
				{
					privillege = "3";
					System.out.println("pri"+privillege);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "你还未选择用户权限",null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				System.out.println(userName + passwd +"pri"+privillege + ","+realname);
				String res = UserHandle.addUser(userName, passwd, privillege, realname);
				if (res.equals("yes"))
				{
					JOptionPane.showMessageDialog(null, "增加用户成功",null, JOptionPane.INFORMATION_MESSAGE);
					field.setText("");
					field1.setText("");
					field2.setText("");
				}
				else if(res.equals("no"))
				{
					JOptionPane.showMessageDialog(null, "增加用户失败",null, JOptionPane.INFORMATION_MESSAGE);
				}
				else if (res.equals("usernameexist"))
				{
					JOptionPane.showMessageDialog(null, "该用户已经存在，增加失败",null, JOptionPane.INFORMATION_MESSAGE);
					field.setText("");
					field1.setText("");
					field2.setText("");
				}
				
			}
		});
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				field.setText("");
				field1.setText("");
				field2.setText("");
			}
		});
	}
//	public void paintComponent(Graphics g) 
//	{
//		   int x = 0;// this.getX()
//		   int y = 0;//this.getY();
//		   //if (icon != null) 
//		   g.drawImage(MyImage.showfile.getImage(), x, y, getSize().width, getSize().height, this);// 图片会自动缩放
//	}

}
