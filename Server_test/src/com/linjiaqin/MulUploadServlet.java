package com.linjiaqin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.scut.server.conf.Conf;
import com.scut.server.conf.MyPath;
import com.scut.server.db.FileDbDao;
import com.scut.server.db.HisDbDao;
import com.scut.server.db.LogDbDao;

/**
 * Servlet implementation class MulUploadServlet
 */
@WebServlet("/MulUploadServlet")
public class MulUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("server");      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MulUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String serverRootPath = getServletContext().getRealPath("");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
      
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("utf-8");  
         
		File file = null;
		String userName = null;
		String serverPath = null;
		String fieldName = null;  
        String fileName = null; 
        
        //检测是不是存在上传文件  
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
          
        System.out.println("have doc to upload " + isMultipart);
        
        if(isMultipart){  

        	
            DiskFileItemFactory factory = new DiskFileItemFactory();  
            
            //指定在内存中缓存数据大小,单位为byte,这里设为1Mb  
            factory.setSizeThreshold(1024*1024);  
           
            //设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录   
            //factory.setRepository(new File("D:\\temp"));  
            factory.setRepository(new File(Conf.cache));  
            
            
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
            
            if(items != null)
            {  
                //解析表单项目  
                Iterator<FileItem> iter = items.iterator();  
                while (iter.hasNext()) 
                {  
                    FileItem item = iter.next(); 
                    
                    //如果是普通表单属性  
                    if (item.isFormField() ) 
                    {  
                       
                        String name = item.getFieldName();
                     
                        String value = item.getString("UTF-8");
                        
                        if (name.equals("clientUser"))
                        {
                        	userName = value;
                        }
                        if (name.equals("serverPath"))
                        {
                        	serverPath = value;
                        }
                        System.out.println("属性:" + name + " 属性值:" + value);  
                    }  
                    //如果是上传文件  
                    else 
                    {  
                    	fieldName = item.getFieldName();  
                        fileName = item.getName(); 
                        log.debug(fileName);
                        try 
                        {  
                            //item.write(new File("D:\\tmp", fileName));
                        	item.write(new File(Conf.tmpPath, fileName));
                            Thread.sleep(10);
                        } 
                        catch (Exception e) 
                        { 
                            e.printStackTrace();  
                        } 
                    } 
                }
                
                
                fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 获得上传文件的文件名  
                log.debug(userName + ":" + serverPath);
                String filePath = serverPath +"\\" + fileName;
                log.debug(filePath);
                ////////int version = FileDbDao.getFileVersion(filePath);
                
               
                String tmpfileName = fileName;// 获得上传文件的文件名  
                log.debug("upload file name:" + tmpfileName);
                
                String name = tmpfileName;
                String names = name.substring(0, name.lastIndexOf("."));
                String type = name.substring(name.lastIndexOf(".")+1, name.length());
                
                char version = (char)(HisDbDao.searchVersion(serverPath,names)-1+97);
              
                String filename = names +"_" +version + "." +type;
                
                File tmpfile = new File(Conf.tmpPath, fileName);  

                //String filePaths = serverRootPath + serverPath +"\\" + sourcefile.getName();

                File oldFile = new File(MyPath.serverRootPath  + serverPath, fileName);
                File verFile = new File(MyPath.serverRootPath  + serverPath, filename);
                log.debug(serverPath+"\\"+fileName +"::::"+ serverPath+"\\"+filename);
                if (oldFile.isFile() && oldFile.exists()) 
                {   
                	if (oldFile.renameTo(verFile))
                		log.debug("success");           //旧文件重命名
                	else
                	{
                		log.debug("fail"); 
                	}
                }
                if (tmpfile.renameTo(oldFile) )
                {  
                    log.debug("File is moved successful!");  
/////////           FileDbDao.insertFile(fileName, filePath);
                    
          ///          FileDbDao.insertFile(fileName, serverPath, filename);
                    log.debug(serverPath+"\\"+fileName +"::::"+ serverPath+"\\"+filename);
                    //HisDbDao.insertVersion(serverPath+"\\"+fileName, serverPath+"\\"+filename, date);
                    log.debug(serverPath+"\\"+fileName +"::::"+ serverPath+"\\"+filename);
                    
                    log.info("上传文件"+serverPath +"\\" + verFile.getName());
                    LogDbDao.insertLog(date, userName, "上传文件"+fileName);
                    response.getWriter().append("firstupload");
                   
                } 
                else
                {
                	 log.debug("File is moved failed!"); 
                }
         
            }  
        }  
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
