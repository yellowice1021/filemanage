package com.scut.client.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import com.itextpdf.text.log.SysoCounter;
import com.scut.client.funtion.MyLogin;
import com.scut.client.user.MyPath;
import com.scut.client.user.MyUser;
import com.scut.client.user.WorkingJob;
import com.scut.tools.DirDeal;

public class MyHandleWin extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
    {
		System.out.println("close1"+WorkingJob.workingjob);
		if(WorkingJob.workingjob > 0)  //如果正在上传或者下载，不能退出
		{
			System.out.println("close2"+WorkingJob.workingjob);
			JOptionPane.showConfirmDialog(null, "当前有文件在下载或上传，请稍后", null, JOptionPane.YES_OPTION);
			return;
		}
		int answer = JOptionPane.showConfirmDialog(null, "是否直接退出？", null, JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION)
		{
			DirDeal.delDir(MyPath.scanPath + "/doc");
			if (!MyUser.userName.equals("游客")) MyLogin.quit(MyUser.userName);
			(e.getWindow()).dispose();
			System.exit(0);
		}
    }
}
