package com.scut.client.funtion;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

/*
 * 在线预览
 */
public class MyScan {
	/*
	 * 调用本地软件打开
	 */
	public static void scan(String path)
	{
		try {
			Desktop.getDesktop().open(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("没有可以预览的方式");
			JOptionPane.showMessageDialog(null, "没有可以打开此文件的软件或\n没有设置过此文件打开方式",null, JOptionPane.INFORMATION_MESSAGE);
		}
		finally
		{
//			File file = new File(path);
//			if( file.delete() )
//			{
//				System.out.println("预览后删除成功");
//			}
		}
	}
	
}
