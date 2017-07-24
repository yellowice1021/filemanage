package com.scut.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class MyPpt {
	public static String readPpt(String path)
	{
		String content="";  
		InputStream is = null; 
        try {  
        	is = new FileInputStream(path);

        	SlideShow ss=new SlideShow(new HSLFSlideShow(is));
            Slide[] slides=ss.getSlides();
            for(int i=0;i<slides.length;i++){
                //��ȡһ�Żõ�Ƭ�ı���
                String title=slides[i].getTitle();
                content += "��"+(i+1)+"�Żõ�Ƭ";
                if(title != null)
                {
                	content += "����:"+title;
                }
                content += '\n';
                //��ȡһ�Żõ�Ƭ������(��������)
                TextRun[] runs=slides[i].getTextRuns();
                for(int j=0;j<runs.length;j++)
                {
                    content += runs[j].getText();
                    content += '\n';
                }
                content += '\n';
            }
             
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
        finally
        {  
            if(is!=null)  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
        }       
        return content;  
	}
	
	public static String readPpt1(String path) 
	{
		InputStream is = null; 
		String text = null;
        	try {
				is = new FileInputStream(path);
				PowerPointExtractor extractor=new PowerPointExtractor(is);
				text = extractor.getText();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return text;
	}
	
	public static String readPptx(String path)
	{
		String content="";  
		InputStream is = null; 
        try {  
        	is = new FileInputStream(path);

            XMLSlideShow ppt=new XMLSlideShow(is);  
            for(XSLFSlide slide:ppt.getSlides())    //����ÿһҳppt 
            {  
                //content+=slide.getTitle()+"\t";  
                for(XSLFShape shape:slide.getShapes())
                {  
                    if(shape instanceof XSLFTextShape)  //��ȡ��ppt���ı���Ϣ  
                    { 
                        for(Iterator iterator=((XSLFTextShape) shape).iterator();iterator.hasNext();)
                        {  
                            //��ȡ��ÿһ�ε��ı���Ϣ  
                            XSLFTextParagraph paragraph=(XSLFTextParagraph) iterator.next();   
                            for (XSLFTextRun xslfTextRun : paragraph) 
                            {  
                                content+=xslfTextRun.getText()+"\t";  
                            }  
                        }  
                    }  
                }  
                //��ȡһ��ppt�����ݺ� ����  
                content+="\n";  
            }  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
        finally
        {  
            if(is!=null)  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
        }       
        return content;  
	}
//	public static void main(String[] args)
//	{
//		System.out.println(readPpt("E:\\��ѧʵ��\\�������---��ʾ.ppt"));
//	}
}
