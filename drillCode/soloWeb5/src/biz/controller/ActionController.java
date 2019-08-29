package biz.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.CommonProperties;
import core.SisServlet;

public class ActionController extends SisServlet
{
    private static final long serialVersionUID = 1L;
    
    private static List<String> urls;
    
    private static List<MainAction> ctrls;
    
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
        
        log.debug("uri : " + uri);
        
        if (!uri.equals(prop.getProperty("/list.do")))
            uri = prop.getProperty("/list.do");
        
        log.debug("uri : " + uri);
        
        try
        {
            Class commandClass = Class.forName(uri);
//            Method commandMethod = Method.
            Object commandInstance = commandClass.newInstance();
            
            ma = (MainAction) commandInstance;
            uri = ma.sisAction(request, response);
        }
        catch (Throwable e)
        {
            log.debug("AcitonController Exception : " + e + ", [ request.getRequestURI() : " + request.getRequestURI() + " ]");
            throw new ServletException();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
        dispatcher.forward(request, response);
    }
    
}
