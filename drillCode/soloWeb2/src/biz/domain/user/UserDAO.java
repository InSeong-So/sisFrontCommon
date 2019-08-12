package biz.domain.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import core.db.DBConnector;

public class UserDAO extends DBConnector
{
    private Logger log = Logger.getRootLogger();
    
    public static UserDAO instance = new UserDAO();
    
    private final int LOGIN_SUCCESS = 1;
    
    private final int LOGIN_FAILED = 0;
    
    public static UserDAO getInstance()
    {
        return instance;
    }
    
    public int login() throws SQLException
    {
        String userId = "";
        String userPassword = "";
        
        String query = "SELECT * FROM SY0000 WHERE USER_ID = '" + userId + "' AND USER_PASSWORD = '" + userPassword + "'";
        
        log.debug("Excute Query : " + query);
        
        ResultSet rs = openConnection().executeQuery(query);
        
        if (rs.next())
            return LOGIN_SUCCESS;
        
        closeConnection();
        
        return LOGIN_FAILED;
    }
}
