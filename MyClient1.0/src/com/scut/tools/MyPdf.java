package com.scut.tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class MyPdf {
	private static Logger log = Logger.getLogger("client");
	
	public static void createPdf(String filename)
	{
		
		Document document = new Document();
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.open();
			BaseFont bfChinese = BaseFont.createFont("conf/SIMKAI.TTF", 
					BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED); 
			          
		     Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);  
		     document.add(new Paragraph(" 产生的报告",FontChinese)); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		document.close();
	}
	
	public static String readPdf(String fileName)
	{
		PdfReader reader = null;
		StringBuffer buff = new StringBuffer();  
		try 
		{
			reader = new PdfReader(fileName);
			PdfReaderContentParser parser = new PdfReaderContentParser(reader); 
			TextExtractionStrategy strategy;  
			for (int i = 1; i <= reader.getNumberOfPages(); i++) 
			{  
				strategy = parser.processContent(i, new SimpleTextExtractionStrategy());  
			    buff.append(strategy.getResultantText());  
			}  
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		}  
		 
		return buff.toString();  
	}
}
