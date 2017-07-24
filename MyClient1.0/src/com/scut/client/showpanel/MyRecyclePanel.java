package com.scut.client.showpanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import com.scut.client.funtion.FileSearchHandle;
import com.scut.client.funtion.MyFileHandle;
import com.scut.client.funtion.RecycleHandle;
import com.scut.client.user.MyUser;
import com.scut.tools.MyImage;

public class MyRecyclePanel extends JPanel implements ActionListener{

	public static String[] Header = {" ","ԭ�ļ�·��"};
	public static Object[][] Data = null;
	public static DefaultTableModel  tableModel;
	public static JTable table;
	
	private static Logger log = Logger.getLogger("client");
	
	JButton button1 = new JButton("�ָ�");
	JButton button2 = new JButton("����ɾ��");

	/**
	 * Create the panel.
	 */
	public MyRecyclePanel() 
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
		
		table.setRowHeight(20);//�����и�
		table.setFont(new Font("΢���ź�",Font.PLAIN,12));//��������
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));   
		table.getColumnModel().getColumn(0).setCellRenderer(new MyTableRenderer());
		
		table.getColumnModel().getColumn(0).setPreferredWidth(38);
		table.getColumnModel().getColumn(1).setPreferredWidth(1313);
		
		Box box = Box.createHorizontalBox();  //����box
		box.add(button1);
		box.add(button2);
		button1.addActionListener(this);
		button2.addActionListener(this);
		add(box,BorderLayout.SOUTH);
	}
	
	public static void addFile(String filename)
	{
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		String filenames = RecycleHandle.getRecycle();
		log.debug(filenames);
		if (filenames.equals("")) return;
		String datas[] = filenames.split(",");
		Object d[] = new Object[2];
		for (String e : datas)
		{
			d[0] = false;
			d[1] = e;
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
		if (e.getSource() == button1)
		{
			
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			String rows = "";
			
			int i = 0;
			int sum = tableModel.getRowCount();
			while(i < sum)
			{
				if ((Boolean)tableModel.getValueAt(i, 0) == true)
				{
					rows += (String)tableModel.getValueAt(i, 1) + ",";
				}
				i++;
			}
			
			int answer = JOptionPane.showConfirmDialog(null, "ȷ���ָ����ļ���",null, JOptionPane.OK_CANCEL_OPTION);
			
			if (answer == JOptionPane.CANCEL_OPTION) return;
			
			RecycleHandle.backFromRecycle(rows);
			String s = RecycleHandle.getRecycle();
			MyRecyclePanel.addFile(s);
		}
		else if (e.getSource() == button2)
		{
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			log.info("�����ӻ���վɾ���ļ�");
			
            String rows = "";
			
			int i = 0;
			int sum = tableModel.getRowCount();
			while(i < sum)
			{
				if ((Boolean)tableModel.getValueAt(i, 0) == true)
				{
					rows += (String)tableModel.getValueAt(i, 1) + ",";
				}
				i++;
			}
			
			log.debug(rows);
			int answer = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����Щ�ļ���",null, JOptionPane.OK_CANCEL_OPTION);
			
			if (answer == JOptionPane.CANCEL_OPTION) return;
			
			String s = MyFileHandle.delFiles(rows);
			
			if (s.equals("yes"))
			{
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�",null, JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��",null, JOptionPane.INFORMATION_MESSAGE);
			}
			
			s = RecycleHandle.getRecycle();
			MyRecyclePanel.addFile(s);

		}
	}


}
