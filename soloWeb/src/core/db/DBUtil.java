package core.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import core.CommonProperties;
import core.Util;

public class DBUtil
{
    static CommonProperties prop = CommonProperties.getInstance();
    
    static Logger log = Logger.getRootLogger();
    
    public static Connection getConnection(String jndiName) throws SQLException
    {
        Connection conn = null;
        
        String default_jndiName = Util.nvl(prop.getProperty("DEFAULT_JNDINAME"));
        
        
        return conn;
    }
}
