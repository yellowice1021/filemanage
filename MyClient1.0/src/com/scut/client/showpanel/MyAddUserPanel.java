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
	JLabel label = new JLabel("�û���  ");
	JLabel label1 = new JLabel("�ʺ�    ");
	JLabel label2 = new JLabel("����    ");
	
	JLabel label3 = new JLabel("�û��ȼ�  ");
	JTextField field = new JTextField(15);
	JTextField field1 = new JTextField(15);
	JPasswordField field2 = new JPasswordField(15);
	JRadioButton radioButton1 = new JRadioButton("����Ա");// ������ѡ��ť
    JRadioButton radioButton2 = new JRadioButton("��ͨ�û�");// ������ѡ��ť
    ButtonGroup group = new ButtonGroup();// ������ѡ��ť��
    JButton button1 = new JButton("ȷ��");
    JButton button2 = new JButton("����");
	/**
	 * Create the panel.
	 */
	public MyAddUserPanel() {
		
		this.setLayout(new FlowLayout());
		
        group.add(radioButton1);// ��radioButton1���ӵ���ѡ��ť����
        group.add(radioButton2);// ��radioButton2���ӵ���ѡ��ť����
        
        Box b = Box.createHorizontalBox();  //����box
		Box b1 = Box.createHorizontalBox();  //����box
		Box b2 = Box.createHorizontalBox();  //����box
		Box b3 = Box.createHorizontalBox();  //����box
		Box b4 = Box.createHorizontalBox();  //����box
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
		b4.add(Box.createVerticalStrut(40));  // ��ӿ��40��ˮƽ�ռ�  
		b4.add(button2);
		
		// ��Ӹ߶�Ϊ200�Ĵ�ֱ�ռ�   
		
		bv1.add(Box.createVerticalStrut(20));  
		bv1.add(b);  
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );// ��ӿ��Ϊ100���߶�Ϊ20�Ĺ̶�����
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
					JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				String userName = field1.getText().toString().trim();
				String passwd = field2.getText().toString().trim();
				String privillege = null;
				String realname = field.getText().toString().trim();
				
				boolean flag = true;
				String warns= "��δ����";
				if (realname.equals(""))
				{
					warns += "�û���,";
					flag = false;
				}
				if (passwd.equals(""))
				{
					warns += "����,";
					flag = false;
				}
				if (userName.equals(""))
				{
					warns += "�ʺ�";
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
					JOptionPane.showMessageDialog(null, "�㻹δѡ���û�Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				System.out.println(userName + passwd +"pri"+privillege + ","+realname);
				String res = UserHandle.addUser(userName, passwd, privillege, realname);
				if (res.equals("yes"))
				{
					JOptionPane.showMessageDialog(null, "�����û��ɹ�",null, JOptionPane.INFORMATION_MESSAGE);
					field.setText("");
					field1.setText("");
					field2.setText("");
				}
				else if(res.equals("no"))
				{
					JOptionPane.showMessageDialog(null, "�����û�ʧ��",null, JOptionPane.INFORMATION_MESSAGE);
				}
				else if (res.equals("usernameexist"))
				{
					JOptionPane.showMessageDialog(null, "���û��Ѿ����ڣ�����ʧ��",null, JOptionPane.INFORMATION_MESSAGE);
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
//		   g.drawImage(MyImage.showfile.getImage(), x, y, getSize().width, getSize().height, this);// ͼƬ���Զ�����
//	}

}
