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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.scut.server.conf.MyUser;
import com.scut.server.db.DbDao;
import com.scut.server.db.LogDbDao;
import com.scut.server.db.UserDbDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("server");  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
		
		String res = "4";
		
		//这是服务器文档的根目录
		//String serverPath = request.getSession().getServletContext().getRealPath("") +"doc\\";	
		try
		{
			String userName = request.getParameter("userName");
			String passwd = request.getParameter("passwd");
			System.out.println(userName + ":" + passwd);
			
			HttpSession session = request.getSession();
			//if (session.getAttribute(userName) == null)
			if (!MyUser.islogined(userName))
			{
				if (!UserDbDao.hasUser(userName)) res = "4";  //帐号不存在
				else if (UserDbDao.isUser(userName, passwd))
				{			
					res = ""+UserDbDao.getPrivilage(userName);
					log.info(userName+" 登录");
					LogDbDao.insertLog(date, userName, "登录");
					//session.setAttribute(userName, passwd);
					MyUser.Login(userName);
				}
				else
				{
					//"密码错误";
					res = "5";
				}
			}
			else
			{
				res = "6"; //已经登录过
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//response.getWriter().append("登录异常");
		}
		finally
		{
					
		}
				
		response.getWriter().append(res);
		//response.getWriter().append("Served at: hah").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
