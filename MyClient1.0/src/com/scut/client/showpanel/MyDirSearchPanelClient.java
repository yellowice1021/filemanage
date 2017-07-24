package com.scut.client.showpanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import com.scut.client.funtion.FileSearchHandle;
import com.scut.client.funtion.UpDownFile;
import com.scut.client.ui.MyMainFrame;
import com.scut.client.user.MyUser;
import com.scut.tools.HistoryIo;
import com.scut.tools.LocalDirIo;
import com.scut.tools.MyParameters;

public class MyDirSearchPanelClient extends JPanel implements ActionListener {

	public static String[] Header = {" ","文件夹的路径"};
	public static Object[][] Data = null;
	public static DefaultTableModel  tableModel;
	public static JTable table;
	
	public boolean checkboxs;
	public JButton all = new JButton("全选/全不选");
	public JButton download = new JButton("下载");
	Box box = Box.createHorizontalBox();  //横向box
	
	private static Logger log = Logger.getLogger("client");
	
	public DefaultTableModel historyTableModel;
	public DefaultTableModel jobTableModel;
	public JProgressBar progressbar;
	Lock lock = new ReentrantLock(); 
	/**
	 * Create the panel.
	 */
	public MyDirSearchPanelClient(DefaultTableModel historyTableModel, DefaultTableModel jobTableModel, JProgressBar progressbar) 
	{
		this.historyTableModel = historyTableModel;
		this.jobTableModel = jobTableModel;
		this.progressbar = progressbar;
		setLayout(new BorderLayout());
		tableModel = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row,int column) 
			{
				if (column > 0)	return false;
				else return true;
             }
	    };
	    tableModel.setDataVector(Data, Header);
		table = new JTable(tableModel);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setOpaque(false);  
		jsp.getViewport().setOpaque(false); 
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();    
		renderer.setOpaque(false);//render单元格的属性  
		//遍历表格中所有列，将其渲染器设置为renderer  
		for(int i = 1 ; i < table.getColumnCount(); i ++)  
		{  
		    table.getColumnModel().getColumn(i).setCellRenderer(renderer);  
		}  
		table.setOpaque(false);
		this.add(jsp, BorderLayout.CENTER);   //得在scrollPane里面才会显示表头
		
		table.setRowHeight(20);//设置列高
		table.setFont(new Font("微软雅黑",Font.PLAIN,12));//设置字体
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("微软雅黑",Font.PLAIN,14));//设置字体
		
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(700);

		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));   
		table.getColumnModel().getColumn(0).setCellRenderer(new MyTableRenderer());
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
		box.add(all);
		box.add(download);
		checkboxs = false;
		all.addActionListener(this);
		download.addActionListener(this);
//		table.addMouseListener(new MouseAdapter(){
//			  public void mouseClicked(MouseEvent e) { 
//				  int clicktimes = e.getClickCount();
//				  if (clicktimes == 1)
//				  {
//					  int row = table.getSelectedRow();
//					  String path = (String) table.getValueAt(row, 2);
//					  System.out.println(row+"," +path);
//					  MyMainFrame.findInTree(MyMainFrame.serverTree, path);
//				  }
//				  else if(clicktimes ==2)
//				  {
//					  
//				  }
//			  }
//		});
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				int row = table.getSelectedRow();
				String path = (String) table.getValueAt(row, 1);
				System.out.println(row+"," +path);
				MyMainFrame.findInTree(MyMainFrame.clientFileTree, path);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
	
	
	// 单个查询
	public static void addSingelSearchFileClient(String powerstation,
	         String crew,
	         String projectname,
	         String keyword,
	         String projectid,
	         String manager,
	         String applysituation,
	         String potentialcustomers,
	         String projectstate,
	         String isprotected)
	{
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		String dirNames = LocalDirIo.singleSearchClient(powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate, isprotected);
		log.debug(dirNames);
		if (dirNames.equals("")) return;
		String datas[] = dirNames.split(",");
		Object d[] = new Object[2];
		for (String e : datas)
		{
			d[0] = false;
			d[1] = e;
			tableModel.addRow(d);
		}
		
	}
	
	// 组合查询
	public static void addUnionSearchFile(String powerstation,
	         String crew,
	         String projectname,
	         String keyword,
	         String projectid,
	         String manager,
	         String applysituation,
	         String potentialcustomers,
	         String projectstate,
	         String isprotected)
	{
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		String dirNames = LocalDirIo.unioSearchClient(powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate, isprotected);
		log.debug(dirNames);
		if (dirNames.equals("")) return;
		String datas[] = dirNames.split(",");
		Object d[] = new Object[2];
		for (String e : datas)
		{
			d[0] = false;
			d[1] = e;
			tableModel.addRow(d);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == all)
		{
			log.debug("choose all");
			for (int i=0; i<table.getRowCount(); i++)
	        {
	            tableModel.setValueAt(checkboxs, i, 0);
	        }
	        checkboxs = !checkboxs;
		}
		else if (e.getSource() == download)
		{
//			String serverPath = "";
//			String clientPath = "";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i=0; i<table.getRowCount(); i++)
	        {
	            if((boolean)tableModel.getValueAt(i, 0) == true)
	            {
	            	
	            	String serverPath = (String) tableModel.getValueAt(i, 2);
	            	String clientPath = MyParameters.getSavePath() + "\\" + serverPath;
	                
	            	new Thread("downLoad")
	    			{
	    				public void run()
	    				{				 
	    					Object[] rowData = new Object[]
	    			        {
	    			               		 df.format(new Date()), clientPath, "下载", clientPath, 0
	    			        };
	    					jobTableModel.addRow(rowData);
	    		            int row = jobTableModel.getRowCount();
	    		            log.debug(jobTableModel.getRowCount());
	    		            UpDownFile.setTableThreadPara(jobTableModel, row-1, 4);
	    		            UpDownFile.getMyProgressBar(progressbar);
	    		            UpDownFile.downloadFile(serverPath,  clientPath);
	    		            jobTableModel.setValueAt("100%", row-1, 4);
	    		            
	    		            Object[] historyData = new Object[] 
	    		            {
	    		        		           //df.format(new Date()), serverPath, "下载", clientPath, "100%"
	    		            		df.format(new Date()), MyUser.userName,serverPath, "下载", clientPath, "100%"
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
	    				
	    			}.start();
	            }
	            
	        }
		}
	}
	
}
