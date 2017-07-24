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
		
		//用来临时保存上传文件的路径
		File uploadFile = new File("D:\\tmp");
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }

        System.out.println("Come on");
        
        request.setCharacterEncoding("UTF-8");  
        //其实这句话讲的是解码方式
        //request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("utf-8");  
         
        /*
        String username = request.getParameter("username");
		System.out.println("-username->>"+username);
        */
        //检测是不是存在上传文件  
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
          
        System.out.println("have doc to upload " + isMultipart);
        
        if(isMultipart){  

        	
            DiskFileItemFactory factory = new DiskFileItemFactory();  
            
            //指定在内存中缓存数据大小,单位为byte,这里设为1Mb  
            factory.setSizeThreshold(1024*1024);  
           
            //设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录   
            factory.setRepository(new File("D:\\temp"));  
            
            // Create a new file upload handler  
            ServletFileUpload upload = new ServletFileUpload(factory);  
           
            // 指定单个上传文件的最大尺寸,单位:字节，这里设为50Mb    
            upload.setFileSizeMax(5000 * 1024 * 1024);    
            
            //指定一次上传多个文件的总尺寸,单位:字节，这里设为50Mb  
            upload.setSizeMax(5000 * 1024 * 1024);     
            upload.setHeaderEncoding("UTF-8");
            
            
            List<FileItem> items = null;  
              
            try 
            {  
                // 解析request请求  
                items = upload.parseRequest(new ServletRequestContext(request));
            } 
            catch (FileUploadException e)
            {  
                e.printStackTrace();  
            }  
            
            if(items != null){  
                //解析表单项目  
                Iterator<FileItem> iter = items.iterator();  
                while (iter.hasNext()) {  
                    FileItem item = iter.next(); 
                    
                    //如果是普通表单属性  
                    if (item.isFormField() ) {  
                        //相当于input的name属性   <input type="text" name="content">  
                        String name = item.getFieldName();
                        ///////////////////////////////////
                        //String name = item.看看能获取其他说明东西
                        //input的value属性  
                        String value = item.getString("UTF-8");
                        
                        System.out.println("属性:" + name + " 属性值:" + value);  
                    }  
                    //如果是上传文件  
                    else {  
                        //属性名  
                        String fieldName = item.getFieldName();  
                        
                        //上传文件路径  
                        String fileName = item.getName();     
                        System.out.println("upload file name1:" + fileName);
                        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 获得上传文件的文件名  
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
