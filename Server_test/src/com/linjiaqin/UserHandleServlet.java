package com.linjiaqin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.scut.server.db.DbDao;
import com.scut.server.db.LogDbDao;
import com.scut.server.db.UserDbDao;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class UserHandleServlet
 */
@WebServlet("/UserHandleServlet")
public class UserHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("server");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserHandleServlet() {
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
		
		//这是服务器文档的根目录
		//String serverPath = request.getSession().getServletContext().getRealPath("") +"doc\\";	
		
		String type = request.getParameter("type");
		String clientuser = request.getParameter("clientuser");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间

		if (type.equals("find"))
		{
			log.info(clientuser+" 查看所有用户");
			LogDbDao.insertLog(date, clientuser,  "查看所有用户");
			res = UserDbDao.getUser();
			response.getWriter().append(res);
		}
		else if (type.equals("delfind"))
		{
			res = UserDbDao.getUser();
			System.out.println(clientuser+" 查看删除后的所有用户");
			response.getWriter().append(res);
		}
		else if (type.equals("del"))
		{
			int id  = Integer.parseInt(request.getParameter("userid"));
			UserDbDao.del(id);
			System.out.println(clientuser+" 删除用户"+id);
			log.info(clientuser+" 删除用户"+id);
			LogDbDao.insertLog(date, clientuser, "删除用户"+id);
			//response.getWriter().append(res);
		}
		else if (type.equals("update"))
		{
			String userName = request.getParameter("userName");
			String passwd = request.getParameter("passwd");
			int privillege = Integer.parseInt(request.getParameter("privillege"));
			int id = Integer.parseInt(request.getParameter("userid"));
			UserDbDao.update(id, userName, privillege, passwd);
			System.out.println(clientuser+" 修改用户"+id);
			log.info(clientuser+" 修改用户"+id);
			LogDbDao.insertLog(date, clientuser, "修改用户"+id);
			response.getWriter().append("yes");
		}
		else if (type.equals("updatePwd"))
		{
			String userName = request.getParameter("userName");
			String newpasswd = request.getParameter("newpasswd");
			String oldpasswd = request.getParameter("oldpasswd");
			log.info(userName+"::"+newpasswd + "::" + oldpasswd);
			if (UserDbDao.updatePwd(userName, newpasswd, oldpasswd))
			{
				res = "yes";
				log.info("修改用户密码");
				LogDbDao.insertLog(date, clientuser, "修改用户密码"+userName+"成功");
			}
			else
			{
				res = "no";
				LogDbDao.insertLog(date, clientuser, "修改用户密码"+userName+"失败");
			}
			response.getWriter().append(res);
		}
		else if (type.equals("add"))
		{
			try
			{
				
				String userName = request.getParameter("userName");
				String passwd = request.getParameter("passwd");
				String realname = request.getParameter("realname");
				System.out.println(clientuser+" 增加用户"+userName);
				log.info(clientuser+" 增加用户"+userName);
				LogDbDao.insertLog(date, clientuser, "增加用户"+userName);
				int privillege = Integer.parseInt(request.getParameter("privillege"));
				System.out.println(userName + passwd + privillege);
					
				if (UserDbDao.isUserExist(userName))
				{
					response.getWriter().append("usernameexist");
					return;
				}
				
				if (UserDbDao.insert(userName, privillege, passwd, realname))
				{
					res = "yes";
					System.out.println("insert" + res);
					log.info(clientuser+" 增加用户"+userName);
					LogDbDao.insertLog(date, clientuser, "增加用户"+userName+"成功");
				}
				else
				{
					res = "no";
					System.out.println("insert" + res);
					LogDbDao.insertLog(date, clientuser, "增加用户"+userName+"失败");
				}
				System.out.println("insert" + res);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				response.getWriter().append("插入数据异常");
			}
			
					
			response.getWriter().append(res);
		}
		//UserDbDao.close();
		
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
