package com.suct.client.print;




import javax.swing.*;
import java.io.File;

/**
 * Created by fzq96 on 2017/3/21.
 */
public class FileHelper
{
    public static final int DEFAULT__FILE = 0;
    public static final int SELECTED_FILE = 1;
    public static final String defaultFile = "print\\text.html";


    public static File getFile(final int fileTypes)
    {
        if (fileTypes == SELECTED_FILE)
        {
            JFileChooser fileChooser = new JFileChooser();
            int state = fileChooser.showOpenDialog(null);
            if (state == JFileChooser.APPROVE_OPTION)
            {
                return fileChooser.getSelectedFile();
            } else
            {
                return null;
            }
        } else
        {
            return new File(defaultFile);
        }
    }
}
