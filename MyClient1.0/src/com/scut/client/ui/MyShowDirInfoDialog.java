package com.scut.client.ui;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import com.scut.client.funtion.MyDirHandle;
import com.scut.client.funtion.UpDownFile;
import com.scut.client.user.MyPath;



public class MyShowDirInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String funtiondesc;
	String technicalFeature;
	String applystituation;
	String potentialcustomer;
	String projectstate;
	
	//窗口组件
	JPanel mainPanel;
	JScrollPane[] scrollPane = new JScrollPane[5];
	JTextArea[] textArea = new JTextArea[5];
	JLabel[] label = new JLabel[6];
	JLabel photo;
	JLabel conceal;
	ImageIcon image;
	String[] labelCaption = {"功能描述:", "技术特点:", "照片或3D图:",
			"应用场合:", "潜在客户:", "项目状态:"};
	
	
	NormalFileInfoDialog normalFileInfoDialog;                       //文本子窗口
	PhotoFileInfoDialog photoFileInfoDialog;                         //图片子窗口
	
	String s;
	String[] pic;    //目录相关信息的字符串
	String serverPath;
	int pic_sum;
	int pic_num;
	private static Logger log = Logger.getLogger("client");

	
	//子窗口创建选择函数
	public void creatDialog(int i)
	{
		switch(i)
		{
		    case 0 :
	    	{
	    		normalFileInfoDialog = new NormalFileInfoDialog(this, i, textArea[0].getText());
	    		break;
	    	}
		    case 1 :
	    	{
	    		normalFileInfoDialog = new NormalFileInfoDialog(this, i, textArea[1].getText());
	    		break;
	    	}
		    case 2 :
	    	{
	    		normalFileInfoDialog = new NormalFileInfoDialog(this, i, textArea[2].getText());
	    		break;
	    	}
		    case 3 :
	    	{
	    		normalFileInfoDialog = new NormalFileInfoDialog(this, i, textArea[3].getText());
	    		break;
	    	}
		    case 4 :
	    	{
	    		normalFileInfoDialog = new NormalFileInfoDialog(this, i, textArea[4].getText());
	    		break;
	    	}
		    case 5 :
	    	{
	    		photoFileInfoDialog = new PhotoFileInfoDialog(serverPath, image);
	    		break;
	    	}
		    default :
		}
	}

	/**
	 * Create the dialog.
	 */
	public MyShowDirInfoDialog(String serverpath, String res) 
	{
		this.serverPath = serverpath;
		this.s = res;
		setTitle("查看目录相关信息");
		setLayout(null);            //无分布
		setResizable(false);        //窗口不可调
		
		//创建主面板
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 600, 600);
		
		//标签的创建和加入面板
		for(int i = 0; i < 6; i++)
		{
			label[i] = new JLabel(labelCaption[i]);
		}
		label[0].setBounds(25, 20, 90, 20);
		label[1].setBounds(310, 20, 90, 20);
		label[2].setBounds(10, 190, 90, 20);
		label[3].setBounds(310, 190, 90, 20);
		label[4].setBounds(25, 360, 90, 20);
		label[5].setBounds(310, 360, 90, 20);
		for(int i = 0; i < 6; i++)
		{
			mainPanel.add(label[i]);
		}
		
		//文本域的创建和加入面板
		for(int i = 0; i < 5; i++)
		{
			textArea[i] = new JTextArea();
			textArea[i].setLineWrap(true);
			//textArea[i].setEnabled(false);                               //不可编辑
			textArea[i].addMouseListener(new MyMouseListener());         //添加点击事件
			scrollPane[i] = new JScrollPane(textArea[i]);                //加入滚动条面板
			scrollPane[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane[i].setCursor(new Cursor(Cursor.HAND_CURSOR));     //设置鼠标形状
		}
		scrollPane[0].setBounds(90, 20, 190, 150);
		scrollPane[1].setBounds(380, 20, 190, 150);
		scrollPane[2].setBounds(380, 190, 190, 150);
		scrollPane[3].setBounds(90, 360, 190, 150);
		scrollPane[4].setBounds(380, 360, 190, 150);
		for(int i = 0; i < 5; i++)
		{
			mainPanel.add(scrollPane[i]);
		}
		
		//缩略图的创建和加入面板
		photo = new JLabel("图片", JLabel.CENTER);
		photo.setBackground(new Color(255, 255, 224));
		photo.setOpaque(true);                                           //背景透明 
		photo.setBorder(new LineBorder(Color.BLACK, 2));
		photo.setBounds(90, 190, 190, 150);
		photo.setCursor(new Cursor(Cursor.HAND_CURSOR));                 //设置鼠标形状
		photo.addMouseListener(new MyMouseListener());                   //添加点击事件

		//image.setImage(image.getImage().getScaledInstance(110, 80, Image.SCALE_DEFAULT));//修改图片大小
		//photo.setIcon(image);                                            //插入图片
		mainPanel.add(photo);
		
		//显示隐藏按钮的创建和加入面板
		conceal = new JLabel("<HTML><U>显示</U></HTML>");                //设置下划线
		conceal.setForeground(Color.blue);
		conceal.setBounds(290, 320, 40, 20);
		conceal.setCursor(new Cursor(Cursor.HAND_CURSOR));               //设置鼠标形状
		conceal.addMouseListener(new MyMouseListener());                 //添加点击事件
		mainPanel.add(conceal);
		
		getContentPane().add(mainPanel);
		
		setBounds(600, 200, 600, 600);
		
		
		String info[] = {"","","","","","","","",""};
		String tmp[] = s.split(",");
		for(int i = 0; i < tmp.length; i++)
		{
			info[i] = tmp[i];
		}
		for(int i = 0; i < 5; i++)
		{
			textArea[i].setText(info[i]);
			textArea[i].setEditable(false);
		}
		
		String s = MyDirHandle.getDirPicPath(serverPath);
		log.debug(s);
	    pic = s.split(",");
		pic_sum = pic.length;
		pic_num = 1;
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	
	//鼠标事件监听器
	class MyMouseListener implements MouseListener
	{
			public void mouseClicked(MouseEvent e) 
			{
				if(e.getSource() == conceal && e.getButton() == e.BUTTON1) //隐藏按钮左键点击响应
				{
					if(photo.getIcon() == null)
					{
						log.debug("pic_sum:" + pic_sum);
						if (pic_sum == 1) 
						{
							JOptionPane.showMessageDialog(null, "没有可显示的相关图片",null, JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						String clientpath = MyPath.DirPicPath + "\\" + pic[pic_num];
						//下载到本地
						UpDownFile.downloadDirPic(pic[pic_num], clientpath);
						image = new ImageIcon(clientpath);
						photo.setIcon(image);
						conceal.setText("<HTML><U>隐藏</U></HTML>");     //设置下划线
					}
					else
					{
						photo.setIcon(null);
						conceal.setText("<HTML><U>显示</U></HTML>");     //设置下划线
					}
				}
				if(e.getClickCount() == 2 && e.getButton() == e.BUTTON1) //左键双击响应
				{
					//新建对应子窗口
					if(e.getSource() == photo)
					{
						creatDialog(5);
					}
					if(e.getSource() == textArea[0])
					{
						creatDialog(0);
					}
					if(e.getSource() == textArea[1])
					{
						creatDialog(1);
					}
					if(e.getSource() == textArea[2])
					{
						creatDialog(2);
					}
					if(e.getSource() == textArea[3])
					{
						creatDialog(3);
					}
					if(e.getSource() == textArea[4])
					{
						creatDialog(4);
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
	

}



class PhotoFileInfoDialog extends JDialog                         //图片子窗口类
{
	LargePhoto largePhoto;                                        //缩放图窗口
	
	//图片子窗口组件
	JPanel mainPanel;
	JLabel photo;
	JLabel previous, next;
	ImageIcon image;
	String serverPath; 
	String[] pic;    //目录相关信息的字符串
	int pic_num;
	int pic_sum;
	
	private static Logger log = Logger.getLogger("client");
	

	public PhotoFileInfoDialog(String serverPath, ImageIcon image)
	{
		this.serverPath = serverPath;
		this.image = image;
		
		String s = MyDirHandle.getDirPicPath(serverPath);
		log.debug(s);
	    pic = s.split(",");
		pic_sum = pic.length;
		pic_num = 1;
		setTitle("相关图片");   //调用父类构造函数，窗口模态化
        setLayout(null);                //无分布
		setResizable(false);            //窗口不可调
		
		//创建主面板
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 320, 280);
		
		//图片切换按钮的创建和加入面板
		previous = new JLabel("<HTML><U>上一张</U></HTML>");       //设置下划线
		previous.setBounds(10, 110, 60, 20);
		previous.setForeground(Color.blue);
		previous.setCursor(new Cursor(Cursor.HAND_CURSOR));        //设置鼠标形状
		previous.addMouseListener(new MyMouseListener());          //添加点击事件
		next = new JLabel("<HTML><U>下一张</U></HTML>");           //设置下划线
	    next.setBounds(260, 110, 60, 20);
	    next.setForeground(Color.blue);
	    next.setCursor(new Cursor(Cursor.HAND_CURSOR));            //设置鼠标形状
	    next.addMouseListener(new MyMouseListener());              //添加点击事件
		mainPanel.add(previous);
		mainPanel.add(next);
		
		//图片框的创建和加入面板
		photo = new JLabel("图片", JLabel.CENTER);                  //图片大小自适应的label定义\
		//////////////////////////////////////////////////////////////////////////////
//		{
//			protected void paintComponent(Graphics graph) 
//			{  
//	            graph.drawImage(image.getImage(), 0, 0, getWidth(),getHeight(),  
//	    	            image.getImageObserver());  
//	        }  
//		};
		
		photo.setBackground(new Color(255, 255, 224));
		photo.setOpaque(true);                                      //背景透明
		photo.setBorder(new LineBorder(Color.BLACK, 2));
		photo.setBounds(60, 20, 190, 200);
		photo.setCursor(new Cursor(Cursor.HAND_CURSOR));            //设置鼠标形状
		photo.addMouseListener(new MyMouseListener());              //添加点击事件
		this.image = new ImageIcon(image.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
                Image.SCALE_DEFAULT)); 
		photo.setIcon(this.image);
		mainPanel.add(photo);
		
		getContentPane().add(mainPanel);
		
		setBounds(640, 250, 320, 280);
		
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);                 //退出模式-关闭当前窗口
	}
	
	//创建缩放图窗口函数
	public void creatDialog()
	{
		largePhoto = new LargePhoto(this, image);                          //新建缩放图窗口
	}
	
	//鼠标事件监听器
	class MyMouseListener implements MouseListener
	{
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == photo && e.getButton() == e.BUTTON1)   //图片左键响应
			{
				creatDialog();
			}
			
			//图片切换按钮左键响应，上一张
			if(e.getSource() == previous && e.getButton() == e.BUTTON1)
			{
				new Thread(new Runnable() 
				{
		            @Override
		            public void run() 
		            {
		            	System.out.println("last");
						pic_num = (pic_num-1);
						if (pic_num == 0) pic_num = 1;
						System.out.println(pic_num);
						System.out.println(pic[pic_num]);
						String clientpath = "D:\\tmp1\\" + pic[pic_num];
						//下载到本地
						UpDownFile.downloadDirPic(pic[pic_num], clientpath);
						image = new ImageIcon(clientpath);
						ImageIcon tmpimage = new ImageIcon(image.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
				                Image.SCALE_DEFAULT)); 
						photo.setIcon(tmpimage);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		               
		        }).start();
							
			}
			//下一张
			if(e.getSource() == next && e.getButton() == e.BUTTON1)
			{
				
				new Thread(new Runnable() 
				{
		            @Override
		            public void run() 
		            {
		            	System.out.println("next");
						pic_num = (pic_num+1);
						if (pic_num == pic_sum) pic_num = pic_sum-1;
						System.out.println(pic_num);
						System.out.println(pic[pic_num]);
						String clientpath = "D:\\tmp1\\" + pic[pic_num];
						//下载到本地
						UpDownFile.downloadDirPic(pic[pic_num], clientpath);
						image = new ImageIcon(clientpath);
						ImageIcon tmpimage = new ImageIcon(image.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
				                Image.SCALE_DEFAULT)); 
						photo.setIcon(tmpimage);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		               
		        }).start();
				
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
}




class NormalFileInfoDialog extends JDialog                         //文本子窗口类
{
	//文本子窗口组件
	JPanel mainPanel;
	JScrollPane scrollPane;
	JTextArea textArea;
	JLabel label;
	String[] labelCaption = {"功能描述:", "技术特点:", 
			"应用场合:", "潜在客户:", "状态项目:"};
	String serverPath;
	
	private static Logger log = Logger.getLogger("client");
	public NormalFileInfoDialog(MyShowDirInfoDialog fileInfoFrame, int i, String text)   //重载构造函数获取对应窗口编号
	{
		super(fileInfoFrame, "相关文档信息", true);       //调用父类构造函数，窗口模态化
        setLayout(null);                //无分布
		setResizable(false);            //窗口不可调
		
		//创建主面板
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 300, 250);
		
		//标签的创建和加入面板
		label = new JLabel(labelCaption[i]);
		label.setBounds(20, 30, 60, 20);
		mainPanel.add(label);
		
		//文本域的创建和加入面板
		textArea = new JTextArea();
		textArea.setEditable(false);;                             //不可编辑
		textArea.setLineWrap(true);                               //自动换行
		textArea.setText(text);                                   //写入信息
		scrollPane = new JScrollPane(textArea);                   //加入滚动条面板
		scrollPane.setBounds(90, 30, 150, 120);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(scrollPane);
		
		getContentPane().add(mainPanel);
		
		setBounds(650, 250, 300, 250);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);               //退出模式-关闭当前窗口
	}
}


class LargePhoto extends JDialog                                   //缩放图窗口类
{
	int X, Y;     //拖动图片时鼠标的初始坐标
	int left, top, width, height;    //图片的动态位置，大小参数
	int originalleft, originaltop, originalwidth, originalheight;  //图片的原始位置，大小参数
	
	//缩放图窗口组件
	JLabel photo;
	ImageIcon image;
	
	public LargePhoto(JDialog owner, ImageIcon image1)
	{
		super(owner, "", true);    //调用父类构造函数，窗口模态化
		setLayout(null);           //无分布
		setUndecorated(true);      //无标题栏 
		setBackground(new Color(0, 0, 0, 80));   //设置背景透明度
		
		//图片参数初始化
		image = image1;
		width = image.getIconWidth();            //获取图片大小
		height = image.getIconHeight();
		if(width > height)                       //按图片比例初始化
		{
			height = height * 700 / width;
			width = width * 700 / width;
			left = 0;
			top = (700 - height)/2;
		}
		else
		{
			width = width * 700 / height;
			height = height *700 / height;
			left = (700 - width)/2;
			top = 0;
		}
		final int x = left*2;                    //获取背景空白长度
		final int y = top*2;
		
		//原始参数赋值
		originalleft = left;
		originaltop = top;
		originalwidth = width;
		originalheight = height;
		
		//图片框的创建和加入面板
		photo = new JLabel()                     //图片大小自适应的label定义
		{
			protected void paintComponent(Graphics graph) {  
				image = new ImageIcon();
				image = image1;
	            graph.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(),  
	    	            image.getImageObserver());  
	            }  
		};
		photo.setBounds(left, top, width, height);                   //根据图片的大小设置label大小
		photo.addMouseListener(new MyMouseWheelListener());          //添加鼠标事件监听器
		photo.addMouseMotionListener(new MyMouseWheelListener());    //添加鼠标滚轮事件监听器

		addMouseListener(new MyMouseWheelListener());                //主面板添加鼠标事件监听器
		addMouseWheelListener(new MyMouseWheelListener());           //主面板添加鼠标拖动事件监听器
		
		getContentPane().add(photo);
		
		setBounds(350, 20, 700, 700);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);                  //退出模式-关闭当前窗口
	}
	
	public class MyMouseWheelListener implements MouseListener, MouseMotionListener,
	MouseWheelListener                                               //鼠标事件监听器
	{
		public void mouseClicked(MouseEvent e) {                     //图片左键响应
			if(e.getButton() == e.BUTTON1)
			{
				dispose();                                           //单击关闭窗口
			}
		}

		public void mousePressed(MouseEvent e) {                     //鼠标按下响应
			if(width > 700 || height > 700)
			{
			    photo.setCursor(new Cursor(Cursor.MOVE_CURSOR));     //设置鼠标形状
			    X = e.getX();                                        //获取鼠标的窗口坐标
			    Y = e.getY();
			}
		}

		public void mouseReleased(MouseEvent e) {                    //鼠标松开响应
			if(e.getButton() == e.BUTTON1)
			{
				photo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  //设置鼠标形状
			}
		}
		
		public void mouseDragged(MouseEvent e) {                     //鼠标拖动响应
			if(width > 700)                                          //X轴方向
			{
				left = e.getXOnScreen() - 350 - X;                   //获取X轴鼠标移动距离           
				
				//图片X轴方向过界不可拖动
				if(left > 0)
				{
					X += left;
					left = 0;
				}
				if(left + width < 700)
				{
					X -= 700 - left - width;
					left = 700 - width;
				}
				
				//刷新图片位置
				photo.setBounds(left, top, width, height);
			}
			if(height > 700)                                         //X轴方向
			{
				top = e.getYOnScreen() - 20 - Y;                     //获取Y轴鼠标移动距离 
				
				//图片Y轴方向过界不可拖动
				if(top > 0)
				{
					Y += top;
					top = 0;
				}
				if(top + height < 700)
				{
					Y -= 700 - top - height;
					top = 700 - height;
				}
				
				//刷新图片位置
				photo.setBounds(left, top, width, height);
			}
		}
		
		public void mouseWheelMoved(MouseWheelEvent e) {              //鼠标滚轮滚动响应
			if(e.getWheelRotation() == 1)                             //滚轮下滚
			{
				left -= 10;
				top -= 10 * originalheight / originalwidth;           //按图片比例移动
				width += 20;
				height += 20 * originalheight / originalwidth;        //按图片比例放大
				photo.setBounds(left, top, width, height);
			}
			if(e.getWheelRotation() == -1 && width > originalwidth / 2) //滚轮上滚，限制图片最小大小为1/2
			{
				//图片缩小归位
				if(width > originalwidth)                                //图片放大后的缩小情况
				{
					int times = (originalwidth - width) / 20;            //变回原来大小所需缩小次数
					left += (left - originalleft) / times;               //根据图片大小移回初始位置
					top += (top - originaltop) / times;
				}
				else                                                     //图片未放大的缩小情况
				{
					left += 10;
					top += 10 * originalheight / originalwidth;
				}
				width -= 20;
				height -= 20 * originalheight / originalwidth;           //按图片比例缩小
				photo.setBounds(left, top, width, height);
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
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
}

//class LargePhoto extends JDialog                                   //缩放图窗口类
//{
//	int X, Y;     //拖动图片时鼠标的初始坐标
//	int left, top, width, length;    //图片的位置，大小参数
//	
//	//缩放图窗口组件
//	JLabel photo;
//	ImageIcon image;
//	
//	public LargePhoto(JDialog owner, ImageIcon image1)
//	{
//		super(owner, "", true);    //调用父类构造函数，窗口模态化
//		setLayout(null);           //无分布
//		setUndecorated(true);      //无标题栏 
//		setBackground(new Color(0, 0, 0, 80));   //设置背景透明度
//	
//		photo = new JLabel()                     //图片大小自适应的label定义
//		{
//			protected void paintComponent(Graphics graph) 
//			{  
//				image = new ImageIcon();
//				image = image1;
//	            graph.drawImage(image.getImage(), 0, 0, getWidth(),getHeight(),  
//	    	            image.getImageObserver());  
//	        }  
//		};
//		
//		//图片参数初始化
//		left = 0;
//		top = 0;
//		width = 700;
//		length = 700;
//		
//		//图片框的创建和加入面板
//		photo.setBounds(left, top, width, length);
//		image = new ImageIcon();
//		photo.setIcon(image);
//		photo.addMouseListener(new MyMouseWheelListener());          //添加鼠标事件监听器
//		photo.addMouseMotionListener(new MyMouseWheelListener());    //添加鼠标滚轮事件监听器
//
//		addMouseListener(new MyMouseWheelListener());                //主面板添加鼠标事件监听器
//		addMouseMotionListener(new MyMouseWheelListener());           //主面板添加鼠标拖动事件监听器
//		
//		getContentPane().add(photo);
//		
//		setBounds(350, 20, 700, 700);
//		setVisible(true);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);                  //退出模式-关闭当前窗口
//	}
//	
//	class MyMouseWheelListener implements MouseListener, MouseMotionListener,
//	MouseWheelListener                                               //鼠标事件监听器
//	{
//		public void mouseClicked(MouseEvent e) {                     //图片左键响应
//			if(e.getButton() == e.BUTTON1)
//			{
//				dispose();                                           //单击关闭窗口
//			}
//		}
//
//		public void mousePressed(MouseEvent e) {                     //鼠标按下响应
//			if(width > 700)
//			{
//			    photo.setCursor(new Cursor(Cursor.MOVE_CURSOR));     //设置鼠标形状
//			    X = e.getX();                                        //获取鼠标的窗口坐标
//			    Y = e.getY();
//			}
//		}
//
//		public void mouseReleased(MouseEvent e) {                    //鼠标松开响应
//			if(e.getButton() == e.BUTTON1)
//			{
//				photo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  //设置鼠标形状
//			}
//		}
//		
//		public void mouseDragged(MouseEvent e) {                     //鼠标拖动响应
//			if(width > 700)
//			{
//				//刷新图片坐标
//				left = e.getXOnScreen() - 350 - X;                       //获取鼠标移动距离           
//				top = e.getYOnScreen() - 20 - Y;
//				
//				//图片过界不可拖动
//				if(left > 0)
//				{
//					X += left;
//					left = 0;
//				}
//				if(top > 0)
//				{
//					Y += top;
//					top = 0;
//				}
//				if(left + width < 700)
//				{
//					X -= 700 - left - width;
//					left = 700 - width;
//				}
//				if(top + length < 700)
//				{
//					Y -= 700 - top - length;
//					top = 700 - length;
//				}
//				
//				//刷新图片位置
//				photo.setBounds(left, top, width, length);
//			}
//		}
//		
//		public void mouseWheelMoved(MouseWheelEvent e) {              //鼠标滚轮滚动响应
//			if(width == 700)                                          //图片归位
//			{
//				left = 0;
//				top = 0;
//				photo.setBounds(left, top, width, length);
//			}
//			if(e.getWheelRotation() == 1)                             //滚轮下滚
//			{
//				left -= 10;
//				top -= 10;
//				width += 20;
//				length += 20;
//				photo.setBounds(left, top, width, length);
//			}
//			if(e.getWheelRotation() == -1 && width > 400)             //滚轮上滚，限制图片最小大小
//			{
//				left += 10;
//				top += 10;
//				width -= 20;
//				length -= 20;
//				photo.setBounds(left, top, width, length);
//			}
//		}
//
//		@Override
//		public void mouseMoved(MouseEvent e) {
//			// TODO 自动生成的方法存根
//			
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO 自动生成的方法存根
//			
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO 自动生成的方法存根
//			
//		}
//
//	
//
//	}
//}