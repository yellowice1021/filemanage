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

import com.scut.client.ui.MyMainFrame;
import com.scut.tools.MyParameters;

/*
 * ��¼ģ�����ز���
 */
public class MyLogin 
{
	private static HttpClient httpClient;
	
	private static Logger log = Logger.getLogger("client");
	static
	{
			//���봴��һ��clientȥִ��
		httpClient = HttpClients.createDefault();
	}
	
	/*
	 * ��¼
	 */
	public static String login(String userName, String passwd)
	{
		String res = null;
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/LoginServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
		parameters.add(new BasicNameValuePair("userName", userName));
		parameters.add(new BasicNameValuePair("passwd", passwd));
		//parameters.add(new BasicNameValuePair("type", type));
		
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
			//JOptionPane.showMessageDialog(null, "������", "������������˻��߷������ܾ�����", JOptionPane.ERROR_MESSAGE);
			int answer = JOptionPane.showConfirmDialog(null, "������", "������������˻��߷������ܾ�����\n �Ƿ�ǿ�ƹر�", JOptionPane.YES_NO_OPTION);
			if(answer == JOptionPane.YES_OPTION)
			{
				MyMainFrame.frame.dispose();//ǿ�ƹر�
			}
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
		log.debug(result);
		return result;
	}
	/*
	 * �˳���¼
	 */
	public static String quit(String userName)
	{
		String res = null;
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/QuitServlet";
		log.debug(url);
		HttpPost httpPost = new HttpPost(url);
        List<NameValuePair>parameters = new ArrayList<NameValuePair>();
		
		parameters.add(new BasicNameValuePair("userName", userName));

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
			JOptionPane.showMessageDialog(null, "������", "������������˻��߷������ܾ�����", JOptionPane.ERROR_MESSAGE);
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
	
	public static String getLoginString(int res)
	{
		String result = null;
		switch(res)
		{
		case 1:
			result = "�����û�";
			break;
		case 2:
			result = "����Ա";
			break;
		case 3:
			result = "��ͨ�û�";
			break;
		case 4:
			result = "�ʺŲ�����";
			break;
		case 5:
			result = "�������";
			break;
		case 6:
			result = "���ʺ��Ѿ�����¼";
			break;
		}
		return result;
	}
}
