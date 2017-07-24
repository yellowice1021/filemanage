package com.scut.client.showpanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
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
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import com.scut.client.funtion.FileSearchHandle;
import com.scut.client.funtion.MyScan;
import com.scut.client.funtion.UpDownFile;
import com.scut.client.ui.MyMainFrame;
import com.scut.client.user.MyPath;
import com.scut.client.user.MyUser;
import com.scut.tools.HistoryIo;
import com.scut.tools.LocalFileIo;
import com.scut.tools.MyImage;
import com.scut.tools.MyParameters;

public class MyFileSearchPanel extends JPanel implements ActionListener 
{
	public static String[] Header = {" ","�ļ���","�ļ���С","�ļ�����","�ļ�·��"};
	public static Object[][] Data = null;
	public static DefaultTableModel  tableModel;
	public static JTable table;
	
	public boolean checkboxs;
	public JButton all = new JButton("ȫѡ/ȫ��ѡ");
	public JButton download = new JButton("����");
	Box box = Box.createHorizontalBox();  //����box
	
	private static Logger log = Logger.getLogger("client");
	
	public DefaultTableModel historyTableModel;
	public DefaultTableModel jobTableModel;
	public JProgressBar progressbar;
	Lock lock = new ReentrantLock(); 
	/**
	 * Create the panel.
	 */
	public MyFileSearchPanel(DefaultTableModel historyTableModel, DefaultTableModel jobTableModel, JProgressBar progressbar) 
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
		renderer.setOpaque(false);//render��Ԫ�������  
		//��������������У�������Ⱦ������Ϊrenderer  
		for(int i = 1 ; i < table.getColumnCount(); i ++)  
		{  
		    table.getColumnModel().getColumn(i).setCellRenderer(renderer);  
		}  
		table.setOpaque(false);
		this.add(jsp, BorderLayout.CENTER);   //����scrollPane����Ż���ʾ��ͷ
		
		//this.add(new JScrollPane(table), BorderLayout.CENTER);   //����scrollPane����Ż���ʾ��ͷ
		this.add(box, BorderLayout.SOUTH);
		
		table.setRowHeight(20);//�����и�
		table.setFont(new Font("΢���ź�",Font.PLAIN,12));//��������
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(330);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(530);
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));   
		table.getColumnModel().getColumn(0).setCellRenderer(new MyTableRenderer());
		
		box.add(all);
		box.add(download);
		checkboxs = false;
		all.addActionListener(this);
		download.addActionListener(this);
		
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);  
        table.setRowSorter(sorter); 
		table.addMouseListener(new MouseAdapter(){
			  public void mouseClicked(MouseEvent e) { 
				  int clicktimes = e.getClickCount();
				  if (clicktimes == 1)
				  {
					  int row = table.getSelectedRow();
					  String path = (String) table.getValueAt(row, 4);
					  System.out.println(row+"," +path);
					  MyMainFrame.findInTree(MyMainFrame.serverTree, path);
				  }
				  else if(clicktimes ==2)
				  {
					  int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //�����λ�� 
					  
		              //int col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); 
					  String fileName = (String) table.getValueAt(row, 4);
					  MyMainFrame.findInTree(MyMainFrame.serverTree, fileName);
					  log.debug(fileName);
						
					   String clientPath = MyPath.scanPath + "\\" + fileName;
						new Thread("onlinescan")
						{
							public void run()
							{
								UpDownFile.getMyProgressBar(new JProgressBar());
								UpDownFile.onlineScanFile(fileName, clientPath);
								MyScan.scan(clientPath);
							}
						}.start();
				  }
			  }
		});

		
	}
	
	public static void addFile(String aimPath, String filename)
	{
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		String filenames = FileSearchHandle.searchFilename(aimPath,filename);
		//System.out.println("�������˲�ѯ�������ļ��� " +filenames);
		log.debug(filenames);
		if (filenames.equals("")) return;
		String datas[] = filenames.split(",");
		String data[];
		Object d[] = new Object[5];
		for (String e : datas)
		{
			data = e.split(":");
			d[0] = false;
			String fileName = data[0].substring(data[0].lastIndexOf("\\") + 1); // ����ϴ��ļ����ļ���  
			String sp[] = fileName.split("\\.");
			d[1] = fileName;
			d[2] = d[2] = data[1]+"KB";
			if (sp.length == 1) d[3] = "δ֪����"; 
			else d[3] = sp[1];
			d[4] = data[0];
			tableModel.addRow(d);
		}
		
	}
	
	public static void addSingelSearchFile(String powerstation,
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
		String filenames = FileSearchHandle.singleSearch(powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate,isprotected);
		log.debug(filenames);
		if (filenames.equals("")) return;
		String datas[] = filenames.split("\t");
		String data[];
		Object d[] = new Object[3];
		for (String e : datas)
		{
			data = e.split(",");
			d[0] = false;
			d[1] = data[0];
			d[2] = data[1];
			tableModel.addRow(d);
		}
		
	}
	
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
		String filenames = FileSearchHandle.unionSearch(powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate, isprotected);
		log.debug(filenames);
		if (filenames.equals("")) return;
		String datas[] = filenames.split("\t");
		String data[];
		Object d[] = new Object[3];
		for (String e : datas)
		{
			data = e.split(",");
			d[0] = false;
			d[1] = data[0];
			d[2] = data[1];
			tableModel.addRow(d);
		}
		
	}
	
//	public void paintComponent(Graphics g) 
//	{
//		   int x = 0;// this.getX()
//		   int y = 0;//this.getY();
//		   //if (icon != null) 
//		   g.drawImage(MyImage.showfile.getImage(), x, y, getSize().width, getSize().height, this);// ͼƬ���Զ�����
//	}

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
	            	
	            	String serverPath = (String) tableModel.getValueAt(i, 4);
	            	String clientPath = MyParameters.getSavePath() + "\\" + serverPath;
	                
	            	new Thread("downLoad")
	    			{
	    				public void run()
	    				{				 
	    					Object[] rowData = new Object[]
	    			        {
	    			               		 df.format(new Date()), clientPath, "����", clientPath, 0
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
	    		        		           //df.format(new Date()), serverPath, "����", clientPath, "100%"
	    		            		df.format(new Date()), MyUser.userName,serverPath, "����", clientPath, "100%"
	    		        	};  
	    		            historyTableModel.addRow(historyData);
	    		             
	    		            // ��ȡ��  
	    		            lock.lock();  
	    		            try {  
	    		            	 boolean flag = HistoryIo.writeLine(historyData); 
	    		             } finally {  
	    		                 // �ͷ���  
	    		                 lock.unlock();  
	    		             } 
	    		            
	    				}   
	    				
	    			}.start();
	            }
	            
	        }
		}
	}


	


}
