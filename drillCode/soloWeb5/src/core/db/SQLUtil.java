package core.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import core.util.CommonProperties;
import core.util.StringUtil;

public class SQLUtil
{
    static Logger log = Logger.getRootLogger();
    
    static CommonProperties commProp = CommonProperties.getInstance();
    
    public static Connection getConnection(String jndi_name) throws SQLException
    {
        CommonProperties commProp = CommonProperties.getInstance();
        
        Connection conn = null;
        
        try
        {
            String default_jndiname = StringUtil.nvl(commProp.getProperty("DEFAULT_JNDINAME"));
            jndi_name = StringUtil.nvl(jndi_name, default_jndiname);
            
            conn = DBConnector.getInstance().getConnection(jndi_name);
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        setAutoCommit(conn, false);
        return conn;
    }
    
    public static Connection getConnection(HttpServletRequest request, String jndi_name) throws SQLException
    {
        Connection conn = null;
        
        try
        {
            String default_jndiname = StringUtil.nvl(commProp.getProperty("DEFAULT_JNDINAME"));
            jndi_name = StringUtil.nvl(jndi_name, default_jndiname);
            
            conn = DBConnector.getInstance().getConnection(jndi_name);
        }
        catch (SQLException e)
        {
            
            throw new SQLException(e);
        }
        
        setAutoCommit(conn, false);
        return conn;
    }
    
    public static void setAutoCommit(Connection conn, boolean b) throws SQLException
    {
        if (conn != null)
        {
            conn.setAutoCommit(b);
        }
    }
    
    public static void commit(Connection conn) throws SQLException
    {
        if (conn != null)
            conn.commit();
    }
    
    public static void rollback(Connection conn) throws SQLException
    {
        if (conn != null)
        {
            
            try
            {
                conn.rollback();
            }
            catch (Exception exception)
            {
            }
        }
    }
    
    public static void releaseConnection(Connection conn) throws SQLException
    {
        if (conn != null)
        {
            
            try
            {
                conn.setAutoCommit(true);
            }
            
            catch (Exception exception)
            
            {
                try
                {
                    
                    conn.close();
                }
                catch (Exception exception1)
                {
                }
            }
            finally
            {
                try
                {
                    conn.close();
                }
                catch (Exception exception)
                {
                }
            }
            
        }
    }
    
    public static SisResultSet getResultSetWithClose(ClearStatement cstmt) throws SQLException
    {
        try
        {
            SisResultSet srs = SisResultSet.make(cstmt.executeQuery());
            return srs;
        }
        finally
        {
            
            cstmt.close();
        }
    }
}