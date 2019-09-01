package core.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import core.util.CommonProperties;
import core.util.StringUtil;

public class IUDSQL
{
    protected static int MAX_CNT = 50;
    
    protected int count = 0;
    
    protected static Logger log = Logger.getRootLogger();
    
    protected Connection conn;
    
    protected DBConnectionWrapper conn_wrapper;
    
    protected String table;
    
    protected ArrayList fieldList;
    
    protected ArrayList keyList;
    
    protected HashMap queryMap;
    
    protected HashMap etcMap;
    
    protected boolean debug;
    
    protected boolean autoFieldAdd;
    
    protected HttpServletRequest request;
    
    protected HashMap insColumnYn;
    
    protected HashMap insGColumnYn;
    
    protected String queryString;
    
    protected String psnIdenStdCol = null;
    
    protected String accessTrgVal = null;
    
    protected boolean psnInfoAccessLogUse = true;
    
    public IUDSQL(Connection conn)
    {
        this.conn = conn;
        this.fieldList = new ArrayList();
        this.keyList = new ArrayList();
        this.etcMap = new HashMap();
        this.queryMap = new HashMap();
        this.insColumnYn = new HashMap();
        this.insGColumnYn = new HashMap();
        this.debug = true;
        this.autoFieldAdd = true;
        this.request = null;
        if (conn instanceof DBConnectionWrapper)
        {
            this.conn_wrapper = (DBConnectionWrapper) conn;
        }
    }
    
    public IUDSQL(Connection conn, HttpServletRequest request)
    {
        this.conn = conn;
        this.fieldList = new ArrayList();
        this.keyList = new ArrayList();
        this.etcMap = new HashMap();
        this.queryMap = new HashMap();
        this.insColumnYn = new HashMap();
        this.insGColumnYn = new HashMap();
        this.debug = true;
        this.autoFieldAdd = true;
        this.request = request;
        if (conn instanceof DBConnectionWrapper)
        {
            this.conn_wrapper = (DBConnectionWrapper) conn;
        }
    }
    
    public void setDebug(boolean debug)
    {
        this.debug = debug;
    }
    
    public void setTable(String table) throws SQLException
    {
        CommonProperties prop = CommonProperties.getInstance();
        String DEFAULT_SCHEMA = StringUtil.nvl(prop.getProperty("DEFAULT_SCHEMA"), this.conn_wrapper.getMetaData().getUserName().toUpperCase());
        this.table = table;
        this.fieldList.clear();
        this.keyList.clear();
        this.etcMap.clear();
        this.psnIdenStdCol = null;
        if (this.insColumnYn.get(table) == null)
        {
            
            ResultSet rs = null;
            
            try
            {
                rs = this.conn_wrapper.getMetaData().getColumns(null, DEFAULT_SCHEMA, table, "%");
                String userColumnYN = "N", dateColumnYN = "N", globalDateColumnYN = "N";
                while (rs.next())
                {
                    
                    if (rs.getString("COLUMN_NAME").equals("INS_USER_ID"))
                    {
                        userColumnYN = "Y";
                    }
                    
                    if (rs.getString("COLUMN_NAME").equals("INS_YMDHMS"))
                    {
                        dateColumnYN = "Y";
                    }
                    
                    if (rs.getString("COLUMN_NAME").equals("INS_GYMDHMS"))
                    {
                        globalDateColumnYN = "Y";
                    }
                }
                
                if (userColumnYN.equals("Y") && dateColumnYN.equals("Y"))
                {
                    
                    this.insColumnYn.put(table, "Y");
                }
                else
                {
                    
                    this.insColumnYn.put(table, "N");
                }
                
                if (globalDateColumnYN.equals("Y"))
                {
                    this.insGColumnYn.put(table, "Y");
                }
                else
                {
                    this.insGColumnYn.put(table, "N");
                }
                
            }
            catch (SQLException e)
            {
                
                e.printStackTrace();
                rs.close();
            }
            finally
            {
                
                rs.close();
            }
        }
    }
    
    public void addField(String field, String value)
    {
        if (this.request != null)
        {
            
            CommonProperties commProp = CommonProperties.getInstance();
            
            this.fieldList.add(new String[] { "1", field, value });
            
        }
        else
        {
            
            this.fieldList.add(new String[] { "1", field, value });
        }
    }
    
    public void addFieldRaw(String field, String value)
    {
        this.fieldList.add(new String[] { "2", field, value });
    }
    
    public void addFieldXML(String field, String value)
    {
        this.fieldList.add(new String[] { "4", field, value });
    }
    
    public void addKey(String field, String value)
    {
        if (this.request != null)
        {
            
            CommonProperties commProp = CommonProperties.getInstance();
            
            this.keyList.add(new String[] { "1", field, value });
            
        }
        else
        {
            
            this.keyList.add(new String[] { "1", field, value });
        }
    }
    
