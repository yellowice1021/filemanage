package com.scut.client.showpanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import com.scut.client.funtion.LogHandle;
import com.scut.client.funtion.RecycleHandle;

public class MyFileVersionPanel extends JPanel implements ActionListener{



	public static String[] Header = {"文件名","时间"};
	public static Object[][] Data = null;
	public static DefaultTableModel  tableModel;
	public static JTable table;
	
	private static Logger log = Logger.getLogger("client");
	

	/**
	 * Create the panel.
	 */
	public MyFileVersionPanel() 
	{
		setLayout(new BorderLayout());
		tableModel = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row,int column) 
			{
              return false;
             }
	    };
	    tableModel.setDataVector(Data, Header);
		//tableModel = new DefaultTableModel( Data, Header);
		table = new JTable(tableModel);
		this.add(new JScrollPane(table), BorderLayout.CENTER);   //得在scrollPane里面才会显示表头
		
		table.setRowHeight(20);//设置列高
		table.setFont(new Font("微软雅黑",Font.PLAIN,12));//设置字体
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("微软雅黑",Font.PLAIN,14));//设置字体

	}
	
	public static void addFile(String filename)
	{
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
//		String datas[] = filename.split(",");
//		for (String e : datas)
//		{
//			Object d[] = new Object[1];
//			d[0] = e;
//			tableModel.addRow(d);
//		}
		
		String res = LogHandle.getAllLog();
		String datas[] = filename.split("\t");
		String data[] = {"",""};
		Object d[] = new Object[2];
		for (String e: datas)
		{
			data = e.split(",");
			d[0] = data[0];
			d[1] = data[1];
			tableModel.addRow(d);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
