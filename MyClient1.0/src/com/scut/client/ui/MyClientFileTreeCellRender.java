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
        String path = getTreePath(node.getPath());
        //判断是哪个文本的节点设置对应的值（这里如果节点传入的是一个实体,则可以根据实体里面的一个类型属性来显示对应的图标）  
        this.setIcon(getSmallIcon(new File(path)));  
   
        return this;  
    }  
}

