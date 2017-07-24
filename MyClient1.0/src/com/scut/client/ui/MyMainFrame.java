package com.scut.client.ui;

import javax.swing.UIManager;



import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;

import com.scut.client.funtion.FileList;
import com.scut.client.funtion.IsFile;
import com.scut.client.funtion.MyDirHandle;
import com.scut.client.funtion.MyFileHandle;
import com.scut.client.funtion.MyLogin;
import com.scut.client.funtion.MyOpenClientFile;
import com.scut.client.funtion.MyScan;
import com.scut.client.funtion.RecycleHandle;
import com.scut.client.funtion.SendMessage;
import com.scut.client.funtion.SynHandle;
import com.scut.client.funtion.UpDownFile;
import com.scut.client.showpanel.MyAddUserPanel;
import com.scut.client.showpanel.MyClientFilePanel;
import com.scut.client.showpanel.MyDirSearchPanel;
import com.scut.client.showpanel.MyDirSearchPanelClient;
import com.scut.client.showpanel.MyFileInfoSearchDialog;
import com.scut.client.showpanel.MyFileSearchDialog;
import com.scut.client.showpanel.MyFileSearchDialogClient;
import com.scut.client.showpanel.MyFileSearchDialogClient1;
import com.scut.client.showpanel.MyFileSearchPanel;
import com.scut.client.showpanel.MyFileSearchPanelClient;
import com.scut.client.showpanel.MyFileSearchPanelClient1;
import com.scut.client.showpanel.MyFileVersionPanel;
import com.scut.client.showpanel.MyLookLogPanel;
import com.scut.client.showpanel.MyLookUserPanel;
import com.scut.client.showpanel.MyPersonalPanel;
import com.scut.client.showpanel.MyRecyclePanel;
import com.scut.client.showpanel.MySearchFileInfoDialog;
import com.scut.client.showpanel.MySetLogDatePanel;
import com.scut.client.showpanel.MyShowClientFilePanel;
import com.scut.client.showpanel.MyShowFilePanel;
import com.scut.client.user.MyPath;
import com.scut.client.user.MyUser;
import com.scut.client.user.WorkingJob;
import com.scut.tools.HistoryIo;
import com.scut.tools.LocalDirIo;
import com.scut.tools.LocalFileIo;
import com.scut.tools.MyImage;
import com.scut.tools.MyLog;
import com.scut.tools.MyParameters;
import com.scut.tools.MyReadXml;
import com.scut.tools.MyTheme;
import com.scut.tools.MyUpdateUi;
import com.sun.star.logging.FileHandler;


import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/*
 * This is the client module 
 * @version 1.0 
 * @date    2016-12-19
 * @author  linjiaqin
 */

public class MyMainFrame implements ActionListener{

	private JFrame frame;
	//private JPanel upPanel;                          //��������panel
	private JToolBar upPanel; 
	private JPanel leftPanel;                        //��ߵ�panel�����treepanel��progressbar
	private JSplitPane mainPanel;                    //����showPanel�������tabpanel
	private JPanel showPanel;                       //�ұ߽���չʾ��panel
	private JPanel[] sonShowPanel;                       //showPanel��cardpane
	private JPanel[] sonPanel;                           //ÿ��showPanel��panel
	private CardLayout cardLayout; 
	

	
	private Box boxHor1 = Box.createHorizontalBox(); 
	//private Box boxHor2 = Box.createHorizontalBox(); //�����serverTree��clienttree,��Ϊtabpanel
	private Box boxVer = Box.createVerticalBox();    //��ֱ����
	
	private JSplitPane rowSplitPane = new JSplitPane();  //�����ұ�չʾ��mainpanel����ߵ�����tree
	private JPanel serverPanel;
	private JPanel clientPanel;                   //���ص�����Ŀ¼�������Ѿ�����
	private JPanel clientFilePanel;               //���ر����Ŀ¼��
	private MyTreeBar serverBarPanel;
	private MyTreeBar clientBarPanel;
	private MyTreeBar clientFileBarPanel;
	private JTabbedPane treePanel;
	
	private JTabbedPane tabPanel;
	private JPanel historyPanel;
	private JPanel workingPanel;
	private JTable historyTable;
	private JTable jobTable;
	public DefaultTableModel historyTableModel;
	public DefaultTableModel jobTableModel;
	
	private JButton connectButton;

	private JLabel hostField;//��������һ���޸ģ�ֻ����ʾ��
	private JLabel portField;
	private JLabel hostLabel;
	private JLabel portLabel;
	private JLabel userLabel;
	private JLabel userField;
	private JLabel stateLabel;
	private JLabel stateField;
	private JLabel privilegeLabel;
	private JLabel privilegeField;
	
//	private JButton userManage;
//	private JButton logManage;
//	private JButton fileManage;
//	private JButton personalCenter;
	
	public JProgressBar progressbar;
	private MyMenu myMenu;
	
	private MyClientPopMenu myClientPopMenu;
	private MyClientFilePopMenu myClientFilePopMenu;
	private MyServerPopMenu myServerPopMenu;
	private MyDirPopMenu myDirPopMenu;
	
	public static JTree serverTree;
	public static JTree clientTree;
	public static JTree clientFileTree;
	
	private DefaultMutableTreeNode serverRoots = new DefaultMutableTreeNode("doc");
	
	//private HttpFile httpFile;
	private static Logger log = Logger.getLogger("client");
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	static
	{
		//log = MyLog.getMyLogLogger(); 
		//�������Ϊ������
		//log = Logger.getLogger("client");
	}
	
	/**
	 * Create the application.
	 */
	public MyMainFrame() 
	{
		
		log.info("initialize");
		//httpFile = new HttpFile();
		initialize();
		cardLayout.show(showPanel, "imageshow");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(50, 50, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setTitle("�˵�����˼��˹����ܹ���ר�����ݿ����");
		frame.setIconImage(MyImage.frametitle.getImage());
		
		showPanel = new JPanel();    //��ʼ��showPanel
		//initShowPanel();
		
		initUpPanel();         //��ʼ��������
		initMenu();            //��ʼ���˵�
		
		//listClientDirectory();
		listClientFileDirectory();
		listServerDirectory();
		initTreePanel();       //��������������ſ�����ʼ��֮����Ϊ��������������
		
		initPopupMenu();       //��ʼ���Ҽ��˵�
		
		progressbar = new JProgressBar();
		progressbar.setStringPainted(true);
		progressbar.setMinimum(0);
		progressbar.setMaximum(99);
		progressbar.setBackground(Color.GREEN);
		//httpFile.getMyProgressBar(progressbar);
		
		initTabPanel();          //������historypane��jobpane
		
		initTable();             //��ʼ��historytable��jobtable
		
		initShowPanel();
		
		initMainPane();          //������showpane��tabpane
		
		initLeftPane();         //������ treepane��progressbar
		
		//rowPane������leftPane��mainPane��Ĭ����ˮƽ����
		rowSplitPane.setDividerLocation(0.5);
		rowSplitPane.setEnabled(true);
        rowSplitPane.setOneTouchExpandable(true);
        //rowSplitPane.add(boxHor2,JSplitPane.LEFT);
        rowSplitPane.add(leftPanel,JSplitPane.LEFT);
		rowSplitPane.add(mainPanel,JSplitPane.RIGHT);
		
		
		
		frame.getContentPane().add(upPanel,BorderLayout.NORTH);
		frame.getContentPane().add(rowSplitPane,BorderLayout.CENTER);
		
		//serverBarPanel.button.addActionListener(this);
		//clientBarPanel.button.addActionListener(this);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.addWindowListener(new MyHandleWin());  //��Ӽ�������������WindowEvent�¼�
		frame.setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE );

		
	}
	
	
	
