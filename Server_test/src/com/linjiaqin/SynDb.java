package com.linjiaqin;

import java.io.IOException;
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
 * Servlet implementation class SynDb
 */
@WebServlet("/SynDb")
public class SynDb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("server");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SynDb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FileList.filenames = "";
		FileList.dirnames = "";
		String path = MyPath.serverRootPath + "doc\\";
		FileList.getFileandDir(path, MyPath.serverRootPath.length());
		String filenames = FileList.filenames;
		String dirnames = FileList.dirnames;
		String[] dirname = dirnames.split(",");
		String[] filename = filenames.split(",");
		String s = "success";
		for(String e:dirname)
		{
			log.debug(e);
			if(DirDbDao.syncDb(e) == false)
			{
				s = "fail";
			}
		}
		
//		for(String e:filename)
//		{
//			log.debug(e);
//			if(FileDbDao.syncDb(e) == false)
//			{
//				s = "fail";
//			}
//		}
		
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
