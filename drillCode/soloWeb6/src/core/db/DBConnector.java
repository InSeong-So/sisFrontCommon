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

import core.util.StringUtil;

public class DBConnector
{
    public static Logger log = Logger.getRootLogger();
    
    HashMap databaseKeyMap = new HashMap();
    
    private static DBConnector instance = new DBConnector();
    
    public static DBConnector getInstance()
    {
        return instance;
    }
    
    public Connection getConnection(String databaseKey) throws SQLException
    {
        try
        {
            DataSource dataSource = getDataSources(databaseKey);
            return new DBConnectionWrapper(dataSource.getConnection());
        }
        catch (Exception e)
        {
            log.debug(e);
            throw new SQLException(e.getMessage());
        }
    }
    
    private DataSource getDataSources(String databaseKey) throws NamingException
    {
        DataSource datasource = (DataSource) this.databaseKeyMap.get(databaseKey);
        
        if (datasource == null)
        {
            Properties prop = System.getProperties();
            
            if (StringUtil.nvl(prop.getProperty("catalina.home")).length() > 0)
            {
                Context initialContext = new InitialContext();
                
                Context envContext = (Context) initialContext.lookup("java:com/env");
                this.databaseKeyMap.put(databaseKey, envContext.lookup(databaseKey));
                log.debug("JDBC BindingName : " + databaseKey);
            }
            
            datasource = (DataSource) this.databaseKeyMap.get(databaseKey);
        }
        
        return datasource;
    }
}