    public void addKeyRaw(String field, String value)
    {
        this.keyList.add(new String[] { "2", field, value });
    }
    
    public void addWhere(String where) throws SQLException
    {
        this.keyList.add(new String[] { "3", where });
    }
    
    public void addEtcParameter(String pName, String pValue)
    {
        if (this.request != null)
        {
            
            CommonProperties commProp = CommonProperties.getInstance();
            
            this.etcMap.put(pName, pValue);
            
        }
        else
        {
            
            this.etcMap.put(pName, pValue);
        }
    }
    
    public int insert() throws SQLException
    {
        if (this.conn_wrapper != null)
        {
            
            String USER_ID = StringUtil.nvl(this.conn_wrapper.getSessionVariable("USER_ID"));
            if (USER_ID.length() > 0 && this.autoFieldAdd && this.insColumnYn.get(this.table).equals("Y"))
            {
                
                if (!isFieldExists("INS_USER_ID"))
                    addField("INS_USER_ID", USER_ID);
                if (!isFieldExists("INS_YMDHMS"))
                    addFieldRaw("INS_YMDHMS", "SYSTIMESTAMP");
                if (this.insGColumnYn.get(this.table).equals("Y"))
                {
                    if (!isFieldExists("INS_GYMDHMS"))
                        addFieldRaw("INS_GYMDHMS", "CURRENT_TIMESTAMP");
                }
            }
        }
        String query = makeInsertQuery();
        ClearStatement cstmt = getStatement(query);
        setParameters(cstmt, this.keyList, "K");
        setParameters(cstmt, this.fieldList, "F");
        setParameters(cstmt, this.etcMap);
        setQueryString(cstmt.getQueryString());
        if (this.debug)
            log.debug(getQueryString());
        int cnt = cstmt.executeUpdate();
        
        return cnt;
    }
    
    boolean isFieldExists(String fieldName)
    {
        int keyCount = this.keyList.size();
        for (int n = 0, sz = keyCount; n < sz; n++)
        {
            
            String[] fieldSet = (String[]) this.keyList.get(n);
            if (fieldSet[1].equals(fieldName))
                return true;
        }
        int fieldCount = this.fieldList.size();
        for (int n = 0, sz = fieldCount; n < sz; n++)
        {
            
            String[] fieldSet = (String[]) this.fieldList.get(n);
            if (fieldSet[1].equals(fieldName))
                return true;
        }
        return false;
    }
    
    public int update() throws SQLException
    {
        String query = makeUpdateQuery();
        ClearStatement cstmt = getStatement(query);
        
        setParameters(cstmt, this.fieldList, "F");
        setParameters(cstmt, this.keyList, "K");
        setParameters(cstmt, this.etcMap);
        setQueryString(cstmt.getQueryString());
        if (this.debug)
            log.debug(getQueryString());
        int cnt = cstmt.executeUpdate();
        
        return cnt;
    }
    
    public int delete() throws SQLException
    {
        String query = makeDeleteQuery();
        ClearStatement cstmt = getStatement(query);
        setParameters(cstmt, this.keyList, "K");
        setParameters(cstmt, this.etcMap);
        setQueryString(cstmt.getQueryString());
        if (this.debug)
            log.debug(getQueryString());
        int cnt = cstmt.executeUpdate();
        
        return cnt;
    }
    
    private void setParameters(ClearStatement cstmt, HashMap map)
    {
        Iterator it = map.keySet().iterator();
        while (it.hasNext())
        {
            String pName = (String) it.next();
            String pValue = (String) map.get(pName);
            cstmt.setParameter(pName, pValue);
        }
    }
    
    ClearStatement getStatement(String query) throws SQLException
    {
        if (this.debug)
            log.debug("count: " + this.count);
        if (this.count == MAX_CNT)
        {
            closeAllStatement();
        }
        ClearStatement cstmt = (ClearStatement) this.queryMap.get(query);
        if (cstmt != null)
            return cstmt;
        cstmt = new ClearStatement(this.conn, query, this.request);
        this.queryMap.put(query, cstmt);
        this.count++;
        return cstmt;
    }
    
    void closeAllStatement()
    {
        Iterator iter = this.queryMap.values().iterator();
        while (iter.hasNext())
        {
            
            ClearStatement cstmt = (ClearStatement) iter.next();
            
            try
            {
                cstmt.close();
            }
            catch (Exception e)
            {
                
                log.error(this, e);
            }
        }
        this.queryMap.clear();
        this.count = 0;
    }
    
    String makeInsertQuery()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT ").append("\n");
        sb.append("  INTO ").append(this.table).append("\n");
        sb.append("(").append("\n");
        
