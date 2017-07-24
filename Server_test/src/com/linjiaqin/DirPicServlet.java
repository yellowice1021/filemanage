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
import com.scut.server.db.DirDbDao;
import com.scut.server.db.FileDbDao;
import com.scut.server.db.LogDbDao;

/**
 * Servlet implementation class DirPicServlet
 */
@WebServlet("/DirPicServlet")
public class DirPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("server"); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirPicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//String serverRootPath = getServletContext().getRealPath("");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
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
                        try 
                        {  
                            item.write(new File(Conf.tmpPath, fileName));
                            Thread.sleep(10);
                        } 
                        catch (Exception e) 
                        { 
                            e.printStackTrace();  
                        } 
                    } 
                }
                
               
                System.out.println(userName + ":" + serverPath);
                fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);// ����ϴ��ļ����ļ���  
                System.out.println("upload file name:" + fileName);
                File sourcefile = new File(Conf.tmpPath, fileName);  
               
                //String filePath = MyPath.serverRootPath + serverPath +"\\" + sourcefile.getName();
                String aimPath = Conf.DirPicPath + "\\" + serverPath;
                File aimFile = new File(aimPath, sourcefile.getName());
    	        //���ļ��ĸ�Ŀ¼�����ڣ��ȴ���
    	        if (!aimFile.getParentFile().exists())  
    			{
    	        	aimFile.getParentFile().mkdirs();
    			}
    	        
                log.debug(aimFile.getAbsolutePath());
                if (aimFile.isFile() && aimFile.exists()) 
                {  
                	aimFile.delete();  
                }
                if (sourcefile.renameTo(aimFile) )
                {  
                    log.debug("�ϴ�ͼƬFile is moved successful!"); 
                    //DirDbDao.addDirPic(serverPath, Conf.DirPicPath+"\\"+sourcefile.getName());
                    //DirDbDao.addDirPic(serverPath, Conf.DirPicPaths+"\\"+sourcefile.getName());
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
