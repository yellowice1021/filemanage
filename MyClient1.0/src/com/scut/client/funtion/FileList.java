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
 * 获取服务器的文件和目录
 */
public class FileList 
{
	private static HttpClient httpClient;
	private static Logger log = Logger.getLogger("client");
	static
	{
	    //必须创建一个client去执行
		httpClient = HttpClients.createDefault();
	}
	public static String sendReq(String Dirname)
	{
		//服务器的地址
		//String url = "http://localhost:8080/Server_test/SolveMessageServlet";
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileListServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
		    	
		List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
		parameters.add(new BasicNameValuePair("clientUser", MyUser.userName));
		parameters.add(new BasicNameValuePair("Dirname", Dirname));
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
		//设置代理
		//httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost("192.168.0.101", 3128));
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
			e1.printStackTrace();
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1);	
		}
		
		HttpEntity entity = response.getEntity();
		String result = "false";
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
		
		return result;                                   
	}
	
	public static String listSonfile(String Dirname)
	{
		//服务器的地址
		//String url = "http://localhost:8080/Server_test/SolveMessageServlet";
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/SonFileListServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
		    	
		List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
		parameters.add(new BasicNameValuePair("clientUser", MyUser.userName));
		parameters.add(new BasicNameValuePair("filepath", Dirname));
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
		//设置代理
		//httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost("192.168.0.101", 3128));
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
			e1.printStackTrace();
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1);	
		}
		
		HttpEntity entity = response.getEntity();
		String result = "false";
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
		
		return result;                                   
	}
	
	public static String isfile(String filename)
	{
		//服务器的地址
		//String url = "http://localhost:8080/Server_test/SolveMessageServlet";
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileHandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
		    	
		List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
		parameters.add(new BasicNameValuePair("clientUser", MyUser.userName));
		parameters.add(new BasicNameValuePair("type", "isfile"));
		parameters.add(new BasicNameValuePair("filepath", filename));
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
		//设置代理
		//httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost("192.168.0.101", 3128));
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
			e1.printStackTrace();
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1);	
		}
		
		HttpEntity entity = response.getEntity();
		String result = "false";
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
		
		return result;                                   
	}
}
