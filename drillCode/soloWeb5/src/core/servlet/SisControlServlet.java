package core.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import core.util.CommonProperties;

public class SisControlServlet extends SisAheadServlet
{
    private static final long serialVersionUID = 1L;
    
    public SisControlServlet()
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
    
    protected void sisAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CommonProperties prop = CommonProperties.getInstance();
        
        String uri = request.getRequestURI();
        
        MainAction ma = null;
        
        if (uri.indexOf(request.getContextPath()) == 0)
            uri = uri.substring(request.getContextPath().length());
        
        log.debug("uri : " + uri);
        
        uri = getClassPath(uri);
        
        log.debug("uri : " + uri);
        
        try
        {
            Class commandClass = Class.forName(uri);
            Object commandInstance = commandClass.newInstance();
            
            ma = (MainAction) commandInstance;
            uri = ma.sisAction(request, response);
        }
        catch (Throwable e)
        {
            log.debug("SisControlServlet Exception : " + e + ", [ request.getRequestURI() : " + request.getRequestURI() + " ]");
            throw new ServletException();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
        dispatcher.forward(request, response);
    }
    
}
