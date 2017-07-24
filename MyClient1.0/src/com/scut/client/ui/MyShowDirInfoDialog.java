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
	
	//�������
	JPanel mainPanel;
	JScrollPane[] scrollPane = new JScrollPane[5];
	JTextArea[] textArea = new JTextArea[5];
	JLabel[] label = new JLabel[6];
	JLabel photo;
	JLabel conceal;
	ImageIcon image;
	String[] labelCaption = {"��������:", "�����ص�:", "��Ƭ��3Dͼ:",
			"Ӧ�ó���:", "Ǳ�ڿͻ�:", "��Ŀ״̬:"};
	
	
	NormalFileInfoDialog normalFileInfoDialog;                       //�ı��Ӵ���
	PhotoFileInfoDialog photoFileInfoDialog;                         //ͼƬ�Ӵ���
	
	String s;
	String[] pic;    //Ŀ¼�����Ϣ���ַ���
	String serverPath;
	int pic_sum;
	int pic_num;
	private static Logger log = Logger.getLogger("client");

	
	//�Ӵ��ڴ���ѡ����
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
		setTitle("�鿴Ŀ¼�����Ϣ");
		setLayout(null);            //�޷ֲ�
		setResizable(false);        //���ڲ��ɵ�
		
		//���������
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 600, 600);
		
		//��ǩ�Ĵ����ͼ������
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
		
		//�ı���Ĵ����ͼ������
		for(int i = 0; i < 5; i++)
		{
			textArea[i] = new JTextArea();
			textArea[i].setLineWrap(true);
			//textArea[i].setEnabled(false);                               //���ɱ༭
			textArea[i].addMouseListener(new MyMouseListener());         //��ӵ���¼�
			scrollPane[i] = new JScrollPane(textArea[i]);                //������������
			scrollPane[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane[i].setCursor(new Cursor(Cursor.HAND_CURSOR));     //���������״
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
		
		//����ͼ�Ĵ����ͼ������
		photo = new JLabel("ͼƬ", JLabel.CENTER);
		photo.setBackground(new Color(255, 255, 224));
		photo.setOpaque(true);                                           //����͸�� 
		photo.setBorder(new LineBorder(Color.BLACK, 2));
		photo.setBounds(90, 190, 190, 150);
		photo.setCursor(new Cursor(Cursor.HAND_CURSOR));                 //���������״
		photo.addMouseListener(new MyMouseListener());                   //��ӵ���¼�

		//image.setImage(image.getImage().getScaledInstance(110, 80, Image.SCALE_DEFAULT));//�޸�ͼƬ��С
		//photo.setIcon(image);                                            //����ͼƬ
		mainPanel.add(photo);
		
		//��ʾ���ذ�ť�Ĵ����ͼ������
		conceal = new JLabel("<HTML><U>��ʾ</U></HTML>");                //�����»���
		conceal.setForeground(Color.blue);
		conceal.setBounds(290, 320, 40, 20);
		conceal.setCursor(new Cursor(Cursor.HAND_CURSOR));               //���������״
		conceal.addMouseListener(new MyMouseListener());                 //��ӵ���¼�
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
	
	
	//����¼�������
	class MyMouseListener implements MouseListener
	{
			public void mouseClicked(MouseEvent e) 
			{
				if(e.getSource() == conceal && e.getButton() == e.BUTTON1) //���ذ�ť��������Ӧ
				{
					if(photo.getIcon() == null)
					{
						log.debug("pic_sum:" + pic_sum);
						if (pic_sum == 1) 
						{
							JOptionPane.showMessageDialog(null, "û�п���ʾ�����ͼƬ",null, JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						String clientpath = MyPath.DirPicPath + "\\" + pic[pic_num];
						//���ص�����
						UpDownFile.downloadDirPic(pic[pic_num], clientpath);
						image = new ImageIcon(clientpath);
						photo.setIcon(image);
						conceal.setText("<HTML><U>����</U></HTML>");     //�����»���
					}
					else
					{
						photo.setIcon(null);
						conceal.setText("<HTML><U>��ʾ</U></HTML>");     //�����»���
					}
				}
				if(e.getClickCount() == 2 && e.getButton() == e.BUTTON1) //���˫����Ӧ
				{
					//�½���Ӧ�Ӵ���
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
				// TODO �Զ����ɵķ������
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}    

			@Override   
			public void mouseExited(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
	}
	

}



class PhotoFileInfoDialog extends JDialog                         //ͼƬ�Ӵ�����
{
	LargePhoto largePhoto;                                        //����ͼ����
	
	//ͼƬ�Ӵ������
	JPanel mainPanel;
	JLabel photo;
	JLabel previous, next;
	ImageIcon image;
	String serverPath; 
	String[] pic;    //Ŀ¼�����Ϣ���ַ���
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
		setTitle("���ͼƬ");   //���ø��๹�캯��������ģ̬��
        setLayout(null);                //�޷ֲ�
		setResizable(false);            //���ڲ��ɵ�
		
		//���������
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 320, 280);
		
		//ͼƬ�л���ť�Ĵ����ͼ������
		previous = new JLabel("<HTML><U>��һ��</U></HTML>");       //�����»���
		previous.setBounds(10, 110, 60, 20);
		previous.setForeground(Color.blue);
		previous.setCursor(new Cursor(Cursor.HAND_CURSOR));        //���������״
		previous.addMouseListener(new MyMouseListener());          //��ӵ���¼�
		next = new JLabel("<HTML><U>��һ��</U></HTML>");           //�����»���
	    next.setBounds(260, 110, 60, 20);
	    next.setForeground(Color.blue);
	    next.setCursor(new Cursor(Cursor.HAND_CURSOR));            //���������״
	    next.addMouseListener(new MyMouseListener());              //��ӵ���¼�
		mainPanel.add(previous);
		mainPanel.add(next);
		
		//ͼƬ��Ĵ����ͼ������
		photo = new JLabel("ͼƬ", JLabel.CENTER);                  //ͼƬ��С����Ӧ��label����\
		//////////////////////////////////////////////////////////////////////////////
//		{
//			protected void paintComponent(Graphics graph) 
//			{  
//	            graph.drawImage(image.getImage(), 0, 0, getWidth(),getHeight(),  
//	    	            image.getImageObserver());  
//	        }  
//		};
		
		photo.setBackground(new Color(255, 255, 224));
		photo.setOpaque(true);                                      //����͸��
		photo.setBorder(new LineBorder(Color.BLACK, 2));
		photo.setBounds(60, 20, 190, 200);
		photo.setCursor(new Cursor(Cursor.HAND_CURSOR));            //���������״
		photo.addMouseListener(new MyMouseListener());              //��ӵ���¼�
		this.image = new ImageIcon(image.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(),  
                Image.SCALE_DEFAULT)); 
		photo.setIcon(this.image);
		mainPanel.add(photo);
		
		getContentPane().add(mainPanel);
		
		setBounds(640, 250, 320, 280);
		
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);                 //�˳�ģʽ-�رյ�ǰ����
	}
	
	//��������ͼ���ں���
	public void creatDialog()
	{
		largePhoto = new LargePhoto(this, image);                          //�½�����ͼ����
	}
	
	//����¼�������
	class MyMouseListener implements MouseListener
	{
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == photo && e.getButton() == e.BUTTON1)   //ͼƬ�����Ӧ
			{
				creatDialog();
			}
			
			//ͼƬ�л���ť�����Ӧ����һ��
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
						//���ص�����
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
			//��һ��
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
						//���ص�����
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
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}
		
	}
}




