package com.linjiaqin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import com.scut.server.conf.MyPath;
import com.scut.server.db.LogDbDao;

/**
 * Servlet implementation class DownLoadServlet
 */
@WebServlet("/DownLoadServlet")
public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("server");     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//�������ļ�ϵͳ��Ŀ¼
        //String serverRootPath = request.getSession().getServletContext().getRealPath("");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��

        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("utf-8");  
         
        String userName = request.getParameter("clientUser");
		String fileName = request.getParameter("serverPath");
		log.debug(userName + "," + fileName);
		File file = null;
		try
		{
			
			if (fileName.equals("\\"))
			{
				file = new File(MyPath.serverRootPath);
			}
			else
			{
				file = new File(MyPath.serverRootPath + fileName);
			}
			System.out.println(file.toString());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("�ļ�������");
			//response.getWriter().append("�ļ�������");
		}
		finally
		{
			
		}
		
		InputStream in = null;
		OutputStream out = null;
		try
		{
		response.setContentLength((int) file.length());
		response.setHeader("Accept-Ranges", "bytes");
		System.out.println("���ؿ�ʼ");
		int readLength = 0;
		int BUFFER_SIZE = 4096;
		
		in = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);
		out = new BufferedOutputStream(response.getOutputStream());
		
		byte[] buffer = new byte[BUFFER_SIZE];
		while ((readLength=in.read(buffer)) > 0) 
		{
			byte[] bytes = new byte[readLength];
			System.arraycopy(buffer, 0, bytes, 0, readLength);
			out.write(bytes);
		}
		     
	    out.flush();
	    log.debug("���ؽ���");  
	    LogDbDao.insertLog(date, userName, "�����ļ�"+fileName);
	    //response.addHeader("token", "hello 1");
	    //response.getWriter().append("Served at: ").append(serverPath);
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (in != null)
			 {
				in.close(); 
			 }
			 if(out != null)
			 {
				 out.close();
			 }
			
		}
		
		//���ܴ��������ݣ��������������ļ�����
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
