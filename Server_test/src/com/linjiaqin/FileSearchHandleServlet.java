package com.linjiaqin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.scut.server.db.FileDbDao;
import com.scut.server.db.LogDbDao;
import com.scut.server.funtion.FindFile;

/**
 * Servlet implementation class FileSearchHandleServlet
 */
@WebServlet("/FileSearchHandleServlet")
public class FileSearchHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("server");  
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileSearchHandleServlet() {
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
		
		String res = "haha";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
		
		String type = request.getParameter("type");
		String clientuser = request.getParameter("clientuser");
		String filename = request.getParameter("filename");
		
		 if (type.equals("search"))
		{
			LogDbDao.insertLog(date, clientuser, "查找文件：" + filename);
			log.debug(clientuser + "查找文件：" + filename);
			//res = FileDbDao.search(filename);
			String aimPath = request.getParameter("aimPath");
			String pattern = request.getParameter("pattern");
			log.debug(aimPath+","+pattern);
			res = FindFile.findFileName(aimPath, pattern);
			response.getWriter().append(res);
		}
		else if(type.equals("singlesearch"))
		{
			LogDbDao.insertLog(date, clientuser, "single查找文件");
			log.debug(clientuser + "single");
			String powerstation = request.getParameter("powerstation");
			String crew = request.getParameter("crew");
			String projectname = request.getParameter("projectname");
			String keyword = request.getParameter("keyword");
			String projectid= request.getParameter("projectid");
			String manager= request.getParameter("manager");
			String applysituation= request.getParameter("applysituation");
			String potentialcustomers= request.getParameter("potentialcustomers");
			String projectstate  = request.getParameter("projectstate"); 
			log.debug(powerstation);
			log.debug(crew);
			log.debug(projectname);
			log.debug(keyword);
			log.debug(projectid);
			log.debug(manager);
			log.debug(applysituation);
			log.debug(potentialcustomers);
			log.debug(projectstate);
			//res = FileDbDao.singleSearch(powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate);
			response.getWriter().append(res);
		}
		else if(type.equals("unionsearch"))
		{
			LogDbDao.insertLog(date, clientuser, "union查找文件");
			log.debug(clientuser + "union查找文件");
			String powerstation = request.getParameter("powerstation");
			String crew = request.getParameter("crew");
			String projectname = request.getParameter("projectname");
			String keyword = request.getParameter("keyword");
			String projectid= request.getParameter("projectid");
			String manager= request.getParameter("manager");
			String applysituation= request.getParameter("applysituation");
			String potentialcustomers= request.getParameter("potentialcustomers");
			String projectstate  = request.getParameter("projectstate");
			log.debug(powerstation);
			log.debug(crew);
			log.debug(projectname);
			log.debug(keyword);
			log.debug(projectid);
			log.debug(manager);
			log.debug(applysituation);
			log.debug(potentialcustomers);
			log.debug(projectstate);
			//res = FileDbDao.unionSearch(powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate);
			response.getWriter().append(res);
		}
		
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
