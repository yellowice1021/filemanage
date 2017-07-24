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

import com.scut.server.conf.Conf;
import com.scut.server.conf.MyPath;
import com.scut.server.db.FileDbDao;
import com.scut.server.db.HisDbDao;
import com.scut.server.db.LogDbDao;
import com.scut.server.db.RecycleDbDao;
import com.scut.server.funtion.DirHandle;
import com.scut.server.funtion.FileList;

/**
 * Servlet implementation class FileHandleServlet
 */
@WebServlet("/FileHandleServlet")
public class FileHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("server");   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileHandleServlet() {
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
		if (type.equals("addfileinfo"))
		{
			/////String filepath = serverRootPath  + request.getParameter("filepath");
			String filepath = request.getParameter("filepath");
			String powerstation = request.getParameter("powerstation");
			String crew = request.getParameter("crew");
			String projectname = request.getParameter("projectname");
			String keyword = request.getParameter("keyword");
			String projectid= request.getParameter("projectid");
			String manager= request.getParameter("manager");
			String applysituation= request.getParameter("applysituation");
			String potentialcustomers= request.getParameter("potentialcustomers");
			String projectstate  = request.getParameter("projectstate");
			
			if (!FileDbDao.isExist(filepath))
			{
				response.getWriter().append("filenoexist");
				log.debug(filepath+"该文件已经不存在");
			}
			else
			{
				if (FileDbDao.addFileInfo(filepath, powerstation, crew, projectname, keyword, projectid, 
						manager, applysituation, potentialcustomers,projectstate ) )
				{
					log.debug("上传文件信息成功");
					LogDbDao.insertLog(date, clientuser, "上传文件信息" + filepath);
					response.getWriter().append("yes");
				}
				else 
				{
					log.debug("上传文件信息失败");
					response.getWriter().append("no");
				}
			}
			
		}
		else if (type.equals("isfile"))
		{  
			//String filepath = serverRootPath + request.getParameter("filepath");
			String filepath = request.getParameter("filepath");
			File file = new File(MyPath.serverRootPath + filepath);
			log.debug(filepath);
			if (!file.exists())
			{
				response.getWriter().append("noexist");
			}
			else if (file.isDirectory())
			{
				log.debug("dir");
				response.getWriter().append("dir");
			}
			else if (file.isFile())
			{
				log.debug("file");
				response.getWriter().append("file");
			}
			
		}
		else if (type.equals("getfileinfo"))
		{  
			//String filepath = serverRootPath + request.getParameter("filepath");
			String filepath = request.getParameter("filepath");
			if (!FileDbDao.isExist(filepath))
			{
				response.getWriter().append("filenoexist");
				log.debug(filepath+"该文件已经不存在");
			}
			else
			{
				log.debug(filepath+"该文件存在");
				String s = FileDbDao.getFileInfo(filepath);
			    log.debug(s);
				response.getWriter().append(s);
			}
			
		}
		else if(type.equals("delfile"))
		{
			
			String filepath = request.getParameter("filepath");
				log.debug("删除文件" + filepath);
				LogDbDao.insertLog(date, clientuser, "删除文件" + filepath);
				//boolean ss = FileDbDao.delFile(filepath);
				boolean s = DirHandle.deleteFile(Conf.recyclePath + filepath);
			    if (s) response.getWriter().append("yes");
			    else response.getWriter().append("no");
				
			
		}
		else if (type.equals("getRecycle"))
		{
			FileList.filenames = "";
			FileList.getFileandDir(MyPath.serverRootPath + "recycle" , 7+MyPath.serverRootPath.length());
			String s = FileList.filenames;
			response.getWriter().append(s);
		}
		else if (type.equals("puttorecycle"))
		{
			String filepath = request.getParameter("file_path");
			log.debug(filepath);
			//boolean flag1 = RecycleDbDao.insertIntoRecy(filepath);
			
			String aimPath = Conf.recyclePath + "\\" + filepath;// 获得上传文件的文件名  
			String sourcePath = MyPath.serverRootPath + filepath;
			log.debug(sourcePath + ":" + aimPath);
			boolean flag2 = DirHandle.moveToRecycle(sourcePath, aimPath);
			
			///if (flag1 && flag2)
			if (flag2)
			{
				response.getWriter().append("yes");
			}
			else
			{
				response.getWriter().append("no");
			}  
		}
		
