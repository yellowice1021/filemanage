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
		
		//���Ƿ������ĵ��ĸ�Ŀ¼
		//String serverPath = request.getSession().getServletContext().getRealPath("") +"doc\\";	
		
		String type = request.getParameter("type");
		String clientuser = request.getParameter("clientuser");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��

		if (type.equals("find"))
		{
			log.info(clientuser+" �鿴�����û�");
			LogDbDao.insertLog(date, clientuser,  "�鿴�����û�");
			res = UserDbDao.getUser();
			response.getWriter().append(res);
		}
		else if (type.equals("delfind"))
		{
			res = UserDbDao.getUser();
			System.out.println(clientuser+" �鿴ɾ����������û�");
			response.getWriter().append(res);
		}
		else if (type.equals("del"))
		{
			int id  = Integer.parseInt(request.getParameter("userid"));
			UserDbDao.del(id);
			System.out.println(clientuser+" ɾ���û�"+id);
			log.info(clientuser+" ɾ���û�"+id);
			LogDbDao.insertLog(date, clientuser, "ɾ���û�"+id);
			//response.getWriter().append(res);
		}
		else if (type.equals("update"))
		{
			String userName = request.getParameter("userName");
			String passwd = request.getParameter("passwd");
			int privillege = Integer.parseInt(request.getParameter("privillege"));
			int id = Integer.parseInt(request.getParameter("userid"));
			UserDbDao.update(id, userName, privillege, passwd);
			System.out.println(clientuser+" �޸��û�"+id);
			log.info(clientuser+" �޸��û�"+id);
			LogDbDao.insertLog(date, clientuser, "�޸��û�"+id);
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
				log.info("�޸��û�����");
				LogDbDao.insertLog(date, clientuser, "�޸��û�����"+userName+"�ɹ�");
			}
			else
			{
				res = "no";
				LogDbDao.insertLog(date, clientuser, "�޸��û�����"+userName+"ʧ��");
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
				System.out.println(clientuser+" �����û�"+userName);
				log.info(clientuser+" �����û�"+userName);
				LogDbDao.insertLog(date, clientuser, "�����û�"+userName);
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
					log.info(clientuser+" �����û�"+userName);
					LogDbDao.insertLog(date, clientuser, "�����û�"+userName+"�ɹ�");
				}
				else
				{
					res = "no";
					System.out.println("insert" + res);
					LogDbDao.insertLog(date, clientuser, "�����û�"+userName+"ʧ��");
				}
				System.out.println("insert" + res);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				response.getWriter().append("���������쳣");
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