	public void initShowPanel()
	{
		/*
		 * Modified by zhr
		 * */
		cardLayout = new CardLayout();
		sonShowPanel = new JPanel[18];
		sonPanel = new JPanel[18];
		showPanel.setLayout(cardLayout);
		
		for (int i = 2; i < 18; i++)
		{
			sonShowPanel[i] = new JPanel();
			showPanel.add(i+"", sonShowPanel[i]);
		}
		sonShowPanel[2].setLayout(new BorderLayout());
		JLabel title2 = new JLabel("����û�");
		sonShowPanel[2].add(title2, BorderLayout.NORTH);
		sonPanel[2] = new MyAddUserPanel();
		sonShowPanel[2].add(sonPanel[2], BorderLayout.CENTER);
		title2.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		
		sonShowPanel[3].setLayout(new BorderLayout());
		JLabel title3 = new JLabel("�鿴�û���Ϣ");
		sonShowPanel[3].add(title3, BorderLayout.NORTH);
		sonPanel[3] = new MyLookUserPanel();
		sonShowPanel[3].add(sonPanel[3], BorderLayout.CENTER);
		title3.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		sonShowPanel[4].setLayout(new BorderLayout());
		JLabel title4 = new JLabel("������־��������");
		sonShowPanel[4].add(title4, BorderLayout.NORTH);
		sonPanel[4] = new MySetLogDatePanel();
		sonShowPanel[4].add(sonPanel[4], BorderLayout.CENTER);
		title4.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		sonShowPanel[5].setLayout(new BorderLayout());
		JLabel title5 = new JLabel("�鿴��־��Ϣ");
		sonShowPanel[5].add(title5, BorderLayout.NORTH);
		sonPanel[5] = new MyLookLogPanel();
		sonShowPanel[5].add(sonPanel[5], BorderLayout.CENTER);
		title5.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		sonShowPanel[6].setLayout(new BorderLayout());
		JLabel title6 = new JLabel("Ŀ¼�ؼ��ּ������");
		sonShowPanel[6].add(title6, BorderLayout.NORTH);
		sonPanel[6] = new MyDirSearchPanel(historyTableModel, jobTableModel, progressbar);
		sonShowPanel[6].add(sonPanel[6], BorderLayout.CENTER);
		title6.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		sonShowPanel[7].setLayout(new BorderLayout());
		JLabel title7 = new JLabel("����վ");
		sonShowPanel[7].add(title7, BorderLayout.NORTH);
		sonPanel[7] = new MyRecyclePanel();
		sonShowPanel[7].add(sonPanel[7], BorderLayout.CENTER);
		title7.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		sonShowPanel[8].setLayout(new BorderLayout());
		JLabel title8 = new JLabel("�����ļ���Ϣ");
		sonShowPanel[8].add(title8, BorderLayout.NORTH);
		sonPanel[8] = new MyClientFilePanel();
		sonShowPanel[8].add(sonPanel[8], BorderLayout.CENTER);
		title8.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		sonShowPanel[9].setLayout(new BorderLayout());
		JLabel title9 = new JLabel("��ʷ�汾");
		sonShowPanel[9].add(title9, BorderLayout.NORTH);
		sonPanel[9] = new MyFileVersionPanel();
		sonShowPanel[9].add(sonPanel[9], BorderLayout.CENTER);
		title9.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		sonShowPanel[10].setLayout(new BorderLayout());
		JLabel title10 = new JLabel("��������");
		sonShowPanel[10].add(title10, BorderLayout.NORTH);
		sonPanel[10] = new MyPersonalPanel();
		sonShowPanel[10].add(sonPanel[10], BorderLayout.CENTER);
		title10.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		sonShowPanel[11].setLayout(new BorderLayout());
		JLabel title11 = new JLabel("��ʾ��������ǰĿ¼���ļ�");
		sonShowPanel[11].add(title11, BorderLayout.NORTH);
		sonPanel[11] = new MyShowFilePanel();
		sonShowPanel[11].add(sonPanel[11], BorderLayout.CENTER);
		title11.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		sonShowPanel[12].setLayout(new BorderLayout());
		JLabel title12 = new JLabel("�ļ����������");
		sonShowPanel[12].add(title12, BorderLayout.NORTH);
		sonPanel[12] = new MyFileSearchPanel(historyTableModel, jobTableModel, progressbar);
		sonShowPanel[12].add(sonPanel[12], BorderLayout.CENTER);
		title12.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		sonShowPanel[13].setLayout(new BorderLayout());
		JLabel title13 = new JLabel("��ʾ���ص�ǰĿ¼���ļ�");
		sonShowPanel[13].add(title13, BorderLayout.NORTH);
		sonPanel[13] = new MyShowClientFilePanel();
		sonShowPanel[13].add(sonPanel[13], BorderLayout.CENTER);
		title13.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������

		/*
		 * Modified by zhr
		 * */
		sonShowPanel[14].setLayout(new BorderLayout());
		sonShowPanel[14].add(new JLabel(""), BorderLayout.NORTH);
		sonPanel[14] = new MyPanel(MyImage.dalouImage);
		sonShowPanel[14].add(sonPanel[14],BorderLayout.CENTER);
		
		/////�����ʾ��ͨ�û�������panel
		sonShowPanel[15].setLayout(new BorderLayout());
		JLabel title15 = new JLabel("�ļ����������");
		sonShowPanel[15].add(title15, BorderLayout.NORTH);
		sonPanel[15] = new MyFileSearchPanelClient(historyTableModel, jobTableModel, progressbar);
		sonShowPanel[15].add(sonPanel[15], BorderLayout.CENTER);
		title15.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������	
		/////
		/////�����ʾ��ͨ�û�������panel
		sonShowPanel[16].setLayout(new BorderLayout());
		JLabel title16 = new JLabel("�ļ����������");
		sonShowPanel[16].add(title16, BorderLayout.NORTH);
		sonPanel[16] = new MyFileSearchPanelClient1(historyTableModel, jobTableModel, progressbar);
		sonShowPanel[16].add(sonPanel[16], BorderLayout.CENTER);
		title15.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		
		// �����ļ���/Ŀ¼�ؼ��ʼ���
		sonShowPanel[17].setLayout(new BorderLayout());
		JLabel title17 = new JLabel("�ļ���/Ŀ¼�ؼ��ʼ������");
		sonShowPanel[17].add(title17, BorderLayout.NORTH);
		sonPanel[17] = new MyDirSearchPanelClient(historyTableModel, jobTableModel, progressbar);
		sonShowPanel[17].add(sonPanel[17], BorderLayout.CENTER);
		title15.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		/////
		
		for (int i = 0; i < 12; i++)
		{
			
			if (i == 0) 
			{
				/*
				 * Modified by zhr
				 * */
				//sonShowPanel[i] = new MyPanel(MyImage.dalouImage);
				//showPanel.add("imageshow", sonShowPanel[i]);
				sonShowPanel[i] = new MyPanel();
				sonShowPanel[i].setLayout(new BorderLayout());
				sonShowPanel[i].add(new JLabel(""), BorderLayout.NORTH);
				sonPanel[i] = new MyLoginDialog();
				sonShowPanel[i].add(sonPanel[i], BorderLayout.CENTER);
				showPanel.add("imageshow", sonShowPanel[i]);

			}
			if (i == 1) 
			{
				sonShowPanel[i] = new MyPanel();
				sonShowPanel[i].setLayout(new BorderLayout());
				sonShowPanel[i].add(new JLabel(""), BorderLayout.CENTER);
				showPanel.add("documentshow", sonShowPanel[i]);			
			}
		}
		
	}
	