class NormalFileInfoDialog extends JDialog                         //�ı��Ӵ�����
{
	//�ı��Ӵ������
	JPanel mainPanel;
	JScrollPane scrollPane;
	JTextArea textArea;
	JLabel label;
	String[] labelCaption = {"��������:", "�����ص�:", 
			"Ӧ�ó���:", "Ǳ�ڿͻ�:", "״̬��Ŀ:"};
	String serverPath;
	
	private static Logger log = Logger.getLogger("client");
	public NormalFileInfoDialog(MyShowDirInfoDialog fileInfoFrame, int i, String text)   //���ع��캯����ȡ��Ӧ���ڱ��
	{
		super(fileInfoFrame, "����ĵ���Ϣ", true);       //���ø��๹�캯��������ģ̬��
        setLayout(null);                //�޷ֲ�
		setResizable(false);            //���ڲ��ɵ�
		
		//���������
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 300, 250);
		
		//��ǩ�Ĵ����ͼ������
		label = new JLabel(labelCaption[i]);
		label.setBounds(20, 30, 60, 20);
		mainPanel.add(label);
		
		//�ı���Ĵ����ͼ������
		textArea = new JTextArea();
		textArea.setEditable(false);;                             //���ɱ༭
		textArea.setLineWrap(true);                               //�Զ�����
		textArea.setText(text);                                   //д����Ϣ
		scrollPane = new JScrollPane(textArea);                   //������������
		scrollPane.setBounds(90, 30, 150, 120);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(scrollPane);
		
