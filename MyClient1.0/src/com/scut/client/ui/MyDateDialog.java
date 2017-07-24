package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.scut.tools.MyImage;

public class MyDateDialog extends JDialog implements ActionListener,ItemListener{
	JButton buttons[] = new JButton[32];
	JLabel label1 = new JLabel("年份");
	JLabel label2 = new JLabel("月份");
	JLabel label3 = new JLabel("日期");
	
	JButton button = new JButton("确定");
	JComboBox cbox1 = new JComboBox();
	JComboBox cbox2 = new JComboBox();
	JTextField day = new JTextField(4);
	public static String date;
	Box box;
	JPanel panel = new JPanel();
	
	
	JTextField field = new JTextField();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			MyDateDialog dialog = new MyDateDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public MyDateDialog(JTextField text) 
	{
		
		Toolkit kit=Toolkit.getDefaultToolkit();

        Dimension screenSize=kit.getScreenSize();  //系统对象获取工具

        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        
        int width = 385;
        int height = 300;
		
		field = text;
		setBounds(screenWidth/2-width/2, screenHeight/2-height/2, width, height);
		setIconImage(MyImage.frametitle.getImage());
		getContentPane().setLayout(new BorderLayout());
		panel.setLayout(new FlowLayout());
		for (int i = 1; i <= 31; i++)
		{
			buttons[i] = new JButton();
			if (i <10)
			{
				buttons[i].setText("0"+String.valueOf(i));
			}
			else buttons[i].setText(String.valueOf(i));
			buttons[i].addActionListener(this);
			panel.add(buttons[i]);
		}
		
		getContentPane().add(panel, BorderLayout.CENTER);
		for (int i = 2010; i < 2040; i++)
		{
			cbox1.addItem(i);
		}
		
		for (int i = 1; i < 10; i++)
		{
			cbox2.addItem("0"+i);
		}
		for (int i = 10; i <= 12; i++)
		{
			cbox2.addItem(i);
		}
		box = Box.createHorizontalBox();
		box.add(label1);
		box.add(cbox1);
		box.add(label2);
		box.add(cbox2);
		box.add(label3);
		box.add(day);
		box.add(button);
		getContentPane().add(box, BorderLayout.NORTH);
		
		button.addActionListener(this);
		cbox1.addItemListener(this);
		cbox2.addItemListener(this);
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static String getDate()
	{
		return date;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 31; i++)
		{
			if (e.getSource() == buttons[i])
			{
				day.setText(buttons[i].getText());
			}
		}
		if (e.getSource() == button)
		{
			if (day.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "您还未选择日期",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			field.setText(cbox1.getSelectedItem().toString()+"-"+cbox2.getSelectedItem().toString()
					+"-"+day.getText());
			date = cbox1.getSelectedItem().toString()+"-"+cbox2.getSelectedItem().toString()
					+"-"+day.getText();
			//System.out.println(date);
			this.dispose();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
        System.out.println("haha");
		  if(e.getStateChange() == ItemEvent.SELECTED)
          {
			  System.out.println(cbox2.getSelectedItem());
			  String date = cbox2.getSelectedItem().toString();
				if(date.equals("01")
						||date.equals("03")
						||date.equals("05")
						||date.equals("07")
						||date.equals("08")
						||date.equals("10")
						||date.equals("12")	)
				{
					
					buttons[29].setVisible(true);
					buttons[30].setVisible(true);
					buttons[31].setVisible(true);
				}
				else if (date.equals("02"))
				{
					//buttons[30].setEnabled(false);
					buttons[30].setVisible(false);
					//buttons[31].setEnabled(false);
					buttons[31].setVisible(false);
					int year = (int) cbox1.getSelectedItem();
					if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
						buttons[29].setVisible(true);
					}
					else
					{
						buttons[29].setVisible(false);
					}
				}
				else if(date.equals("04")
						||date.equals("06")
						||date.equals("09")
						||date.equals("11"))	
				{
					buttons[29].setVisible(true);
					buttons[30].setVisible(true);
					buttons[31].setVisible(false);
				}
				
		  }
	}

	public void resetDate() {
		// TODO Auto-generated method stub
		date = "null";
	}

}
