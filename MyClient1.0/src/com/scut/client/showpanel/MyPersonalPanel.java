package com.scut.client.showpanel;

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

public class MyPersonalPanel extends JPanel {


	JLabel label1 = new JLabel("�ʺ�    ");
	JLabel label2 = new JLabel("������    ");
	JLabel label3 = new JLabel("������    ");
	JLabel label4 = new JLabel("������һ��������    ");
	public static JTextField field1 = new JTextField(15);//�˺�
	public static JPasswordField field2 = new JPasswordField(15);//������
	public static JPasswordField field3 = new JPasswordField(15);//������
	public static JPasswordField field4 = new JPasswordField(15);//ȷ��������
    JButton button1 = new JButton("ȷ��");
    JButton button2 = new JButton("����");
    
    public static void setuser(String user)
    {
    	field1.setText(user);
    	field2.setText("");
    	field3.setText("");
    }
    
//    public void paintComponent(Graphics g) 
//	{
//		   int x = 0;// this.getX()
//		   int y = 0;//this.getY();
//		   //if (icon != null) 
//		  // g.drawImage(MyImage.showfile.getImage(), x, y, getSize().width, getSize().height, this);// ͼƬ���Զ�����
//	}
	/**
	 * Create the panel.
	 */
	public MyPersonalPanel() 
	{
		
		this.setLayout(new FlowLayout());
        
		field2.setText("");
		field3.setText("");
		
		field1.setEditable(false);
		Box b1 = Box.createHorizontalBox();  //����box
		Box b2 = Box.createHorizontalBox();  //����box
		Box b3 = Box.createHorizontalBox();  //����box
		Box b4 = Box.createHorizontalBox();  //����box
		Box b5 = Box.createHorizontalBox();  //����box
		Box bv1 = Box.createVerticalBox();
		
		b1.add(label1);//�˺�
		b1.add(field1);
		b2.add(label2);//������
		b2.add(field3);
		b3.add(label3);//������
		b3.add(field2);
		b4.add(label4);//�ٴ�����������
		b4.add(field4);
		
		b5.add(button1);
		b5.add(Box.createVerticalStrut(40));  // ��ӿ��40��ˮƽ�ռ�  
		b5.add(button2);
		
		// ��Ӹ߶�Ϊ200�Ĵ�ֱ�ռ�    
	    bv1.add(Box.createVerticalStrut(20));  
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );// ��ӿ��Ϊ100���߶�Ϊ20�Ĺ̶�����
		bv1.add(b1);
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );
		bv1.add(b2);
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );
		bv1.add(b3);
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );
		bv1.add(b4);
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );
		bv1.add(b5);
	
		this.add(bv1);
		
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName = field1.getText().toString().trim();
				String oldpasswd = field3.getText().toString().trim();
				String newpasswd = field2.getText().toString().trim();
				String againpasswd = field4.getText().toString().trim();//field3 to field4

				if(oldpasswd.equals("") || newpasswd.equals("") || againpasswd.equals(""))
				{
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��",null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if (!againpasswd.equals(newpasswd))
				{
					JOptionPane.showMessageDialog(null, "�������������벻һ��",null, JOptionPane.INFORMATION_MESSAGE);
					return;	
				}

				String res = UserHandle.updatePwd(userName, newpasswd, oldpasswd);
				System.out.println(res);
				if (res.equals("yes"))
				{
					JOptionPane.showMessageDialog(null, "�޸�����ɹ� ���������ʹ������Ч",null, JOptionPane.INFORMATION_MESSAGE);
					field2.setText("");
					field3.setText("");
					field4.setText("");
				}
				else if(res.equals("pass"))
				{
					JOptionPane.showMessageDialog(null, "���������",null, JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "�޸�ʧ��",null, JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				field3.setText("");
				field2.setText("");
				field4.setText("");
			}
		});
	}
}
