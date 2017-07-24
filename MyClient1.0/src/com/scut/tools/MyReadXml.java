package com.scut.tools;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * ���Ƕ�xml��ȡ��һ��ͨ�ù�����
 * @author��linjiaqin
 * @date: 2017.01.08
 * @version: 1.0
 */
public class MyReadXml {
	
    private static Logger log = Logger.getLogger("client"); //���Logger���Ѿ�������Ϊclient��log����ֱ�ӻ�ã����򴴽�

	//����xml�ļ��������Ϊ�ļ�·��
	//����document�����Ժ�������в���
	public static Document parse(String path)
	{
		//����SAXReader��ȡ����ר�����ڶ�ȡxml
		SAXReader reader = new SAXReader();
		
		Document document = null;
		try {
			document = reader.read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.toString());
		}
		return document;
	}
	
	/*
	 * �������xml�Ĵ���
	 */
	public void display(Document docment)
	{
		//��ȡ���ڵ����
		Element element = docment.getRootElement();
		
		//���ڵ������
		log.debug("���ڵ������" + element.getName());
		
		//���ڵ��������Ŀ
		log.debug("������Ŀ"+ element.attributeCount());
		
	    //��ȡ����id��ֵ
		log.debug("����id��ֵ"+element.attributeValue("ids"));
		
		//���ڵ���ı�,�ỻ�У���Ϊ��tab�ͻ��з���������getTextTrim()
		log.debug("���ڵ���ı� " +  element.getTextTrim());
		//���ص�ǰ�ڵ�ݹ������ӽڵ���ı���Ϣ
		log.debug("�����ӽڵ������" + element.getStringValue());
		
		//ȡ�ӽڵ����
		Element ipElement = element.element("server_ip");
		log.debug("server_ip:   " + ipElement.getTextTrim());
		Element portElements = element.element("server_port");
		log.debug("server_port:  " + portElements.getTextTrim());	
		
		//�������ڵ���������ԵĽڵ�
		Iterator<Element> it = element.elementIterator();  
        // ����  
        while (it.hasNext()) {  
            // ��ȡĳ���ӽڵ����  
            Element e = it.next();  
            log.debug("�ӽڵ������" + e.getName());
            log.debug("�ӽڵ���ı� " + e.getTextTrim()); 
        }  
        
	}
	
	public void listSonNodes(Element node) {  
        System.out.println("��ǰ�ڵ�����ƣ�" + node.getName());  
        // ��ȡ��ǰ�ڵ���������Խڵ�  
        List<Attribute> list = node.attributes();  
        // �������Խڵ�  
        for (Attribute attr : list) {  
            System.out.println(attr.getText() + "-----" + attr.getName()  
                    + "---" + attr.getValue());  
        }  
        if (!(node.getTextTrim().equals(""))) {  
            System.out.println("�ı����ݣ�������" + node.getText());  
        }  
    }  
	
	public static String getFirstLevelValue(Document docment, String sonNodeName)
	{
		//��ȡ���ڵ����
		Element element = docment.getRootElement().element(sonNodeName);
		log.debug("get server.xml " + sonNodeName+":"+element.getTextTrim());
		return element.getTextTrim();
	}
	
	public static boolean setFirstLevelValue(Document docment, String sonNodeName, String sonNodeValue)
	{
		
		try
		{
			Element element = docment.getRootElement().element(sonNodeName);
			element.setText(sonNodeValue);
			log.info("set server.xml " + sonNodeName+":"+sonNodeValue);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.warn(e);
			return false;
		}
		return true;
	}
	
	public static boolean delFirstLevelValue(Document docment, String sonNodeName, String sonNodeValue)
	{
		
		try
		{
			Element element = docment.getRootElement().element(sonNodeName);
			docment.getRootElement().remove(element);
			log.debug("set  " + sonNodeName+":"+sonNodeValue);
		}
		catch(Exception e)
		{
			log.warn(e.getMessage());
			return false;
		}
		return true;
	}
	
//	public static void main(String args[]){
//		MyReadXml readxml = new MyReadXml();
//		Document docment = readxml.parse("conf/server.xml");
//		readxml.display(docment);
//	}
}
