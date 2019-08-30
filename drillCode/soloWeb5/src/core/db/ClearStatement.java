package core.db;

import java.io.Reader;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import core.util.CommonProperties;
import core.util.StringUtil;

public class ClearStatement
{
    static Logger log = Logger.getRootLogger();
    
    protected Connection conn;
    
    protected CallableStatement stmt;
    
    protected String clearQuery;
    
    protected String stmtQuery;
    
    protected ArrayList parameterList;
    
    protected HashMap parameterMap;
    
    public static final Pattern defaultPattern = Pattern.compile("\\:((\\w|[ㄱ-힝])+)");
    
    Pattern pattern = null;
    
    protected HttpServletRequest request;
    
    public ClearStatement(Connection conn, String clearQuery) throws SQLException
    {
        this(conn, clearQuery, "", null);
    }
    
    public ClearStatement(Connection conn, String clearQuery, HttpServletRequest request) throws SQLException
    {
        this(conn, clearQuery, null, request);
    }
    
    public ClearStatement(Connection conn, String clearQuery, String addPattern) throws SQLException
    {
        if (clearQuery == null)
            throw new RuntimeException("[ ClearStatement ] Query is Null!");
        
        this.conn = conn;
        this.clearQuery = clearQuery;
        this.parameterList = new ArrayList();
        this.parameterMap = new HashMap();
        if (addPattern == null)
            addPattern = "";
        this.pattern = (addPattern.length() == 0) ? defaultPattern : Pattern.compile(addPattern);
        this.stmtQuery = makePStmtQuery(this.clearQuery);
        setRealStatement(stmtQuery);
        this.request = null;
        
    }
    
    public ClearStatement(Connection conn, String clearQuery, String addPattern, HttpServletRequest request) throws SQLException
    {
        if (clearQuery == null)
            throw new RuntimeException("[ ClearStatement ] Query is Null!");
        
        this.conn = conn;
        this.clearQuery = clearQuery;
        this.parameterList = new ArrayList();
        this.parameterMap = new HashMap();
        if (addPattern == null)
            addPattern = "";
        this.pattern = (addPattern.length() == 0) ? defaultPattern : Pattern.compile(addPattern);
        this.stmtQuery = makePStmtQuery(this.clearQuery);
        setRealStatement(stmtQuery);
        this.request = request;
        
    }
    
