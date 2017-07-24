package com.linjiaqin;

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

/**
 * Servlet implementation class QuitServlet
 */
@WebServlet("/QuitServlet")
public class QuitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("server");   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuitServlet() {
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
		
		String res = "�˳��ɹ�";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		//���Ƿ������ĵ��ĸ�Ŀ¼
		//String serverPath = request.getSession().getServletContext().getRealPath("") +"doc\\";	
		try
		{
			String userName = request.getParameter("userName");
			
			HttpSession session = request.getSession();
			log.info(userName+" �˳���¼");
			LogDbDao.insertLog(date, userName, "�˳���¼");
			//session.removeAttribute(userName);
			MyUser.Logout(userName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			res = "�˳��쳣";
		}
				
		response.getWriter().append(res);
		
		
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
