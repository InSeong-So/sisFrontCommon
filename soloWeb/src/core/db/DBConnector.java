package core.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import core.Util;

public class DBConnector
{
    static Logger log = Logger.getRootLogger();
    
    private static DBConnector instance = new DBConnector();
    
    HashMap dbKeyMap = new HashMap();
    
    public static DBConnector getInstance()
    {
        return instance;
    }
    
    public Connection getConnection(String dbKeyName) throws SQLException
    {
        try
        {
            DataSource dataSource = getDataSource(dbKeyName);
            return new DBConnectionWrapper(dataSource.getConnection());
        }
        catch (Exception e)
        {
            log.debug(e.getMessage());
            throw new SQLException(e.getMessage());
        }
        
    }
    
    private DataSource getDataSource(String dbKeyName) throws NamingException
    {
        DataSource dataSource = (DataSource) dbKeyMap.get(dbKeyName);
        if (dataSource == null)
        {
            Properties prop = System.getProperties();
            
            if (Util.nvl(prop.getProperty("jboss.server.base.dir")).length() > 0)
            {
                Context initialContext = new InitialContext();
                
                Context envContext = (Context) initialContext.lookup("java:jboss");
                dbKeyMap.put(dbKeyName, envContext.lookup(dbKeyName));
                log.debug("jdbc bindingName: " + dbKeyName);
            }
            else if (Util.nvl(prop.getProperty("catalina.home")).length() > 0)
            {
                Context initialContext = new InitialContext();
                
                Context envContext = (Context) initialContext.lookup("java:comp/env");
                dbKeyMap.put(dbKeyName, envContext.lookup(dbKeyName));
                log.debug("jdbc bindingName: " + dbKeyName);
            }
            else if (Util.nvl(prop.getProperty("jeus.home")).length() > 0)
            {
                InitialContext initialContext = new InitialContext();
                dbKeyMap.put(dbKeyName, (DataSource) initialContext.lookup(dbKeyName));
                log.debug("jdbc bindingName: " + dbKeyName);
            }
            else
            {
                InitialContext initialContext = new InitialContext();
                dbKeyMap.put(dbKeyName, (DataSource) initialContext.lookup(dbKeyName));
                log.debug("jdbc bindingName: " + dbKeyName);
            }
            
            dataSource = (DataSource) dbKeyMap.get(dbKeyName);
        }
        
        return dataSource;
    }
}
