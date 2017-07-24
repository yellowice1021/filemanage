package com.scut.tools;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JMenu;

import com.scut.client.ui.MyTreeCellRender;

public class MyUpdateUi {
	/*
	 * 更新容器的界面的函数
	 */
	public static void updateAllUI(Component c) 
	{
		   if (c == null) return;
		   try 
		   {
		      if (c instanceof JComponent)
		      {
		         ((JComponent) c).updateUI();//
		      } 
		      else 
		      {
		         c.repaint();
		      }
		   } 
		   catch (Exception e) 
		   {
		      e.printStackTrace(); 
		   }
		   if (c instanceof Container) 
		   {
//			  if (c instanceof MyTreeCellRender)
//			  {
//				  Component[] cs = ((MyTreeCellRender) c).getComponents();
//			        for (Component c2 : cs) 
//			        {
//			           updateAllUI(c2);
//			        }
//			  }
		      if (c instanceof JMenu)
		      {// 注意菜单的更新与其他不同
		         Component[] cs = ((JMenu) c).getMenuComponents();
		         for (Component c2 : cs) 
		         {
		            updateAllUI(c2);
		         }
		      }
		      Component[] cs = ((Container) c).getComponents();
		      for (Component c2 : cs) 
		      {
		         updateAllUI(c2);
		      }
		   }
	}
}
