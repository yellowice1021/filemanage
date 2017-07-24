package com.scut.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MyExcel 
{
	private static Logger log = Logger.getLogger("client"); 
	
	public static String readXls1(String path)
	{
		FileInputStream is;
		String text = null;
		try {
			is = new FileInputStream(path);
			HSSFWorkbook wb=new HSSFWorkbook(new POIFSFileSystem(is));
	        ExcelExtractor extractor=new ExcelExtractor(wb);
	        extractor.setFormulasNotResults(false);
	        extractor.setIncludeSheetNames(true);
	        text = extractor.getText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return text;
    }
	
	public static String readXls(String path)
	{
		String text="";  
	     try 
	     {  
	          FileInputStream is =  new FileInputStream(path);
	          HSSFWorkbook excel=new HSSFWorkbook(is);  
             //��ȡ��һ��sheet  
             HSSFSheet sheet0=excel.getSheetAt(0);  
             for (Iterator rowIterator=sheet0.iterator();rowIterator.hasNext();) 
             {  
                 HSSFRow row=(HSSFRow) rowIterator.next();  
                 for (Iterator iterator=row.cellIterator();iterator.hasNext();) 
                 {  
                     HSSFCell cell=(HSSFCell) iterator.next();  
                     //���ݵ�Ԫ�ĵ����� ��ȡ��Ӧ�Ľ��  
                     if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING) text+=cell.getStringCellValue()+"\t";  
                     else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC) text+=cell.getNumericCellValue()+"\t";  
                     else if(cell.getCellType()==HSSFCell.CELL_TYPE_FORMULA) text+=cell.getCellFormula()+"\t";  
                 }  
               text+="\n";  
             }          
	     } 
	     catch (Exception e) 
	     {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            log.warn(e);
	     }  
 	     
	     return text; 
	}
	public static String readXlsx(String path)
	{
	     String text="";  
	     try 
	     {  
	          OPCPackage pkg=OPCPackage.open(path);  
	          XSSFWorkbook excel=new XSSFWorkbook(pkg);  
              //��ȡ��һ��sheet  
              XSSFSheet sheet0=excel.getSheetAt(0);  
              for (Iterator rowIterator=sheet0.iterator();rowIterator.hasNext();) 
              {  
                  XSSFRow row=(XSSFRow) rowIterator.next();  
                  for (Iterator iterator=row.cellIterator();iterator.hasNext();) 
                  {  
                      XSSFCell cell=(XSSFCell) iterator.next();  
                      //���ݵ�Ԫ�ĵ����� ��ȡ��Ӧ�Ľ��  
                      if(cell.getCellType()==XSSFCell.CELL_TYPE_STRING) text+=cell.getStringCellValue()+"\t";  
                      else if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC) text+=cell.getNumericCellValue()+"\t";  
                      else if(cell.getCellType()==XSSFCell.CELL_TYPE_FORMULA) text+=cell.getCellFormula()+"\t";  
                  }  
                text+="\n";  
              }          
	     } 
	     catch (Exception e) 
	     {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            log.warn(e);
	     }  
  	     
	     return text;  
	}
	
}