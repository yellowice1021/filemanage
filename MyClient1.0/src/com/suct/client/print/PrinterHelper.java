package com.suct.client.print;


import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by fzq96 on 2017/3/21.
 */
public class PrinterHelper implements Printable
{

    private static PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
    private static DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
    private static PrintService[] printServices = PrintServiceLookup.lookupPrintServices(flavor, pras);
    private static PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();


    public void drawMyString(Graphics2D g, String str, int x, int y){
        int my_x = x;
        int my_y = y;
        FontMetrics fm = g.getFontMetrics();
        int len=str.length();
        for(int i=0;i<len;i++){
            String mystr=str.substring(i, i+1);
            g.drawString(mystr, my_x, my_y);
            my_x += fm.getStringBounds(mystr, g).getWidth();
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException
    {
        Graphics2D g2 = (Graphics2D) graphics;

        g2.setPaint(Color.black);
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        Font font = new Font("榛戜綋", Font.PLAIN, 50);
        g2.setFont(font);

        return Printable.PAGE_EXISTS;
    }

    public static void printToFile(int fileType) throws PrinterException
    {

    	Toolkit kits=Toolkit.getDefaultToolkit();

        Dimension screenSize=kits.getScreenSize();  //系统对象获取工具

        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
    	
        File file = FileHelper.getFile(fileType);
        PrinterJob job = PrinterJob.getPrinterJob();

        PrintService service = ServiceUI.printDialog(null, screenWidth/2, screenHeight/2,
                printServices, defaultService, flavor, pras);

        job.setPrintService(service);
        JEditorPane reportPane = new JEditorPane();
        reportPane.setContentType("txt/html");
        HTMLEditorKit kit=new HTMLEditorKit();
        reportPane.setEditorKit(kit);
        HTMLDocument doc= (HTMLDocument) reportPane.getDocument();

        try
        {
            if (file != null)
            {
                FileInputStream   reader   =   new FileInputStream(file);
                kit.read(reader,doc,0);

                //reportPane.setPage("file:\\" + file.getAbsolutePath());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (BadLocationException e)
        {
            e.printStackTrace();
        }

        job.setPrintable(reportPane.getPrintable(null, null));

        job.print();
    }
}
