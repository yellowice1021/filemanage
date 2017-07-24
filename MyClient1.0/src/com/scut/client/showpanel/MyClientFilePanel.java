package com.scut.client.showpanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import com.scut.client.funtion.FileSearchHandle;
import com.scut.client.ui.MyMainFrame;

public class MyClientFilePanel extends JPanel implements ActionListener{

	
	public static String[] Header = {"文件路径","电站","机组","项目名称","关键字","项目立项号","负责人","潜在客户","项目状态"};
	public static Object[][] Data = null;
	public static DefaultTableModel  tableModel;
	public static JTable table;
	
	
	private static Logger log = Logger.getLogger("client");
	
	JButton button1 = new JButton("条件查询");
	//JButton button2 = new JButton("组合条件查询");

	/**
	 * Create the panel.
	 */
	public MyClientFilePanel() 
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
		
		Box box = Box.createHorizontalBox();  //横向box
		box.add(button1);
		//box.add(button2);
		add(box, BorderLayout.SOUTH);
		button1.addActionListener(this);
		table.addMouseListener(new MouseListener() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("hahahaha");
				int row = table.getSelectedRow();
				String path = (String) table.getValueAt(row, 0);
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
	
	public static void addFile(List<Object[]> datas)
	{
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		int i = 0;
		while(i < datas.size())
		{

			tableModel.addRow(datas.get(i));
			i++;
		}
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button1)
		{
			MyClientFileSearchDialog clientFileSearchDialog = new MyClientFileSearchDialog();
		}
	}
	

}
