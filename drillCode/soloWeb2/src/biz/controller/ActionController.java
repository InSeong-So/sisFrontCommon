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
        
        String view = null;
        
        MainAction com = null;
        try
        {
            String command = request.getRequestURI();
            if (command.indexOf(request.getContextPath()) == 0)
            {
                command = command.substring(request.getContextPath().length());
            }
            com = (MainAction) prop.defaultMap.get(command);
            if (com == null)
            {
                log.debug("not found command : " + command);
                return;
            }
            view = com.sisAction(request, response);
            if (view == null)
            {
                log.debug("not found view : " + view);
                return;
            }
        }
        catch (Throwable e)
        {
            throw new ServletException(e);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        
        dispatcher.forward(request, response);
    }
}
