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

public class SynHandle 
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
	public static String syn()
	{
        String res = "";
		
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/SynDb";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
        parameters.add(new BasicNameValuePair("clientuser", MyUser.userName));

		
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