	public void initTreePanel()
	{
		treePanel =new JTabbedPane();
		//treePanel.add("�鿴����", clientPanel);
		treePanel.setFont(new Font("΢���ź�",Font.PLAIN,14));
		treePanel.add("������", serverPanel);
		treePanel.add("����", clientFilePanel);
		
	}
	
void initPopupMenu()
	{
//		myClientPopMenu = new MyClientPopMenu(); 
//		//myClientPopMenu.menuItem1.addActionListener(this);
//		myClientPopMenu.menuItem2.addActionListener(this);
//		myClientPopMenu.menuItem3.addActionListener(this);
//		myClientPopMenu.menuItem4.addActionListener(this);
//		myClientPopMenu.menuItem5.addActionListener(this);
		
		myServerPopMenu = new MyServerPopMenu();
		myServerPopMenu.menuItem1.addActionListener(this);
		myServerPopMenu.menuItem2.addActionListener(this);
		//myServerPopMenu.menuItem3.addActionListener(this);
		//myServerPopMenu.menuItem4.addActionListener(this);
		//myServerPopMenu.menuItem5.addActionListener(this);
		//myServerPopMenu.menuItem6.addActionListener(this);
		myServerPopMenu.menuItem7.addActionListener(this);
		myServerPopMenu.menuItem8.addActionListener(this);
		
		myDirPopMenu = new MyDirPopMenu();
		myDirPopMenu.menuItem1.addActionListener(this);
		myDirPopMenu.menuItem2.addActionListener(this);
		//myDirPopMenu.menuItem3.addActionListener(this);
		myDirPopMenu.menuItem4.addActionListener(this);
		myDirPopMenu.menuItem5.addActionListener(this);
		myDirPopMenu.menuItem6.addActionListener(this);
		myDirPopMenu.menuItem7.addActionListener(this);
		myDirPopMenu.menuItem8.addActionListener(this);
		myDirPopMenu.menuItem9.addActionListener(this);
		
		myClientFilePopMenu = new MyClientFilePopMenu();
		myClientFilePopMenu.menuItem1.addActionListener(this);
		//myClientFilePopMenu.menuItem2.addActionListener(this);
		serverTree.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) { 
				if (e.getButton() == MouseEvent.BUTTON3) 
				{  
					//�����Ҽ��˵�  
					log.debug("����Ҽ��˵�");
					String filename = serverBarPanel.textField.getText().trim();
					if (IsFile.isFile(filename))
					{
						myServerPopMenu.popupMenu.show(e.getComponent(), e.getX(), e.getY());  
					}
					else
					{
						myDirPopMenu.popupMenu.show(e.getComponent(), e.getX(), e.getY()); 
					}
				}
		    } 	
		});  
		
					
		clientFileTree.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {  
                if (e.isPopupTrigger()) { 
                	log.debug("����Ҽ��˵�clientFileTree");
                    myClientFilePopMenu.popupMenu.show(e.getComponent(), e.getX(), e.getY());     
                }  
            }              
		});	
		
	}
	
	/*
	 * ��ʼ��������
	 */
	private void initUpPanel()
	{
		connectButton = new JButton("����");
		connectButton.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		hostLabel = new JLabel("��������ַ��");
		hostLabel.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		portLabel = new JLabel("�˿ںţ�");
		portLabel.setFont(new Font("΢���ź�",Font.PLAIN,14));
		//hostField = new JTextField(20);
		//portField = new JTextField(5);
		
		hostField = new JLabel();
		portField = new JLabel();
		if(!MyParameters.getIppath().equals(""))
		{
			hostField.setText(MyParameters.getIppath() + "  ");
		}
		else hostField.setText(MyParameters.getIp() + "  ");
		//hostField.setText(MyParameters.getIppath() + "  ");
		portField.setText(MyParameters.getPort()+ "  ");
		hostField.setFont(new Font("΢���ź�",Font.PLAIN,14));
		portField.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		userLabel = new JLabel("��ǰ�û�:");
		userLabel.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		stateLabel = new JLabel("��ǰ״̬");
		stateLabel.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		userField = new JLabel("�ο�");
		userField.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		stateField = new JLabel("����");
		stateField.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		privilegeLabel = new JLabel("�û����");
		privilegeLabel.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		privilegeField = new JLabel("5");
		privilegeField.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		privilegeField.setVisible(false);
		privilegeLabel.setVisible(false);
		stateField.setVisible(false);
		stateLabel.setVisible(false);
		
		upPanel = new JToolBar();
		hostField.setToolTipText("��������ip");
		hostField.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		hostField.setBorder(BorderFactory.createEtchedBorder());
		portField.setBorder(BorderFactory.createEtchedBorder());
		userField.setBorder(BorderFactory.createEtchedBorder());
		stateField.setBorder(BorderFactory.createEtchedBorder());
		privilegeField.setBorder(BorderFactory.createEtchedBorder());
		
		upPanel.add(hostLabel);
		upPanel.add(hostField);
		
		upPanel.add(new JLabel("    "));
		
		upPanel.add(portLabel);
		upPanel.add(portField);
		
		upPanel.add(new JLabel("    "));
		upPanel.add(userLabel);
		upPanel.add(userField);
		
		upPanel.add(new JLabel("    "));
		upPanel.add(stateLabel);
		upPanel.add(stateField);
		
		upPanel.add(new JLabel("    "));
		upPanel.add(privilegeLabel);
		upPanel.add(privilegeField);
		
		//upPanel.add(connectButton);
	}
	
	/*
	 * ����tabpanel������
	 */
	private void initTabPanel()
	{
		tabPanel = new JTabbedPane();
		workingPanel = new JPanel(new BorderLayout());
		historyPanel = new JPanel(new BorderLayout());
		tabPanel.add("������ʷ", historyPanel);
		tabPanel.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		tabPanel.add("�����б�", workingPanel);
		tabPanel.setFont(new Font("΢���ź�",Font.PLAIN,14));
		tabPanel.setVisible(false);
	}
	
	public void initLeftPane()
	{
		leftPanel = new JPanel(new BorderLayout());
		leftPanel.add(treePanel, BorderLayout.CENTER);
		leftPanel.add(progressbar, BorderLayout.SOUTH);
	}
	public void initMainPane()
	{
		mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);   //��Ϊ��ֱ����
		
		mainPanel.setEnabled(true);
		mainPanel.setOneTouchExpandable(true);
		mainPanel.add(showPanel,JSplitPane.TOP);
		mainPanel.add(tabPanel,JSplitPane.BOTTOM);
	}
	/*
	 * ��historypanel��workingpanel�д���table
	 */
	private void initTable()
	{
		//String[] historyHeader = {"����", "�ļ���", "�ϴ�/����","�ļ�·��","����",""};
		//String[] historyHeader = {"����", "�˺�","�ļ���", "�ϴ�/����","�ļ�·��","����",""};
		String[] historyHeader = {"����", "�û��˺�","�ļ���", "�ϴ�/����","�ļ�·��","����"};
		//����Ҫ����ʲôʱ����رȽϺã���Ϊ�п����кܶ�����¼
		List<Object[]> list = HistoryIo.getObject();
    	Object[][] rawdata = new Object[list.size()][];
		int i =0 ;
    		for(Object[] e: list)
    		{
    			rawdata[i++] = e;
    		}
		
		historyTableModel = new DefaultTableModel(rawdata, historyHeader);
		historyTable= new JTable(historyTableModel);
		TableRowSorter<TableModel>sorter = new TableRowSorter<TableModel>(historyTable.getModel());
		historyTable.setRowSorter(sorter);
		
		/*
		 * ���ò�����ʷpanel���п�
		 * */
		historyTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		historyTable.getColumnModel().getColumn(0).setMaxWidth(200);
		
		historyTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		historyTable.getColumnModel().getColumn(1).setMaxWidth(150);
		
		historyTable.getColumnModel().getColumn(2).setPreferredWidth(350);
		historyTable.getColumnModel().getColumn(2).setMaxWidth(350);
		
		historyTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		historyTable.getColumnModel().getColumn(3).setMaxWidth(150);
		
		historyTable.getColumnModel().getColumn(4).setPreferredWidth(500);
		historyTable.getColumnModel().getColumn(4).setMaxWidth(500);
		
		historyTable.getColumnModel().getColumn(5).setPreferredWidth(150);
		historyTable.getColumnModel().getColumn(5).setMaxWidth(150);
		
		JTextField filterField = new JTextField(15); 
		JButton filterButton = new JButton("����");
		filterButton.setBorder(BorderFactory.createEtchedBorder());
		filterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = filterField.getText().toString();
				if (text.trim().length() == 0)
				{
					sorter.setRowFilter(null);
				}
				else
				{
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}
			}
		});
		
		//JPanel filterPanel = new JPanel(new FlowLayout());
		JToolBar filterPanel = new JToolBar();
		filterPanel.add(filterButton);
		filterPanel.add(filterField);
		historyPanel.add(filterPanel, BorderLayout.SOUTH);
		historyPanel.add(new JScrollPane(historyTable), BorderLayout.CENTER);
		
		
		historyTable.setRowHeight(20);//�����и�
		historyTable.setFont(new Font("΢���ź�",Font.PLAIN,12));//��������
		
		JTableHeader header =historyTable.getTableHeader();
		header.setFont(new Font("΢���ź�",Font.PLAIN,14));//��������
		//////////////////////////////////////////////////////////////////////////////////
		
		//String[] jobHeader = {"����", "�ļ���", "�ϴ�/����","�ļ�·��", "����"};
		String[] jobHeader = {"����", "�û��˺�","�ļ���", "�ϴ�/����","�ļ�·��", "����"};
		Object[][]  jobData = null;
		jobTableModel = new DefaultTableModel(jobData, jobHeader);
		jobTable = new JTable(jobTableModel);
		workingPanel.add(new JScrollPane(jobTable), BorderLayout.CENTER);   //����scrollPane����Ż���ʾ��ͷ
	}
	
	/*
	 * ��ʼ���˵���
	 */
	private void initMenu()
	{
		myMenu = new MyMenu(this);
		
		myMenu.loginItem.addActionListener(this);
		myMenu.quitItem.addActionListener(this);
		
		myMenu.setServerItem.addActionListener(this);
		myMenu.setUiItem.addActionListener(this);
		myMenu.setServerIpItem.addActionListener(this);
		myMenu.setSavePathItem.addActionListener(this);
	
		
		myMenu.helpItem.addActionListener(this);
		
		myMenu.lookUserItem.addActionListener(this);
		myMenu.addUserItem.addActionListener(this);
		myMenu.lookLogItem.addActionListener(this);
		myMenu.setLogDateItem.addActionListener(this);
		myMenu.filesearch.addActionListener(this);
		myMenu.fileinfosearch.addActionListener(this);
		
		myMenu.filesearchClient.addActionListener(this);
		myMenu.fileinfoSearchClient.addActionListener(this);
		
		myMenu.recycle.addActionListener(this);
		myMenu.local.addActionListener(this);
		myMenu.updatePwd.addActionListener(this);
		myMenu.history.addActionListener(this);
		myMenu.syn.addActionListener(this);
		MyMenu.localMessage.addActionListener(this);
		
		MyLoginDialog.button.addActionListener(this);
		MyLoginDialog.button_1.addActionListener(this);
	}
	
	public static  String getTreePath(TreeNode[] treenode)
	{
		String tmp = "";
         for (int i = 0; i < treenode.length-1; i++)
         {
         	 tmp += (treenode[i].toString() + "\\");
         }
         tmp += treenode[ treenode.length-1 ].toString();
		 return tmp;
	}
	
	/*
	 * ��ñ���ѡ�е��ļ�·��
	 */
	private String getClientTreePath(TreeNode[] treenode)
	{
		String tmp = "";
         for (int i = 1; i < treenode.length-1; i++)
         {
         	 tmp += (treenode[i].toString() + "\\");
         }
         tmp += treenode[ treenode.length-1 ].toString();
		 return tmp;
	}

	/*
	 * ���·�����Ŀ¼�ṹ
	 */
	public void updateServer(String path)
	{
		DefaultMutableTreeNode aimnode = 
 			     (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
		String aimpath = getTreePath(aimnode.getPath());
	    
		try 
			{
				new Thread ()
				{
					public void run()
					{
						serverRoots.removeAllChildren();
						frame.setCursor(Cursor.WAIT_CURSOR);
						//String sonfileList = "doc\\A\\a.txt,doc\\A\\b.txt,doc\\B\\b.txt";
						String sonfileList = FileList.sendReq("doc");
						log.debug("���ص��б�:" + sonfileList);
						String[] sonfileNames = sonfileList.split(",");
						for(String e:sonfileNames)
						{
							if (!e.equals("")&&e!=null)insertServerNode(e);
						}
						serverTree.updateUI();
						frame.setCursor(Cursor.DEFAULT_CURSOR);
					}
				}.start();
			}
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		//aimnode.getPath()
		log.debug(aimnode.toString());
		expandTree(serverTree, new TreePath(aimnode.getPath()));
		//tree.scrollPathToVisible(treePath);  
		
		
	}
	
	public void updatedelserver()
	{
		DefaultMutableTreeNode nodes = 
			     (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes.getParent();

		String filenames = FileList.listSonfile(getTreePath(node.getPath()));
		log.debug(filenames);
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
						log.debug(filepath + ":" + filename);
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
	}
	
	 public static void expandTreeNode(JTree aTree, DefaultMutableTreeNode aNode) {  
	        if (aNode.isLeaf()) {  
	          return;  
	        }  
	        aTree.expandPath(new TreePath( ( (DefaultMutableTreeNode) aNode).getPath()));  
	        int n = aNode.getChildCount();  
	        for (int i = 0; i <n; i++) {  
	          log.debug(i);
	          expandTreeNode(aTree, (DefaultMutableTreeNode) aNode.getChildAt(i));  
	        }  
	 }  
	 
	 private void expandTree(JTree tree, TreePath parent) {
		    TreeNode node = (TreeNode) parent.getLastPathComponent();
		    if (node.getChildCount() >= 0) {
		       for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
		           TreeNode n = (TreeNode) e.nextElement();
		           TreePath path = parent.pathByAddingChild(n);
		           expandTree(tree, path);
		       }
		    }
		    tree.expandPath(parent);
	 }
	
	    public static void findInTree(JTree tree,String str){  
	        Object root = tree.getModel().getRoot();  
	        TreePath treePath = new TreePath(root);  
	        treePath = findInPath(tree, treePath, str);  
	        if (treePath != null) {  
	            tree.setSelectionPath(treePath);  
	            tree.scrollPathToVisible(treePath);  
	        }  
	    }  
	  
	    public static String TreePathtoPath(String treepath)
		 {
			 String path  = "";
			 path = treepath.substring(1, treepath.length()-1);
			 String res[] = path.split(",");
			 path = "";
			 for(int i = 0; i < res.length-1; i++)
			 {
				 path += res[i].trim() + "\\";
			 }
			 path += res[res.length-1].trim();
			 return path;
		 }
	    public static TreePath findInPath(JTree tree, TreePath treePath, String str){  
	    	
	        Object object = treePath.getLastPathComponent();  
	        if (object == null) {  
	            return null;  
	        }  
	 
	        //System.out.println( TreePathtoPath(treePath.toString()) );
	        if (str.equals(TreePathtoPath(treePath.toString()))) {  
	            return treePath;  
	        }else {  
	            TreeModel model = tree.getModel();  
	            int n = model.getChildCount(object);  
	            for (int i = 0; i < n; i++) {  
	                Object child = model.getChild(object, i);  
	                TreePath path = treePath.pathByAddingChild(child);  
	  
	                path = findInPath(tree, path, str);  
	                if (path != null) {  
	                    return path;  
	                }  
	            }  
	            return null;  
	        }  
	    } 
	public void insertServerNode(String paths)
	{
		String sonfileList = null;
		//sonfileList = FileList.sendReq(getTreePath(node.getPath()));
		//log.debug(paths);
		String path[] = paths.split("\\\\");
		DefaultMutableTreeNode tmpnode = serverRoots;
		for(int i = 1; i < path.length; i++)
		{
			//log.debug(path[i]);
			int n = tmpnode.getChildCount();
			boolean flag = false;
			for (int j = 0; j < n; j++ )
			{
				if (tmpnode.getChildAt(j).toString().equals(path[i])) 
				{
					tmpnode = (DefaultMutableTreeNode) tmpnode.getChildAt(j);
					flag = true;
					break;
				}
			}
			if (!flag) 
			{
				DefaultMutableTreeNode tmp = new DefaultMutableTreeNode(path[i]);
				tmpnode.add(tmp);
				tmpnode = tmp;
			}
		}
	}
	
	private void listServerDirectory()
	{
		//DefaultMutableTreeNode serverRoots = new DefaultMutableTreeNode("doc");
		serverTree = new JTree(serverRoots);
		serverTree.setCellRenderer(new MySeverCellRender());
	    JScrollPane serverScrollTree = new JScrollPane(serverTree);

	    
	    serverPanel = new JPanel(new BorderLayout());
	    serverBarPanel = new MyTreeBar("����");
	    serverPanel.add(serverBarPanel, BorderLayout.NORTH);
	    serverPanel.add(serverScrollTree, BorderLayout.CENTER);
	    
	    drag();              /////////////////////////////��ק�Ĺ���
	     
	    	 
	    serverTree.addMouseListener(new MouseListener() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//log.debug(e.getClickCount() + " "+e.getSource());
				if (e.getClickCount() == 1 )
				{
					if (MyUser.userName.equals("�ο�"))
					{
						JOptionPane.showMessageDialog(null, "��ǰ�û�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					/*
					 * ��ȡѡ�еĽڵ�
					 */
					DefaultMutableTreeNode node = 
	  			     (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent(); //�������ѡ�еĽ��     					
					if (node == null)
					{
						return;
					} 
					
			  	    String nodeName = node.toString();//��������������;
			  	    
			  	    serverBarPanel.textField.setText( getTreePath(node.getPath()) );	  
			  	    MyShowFilePanel.pathtext.setText( getTreePath(node.getPath()) );
			  	    /*
			  	     * 
			  	     */
			  	    cardLayout.show(showPanel,"11");
			  	    String dirname = serverBarPanel.textField.getText();
			  	    if (IsFile.isFile(dirname)) return;
			  	    MyShowFilePanel.addFile(dirname);
			  	    
//	                log.debug("�ļ���·��:" +serverTree.getSelectionPath().toString());
//	                serverBarPanel.textField.setText( getTreePath(node.getPath()) );
            	    /////////////////////
            	    
        		    EventQueue.invokeLater(new Runnable() 
        		    {
        				public void run() 
        				{
        					node.removeAllChildren();
        					try 
        					{  
        						for (int i = 0; i < MyShowFilePanel.table.getRowCount(); i++)
        						{
        							String filename = (String) MyShowFilePanel.table.getValueAt(i, 1);
        							node.add(new DefaultMutableTreeNode(filename));
        						}
        						serverTree.updateUI();  //���������λ�ú���Ҫ

        					} 
        					catch (Exception e) 
        					{
        						e.printStackTrace();
        					}
        				}
        			 });	
        		    
        		    ///////////////////////////
	                
//	                if (getTreePath(node.getPath()).equals("doc"))
//			  	    {
//			  	    	
//		                try 
//		 				{
//		 					new Thread ()
//		 					{
//		 						public void run()
//		 						{
//		 							serverRoots.removeAllChildren();
//		 							frame.setCursor(Cursor.WAIT_CURSOR);
//		 							//String sonfileList = "doc\\A\\a.txt,doc\\A\\b.txt,doc\\B\\b.txt";
//		 							String sonfileList = FileList.sendReq(getTreePath(node.getPath()));
//		 							log.debug("���ص��б�:" + sonfileList);
//		 							String[] sonfileNames = sonfileList.split(",");
//		 							for(String e:sonfileNames)
//		 							{
//		 								if (!e.equals("")&&e!=null) insertServerNode(e);
//		 							}
//		 							serverTree.updateUI();
//		 							frame.setCursor(Cursor.DEFAULT_CURSOR);
//		 						}
//		 					}.start();
//		 				}
//		     			catch (Exception e1) 
//		     			{
//		     				e1.printStackTrace();
//		     			}
//					} 
				}
				else if (e.getClickCount() == 2)
				{
					DefaultMutableTreeNode node = 
			  			     (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent(); //�������ѡ�еĽ��     					
					if (node == null)
					{
						return;
					} 
			  	    String nodeName = node.toString();//��������������;  	    
					serverBarPanel.textField.setText( getTreePath(node.getPath()) );
					String fileName = serverBarPanel.textField.getText();
			  	    if (IsFile.isFile(fileName))
			  	    {					
						clientPath = MyPath.scanPath + "\\" + fileName;
						new Thread("onlinescan")
						{
							public void run()
							{
								UpDownFile.getMyProgressBar(progressbar);
								UpDownFile.onlineScanFile(fileName, clientPath);
								MyScan.scan(clientPath);
							}
						}.start();
			  	    }
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	      
	}
	
	

	
	private void listClientFileDirectory()
	{
		 DefaultMutableTreeNode clientFileRoots = new DefaultMutableTreeNode(MyParameters.getSavePath());
		 File file = new File(MyParameters.getSavePath());
	     file.mkdirs();
		 
		 clientFileTree = new JTree(clientFileRoots);
	     clientFileTree.setCellRenderer(new MyClientFileTreeCellRender());
	     JScrollPane clientFileScrollTree = new JScrollPane(clientFileTree); 
	     
	           
	     clientFilePanel = new JPanel(new BorderLayout());
		 clientFileBarPanel = new MyTreeBar("·��");
		 clientFilePanel.add(clientFileBarPanel, BorderLayout.NORTH);
		 clientFilePanel.add(clientFileScrollTree, BorderLayout.CENTER);
  
	     
		 clientFileTree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				// TODO Auto-generated method stub
				log.debug(e.getClickCount()+" "+e.getSource().toString());
				if (e.getClickCount() == 1)
				{
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) clientFileTree.getLastSelectedPathComponent(); //�������ѡ�еĽ��  

	          	    if (node == null)
	          	    {
	          	    	return;
	          	    }
					String nodeName = node.toString();//��������������
					
					
					clientFileBarPanel.textField.setText(getTreePath(node.getPath()));
	                log.debug(nodeName+"expanded����");
	                
	                
                    String filePath = getTreePath(node.getPath());
            	    log.debug("��ǰ�ڵ�·��" + filePath);
                    File parentFile = new File(filePath);
                    
                    if (parentFile.isDirectory())
                    {
                	    log.info(nodeName + " is " + "directory");
                	    String[] sonFiles = parentFile.list();
                	    MyShowClientFilePanel.pathtext.setText( getTreePath(node.getPath()) );
    			  	    cardLayout.show(showPanel,"13");
    			  	    
    			  	    String dirname = MyShowClientFilePanel.pathtext.getText();
    			  	    
                	    //list��ȡ�Ľ��������ļ�·����ֻ�ǵ�ǰ�ļ���
                	    
            		    EventQueue.invokeLater(new Runnable() 
            		    {
            				public void run() 
            				{
            					node.removeAllChildren();
            					try 
            					{      
            						for (String file : sonFiles)
            						node.add(new DefaultMutableTreeNode(file));     
            						
            						MyShowClientFilePanel.addFile(dirname);
            					} 
            					catch (Exception e) 
            					{
            						e.printStackTrace();
            					}
            					clientFileTree.updateUI();
            				}
            			 });	                		  
                	    
                     }
	          	                      
                   
				} 
				else if (e.getClickCount() == 2)
				{
					String fileName = clientFileBarPanel.textField.getText();
					File file = new File(fileName);
					if (file.isFile())	MyScan.scan(fileName);
				}
			}
		});
		 		  
	}
	/*
	 * to list the file under the directory
	 */
	private void listClientDirectory()
	{
		 DefaultMutableTreeNode clientRoots = new DefaultMutableTreeNode("�˵���");
		 File[] root = File.listRoots(); 
		 clientTree = new JTree(clientRoots);
	     clientTree.setCellRenderer(new MyTreeCellRender());
	     JScrollPane clientScrollTree = new JScrollPane(clientTree); 
	     
	     for (int i = 0; i < root.length; i++) 
	     {  
	         if(root[i].canRead())
	         {
	        	 clientRoots.add(new DefaultMutableTreeNode(root[i].toString()));	        	 
	         }
	     }
	           
	     clientPanel = new JPanel(new BorderLayout());
		 clientBarPanel = new MyTreeBar("�ϴ�");
		 clientPanel.add(clientBarPanel, BorderLayout.NORTH);
		 clientPanel.add(clientScrollTree, BorderLayout.CENTER);
		  //boxHor2.add(clientPanel);
	     
		 clientTree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				// TODO Auto-generated method stub
				log.debug(e.getClickCount()+" "+e.getSource().toString());
				if (e.getClickCount() >= 1)
				{
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) clientTree.getLastSelectedPathComponent(); //�������ѡ�еĽ��  
	          	    if (node == null)
	          	    {
	          	    	return;
	          	    }
					String nodeName = node.toString();//��������������
	          	    
	          	    if (nodeName.equals("�˵���"))
	          	    {
	          	    	return;
	          	    }
	          	    
	          	    HashMap<String, Integer>map =new HashMap<String, Integer>();
	                int i = 0;
	                for (i = 0; i < node.getChildCount(); i++)
	                {
	            	    map.put(node.getChildAt(i).toString(), 1);
	                }
	          	    
	                log.debug(nodeName+"expanded����"+clientTree.isExpanded(clientTree.getSelectionPath()));
                    String filePath = getClientTreePath(node.getPath());
            	    log.debug("��ǰ�ڵ�·��" + filePath);
                    File parentFile = new File(filePath);
                  
                    if (parentFile.isDirectory())
                    {
                	    log.info(nodeName + " is " + "directory");
                	    String[] sonFiles = parentFile.list();
                	    //list��ȡ�Ľ��������ļ�·����ֻ�ǵ�ǰ�ļ���
                	    for (String file : sonFiles)
                	    {
                	    	if ( map.containsKey(file) ) continue;
 	            		    map.put(file, 2);
                		    EventQueue.invokeLater(new Runnable() 
                		    {
                				public void run() 
                				{
                					try 
                					{              					
                						node.add(new DefaultMutableTreeNode(file));               						            						
                					} 
                					catch (Exception e) 
                					{
                						e.printStackTrace();
                					}
                				}
                			 });	                		  
                	    }
                     }
                    else
                    {
                    	if (e.getClickCount() == 2)
                    	{
                    		if (nodeName.equals("�˵���")) return;
                    		log.info(nodeName+ " is  file");                      	
                        	clientBarPanel.textField.setText(filePath);  
                        	JOptionPane.showMessageDialog(frame, "�����ļ���");
                    	}
                    	
                    }
	          	                      
                   
				}   
			}
		});
		 		  
	}
	
	private void drag()
	{
		new DropTarget(serverPanel, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter() {
			
			@Override
			public void drop(DropTargetDropEvent dtde) {
				// TODO Auto-generated method stub
				try
				{
					if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))//���������ļ���ʽ��֧��
	                 {
	                     dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//������ק��������
	                     List<File> list =  (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
	                     String temp="";
	                     for(File file:list)
	                         temp+=file.getAbsolutePath()+";\n";
	                     JOptionPane.showMessageDialog(null, temp);
	                     dtde.dropComplete(true);//ָʾ��ק���������
	                 }
	                 else
	                 {
	                     dtde.rejectDrop();//����ܾ���ק��������
	                 }
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				 
			}
		});
	}
	
	
	
	String userName = null;
	String fileName = null;
	String clientPath = null;
	/*
	 * �¼�������
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		
	    if (e.getSource() == myServerPopMenu.menuItem1)   //Ԥ��
		{
			
	    	log.info("Ԥ��");
	    	if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
	    	
			fileName = serverBarPanel.textField.getText().toString();
			log.debug(fileName);
			
			clientPath = MyPath.scanPath + "\\" + fileName;
			new Thread("onlinescan")
			{
				public void run()
				{
					UpDownFile.getMyProgressBar(progressbar);
					UpDownFile.onlineScanFile(fileName, clientPath);
					MyScan.scan(clientPath);
				}
			}.start();
			
			
//			clientPath = MyParameters.getSavePath() + "\\" +fileName;
//			log.debug("Ԥ��ʱ�ı�����ʱ�ļ�" + clientPath);	
			
//			//�������߳�1.0new Thread(new MyProgressThread()).start();
//			//�������ڸ�ʽ
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            log.debug("��ǰʱ��"+(df.format(new Date())) );
//            Object[] rowData = new Object[]
//            {
//           		 df.format(new Date()), fileName, "�ϴ���"
//			};
//			
//            CountDownLatch barrier = new CountDownLatch(1);
//            Semaphore
            
           
//			new Thread("onlinescan")
//			{
//				public void run()
//				{
//					log.info("�ļ�Ԥ����");
//
//					
//					jobTableModel.addRow(rowData);
//		            int row = jobTableModel.getRowCount();
//		            log.debug(jobTableModel.getRowCount());
//					httpFile.setTableThreadPara(jobTableModel, row-1, 4);
//					httpFile.downloadFile(serverBarPanel.textField.getText().toString(), userName, clientPath);
//					log.debug("Ԥ���ļ���������");
////					barrier.countDown();
//					//���������Ǹ��Ƿ�������
//					//���Ҳ�Ƿ�����ʽ�ģ��������滹��������
//					MyScan.scan(clientPath);
					
					
//					try {
//						Thread.sleep(10000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					File file = new File(clientPath);
//					if( file.delete() )
//					{
//						System.out.println("Ԥ����ɾ���ɹ�");
//					}
//					
//				}
//				
//				
//			}.start();     
		
			
			
				
		}
		else if (e.getSource() == myServerPopMenu.menuItem2)  //����
		{
			if(!MyUser.download()){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û������Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
//			JFileChooser fc = new JFileChooser();
//			fc.setDialogTitle("��ѡ�����ص��ı���Ŀ¼");
//			//ָ��ֻ��ѡ��Ŀ¼
//			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY ); 
//			fc.showOpenDialog(frame);
//	        File path = fc.getSelectedFile();
//	        if (path == null)
//	        {
//	        	return;
//	        }
//
//	        log.info("ѡ��ı���Ŀ¼" + path.getAbsolutePath());
			
	        //�����ļ���·���������ļ�����
	        String serverPath = serverBarPanel.textField.getText().toString().trim();
	        
	        log.debug(serverPath);
	
	        String clientPath = MyParameters.getSavePath()  +"\\"+ serverPath;
	        
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           
            Object[] rowData = new Object[]
            {
           		 df.format(new Date()), clientPath, "����", clientPath, 0
			};
            
       
			new Thread("downLoad")
			{
				public void run()
				{				 
					WorkingJob.addjob();
					log.debug(WorkingJob.workingjob);
					jobTableModel.addRow(rowData);
		            int row = jobTableModel.getRowCount();
		            log.debug(jobTableModel.getRowCount());
		            UpDownFile.setTableThreadPara(jobTableModel, row-1, 4);
		            UpDownFile.getMyProgressBar(progressbar);
		            UpDownFile.downloadFile(serverPath,  clientPath);
		            WorkingJob.deljob();
		            log.debug(WorkingJob.workingjob);
				}   
				
			}.start();
			
//			log.debug("�����ļ������Ϣ");
//			String s = MyFileHandle.getFileInfo(serverPath);
//			
//			LocalFileIo.writeLine(clientPath + "," + s);
//			log.debug(clientPath + "," + s);
			// ����Ŀ¼�ؼ�����Ϣ
			String dirPath = serverPath;
			while(dirPath.lastIndexOf("\\") != 3)
			{
				dirPath = dirPath.substring(0, dirPath.lastIndexOf("\\"));
				String s = MyDirHandle.getDirKeywords(dirPath);
				String endTime = MyDirHandle.getDirApplyEndTime(dirPath);
				if(!endTime.equals(""))
				{
					s = s + endTime + ",";
				}
				else
				{
					s = s + ",";
				}
				LocalDirIo.writeDirKeywords(dirPath + ";" + s);
			}
				
			Object[] historyData = new Object[] 
		    {
		           //df.format(new Date()),serverPath, "����", clientPath, "100%"
		          df.format(new Date()), MyUser.userName,serverPath, "����", clientPath, "100%"
			};  
			historyTableModel.addRow(historyData);
		    boolean flag = HistoryIo.writeLine(historyData);
			

		}
//		else if (e.getSource() == myServerPopMenu.menuItem4)  //ɾ���ļ�
//		{
//			if(MyUser.privilage!=1){
//				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
//				return;
//			}
//			log.info("ɾ���ļ�");
//			String s = MyFileHandle.delFile(serverBarPanel.textField.getText().toString().trim());
//			
//			if (s.startsWith("filenoexist"))
//			{
//				JOptionPane.showMessageDialog(null, "���ļ��Ѿ�������",null, JOptionPane.INFORMATION_MESSAGE);
//			}
//			else if (s.equals("yes"))
//			{
//				JOptionPane.showMessageDialog(null, "ɾ���ļ��ɹ�",null, JOptionPane.INFORMATION_MESSAGE);
//			}
//			else if (e.equals("no"))
//			{
//				JOptionPane.showMessageDialog(null, "ɾ���ļ�ʧ��",null, JOptionPane.INFORMATION_MESSAGE);
//			}
//			
//		}
//		else if (e.getSource() == myServerPopMenu.menuItem5)  //�鿴�ļ������Ϣ
//		{
//			if(!MyUser.scanServer()){
//				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
//				return;
//			}
//			log.info("�鿴�ļ������Ϣ");
//			String s = MyFileHandle.getFileInfo(serverBarPanel.textField.getText().toString().trim());
//			
//			log.debug(s);
//			if (s.startsWith("filenoexist"))
//			{
//				JOptionPane.showMessageDialog(null, "���ļ��Ѿ�������",null, JOptionPane.INFORMATION_MESSAGE);
//			}
//			else 
//			{
//			
//				MyShowFileInfoDialog showDialog = new MyShowFileInfoDialog(s);
//			}
//			
//		}
//		else if (e.getSource() == myServerPopMenu.menuItem6)  //�޸��ļ������Ϣ
//		{
//			if(MyUser.privilage!=1){
//				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
//				return;
//			}
//			log.info("�޸��ļ������Ϣ");
//			String s = MyFileHandle.getFileInfo(serverBarPanel.textField.getText().toString().trim());
//			log.debug(s);
//			MyUpLoadFileInfoDialog fileinfoDialog = new MyUpLoadFileInfoDialog(serverBarPanel.textField.getText().toString().trim(), s);
//		}
		else if (e.getSource() == myServerPopMenu.menuItem7)  //ɾ��,�������վ
		{
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			log.info("ɾ��");
			String s = RecycleHandle.putToRecycle(serverBarPanel.textField.getText().toString().trim());
			if (s.startsWith("yes"))
			{
				JOptionPane.showMessageDialog(null, "�������վ�ɹ�",null, JOptionPane.INFORMATION_MESSAGE);
				updatedelserver();
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "�������վʧ��",null, JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		}
		else if (e.getSource() == myServerPopMenu.menuItem8)  //�鿴��ʷ�汾
		{
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String s = MyFileHandle.getVersionFile(serverBarPanel.textField.getText().toString().trim());
			
			cardLayout.show(showPanel,"9");
			
			MyFileVersionPanel.addFile(s);
			
		}
		else if (e.getSource() == myDirPopMenu.menuItem1)  //����Ŀ¼
		{
			if(MyUser.privilage!=1)
			{
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			log.info("����Ŀ¼");
			MyAddDirDialog addDirDialog = new MyAddDirDialog((DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent(),
					serverBarPanel.textField.getText().toString().trim());
			
		}
		else if (e.getSource() == myDirPopMenu.menuItem2)  //ɾ��Ŀ¼
		{
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			log.info("ɾ��Ŀ¼");
			String serverPath = serverBarPanel.textField.getText().toString().trim();
			String res = MyDirHandle.delDir(serverPath).trim();
			if (res.equals("yes"))
			{
				
				JOptionPane.showMessageDialog(null, "ɾ���ļ��гɹ�",null, JOptionPane.INFORMATION_MESSAGE);
				updatedelserver();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "ɾ���ļ���ʧ��",null, JOptionPane.INFORMATION_MESSAGE);
			}
			 
		}
//		else if (e.getSource() == myDirPopMenu.menuItem3)  //�޸�Ŀ¼��
//		{
//			log.info("�޸�Ŀ¼��");
//			
//		}  
		else if (e.getSource() == myDirPopMenu.menuItem4)  //�༭Ŀ¼��ǩ��Ϣ
		{
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			log.info("�༭Ŀ¼��ǩ��Ϣ");
			String serverPath = serverBarPanel.textField.getText().toString().trim();
			String s = MyDirHandle.getDirInfo(serverPath).trim();
			MyAddDirInfoDialog dialog = new MyAddDirInfoDialog(serverPath, s);
			
		}
		else if (e.getSource() == myDirPopMenu.menuItem5)  //�鿴Ŀ¼��ǩ��Ϣ
		{
			if(MyUser.privilage>=3){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			log.info("�鿴Ŀ¼��ǩ��Ϣ");
			String serverPath = serverBarPanel.textField.getText().toString().trim();
			String s = MyDirHandle.getDirInfo(serverPath).trim();
			log.debug(s);
			if (s.equals("dirnoexist"))
			{
				JOptionPane.showMessageDialog(null, "��Ŀ¼�Ѿ�������",null, JOptionPane.INFORMATION_MESSAGE);
			}
			else 
			{
				//MyShowDirInfoDialog dirInfoDialog = new MyShowDirInfoDialog(serverPath, s); 
				MyShowDirInfoDialog1 dirInfoDialog = new MyShowDirInfoDialog1(serverPath, s); 
			}
						
		}
		else if (e.getSource() == myDirPopMenu.menuItem6)  //�ϴ��ļ�
		{
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			log.info("�ϴ��ļ�");
			//��ѡ�еķ�����Ŀ¼����ȥ
			MyUpLoadDialog myUpLoadDialog = new MyUpLoadDialog( (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent(),
					serverBarPanel.textField.getText().toString().trim(), historyTableModel, jobTableModel);
			

		}
		else if (e.getSource() == myDirPopMenu.menuItem7)   //�鿴Ŀ¼�ؼ���
		{
			if(!MyUser.scanServer())
			{
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			log.info("�鿴Ŀ¼�ؼ���");
			//String s = MyFileHandle.getFileInfo(serverBarPanel.textField.getText().toString().trim());
			String s = MyDirHandle.getDirKeywords(serverBarPanel.textField.getText().toString().trim());
			log.debug(s);
			if (s.startsWith("filenoexist"))
			{
				JOptionPane.showMessageDialog(null, "��Ŀ¼�Ѿ�������",null, JOptionPane.INFORMATION_MESSAGE);
			}
			else 
			{
				MyShowFileInfoDialog showDialog = new MyShowFileInfoDialog(s);
			}
		}
		else if (e.getSource() == myDirPopMenu.menuItem8) //�༭Ŀ¼�ؼ���
		{
			if(MyUser.privilage != 1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			log.info("�༭Ŀ¼�ؼ���");
			String s = MyDirHandle.getDirKeywords(serverBarPanel.textField.getText().toString().trim());
			log.debug(s);
			MyUpLoadFileInfoDialog fileinfoDialog = new MyUpLoadFileInfoDialog(serverBarPanel.textField.getText().toString().trim(), s);
		}
		else if (e.getSource() == myDirPopMenu.menuItem9) //�༭Ŀ¼�ؼ���
		{
			if(MyUser.privilage != 1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			log.info("��Ѱ��Ŀ¼�µ��ļ�");
			String aimPath = serverBarPanel.textField.getText().toString().trim();
			MyFileSearchDialog dialog = new MyFileSearchDialog(aimPath); 
			cardLayout.show(showPanel,"12");
		}
		else if (e.getSource() == myClientFilePopMenu.menuItem1)     //���ļ������ļ���
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) clientFileTree.getLastSelectedPathComponent(); //�������ѡ�еĽ��    
			String savePath = getTreePath(node.getPath());
			File file = new File(savePath);
     	    log.debug(file.toString()+"���ڵ�Ŀ¼Ϊ"+file.getParent());
     	    MyOpenClientFile.openDir(file.getParent());
		}
//		else if (e.getSource() == myClientFilePopMenu.menuItem2)     //�鿴�ļ������Ϣ
//		{
//			DefaultMutableTreeNode node = (DefaultMutableTreeNode) clientFileTree.getLastSelectedPathComponent(); //�������ѡ�еĽ��    
//			String savePath = MyParameters.getSavePath() + "\\" + getClientTreePath(node.getPath());
//			log.info("�鿴�ļ������Ϣ");
//			String s = LocalFileIo.getFileInfo(savePath);
//			log.debug(s);
//			
//			MyShowFileInfoDialog showDialog = new MyShowFileInfoDialog(s);
//			
//     	    
//		}
	    ////////////////////////////////////////////
	    //MyLoginDialog myLoginDialog = new MyLoginDialog();
		else if (e.getSource() == MyLoginDialog.button)      //��¼
		{
			log.info("��¼");
			if (MyLoginDialog.comboBox.getText() == null || MyLoginDialog.textField.getText() == null || MyLoginDialog.comboBox.getText().equals("") || MyLoginDialog.textField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			log.debug(MyLoginDialog.comboBox.getText().toString());
			log.debug(MyLoginDialog.textField.getText());
			
			String name = MyLoginDialog.comboBox.getText().toString();
			
			
			CountDownLatch mCountDownLatch = new CountDownLatch(1);
			String res = MyLogin.login(MyLoginDialog.comboBox.getText().toString(),MyLoginDialog. textField.getText());
			
			int result = Integer.parseInt(res.trim());
			System.out.println(result);
			if (result >= 1 && result <= 3)
			{
				MyUser.userName = name;
				MyUser.state = "����";
				MyUser.privilage = result;
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, MyLogin.getLoginString(result),null, JOptionPane.INFORMATION_MESSAGE);
				//return;
		//loginres.setText(MyLogin.getLoginString(result));
//				cardLayout.show(getContentPane(), "2");
			}
//			if ( !MyUser.userName.equals("�ο�"))
//			{
//				JOptionPane.showMessageDialog(null, "��ǰ�û���û�˳���¼",null, JOptionPane.INFORMATION_MESSAGE);
//				return;
//			}
			
			/*
			 * 
			 * 
			 */
			String ress = MyParameters.init();
			if (ress.equals("true"))
			{
				//MyLoginDialog myLoginDialog = new MyLoginDialog(loginPanel);
			}
			else if(ress.equals("false"))
			{
				JOptionPane.showMessageDialog(null, "��������ַ�����д��Ժ�����������");
			}
			
			
			
			if(MyUser.privilage == 1) 
			{
				MyMenu.menuBar.remove(MyMenu.setMenu);
				MyMenu.menuBar.remove(MyMenu.helpMenu);
				MyMenu.menuBar.add(MyMenu.fileManage);
				MyMenu.menuBar.add(MyMenu.logManage);
				MyMenu.menuBar.add(MyMenu.userManage);
				MyMenu.menuBar.add(MyMenu.personalCenter);
				MyMenu.menuBar.add(MyMenu.setMenu);
				MyMenu.menuBar.add(MyMenu.helpMenu);
				MyMenu.menuBar.remove(MyMenu.fileManageClient);
			}
			else if(MyUser.privilage == 2)
			{
				MyMenu.menuBar.remove(MyMenu.setMenu);
				MyMenu.menuBar.remove(MyMenu.helpMenu);
				MyMenu.menuBar.add(MyMenu.fileManage);
				MyMenu.menuBar.add(MyMenu.personalCenter);
				MyMenu.menuBar.add(MyMenu.setMenu);
				MyMenu.menuBar.add(MyMenu.helpMenu);
				MyMenu.fileManage.remove(MyMenu.recycle);
				MyMenu.menuBar.remove(MyMenu.fileManageClient);
			}else if( MyUser.privilage ==3){
				MyMenu.menuBar.remove(MyMenu.setMenu);
				MyMenu.menuBar.remove(MyMenu.helpMenu);
				MyMenu.menuBar.add(MyMenu.fileManage);
				MyMenu.menuBar.add(MyMenu.personalCenter);
				MyMenu.menuBar.add(MyMenu.setMenu);
				MyMenu.menuBar.add(MyMenu.helpMenu);
				MyMenu.fileManage.remove(MyMenu.recycle);
				MyMenu.menuBar.remove(MyMenu.fileManageClient);
				MyMenu.fileManage.remove(MyMenu.syn);
			}
			initPopupMenu();			
			stateField.setText(MyUser.state);						
			System.out.println(MyUser.userName);
			userField.setText(MyUser.userName);
			privilegeField.setText(MyUser.privilage+"");
			cardLayout.show(showPanel, "14");
		}
		else if (e.getSource() == MyLoginDialog.button_1) //ȡ����¼
		{
			log.debug("Logout");
			MyLoginDialog.comboBox.setText(null); 
			MyLoginDialog.textField .setText(null);
			cardLayout.show(showPanel, "imageshow");
		}
	    
		else if(e.getSource() == myMenu.helpItem)//�鿴ʹ�ð���
		{
			String path = "scan\\�˵�����˼����ܹ���ר�����ݿ����ʹ�ð���.pdf";
			MyScan.scan(path);
		}
		else if (e.getSource() == myMenu.setServerItem)   //�޸ķ���������
		{
			log.info("�޸ķ���������");
			MyServerDialog myServerDialog = new MyServerDialog(frame, hostField, portField);

		}
		else if(e.getSource() == myMenu.setServerIpItem)//�޸ķ�������ip
		{
			log.info("�޸ķ�����ip");
			MyServerIpDialog myServerIpDialog = new MyServerIpDialog(frame,hostField,portField);
		}
		else if( e.getSource() == myMenu.setUiItem)      //���ý�����
		{
			//Object[] possibleValues = { "windows", "Nimbus", "Motif����" }; 
			Object[] possibleValues = MyTheme.getThemes();
			Object selectedValue = JOptionPane.showInputDialog(null, "��ǰ�����ǣ�"+MyParameters.getTheme(), "������������", 
			JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);		
			if (selectedValue == null) return;   //����ʲô��ûѡ��
			log.info("ѡ�������⣺"+selectedValue);
			
			
					String theme = null;
					try
					{
						switch(selectedValue.toString())
						{
						case "windows" :
							theme = UIManager.getSystemLookAndFeelClassName();
							log.info(theme);
							UIManager.setLookAndFeel(theme);
							break;
						case "Nimbus" :
							theme = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
							log.info(theme);
							UIManager.setLookAndFeel(theme);
							break;
						case "Motif" : 
							theme = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
							log.info(theme);
							UIManager.setLookAndFeel(theme);
							break;
						default :
//							SwingUtilities.invokeLater(new Runnable() {
//								public void run() 
//								{
									theme = "org.jvnet.substance.skin."+selectedValue;
									log.info(theme);
									SubstanceLookAndFeel.setSkin(theme);
									SubstanceLookAndFeel.setCurrentButtonShaper(new StandardButtonShaper());
							
//								}
//							});
							break;
						}
					}
		
					catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e2)
					{
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					MyParameters.setTheme(selectedValue.toString());

					
					//����������֮�󣬸���ui
					MyUpdateUi.updateAllUI(frame); 
							
		}
		else if (e.getSource() == myMenu.setSavePathItem)    //�����ļ���Ĭ�ϱ���·��
		{
			JFileChooser chooser = new JFileChooser();
	        chooser.setDialogTitle("��ѡ��·��");
	        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
	        //chooser.addChoosableFileFilter(new FileNameExtensionFilter("����ͼƬ��ʽ", "jpg", "jpeg", "gif", "png"));
	        chooser.showOpenDialog(frame);
	        File path = chooser.getSelectedFile();
	        if (path == null)
	        {
	        	return;
	        }
	        log.debug(path.getAbsolutePath());
				int answer = JOptionPane.showConfirmDialog(frame, "����Ĭ������·��������Ϊ��"+ path.getAbsolutePath() +
						",��رղ����´��������Ч��", null, JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
				{
					log.info("����Ĭ������·��" + path.getAbsolutePath());
					MyParameters.setSavePath(path.getAbsolutePath());
				}
				else
				{
					log.info("��ȡ��������Ĭ������·��");
					return;
				}
				
	
		}
		else if(e.getSource() == myMenu.loginItem)//�������¼
		{

			log.info("��¼");
			if ( !MyUser.userName.equals("�ο�"))
			{
				JOptionPane.showMessageDialog(null, "��ǰ�û���û�˳���¼",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			/*
			 * 
			 * 
			 */
			log.debug(MyParameters.getIp());
			String res = MyParameters.init();
			log.debug(MyParameters.getIp());
			if (res.equals("true"))
			{
				MyLoginDialog1 myLoginDialog1 = new MyLoginDialog1(frame);
			}
			else if(res.equals("false"))
			{
				JOptionPane.showMessageDialog(null, "��������ַ�����д��Ժ�����������");
			}
			
			
			
			if(MyUser.privilage == 1) 
			{
				MyMenu.menuBar.remove(MyMenu.setMenu);
				MyMenu.menuBar.remove(MyMenu.helpMenu);
				MyMenu.menuBar.add(MyMenu.fileManage);
				MyMenu.menuBar.add(MyMenu.logManage);
				MyMenu.menuBar.add(MyMenu.userManage);
				MyMenu.menuBar.add(MyMenu.personalCenter);
				MyMenu.menuBar.add(MyMenu.setMenu);
				MyMenu.menuBar.add(MyMenu.helpMenu);
				MyMenu.menuBar.remove(MyMenu.fileManageClient);
			}
			else if(MyUser.privilage == 2)
			{
				MyMenu.menuBar.remove(MyMenu.setMenu);
				MyMenu.menuBar.remove(MyMenu.helpMenu);
				MyMenu.menuBar.add(MyMenu.fileManage);
				MyMenu.menuBar.add(MyMenu.personalCenter);
				MyMenu.menuBar.add(MyMenu.setMenu);
				MyMenu.menuBar.add(MyMenu.helpMenu);
				MyMenu.fileManage.remove(MyMenu.recycle);
				MyMenu.menuBar.remove(MyMenu.fileManageClient);
			}else if( MyUser.privilage ==3)
			{
				MyMenu.menuBar.remove(MyMenu.setMenu);
				MyMenu.menuBar.remove(MyMenu.helpMenu);
				MyMenu.menuBar.add(MyMenu.fileManage);
				MyMenu.menuBar.add(MyMenu.personalCenter);
				MyMenu.menuBar.add(MyMenu.setMenu);
				MyMenu.menuBar.add(MyMenu.helpMenu);
				MyMenu.fileManage.remove(MyMenu.recycle);
				MyMenu.menuBar.remove(MyMenu.fileManageClient);
				MyMenu.fileManage.remove(MyMenu.syn);
			}
//			else if(MyUser.privilage == 2 || MyUser.privilage ==3)
//			{
//				MyMenu.menuBar.remove(MyMenu.setMenu);
//				MyMenu.menuBar.remove(MyMenu.helpMenu);
//				MyMenu.menuBar.add(MyMenu.fileManage);
//				MyMenu.menuBar.add(MyMenu.personalCenter);
//				MyMenu.menuBar.add(MyMenu.setMenu);
//				MyMenu.menuBar.add(MyMenu.helpMenu);
//				MyMenu.menuBar.remove(MyMenu.fileManageClient);
//				MyMenu.fileManage.remove(MyMenu.recycle);
//			}
			initPopupMenu();
			
			stateField.setText(MyUser.state);						
			System.out.println(MyUser.userName);
			userField.setText(MyUser.userName);
			privilegeField.setText(MyUser.privilage+"");
		
			cardLayout.show(showPanel, "14");//ʹһ�����������ʹ�õ������¼�����ص������Ĵ�¥ҳ��
		}
			
		else if (e.getSource() == myMenu.quitItem)    //�˳���¼
		{ 
			if (MyUser.userName.equals("�ο�"))
			{
				JOptionPane.showMessageDialog(frame, "����δ��¼",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String res = MyLogin.quit(MyUser.userName);
			JOptionPane.showMessageDialog(frame, res,null, JOptionPane.INFORMATION_MESSAGE);
			//userField.setText(MyUser.userName);
			if(res.equals("�˳��ɹ�")) 
			{
				MyMenu.menuBar.removeAll();
				MyMenu.menuBar.add(MyMenu.loginMenu);
				MyMenu.menuBar.add(MyMenu.fileManageClient);
				MyMenu.menuBar.add(MyMenu.setMenu);
				MyMenu.menuBar.add(MyMenu.helpMenu);
			}
			if(tabPanel.isVisible()) tabPanel.setVisible(false);
			
			MyUser.userName = "�ο�";
			MyUser.state = "����";
			MyUser.privilage = 5;
			userField.setText(MyUser.userName);
			stateField.setText(MyUser.state);
			privilegeField.setText(MyUser.privilage+"");
			
//			MyLoginDialog.comboBox.setText(null); 
//			MyLoginDialog.textField .setText(null);
			cardLayout.show(showPanel, "14");
			
			
		} 
		
		else if (e.getSource() == myMenu.addUserItem)
		{
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			cardLayout.show(showPanel,"2");
		}
		
		else if (e.getSource() == myMenu.lookUserItem)
		{
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			cardLayout.show(showPanel,"3");
			MyLookUserPanel.getAllUser();
		}
		
		else if (e.getSource() == myMenu.setLogDateItem)
		{
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			cardLayout.show(showPanel,"4");
		}
		
		else if (e.getSource() == myMenu.lookLogItem)
		{
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			cardLayout.show(showPanel,"5");
			MyLookLogPanel.getAllLog();
		}
		else if(e.getSource() == myMenu.filesearchClient)//�ο�δ��¼�����ļ�����ѯ
		{
			//MyFileSearchDialogClient searchFileClient= new MyFileSearchDialogClient("D:\\ClientFile\\doc");
			MyFileSearchDialogClient searchFileClient= new MyFileSearchDialogClient(MyParameters.getSavePath());
			cardLayout.show(showPanel,"15");
		}
		else if(e.getSource() == myMenu.fileinfoSearchClient)//�ο�δ��¼���а�Ŀ¼�ؼ��ֲ�ѯ��ѯ
		{
			MySearchFileInfoDialog searchFileDialog = new MySearchFileInfoDialog();
			cardLayout.show(showPanel,"17");
		}
		else if (e.getSource() == myMenu.filesearch)//
		{    
			log.debug("�ļ����Ƽ���");
			if(MyUser.privilage>3){
				//JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				//MyFileSearchDialogClient searchFileClient= new MyFileSearchDialogClient("D:\\ClientFile\\doc");
				MyFileSearchDialogClient searchFileClient= new MyFileSearchDialogClient(MyParameters.getSavePath());
				cardLayout.show(showPanel,"15");
				//return;
			}else if(MyUser.privilage == 3){//�������������ǲ�������
				MyFileSearchDialogClient1 searchFileDialog = new MyFileSearchDialogClient1("doc");
				cardLayout.show(showPanel,"16");
			}
			else{
				log.debug("�ļ����Ƽ���");
				MyFileSearchDialog searchFileDialog = new MyFileSearchDialog("doc");
				cardLayout.show(showPanel,"12");
			}
		}
		else if (e.getSource() == myMenu.fileinfosearch)
		{
			if(MyUser.privilage>3){
//				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
//				return;
				//MyFileSearchDialogClient searchFileClient= new MyFileSearchDialogClient("D:\\ClientFile\\doc");
				MyFileSearchDialogClient searchFileClient= new MyFileSearchDialogClient(MyParameters.getSavePath());
				cardLayout.show(showPanel,"12");
			}
			MySearchFileInfoDialog searchFileDialog = new MySearchFileInfoDialog();
			cardLayout.show(showPanel,"6");
		}
		else if (e.getSource() == myMenu.recycle)
		{
			if(MyUser.privilage!=1){
				JOptionPane.showMessageDialog(null, "��ǰ�û��ȼ�û��Ȩ��",null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			cardLayout.show(showPanel,"7");
			String s = RecycleHandle.getRecycle();
			MyRecyclePanel.addFile(s);
		}
//		else if (e.getSource() == myMenu.local)
//		{
//			cardLayout.show(showPanel,"8");
//			List<Object[]> s = LocalFileIo.getObject();
//			MyClientFilePanel.addFile(s);
//			
//		}
		else if (e.getSource() == myMenu.syn)
		{
			String res = SynHandle.syn();
			if (res.equals("success"))
			{
				JOptionPane.showMessageDialog(null, "���������ݿ�ͬ���ɹ�");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "���������ݿ�ͬ��ʧ��");
			}
		}
		else if (e.getSource() == myMenu.localMessage)		// ͬ�����عؼ��ֱ�ǩ
		{
			if(LocalDirIo.rewriteKeywords())
			{
				JOptionPane.showMessageDialog(null, "���عؼ��ֱ�ǩ���³ɹ�");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "���عؼ��ֱ�ǩ����ʧ��");
			}
		}
		else if (e.getSource() == myMenu.updatePwd)
		{
			if (MyUser.userName.equals("�ο�"))
			{
				JOptionPane.showMessageDialog(null, "�㻹δ��¼",null, JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				MyPersonalPanel.setuser(MyUser.userName);
				cardLayout.show(showPanel,"10");
			}
			
		}
		else if (e.getSource() == myMenu.history)
		{
			if(tabPanel.isVisible())
			{
				tabPanel.setVisible(false);
			}
			else
			{
				tabPanel.setVisible(true);
				mainPanel.setDividerLocation(0.7);
			}
		}
		
		
	}
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			String theme = null;
			switch(MyParameters.getTheme())
			{
			case "windows" :
				theme = UIManager.getSystemLookAndFeelClassName();
				UIManager.setLookAndFeel(theme);
				break;
			case "Nimbus" :
				theme = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
				UIManager.setLookAndFeel(theme);
				break;
			case "Motif" : 
				theme = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
				UIManager.setLookAndFeel(theme);
				break;
			default :
				theme = "org.jvnet.substance.skin."+MyParameters.getTheme();
				SubstanceLookAndFeel.setSkin(theme);
				SubstanceLookAndFeel.setCurrentButtonShaper(new StandardButtonShaper());
				break;
			}
			//UIManager.setLookAndFeel(theme);
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");    //����macϵͳ�²���
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyMainFrame window = new MyMainFrame();
					window.frame.setVisible(true);
					window.mainPanel.setDividerLocation(0.7);  //���±����������֮�󲼾ֿɼ�֮�����Ч
					log.info("start up");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
