package com.linjiaqin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.scut.server.conf.MyPath;
import com.scut.server.funtion.FileList;

/**
 * Servlet implementation class SonFileListServlet
 */
@WebServlet("/SonFileListServlet")
public class SonFileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("server");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SonFileListServlet() {
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
		String path = MyPath.serverRootPath + "doc\\";
		String filePath = request.getParameter("filepath");
		String s = FileList.getFileInfo(filePath, MyPath.serverRootPath.length());
		response.getWriter().append(s);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
