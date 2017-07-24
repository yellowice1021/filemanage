package com.scut.tools;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.sun.star.io.ConnectException;

public class MyOfficeImage {
	 public static final String tmpDir = "tmp/";

	 public static void pdf2Img(String pdfPath)  
	 {  
		
		File file = new File(pdfPath);
		if ( !file.exists() )
		{
			return;
		}
		
		String imgName = pdfPath.substring(pdfPath.lastIndexOf("/"), pdfPath.lastIndexOf(".")) + ".png";
		
		PDDocument doc = null;
		List<BufferedImage> list = new ArrayList<BufferedImage>();
		int heights = 0;
		try {
			doc = PDDocument.load(file);
			PDFRenderer render = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for(int i = 0; i < pageCount; i++)
			{
				BufferedImage image = render.renderImage(i);
				list.add(image);
				heights += image.getHeight();
				//ImageIO.write(image, "PNG", new File("doc/"+dir[0]+"/"+i+".png"));
			}
			
			imgs2img(list, tmpDir + imgName, heights);
			
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		finally
		{
			if (doc != null) 
			{
				try {
					doc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	 }  
	 
	 /*
	  * 不建议这样子的写法，因为用了太多临时变量，没必要
	  */
	 @Deprecated
	 public static void imgs2imgold(List<BufferedImage> list, String imgpath)
	 {
		 int imgCount = list.size();
		 List<int[]> imgRgb = new ArrayList<int[]>();  //总数据
		 int[] tmpImgRgb;
		 int height = 0;   //总高度
		 int width = 0;
		 int tmpHeight = 0;  //当前偏移高度
		 int[] heightArray = new int[imgCount];
		 BufferedImage buffer = null;
		 for(int i = 0; i < imgCount; i++)
		 {
			 buffer = list.get(i);
			 heightArray[i] = buffer.getHeight();
			 
			 if (i == 0) width = buffer.getWidth();
			 height += heightArray[i];   
			 
			 tmpImgRgb = new int[width * heightArray[i]];
			 tmpImgRgb = buffer.getRGB(0, 0, width, heightArray[i], tmpImgRgb, 0, width);
			 imgRgb.add(tmpImgRgb);
		 }
		 
		 BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		 for (int i = 0; i < imgCount; i++)
		 {
			 if (i != 0) tmpHeight += heightArray[i];
			 imageResult.setRGB(0, tmpHeight, width, heightArray[i], imgRgb.get(i), 0, width);
		 }
		 try {
			ImageIO.write(imageResult, "PNG", new File(imgpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public static void imgs2img(List<BufferedImage> list, String imgpath, int heights)
	 {
		 //heights;//总高度
		 int imgCount = list.size();                   //总图片数
		 int[] tmpImgRgb;                              //临时存储图片的临时像素值
		 int width = list.get(0).getWidth();           //图片的宽度，这里默认所以图片的宽度是一样的
		 int tmpHeight = 0;                            //当前偏移高度
		 BufferedImage buffer = null;                  //用来临时存储图片
		 //用来存储最后那张总的图片
		 BufferedImage imageResult = new BufferedImage(width, heights, BufferedImage.TYPE_INT_RGB);
		 
		 for(int i = 0; i < imgCount; i++)
		 {
			 buffer = list.get(i);
			 if (i != 0) tmpHeight += buffer.getHeight();
			 
			 tmpImgRgb = new int[width * buffer.getHeight()];
			 tmpImgRgb = buffer.getRGB(0, 0, width, buffer.getHeight(), tmpImgRgb, 0, width);
			 
			 imageResult.setRGB(0, tmpHeight, width, buffer.getHeight(), tmpImgRgb, 0, width);
		 }
		 
		 try 
		 {
			ImageIO.write(imageResult, "PNG", new File(imgpath));
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	 }
	 
	  public static String office2Pdf(String officePath)
	  {  
		    File inputfile = new File(officePath);
		    File outputFile = null;
			if ( !inputfile.exists() )
			{
				return null;
			}
			
			String outPdf = tmpDir + officePath.substring(officePath.lastIndexOf('/'), officePath.lastIndexOf('.'))
			             + ".pdf";
			
		    try {  
	            // 如果目标路径不存在, 则新建该路径  
		    	outputFile = new File(outPdf);  
	            // connect to an OpenOffice.org instance running on port 8100  
	            OpenOfficeConnection connection = new SocketOpenOfficeConnection(  
	                    "127.0.0.1", 8100);  
	            connection.connect();  
	            // convert  
	            DocumentConverter converter = new OpenOfficeDocumentConverter(  
	                    connection);  
	            converter.convert(inputfile, outputFile);  
	            // close the connection  
	            connection.disconnect();  
	        } catch (IOException e) {  
	            e.printStackTrace(); 
	            return null;  
	        }  
	        //return outputFile.getAbsolutePath();  
	        return outPdf;
	        
	    }  	
	  
	  
	  public static void office2Imag(String docPath)
	  {  
			   String pdfPath = office2Pdf(docPath);
			   pdf2Img(pdfPath);   
	  }  
	  
//	  public static void main(String[] args)
//	  {
//			// pdf2Img("doc/a.pdf");
//			// pdf2Img("doc/基于交通视频的运动目标检测和跟踪.pdf");
//			 //office2Imags("doc/自动化毕业论文开题报告范文.docx");
//			 office2Imag("tmp/决算收支.xls");
//	  }
	  
}
