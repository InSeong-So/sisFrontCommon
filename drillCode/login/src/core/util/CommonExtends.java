package core.util;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import core.db.XMLParsingQuery;

public class CommonExtends
{
    protected Connection conn;
    
    protected HttpServletRequest request;
    
    protected HttpServletResponse response;
    
    protected Logger log = Logger.getRootLogger();
    
    protected XMLParsingQuery xmlQuery = XMLParsingQuery.getInstance();
    
    public CommonExtends()
    {
        // TODO Auto-generated constructor stub
    }
    
    
}
