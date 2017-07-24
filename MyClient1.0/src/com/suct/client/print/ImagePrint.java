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

	// 截取指定屏幕内容
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
        // 截图保存的路径 
        File screenFile = new File(defaultPath);    
        // 如果路径不存在,则创建  
        if (!screenFile.getParentFile().exists()) {  
            screenFile.getParentFile().mkdirs();  
        } 
        //判断文件是否存在，不存在就创建文件
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
        
        // 打印图片
        printToImage();
	}
	
	// 打印图片
	public static void printToImage()
	{
		// 设置窗体居中
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
	    // 构建打印请求属性集
	    HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        // 设置打印格式，因为未确定类型，所以选择autosense
	    DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
	    // 查找所有的可用的打印服务
	    PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
	    // 如果没有获取打印机
		if (printService.length == 0) {
			// 终止程序
			return;
		}
	    // 定位默认的打印服务
	    PrintService defaultService = PrintServiceLookup
	                     .lookupDefaultPrintService();
	    // 显示打印对话框
	    PrintService service = ServiceUI.printDialog(null, screenWidth/2, screenHeight/2,
	                     printService, defaultService, flavor, pras);
	    if (service != null) {
	    	try {
	    		DocPrintJob job = service.createPrintJob(); // 创建打印作业
	            FileInputStream fis = new FileInputStream(imagePath); // 构造待打印的文件流
	            DocAttributeSet das = new HashDocAttributeSet();
	            Doc doc = new SimpleDoc(fis, flavor, das);
	            job.print(doc, pras);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	    }
	}
	
}
