package core.util;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import core.db.XMLParsingQuery;

public class SisRequiredClass
{
    protected Connection conn;
    
    protected Logger log = Logger.getRootLogger();
    
    protected HttpServletRequest request;
    
    protected HttpServletResponse response;
    
    protected XMLParsingQuery xmlParsingQuery = XMLParsingQuery.getInstance();
    
    protected CommonProperties prop = CommonProperties.getInstance();
    
    public SisRequiredClass()
    {
    }
    
    public void setConnection(Connection conn)
    {
        this.conn = conn;
    }
    
    public SisRequiredClass(Connection conn, HttpServletRequest request, HttpServletResponse response)
    {
        setConnection(conn);
        this.request = request;
        this.response = response;
    }
}
