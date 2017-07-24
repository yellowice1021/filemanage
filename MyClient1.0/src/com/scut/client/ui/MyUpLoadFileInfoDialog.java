package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.log.SysoCounter;
import com.scut.client.funtion.MyDirHandle;
import com.scut.client.funtion.MyFileHandle;
import com.scut.client.funtion.UpDownFile;
import com.scut.tools.MyImage;

public class MyUpLoadFileInfoDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	JLabel lblNewLabel = new JLabel("电站");
	JLabel lblNewLabel_1 = new JLabel("机组");
	JLabel lblNewLabel_2 = new JLabel("项目名称");
	JLabel lblNewLabel_3 = new JLabel("关键字");
	JLabel lblNewLabel_4 = new JLabel("项目号");
	JLabel lblNewLabel_5 = new JLabel("负责人");
	JLabel lblNewLabel_6 = new JLabel("应用场合");
	JLabel lblNewLabel_7 = new JLabel("潜在客户");
	JLabel lblNewLabel_8 = new JLabel("项目状态");

	
	JButton btnNewButton = new JButton("确定");
	JButton btnNewButton_1 = new JButton("取消");
	
	private String serverPath;

	public void init(String serverPath)
	{
		
		Toolkit kit=Toolkit.getDefaultToolkit();

        Dimension screenSize=kit.getScreenSize();  //系统对象获取工具

        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        int width = 391;
        int height = 406;
		
		System.out.println(serverPath);
		this.serverPath = serverPath;
		setTitle("请上传目录关键字信息");
		setIconImage(MyImage.frametitle.getImage());
		setBounds(screenWidth/2-width/2, screenHeight/2-height/2, width, height);
		getContentPane().setLayout(null);
		
	
		lblNewLabel.setBounds(49, 19, 54, 15);
		lblNewLabel.setFont(new java.awt.Font("微软雅黑", 0, 14));
		getContentPane().add(lblNewLabel);
		
		
		lblNewLabel_1.setBounds(49, 52, 54, 15);
		lblNewLabel_1.setFont(new java.awt.Font("微软雅黑", 0, 14));
		getContentPane().add(lblNewLabel_1);
		
	
		lblNewLabel_2.setBounds(49, 85, 64, 15);
		lblNewLabel_2.setFont(new java.awt.Font("微软雅黑", 0, 14));
		getContentPane().add(lblNewLabel_2);
		
	
		lblNewLabel_3.setBounds(49, 118, 54, 15);
		lblNewLabel_3.setFont(new java.awt.Font("微软雅黑", 0, 14));
		getContentPane().add(lblNewLabel_3);
		
	
		lblNewLabel_4.setBounds(49, 151, 93, 15);
		lblNewLabel_4.setFont(new java.awt.Font("微软雅黑", 0, 14));
		getContentPane().add(lblNewLabel_4);
		
	
		lblNewLabel_5.setBounds(49, 184, 54, 15);
		lblNewLabel_5.setFont(new java.awt.Font("微软雅黑", 0, 14));
		getContentPane().add(lblNewLabel_5);
		
		
		lblNewLabel_6.setBounds(49, 217, 64, 15);
		lblNewLabel_6.setFont(new java.awt.Font("微软雅黑", 0, 14));
		getContentPane().add(lblNewLabel_6);
		
		
		lblNewLabel_7.setBounds(49, 250, 64, 15);
		lblNewLabel_7.setFont(new java.awt.Font("微软雅黑", 0, 14));
		getContentPane().add(lblNewLabel_7);
		
		
		lblNewLabel_8.setBounds(49, 283, 64, 15);
		lblNewLabel_8.setFont(new java.awt.Font("微软雅黑", 0, 14));
		getContentPane().add(lblNewLabel_8);
		
		
		btnNewButton.setBounds(69, 320, 79, 27);
		getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(136, 17, 186, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 49, 186, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(136, 82, 186, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(136, 115, 186, 21);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(136, 148, 186, 21);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(136, 181, 186, 21);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(136, 214, 186, 21);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(136, 247, 186, 21);
		getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(136, 280, 186, 21);
		getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		
		btnNewButton_1.setBounds(216, 320, 79, 27);
		getContentPane().add(btnNewButton_1);
		
		
	}
	public MyUpLoadFileInfoDialog(String serverPath, String fileInfo)
	{
		init(serverPath);
		
		String info[] = {"","","","","","","","",""};
		String tmp[] = fileInfo.split(",");
		for(int i = 0; i < tmp.length; i++)
		{
			info[i] = tmp[i];
			if(info[i].equals("null")||info[i].equals(null)) info[i] = "";
		}
		textField.setText(info[0]);
		textField_1.setText(info[1]);
		textField_2.setText(info[2]);
		textField_3.setText(info[3]);
		textField_4.setText(info[4]);
		textField_5.setText(info[5]);
		textField_6.setText(info[6]);
		textField_7.setText(info[7]);
		textField_8.setText(info[8]);
		
		
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
	
		setModal(true);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public MyUpLoadFileInfoDialog(String serverPath) {
		
		init(serverPath);
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
			String powerstation = textField.getText().trim();
			String crew = textField_1.getText().trim();
			String projectname = textField_2.getText().trim();
			String keyword = textField_3.getText().trim();
			String projectid= textField_4.getText().trim();
			String manager= textField_5.getText().trim();
			String applysituation= textField_6.getText().trim();
			String potentialcustomers= textField_7.getText().trim();
			String projectstate  = textField_8.getText().trim();
				
			String res = MyDirHandle.addDirKeywords(serverPath, powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate);
			if (res.equals("yes"))
			{
				JOptionPane.showMessageDialog(null, "文件信息上传成功",null, JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else if (res.equals("no"))
			{
				JOptionPane.showMessageDialog(null, "文件信息上传失败",null, JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "该文件已经不存在",null, JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
		}
		else if (e.getSource() == btnNewButton_1)
		{
			this.dispose();
			return;
		}
	}

}
