package com.scut.client.funtion;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

/*
 * ����Ԥ��
 */
public class MyScan {
	/*
	 * ���ñ��������
	 */
	public static void scan(String path)
	{
		try {
			Desktop.getDesktop().open(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("û�п���Ԥ���ķ�ʽ");
			JOptionPane.showMessageDialog(null, "û�п��Դ򿪴��ļ��������\nû�����ù����ļ��򿪷�ʽ",null, JOptionPane.INFORMATION_MESSAGE);
		}
		finally
		{
//			File file = new File(path);
//			if( file.delete() )
//			{
//				System.out.println("Ԥ����ɾ���ɹ�");
//			}
		}
	}
	
}
