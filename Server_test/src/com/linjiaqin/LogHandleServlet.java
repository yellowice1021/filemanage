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

import com.scut.server.db.DbDao;
import com.scut.server.db.LogDbDao;
import com.scut.server.db.UserDbDao;
import com.scut.server.funtion.LogHandle;

/**
 * Servlet implementation class LogHandleServlet
 */
@WebServlet("/LogHandleServlet")
public class LogHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("server");   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogHandleServlet() 
    {
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
			
		
		String type = request.getParameter("type");
		String clientuser = request.getParameter("clientuser");
		if (clientuser.equals("�ο�"))
		{
			res = "unlogin";      //����ͻ��˻�û���ú�
			log.debug("��δ��¼");
			clientuser = "xxx";
		}
		if (type.equals("getLog"))
		{
			//res = LogHandle.getAlllog();
			System.out.println(clientuser+" �鿴������־");
			LogDbDao.insertLog(date, clientuser, "�鿴������־");
			log.info(clientuser+" �鿴������־");
			res = LogDbDao.getAllLog();
			response.getWriter().append(res);
		}
		else if (type.equals("getDelLog"))
		{
			res = LogDbDao.getAllLog();
			response.getWriter().append(res);
		}
		else if (type.equals("delLog"))
		{
			String rows = request.getParameter("lines");
			String ids[] = rows.split(" ");
			for (String id : ids)
			{
				if (LogDbDao.delLog( Integer.parseInt(id)) )
				{
					res = "yes";
					
				}
			}
			LogDbDao.insertLog(date, clientuser, "ɾ����־");
			//response.getWriter().append(res);
		}
		else if (type.equals("setLogDate"))
		{
			String dates = request.getParameter("date");
			log.debug(dates);
			if (LogHandle.writeDate(dates))
			{
				LogDbDao.insertLog(date, clientuser, "������־��������");
				response.getWriter().append("yes");
			}
			else 
			{
				response.getWriter().append("no");
			}
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
