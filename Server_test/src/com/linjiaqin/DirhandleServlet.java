package com.linjiaqin;

import java.io.File;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.scut.server.conf.Conf;
import com.scut.server.conf.MyPath;
import com.scut.server.db.DirDbDao;
import com.scut.server.db.FileDbDao;
import com.scut.server.db.LogDbDao;
import com.scut.server.funtion.DirHandle;

/**
 * Servlet implementation class DirhandleServlet
 */
@WebServlet("/DirhandleServlet")
public class DirhandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	private static Logger log = Logger.getLogger("server");   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirhandleServlet() {
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
		
		String res = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
			
        //String serverRootPath = request.getSession().getServletContext().getRealPath("");
		 
		String type = request.getParameter("type");
		String clientuser = request.getParameter("clientuser");
		if (clientuser.equals("游客"))
		{
			res = "unlogin";      //这里客户端还没设置好
			log.debug("还未登录");
			clientuser = "游客";
		}
		if (type.equals("addDir"))
		{
			  String Dirname = request.getParameter("Dirname");
			  String serverPath = request.getParameter("serverPath");
			  
			  String Dirpath = MyPath.serverRootPath + serverPath + "\\" + Dirname;
			  log.debug(Dirpath);
			  File file = new File(Dirpath);
		      if (file.mkdirs()) 
		      {
		    	  log.debug("insert into tb_dir"+DirDbDao.addDir(serverPath + "\\" + Dirname));
		          log.info("创建 文件夹"+Dirpath);
		          LogDbDao.insertLog(date, clientuser, "创建文件夹"+serverPath + "\\" + Dirname);
		          response.getWriter().append("yes");
		      }
		      else
		      {
		    	  response.getWriter().append("no");
		      } 
		}   
		else if(type.equals("delDir"))
		{
			String serverPath = MyPath.serverRootPath + request.getParameter("serverPath");
			String picPath = MyPath.serverRootPath + "pic\\" + request.getParameter("serverPath");
			String dirAllName[] = DirHandle.getAllDir(serverPath, MyPath.serverRootPath.length()).split(",");
			
			if (DirHandle.deleteDirectory(serverPath))
			 {
				  DirHandle.deleteDirectory(picPath);
				  
				  DirDbDao.delDir(request.getParameter("serverPath"));
				  
				  for(int i = 0; i < dirAllName.length; i++)
				  {
					  DirDbDao.delDir(dirAllName[i]);
				  }
				
		          log.info("成功删除文件夹 "+serverPath);
		          LogDbDao.insertLog(date, clientuser, "删除文件夹"+request.getParameter("serverPath"));
		          response.getWriter().append("yes");
		      }
		      else
		      {
		    	  response.getWriter().append("no");
		      } 
		}
		else if(type.equals("addDirInfo"))
		{
			//String serverPath = serverRootPath + request.getParameter("serverPath");
			String serverPath = request.getParameter("serverPath");
			if (!DirDbDao.isExist(serverPath))
			{
				response.getWriter().append("dirnoexist");
			}
			else
			{
				String funtiondesc = request.getParameter("funtiondesc");
				String technicalFeature = request.getParameter("technicalFeature");
				String potentialcustomr = request.getParameter("potentialcustomr");
				String projectstate = request.getParameter("projectstate");
				boolean flag = DirDbDao.updateDirInfo(funtiondesc, technicalFeature, potentialcustomr, projectstate, serverPath);
				if (flag)
				{
					response.getWriter().append("yes");
				}
				else
				{
					response.getWriter().append("no");
				}
			}	
		}
		else if(type.equals("getDirInfo"))
		{
			//String serverPath = serverRootPath + request.getParameter("serverPath");
			String serverPath = request.getParameter("serverPath");
			if (!DirDbDao.isExist(serverPath))
			{
				response.getWriter().append("dirnoexist");
			}
			else
			{		
				String s = DirDbDao.getDirInfo(serverPath);
				response.getWriter().append(s);
			
			}	
		}
		else if(type.equals("addApply"))
		{
			log.debug("addapply");
			String Dirname = request.getParameter("Dirname");
			String applystring = request.getParameter("applystring");
			DirDbDao.delDirapply(Dirname);
			String rows[] = applystring.split("\t");
			for (String e:rows)
			{
				String tmp[] = e.split(",");
	
				Date imputdate = null;
				try {
					imputdate = df.parse(tmp[1]);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Calendar cal = Calendar.getInstance();
				cal.setTime(imputdate);
		        cal.add(Calendar.MONTH, Integer.parseInt(tmp[2])); 
		        String aimDate = df.format(cal.getTime());
		        System.out.println("aimDate" + aimDate);
				DirDbDao.addDirapply(Dirname, tmp[0], tmp[1], tmp[2], aimDate);
			}
			//DirDbDao.addDirapply(Dirname, powersattion, starttime, protecttime);
		}
		else if (type.equals("getApply"))
		{
			log.debug("gteapply");
			String Dirname = request.getParameter("serverPath");
			log.debug(Dirname);
			String s = DirDbDao.getDirapply(Dirname);
			log.debug(s);
			response.getWriter().append(s);
		}
		else if(type.equals("getdirkeyword"))
		{
			//String serverPath = serverRootPath + request.getParameter("serverPath");
			String serverPath = request.getParameter("serverPath");
			log.debug(serverPath);
			if (!DirDbDao.isExist(serverPath))
			{
				response.getWriter().append("dirnoexist");
			}
			else
			{		
				String s = DirDbDao.getDirKeyword(serverPath);
				log.debug(s);
				response.getWriter().append(s);
			
			}	
		}
		else if(type.equals("adddirkeyword"))
		{
			//String serverPath = serverRootPath + request.getParameter("serverPath");
			
			String serverPath = request.getParameter("serverPath");
			log.debug(serverPath);
			//String filepath = request.getParameter("filepath");
			String powerstation = request.getParameter("powerstation");
			String crew = request.getParameter("crew");
			String projectname = request.getParameter("projectname");
			String keyword = request.getParameter("keyword");
			String projectid= request.getParameter("projectid");
			String manager= request.getParameter("manager");
			String applysituation= request.getParameter("applysituation");
			String potentialcustomers= request.getParameter("potentialcustomers");
			String projectstate  = request.getParameter("projectstate");
			if (!DirDbDao.isExist(serverPath))
			{
				response.getWriter().append("dirnoexist");
			}
			else
			{		
				
				boolean flag = DirDbDao.addDirKeyword(serverPath, powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate);
				if (flag)
				{
					response.getWriter().append("yes");
				}
				else
				{
					response.getWriter().append("no");
				}
			
			}	
		}
		else if(type.equals("getDirPic"))
		{
			//String serverPath = serverRootPath + request.getParameter("serverPath");
			String serverPath = request.getParameter("serverPath");
			
//			if (!DirDbDao.isExist(serverPath))
//			{
//				response.getWriter().append("dirnoexist");
//			}
//			else
//			{		
//				String s = DirDbDao.getDirPicPath(serverPath);
//				response.getWriter().append(s);		
//			}
			String s = "";
			File dir = new File(Conf.DirPicPath + "\\" + serverPath);
			if (!dir.exists()) 
			{
				dir.mkdir();
				response.getWriter().append(s);
			}
			else
			{
				log.debug(dir.getAbsolutePath());
				File files[] = dir.listFiles();
				log.debug(files.length);
				if(files.length > 0)
				{
					for(File e:files)
					{
						if (e.isDirectory()) continue;
						s += e.getAbsolutePath() + ";";
					}
				}
				response.getWriter().append(s);
			}
			
		
		}
		else if (type.equals("dirsinglesearch"))
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
			String onprotect = request.getParameter("isprotected");
			log.debug(powerstation);
			log.debug(crew);
			log.debug(projectname);
			log.debug(keyword);
			log.debug(projectid);
			log.debug(manager);
			log.debug(applysituation);
			log.debug(potentialcustomers);
			log.debug(projectstate);
			res = FileDbDao.singleSearch(powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate, onprotect);
			response.getWriter().append(res);
		}
		else if (type.endsWith("dirunionsearch"))
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
			String onprotect = request.getParameter("isprotected");
			log.debug(powerstation);
			log.debug(crew);
			log.debug(projectname);
			log.debug(keyword);
			log.debug(projectid);
			log.debug(manager);
			log.debug(applysituation);
			log.debug(potentialcustomers);
			log.debug(projectstate);
			res = FileDbDao.unionSearch(powerstation, crew, projectname, keyword, projectid, manager, applysituation, potentialcustomers, projectstate,onprotect);
			response.getWriter().append(res);
		}
		else if (type.endsWith("getDirApplyEndTime"))
		{
			String dirPath = request.getParameter("serverPath");
			res = DirDbDao.getDirapplyEndTime(dirPath);
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
