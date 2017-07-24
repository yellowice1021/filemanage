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

public class MyFileSearchPanelClient extends JPanel implements ActionListener 
{
	public static String[] Header = {" ","文件名","文件大小","文件类型","文件路径"};
	public static Object[][] Data = null;
	public static DefaultTableModel  tableModel;
	public static JTable table;
	
//	public boolean checkboxs;
//	public JButton all = new JButton("全选/全不选");
//	public JButton download = new JButton("下载");
//	Box box = Box.createHorizontalBox();  //横向box
	
	private static Logger log = Logger.getLogger("client");
	
	public DefaultTableModel historyTableModel;
	public DefaultTableModel jobTableModel;
	public JProgressBar progressbar;
	Lock lock = new ReentrantLock(); 
	/**
	 * Create the panel.
	 */
	public MyFileSearchPanelClient(DefaultTableModel historyTableModel, DefaultTableModel jobTableModel, JProgressBar progressbar) 
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
		
		//this.add(new JScrollPane(table), BorderLayout.CENTER);   //得在scrollPane里面才会显示表头
		//this.add(box, BorderLayout.SOUTH);
		
		table.setRowHeight(20);//设置列高
		table.setFont(new Font("微软雅黑",Font.PLAIN,12));//设置字体
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("微软雅黑",Font.PLAIN,14));//设置字体
		
		
		//table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);

		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(330);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(530);
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));   
		table.getColumnModel().getColumn(0).setCellRenderer(new MyTableRenderer());
		
//		box.add(all);
//		box.add(download);
//		checkboxs = false;
//		all.addActionListener(this);
//		download.addActionListener(this);
		
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
					  int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置 
					  
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

	
	/*
	 * 单独写一个普通用户检索的panle
	 * */
	public static void addFileClient(String aimPath, String filename)
	{ 
		// TODO Auto-generated method stub
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		//String filenames = FileSearchHandle.searchFilenameClient(aimPath,filename);
		String filenames = FileSearchHandle.findFileNameClient(aimPath, filename);
		log.debug(filenames);
		if (filenames.equals("")) return;
		String datas[] = filenames.split(",");
//		String[] datas2 = new String[datas.length];
//		for(int i =0;i<datas.length;i++)
//		{
//			//datas2[i]= datas[i].substring(14);
//			datas2[i]= datas[i];
//			//System.out.println("单个文件文件名 " +datas2[i]);
//		}
		String data[];
		Object d[] = new Object[5];
		for (String e : datas)
		{
			data = e.split(":");
			d[0] = false;
			String fileName = data[0].substring(data[0].lastIndexOf("\\") + 1); // 获得上传文件的文件名  
			String sp[] = fileName.split("\\.");
			d[1] = fileName;
			d[2] = d[2] = data[1]+"KB";
			if (sp.length == 1) d[3] = "未知类型"; 
			else d[3] = sp[1];
			d[4] = data[0];
			tableModel.addRow(d);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}



}
