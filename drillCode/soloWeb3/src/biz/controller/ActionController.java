package biz.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.CommonProperties;
import core.sisServlet;

public class ActionController extends sisServlet
{
    private static final long serialVersionUID = 1L;
    
    private List<String> urls;
    
    private List<MainAction> ctrls;
    
    public ActionController()
    {
        urls = new ArrayList<String>();
        ctrls = new ArrayList<MainAction>();
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
        
        String afterUri = prop.getProperty(uri);
        
        log.debug("mapping servlet uri : "+afterUri);
        
        try
        {
            Class commandClass = Class.forName(afterUri);
            Object commandInstance = commandClass.newInstance();
            
            ma = (MainAction) commandInstance;
            afterUri = ma.sisAction(request, response);
        }
        catch (Throwable e)
        {
            log.debug("AcitonController Exception : " + e);
            throw new ServletException();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(afterUri);
        dispatcher.forward(request, response);
    }
}
