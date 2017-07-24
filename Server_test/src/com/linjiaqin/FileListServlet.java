package com.linjiaqin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.scut.server.conf.MyPath;
import com.scut.server.db.DirDbDao;
import com.scut.server.db.FileDbDao;
import com.scut.server.funtion.FileList;

/**
 * Servlet implementation class FileListServlet
 */
@WebServlet("/FileListServlet")
public class FileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("server");      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileListServlet() {
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
		
		
		
		String s = "";
//		s += DirDbDao.getAllDirPath();
//		s += FileDbDao.getAllFilePath();
		FileList.s = "";
		String path = MyPath.serverRootPath + "doc\\";
		FileList.getFiles(path, MyPath.serverRootPath.length());
		s = FileList.s;
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
