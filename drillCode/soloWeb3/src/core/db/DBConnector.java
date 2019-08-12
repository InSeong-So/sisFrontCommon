package core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import core.CommonProperties;

public class DBConnector
{
    static Logger log = Logger.getRootLogger();
    
    private CommonProperties prop = CommonProperties.getInstance();
    
    private final String DB_CASS = prop.getProperty("DB_CLASS");
    
    private final String DB_URL = prop.getProperty("DB_URL");
    
    private final String DB_USER = prop.getProperty("DB_USER");
    
    private final String DB_USERPWD = prop.getProperty("DB_USERPWD");
    
    private Connection conn = null;
    
    private Statement stmt = null;
    
    public Statement openConnection() throws SQLException
    {
        conn = null;
        
        if (conn != null)
        {
            throw new SQLException("Connection has Already Used!");
        }
        
        try
        {
            Class.forName(DB_CASS);
            
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_USERPWD);
            log.debug("DBConnection Info : Oracle Database Connection Success!");
            
            stmt = conn.createStatement();
            
        }
        catch (SQLException e)
        {
            log.debug("DBConnection Info : Oracle Database Connection Normally Problem.");
            log.debug("SQLException : " + e.getMessage());
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            log.debug("DBConnection Info : Oracle Database Connection Class Not Founded.");
            log.debug("ClassNotFoundException : " + e.getMessage());
            e.printStackTrace();
        }
        
        return stmt;
    }
    
    public void closeConnection()
    {
        try
        {
            if(!conn.isClosed())
                conn.close();
        }
        catch (SQLException e)
        {
            log.debug("DBConnection Info : Oracle Database Connection Normally Problem.");
            log.debug("SQLException : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
