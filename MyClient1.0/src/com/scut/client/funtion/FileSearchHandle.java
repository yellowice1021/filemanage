package com.scut.client.funtion;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.scut.client.user.MyUser;
import com.scut.tools.MyParameters;

public class FileSearchHandle 
{
    private static HttpClient httpClient;
    private static String res = "";
	
	private static Logger log = Logger.getLogger("client");
	static
	{
			//必须创建一个client去执行
		httpClient = HttpClients.createDefault();
	}
	
	/*
	 * 根据文件名检索服务器文件
	 */
	public static String searchFilename(String aimPath, String filename)
	{
		String res = null;
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileSearchHandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "search"));
		parameters.add(new BasicNameValuePair("pattern", filename));
		parameters.add(new BasicNameValuePair("aimPath", aimPath));
		HttpEntity httpEntity = null;
		try 
		{
			httpEntity = new UrlEncodedFormEntity(parameters, "utf-8");
		} 
		catch (UnsupportedEncodingException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		} 
		httpPost.setEntity(httpEntity);
		HttpResponse response = null;
		try 
		{
			response = httpClient.execute(httpPost);
		} 
		catch (org.apache.http.conn.HttpHostConnectException e)
		{
			log.warn(e);
			JOptionPane.showMessageDialog(null, "出错啦", "您网络出问题了或者服务器拒绝了您", JOptionPane.ERROR_MESSAGE);
		}
		catch (ClientProtocolException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();;
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1);	
		}
		
		HttpEntity entity = response.getEntity();
		String result = "falsess";
		try 
		{
			//result = new String (EntityUtils.toString(entity).getBytes("ISO-8859-1"),"utf-8");
			result = EntityUtils.toString(entity,"utf-8");
		} 
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		}            
		log.debug(result);
		return result;
	}
	
	/*
	 * 单个查询
	 */
	public static String singleSearch(
			String powerstation,
			String crew,
			String projectname,
			String keyword,
			String projectid,
			String manager,
			String applysituation,
			String potentialcustomers,
			String projectstate,
			String isprotected)
	{
        String res = null;
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "dirsinglesearch"));
		parameters.add(new BasicNameValuePair("powerstation", powerstation));
		parameters.add(new BasicNameValuePair("crew", crew));
		parameters.add(new BasicNameValuePair("projectname", projectname));
		parameters.add(new BasicNameValuePair("keyword", keyword));
		parameters.add(new BasicNameValuePair("projectid", projectid));
		parameters.add(new BasicNameValuePair("manager", manager));
		parameters.add(new BasicNameValuePair("applysituation", applysituation));
		parameters.add(new BasicNameValuePair("potentialcustomers", potentialcustomers));
		parameters.add(new BasicNameValuePair("projectstate", projectstate));
		parameters.add(new BasicNameValuePair("isprotected", isprotected ));
		
		HttpEntity httpEntity = null;
		try 
		{
			httpEntity = new UrlEncodedFormEntity(parameters, "utf-8");
		} 
		catch (UnsupportedEncodingException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		} 
		httpPost.setEntity(httpEntity);
		HttpResponse response = null;
		try 
		{
			response = httpClient.execute(httpPost);
		} 
		catch (org.apache.http.conn.HttpHostConnectException e)
		{
			log.warn(e);
			JOptionPane.showMessageDialog(null, "出错啦", "您网络出问题了或者服务器拒绝了您", JOptionPane.ERROR_MESSAGE);
		}
		catch (ClientProtocolException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();; 
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1);	
		}
		
		HttpEntity entity = response.getEntity();
		try 
		{
			//result = new String (EntityUtils.toString(entity).getBytes("ISO-8859-1"),"utf-8");
			res = EntityUtils.toString(entity,"utf-8");
		} 
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		}            
		log.debug(res);
		return res;
	}
	
	/*
	 * 组合查询
	 */
	public static String unionSearch(
			String powerstation,
			String crew,
			String projectname,
			String keyword,
			String projectid,
			String manager,
			String applysituation,
			String potentialcustomers,
			String projectstate,
			String isprotected)
	{
        String res = null;
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "dirunionsearch"));
		parameters.add(new BasicNameValuePair("powerstation", powerstation));
		parameters.add(new BasicNameValuePair("crew", crew));
		parameters.add(new BasicNameValuePair("projectname", projectname));
		parameters.add(new BasicNameValuePair("keyword", keyword));
		parameters.add(new BasicNameValuePair("projectid", projectid));
		parameters.add(new BasicNameValuePair("manager", manager));
		parameters.add(new BasicNameValuePair("applysituation", applysituation));
		parameters.add(new BasicNameValuePair("potentialcustomers", potentialcustomers));
		parameters.add(new BasicNameValuePair("projectstate", projectstate));
		parameters.add(new BasicNameValuePair("isprotected", isprotected ));
		
		HttpEntity httpEntity = null;
		try 
		{
			httpEntity = new UrlEncodedFormEntity(parameters, "utf-8");
		} 
		catch (UnsupportedEncodingException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		} 
		httpPost.setEntity(httpEntity);
		HttpResponse response = null;
		try 
		{
			response = httpClient.execute(httpPost);
		} 
		catch (org.apache.http.conn.HttpHostConnectException e)
		{
			log.warn(e);
			JOptionPane.showMessageDialog(null, "出错啦", "您网络出问题了或者服务器拒绝了您", JOptionPane.ERROR_MESSAGE);
		}
		catch (ClientProtocolException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();; 
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1);	
		}
		
		HttpEntity entity = response.getEntity();
		try 
		{
			//result = new String (EntityUtils.toString(entity).getBytes("ISO-8859-1"),"utf-8");
			res = EntityUtils.toString(entity,"utf-8");
		} 
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e);
		}            
		log.debug(res);
		return res;
	}

	/*
	 * 客户端检索文件，类似服务器端，最长匹配
	 * **/
		public static void dfs_findname_client(String filePath, String pattern, int rootPathIndex)
		{
			   File root = new File(filePath);
			   File[] files = root.listFiles();
			   for(File file:files)
			   {     
				    if(file.isDirectory())
				    {
				    	dfs_findname_client(file.getAbsolutePath(), pattern, rootPathIndex);   
					}
				    else
				    {
				    	log.debug(file.getAbsolutePath().substring(rootPathIndex)+":"+pattern);
				    	if(file.getAbsolutePath().substring(rootPathIndex).indexOf(pattern) > 0)
				    	{
				    		res += file.getAbsolutePath().substring(rootPathIndex) +":"+ file.length()+",";
				    	}
				    }     
			   }
		}
		public static String findFileNameClient(String aimPath, String pattern)
		{
	       	res = "";
			dfs_findname_client(aimPath, pattern, aimPath.length());
			return res;
			
		}
	/*
	 * 客户端检索文件大小写不区分，文件名是包含关系
	 * **/
//	public static String searchFilenameClient(String aimPath, String filename) {    // 递归查找包含关键字的文件  
//		// TODO Auto-generated method stub 
//		File file = new File(aimPath);
//		File[] files = file.listFiles();
//		if(files != null){
//			for(int i =0;i<files.length;i++){
//				if( files[i].isFile() && files[i].getName().toLowerCase().contains(filename)){
//				   //res += files[i].getAbsolutePath() + ",";
//					res += files[i].getAbsolutePath()+":"+ files[i].length()+",";
//				}
//				if(files[i].isDirectory()){
//					searchFilenameClient(files[i].getAbsolutePath(),filename);
//				}
//			}
//		}
//		//System.out.println("查询到的文件是："+res);
//		return res;
//	}
	
}
