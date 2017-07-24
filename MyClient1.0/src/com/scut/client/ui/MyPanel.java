package com.scut.client.ui;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.scut.tools.MyImage;

/*
 * 
 */
public class MyPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon icon;
	public MyPanel()
	{
		super();
		icon = MyImage.white;
	}
	public MyPanel(ImageIcon icon)
	{
		super();
		this.icon = icon;
	}
	public void paintComponent(Graphics g) 
	{
		   int x = 0;// this.getX()
		   int y = 0;//this.getY();
		   //if (icon != null) 
		   g.drawImage(icon.getImage(), x, y, getSize().width, getSize().height, this);// 图片会自动缩放
	}
}
