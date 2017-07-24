package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import com.scut.client.funtion.FileList;
import com.scut.client.funtion.MyDirHandle;
import com.scut.tools.MyImage;

public class MyAddDirDialog extends JDialog implements ActionListener{

	private final JPanel contentPane = new JPanel();
	private JTextField textField;
	JButton btnNewButton = new JButton("ȷ��");
	JButton btnNewButton_1 = new JButton("ȡ��");
	String serverPath;
	public DefaultMutableTreeNode node;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			MyAddDirDialog dialog = new MyAddDirDialog("");
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public MyAddDirDialog(String path) 
	{
		setSize(376,226);
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰� 
		 Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ� 
		 int screenWidth = screenSize.width/2; // ��ȡ��Ļ�Ŀ�
		 int screenHeight = screenSize.height/2; // ��ȡ��Ļ�ĸ�
		 int height = this.getHeight(); 
		 int width = this.getWidth(); 
		 setLocation(screenWidth-width/2, screenHeight-height/2);
		 setIconImage(MyImage.frametitle.getImage());
		serverPath = path;
		setTitle("����Ŀ¼");
		
		
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Ŀ¼����");
		label.setBounds(59, 54, 250, 15);
		label.setFont(new java.awt.Font("΢���ź�", 0, 15));
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(146, 46, 166, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		btnNewButton.setBounds(78, 111, 79, 27);
		getContentPane().add(btnNewButton);
		
		
		btnNewButton_1.setBounds(210, 111, 79, 27);
		getContentPane().add(btnNewButton_1);
		
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public MyAddDirDialog(DefaultMutableTreeNode node , String path) 
	{
		setSize(376,226);
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰� 
		 Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ� 
		 int screenWidth = screenSize.width/2; // ��ȡ��Ļ�Ŀ�
		 int screenHeight = screenSize.height/2; // ��ȡ��Ļ�ĸ�
		 int height = this.getHeight(); 
		 int width = this.getWidth(); 
		 setLocation(screenWidth-width/2, screenHeight-height/2);
		 setIconImage(MyImage.frametitle.getImage());
		//this.setLocationRelativeTo(null); 
		this.node = node;
		serverPath = path;
		setTitle("����Ŀ¼");
		
		//setBounds(100, 100, 520, 240);
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Ŀ¼����");
		label.setBounds(59, 54, 150, 15);
		label.setFont(new java.awt.Font("΢���ź�", 0, 15));
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(146, 51, 166, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		btnNewButton.setBounds(78, 111, 79, 27);
		getContentPane().add(btnNewButton);
		
		
		btnNewButton_1.setBounds(210, 111, 79, 27);
		getContentPane().add(btnNewButton_1);
		
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnNewButton)
		{
			if (textField.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(null, "�ļ���������Ϊ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String res = MyDirHandle.addDir(textField.getText().trim(), serverPath);
			if (res.equals("yes"))
			{
				JOptionPane.showMessageDialog(null, "�½��ļ��гɹ�",null, JOptionPane.INFORMATION_MESSAGE);
				/////
				String filenames = FileList.listSonfile(MyMainFrame.getTreePath(node.getPath()));
				if (filenames.equals("")) return;
				String datas[] = filenames.split(",");
    		    EventQueue.invokeLater(new Runnable() 
    		    {
    				public void run() 
    				{
    					node.removeAllChildren();
    					try 
    					{  
    						for (int i = 0; i < datas.length; i++)
    						{
    							String filepath = datas[i].split(":")[0];
    							String filename = filepath.substring(filepath.lastIndexOf("\\")+1);
    							node.add(new DefaultMutableTreeNode(filename));
    						}
    						MyMainFrame.serverTree.updateUI();
    					} 
    					catch (Exception e) 
    					{
    						e.printStackTrace();
    					}
    				}
    			 });	
    		    /////
			
			}
			else
			{
				JOptionPane.showMessageDialog(null, "�½��ļ���ʧ�ܣ���Ŀ¼�Ѵ���",null, JOptionPane.INFORMATION_MESSAGE);
			}
			dispose();
		}
		else if (e.getSource() == btnNewButton_1)
		{
			dispose();
		}
	}

}
