package com.scut.client.showpanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.scut.client.funtion.LogHandle;

public class MySetLogDatePanel extends JPanel implements ActionListener{
	
	JLabel label = new JLabel("��־����������");
	String times[] = {""};
	//JComboBox comboBox;
	JTextField text = new JTextField(5);
	JButton button = new JButton("ȷ��");
	/**
	 * Create the panel.
	 */
	public MySetLogDatePanel() 
	{
		setLayout(new FlowLayout());
		//comboBox = new JComboBox(times);
		Box b1 = Box.createHorizontalBox();  //����box
		Box bv1 = Box.createVerticalBox();
		bv1.add(Box.createVerticalStrut(20));  
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );// ��ӿ��Ϊ100���߶�Ϊ20�Ĺ̶�����
		
		label.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������

		b1.add(label);
		b1.add(text);
		bv1.add(b1);
		bv1.add(Box.createRigidArea(new Dimension(100, 20)) );// ��ӿ��Ϊ100���߶�Ϊ20�Ĺ̶�����
		bv1.add(button);
		
		this.add(bv1);
		button.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button)
		{
			String date = text.getText().toString();
			String res = LogHandle.setLogDate(date);
			if (res.equals("yes"))
			{
				JOptionPane.showMessageDialog(null, "���ñ������ڳɹ�",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			else if (res.equals("no"))
			{
				JOptionPane.showMessageDialog(null, "���ñ�������ʧ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	}

}
