package com.scut.client.ui;


import java.awt.Color;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.apache.log4j.Logger;

import com.scut.client.funtion.MyDirHandle;
import com.scut.client.funtion.UpDownFile;
import com.scut.client.user.MyPath;
import com.scut.client.user.WorkingJob;
import com.scut.tools.MyImage;

class PicFilefilter extends FileFilter
{

	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		String name = f.getName();
		return f.isDirectory() || name.endsWith(".png") || name.endsWith(".bmp") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".JPG") || name.endsWith(".PNG") || name.endsWith(".BMP") || name.endsWith(".JPEG");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "*.png;*.jpg;*.bmp;*.jpeg;*.PNG;*.JPG;*.BMP;*.JPEG";
	}
	
}

public class MyAddDirInfoDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
    private  JFileChooser fc =  new JFileChooser();
    private File[] path =  new File[]{};

	String funtiondesc;
	String technicalFeature;
	String applystituation;
	String potentialcustomer;
	String projectstate;
	
	//窗口组件
	JPanel mainPanel;
	JScrollPane[] scrollPane = new JScrollPane[4];
	JTextArea[] textArea = new JTextArea[4];
	JLabel[] label = new JLabel[5];
	JLabel photo;
	JLabel conceal;
	JLabel pre;
	JLabel next;
	JLabel del;
	
	ImageIcon image;
	String[] labelCaption = {"设备描述(200字)", "技术特点(200字)", 
			 "项目状态(50字)","潜在客户(50字)"};//"照片或3D图",
	
	public DefaultTableModel tbmodel;
	JTable table;
	public static Object[][] Data ={{"","",""},
			                                     {"","",""},
	                                             {"","",""},
                                                 {"","",""},
                                                 {"","",""},
			                                     {"","",""},
	                                             {"","",""},
                                                 {"","",""},
                                                 {"","",""},
                                                 {"","",""}
	                                            };//{new JTextArea(),new JTextArea(),new JTextArea()}
	//JTable表头
	String[] columnNames = {"应用电站/机组", "供货日期","质保时限(月)"};
	
	//JTable table = new JTable(Data, columnNames);
	
	JButton button1 = new JButton("确定");
	JButton button2 = new JButton("取消");
	
	String serverPath;
	String s;
	
	int pic_sum;
	int pic_num;
	String[] pic;         //保存目录下的所以图片

	
	private static Logger log = Logger.getLogger("client");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		try {
			MyAddDirInfoDialog dialog = new MyAddDirInfoDialog("","");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public MyAddDirInfoDialog(String serverPath, String res) 
	{
		this.s = res;
		this.serverPath = serverPath;
		setTitle("编辑目录相关信息");
		getContentPane().setLayout(null);            //无分布
		setResizable(false);        //窗口不可调
			
		//创建主面板
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 860, 600);
		
		setSize(860, 600);
		setIconImage(MyImage.frametitle.getImage());
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包 
		 Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸 
		 int screenWidth = screenSize.width/2; // 获取屏幕的宽
		 int screenHeight = screenSize.height/2; // 获取屏幕的高
		 int height = this.getHeight(); 
		 int width = this.getWidth(); 
		 setLocation(screenWidth-width/2, screenHeight-height/2);
	
		
		//标签的创建和加入面板
		for(int i = 0; i < 4; i++)
		{
			label[i] = new JLabel(labelCaption[i]);
		}
		label[0].setBounds(480, 20, 160, 25);
		label[0].setFont(new Font("微软雅黑",Font.PLAIN,12));//设置字体
		
		label[1].setBounds(480, 145, 160, 25);
		label[1].setFont(new Font("微软雅黑",Font.PLAIN,12));//设置字体
		
		//label[2].setBounds(20, 5, 90, 80);
		label[2].setBounds(480, 274, 160, 25);
		label[2].setFont(new Font("微软雅黑",Font.PLAIN,12));//设置字体
		
		label[3].setBounds(480, 400,160, 25);
		label[3].setFont(new Font("微软雅黑",Font.PLAIN,12));//设置字体
		
//		label[3].setBounds(340, 215, 90, 15);//应用电站/机组
//		label[6].setBounds(340, 240, 90, 15);
//		label[7].setBounds(340, 275, 90, 15);

		
		for(int i = 0; i < 4; i++)
		{
			mainPanel.add(label[i]);
		}
		
		//文本域的创建和加入面板
		for(int i = 0; i < 4; i++)
		{
			textArea[i] = new JTextArea();
			textArea[i].setLineWrap(true);
			scrollPane[i] = new JScrollPane(textArea[i]);                //加入滚动条面板
			scrollPane[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			//scrollPane[i].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane[i].setCursor(new Cursor(Cursor.HAND_CURSOR));     //设置鼠标形状
		}
		scrollPane[0].setBounds(480, 40, 320, 100);
		scrollPane[1].setBounds(480, 164, 320, 100);
		//scrollPane[2].setBounds(420, 210, 180, 30);//电站
		scrollPane[2].setBounds(480, 295, 320, 100);
		scrollPane[3].setBounds(480, 420, 320, 100);
		for(int i = 0; i < 4; i++)
		{
			mainPanel.add(scrollPane[i]);
		}
		
		tbmodel = new DefaultTableModel(Data, columnNames);
		table = new JTable(tbmodel);
		JScrollPane table_scrollpane = new JScrollPane(table);
		mainPanel.add(table_scrollpane);
		table.setRowHeight(23);//设置列宽
		table.setFont(new Font("微软雅黑",Font.PLAIN,12));//设置字体
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("微软雅黑",Font.PLAIN,11));//设置表头显示字体
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, render);
		
		table_scrollpane.setBounds(25, 420, 400, 90);
		//table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		table.getColumnModel().getColumn(0).setCellRenderer(null);
		table.addMouseListener(new MouseAdapter(){
			  public void mouseClicked(MouseEvent e) { 
				log.debug("click");
				 
                int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置 

                int col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); //获得列位置 String cellVal=(String)(tbModel.getValueAt(row,col)); //获得点击单元格数据 txtboxRow.setText((row+1)+""); txtboxCol.setText((col+1)+""); 

                log.debug(row+","+col);
                
                if (col == 1)
                {
                	JTextField  text = new JTextField();
                	MyDateDialog dialog = new MyDateDialog(text);
                	String date = dialog.getDate();
                	log.debug(date);
                	if (!date.equals("null")) 
                	{
                		table.setValueAt(date, row, col);
                		dialog.resetDate();
                	}
                }

			  }          
		});
		
		
		//应用电站和机组下面加上日期和时限
		
		
		//缩略图的创建和加入面板
		photo = new JLabel("图片", JLabel.CENTER);
		photo.setBackground(new Color(255, 255, 224));
		photo.setOpaque(true);                                           //背景透明 
		photo.setBorder(new LineBorder(Color.BLACK, 2));
		photo.setBounds(25, 30, 400, 360);
		photo.setCursor(new Cursor(Cursor.HAND_CURSOR));                 //设置鼠标形状
		//photo.addMouseListener(new MyMouseListener());                   //添加点击事件

		//image.setImage(image.getImage().getScaledInstance(110, 80, Image.SCALE_DEFAULT));//修改图片大小
		//photo.setIcon(image);                                            //插入图片
		mainPanel.add(photo);
		
		String dir_path = MyDirHandle.getDirPicPath(serverPath);
		log.debug(dir_path);
		
		pic = dir_path.split(";");
		pic_sum = pic.length;
		pic_num = 0;
		for(String e:pic)
		{
			System.out.println(e);
		}
		if (!"".equals(dir_path))
		{
			String pic_name = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// 获得上传文件的文件名  
			String serverRootPath = "D:\\filemanage\\";
			String clientpath = MyPath.DirPicPath + "\\" + pic_name;
			UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
			ImageIcon tmpimage = new ImageIcon(clientpath);
			image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
	                Image.SCALE_DEFAULT)); 
			photo.setIcon(image); 

		}
		
		//隐藏按钮的创建和加入面板		photo.setBounds(25, 100, 300, 280);
		conceal = new JLabel("<HTML><U>上传</U></HTML>");                //设置下划线
		conceal.setForeground(Color.blue);
		conceal.setBounds(156, 390, 40, 20);
		conceal.setCursor(new Cursor(Cursor.HAND_CURSOR));               //设置鼠标形状
		conceal.addMouseListener(new MyMouseListener());                 //添加点击事件
	
		//隐藏按钮的创建和加入面板
		pre = new JLabel("<HTML><U>上一张</U></HTML>");                //设置下划线<上一张>
		pre.setForeground(Color.blue);
		pre.setBounds(25, 390, 60, 20);
		pre.setCursor(new Cursor(Cursor.HAND_CURSOR));               //设置鼠标形状
		pre.addMouseListener(new MyMouseListener());		//添加点击事件
		
		
		//隐藏按钮的创建和加入面板
		next = new JLabel("<HTML><U>下一张</U></HTML>");                //设置下划线<下一张>
		next.setForeground(Color.blue);
		next.setBounds(386, 390, 60, 20);
		next.setCursor(new Cursor(Cursor.HAND_CURSOR));               //设置鼠标形状
		next.addMouseListener(new MyMouseListener());      
		//添加点击事件
		
		del = new JLabel("<HTML><U>删除</U></HTML>");                //设置下划线
		del.setForeground(Color.blue);
		del.setBounds(276, 390, 40, 20);
		del.setCursor(new Cursor(Cursor.HAND_CURSOR));               //设置鼠标形状
		del.addMouseListener(new MyMouseListener());                 //添加点击事件
		
		mainPanel.add(conceal);
		mainPanel.add(pre);
		mainPanel.add(next);
		mainPanel.add(del);
		
		getContentPane().add(mainPanel);
		
		
		
		button1.setBounds(160, 538, 93, 23);
		mainPanel.add(button1);
		
		button2.setBounds(570, 538, 93, 23);
		mainPanel.add(button2);
		

		String info[] = {"","","","","","","","",""};
		String tmp[] = s.split(",");
		for(int i = 0; i < tmp.length; i++)
		{
			info[i] = tmp[i];
		}
		for(int i = 0; i < 4; i++)
		{
			if (info[i] == null || info[i].equals("null"))textArea[i].setText("");
			else textArea[i].setText(info[i]);
		}
		
		String applystring = MyDirHandle.getDirapply(serverPath);
		String rows[] = applystring.split("\t");
		int i = 0;
		log.debug(applystring);
		for (String e:rows)
		{
			log.debug(e);
			String tmps[] = e.split(",");
			log.debug("长度:"+tmps.length);
			if (tmps.length <3) continue;
			log.debug(tmps[0]);
			log.debug(tmps[1]);
			log.debug(tmps[2]);
			tbmodel.setValueAt(tmps[0], i, 0);
			tbmodel.setValueAt(tmps[1], i, 1);
			tbmodel.setValueAt(tmps[2], i, 2);
			i++;
			//tbmodel.addRow(new Object[]{tmps[0],tmps[1],tmps[2]});
		}
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	
	//鼠标事件监听器
		class MyMouseListener implements MouseListener
		{
			/*
			 * modifeid by zhr
			 * */
				public void mouseClicked(MouseEvent e) 
				{
					if(e.getSource() == conceal )//上传图片
					{
						log.debug("上传图片");
						//PicFilefilter filter = new PicFilefilter();
						//JFileChooser fc = new JFileChooser();
						
//					    fc =  new JFileChooser();
//					    path =  new File[]{};
					    
						fc.setMultiSelectionEnabled(true);
						fc.setDialogTitle("请选择上传的图片");
						fc.showOpenDialog(MyAddDirInfoDialog.this);

						
				       //File path = fc.getSelectedFile();
					    path = fc.getSelectedFiles();
//				        if (path == null)
//				        {
//				        	return;
//				        }
//				        
//				        String name = path.getName();

					    if (path == null || path.length== 0)
				        {
				        	return;
				        }	
					    for (File file : path)
					    {
					    	String name = file.getName();
					        log.debug(name);
							if( name.endsWith(".png") || name.endsWith(".bmp") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".PNG") || name.endsWith(".BMP") || name.endsWith(".JPG") || name.endsWith(".JPEG"))
							{
								String clientPath = file.getAbsolutePath();
//								new Thread(){
//									public void run(){
//										try{
											log.debug("上传图片");
											WorkingJob.addjob();
										    UpDownFile.uploadDirPic(clientPath, serverPath);
											WorkingJob.deljob();
//										}catch(Exception e){
//											e.printStackTrace();
//										}
//										
//									}
//								}.start();						       
						        String dir_path = MyDirHandle.getDirPicPath(serverPath);
								log.debug(dir_path);	
						        pic = dir_path.split(";");
								pic_sum = pic.length;
								pic_num = 0;
								for(String ee:pic)
								{
									System.out.println(ee);
								}
								if (!"".equals(dir_path))
								{
									String pic_name = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// 获得上传文件的文件名  
									String serverRootPath = "D:\\filemanage\\";
									String clientpath = MyPath.DirPicPath + "\\" + pic_name;
									UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
									ImageIcon tmpimage = new ImageIcon(clientpath);
									image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
							                Image.SCALE_DEFAULT)); 
									photo.setIcon(image); 
	
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "只支持*.png;*.jpg;*.bmp;*.jpeg;*.PNG;*.JPG;*.BMP;*.JPEG",null, JOptionPane.INFORMATION_MESSAGE);
							}   
					    	
					    }
