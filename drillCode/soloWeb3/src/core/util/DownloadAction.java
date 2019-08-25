package core.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DownloadAction extends HttpServlet
{
    private Logger log = Logger.getRootLogger();
    
    public DownloadAction()
    {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        sisAction(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        sisAction(request, response);
    }
    
    private void sisAction(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String filename = "";
        String originFilename = "";
        File file = new File(filename);
        int fileLengh = 0;
        ServletOutputStream op = response.getOutputStream();
        ServletContext context = getServletConfig().getServletContext();
        String mimeType = context.getMimeType(filename);
        response.setContentType((mimeType != null) ? mimeType : "application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + originFilename + "\"");
        
        //
        // Stream to the requester.
        //
        byte[] bbuf = new byte[100000];
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        
        while ((in != null) && ((fileLengh = in.read(bbuf)) != -1))
        {
            op.write(bbuf, 0, fileLengh);
        }
        
        in.close();
        op.flush();
        op.close();
    }
    
}
