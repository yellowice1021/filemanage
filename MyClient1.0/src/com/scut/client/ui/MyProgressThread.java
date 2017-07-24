package com.scut.client.ui;


import javax.swing.JProgressBar;

public class MyProgressThread implements Runnable{

	private JProgressBar progressBar;
	private int nowValue;
	
	
	public MyProgressThread() 
	{
		// TODO Auto-generated constructor stub
		this.nowValue = 0;
	}
	
	public MyProgressThread(int maxsize, JProgressBar progressbar) 
	{
		// TODO Auto-generated constructor stub
		this.nowValue = 0;
		this.progressBar = progressbar;
		this.progressBar.setMaximum(maxsize);
		this.progressBar.setMinimum(0);
		System.out.println("���ؿ�ʼ");
	}
	
	public void setMyProgressBar( JProgressBar progressbar)
	{
		this.progressBar = progressbar;
	}
	
	public void setMyMaximum(int maxsize)
	{
		this.progressBar.setMaximum(maxsize);
	}
	
	/*
	 * ÿ���������øú�����ǰֵ
	 */
	public void setNowSize(long size)
	{
		nowValue += (int)size;
	}
	
	/*
	 * 
	 * ����progressBar���߳�
	 */
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while(nowValue < progressBar.getMaximum())
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(nowValue);
			this.progressBar.setValue(nowValue);
		}
		this.progressBar.setValue(nowValue);
		System.out.println("�������");
	}

}