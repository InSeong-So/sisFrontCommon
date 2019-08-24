package core.util;

import java.io.IOException;

import javax.servlet.ServletException;
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

    private void sisAction(HttpServletRequest request, HttpServletResponse response)
    {
        // TODO Auto-generated method stub
        
    }
    
    
}