		getContentPane().add(mainPanel);
		
		setBounds(650, 250, 300, 250);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);               //�˳�ģʽ-�رյ�ǰ����
	}
}


class LargePhoto extends JDialog                                   //����ͼ������
{
	int X, Y;     //�϶�ͼƬʱ���ĳ�ʼ����
	int left, top, width, height;    //ͼƬ�Ķ�̬λ�ã���С����
	int originalleft, originaltop, originalwidth, originalheight;  //ͼƬ��ԭʼλ�ã���С����
	
	//����ͼ�������
	JLabel photo;
	ImageIcon image;
	
	public LargePhoto(JDialog owner, ImageIcon image1)
	{
		super(owner, "", true);    //���ø��๹�캯��������ģ̬��
		setLayout(null);           //�޷ֲ�
		setUndecorated(true);      //�ޱ����� 
		setBackground(new Color(0, 0, 0, 80));   //���ñ���͸����
		
		//ͼƬ������ʼ��
		image = image1;
		width = image.getIconWidth();            //��ȡͼƬ��С
		height = image.getIconHeight();
		if(width > height)                       //��ͼƬ������ʼ��
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
		final int x = left*2;                    //��ȡ�����հ׳���
		final int y = top*2;
		
		//ԭʼ������ֵ
		originalleft = left;
		originaltop = top;
		originalwidth = width;
		originalheight = height;
		
		//ͼƬ��Ĵ����ͼ������
		photo = new JLabel()                     //ͼƬ��С����Ӧ��label����
		{
			protected void paintComponent(Graphics graph) {  
				image = new ImageIcon();
				image = image1;
	            graph.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(),  
	    	            image.getImageObserver());  
	            }  
		};
		photo.setBounds(left, top, width, height);                   //����ͼƬ�Ĵ�С����label��С
		photo.addMouseListener(new MyMouseWheelListener());          //�������¼�������
		photo.addMouseMotionListener(new MyMouseWheelListener());    //����������¼�������

		addMouseListener(new MyMouseWheelListener());                //������������¼�������
		addMouseWheelListener(new MyMouseWheelListener());           //������������϶��¼�������
		
		getContentPane().add(photo);
		
		setBounds(350, 20, 700, 700);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);                  //�˳�ģʽ-�رյ�ǰ����
	}
	
	public class MyMouseWheelListener implements MouseListener, MouseMotionListener,
	MouseWheelListener                                               //����¼�������
	{
		public void mouseClicked(MouseEvent e) {                     //ͼƬ�����Ӧ
			if(e.getButton() == e.BUTTON1)
			{
				dispose();                                           //�����رմ���
			}
		}

		public void mousePressed(MouseEvent e) {                     //��갴����Ӧ
			if(width > 700 || height > 700)
			{
			    photo.setCursor(new Cursor(Cursor.MOVE_CURSOR));     //���������״
			    X = e.getX();                                        //��ȡ���Ĵ�������
			    Y = e.getY();
			}
		}

		public void mouseReleased(MouseEvent e) {                    //����ɿ���Ӧ
			if(e.getButton() == e.BUTTON1)
			{
				photo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  //���������״
			}
		}
		
		public void mouseDragged(MouseEvent e) {                     //����϶���Ӧ
			if(width > 700)                                          //X�᷽��
			{
				left = e.getXOnScreen() - 350 - X;                   //��ȡX������ƶ�����           
				
				//ͼƬX�᷽����粻���϶�
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
				
				//ˢ��ͼƬλ��
				photo.setBounds(left, top, width, height);
			}
			if(height > 700)                                         //X�᷽��
			{
				top = e.getYOnScreen() - 20 - Y;                     //��ȡY������ƶ����� 
				
				//ͼƬY�᷽����粻���϶�
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
				
				//ˢ��ͼƬλ��
				photo.setBounds(left, top, width, height);
			}
		}
		
		public void mouseWheelMoved(MouseWheelEvent e) {              //�����ֹ�����Ӧ
			if(e.getWheelRotation() == 1)                             //�����¹�
			{
				left -= 10;
				top -= 10 * originalheight / originalwidth;           //��ͼƬ�����ƶ�
				width += 20;
				height += 20 * originalheight / originalwidth;        //��ͼƬ�����Ŵ�
				photo.setBounds(left, top, width, height);
			}
			if(e.getWheelRotation() == -1 && width > originalwidth / 2) //�����Ϲ�������ͼƬ��С��СΪ1/2
			{
				//ͼƬ��С��λ
				if(width > originalwidth)                                //ͼƬ�Ŵ�����С���
				{
					int times = (originalwidth - width) / 20;            //���ԭ����С������С����
					left += (left - originalleft) / times;               //����ͼƬ��С�ƻس�ʼλ��
					top += (top - originaltop) / times;
				}
				else                                                     //ͼƬδ�Ŵ����С���
				{
					left += 10;
					top += 10 * originalheight / originalwidth;
				}
				width -= 20;
				height -= 20 * originalheight / originalwidth;           //��ͼƬ������С
				photo.setBounds(left, top, width, height);
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}

	}
}

