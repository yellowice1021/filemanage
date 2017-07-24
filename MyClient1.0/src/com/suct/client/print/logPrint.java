package com.suct.client.print;

import java.util.ArrayList;
import java.util.Arrays;

import com.scut.client.funtion.LogHandle;

public class logPrint 
{
	public static void makeLogFile(String res) throws Exception
	{
		ArrayList<String> header = new ArrayList<>(Arrays.asList("日志编号", "用户帐号", "操作日期","用户操作"));
        System.out.println(header.size());

        //使用传入表头的方式构造表
        Sheet sheet = new Sheet(header);
        /**
         * 按行写入示例
         * 如果要设置列距的话第一步就要设置列距
         */
        //逐行写入数据，cellContents为header.size()大小的ArrayList对象
//        ArrayList<String> cellContents = new ArrayList<>(Arrays.asList("这是 a ",
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

        //把内容保存到文件中
        //可以使用sheet.saveRowToFile()方法将每一次添加的行都输入到文件中
        if (sheet.saveToFile())
        {
            //do something
        }
	}
	
	public static void printLogFile()
	{
		
	}
}
