package com.scut.client.showpanel;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

import com.scut.client.funtion.FileSearchHandle;
import com.scut.client.user.MyUser;
import com.scut.tools.MyImage;

import java.awt.event.ActionListener;

public class MySearchFileInfoDialog extends JDialog implements ActionListener{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //JLabel accountL,nameL;
    private JLabel l1 = new JLabel("电站:",JLabel.CENTER);
    private JLabel l2 = new JLabel("机组:",JLabel.CENTER);
    private JLabel l3 = new JLabel("项目名称:",JLabel.CENTER);
    private JLabel l4 = new JLabel("关键字:",JLabel.CENTER);
    private JLabel l5 = new JLabel("项目号:",JLabel.CENTER);
    private JLabel l6 = new JLabel("负责人:",JLabel.CENTER);
    private JLabel l7 = new JLabel("应用场合:",JLabel.CENTER);
    private JLabel l8 = new JLabel("潜在客户:",JLabel.CENTER);
    private JLabel l9 = new JLabel("项目状态:",JLabel.CENTER);
    private JLabel l10 = new JLabel("质保:",JLabel.CENTER);
    
    private JTextField t1 = new JTextField(20);
    private JTextField t2 = new JTextField(20);
    private JTextField t3 = new JTextField(20);
    private JTextField t4 = new JTextField(20);
    private JTextField t5 = new JTextField(20);
    private JTextField t6 = new JTextField(20);
    private JTextField t7 = new JTextField(20);
    private JTextField t8 = new JTextField(20);
    private JTextField t9 = new JTextField(20);
    private JComboBox combox = new JComboBox();
    
    private JButton singleB = new JButton("单个查询");  
    private JButton combineB = new JButton("组合查询");  
    private JButton cancelB = new JButton("取消");  
    
    public static void main(String[] args)
    {
    	new MySearchFileInfoDialog();
    }
    public MySearchFileInfoDialog(){
    	super();
        init();
        setTitle("目录关键字检索");
    }
    void init(){
    	
    	Toolkit kit=Toolkit.getDefaultToolkit();

        Dimension screenSize=kit.getScreenSize();  //系统对象获取工具

        int width=screenSize.width;

        int height=screenSize.height;

        int x=(width-400)/2;

        int y=(height-400)/2;

        this.setLocation(x,y);
        this.setIconImage(MyImage.frametitle.getImage());
         
        setSize(400, 460);
        
    
    	
    	l1.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	l2.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	l3.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	l4.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	l5.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	l6.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	l7.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	l8.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	l9.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	l10.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	singleB.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	combineB.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	cancelB.setFont(new   java.awt.Font("微软雅黑",   0,   15)); 
    	
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
        combox.addItem(""); 
        combox.addItem("过保");
     	combox.addItem("在保");
     	GridBagConstraints g101 = new GridBagConstraints();  
     	g101.gridx = 0;  
     	g101.gridy = 9;  
     	g101.gridwidth=1; 
     	g101.gridheight=1;
     	g101.anchor = GridBagConstraints.CENTER;
     	g101.fill = GridBagConstraints.BOTH;
     	g101.insets = new Insets(10,0,0,0);
        container.add(l10,g101); 
        
         
        GridBagConstraints g102 = new GridBagConstraints();  
        g102.gridx = 1;  
        g102.gridy = 9;  
        g102.gridwidth = 1;
        g102.gridheight = 1;
        g102.anchor = GridBagConstraints.WEST;
        g102.fill = GridBagConstraints.BOTH;
        g102.insets = new Insets(10,0,0,0);
        container.add(combox,g102); 
          
         GridBagConstraints ctr4 = new GridBagConstraints();
         ctr4.gridx = 0;
         ctr4.gridy = 10;
        // ctr4.weighty = 1;
         //ctr4.weightx = 3;
         ctr4.gridwidth = 1;
         ctr4.gridheight = 1;
         //ctr4.anchor = GridBagConstraints.WEST;
         ctr4.fill = GridBagConstraints.HORIZONTAL;
         ctr4.insets = new Insets(15,0,0,0);
         container.add(singleB,ctr4);
          
         GridBagConstraints ctr5 = new GridBagConstraints();
         ctr5.gridx = 1;
         ctr5.gridy = 10;
         //ctr5.weighty = 1;
         //ctr5.weightx = 2;
         ctr5.gridwidth = 1;
         ctr5.gridheight = 1;
         //ctr5.anchor = GridBagConstraints.WEST;
         ctr5.fill = GridBagConstraints.HORIZONTAL;
         ctr5.insets = new Insets(15,30,0,0);
         container.add(combineB,ctr5);
          
         GridBagConstraints ctr6 = new GridBagConstraints();
         ctr6.gridx = 2;
         ctr6.gridy = 10;
         //ctr6.weighty = 1;
         //ctr6.weightx = 1;
         ctr6.gridwidth = 1;
         ctr6.gridheight = 1;
         ctr6.fill = GridBagConstraints.HORIZONTAL;
         ctr6.insets = new Insets(15,30,0,0);
         container.add(cancelB,ctr6);


        
        singleB.addActionListener(this);
        combineB.addActionListener(this);
        cancelB.addActionListener(this);

        setTitle("目录关键词检索");
        setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
    }
    
  

    @Override 
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
         if(e.getSource() == singleB)
         {
        	 String powerstation = t1.getText();
             String crew = t2.getText();
             String projectname = t3.getText();
             String keyword = t4.getText();
             String projectid = t5.getText();
             String manager = t6.getText();
             String applysituation = t7.getText();
             String potentialcustomers = t8.getText();
             String projectstate = t9.getText();
             String isprotected = (String)combox.getSelectedItem();
             if(MyUser.privilage ==1 || MyUser.privilage == 2 || MyUser.privilage == 3)
             {
            	 MyDirSearchPanel.addSingelSearchFile(powerstation, crew, projectname, keyword, projectid, manager, 
            			 applysituation, potentialcustomers, projectstate, isprotected);
            	 this.dispose();
             }
             else if(MyUser.privilage == 5)
             {
            	 MyDirSearchPanelClient.addSingelSearchFileClient(powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate, isprotected);
            	 this.dispose();
             }
             
         }
         else if(e.getSource()==combineB)
         {
        	
        	 String powerstation = t1.getText();
             String crew = t2.getText();
             String projectname = t3.getText();
             String keyword = t4.getText();
             String projectid = t5.getText();
             String manager = t6.getText();
             String applysituation = t7.getText();
             String potentialcustomers = t8.getText();
             String projectstate = t9.getText(); 
             String isprotected = (String)combox.getSelectedItem();
             if(MyUser.privilage == 1 || MyUser.privilage == 2 || MyUser.privilage==3)
             {
            	 MyDirSearchPanel.addUnionSearchFile(powerstation, crew, projectname, keyword, projectid, manager, 
            			 applysituation, potentialcustomers, projectstate,isprotected);
            	 this.dispose();
             }
             else if(MyUser.privilage == 5)
             {
            	 MyDirSearchPanelClient.addUnionSearchFile(powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate, isprotected);
            	 this.dispose();
             }
         }
         else if(e.getSource() == cancelB)
         {
                //new JFrame().dispose();
                dispose();
         }
    }

}