//				        log.debug(name);
//						if( name.endsWith(".png") || name.endsWith(".bmp") || name.endsWith(".jpg") )
//						{
//							String clientPath = path.getAbsolutePath();
//					        UpDownFile.uploadDirPic(clientPath, serverPath);
//					        String dir_path = MyDirHandle.getDirPicPath(serverPath);
//							log.debug(dir_path);	
//					        pic = dir_path.split(";");
//							pic_sum = pic.length;
//							pic_num = 0;
//							for(String ee:pic)
//							{
//								System.out.println(ee);
//							}
//							if (!"".equals(dir_path))
//							{
//								String pic_name = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// 获得上传文件的文件名  
//								String serverRootPath = "D:\\filemanage\\";
//								String clientpath = MyPath.DirPicPath + "\\" + pic_name;
//								UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
//								ImageIcon tmpimage = new ImageIcon(clientpath);
//								image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
//						                Image.SCALE_DEFAULT)); 
//								photo.setIcon(image); 
//
//							}
//						}
//						else
//						{
//							JOptionPane.showMessageDialog(null, "只支持*.png;*.jpg;*.bmp",null, JOptionPane.INFORMATION_MESSAGE);
//						}   
					}
					else if (e.getSource() == pre)
					{
						if(pic_num > 0) 
						{
							pic_num--;
							String picname = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// 获得上传文件的文件名  
							String serverRootPath = "D:\\filemanage\\";
							String clientpath = MyPath.DirPicPath + "\\" + picname;
							//下载到本地
							UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
							ImageIcon tmpimage = new ImageIcon(clientpath);
							image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
					                Image.SCALE_DEFAULT)); 
							photo.setIcon(image); 
						}
					}
					else if (e.getSource() == next)
					{
						if (pic_num < pic_sum-1) 
						{
							pic_num++;
							String picname = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// 获得上传文件的文件名  
							String serverRootPath = "D:\\filemanage\\";
							String clientpath = MyPath.DirPicPath + "\\" + picname;
							//下载到本地
							UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
							ImageIcon tmpimage = new ImageIcon(clientpath);
							image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
					                Image.SCALE_DEFAULT)); 
							photo.setIcon(image); 
						}
					}
					else if (e.getSource() == del)
					{
						UpDownFile.delDirPic(pic[pic_num]);
						String dir_path = MyDirHandle.getDirPicPath(serverPath);
						log.debug(dir_path);					
						pic = dir_path.split(";");
						pic_sum = pic.length;
						pic_num = 0;
						for(String ee:pic)
						{
							System.out.println(ee);
						}
						if (!"".equals(dir_path))
						{
							String pic_name = pic[pic_num].substring(pic[pic_num].lastIndexOf("\\") + 1);// 获得上传文件的文件名  
							String serverRootPath = "D:\\filemanage\\";
							String clientpath = MyPath.DirPicPath + "\\" + pic_name;
							UpDownFile.downloadDirPic(pic[pic_num].substring(serverRootPath.length(), pic[pic_num].length()), clientpath);
							ImageIcon tmpimage = new ImageIcon(clientpath);
							image = new ImageIcon(tmpimage.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
					                Image.SCALE_DEFAULT)); 
							photo.setIcon(image); 
						}
						else
						{
							photo.setIcon(null);
						}
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO 自动生成的方法存根
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO 自动生成的方法存根
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO 自动生成的方法存根
					
				}    

				@Override   
				public void mouseExited(MouseEvent e) {
					// TODO 自动生成的方法存根
					
				}
		}

		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button1)
		{
			String s = "";
			int sum = 0;
			if ((sum=textArea[0].getText().length()) > 200)
			{
				s += "设备概述超出字数限制("+sum+"/200),";
			}
			if ((sum=textArea[1].getText().length()) > 200)
			{
				s += "技术特点超出字数限制("+sum+"/200),";
			}
			if ((sum=textArea[2].getText().length()) > 50)
			{
				s += "项目状态超出字数限制("+sum+"/50),";
			}
			if ((sum=textArea[3].getText().length()) > 50)
			{
				s += "潜在客户超出字数限制("+sum+"/50),";
			}
			if (!"".equals(s))
			{
				JOptionPane.showMessageDialog(null, s);
				return;
			}
			String res = MyDirHandle.addDirInfo(textArea[0].getText(), textArea[1].getText(), 
					textArea[2].getText(), textArea[3].getText(),   serverPath);
			log.debug(res);
			if (res.equals("yes"))
			{
				JOptionPane.showMessageDialog(null, "信息上传成功",null, JOptionPane.INFORMATION_MESSAGE);
			}
			else if (res.equals("no"))
			{
				JOptionPane.showMessageDialog(null, "信息上传失败",null, JOptionPane.INFORMATION_MESSAGE);

			}
			else if (res.equals("dirnoexist"))
			{
				JOptionPane.showMessageDialog(null, "该文件夹不存在",null, JOptionPane.INFORMATION_MESSAGE);
			}
			String applystring = "";
			String Dirname = serverPath;
			String powersattion;
			String starttime;
			String protecttime;
			for (int i = 0; i < tbmodel.getRowCount(); i++)
			{
				powersattion = (String) tbmodel.getValueAt(i, 0);
				if(powersattion.equals("")) continue;
				starttime = (String) tbmodel.getValueAt(i, 1);
				protecttime = (String) tbmodel.getValueAt(i, 2);
				applystring += powersattion + "," + starttime +"," + protecttime +"\t";
				//MyDirHandle.addApply(Dirname, powersattion, starttime, protecttime);
			}
			MyDirHandle.addApply(Dirname, applystring);
			this.dispose();
			
		}
		else if (e.getSource() == button2)
		{
			this.dispose();
		}
		else if (e.getSource() == conceal)
		{
			
		}
	}
}