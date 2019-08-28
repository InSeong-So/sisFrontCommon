package core.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import core.CommonProperties;
import core.util.StringUtil;

public class DBConnector
{
    private static DBConnector instance = new DBConnector();
    
    private DBConnector()
    {
    }
    
    public static DBConnector getInstance()
    {
        return instance;
    }
    
    static Logger log = Logger.getRootLogger();
    
    HashMap databaseKeyMap = new HashMap();
    
    private CommonProperties prop = CommonProperties.getInstance();
    
    private Connection conn = null;
    
    private Statement stmt = null;
    
    public Connection getConnection(String databaseKey) throws SQLException
    {
        try
        {
            DataSource dataSource = getDataSource(databaseKey);
            return new DBConnectionWrapper(dataSource.getConnection());
        }
        catch (Exception e)
        {
            log.debug(e);
            throw new SQLException(e.getMessage());
        }
    }
    
    private DataSource getDataSource(String databaseKey) throws NamingException
    {
        DataSource datasource = (DataSource) this.databaseKeyMap.get(databaseKey);
        
        if (datasource == null)
        {
            Properties prop = System.getProperties();
            
            if (StringUtil.nvl(prop.getProperty("catalina.home")).length() > 0)
            {
                Context initialContext = new InitialContext();
                
                Context envContext = (Context) initialContext.lookup("java:comp/env");
                this.databaseKeyMap.put(databaseKey, envContext.lookup(databaseKey));
                log.debug("JDBC BindingName : " + databaseKey + ", " + this.databaseKeyMap.get(databaseKey));
            }
            
            datasource = (DataSource) this.databaseKeyMap.get(databaseKey);
        }
        
        return datasource;
    }
}
