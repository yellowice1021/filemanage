package com.scut.client.showpanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.apache.log4j.Logger;

import com.scut.client.funtion.LogHandle;
import com.scut.client.funtion.UserHandle;
import com.scut.client.ui.MyDateDialog;
import com.suct.client.print.FileHelper;
import com.suct.client.print.PrinterHelper;
import com.suct.client.print.logPrint;



/*
 * 
 */
public class MyLookLogPanel extends JPanel implements ActionListener{

//	JLabel label1 = new JLabel("");
//	JLabel label2   = new JLabel("");
	private static Logger log = Logger.getLogger("client");
	public static String[] Header = {" ","��־���", "�û��ʺ�", "��������","�û�����"};
	public static Object[][] Data = null;
	public static Object[][] tmpData = null;
	public static DefaultTableModel  tableModel;
	public static DefaultTableModel tmpModel;
	public static JTable table;
	JLabel label1 = new JLabel("�û��ʺ�");
	JButton label2 = new JButton("��ʼʱ��");
	JButton label3 = new JButton("����ʱ��");
	JLabel label4 = new JLabel("����");
	JButton button = new JButton("��ѯ");
	JTextField userid = new JTextField();
	JTextField date1 = new JTextField();
	JTextField date2 = new JTextField();
	
//	JButton date1= new JButton();
//	JButton date2 = new JButton();
	
	JTextField type = new JTextField();
	
