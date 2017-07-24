package com.scut.client.showpanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import javax.swing.table.TableCellRenderer;

import org.apache.log4j.Logger;

import com.scut.client.funtion.UserHandle;
import com.scut.tools.HistoryIo;

public class MyLookUserPanel extends JPanel implements ActionListener{

	public static String[] Header = {" ","id","帐号", "用户等级", "用户密码","用户姓名   "};
	public static Object[][] Data = null;
	public static DefaultTableModel  tableModel;
	//public LookUserTableModel  tableModel;
	public static JTable table;
	public static JButton del;
	public static JButton update;
	public static JButton all;
	public boolean checkboxs;
	
	private static Logger log = Logger.getLogger("client");
	/**
	 * Create the panel.
	 */
	public MyLookUserPanel() 
	{
		
		setLayout(new BorderLayout());
		//tableModel = new DefaultTableModel( Data, Header);
		tableModel = new DefaultTableModel(){
				@Override
				public boolean isCellEditable(int row,int column) 
				{
				  if (column > 0)	
	              return false;
				  else return true;
	             }
		};
		tableModel.setDataVector(Data, Header);
		table = new JTable(tableModel);
		this.add(new JScrollPane(table), BorderLayout.CENTER);   //得在scrollPane里面才会显示表头
		
		table.setRowHeight(20);//设置列高
		table.setFont(new Font("微软雅黑",Font.PLAIN,12));//设置字体
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("微软雅黑",Font.PLAIN,14));//设置字体
		
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));   
		table.getColumnModel().getColumn(0).setCellRenderer(new MyTableRenderer());
		 
		//getAllUser();
		
		del = new JButton("删除");
		update = new JButton("修改");
		all = new JButton("全选/全不选");
		Box box = Box.createHorizontalBox();  //横向box
		box.add(all);
		//box.add(Box.createVerticalStrut(20));  // 添加宽度40的水平空间  
		box.add(update);
		//box.add(Box.createVerticalStrut(20));  // 添加宽度40的水平空间  
		box.add(del);
		this.add(box, BorderLayout.SOUTH);
		
		checkboxs = false;
		
		all.addActionListener(this);
		del.addActionListener(this);
		update.addActionListener(this);
	}
	
	
	public static void getAllUser()
	{
		
		//不能这样写，因为删除一个之后后面行的序号也会变
//		int sum = tableModel.getRowCount();             
//		for(int i = 0; i < sum; i++)
//		{
//			
//		}
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		
		String res = UserHandle.getAllUser();
		String datas[] = res.split("\t");     //每一行数据用\t隔开，属性之间用,隔开
		String data[];
		Object d[] = new Object[6];
		for(String e : datas)
		{
			data = e.split(",");
			d[0] = false;
			d[1] = data[0];
			d[2] = data[1];
			d[3] = data[2];
			d[4] = data[3];
			d[5] = data[4];
			//tableModel.addRow(data);
			tableModel.addRow(d);
		}
	}
	
	public static void getDelUser()
	{
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		
		String res = UserHandle.getDelUser();
		String datas[] = res.split("\t");     //每一行数据用\t隔开，属性之间用,隔开
		String data[];
		Object d[] = new Object[6];
		for(String e : datas)
		{
			data = e.split(",");
			d[0] = false;
			d[1] = data[0];
			d[2] = data[1];
			d[3] = data[2];
			d[4] = data[3];
			d[5] = data[4];
			//tableModel.addRow(data);
			tableModel.addRow(d);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == update)
		{
			int row = table.getSelectedRow();//获取选中的行号
			log.debug("选中第:"+row+"行");
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "您并未选择任何记录",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String id = (String)tableModel.getValueAt(row, 1);
			String username = (String)tableModel.getValueAt(row, 2);
			String passwd = (String)tableModel.getValueAt(row, 4);
			int privillege = Integer.parseInt((String)tableModel.getValueAt(row, 3));
			MyUpdateUserDialog updateDialog = new MyUpdateUserDialog(id, username, passwd, privillege);
			
			getDelUser();   //修改完成后重新加载所有用户
		}
		else if (e.getSource() == del)
		{
			//不能这样写
//			int[] row = table.getSelectedRows();//获取选中的行号
//			for(int a:row) tableModel.removeRow(a);
			
			int rowCount = table.getSelectedRowCount();
			if (rowCount == 0)
			{
				JOptionPane.showMessageDialog(null, "您并未选择任何记录",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			int answer = JOptionPane.showConfirmDialog(null, "确定删除用户？",null, JOptionPane.OK_CANCEL_OPTION);
			if (answer == JOptionPane.CANCEL_OPTION) return;
			/*
			 * 这段代码有点技术含量，因为每删除一行，后面行的序号都会减一，总行数也会变化，所以要实时获取行数，选中的删除后指针不用加一，因为后面的会替上来，如果不是选中的，指针就要加一
			 */
			int i = 0;
			int sum = tableModel.getRowCount();
			while(i < sum)
			{
				if ( (boolean) tableModel.getValueAt(i, 0) == true)
				{
					UserHandle.delUser((String)(tableModel.getValueAt(i, 1)));
					tableModel.removeRow(i);
				}
				else
				{
					i++;
				}
				sum = tableModel.getRowCount();
			}
		}
		else if (e.getSource() == all)
		{
			log.debug("choose all");
			for (int i=0; i<table.getRowCount(); i++)
	        {
	            tableModel.setValueAt(checkboxs, i, 0);
	        }
	        checkboxs = !checkboxs;
		}
	}
	

}


class MyTableRenderer extends JCheckBox implements TableCellRenderer {   
    //此方法可以查考JDK文档的说明   
	@Override
    public Component getTableCellRendererComponent( JTable table,   
            Object value,   
            boolean isSelected,   
            boolean hasFocus,   
            int row,   
            int column ) {   
        Boolean b = (Boolean) value;   
        this.setSelected(b.booleanValue()); 
        this.setOpaque(false);
        return this;   
    }   
	
} 
