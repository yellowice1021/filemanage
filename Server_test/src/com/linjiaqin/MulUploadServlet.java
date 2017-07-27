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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//�������ڸ�ʽ
        String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
      
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("utf-8");  
         
		File file = null;
		String userName = null;
		String serverPath = null;
		String fieldName = null;  
        String fileName = null; 
        
        //����ǲ��Ǵ����ϴ��ļ�  
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
          
        System.out.println("have doc to upload " + isMultipart);
        
        if(isMultipart){  

        	
            DiskFileItemFactory factory = new DiskFileItemFactory();  
            
            //ָ�����ڴ��л������ݴ�С,��λΪbyte,������Ϊ1Mb  
            factory.setSizeThreshold(1024*1024);  
           
            //����һ���ļ���С����getSizeThreshold()��ֵʱ���ݴ����Ӳ�̵�Ŀ¼   
            //factory.setRepository(new File("D:\\temp"));  
            factory.setRepository(new File(Conf.cache));  
            
            
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
            
            if(items != null)
            {  
                //��������Ŀ  
                Iterator<FileItem> iter = items.iterator();  
                while (iter.hasNext()) 
                {  
                    FileItem item = iter.next(); 
                    
                    //�������ͨ������  
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
                        System.out.println("����:" + name + " ����ֵ:" + value);  
                    }  
                    //������ϴ��ļ�  
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
                
                
                fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // ����ϴ��ļ����ļ���  
                log.debug(userName + ":" + serverPath);
                String filePath = serverPath +"\\" + fileName;
                log.debug(filePath);
                ////////int version = FileDbDao.getFileVersion(filePath);
                
               
                String tmpfileName = fileName;// ����ϴ��ļ����ļ���  
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
                		log.debug("success");           //���ļ�������
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
                    
                    log.info("�ϴ��ļ�"+serverPath +"\\" + verFile.getName());
                    LogDbDao.insertLog(date, userName, "�ϴ��ļ�"+fileName);
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
