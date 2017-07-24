package com.suct.client.print;

import java.util.ArrayList;
import java.util.Arrays;

import com.scut.client.funtion.LogHandle;

public class logPrint 
{
	public static void makeLogFile(String res) throws Exception
	{
		ArrayList<String> header = new ArrayList<>(Arrays.asList("��־���", "�û��ʺ�", "��������","�û�����"));
        System.out.println(header.size());

        //ʹ�ô����ͷ�ķ�ʽ�����
        Sheet sheet = new Sheet(header);
        /**
         * ����д��ʾ��
         * ���Ҫ�����о�Ļ���һ����Ҫ�����о�
         */
        //����д�����ݣ�cellContentsΪheader.size()��С��ArrayList����
//        ArrayList<String> cellContents = new ArrayList<>(Arrays.asList("���� a ",
//                "this is b", "this is c", "this is d"));
        
        
        //String res = LogHandle.getAllLog();
		String datas[] = res.split("\t");
		String data[];

		for (String e: datas)
		{
			data = e.split(" ");
			ArrayList<String> cellContents = new ArrayList<>(Arrays.asList(data[0], data[1], data[2], data[3]) );
			sheet.addRowContent(cellContents);
		}

        //�����ݱ��浽�ļ���
        //����ʹ��sheet.saveRowToFile()������ÿһ����ӵ��ж����뵽�ļ���
        if (sheet.saveToFile())
        {
            //do something
        }
	}
	
	public static void printLogFile()
	{
		
	}
}