//class LargePhoto extends JDialog                                   //����ͼ������
//{
//	int X, Y;     //�϶�ͼƬʱ���ĳ�ʼ����
//	int left, top, width, length;    //ͼƬ��λ�ã���С����
//	
//	//����ͼ�������
//	JLabel photo;
//	ImageIcon image;
//	
//	public LargePhoto(JDialog owner, ImageIcon image1)
//	{
//		super(owner, "", true);    //���ø��๹�캯��������ģ̬��
//		setLayout(null);           //�޷ֲ�
//		setUndecorated(true);      //�ޱ����� 
//		setBackground(new Color(0, 0, 0, 80));   //���ñ���͸����
//	
//		photo = new JLabel()                     //ͼƬ��С����Ӧ��label����
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
//		//ͼƬ������ʼ��
//		left = 0;
//		top = 0;
//		width = 700;
//		length = 700;
//		
//		//ͼƬ��Ĵ����ͼ������
//		photo.setBounds(left, top, width, length);
//		image = new ImageIcon();
//		photo.setIcon(image);
//		photo.addMouseListener(new MyMouseWheelListener());          //�������¼�������
//		photo.addMouseMotionListener(new MyMouseWheelListener());    //����������¼�������
//
//		addMouseListener(new MyMouseWheelListener());                //������������¼�������
//		addMouseMotionListener(new MyMouseWheelListener());           //������������϶��¼�������
//		
//		getContentPane().add(photo);
//		
//		setBounds(350, 20, 700, 700);
//		setVisible(true);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);                  //�˳�ģʽ-�رյ�ǰ����
//	}
//	
//	class MyMouseWheelListener implements MouseListener, MouseMotionListener,
//	MouseWheelListener                                               //����¼�������
//	{
//		public void mouseClicked(MouseEvent e) {                     //ͼƬ�����Ӧ
//			if(e.getButton() == e.BUTTON1)
//			{
//				dispose();                                           //�����رմ���
//			}
//		}
//
//		public void mousePressed(MouseEvent e) {                     //��갴����Ӧ
//			if(width > 700)
//			{
//			    photo.setCursor(new Cursor(Cursor.MOVE_CURSOR));     //���������״
//			    X = e.getX();                                        //��ȡ���Ĵ�������
//			    Y = e.getY();
//			}
//		}
//
//		public void mouseReleased(MouseEvent e) {                    //����ɿ���Ӧ
//			if(e.getButton() == e.BUTTON1)
//			{
//				photo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  //���������״
//			}
//		}
//		
//		public void mouseDragged(MouseEvent e) {                     //����϶���Ӧ
//			if(width > 700)
//			{
//				//ˢ��ͼƬ����
//				left = e.getXOnScreen() - 350 - X;                       //��ȡ����ƶ�����           
//				top = e.getYOnScreen() - 20 - Y;
//				
//				//ͼƬ���粻���϶�
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
//				//ˢ��ͼƬλ��
//				photo.setBounds(left, top, width, length);
//			}
//		}
//		
//		public void mouseWheelMoved(MouseWheelEvent e) {              //�����ֹ�����Ӧ
//			if(width == 700)                                          //ͼƬ��λ
//			{
//				left = 0;
//				top = 0;
//				photo.setBounds(left, top, width, length);
//			}
//			if(e.getWheelRotation() == 1)                             //�����¹�
//			{
//				left -= 10;
//				top -= 10;
//				width += 20;
//				length += 20;
//				photo.setBounds(left, top, width, length);
//			}
//			if(e.getWheelRotation() == -1 && width > 400)             //�����Ϲ�������ͼƬ��С��С
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
//			// TODO �Զ����ɵķ������
//			
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO �Զ����ɵķ������
//			
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO �Զ����ɵķ������
//			
//		}
//
//	
//
//	}
//}