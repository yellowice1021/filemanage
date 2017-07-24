package com.scut.client.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.BusinessBlackSteelSkin;

import com.scut.tools.MyOfficeImage;
import com.scut.tools.MyPdf;
import com.scut.tools.MyWord;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.TextArea;
import java.io.File;

public class MyScanDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea = new JTextArea();
	private JLabel[] label;
	private JScrollPane jsPane;
	
	public void initScan()
	{
		jsPane = new JScrollPane();
		//JPanel panel = new JPanel();
		Box verticalBox   = Box.createVerticalBox(); 
		File file = new File("tmp/");
		String[] files = file.list();
		label = new JLabel[files.length];
		int i = 0;
		for(String e: files)
		{
			if (!e.endsWith("png")) continue; 
			System.out.println(e);
			label[i] = new JLabel();
			label[i].setIcon(new ImageIcon(MyOfficeImage.tmpDir + e));
			label[i].setBorder(BorderFactory.createEtchedBorder());
			//panel.add(label[i]);
			verticalBox.add(label[i]);
//			JSeparator separator = new JSeparator();   //创建水平分隔线  
//	        separator.setOrientation(JSeparator.HORIZONTAL); 
//			verticalBox.add(separator);
			//jsPane.getViewport().add(label[i]);
			i++;
		}
		jsPane.getViewport().add(verticalBox);
		contentPanel.add(jsPane, BorderLayout.CENTER);//jsPane
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//String content = MyPdf.readPdf("E:\\杂项\\CMake Practice.pdf");
		//String content = MyWord.readDoc("E:\\杂项\\毕业论文开题报告范文.doc");
		//textArea.setText(content);//差点闹了个笑话
		
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//必须要有这一句，因为英文解释里大概是可以设置了这个，修饰的那些东西就会由lookfeel提供
		JDialog.setDefaultLookAndFeelDecorated(true);
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{
					 //SubstanceLookAndFeel.setCurrentTheme(new SubstanceBottleGreenTheme());//设置主题
					 SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
					//StandardButtonShaper()是圆的
					 //String c = Class.forName("BusinessBlackSteelSkin").newInstance().toString();
					 //UIManager.setLookAndFeel(c);
					 
					//SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessBlackSteelSkin");
					SubstanceLookAndFeel.setCurrentButtonShaper(new org.jvnet.substance.button.ClassicButtonShaper());
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				MyScanDialog dialog = null;
				try {
				    dialog= new MyScanDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.initScan();	
					dialog.setVisible(true);
					//dialog.textArea.setText(content); //为什么在线程中调用有点区别
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
		
		//SwingUtilities.updateComponentTreeUI(dialog);
	}

	/**
	 * Create the dialog.
	 */
	public MyScanDialog()
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
		
		
		//contentPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
		
	}

}
