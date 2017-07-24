package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.BusinessBlackSteelSkin;

import com.scut.tools.MyExcel;
import com.scut.tools.MyPdf;
import com.scut.tools.MyWord;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.TextArea;

public class MyOnlineSacnDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea = new JTextArea();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		//String content = MyPdf.readPdf("E:\\杂项\\CMake Practice.pdf");
//		//String content = MyWord.readDoc("E:\\杂项\\毕业论文开题报告范文.doc");
//		//String content = MyWord.readDocx("E:\\杂项\\自动化毕业论文开题报告范文.docx");
//		String content = MyExcel.readXls1("E:\\杂项\\excel\\决算收支.xls");
//		JDialog.setDefaultLookAndFeelDecorated(true);
//		EventQueue.invokeLater(new Runnable() {
//			public void run() 
//			{
//				try 
//				{
//					 //SubstanceLookAndFeel.setCurrentTheme(new SubstanceBottleGreenTheme());//设置主题
//					 SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
//					 SubstanceLookAndFeel.setCurrentButtonShaper(new org.jvnet.substance.button.ClassicButtonShaper());
//				} 
//				catch (Exception e) 
//				{
//					e.printStackTrace();
//				}
//				
//				MyOnlineSacnDialog dialog = null;
//				try {
//				    dialog= new MyOnlineSacnDialog();
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//					dialog.textArea.setText(content); //为什么在线程中调用有点区别
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//
//		
//		
//		//SwingUtilities.updateComponentTreeUI(dialog);
//	}

	/**
	 * Create the dialog.
	 */
	public MyOnlineSacnDialog()
	{
		setBounds(100, 100, 657, 484);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("\u5728\u7EBF\u9884\u89C8");
		label.setFont(new Font("宋体", Font.PLAIN, 22));
		panel.add(label);
		
		
		contentPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
		
	}
}