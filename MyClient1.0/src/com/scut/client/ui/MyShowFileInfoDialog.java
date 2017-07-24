package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.scut.client.funtion.MyFileHandle;
import com.scut.tools.MyImage;

public class MyShowFileInfoDialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JLabel l1 = new JLabel("电站:",JLabel.CENTER);
    private JLabel l2 = new JLabel("机组:",JLabel.CENTER);
    private JLabel l3 = new JLabel("项目名称:",JLabel.CENTER);
    private JLabel l4 = new JLabel("关键字:",JLabel.CENTER);
    private JLabel l5 = new JLabel("项目立项号:",JLabel.CENTER);
    private JLabel l6 = new JLabel("负责人:",JLabel.CENTER);
    private JLabel l7 = new JLabel("应用场合:",JLabel.CENTER);
    private JLabel l8 = new JLabel("潜在客户:",JLabel.CENTER);
    private JLabel l9 = new JLabel("项目状态:",JLabel.CENTER);
    private JTextField t1 = new JTextField(20);
    private JTextField t2 = new JTextField(20);
    private JTextField t3 = new JTextField(20);
    private JTextField t4 = new JTextField(20);
    private JTextField t5 = new JTextField(20);
    private JTextField t6 = new JTextField(20);
    private JTextField t7 = new JTextField(20);
    private JTextField t8 = new JTextField(20);
    private JTextField t9 = new JTextField(20);
	
	
	private String serverPath;
	
	private static Logger log = Logger.getLogger("client");


	/**
	 * Create the dialog.
	 */
	public MyShowFileInfoDialog(String s) {
	
		setTitle("目录关键字信息");
		setIconImage(MyImage.frametitle.getImage());
		Toolkit kit=Toolkit.getDefaultToolkit();

        Dimension screenSize=kit.getScreenSize();  //系统对象获取工具

        int width=screenSize.width;

        int height=screenSize.height;

        int x=(width-400)/2;

        int y=(height-400)/2;

        this.setLocation(x,y);
         
        setSize(400, 400);
        
    
    	
    	l1.setFont(new   java.awt.Font("黑体",   0,   15)); 
    	l2.setFont(new   java.awt.Font("黑体",   0,   15)); 
    	l3.setFont(new   java.awt.Font("黑体",   0,   15)); 
    	l4.setFont(new   java.awt.Font("黑体",   0,   15)); 
    	l5.setFont(new   java.awt.Font("黑体",   0,   15)); 
    	l6.setFont(new   java.awt.Font("黑体",   0,   15)); 
    	l7.setFont(new   java.awt.Font("黑体",   0,   15)); 
    	l8.setFont(new   java.awt.Font("黑体",   0,   15)); 
    	l9.setFont(new   java.awt.Font("黑体",   0,   15)); 

    	
    	 Container container = this.getContentPane();
         container.setLayout(new GridBagLayout());
          
         GridBagConstraints g11 = new GridBagConstraints();  
         g11.gridx = 0;  
         g11.gridy = 0;  
         g11.gridwidth=1; 
         g11.gridheight=1;
         g11.fill = GridBagConstraints.BOTH;
         container.add(l1,g11); 
         
         
         GridBagConstraints g12 = new GridBagConstraints();  
         g12.gridx = 1;  
         g12.gridy = 0;  
         g12.gridwidth = 2;
         g12.gridheight = 1;
         g12.anchor = GridBagConstraints.CENTER;
         g12.fill = GridBagConstraints.BOTH;
         container.add(t1,g12); 
         
         GridBagConstraints g21 = new GridBagConstraints();  
         g21.gridx = 0;  
         g21.gridy = 1;  
         g21.gridwidth=1; 
         g21.gridheight=1;
         g21.anchor = GridBagConstraints.CENTER;
         g21.fill = GridBagConstraints.BOTH;
         g21.insets = new Insets(10,0,0,0);
         container.add(l2,g21); 
         
         GridBagConstraints g22 = new GridBagConstraints();  
         g22.gridx = 1;  
         g22.gridy = 1;  
         g22.gridwidth = 2;
         g22.gridheight = 1;
         g22.anchor = GridBagConstraints.WEST;
         g22.fill = GridBagConstraints.BOTH;
         g22.insets = new Insets(10,0,0,0);
         container.add(t2,g22); 
         
         GridBagConstraints g31 = new GridBagConstraints();  
         g31.gridx = 0;  
         g31.gridy = 2;  
         g31.gridwidth=1; 
         g31.gridheight=1;
         g31.anchor = GridBagConstraints.CENTER;
         g31.fill = GridBagConstraints.BOTH;
         g31.insets = new Insets(10,0,0,0);
         container.add(l3,g31); 
         
         GridBagConstraints g32 = new GridBagConstraints();  
         g32.gridx = 1;  
         g32.gridy = 2;  
         g32.gridwidth = 2;
         g32.gridheight = 1;
         g32.anchor = GridBagConstraints.WEST;
         g32.fill = GridBagConstraints.BOTH;
         g32.insets = new Insets(10,0,0,0);
         container.add(t3,g32); 
         
         GridBagConstraints g41 = new GridBagConstraints();  
         g41.gridx = 0;  
         g41.gridy = 3;  
         g41.gridwidth=1; 
         g41.gridheight=1;
         g41.anchor = GridBagConstraints.CENTER;
         g41.fill = GridBagConstraints.BOTH;
         g41.insets = new Insets(10,0,0,0);
         container.add(l4,g41); 
         
         GridBagConstraints g42 = new GridBagConstraints();  
         g42.gridx = 1;  
         g42.gridy = 3;  
         g42.gridwidth = 2;
         g42.gridheight = 1;
         g42.anchor = GridBagConstraints.WEST;
         g42.fill = GridBagConstraints.BOTH;
         g42.insets = new Insets(10,0,0,0);
         container.add(t4,g42); 
         
         GridBagConstraints g51 = new GridBagConstraints();  
         g51.gridx = 0;  
         g51.gridy = 4;  
         g51.gridwidth=1; 
         g51.gridheight=1;
         g51.anchor = GridBagConstraints.CENTER;
         g51.fill = GridBagConstraints.BOTH;
         g51.insets = new Insets(10,0,0,0);
         container.add(l5,g51); 
         
         GridBagConstraints g52 = new GridBagConstraints();  
         g52.gridx = 1;  
         g52.gridy = 4;  
         g52.gridwidth = 2;
         g52.gridheight = 1;
         g52.anchor = GridBagConstraints.WEST;
         g52.fill = GridBagConstraints.BOTH;
         g52.insets = new Insets(10,0,0,0);
         container.add(t5,g52); 
         
         GridBagConstraints g61 = new GridBagConstraints();  
         g61.gridx = 0;  
         g61.gridy = 5;  
         g61.gridwidth=1; 
         g61.gridheight=1;
         g61.anchor = GridBagConstraints.CENTER;
         g61.fill = GridBagConstraints.BOTH;
         g61.insets = new Insets(10,0,0,0);
         container.add(l6,g61); 
         
         GridBagConstraints g62 = new GridBagConstraints();  
         g62.gridx = 1;  
         g62.gridy = 5;  
         g62.gridwidth = 2;
         g62.gridheight = 1;
         g62.anchor = GridBagConstraints.WEST;
         g62.fill = GridBagConstraints.BOTH;
         g62.insets = new Insets(10,0,0,0);
         container.add(t6,g62); 
         
         GridBagConstraints g71 = new GridBagConstraints();  
         g71.gridx = 0;  
         g71.gridy = 6;  
         g71.gridwidth=1; 
         g71.gridheight=1;
         g71.anchor = GridBagConstraints.CENTER;
         g71.fill = GridBagConstraints.BOTH;
         g71.insets = new Insets(10,0,0,0);
         container.add(l7,g71); 
         
         GridBagConstraints g72 = new GridBagConstraints();  
         g72.gridx = 1;  
         g72.gridy = 6;  
         g72.gridwidth = 2;
         g72.gridheight = 1;
         g72.anchor = GridBagConstraints.WEST;
         g72.fill = GridBagConstraints.BOTH;
         g72.insets = new Insets(10,0,0,0);
         container.add(t7,g72); 
         
         GridBagConstraints g81 = new GridBagConstraints();  
         g81.gridx = 0;  
         g81.gridy = 7;  
         g81.gridwidth=1; 
         g81.gridheight=1;
         g81.anchor = GridBagConstraints.CENTER;
         g81.fill = GridBagConstraints.BOTH;
         g81.insets = new Insets(10,0,0,0);
         container.add(l8,g81); 
         
         GridBagConstraints g82 = new GridBagConstraints();  
         g82.gridx = 1;  
         g82.gridy = 7;  
         g82.gridwidth = 2;
         g82.gridheight = 1;
         g82.anchor = GridBagConstraints.WEST;
         g82.fill = GridBagConstraints.BOTH;
         g82.insets = new Insets(10,0,0,0);
         container.add(t8,g82); 
         
         GridBagConstraints g91 = new GridBagConstraints();  
         g91.gridx = 0;  
         g91.gridy = 8;  
         g91.gridwidth=1; 
         g91.gridheight=1;
         g91.anchor = GridBagConstraints.CENTER;
         g91.fill = GridBagConstraints.BOTH;
         g91.insets = new Insets(10,0,0,0);
         container.add(l9,g91); 
         
          
         GridBagConstraints g92 = new GridBagConstraints();  
         g92.gridx = 1;  
         g92.gridy = 8;  
         g92.gridwidth = 2;
         g92.gridheight = 1;
         g92.anchor = GridBagConstraints.WEST;
         g92.fill = GridBagConstraints.BOTH;
         g92.insets = new Insets(10,0,0,0);
         container.add(t9,g92); 
		
		
		String info[] = {"","","","","","","","",""};
		String tmp[] = s.split(",");
		for(int i = 0; i < tmp.length; i++)
		{
			info[i] = tmp[i];
			if(info[i].equals("null")||info[i].equals(null)) info[i] = "";
		}
		t1.setText(info[0]);
		t2.setText(info[1]);
		t3.setText(info[2]);
		t4.setText(info[3]);
		t5.setText(info[4]);
		t6.setText(info[5]);
		t7.setText(info[6]);
		t8.setText(info[7]);
		t9.setText(info[8]);
		t1.setEditable(false);
		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);
		t5.setEditable(false);
		t6.setEditable(false);
		t7.setEditable(false);
		t8.setEditable(false);
		t9.setEditable(false);
		
		
		setModal(true);
	
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		
	}
	
}
