package core.servlet;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import core.db.DBConnectionWrapper;
import core.db.SQLUtil;
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
        MainAction ma = null;
        Connection conn = null;
        Object commandInstance;
        String uri = request.getRequestURI();
        
        if (uri.indexOf(request.getContextPath()) == 0)
            uri = uri.substring(request.getContextPath().length());
        
        log.debug("uri : " + uri);
        
        uri = getClassPath(uri);
        
        try
        {
            conn = SQLUtil.getConnection(request, request.getParameter("CONNECTION_KEY"));
            
            commandInstance = (DBConnectionWrapper) conn;
            
            SQLUtil.setAutoCommit(conn, false);
        }
        catch (SQLException e)
        {
            log.debug("Exception : DB Connection has Fail! [ " + e + " ]");
        }
        
        try
        {
            SQLUtil.commit(conn);
            
            Class commandClass = Class.forName(uri);
            Class[] parameterTypes = { Connection.class, HttpServletRequest.class, HttpServletResponse.class };
            Constructor constructor = commandClass.getConstructor(parameterTypes);
            commandInstance = constructor.newInstance(new Object[] { conn, request, response });
            
            ma = (MainAction) commandInstance;
            uri = ma.sisAction(request, response);
        }
        catch (Throwable e)
        {
            log.debug("SisControlServlet Exception : " + e + ", [ request.getRequestURI() : " + request.getRequestURI() + " ]");
            try
            {
                SQLUtil.rollback(conn);
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        finally
        {
            try
            {
                SQLUtil.releaseConnection(conn);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            
            request.getRequestDispatcher(uri).forward(request, response);
        }
    }
    
}
