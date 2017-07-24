package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;

import com.scut.client.funtion.FileList;
import com.scut.client.funtion.UpDownFile;
import com.scut.client.showpanel.MyShowFilePanel;
import com.scut.client.user.MyUser;
import com.scut.client.user.WorkingJob;
import com.scut.tools.HistoryIo;
import com.scut.tools.MyImage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyUpLoadDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	
	/*
	 * modifeid by zhr
	 * */
	private JFileChooser fc;
	private File[] path;
	
	
	private JTextField textField;
	JButton btnNewButton = new JButton("选择文件");
	JButton btnNewButton_1 = new JButton("确定");
	JButton btnNewButton_2 = new JButton("取消");

	private static Logger log = Logger.getLogger("client");
	
	private String serverPath;
	private JTextField textField_1;
	
	public DefaultTableModel historyTableModel;
	public DefaultTableModel jobTableModel;

	public DefaultMutableTreeNode node;
	Lock lock = new ReentrantLock(); 



	/**
	 * Create the dialog.
	 */
	public MyUpLoadDialog(String serverpath,  DefaultTableModel historyTableModel, DefaultTableModel jobTableModel) 
	{
		
		this.historyTableModel = historyTableModel;
		this.jobTableModel = jobTableModel;
		serverPath = serverpath;
		setBounds(100, 100, 537, 208);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("本地文件");
		label.setBounds(28, 66, 126, 15);
		getContentPane().add(label);
		
		/*
		 * modifeid by zhr
		 * */
		fc = new JFileChooser();
		path = new File[]{};
		
		textField = new JTextField();
		textField.setBounds(142, 63, 223, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		btnNewButton.setBounds(374, 62, 119, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton_1.setBounds(28, 113, 162, 23);
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		btnNewButton_2.setBounds(356, 113, 137, 23);
		getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("上传到：");
		lblNewLabel.setBounds(28, 27, 119, 15);
		getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(142, 24, 351, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(serverPath);
		btnNewButton_2.addActionListener(this);
		
		setModal(true);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}

	
	
	/**
	 * Create the dialog.
	 */
	public MyUpLoadDialog(DefaultMutableTreeNode node,String serverpath,  DefaultTableModel historyTableModel, DefaultTableModel jobTableModel) 
	{
		
		Toolkit kit=Toolkit.getDefaultToolkit();

        Dimension screenSize=kit.getScreenSize();  //系统对象获取工具

        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        int width = 420;
        int height = 208;
		
		this.node = node;
		this.historyTableModel = historyTableModel;
		this.jobTableModel = jobTableModel;
		serverPath = serverpath;
		setBounds(screenWidth/2-width/2, screenHeight/2-height/2, width, height);
		setIconImage(MyImage.frametitle.getImage());
		setTitle("上传文件");
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("本地文件");
		label.setBounds(37, 66, 120, 15);
		getContentPane().add(label);
		
		/*
		 * modifeid by zhr
		 * */
		fc = new JFileChooser();
		path = new File[]{};
		
		textField = new JTextField();
		textField.setBounds(96, 63, 189, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		btnNewButton.setBounds(296, 62, 70, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton_1.setBounds(90, 113, 90, 23);
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		btnNewButton_2.setBounds(248, 113, 90, 23);
		getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("上传到");
		lblNewLabel.setBounds(37, 27, 110, 15);
		getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(96, 24, 270, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(serverPath);
		btnNewButton_2.addActionListener(this);
		
		setModal(true);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnNewButton)
		{
			//JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("请选择要上传到的本地文件");
			//设置只能选择文件
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setMultiSelectionEnabled(true);
			fc.showOpenDialog(this);
	       // File path = fc.getSelectedFile();
			path = fc.getSelectedFiles();
		    if (path == null || path.length== 0)
	        {
	        	return;
	        }	        
        	StringBuffer buf = new StringBuffer();
	        for(File file : path){
	        	buf = buf.append(file.getAbsolutePath());
	        }
	        log.info("您选择了" + path.length+"个文件");
	        textField.setText(buf.toString());
		}
		else if (e.getSource() == btnNewButton_1)
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(File file : path){
				
				textField.setText(file.getAbsolutePath()); 
			    String clientPath = textField.getText().trim();  	  
			String serverPath = textField_1.getText().trim();
			if (clientPath.equals("")) 
			{
				JOptionPane.showMessageDialog(null, "您尚未选择本地文件",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			new Thread(){
				public void run() 
				{
					try 
					{     
						log.debug("上传");
						String res = UpDownFile.isFileExist(serverPath +"\\"+ clientPath.substring(clientPath.lastIndexOf("\\") + 1));
						log.debug(res + ":" +serverPath);
						if (res.equals("yes"))
						{
							int answer = JOptionPane.showConfirmDialog(null, "服务器存在同名文件，继续上传将为文件名添加后缀作为版本号",null, JOptionPane.OK_CANCEL_OPTION);
							if (answer == JOptionPane.YES_OPTION)
							{
								WorkingJob.addjob();
								UpDownFile.mulUploadFile(clientPath, serverPath);
								WorkingJob.deljob();
							}
							else return;
						}
						else
						{
							WorkingJob.addjob();
							UpDownFile.uploadFile(clientPath, serverPath);
							String fileName = clientPath.substring(clientPath.lastIndexOf("\\") + 1);
							WorkingJob.deljob();
						}
						//////////////////
						String filenames = FileList.listSonfile(MyMainFrame.getTreePath(node.getPath()));
						log.debug(filenames);
						if (filenames.equals("")) return;
						String datas[] = filenames.split(",");
	        		    EventQueue.invokeLater(new Runnable() 
	        		    {
	        				public void run() 
	        				{
	        					node.removeAllChildren();
	        					try 
	        					{  
	        						for (int i = 0; i < datas.length; i++)
	        						{
	        							String filepath = datas[i].split(":")[0];
	        							String filename = filepath.substring(filepath.lastIndexOf("\\")+1);
	        							log.debug(filepath + ":" + filename);
	        							node.add(new DefaultMutableTreeNode(filename));
	        						}
	        						MyMainFrame.serverTree.updateUI();
	        					} 
	        					catch (Exception e) 
	        					{
	        						e.printStackTrace();
	        					}
	        				}
	        			 });	
						//////////////////////////////////
						Object[] rowData = new Object[]
		    			{
		    			        df.format(new Date()), clientPath, "上传", clientPath, ""
		    			};
		    			jobTableModel.addRow(rowData);

		    		    Object[] historyData = new Object[] 
    		            {
    		            	  //df.format(new Date()),serverPath, "上传", clientPath, "100%"
    		        		    df.format(new Date()), MyUser.userName,serverPath, "上传", clientPath, "100%"
    		        	};  
    		            historyTableModel.addRow(historyData);
    		             
    		            // 获取锁  
    		            lock.lock();  
    		            try {  
    		            	 boolean flag = HistoryIo.writeLine(historyData); 
    		             } finally {  
    		                 // 释放锁  
    		                 lock.unlock();  
    		             } 
					} 
					catch (Exception e2) 
					{
						e2.printStackTrace();
					}
				}
			 }.start();	
			
			this.dispose();
		}
	}
		else if (e.getSource() == btnNewButton_2)
		{
			this.dispose();
		}
		
		
	}
}
