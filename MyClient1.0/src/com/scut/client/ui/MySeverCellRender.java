package com.scut.client.ui;

import java.awt.Component;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

import com.scut.tools.MyImage;

public class MySeverCellRender extends DefaultTreeCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Icon getSmallIcon(File file)
	{
		if (file != null && file.exists())
		{
			FileSystemView fsv = FileSystemView.getFileSystemView();
			return fsv.getSystemIcon(file);
		}
		return null;
	}
	
	private static String getTreePath(TreeNode[] treenode)
	{
		String tmp = "";
         for (int i = 1; i < treenode.length-1; i++)
         {
         	 tmp += (treenode[i].toString() + "\\");
         }
         tmp += treenode[ treenode.length-1 ].toString();
		 return tmp;
	}
	
	
	/** 
     * 重写父类DefaultTreeCellRenderer的方法 
     */  
    @Override  
    public Component getTreeCellRendererComponent(JTree tree, Object value,  
            boolean sel, boolean expanded, boolean leaf, int row,  
            boolean hasFocus)  
    {  
  
        //执行父类原型操作  
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,  
                row, hasFocus);  
  
        setText(value.toString());  
        //得到每个节点的TreeNode  
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;  
        String name = node.toString();
       
        if (name.endsWith(".txt"))
        {
        	this.setIcon(MyImage.txt);
        }
        else if (name.endsWith(".doc") || name.endsWith(".docx"))
        {
        	this.setIcon(MyImage.word);
        }
        else if (name.endsWith(".rar"))
        {
        	this.setIcon(MyImage.rar);  
        }
        else if (name.endsWith(".zip"))
        {
        	this.setIcon(MyImage.zip);  
        }
        else if (name.endsWith(".mp3"))
        {
        	this.setIcon(MyImage.mp3);  
        }
        else if (name.endsWith(".mp4") || name.endsWith(".avi") || name.endsWith(".flv"))
        {
        	this.setIcon(MyImage.Video);  
        }
        else if (name.endsWith(".ppt") || name.endsWith(".pptx"))
        {
        	this.setIcon(MyImage.ppt);  
        }
        else if (name.endsWith(".html"))
        {
        	this.setIcon(MyImage.ie);  
        }
        else if (name.endsWith(".xls"))
        {
        	this.setIcon(MyImage.excel);  
        }
        else if (name.endsWith(".exe"))
        {
        	this.setIcon(MyImage.exe);  
        }
        else if (name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".bmp"))
        {
        	this.setIcon(MyImage.pic);  
        }
        else if (name.endsWith(".dwg") || name.endsWith(".dxf"))
        {
        	this.setIcon(MyImage.dwg);  
        }
        else if (name.endsWith(".pdf"))
        {
        	this.setIcon(MyImage.pdf);  
        }
        else this.setIcon(MyImage.folder);  
        //this.setIcon(getSmallIcon(new File("C:\\")));   
        return this;  
    }  
}

