package biz.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import core.CommonProperties;
import core.sisServlet;

public class ActionController extends sisServlet
{
    private static final long serialVersionUID = 1L;
    
    private Logger log = Logger.getRootLogger();
    
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
        
        String viewPath = null;
        
        MainAction ma = null;
        
        try
        {
            String uriPath = request.getRequestURI();
            if (uriPath.indexOf(request.getContextPath()) == 0)
                uriPath = uriPath.substring(request.getContextPath().length());
            
            ma = (MainAction) uriMap.get(uriPath);
            if (ma == null)
            {
                log.debug("URI PATH NOT FOUND : " + uriPath);
                return;
            }
            
            viewPath = ma.sisAction(request, response);
            
            if (viewPath == null)
            {
                log.debug("VIEW PATH NOT FOUND : " + viewPath);
                return;
            }
        }
        catch (Throwable e)
        {
            throw new ServletException(e);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        
        dispatcher.forward(request, response);
    }
}
