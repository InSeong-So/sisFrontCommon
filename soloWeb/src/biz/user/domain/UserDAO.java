package biz.user.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class UserDAO
{
    private Logger log = Logger.getRootLogger();
    
    private Connection conn;
    
    private PreparedStatement pstmt;
    
    private ResultSet resultSet;
    
    public static final Pattern default_pattern = Pattern.compile("\\:((\\w|[ㄱ-힝])+)");
    
    public UserDAO() throws SQLException, ClassNotFoundException
    {
        String userName = "GRSEHR";
        String userPassword = "GRS$EHR!11";
        String url = "jdbc:oracle:thin:@10.66.1.104:1522:GRSEHR";
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(url, userName, userPassword);
    }
    
    public int login(String userId, String userPassword) throws SQLException
    {
        String query = "SELECT * FROM SY4100 WHERE C_CD = '10' AND USER_ID = ?";
        
        int returnInt = 0;
        
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, userId);
        resultSet = pstmt.executeQuery();
        
        if (resultSet.next())
        {
            log.debug("로그인");
            returnInt = 1;
        }
        else
        {
            log.debug("로그인 실패");
            returnInt = -1;
        }
        log.debug("로그인 메소드 종료");
        
        return returnInt;
    }
}