        int keyCount = this.keyList.size();
        int fieldCount = this.fieldList.size();
        int totCount = keyCount + fieldCount;
        int count = 0;
        for (int n = 0, sz = keyCount; n < sz; n++, count++)
        {
            
            String[] fieldSet = (String[]) this.keyList.get(n);
            sb.append("  ").append(fieldSet[1]).append((count < totCount - 1) ? "," : "").append("\n");
        }
        for (int n = 0, sz = fieldCount; n < sz; n++, count++)
        {
            
            String[] fieldSet = (String[]) this.fieldList.get(n);
            sb.append("  ").append(fieldSet[1]).append((count < totCount - 1) ? "," : "").append("\n");
        }
        sb.append(")").append("\n");
        sb.append("VALUES").append("\n");
        sb.append("(").append("\n");
        count = 0;
        for (int n = 0, sz = keyCount; n < sz; n++, count++)
        {
            
            String[] fieldSet = (String[]) this.keyList.get(n);
            sb.append("  ").append("1".equals(fieldSet[0]) ? (":K" + n) : fieldSet[2]).append((count < totCount - 1) ? "," : "").append("\n");
        }
        for (int n = 0, sz = fieldCount; n < sz; n++, count++)
        {
            
            String[] fieldSet = (String[]) this.fieldList.get(n);
            
            String sField = "";
            
            if ("1".equals(fieldSet[0]))
            {
                
                sField = ":F" + n;
            }
            else if ("4".equals(fieldSet[0]))
            {
                
                sField = "sys.XMLType.createXML(:F" + n + ")";
            }
            else
            {
                
                sField = fieldSet[2];
            }
            
            sb.append("  ").append(sField).append((count < totCount - 1) ? "," : "").append("\n");
        }
        sb.append(")").append("\n");
        return sb.toString();
    }
    
    String makeUpdateQuery()
    {
        if (this.keyList.size() == 0)
            throw new RuntimeException("key must be set.");
        
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(this.table).append(" SET\n");
        for (int n = 0, sz = this.fieldList.size(); n < sz; n++)
        {
            
            String[] fieldSet = (String[]) this.fieldList.get(n);
            
            String sField = "";
            
            if ("1".equals(fieldSet[0]))
            {
                
                sField = ":F" + n;
            }
            else if ("4".equals(fieldSet[0]))
            {
                
                sField = "sys.XMLType.createXML(:F" + n + ")";
            }
            else
            {
                
                sField = fieldSet[2];
            }
            
            sb.append("       ").append(fieldSet[1]).append(" = ").append(sField).append((n < sz - 1) ? "," : "").append("\n");
        }
        appendWhrereQuery(sb);
        return sb.toString();
    }
    
    String makeDeleteQuery()
    {
        if (this.keyList.size() == 0)
            throw new RuntimeException("key must be set.");
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE ").append("\n");
        sb.append("  FROM ").append(this.table).append("\n");
        appendWhrereQuery(sb);
        return sb.toString();
    }
    
    void appendWhrereQuery(StringBuffer sb)
    {
        sb.append(" WHERE 1=1").append("\n");
        for (int n = 0; n < this.keyList.size(); n++)
        {
            
            String[] fieldSet = (String[]) this.keyList.get(n);
            sb.append("   AND ");
            if ("1".equals(fieldSet[0]))
            {
                
                sb.append(fieldSet[1]).append(" = ").append(":K" + n);
            }
            else if ("2".equals(fieldSet[0]))
            {
                
                sb.append(fieldSet[1]).append(" = ").append(fieldSet[2]);
            }
            else if ("3".equals(fieldSet[0]))
            {
                
                sb.append(fieldSet[1]);
            }
            sb.append("\n");
        }
        
    }
    
    void setParameters(ClearStatement cstmt, ArrayList pList, String strPrefix)
    {
        for (int n = 0, nlen = pList.size(); n < nlen; n++)
        {
            
            String[] fieldSet = (String[]) pList.get(n);
            if ("1".equals(fieldSet[0]) || "4".equals(fieldSet[0]))
            {
                cstmt.setParameter(String.valueOf(strPrefix) + n, fieldSet[2]);
            }
        }
    }
    
    public void logRow(String logType) throws SQLException
    {
        StringBuffer sb = new StringBuffer();
        for (int n = 0; n < this.keyList.size(); n++)
        {
            sb.append(StringUtil.nvl((String) this.keyList.get(n), "")).append("\t");
        }
        for (int n = 0; n < this.fieldList.size(); n++)
        {
            sb.append(((String[]) this.fieldList.get(n))[2]).append("\t");
        }
        log.debug(sb.toString());
        
    }
    
    public String getQueryString()
    {
        return this.queryString;
    }
    
    protected void setQueryString(String queryString) throws SQLException
    {
        this.queryString = queryString;
    }
    
}
