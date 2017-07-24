package com.scut.client.funtion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.scut.client.ui.MyProgressThread;
import com.scut.client.ui.MyTableThread;
import com.scut.client.user.MyUser;
import com.scut.tools.MyParameters;

public class UpDownFile 
{
	private static Logger log = Logger.getLogger("client");
	private static JProgressBar progressBar;
	private static int row,col;
	private static DefaultTableModel tbModel;
	private static HttpClient httpClient = null;
	
	static
	{
			//���봴��һ��clientȥִ��
		httpClient = HttpClients.createDefault();
	}
	
	/*
	 * �ж��ļ��Ƿ����
	 */
	public static String isFileExist(String serverPath)
	{
		log.debug("Ŀ��·��" + serverPath);
		String res = "";
			//�������ĵ�ַ
			String url = "http://";
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileHandleServlet";
			log.debug("�������ĵ�ַ"+url);
			
	    	HttpPost httpPost = new HttpPost(url);
	    	
	    	List<NameValuePair>parameters = new ArrayList<NameValuePair>();
	    	parameters.add(new BasicNameValuePair("serverPath", serverPath));
	    	parameters.add(new BasicNameValuePair("type", "fileexist"));
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
	
	/*
	 * �ϴ��ļ�
	 */
	public static String uploadFile(String clientPath, String serverPath)
	{
		log.debug("����·����"+ clientPath +",Ŀ��·��" + serverPath);
		String res = "";
		try{
			//�������ĵ�ַ
			String url = "http://";
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/UpLoadServlet";
			log.debug("�������ĵ�ַ"+url);
			
	    	HttpPost httpPost = new HttpPost(url);
	    	
	    	File file = new File(clientPath);
	    	
	    	//���ļ�����ת��Ϊ������FileBody,�ַ���ת����StringBody,����Ҫ��������ȫ����ΪBody���� 
	    	FileBody fileBody = new FileBody(file);
	    	
		
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.setCharset(CharsetUtils.get("UTF-8"));
			builder.addPart("clientFile", fileBody);
			
			//StringBody�ڲ�����������,��TextBody
			//builder.addTextBody("clientPath", clientPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("serverPath", serverPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("clientUser", MyUser.userName, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
	    
	    	
	    	HttpResponse response = httpClient.execute(httpPost);
	    	int statusCode = response.getStatusLine().getStatusCode(); 
	    	
	    	if(statusCode == HttpStatus.SC_OK)
	    	{  
	             log.info("������������Ӧ���ϴ��ɹ�");  
	             HttpEntity resEntity = response.getEntity();  
	             res = EntityUtils.toString(resEntity);
	             System.out.println(res);//httpclient�Դ��Ĺ������ȡ��������   
	             System.out.println(resEntity.getContent());  
	             EntityUtils.consume(resEntity);  
	        }  
	    	else
	    	{
	
	    		log.info("�ϴ�ʧ��" + statusCode);
	    	}
		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 log.warn(e);
		}
		finally
		{
			//httpClient.getConnectionManager().shutdown();
		}
		
    	return res;
    	
    	
	}
	
	/*
	 * �ϴ�ͬ���ļ�
	 */
	public static String mulUploadFile(String clientPath, String serverPath)
	{
		log.debug("����·����"+ clientPath +",Ŀ��·��" + serverPath);
		String res = "";
		try{
			//�������ĵ�ַ
			String url = "http://";
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/MulUploadServlet";
			log.debug("�������ĵ�ַ"+url);
			
	    	HttpPost httpPost = new HttpPost(url);
	    	
	    	File file = new File(clientPath);
	    	
	    	//���ļ�����ת��Ϊ������FileBody,�ַ���ת����StringBody,����Ҫ��������ȫ����ΪBody���� 
	    	FileBody fileBody = new FileBody(file);
	    	
		
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.setCharset(CharsetUtils.get("UTF-8"));
			builder.addPart("clientFile", fileBody);
			
			//StringBody�ڲ�����������,��TextBody
			//builder.addTextBody("clientPath", clientPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("serverPath", serverPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("clientUser", MyUser.userName, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
	    
	    	
	    	HttpResponse response = httpClient.execute(httpPost);
	    	int statusCode = response.getStatusLine().getStatusCode(); 
	    	
	    	if(statusCode == HttpStatus.SC_OK)
	    	{  
	             log.info("������������Ӧ���ϴ��ɹ�");  
	             HttpEntity resEntity = response.getEntity();  
	             res = EntityUtils.toString(resEntity);
	             System.out.println(res);//httpclient�Դ��Ĺ������ȡ��������   
	             System.out.println(resEntity.getContent());  
	             EntityUtils.consume(resEntity);  
	        }  
	    	else
	    	{
	
	    		log.info("�ϴ�ʧ��" + statusCode);
	    	}
		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 log.warn(e);
		}
		finally
		{
			//httpClient.getConnectionManager().shutdown();
		}
		
    	return res;
    	
    	
	}
	
	/*
	 * �ϴ�Ŀ¼ͼƬ
	 */
	public static String uploadDirPic(String clientPath, String serverPath)
	{
		log.debug("Ŀ��·��" + serverPath);
		String res = "";
		try{
			//�������ĵ�ַ
			String url = "http://";
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirPicServlet";
			log.debug("�������ĵ�ַ"+url);
			
	    	HttpPost httpPost = new HttpPost(url);
	    	
	    	File file = new File(clientPath);
	    	
	    	//���ļ�����ת��Ϊ������FileBody,�ַ���ת����StringBody,����Ҫ��������ȫ����ΪBody���� 
	    	FileBody fileBody = new FileBody(file);
	    	
		
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.setCharset(CharsetUtils.get("UTF-8"));
			builder.addPart("clientFile", fileBody);
			
			//StringBody�ڲ�����������,��TextBody
			//builder.addTextBody("clientPath", clientPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("serverPath", serverPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("clientUser", MyUser.userName, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
	    
	    	
	    	HttpResponse response = httpClient.execute(httpPost);
	    	int statusCode = response.getStatusLine().getStatusCode(); 
	    	
	    	if(statusCode == HttpStatus.SC_OK)
	    	{  
	             log.info("������������Ӧ���ϴ��ɹ�");  
	             HttpEntity resEntity = response.getEntity();  
	             res = EntityUtils.toString(resEntity);
	             System.out.println(res);//httpclient�Դ��Ĺ������ȡ��������   
	             System.out.println(resEntity.getContent());  
	             EntityUtils.consume(resEntity);  
	        }  
	    	else
	    	{
	
	    		log.info("�ϴ�ʧ��" + statusCode);
	    	}
		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 log.warn(e);
		}
		finally
		{
			//httpClient.getConnectionManager().shutdown();
		}
		
    	return res;
    	
    	
	}
	
	/*
	 * ��������Ԥ�����ļ�
	 */
	public static void onlineScanFile(String serverPath, String clientPath)

	{
		//HttpClient httpclient = HttpClients.createDefault();
		log.debug("����·����"+ clientPath +",Ŀ��·��" + serverPath);
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DownLoadServlet";
		log.debug("�������ĵ�ַ"+url);
    	List<NameValuePair>parameters = new ArrayList<NameValuePair>();
    	parameters.add(new BasicNameValuePair("serverPath", serverPath));
    	parameters.add(new BasicNameValuePair("clientUser", MyUser.userName));
	
    	
    	HttpGet httpGet = null;
		try {
			String str = EntityUtils.toString(new UrlEncodedFormEntity(parameters,Consts.UTF_8));
			httpGet = new HttpGet(url + "?" + str);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			log.warn(e2.toString());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			log.warn(e2.toString());
		}

		
        HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		}
        HttpEntity entity = response.getEntity();
        
        
        OutputStream out = null;
        InputStream in = null;
        
        //�ڱ������½����ļ�
        File file = new File(clientPath);
        //���ļ��ĸ�Ŀ¼�����ڣ��ȴ���
        if (!file.getParentFile().exists())  
		{
			file.getParentFile().mkdirs();
		}
        //���ļ������ڣ����½�
        if( !file.exists() )     
    	{
        	try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        long length = entity.getContentLength();
        System.out.println("�ļ�����:"+length);
        log.info(file.toString()+"�ñ����ص��ļ�����:"+length);
        
        MyProgressThread  mpt= new MyProgressThread((int)length, progressBar);
        new Thread(mpt).start();
        
        try 
        {
            in= entity.getContent();
            out = new FileOutputStream(file);  
             
            byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength=in.read(buffer)) > 0)
            {
            	byte[] bytes = new byte[readLength];
            	System.arraycopy(buffer, 0, bytes, 0, readLength);
            	out.write(bytes);
            	mpt.setNowSize(readLength);
            }
                      
            out.flush();
            
        } 
        catch (IOException e) {
        	e.printStackTrace();
        	log.warn(e.toString());
        }	 
        catch (Exception e) {
        	e.printStackTrace();
        	log.warn(e.toString());
        }
        finally 
        {
           
            if(in != null)
            {
            	try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.warn(e.toString());
				}
            }
            if(out != null)
            {
            	try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.warn(e.toString());
				}
            }
            
        }
		
	}
	
	
	
	public static void downloadFile(String serverPath, String clientPath)

	{
		//HttpClient httpclient = HttpClients.createDefault();
		log.debug("����·����"+ clientPath +",Ŀ��·��" + serverPath);
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DownLoadServlet";
		log.debug("�������ĵ�ַ"+url);
    	List<NameValuePair>parameters = new ArrayList<NameValuePair>();
    	parameters.add(new BasicNameValuePair("serverPath", serverPath));
    	parameters.add(new BasicNameValuePair("clientUser", MyUser.userName));
	
    	
    	HttpGet httpGet = null;
		try {
			String str = EntityUtils.toString(new UrlEncodedFormEntity(parameters,Consts.UTF_8));
			httpGet = new HttpGet(url + "?" + str);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			log.warn(e2.toString());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			log.warn(e2.toString());
		}

		
        HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		}
        HttpEntity entity = response.getEntity();
        
        
        OutputStream out = null;
        InputStream in = null;
        
        //�ڱ������½����ļ�
        File file = new File(clientPath);
        //���ļ��ĸ�Ŀ¼�����ڣ��ȴ���
        if (!file.getParentFile().exists())  
		{
			file.getParentFile().mkdirs();
		}
        //���ļ������ڣ����½�
        if( !file.exists() )     
    	{
        	try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        long length = entity.getContentLength();
        System.out.println("�ļ�����:"+length);
        log.info(file.toString()+"�ñ����ص��ļ�����:"+length);
        
        MyProgressThread  mpt= new MyProgressThread((int)length, progressBar);
        new Thread(mpt).start();
        
        MyTableThread mtt = new MyTableThread(tbModel, row, col, (int)length);
        new Thread(mtt).start();
        
        try 
        {
            in= entity.getContent();
            out = new FileOutputStream(file);  
             
            byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength=in.read(buffer)) > 0)
            {
            	byte[] bytes = new byte[readLength];
            	System.arraycopy(buffer, 0, bytes, 0, readLength);
            	out.write(bytes);
            	mpt.setNowSize(readLength);
            	mtt.setNowSize(readLength);//////////////////////////////////////////
            }
                      
            out.flush();
            
        } 
        catch (IOException e) {
        	e.printStackTrace();
        	log.warn(e.toString());
        }	 
        catch (Exception e) {
        	e.printStackTrace();
        	log.warn(e.toString());
        }
        finally 
        {
           
            if(in != null)
            {
            	try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.warn(e.toString());
				}
            }
            if(out != null)
            {
            	try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.warn(e.toString());
				}
            }
            
        }
		
	}
	
	public static void delDirPic(String serverPath)
	{
		//HttpClient httpclient = HttpClients.createDefault();
		log.debug("Ŀ��·��" + serverPath);
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DelDirPicServlet";
		log.debug("�������ĵ�ַ"+url);
    	List<NameValuePair>parameters = new ArrayList<NameValuePair>();
    	parameters.add(new BasicNameValuePair("dirpath", serverPath));
    	
    	HttpGet httpGet = null;
		try {
			String str = EntityUtils.toString(new UrlEncodedFormEntity(parameters,Consts.UTF_8));
			httpGet = new HttpGet(url + "?" + str);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			log.warn(e2.toString());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			log.warn(e2.toString());
		}

		
        HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		}
        HttpEntity entity = response.getEntity();
               		
	}
	
	public static void downloadDirPic(String serverPath, String clientPath)

	{
		//HttpClient httpclient = HttpClients.createDefault();
		log.debug("����·����"+ clientPath +",Ŀ��·��" + serverPath);
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DownLoadServlet";
		log.debug("�������ĵ�ַ"+url);
    	List<NameValuePair>parameters = new ArrayList<NameValuePair>();
    	parameters.add(new BasicNameValuePair("serverPath", serverPath));
    	parameters.add(new BasicNameValuePair("clientUser", MyUser.userName));
	
    	
    	HttpGet httpGet = null;
		try {
			String str = EntityUtils.toString(new UrlEncodedFormEntity(parameters,Consts.UTF_8));
			httpGet = new HttpGet(url + "?" + str);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			log.warn(e2.toString());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			log.warn(e2.toString());
		}

		
        HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.warn(e1.toString());
		}
        HttpEntity entity = response.getEntity();
        
        
        OutputStream out = null;
        InputStream in = null;
        
        //�ڱ������½����ļ�
        File file = new File(clientPath);
        //���ļ��ĸ�Ŀ¼�����ڣ��ȴ���
        if (!file.getParentFile().exists())  
		{
			file.getParentFile().mkdirs();
		}
        //���ļ������ڣ����½�
        if( !file.exists() )     
    	{
        	try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        long length = entity.getContentLength();
        System.out.println("�ļ�����:"+length);
        log.info(file.toString()+"�ñ����ص��ļ�����:"+length);
        
        try 
        {
            in= entity.getContent();
            out = new FileOutputStream(file);  
             
            byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength=in.read(buffer)) > 0)
            {
            	byte[] bytes = new byte[readLength];
            	System.arraycopy(buffer, 0, bytes, 0, readLength);
            	out.write(bytes);
            }
                      
            out.flush();
            
        } 
        catch (IOException e) {
        	e.printStackTrace();
        	log.warn(e.toString());
        }	 
        catch (Exception e) {
        	e.printStackTrace();
        	log.warn(e.toString());
        }
        finally 
        {
           
            if(in != null)
            {
            	try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.warn(e.toString());
				}
            }
            if(out != null)
            {
            	try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.warn(e.toString());
				}
            }
            
        }
		
	}

	public static void getMyProgressBar(JProgressBar progressbars) {
		// TODO Auto-generated method stub
		progressBar = progressbars;
	}
	
	public static void setTableThreadPara(DefaultTableModel tbModels, int rows, int cols)
	{
		tbModel = tbModels;
		row = rows;
		col = cols;
	}
}
