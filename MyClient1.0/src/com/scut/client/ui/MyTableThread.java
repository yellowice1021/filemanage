package com.scut.client.ui;

import java.text.NumberFormat;

import javax.swing.table.DefaultTableModel;

import org.apache.http.util.Asserts;
import org.apache.log4j.Logger;

public class MyTableThread implements Runnable{
	
	private int row,column;
	private DefaultTableModel tbModel;
	private Object object;
	private int nowSize;
	private int maxSize;
	private static Logger log = Logger.getLogger("client");
	private static NumberFormat nt = NumberFormat.getPercentInstance();
	static
	{
		//设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);
	}
	public MyTableThread(Object value) {
		// TODO Auto-generated constructor stub
		object = value;
	}
	
	public MyTableThread(DefaultTableModel tbModel, int row, int column, int maxSize) {
		// TODO Auto-generated constructor stub
		this.tbModel = tbModel;
		this.row =row;
		this.column = column;
		this.object = tbModel.getValueAt(row, column);
		this.nowSize = 0;
		this.maxSize = maxSize;
	}
	
	public void setNowSize(int size)
	{
		nowSize += size;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while( nowSize < maxSize)
		{
			//object = nowSize*1.0/maxSize +"%";
			object = nt.format(nowSize*1.0/maxSize);
			tbModel.setValueAt(object, row, column);
			//System.out.println(object);
			try 
			{
				Thread.sleep(500);
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.warn(e);
			}
		}
		object = "100%";
		tbModel.setValueAt(object, row, column);
	}

}
