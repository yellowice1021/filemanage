package com.suct.client.print;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;

import com.scut.tools.MyParameters;

public class ImagePrint {
	
	private static String defaultPath = "print\\image\\";
	private static String savePath;
	private static String imagePath;

	// ��ȡָ����Ļ����
	public static void screenToImage(int x, int y, int width, int height) {
		
		//savePath = MyParameters.getSavePath() + "\\" + defaultPath;
        String folder = "print.png";
        
        //imagePath = savePath + folder;
        imagePath = defaultPath + folder;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, width, height));
        // ��ͼ�����·�� 
        File screenFile = new File(defaultPath);    
        // ���·��������,�򴴽�  
        if (!screenFile.getParentFile().exists()) {  
            screenFile.getParentFile().mkdirs();  
        } 
        //�ж��ļ��Ƿ���ڣ������ھʹ����ļ�
        if(!screenFile.exists()&& !screenFile .isDirectory()) {
            screenFile.mkdir();
        }
        
        File f = new File(screenFile, folder);        
        try {
			ImageIO.write(image, "png", f);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        // ��ӡͼƬ
        printToImage();
	}
	
	// ��ӡͼƬ
	public static void printToImage()
	{
		// ���ô������
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
	    // ������ӡ�������Լ�
	    HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        // ���ô�ӡ��ʽ����Ϊδȷ�����ͣ�����ѡ��autosense
	    DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
	    // �������еĿ��õĴ�ӡ����
	    PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
	    // ���û�л�ȡ��ӡ��
		if (printService.length == 0) {
			// ��ֹ����
			return;
		}
	    // ��λĬ�ϵĴ�ӡ����
	    PrintService defaultService = PrintServiceLookup
	                     .lookupDefaultPrintService();
	    // ��ʾ��ӡ�Ի���
	    PrintService service = ServiceUI.printDialog(null, screenWidth/2, screenHeight/2,
	                     printService, defaultService, flavor, pras);
	    if (service != null) {
	    	try {
	    		DocPrintJob job = service.createPrintJob(); // ������ӡ��ҵ
	            FileInputStream fis = new FileInputStream(imagePath); // �������ӡ���ļ���
	            DocAttributeSet das = new HashDocAttributeSet();
	            Doc doc = new SimpleDoc(fis, flavor, das);
	            job.print(doc, pras);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	    }
	}
	
}
