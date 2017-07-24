package com.suct.client.print;

import java.io.*;
import java.util.ArrayList;


/**
 * Created by fzq96 on 2017/3/28.
 */
public class Sheet
{

    private int column = 4;
    private FileWriter fw;

    private final String baseString="<!DOCTYPE html>\n" +
            "<html lang=\"en\">" +
            "<head>" +
            "<meta charset=\"GBK\">" +
            "<title>Document</title>" +
            "</head>" +
            "<body>"+
            "<table border=\"1\">";
    private final StringBuilder contentBuilder=new StringBuilder(baseString);


    private final String endString="</table></body></html>";




    public Sheet(ArrayList<String> header) throws Exception
    {
        setHeader(header);
    }


    public boolean setHeader(ArrayList<String> header)
    {
        this.column = header.size();
        contentBuilder.append("<tr>");
        for(int i=0;i<column;i++){
            String tempContent="<td>"+header.get(i)+"</td>";
            contentBuilder.append(tempContent);
        }
        contentBuilder.append("</tr>");

        return true;
    }


    public boolean addRowContent(ArrayList<String> cellContents) throws Exception
    {
        if (cellContents.size()==column)
        {
            contentBuilder.append("<tr>");
            for (int i = 0; i < column; i++)
            {
                String tempCellContents = "<td>" + cellContents.get(i) + "</td>";
                contentBuilder.append(tempCellContents);
            }
            contentBuilder.append("</tr>");
            return true;
        }
        return false;
    }


    public boolean recreateLogFile()
    {
        if (FileHelper.getFile(FileHelper.DEFAULT__FILE).exists())
        {
            FileHelper.getFile(FileHelper.DEFAULT__FILE).delete();
            return true;
        }
        return true;
    }


    public boolean saveToFile()
    {
        try
        {
            recreateLogFile();
            fw = new FileWriter(FileHelper.getFile(FileHelper.DEFAULT__FILE), true);
            contentBuilder.append(endString);
            //System.out.println(wholeContent.toString());
            fw.write(contentBuilder.toString());
            fw.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
