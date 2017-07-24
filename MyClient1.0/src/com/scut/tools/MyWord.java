package com.scut.tools;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class MyWord {
	
	private static Logger log = Logger.getLogger("client");
	
	public static String readDocx(String path)
	{
		String text = null;
		InputStream is = null;
		try {
			is = new FileInputStream(path);
			XWPFDocument doc = new XWPFDocument(is);  
		    XWPFWordExtractor extractor = new XWPFWordExtractor(doc);  
		    text = extractor.getText(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{  
            if(is != null)  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }
		}
		
		return text;
	}
	
	public static String readDoc(String path)
	{
		String text = null;
		FileInputStream in = null;
		try 
		{
			in = new FileInputStream(path);
			WordExtractor extractor = new WordExtractor(in);
			text = extractor.getText();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{  
            if(in != null)  
                try {  
                    in.close();  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }
		}
		return text;
	}
	
	//一样的效果和readDoc，只不过是对每页遍历，可以自己进行一些控制
	public static String readDoc1(String path)
	{
		String content="";  
        InputStream istream=null;  
        try {  
            istream = new FileInputStream(path);  
            WordExtractor wordExtractor=new WordExtractor(istream);  
            String[] para=wordExtractor.getParagraphText();  
            for (String string : para) 
            {  
                content+=string; 
                content+='\n';
            }  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
        finally
        {  
            if(istream!=null)  
                try {  
                    istream.close();  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
        }  
          
        return content; 
	}
	
    
	@Deprecated
	public static String readWord(String name)
	{
		ByteArrayOutputStream byteOS = new ByteArrayOutputStream(); 
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(name);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			log.warn(e2);
		}  

		byte[] by = new byte[512];  
		int t;
		try 
		{
			t = fis.read(by,0,by.length);
			while(t>0)
			{   
				byteOS.write(by, 0, 512);  //这里别写成t，写够512，呵呵，SB的方法对付SB的Java API
				t = fis.read(by,0,by.length);  
			} 
			byteOS.close(); 
		} 
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1);
		} 

		InputStream byteIS = new ByteArrayInputStream(byteOS.toByteArray());  
		String Data = null;
		WordExtractor extractor = null ;
		try 
		{
//		   extractor = new WordExtractor();
//		   Data = extractor.extractText(byteIS);   
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.warn(e);
		}
		return Data;
	}
	
}