    protected String makePStmtQuery(String clearQuery)
    {
        Matcher matcher = this.pattern.matcher(clearQuery);
        StringBuffer sb = new StringBuffer();
        while (matcher.find())
        {
            matcher.appendReplacement(sb, "?");
            this.parameterList.add(matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString().trim();
    }
    
    private void setRealStatement(String stmtQuery) throws SQLException
    {
        try
        {
            this.stmt = this.conn.prepareCall(stmtQuery);
        }
        catch (SQLException e)
        {
            log.debug(stmtQuery);
            throw new SQLException(e.getMessage());
        }
    }
    
    public void setParameter(String param, String value)
    {
        param = param.toUpperCase();
        
        if (!this.parameterList.contains(param))
            throw new RuntimeException("Parameter[ " + param + " ] No Exists.");
        
        if (this.request != null)
        {
            //            CommonProperties prop = CommonProperties.getInstance();
            this.parameterMap.put(param, new String[] { "STRING", value });
        }
        else
        {
            this.parameterMap.put(param, new String[] { "STRING", value });
        }
    }
    
    protected void setParameters() throws SQLException
    {
        int sz = this.parameterList.size();
        for (int i = 0, col = 1; i < sz; i++, col++)
        {
            String param = this.parameterList.get(i).toString();
            
            if (!this.parameterMap.containsKey(param))
                throw new RuntimeException("Paramter [ " + param + " ] No Set.");
            
            String[] typeValuePair = (String[]) this.parameterMap.get(param);
            
            String stringValue = typeValuePair[1];
            if (stringValue != null && stringValue.length() > 1000)
            {
                Reader reader = new StringReader(stringValue.toString());
                this.stmt.setCharacterStream(col, reader, stringValue.length());
            }
            else
            {
                this.stmt.setString(col, stringValue);
            }
        }
    }
    
    public int executeUpdate() throws SQLException
    {
        CommonProperties commProp = CommonProperties.getInstance();
        String alterSessionYn = StringUtil.nvl(commProp.getProperty("ALTER_SESSION_USE_YN"), "Y");
        setParameters();
        
        if (this.request == null)
        {
            if ("Y".equals(alterSessionYn))
            {
                
                StringBuffer sb = new StringBuffer();
                sb.append("ALTER SESSION SET TIME_ZONE = ");
                sb.append("'Asia/Seoul'");
                String query = sb.toString();
                
                CallableStatement cs = null;
                try
                {
                    cs = this.conn.prepareCall(query);
                    cs.execute();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    cs.close();
                }
                
            }
        }
        else
        {
            
            if (this.clearQuery.toUpperCase().startsWith("INSERT") || this.clearQuery.toUpperCase().startsWith("UPDATE") || this.clearQuery.toUpperCase().startsWith("DELETE") || this.clearQuery.toUpperCase().trim().startsWith("{CALL"))
            {
                
                if ("Y".equals(alterSessionYn))
                {
                    
                    StringBuffer sb = new StringBuffer();
                    sb.append("ALTER SESSION SET TIME_ZONE = ");
                    sb.append("''");
                    String query = sb.toString();
                    
                    try
                    {
                        ClearStatement vstmt = new ClearStatement(this.conn, query, this.request);
                        
                        log.debug(vstmt.getQueryString());
                        
                        vstmt.executeUpdate();
                    }
                    catch (Exception exception)
                    {
                    }
                }
            }
        }
        
        int executeCount = this.stmt.executeUpdate();
        
        //        if (this.clearQuery.toUpperCase().trim().startsWith("{CALL"))
        //        {
        //            insertAccessLog();
        //        }
        
        return executeCount;
    }
    
    public ResultSet executeQuery() throws SQLException
    {
        CommonProperties commProp = CommonProperties.getInstance();
        String alterSessionYn = StringUtil.nvl(commProp.getProperty("ALTER_SESSION_USE_YN"), "Y");
        setParameters();
        
        if (this.request == null)
        {
            if ("Y".equals(alterSessionYn))
            {
                
                StringBuffer sb = new StringBuffer();
                sb.append("ALTER SESSION SET TIME_ZONE = ");
                sb.append("'Asia/Seoul'");
                String query = sb.toString();
                
                CallableStatement cs = null;
                try
                {
                    cs = this.conn.prepareCall(query);
                    cs.execute();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    cs.close();
                }
                
            }
        }
        
        if ("Y".equals(alterSessionYn))
        {
            
            StringBuffer sb = new StringBuffer();
            sb.append("ALTER SESSION SET TIME_ZONE = ");
            sb.append("''");
            String query = sb.toString();
            
            try
            {
                ClearStatement vstmt = new ClearStatement(this.conn, query, this.request);
                
                log.debug(vstmt.getQueryString());
                
                vstmt.executeUpdate();
            }
            catch (Exception exception)
            {
            }
        }
        return this.stmt.executeQuery();
    }
    
    public String getQueryString()
    {
        int prevEnd = 0;
        Matcher matcher = this.pattern.matcher(this.clearQuery);
        StringBuffer sb = new StringBuffer();
        while (matcher.find())
        {
            String param = matcher.group(1).toUpperCase();
            String[] typeValuePair = (String[]) this.parameterMap.get(param);
            sb.append(this.clearQuery.substring(prevEnd, matcher.start()));
            
            sb.append((typeValuePair == null || "CRYPT_KEY".equals(param) || "OUTPUT".equals(typeValuePair[0])) ? (":" + param) : ((typeValuePair[1] == null) ? "null" : ("'" + typeValuePair[1] + "'")));
            prevEnd = matcher.end();
        }
        
        sb.append(this.clearQuery.substring(prevEnd));
        return sb.toString();
    }
    
    public void close() throws SQLException
    {
        if (this.stmt != null)
            this.stmt.close();
    }
}
