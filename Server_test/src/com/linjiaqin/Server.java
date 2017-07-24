package com.linjiaqin;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext; 

/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Server() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//������ʱ�����ϴ��ļ���·��
		File uploadFile = new File("D:\\tmp");
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }

        System.out.println("Come on");
        
        request.setCharacterEncoding("UTF-8");  
        //��ʵ��仰�����ǽ��뷽ʽ
        //request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("utf-8");  
         
        /*
        String username = request.getParameter("username");
		System.out.println("-username->>"+username);
        */
        //����ǲ��Ǵ����ϴ��ļ�  
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
          
        System.out.println("have doc to upload " + isMultipart);
        
        if(isMultipart){  

        	
            DiskFileItemFactory factory = new DiskFileItemFactory();  
            
            //ָ�����ڴ��л������ݴ�С,��λΪbyte,������Ϊ1Mb  
            factory.setSizeThreshold(1024*1024);  
           
            //����һ���ļ���С����getSizeThreshold()��ֵʱ���ݴ����Ӳ�̵�Ŀ¼   
            factory.setRepository(new File("D:\\temp"));  
            
            // Create a new file upload handler  
            ServletFileUpload upload = new ServletFileUpload(factory);  
           
            // ָ�������ϴ��ļ������ߴ�,��λ:�ֽڣ�������Ϊ50Mb    
            upload.setFileSizeMax(5000 * 1024 * 1024);    
            
            //ָ��һ���ϴ�����ļ����ܳߴ�,��λ:�ֽڣ�������Ϊ50Mb  
            upload.setSizeMax(5000 * 1024 * 1024);     
            upload.setHeaderEncoding("UTF-8");
            
            
            List<FileItem> items = null;  
              
            try 
            {  
                // ����request����  
                items = upload.parseRequest(new ServletRequestContext(request));
            } 
            catch (FileUploadException e)
            {  
                e.printStackTrace();  
            }  
            
            if(items != null){  
                //��������Ŀ  
                Iterator<FileItem> iter = items.iterator();  
                while (iter.hasNext()) {  
                    FileItem item = iter.next(); 
                    
                    //�������ͨ������  
                    if (item.isFormField() ) {  
                        //�൱��input��name����   <input type="text" name="content">  
                        String name = item.getFieldName();
                        ///////////////////////////////////
                        //String name = item.�����ܻ�ȡ����˵������
                        //input��value����  
                        String value = item.getString("UTF-8");
                        
                        System.out.println("����:" + name + " ����ֵ:" + value);  
                    }  
                    //������ϴ��ļ�  
                    else {  
                        //������  
                        String fieldName = item.getFieldName();  
                        
                        //�ϴ��ļ�·��  
                        String fileName = item.getName();     
                        System.out.println("upload file name1:" + fileName);
                        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// ����ϴ��ļ����ļ���  
                        System.out.println("upload file name2:" + fileName);
                        
                        try {  
                            item.write(new File(uploadFile, fileName));
                            Thread.sleep(10);
                        } catch (Exception e) { 
                        	System.out.println("write file Exception name :"+e.getClass().getName());
                            e.printStackTrace();  
                        }  
                    } 
                }  
            }  
        }  
        
        response.addHeader("token", "hello");
		response.getWriter().append("Served at  hhhh  : ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		doPost(request, response);
	}

}
