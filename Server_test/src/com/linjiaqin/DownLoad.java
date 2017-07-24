package com.linjiaqin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownLoad
 */
@WebServlet("/DownLoad")
public class DownLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("receive a download");
		
		request.setCharacterEncoding("utf-8");  
		response.setCharacterEncoding("utf-8"); 
		
		//这是服务器文档的根目录
		String serverPath = request.getSession().getServletContext().getRealPath("");
		
		
		File file = null;
		try
		{
			String userName = request.getParameter("userName");
			String fileName = request.getParameter("fileName");
			//String userName = request.getHeader("userName");
			//String fileName = request.getHeader("fileName");
			//String userName = new String(request.getHeader("userName").getBytes("ISO8859-1"), "gb2312");
			//String fileName = new String(request.getHeader("fileName").getBytes("ISO8859-1"), "gb2312");
			System.out.println(userName);
			System.out.println(fileName);
			if (fileName.equals("\\"))
			{
				file = new File(serverPath);
			}
			else
			{
				file = new File(serverPath + fileName);
			}
			System.out.println(file.toString());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("文件不存在");
			//response.getWriter().append("文件不存在");
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
		System.out.println("下载开始");
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
	    System.out.println("下载结束");        
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
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
