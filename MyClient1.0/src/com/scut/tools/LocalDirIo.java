package com.scut.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.scut.client.funtion.MyDirHandle;
import com.sun.star.i18n.DirectionProperty;
import com.sun.star.sdbcx.KeyType;

public class LocalDirIo {

	public static final String path = "log/DirIo.log";
	public static final String tmpPath = "log/tmp.log";
	private static Logger log = Logger.getLogger("client");
	
	/*
	 * д���ļ���
	 */
	public static boolean writeDirKeywords(String keywords)
	{
		File file = new File(path);
		PrintWriter pw = null;
		boolean flag = true;
		FileWriter fw = null;
		
		keywords = keywords.replaceAll("null", "");
		
		String dirPath = keywords.substring(0, keywords.indexOf(";"));
		
		if(!ifDirExit(dirPath))
		{
			// �ж��ļ��Ƿ����
			if(!file.exists())
			{
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				fw = new FileWriter(path, true);
				pw = new PrintWriter(fw);
				pw.println(keywords);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.warn(e);
				flag = false;
			}
			finally 
			{
				if(pw != null)
				{
					pw.close();
				}
				if(fw != null)
				{
					try {
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						log.warn(e);
					}
				}
			}
			
		}
		
		return flag;
	
	}
	
	/*
	 * ������ѯ
	 */
	public static String singleSearchClient(String powerstation,
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
		String dirPath = "";	
		Scanner sc = null;
		String line = "";
		String keywords = "";
		String dirPaths = "";
		String[] findKeywords = new String[10];
		String[] fileKeywords = new String[10];
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		findKeywords[0] = powerstation;
		findKeywords[1] = crew;
		findKeywords[2] = projectname;
		findKeywords[3] = keyword;
		findKeywords[4] = projectid;
		findKeywords[5] = manager;
		findKeywords[6] = applysituation;
		findKeywords[7] = potentialcustomers;
		findKeywords[8] = projectstate;
		findKeywords[9] = isprotected;

		try {
			sc = new Scanner(new File(path));
			while(sc.hasNextLine())
			{
				line = sc.nextLine();
				keywords = line.substring(line.lastIndexOf(";")+1, line.length());
				dirPath = line.substring(0, line.lastIndexOf(";"));
				
				for(int i = 0; i < 10; i++)
				{
					fileKeywords[i] = keywords.substring(0, keywords.indexOf(","));
					keywords = keywords.substring(keywords.indexOf(",")+1, keywords.length());
				}
				
				// �жϸ�Ŀ¼�Ĺؼ����Ƿ�Ͳ�ѯ�Ĺؼ���һ����������ѯ
				for(int i = 0; i < 10; i++)
				{
					if(i < 9)
					{
						if(findKeywords[i].equals(fileKeywords[i]) && !findKeywords[i].equals(""))
						{
							dirPaths += dirPath + ",";
							break;
						}
					}
					if(i == 9)
					{
						if(!findKeywords[i].equals(""))
						{
							String fileDate = fileKeywords[i];
							if(findKeywords[i].equals("����"))
							{
								while(!fileDate.equals(""))
								{
									String fileKeywordsDate = fileDate.substring(0, fileDate.indexOf("#"));
									fileDate = fileDate.substring(fileDate.indexOf("#")+1, fileDate.length());
									try {
										if(date.getTime() > sf.parse(fileKeywordsDate).getTime())
										{
											dirPaths += dirPath + ",";
											break;
										}
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
							else if(findKeywords[i].equals("�ڱ�"))
							{
								while(!fileDate.equals(""))
								{
									String fileKeywordsDate = fileDate.substring(0, fileDate.indexOf("#"));
									fileDate = fileDate.substring(fileDate.indexOf("#")+1, fileDate.length());
									try {
										if(date.getTime() <= sf.parse(fileKeywordsDate).getTime())
										{
											dirPaths += dirPath + ",";
											break;
										}
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dirPaths;
	}
	
	/*
	 * ��ϲ�ѯ
	 */
	public static String unioSearchClient(String powerstation,
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
		String dirPath = "";	
		Scanner sc = null;
		String line = "";
		String keywords = "";
		String dirPaths = "";
		String[] findKeywords = new String[10];
		String[] fileKeywords = new String[10];
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		findKeywords[0] = powerstation;
		findKeywords[1] = crew;
		findKeywords[2] = projectname;
		findKeywords[3] = keyword;
		findKeywords[4] = projectid;
		findKeywords[5] = manager;
		findKeywords[6] = applysituation;
		findKeywords[7] = potentialcustomers;
		findKeywords[8] = projectstate;
		findKeywords[9] = isprotected;

		try {
			sc = new Scanner(new File(path));
			while(sc.hasNextLine())
			{
				Boolean flag = true;
				line = sc.nextLine();
				keywords = line.substring(line.lastIndexOf(";")+1, line.length());
				dirPath = line.substring(0, line.lastIndexOf(";"));
				
				for(int i = 0; i < 10; i++)
				{
					fileKeywords[i] = keywords.substring(0, keywords.indexOf(","));
					keywords = keywords.substring(keywords.indexOf(",")+1, keywords.length());
				}
				
				// �жϸ�Ŀ¼�Ĺؼ����Ƿ�Ͳ�ѯ�Ĺؼ���һ������ϲ�ѯ
				for(int i = 0; i < 10; i++)
				{
					if(i < 9)
					{
						if(!findKeywords[i].equals(""))
						{
							if(!findKeywords[i].equals(fileKeywords[i]))
							{
								flag = false;
								break;
							}
						}
					}
					if(i == 9)
					{
						if(!findKeywords[i].equals(""))
						{
							String fileDate = fileKeywords[i];
							Boolean fileDateFlag = false;
							if(findKeywords[i].equals("����"))
							{
								while(!fileDate.equals(""))
								{
									String fileKeywordsDate = fileDate.substring(0, fileDate.indexOf("#"));
									fileDate = fileDate.substring(fileDate.indexOf("#")+1, fileDate.length());
									try {
										if(date.getTime() > sf.parse(fileKeywordsDate).getTime())
										{
											fileDateFlag = true;
											break;
										}
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								flag = fileDateFlag;
							}
							else if(findKeywords[i].equals("�ڱ�"))
							{
								while(!fileDate.equals(""))
								{
									String fileKeywordsDate = fileDate.substring(0, fileDate.indexOf("#"));
									fileDate = fileDate.substring(fileDate.indexOf("#")+1, fileDate.length());
									try {
										if(date.getTime() <= sf.parse(fileKeywordsDate).getTime())
										{
											fileDateFlag = true;
											break;
										}
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								flag = fileDateFlag;
							}
						}
					}
				}
				if(flag)
				{
					dirPaths += dirPath + ",";
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dirPaths;
	}
	
	/*
	 * �жϸ�Ŀ¼�Ƿ��Ѿ�����
	 */
	public static boolean ifDirExit(String dirPath)
	{
		Boolean flag = false;
		
		Scanner sc = null;
		String line = "";
		String paths = "";

		try {
			sc = new Scanner(new File(path));
			while(sc.hasNextLine())
			{
				line = sc.nextLine();
				paths = line.substring(0, line.indexOf(";"));
				if(dirPath.equals(paths))
				{
					flag = true;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/*
	 * �����ļ��е�Ŀ¼�ؼ�����Ϣ
	 */
	public static Boolean rewriteKeywords()
	{
		String dirPath = "";
		Scanner sc = null;
		String line = "";
		String keyWords = "";
		String databaseKeywords = "";
		
		File file = new File(tmpPath);
		PrintWriter pw = null;
		boolean flag = true;
		FileWriter fw = null;
		Boolean flags = false;
		
		// �ж��ļ��Ƿ����
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			sc = new Scanner(new File(path));
			try {
				fw = new FileWriter(tmpPath, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(sc.hasNextLine())
			{
				// ��Ŀ¼�ؼ�����Ϣ�ļ��ж�ȡ��Ŀ¼����Ϣ����Ŀ¼��ɾ�����򽫾ɵ���Ϣд���µ��ļ��У���Ŀ¼û��ɾ���������ݿ��е���Ϣд���ļ���
				line = sc.nextLine();
				dirPath = line.substring(0, line.indexOf(";"));
				keyWords = line.substring(line.indexOf(";")+1, line.length());
				databaseKeywords = MyDirHandle.getDirKeywords(dirPath);
				if(databaseKeywords.endsWith("dirnoexist"))
				{
					pw = new PrintWriter(fw);
					pw.println(line);
				}
				else 
				{
					databaseKeywords += MyDirHandle.getDirApplyEndTime(dirPath) + ",";
					pw = new PrintWriter(fw);
					pw.println(dirPath + ";" + databaseKeywords);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			if(pw != null)
			{
				pw.close();
			}
			if(fw != null)
			{
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.warn(e);
				}
			}
			if(sc != null)
			{
				sc.close();
			}
		}
		flags = renameFile();
		
		return flags;

	}
	
	/*
	 * ɾ���ļ����������ļ�
	 */
	public static Boolean renameFile()
	{
		File deleteFile = new File(path);
		File newFile = new File(tmpPath);
		Boolean flag = false;
		
		// ��ԭ�����ļ�ɾ�����������ɵ��ļ�������Ϊԭ�����ļ�
		if(deleteFile.delete())
		{
			newFile.renameTo(deleteFile);
			flag = true;
		}
		
		return flag;
	}
	
}
