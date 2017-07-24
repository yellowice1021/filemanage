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
			//必须创建一个client去执行
		httpClient = HttpClients.createDefault();
	}
	
	/*
	 * 判断文件是否存在
	 */
	public static String isFileExist(String serverPath)
	{
		log.debug("目标路径" + serverPath);
		String res = "";
			//服务器的地址
			String url = "http://";
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/FileHandleServlet";
			log.debug("服务器的地址"+url);
			
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
	 * 上传文件
	 */
	public static String uploadFile(String clientPath, String serverPath)
	{
		log.debug("本地路径："+ clientPath +",目标路径" + serverPath);
		String res = "";
		try{
			//服务器的地址
			String url = "http://";
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/UpLoadServlet";
			log.debug("服务器的地址"+url);
			
	    	HttpPost httpPost = new HttpPost(url);
	    	
	    	File file = new File(clientPath);
	    	
	    	//把文件对象转化为流对象FileBody,字符串转化成StringBody,所有要传的数据全部改为Body类型 
	    	FileBody fileBody = new FileBody(file);
	    	
		
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.setCharset(CharsetUtils.get("UTF-8"));
			builder.addPart("clientFile", fileBody);
			
			//StringBody内部编码有问题,用TextBody
			//builder.addTextBody("clientPath", clientPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("serverPath", serverPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("clientUser", MyUser.userName, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
	    
	    	
	    	HttpResponse response = httpClient.execute(httpPost);
	    	int statusCode = response.getStatusLine().getStatusCode(); 
	    	
	    	if(statusCode == HttpStatus.SC_OK)
	    	{  
	             log.info("服务器正常响应，上传成功");  
	             HttpEntity resEntity = response.getEntity();  
	             res = EntityUtils.toString(resEntity);
	             System.out.println(res);//httpclient自带的工具类读取返回数据   
	             System.out.println(resEntity.getContent());  
	             EntityUtils.consume(resEntity);  
	        }  
	    	else
	    	{
	
	    		log.info("上传失败" + statusCode);
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
	 * 上传同名文件
	 */
	public static String mulUploadFile(String clientPath, String serverPath)
	{
		log.debug("本地路径："+ clientPath +",目标路径" + serverPath);
		String res = "";
		try{
			//服务器的地址
			String url = "http://";
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/MulUploadServlet";
			log.debug("服务器的地址"+url);
			
	    	HttpPost httpPost = new HttpPost(url);
	    	
	    	File file = new File(clientPath);
	    	
	    	//把文件对象转化为流对象FileBody,字符串转化成StringBody,所有要传的数据全部改为Body类型 
	    	FileBody fileBody = new FileBody(file);
	    	
		
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.setCharset(CharsetUtils.get("UTF-8"));
			builder.addPart("clientFile", fileBody);
			
			//StringBody内部编码有问题,用TextBody
			//builder.addTextBody("clientPath", clientPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("serverPath", serverPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("clientUser", MyUser.userName, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
	    
	    	
	    	HttpResponse response = httpClient.execute(httpPost);
	    	int statusCode = response.getStatusLine().getStatusCode(); 
	    	
	    	if(statusCode == HttpStatus.SC_OK)
	    	{  
	             log.info("服务器正常响应，上传成功");  
	             HttpEntity resEntity = response.getEntity();  
	             res = EntityUtils.toString(resEntity);
	             System.out.println(res);//httpclient自带的工具类读取返回数据   
	             System.out.println(resEntity.getContent());  
	             EntityUtils.consume(resEntity);  
	        }  
	    	else
	    	{
	
	    		log.info("上传失败" + statusCode);
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
	 * 上传目录图片
	 */
	public static String uploadDirPic(String clientPath, String serverPath)
	{
		log.debug("目标路径" + serverPath);
		String res = "";
		try{
			//服务器的地址
			String url = "http://";
			url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DirPicServlet";
			log.debug("服务器的地址"+url);
			
	    	HttpPost httpPost = new HttpPost(url);
	    	
	    	File file = new File(clientPath);
	    	
	    	//把文件对象转化为流对象FileBody,字符串转化成StringBody,所有要传的数据全部改为Body类型 
	    	FileBody fileBody = new FileBody(file);
	    	
		
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.setCharset(CharsetUtils.get("UTF-8"));
			builder.addPart("clientFile", fileBody);
			
			//StringBody内部编码有问题,用TextBody
			//builder.addTextBody("clientPath", clientPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("serverPath", serverPath, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			builder.addTextBody("clientUser", MyUser.userName, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
	    
	    	
	    	HttpResponse response = httpClient.execute(httpPost);
	    	int statusCode = response.getStatusLine().getStatusCode(); 
	    	
	    	if(statusCode == HttpStatus.SC_OK)
	    	{  
	             log.info("服务器正常响应，上传成功");  
	             HttpEntity resEntity = response.getEntity();  
	             res = EntityUtils.toString(resEntity);
	             System.out.println(res);//httpclient自带的工具类读取返回数据   
	             System.out.println(resEntity.getContent());  
	             EntityUtils.consume(resEntity);  
	        }  
	    	else
	    	{
	
	    		log.info("上传失败" + statusCode);
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
	 * 下载在线预览的文件
	 */
	public static void onlineScanFile(String serverPath, String clientPath)

	{
		//HttpClient httpclient = HttpClients.createDefault();
		log.debug("本地路径："+ clientPath +",目标路径" + serverPath);
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DownLoadServlet";
		log.debug("服务器的地址"+url);
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
        
        //在本地先新建该文件
        File file = new File(clientPath);
        //该文件的父目录不存在，先创建
        if (!file.getParentFile().exists())  
		{
			file.getParentFile().mkdirs();
		}
        //该文件不存在，则新建
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
        System.out.println("文件长度:"+length);
        log.info(file.toString()+"该被下载的文件长度:"+length);
        
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
		log.debug("本地路径："+ clientPath +",目标路径" + serverPath);
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DownLoadServlet";
		log.debug("服务器的地址"+url);
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
        
        //在本地先新建该文件
        File file = new File(clientPath);
        //该文件的父目录不存在，先创建
        if (!file.getParentFile().exists())  
		{
			file.getParentFile().mkdirs();
		}
        //该文件不存在，则新建
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
        System.out.println("文件长度:"+length);
        log.info(file.toString()+"该被下载的文件长度:"+length);
        
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
		log.debug("目标路径" + serverPath);
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DelDirPicServlet";
		log.debug("服务器的地址"+url);
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
		log.debug("本地路径："+ clientPath +",目标路径" + serverPath);
		String url = "http://";
		url += MyParameters.getIp()+":"+MyParameters.getPort()+"/Server_test/DownLoadServlet";
		log.debug("服务器的地址"+url);
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
        
        //在本地先新建该文件
        File file = new File(clientPath);
        //该文件的父目录不存在，先创建
        if (!file.getParentFile().exists())  
		{
			file.getParentFile().mkdirs();
		}
        //该文件不存在，则新建
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
        System.out.println("文件长度:"+length);
        log.info(file.toString()+"该被下载的文件长度:"+length);
        
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