	JButton all = new JButton("ȫѡ/ȫ��ѡ");
	JButton del = new JButton("ɾ����־");
	JButton printlog = new JButton("��ӡ��־");
	public boolean checkboxs;
	/**
	 * Create the panel.
	 */
	public MyLookLogPanel() 
	{
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
	    
		//tableModel = new DefaultTableModel(Data, Header);
		tmpModel = new DefaultTableModel(tmpData, Header);
		table = new JTable(tableModel);
		this.add(new JScrollPane(table), BorderLayout.CENTER);   //����scrollPane����Ż���ʾ��ͷ
		
		table.setRowHeight(20);//�����и�
		table.setFont(new Font("΢���ź�",Font.PLAIN,12));//��������
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));   
		table.getColumnModel().getColumn(0).setCellRenderer(new MyTableRenderer());
		
		//��һ�������п��С
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(48);
		table.getColumnModel().getColumn(0).setMinWidth(38);
		
		Box box = Box.createHorizontalBox();  //����box
		box.add(label1);
		box.add(userid);
		box.add(label2);
		box.add(date1);
		box.add(label3);
		box.add(date2);
		box.add(label4);
		box.add(type);
		box.add(button);
		this.add(box, BorderLayout.NORTH);
		
		Box box1 = Box.createHorizontalBox();
		box1.add(all);
		box1.add(del);
		box1.add(printlog);
		add(box1, BorderLayout.SOUTH);
		
		checkboxs = false;
		
		label2.addActionListener(this);
		label3.addActionListener(this);
		all.addActionListener(this);
		del.addActionListener(this);
		printlog.addActionListener(this);
		button.addActionListener(this);
	}
	
	
	/*
	 * tableModel����չʾ���������tmpModel�����洢���ص�������Ϣ�������Ƕ�tmpModel���в��ң�ÿ��ɾ����Ϣ֮������»�ȡ���������ݵ�
	 */
	public static void search(String username, String date1, String date2, String type)
	{
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		for (int i = 0; i < tmpModel.getRowCount(); i++)
		{
			tableModel.addRow(new Object[]{false,tmpModel.getValueAt(i, 1),
					tmpModel.getValueAt(i, 2),tmpModel.getValueAt(i, 3), tmpModel.getValueAt(i, 4)});
		}
		//tableModel = tmpModel;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dt1 = null;
		Date dt2 = null;
		if (!date1.equals(""))
		{
			try {
				dt1 = df.parse(date1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!date2.equals(""))
		{
			try {
				dt2 = df.parse(date2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    Date tmp = null;
	    //Object d[][] = new Object[tableModel.getRowCount()][];
	    //int j = 0;
	    ArrayList<Object[]>vec = new ArrayList<Object[]>();
	   
		for (int i = 0; i < tableModel.getRowCount(); i++)
		{
			try {
				tmp = df.parse((String)tableModel.getValueAt(i, 3));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if ( !date1.equals("") )
			{
				if (tmp.getTime() < dt1.getTime()) continue;
			}
			if ( !date2.equals(""))
			{
				if (tmp.getTime() > dt2.getTime()) continue;
			}
			if ( !username.equals("") && !((String)tableModel.getValueAt(i, 2)).equals(username) )
			{
					continue;	
			}
			if ( !type.equals("") && ((String)tableModel.getValueAt(i, 4)).indexOf(type) == -1)
			{
				continue;
			}
			vec.add( new Object[]{false,tableModel.getValueAt(i, 1), tableModel.getValueAt(i, 2),
					tableModel.getValueAt(i, 3), tableModel.getValueAt(i, 4)} );
		}
		
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		
		for (int i = 0; i < vec.size(); i++)
		{
			tableModel.addRow(vec.get(i));
		}
		
	}
	
	public static void getAllLog()
	{
		while(tableModel.getRowCount() > 0)
		{
			tableModel.removeRow(0);
		}
		while(tmpModel.getRowCount() > 0)
		{
			tmpModel.removeRow(0);
		}
		
		String res = LogHandle.getAllLog();
		String datas[] = res.split("\t");
		String data[];
		Object d[] = new Object[5];
		for (String e: datas)
		{
			data = e.split(" ");
			d[0] = false;
			d[1] = data[0];
			d[2] = data[1];
			d[3] = data[2];
			d[4] = data[3];
			tableModel.addRow(d);
			tmpModel.addRow(d);
		}
		
	}
	
	/*
	 * ��ȡɾ��ָ����־���������־
	 */
	public static void getDelLog()
	{
		while(tableModel.getRowCount() > 0)
		{
			tableModel.removeRow(0);
		}
		while(tmpModel.getRowCount() > 0)
		{
			tmpModel.removeRow(0);
		}
		
		String res = LogHandle.getDelLog();
		String datas[] = res.split("\t");
		String data[];
		Object d[] = new Object[5];
		for (String e: datas)
		{
			data = e.split(" ");
			d[0] = false;
			d[1] = data[0];
			d[2] = data[1];
			d[3] = data[2];
			d[4] = data[3];
			tableModel.addRow(d);
			tmpModel.addRow(d);
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
	            tmpModel.setValueAt(checkboxs, i, 0);
	        }
	        checkboxs = !checkboxs;
		}
		else if (e.getSource() == del)            //����ɾ����־
		{
			String rows = "";
			
			int i = 0;
			int sum = tableModel.getRowCount();
			while(i < sum)
			{
				if ((Boolean)tableModel.getValueAt(i, 0) == true)
				{
					rows += (String)tableModel.getValueAt(i, 1) + " ";
				}
				i++;
			}
			
			int answer = JOptionPane.showConfirmDialog(null, "ȷ��ɾ������־��",null, JOptionPane.OK_CANCEL_OPTION);
			
			if (answer == JOptionPane.CANCEL_OPTION) return;
			
			LogHandle.delLog(rows);
			
			getDelLog();
			search(userid.getText(), date1.getText(), date2.getText(), type.getText());
		}
		else if (e.getSource() == printlog)
		{
		   
			String res = "";
			for (int i=0; i<table.getRowCount(); i++)
	        {
				if ((Boolean)tableModel.getValueAt(i, 0) == true)
				{
					res += tableModel.getValueAt(i, 1) + " ";
		            res += tableModel.getValueAt(i, 2) + " ";
		            res += tableModel.getValueAt(i, 3) + " ";
		            res += tableModel.getValueAt(i, 4) + "\t";
				}
	            
	        }
			
			try {
				logPrint.makeLogFile(res); 
				PrinterHelper.printToFile(FileHelper.DEFAULT__FILE);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == button)
		{
			search(userid.getText(), date1.getText(), date2.getText(), type.getText());
		}
		else if (e.getSource() == label2)
		{
			MyDateDialog dateDialog = new MyDateDialog(date1);
		}
		else if (e.getSource() == label3)
		{
			MyDateDialog dateDialog = new MyDateDialog(date2);
		}
	}

}
//class MyTableRenderer extends JCheckBox implements TableCellRenderer {   
//    //�˷������Բ鿼JDK�ĵ���˵��   
//	@Override
//    public Component getTableCellRendererComponent( JTable table,   
//            Object value,   
//            boolean isSelected,   
//            boolean hasFocus,   
//            int row,   
//            int column ) {   
//        Boolean b = (Boolean) value;   
//        this.setSelected(b.booleanValue());   
//        return this;   
//    }   
//} 