		else if (type.equals("backFromRecycle"))
		{
			String rows = request.getParameter("file_names");
			String filenames[] = rows.split(",");
			for (String Path : filenames)
			{
				String oldPath = MyPath.serverRootPath + Path;
				String newPath = Conf.recyclePath + "\\" + Path;
				log.debug(oldPath + ":" + newPath);
				//boolean flag2 = DirHandle.moveFile(sourcePath, aimPath);
				if (DirHandle.isExist(oldPath))
				{
					String fileType = Path.substring(Path.lastIndexOf('.') + 1);
					String oldPaths = Path.substring(0, Path.lastIndexOf('.')) + "_old" + "." + fileType;
					String destpath = MyPath.serverRootPath + oldPaths;
//						newPath = serverRootPath + File.separator + 			//新名称为原名加_old加文件类型
//								srcFile.getName().substring(0, srcFile.getName().indexOf('.')) 
//								+ "_old"+ '.' + fileType;
//						destFile = new File(newPath);
					log.debug(destpath+":::"+ oldPaths);
					DirHandle.moveBackRecycle(newPath, destpath);
					//FileDbDao.updateRecycleFile(Integer.parseInt(id), oldPaths);
				}
				else 
				{
					DirHandle.moveBackRecycle(newPath, oldPath);
					//RecycleDbDao.delFromRecy(Integer.parseInt(id));
				}
				
				
			}
		}
//		else if (type.equals("backFromRecycle"))
//		{
//			String rows = request.getParameter("file_ids");
//			String ids[] = rows.split(" ");
//			for (String id : ids)
//			{
//				String Path = RecycleDbDao.getFileName(Integer.parseInt(id)) ;
//				String oldPath = MyPath.serverRootPath + Path;
//				String newPath = Conf.recyclePath + "\\" + Path;
//				log.debug(oldPath + ":" + newPath);
//				//boolean flag2 = DirHandle.moveFile(sourcePath, aimPath);
//				if (DirHandle.isExist(oldPath))
//				{
//					String fileType = Path.substring(Path.lastIndexOf('.') + 1);
//					String oldPaths = Path.substring(0, Path.lastIndexOf('.')) + "_old" + "." + fileType;
//					String destpath = MyPath.serverRootPath + oldPaths;
////					newPath = serverRootPath + File.separator + 			//新名称为原名加_old加文件类型
////							srcFile.getName().substring(0, srcFile.getName().indexOf('.')) 
////							+ "_old"+ '.' + fileType;
////					destFile = new File(newPath);
//					log.debug(destpath+":::"+ oldPaths);
//					DirHandle.moveBackRecycle(newPath, destpath);
//					FileDbDao.updateRecycleFile(Integer.parseInt(id), oldPaths);
//				}
//				else 
//				{
//					DirHandle.moveBackRecycle(newPath, oldPath);
//					RecycleDbDao.delFromRecy(Integer.parseInt(id));
//				}
//				
//				
//			}
//		}
		else if (type.equals("fileexist"))
		{
			String filepath = request.getParameter("serverPath");
			log.debug(filepath);
			boolean flag = FileDbDao.isExist(filepath);
			if (flag)
			{
				response.getWriter().append("yes");
			}
			else
			{
				response.getWriter().append("no");
			} 
		}
		else if (type.equals("getVersionfile"))
		{
			String filepath = request.getParameter("filepath");
			log.debug(filepath);
			res  = HisDbDao.getVersionFile(filepath);
			response.getWriter().append(res);
			
		}
		else if (type.equals("delfiles"))
		{
			String rows = request.getParameter("filepath");
			log.debug(rows);
			String files[] = rows.split(",");
			for (String filename : files)
			{
				
//				if (FileDbDao.delFile(filename))
//				{
					DirHandle.deleteFile(Conf.recyclePath + filename);
					res = "yes";	
//				}
			}
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
