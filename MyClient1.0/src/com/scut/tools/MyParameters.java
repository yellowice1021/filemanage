package com.scut.tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.scut.client.ui.MyServerDialog;

/*
 * ���úͼ��ز����Ĺ����࣬��MyRealXml�����˷�װ
 */
public class MyParameters {
//	public enum Theme
//	{
//		WINDOWS,JAVA,NIMBUS
//	}
	private static String port;
	public static String ip = "127.0.0.1";
	private static String ippath;
	private static String theme;
	private static String path = "conf/server.xml";
	private static String savePath;
	private static Document doc;
	private static Logger log = Logger.getLogger("client");
	
	static 
	{
		doc = MyReadXml.parse(path);  
		ip = MyReadXml.getFirstLevelValue(doc, "server_ip");
		savePath = MyReadXml.getFirstLevelValue(doc, "client_path");
		ippath = MyReadXml.getFirstLevelValue(doc, "server_ippath");
		port = MyReadXml.getFirstLevelValue(doc, "server_port");
		theme = getXmlValue("client_theme");       //�Ѿ���װ��
	}
	//��Ҫע���ʼ����˳��docһ�������ȵ�
	public static String init() 
	{
		//doc = MyReadXml.parse(path);            
		//ip = MyReadXml.getFirstLevelValue(doc, "server_ip");
		//ippath = MyReadXml.getFirstLevelValue(doc, "server_ippath");
		
		log.debug(ippath);
		
		if(!ippath.equals(""))
		{
			ip = MyServerDialog.analysisIp(ippath);
			log.debug(ip+":"+ippath);
			if (ip.equals("false"))
			{
				return ip;
			}
			else
			{
				setIp(ip);
				return "true";
			}
		}
		
		return "true";
		
	}
	
	public static void refresh()
	{
		doc = MyReadXml.parse(path);            
		//ip = MyReadXml.getFirstLevelValue(doc, "server_ip");
		port = MyReadXml.getFirstLevelValue(doc, "server_port");
		savePath = MyReadXml.getFirstLevelValue(doc, "client_path");
		ippath = MyReadXml.getFirstLevelValue(doc, "server_ippath");
		//if (!ip.equals("127.0.0.1")) ip = MyServerDialog.analysisIp(ippath);
		if(!ippath.equals(""))
		{
			ip = MyServerDialog.analysisIp(ippath);
		}
		//setIp(ip);  
		theme = getXmlValue("client_theme");
	}
	
	public MyParameters()
	{
		//setTheme(Theme.WINDOWS);
	}
	
	public static String getXmlValue(String sonNodeName)
	{
		return MyReadXml.getFirstLevelValue(doc, sonNodeName);
	}
	
	/*
	 * 1.0�汾����֧������
	 */
	public static void setXmlValue1(String sonNodeName, String sonNodeValue)
	{
		MyReadXml.setFirstLevelValue(doc, sonNodeName, sonNodeValue);
		Writer fileWriter = null;
		
		try {
			fileWriter = new FileWriter(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		}  
        //dom4j�ṩ��ר��д���ļ��Ķ���XMLWriter  
        XMLWriter xmlWriter = new XMLWriter(fileWriter);  
        try {
			xmlWriter.write(doc);
			xmlWriter.flush();  
	        xmlWriter.close();
	        log.debug("xmlд��ɹ�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}  
		//����д��֮��Ҫ����document�����¼������е����ã��������ڴ��ֵ����
		refresh();
	}
	public static void setXmlValue(String sonNodeName, String sonNodeValue)
	{
		MyReadXml.setFirstLevelValue(doc, sonNodeName, sonNodeValue);
		
        OutputFormat format = OutputFormat.createPrettyPrint();  
        format.setEncoding("UTF-8"); 
        FileOutputStream fos = null;
        try {
			fos = new FileOutputStream(path);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1);
		}
        XMLWriter xmlWriter;
		try {
			xmlWriter = new XMLWriter(fos,format);
			xmlWriter.write(doc); 
			xmlWriter.flush(); 
	        xmlWriter.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		}  
        
        //����д��֮��Ҫ����document�����¼������е����ã��������ڴ��ֵ����
		refresh();	
	}
	
	public static String getPort() 
	{
		return port;
	}
	public static void setPort(String port) 
	{
		setXmlValue("server_port", port);
	}
	public static void setIp(String ip)
	{
		MyParameters.ip = ip;
		setXmlValue("server_ip", ip);
	}
	public static String getIp() 
	{
		return ip;
	}
	public static void setSavePath(String savePath)
	{
		setXmlValue("client_path", savePath);
	}
	public static void setIppath(String ippath)
	{
		setXmlValue("server_ippath", ippath);
	}
	
	public static String getIppath()
	{
		return ippath;
	}
	public static String getSavePath()
	{
		return savePath;
	}
	
	public static String getTheme()
	{
		return theme;
	}
	
	public static void setTheme(String theme)
	{
		setXmlValue("client_theme", theme);
	}
	
//	public static void main(String[] args)
//	{
//		log.info(MyParameters.getXmlValue("server_port"));
//		MyParameters.setXmlValue("server_port","8080");
//		log.info(MyParameters.getXmlValue("server_port"));
//	}
	
}
