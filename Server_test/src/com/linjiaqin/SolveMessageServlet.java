package com.linjiaqin;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SolveMessageServlet
 */
@WebServlet("/SolveMessageServlet")
public class SolveMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolveMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");  
		response.setCharacterEncoding("utf-8"); 
		
		//这是服务器文档的根目录
		String serverPath = request.getSession().getServletContext().getRealPath("") +"doc\\";
		
		
		File file = null;
		try
		{
			String userName = request.getParameter("userName");
			String fileName = request.getParameter("message");
			System.out.println(fileName);
			if (fileName.equals("服务器"))
			{
				file = new File(serverPath);
			}
			else
			{
				file = new File(serverPath + fileName);
			}
			System.out.println(file.toString());
			String[] files = null;
			if (file.isDirectory())
			{
				files = file.list();
				for (String each: files)
				{
					System.out.println(each + response.getCharacterEncoding());
					response.getWriter().append(each + ",,");
					//response.getOutputStream().write(each.getBytes("utf-8"));
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.getWriter().append("文件不存在");
		}
		finally
		{
			
		}
		
		response.getWriter().append("Served at: ").append(serverPath);
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
