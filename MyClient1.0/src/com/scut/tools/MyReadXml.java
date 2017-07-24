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
 * 这是对xml读取的一个通用工具类
 * @author：linjiaqin
 * @date: 2017.01.08
 * @version: 1.0
 */
public class MyReadXml {
	
    private static Logger log = Logger.getLogger("client"); //如果Logger中已经存在名为client的log，则直接获得，否则创建

	//解析xml文件，传入的为文件路径
	//返回document对象，以后对他进行操作
	public static Document parse(String path)
	{
		//创建SAXReader读取器，专门用于读取xml
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
	 * 测试输出xml的代码
	 */
	public void display(Document docment)
	{
		//获取根节点对象
		Element element = docment.getRootElement();
		
		//根节点的名字
		log.debug("根节点的名字" + element.getName());
		
		//根节点的属性数目
		log.debug("属性数目"+ element.attributeCount());
		
	    //获取属性id的值
		log.debug("属性id的值"+element.attributeValue("ids"));
		
		//根节点的文本,会换行，因为有tab和换行符，可以用getTextTrim()
		log.debug("根节点的文本 " +  element.getTextTrim());
		//返回当前节点递归所有子节点的文本信息
		log.debug("所有子节点的内容" + element.getStringValue());
		
		//取子节点对象
		Element ipElement = element.element("server_ip");
		log.debug("server_ip:   " + ipElement.getTextTrim());
		Element portElements = element.element("server_port");
		log.debug("server_port:  " + portElements.getTextTrim());	
		
		//遍历根节点下面的所以的节点
		Iterator<Element> it = element.elementIterator();  
        // 遍历  
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            log.debug("子节点的名字" + e.getName());
            log.debug("子节点的文本 " + e.getTextTrim()); 
        }  
        
	}
	
	public void listSonNodes(Element node) {  
        System.out.println("当前节点的名称：" + node.getName());  
        // 获取当前节点的所有属性节点  
        List<Attribute> list = node.attributes();  
        // 遍历属性节点  
        for (Attribute attr : list) {  
            System.out.println(attr.getText() + "-----" + attr.getName()  
                    + "---" + attr.getValue());  
        }  
        if (!(node.getTextTrim().equals(""))) {  
            System.out.println("文本内容：：：：" + node.getText());  
        }  
    }  
	
	public static String getFirstLevelValue(Document docment, String sonNodeName)
	{
		//获取根节点对象
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
