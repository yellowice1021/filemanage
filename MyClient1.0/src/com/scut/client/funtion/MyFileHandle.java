package com.scut.client.funtion;

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

/*
 * 发送文件相关操作的http
 */
public class MyFileHandle 
{
	private static HttpClient httpClient;
	
	private static Logger log = Logger.getLogger("client");
	static
	{
		httpClient = HttpClients.createDefault();
	}
	
	/*
	 * 增加文件的相关信息
	 */
	public static boolean addFileInfo(int fileId,
			//char version,
			String powerstation,
			String crew,
			String projectname,
			String keyword,
			String projectid,
			String manager,
			String applysituation,
			String potentialcustomers,
			String projectstate)
	{
        String res = null;
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileHandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
		parameters.add(new BasicNameValuePair("fileId", ""+fileId));
		//parameters.add(new BasicNameValuePair("version", ""+version));
		parameters.add(new BasicNameValuePair("powerstation", powerstation));
		parameters.add(new BasicNameValuePair("crew", crew));
		parameters.add(new BasicNameValuePair("projectname", projectname));
		parameters.add(new BasicNameValuePair("keyword", keyword));
		parameters.add(new BasicNameValuePair("manager", manager));
		parameters.add(new BasicNameValuePair("applysituation", applysituation));
		parameters.add(new BasicNameValuePair("potentialcustomers", potentialcustomers));
		parameters.add(new BasicNameValuePair("projectstate", projectstate));
		
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
		//return res;
		boolean flag = true;
		return flag;
	}
	
	/*
	 * 批量删除文件
	 */
	public static String delFiles(String filepaths)
	{
		String res = "";
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileHandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "delfiles"));
		parameters.add(new BasicNameValuePair("filepath", filepaths));
		
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
	 * 删除单个文件
	 */
	public static String delFile(String filepath)
	{
		String res = "";
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileHandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "delfile"));
		parameters.add(new BasicNameValuePair("filepath", filepath));
		
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
	 * 获取历史版本
	 */
	public static String getVersionFile(String filepath)
	{
		String res = "";
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileHandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "getVersionfile"));
		parameters.add(new BasicNameValuePair("filepath", filepath));
		
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
	 * 增加文件的相关信息
	 */
	public static String addFileInfo(String filepath,
			//char version,
			String powerstation,
			String crew,
			String projectname,
			String keyword,
			String projectid,
			String manager,
			String applysituation,
			String potentialcustomers,
			String projectstate)
	{
        String res = null;
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileHandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "addfileinfo"));
		parameters.add(new BasicNameValuePair("filepath", filepath));
		//parameters.add(new BasicNameValuePair("version", ""+version));
		parameters.add(new BasicNameValuePair("powerstation", powerstation));
		parameters.add(new BasicNameValuePair("crew", crew));
		parameters.add(new BasicNameValuePair("projectname", projectname));
		parameters.add(new BasicNameValuePair("keyword", keyword));
		parameters.add(new BasicNameValuePair("projectid", projectid));
		parameters.add(new BasicNameValuePair("manager", manager));
		parameters.add(new BasicNameValuePair("applysituation", applysituation));
		parameters.add(new BasicNameValuePair("potentialcustomers", potentialcustomers));
		parameters.add(new BasicNameValuePair("projectstate", projectstate));
		
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
	 * 获取文件的相关信息
	 */
	public static String getFileInfo(String filepath)
	{
        String res = null;
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileHandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "getfileinfo"));
		parameters.add(new BasicNameValuePair("filepath", filepath));
		
		
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
	 *获取文件的历史版本
	 */
	public static String getFilehis(String filepath)
	{
        String res = null;
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileHandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "getFilehis"));
		parameters.add(new BasicNameValuePair("filepath", filepath));
		
		
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
	
}
