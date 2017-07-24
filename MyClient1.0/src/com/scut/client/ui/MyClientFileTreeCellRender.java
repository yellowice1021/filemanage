package com.scut.client.ui;

import java.awt.Component;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

public class MyClientFileTreeCellRender extends DefaultTreeCellRenderer{

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
         for (int i = 0; i < treenode.length-1; i++)
         {
         	 tmp += (treenode[i].toString() + "\\");
         }
         tmp += treenode[ treenode.length-1 ].toString();
		 return tmp;
	}
	
	
	/** 
     * ��д����DefaultTreeCellRenderer�ķ��� 
     */  
    @Override  
    public Component getTreeCellRendererComponent(JTree tree, Object value,  
            boolean sel, boolean expanded, boolean leaf, int row,  
            boolean hasFocus)  
    {  
  
        //ִ�и���ԭ�Ͳ���  
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,  
                row, hasFocus);  
  
        setText(value.toString());  
        //�õ�ÿ���ڵ��TreeNode  
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;  
        String path = getTreePath(node.getPath());
        //�ж����ĸ��ı��Ľڵ����ö�Ӧ��ֵ����������ڵ㴫�����һ��ʵ��,����Ը���ʵ�������һ��������������ʾ��Ӧ��ͼ�꣩  
        this.setIcon(getSmallIcon(new File(path)));  
   
        return this;  
    }  
}

