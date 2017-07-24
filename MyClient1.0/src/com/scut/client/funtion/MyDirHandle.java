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
 * 发送文件夹相关操作的http
 */
public class MyDirHandle 
{
	private static Logger log = Logger.getLogger("client");
	private static HttpClient httpClient;
	static
	{
		httpClient = HttpClients.createDefault();
	}
	
	/*
	 * 增加目录
	 */
	public static String addDir(String Dirname, String serverPath)
	{
        String res = "";
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
		parameters.add(new BasicNameValuePair("type", "addDir"));
		parameters.add(new BasicNameValuePair("Dirname", Dirname));
		parameters.add(new BasicNameValuePair("serverPath", serverPath));
		
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
	
	
	public static String getDirapply(String filepath)
	{
        String res = null;
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "getApply"));
		parameters.add(new BasicNameValuePair("serverPath", filepath));
		
		
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
	 * 增加应用电站
	 */
	public static String addApply(String Dirname, String applystring)
			//String powersattion, String starttime, String protecttime)
	{
        String res = "";
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
		parameters.add(new BasicNameValuePair("type", "addApply"));
		parameters.add(new BasicNameValuePair("Dirname", Dirname));
		parameters.add(new BasicNameValuePair("applystring", applystring));
//		parameters.add(new BasicNameValuePair("powersattion", powersattion));
//		parameters.add(new BasicNameValuePair("starttime", starttime));
//		parameters.add(new BasicNameValuePair("protecttime", protecttime));
		
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
	 * 增加目录的相关信息
	 */
	public static String addDirInfo(String funtiondesc,
			String technicalFeature,
			String projectstate,
			String potentialcustomr,
			String Dirname)
	{
		    String res = null;
			
			String url = "http://";
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
			log.debug(url);
			HttpPost httpPost = new HttpPost(url);
	        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
			
	        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
	        parameters.add(new BasicNameValuePair("serverPath", Dirname));
	        parameters.add(new BasicNameValuePair("funtiondesc", funtiondesc));
	        parameters.add(new BasicNameValuePair("technicalFeature", technicalFeature));
	        parameters.add(new BasicNameValuePair("potentialcustomr", potentialcustomr));
	        parameters.add(new BasicNameValuePair("projectstate", projectstate));
	        parameters.add(new BasicNameValuePair("type", "addDirInfo"));
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
	
	public static String getDirKeywords(String filepath)
	{
        String res = null;
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "getdirkeyword"));
		parameters.add(new BasicNameValuePair("serverPath", filepath));
		
		
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
	
	public static String addDirKeywords(
			String filepath,
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
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
			log.debug(url);
			HttpPost httpPost = new HttpPost(url);
	        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
	        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
	        parameters.add(new BasicNameValuePair("type", "adddirkeyword"));
			parameters.add(new BasicNameValuePair("serverPath", filepath));
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
	 * 删除目录
	 */
	public static String delDir(String serverPath)
	{
        String res = "";
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
		parameters.add(new BasicNameValuePair("type", "delDir"));
		parameters.add(new BasicNameValuePair("serverPath", serverPath));
		
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
	 * 获取目录的相关信息
	 */
	public static String getDirInfo(String Dirname)
	{
		    String res = null;
			
			String url = "http://";
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
			log.debug(url);
			HttpPost httpPost = new HttpPost(url);
	        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
			
	        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
	        parameters.add(new BasicNameValuePair("serverPath", Dirname));
	        parameters.add(new BasicNameValuePair("type", "getDirInfo"));
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
	 * 获取服务器端目录图片路径
	 */
	public static String getDirPicPath(String Dirname)
	{
		    String res = null;
			
			String url = "http://";
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
			log.debug(url);
			HttpPost httpPost = new HttpPost(url);
	        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
			
	        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
	        parameters.add(new BasicNameValuePair("serverPath", Dirname));
	        parameters.add(new BasicNameValuePair("type", "getDirPic"));
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
	
	public static String getDirApplyEndTime(String dirPath)
	{
        String res = null;
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirhandleServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));
        parameters.add(new BasicNameValuePair("type", "getDirApplyEndTime"));
		parameters.add(new BasicNameValuePair("serverPath", dirPath));
		
		
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
