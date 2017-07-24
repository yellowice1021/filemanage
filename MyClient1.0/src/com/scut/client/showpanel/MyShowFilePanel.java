package com.scut.client.showpanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import com.scut.client.funtion.FileList;
import com.scut.client.funtion.MyScan;
import com.scut.client.funtion.UpDownFile;
import com.scut.client.ui.MyMainFrame;
import com.scut.client.ui.MyPanel;
import com.scut.client.user.MyPath;
import com.scut.tools.MyImage;

public class MyShowFilePanel extends JPanel implements ActionListener
{
	private static Logger log = Logger.getLogger("client");
	
	public static String[] Header = {" ","文件名","文件大小","文件类型","文件路径"};
	//public static String[] Header = {"文件名","文件大小","文件类型","文件路径"};///////////////////////
	public static Object[][] Data = null;
	public static DefaultTableModel  tableModel;
	public static JTable table;
	public static JLabel pathlabel = new JLabel("当前路径");
	public static JTextField pathtext = new JTextField(20);
	public static JButton lastdir = new JButton("返回上一级");
	public static JToolBar toolbar = new JToolBar();
	public MyShowFilePanel()
	{
		super();
		setLayout(new BorderLayout());
		toolbar.add(pathlabel);
		toolbar.add(pathtext);
		toolbar.add(lastdir);
		this.add(toolbar, BorderLayout.NORTH);
		tableModel = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row,int column) 
			{
				if (column >= 0)	return false;
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
		for(int i = 0 ; i <table.getColumnCount(); i ++)  
		{  
		    table.getColumnModel().getColumn(i).setCellRenderer(renderer);  
		}  
		table.setOpaque(false);
		this.add(jsp, BorderLayout.CENTER);   //得在scrollPane里面才会显示表头
		
		
		table.setRowHeight(20);//设置列高
		table.setFont(new Font("微软雅黑",Font.PLAIN,12));//设置字体
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("微软雅黑",Font.PLAIN,14));//设置字体

		pathlabel.setFont(new Font("微软雅黑",Font.PLAIN,14));//设置字体
		pathtext.setFont(new Font("微软雅黑",Font.PLAIN,14));//设置字体
		lastdir.setFont(new Font("微软雅黑",Font.PLAIN,14));//设置字体
		
		//table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));   
		//table.getColumnModel().getColumn(0).setCellRenderer(new MyTableRenderer());
		
//		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);

		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(430);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);  
        table.setRowSorter(sorter); 
		table.addMouseListener(new MouseAdapter(){
			  public void mouseClicked(MouseEvent e) { 
				  log.debug("click");
				  int clickTimes=e.getClickCount();
				  if(clickTimes==2)
				  {
					  log.debug("Doublc Clicked!");
					  int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置 

		              //int col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); 
					  String fileName = (String) table.getValueAt(row, 4);
					  log.debug(fileName);
					  //MyMainFrame.findInTree(MyMainFrame.serverTree, fileName);
						
					  if (fileName.contains("."))
					  {
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
					  else
					  {
						  addFile(fileName);
					  }
					  pathtext.setText(fileName);
				  }
				  else if(clickTimes==1)
				  {
					  int row =((JTable)e.getSource()).rowAtPoint(e.getPoint());
					  String fileName = (String) table.getValueAt(row, 4);
					  //String fileName = (String) table.getValueAt(row, 3);
					  log.debug(fileName);
					  MyMainFrame.findInTree(MyMainFrame.serverTree, fileName);
				  }
			  }
		});
		
		lastdir.addActionListener(this);
	
	}
	
	public static void addFile(String filename)
	{
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		String filenames = FileList.listSonfile(filename);
		log.debug(filenames);
		if (filenames.equals("")) return;
		String datas[] = filenames.split(",");
		String data[];
		Object d[] = new Object[5];
		//Object d[] = new Object[4];
		for (String e : datas)
		{
			data = e.split(":");
			d[0] = "";
			//log.debug(data[0]);
			String fileName = data[0].substring(data[0].lastIndexOf("\\") + 1); // 获得上传文件的文件名  
			String sp[] = fileName.split("\\.");
			d[1] = fileName;    //文件名
			//d[0] = fileName;    //文件名
			d[2] = data[1];     //文件大小
			//d[1] = data[1];     //文件大小
			if (sp.length == 1) d[3] = "文件夹"; 
			else d[3] = sp[1]; 
			d[4] = data[0];    //文件路径
//			if (sp.length == 1) d[2] = "文件夹"; 
//			else d[2] = sp[1]; 
//			d[3] = data[0];    //文件路径
			tableModel.addRow(d);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == lastdir)
		{
			if (pathtext.getText().equals("doc")) return;
			String parentDir = pathtext.getText().substring(0, pathtext.getText().lastIndexOf("\\"));
			pathtext.setText(parentDir);
			addFile(parentDir);
		}
	}
	
//	public void paintComponent(Graphics g) 
//	{
//		   int x = 0;// this.getX()
//		   int y = 0;//this.getY();
//		   //if (icon != null) 
//		   g.drawImage(MyImage.showfile.getImage(), x, y, getSize().width, getSize().height, this);// 图片会自动缩放
//	}
	
}



