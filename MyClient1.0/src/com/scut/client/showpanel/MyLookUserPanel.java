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

	public static String[] Header = {" ","id","�ʺ�", "�û��ȼ�", "�û�����","�û�����   "};
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
		this.add(new JScrollPane(table), BorderLayout.CENTER);   //����scrollPane����Ż���ʾ��ͷ
		
		table.setRowHeight(20);//�����и�
		table.setFont(new Font("΢���ź�",Font.PLAIN,12));//��������
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));   
		table.getColumnModel().getColumn(0).setCellRenderer(new MyTableRenderer());
		 
		//getAllUser();
		
		del = new JButton("ɾ��");
		update = new JButton("�޸�");
		all = new JButton("ȫѡ/ȫ��ѡ");
		Box box = Box.createHorizontalBox();  //����box
		box.add(all);
		//box.add(Box.createVerticalStrut(20));  // ��ӿ��40��ˮƽ�ռ�  
		box.add(update);
		//box.add(Box.createVerticalStrut(20));  // ��ӿ��40��ˮƽ�ռ�  
		box.add(del);
		this.add(box, BorderLayout.SOUTH);
		
		checkboxs = false;
		
		all.addActionListener(this);
		del.addActionListener(this);
		update.addActionListener(this);
	}
	
	
	public static void getAllUser()
	{
		
		//��������д����Ϊɾ��һ��֮������е����Ҳ���
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
		String datas[] = res.split("\t");     //ÿһ��������\t����������֮����,����
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
		String datas[] = res.split("\t");     //ÿһ��������\t����������֮����,����
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
			int row = table.getSelectedRow();//��ȡѡ�е��к�
			log.debug("ѡ�е�:"+row+"��");
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "����δѡ���κμ�¼",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String id = (String)tableModel.getValueAt(row, 1);
			String username = (String)tableModel.getValueAt(row, 2);
			String passwd = (String)tableModel.getValueAt(row, 4);
			int privillege = Integer.parseInt((String)tableModel.getValueAt(row, 3));
			MyUpdateUserDialog updateDialog = new MyUpdateUserDialog(id, username, passwd, privillege);
			
			getDelUser();   //�޸���ɺ����¼��������û�
		}
		else if (e.getSource() == del)
		{
			//��������д
//			int[] row = table.getSelectedRows();//��ȡѡ�е��к�
//			for(int a:row) tableModel.removeRow(a);
			
			int rowCount = table.getSelectedRowCount();
			if (rowCount == 0)
			{
				JOptionPane.showMessageDialog(null, "����δѡ���κμ�¼",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			int answer = JOptionPane.showConfirmDialog(null, "ȷ��ɾ���û���",null, JOptionPane.OK_CANCEL_OPTION);
			if (answer == JOptionPane.CANCEL_OPTION) return;
			/*
			 * ��δ����е㼼����������Ϊÿɾ��һ�У������е���Ŷ����һ��������Ҳ��仯������Ҫʵʱ��ȡ������ѡ�е�ɾ����ָ�벻�ü�һ����Ϊ����Ļ����������������ѡ�еģ�ָ���Ҫ��һ
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
    //�˷������Բ鿼JDK�ĵ���˵��   
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
