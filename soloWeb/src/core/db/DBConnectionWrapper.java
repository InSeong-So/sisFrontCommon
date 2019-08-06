package core.db;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class DBConnectionWrapper implements Connection
{
    Connection instance;
    
    protected ArrayList statements;
    
    protected HashMap<String, String> sessionVariableMap;
    
    protected HashMap sessionObjectMap;
    
    public void setReadOnly(boolean arg0) throws SQLException
    {
        instance.setReadOnly(arg0);
    }
    
    public boolean isReadOnly() throws SQLException
    {
        return instance.isReadOnly();
    }
    
    public void close() throws SQLException
    {
        int size = statements.size();
        for (int n = 0; n < size; n++)
        {
            try
            {
                ((Statement) statements.get(n)).close();
            }
            catch (Exception exception)
            {
            }
        }
        instance.close();
    }
    
    public boolean isClosed() throws SQLException
    {
        return instance.isClosed();
    }
    
    public Statement createStatement(int arg0, int arg1) throws SQLException
    {
        Statement stmt = instance.createStatement(arg0, arg1);
        statements.add(stmt);
        return stmt;
    }
    
    public Statement createStatement(int arg0, int arg1, int arg2) throws SQLException
    {
        Statement stmt = instance.createStatement(arg0, arg1, arg2);
        statements.add(stmt);
        return stmt;
    }
    
    public Statement createStatement() throws SQLException
    {
        Statement stmt = instance.createStatement();
        statements.add(stmt);
        return stmt;
    }
    
    public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException
    {
        Statement stmt = instance.prepareStatement(arg0, arg1);
        statements.add(stmt);
        return (PreparedStatement) stmt;
    }
    
    public DBConnectionWrapper(Connection instance)
    {
        statements = new ArrayList();
        sessionVariableMap = new HashMap();
        sessionObjectMap = new HashMap();
        instance = instance;
    }
    
    public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException
    {
        Statement stmt = instance.prepareStatement(arg0, arg1);
        statements.add(stmt);
        return (PreparedStatement) stmt;
    }
    
    public void saveStatement(Statement stmt)
    {
        statements.add(stmt);
    }
    
    public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException
    {
        Statement stmt = instance.prepareStatement(arg0, arg1);
        statements.add(stmt);
        return (PreparedStatement) stmt;
    }
    
    public PreparedStatement prepareStatement(String arg0) throws SQLException
    {
        Statement stmt = instance.prepareStatement(arg0);
        statements.add(stmt);
        return (PreparedStatement) stmt;
    }
    
    public PreparedStatement prepareStatement(String arg0, int arg1, int arg2) throws SQLException
    {
        Statement stmt = instance.prepareStatement(arg0, arg1, arg2);
        statements.add(stmt);
        return (PreparedStatement) stmt;
    }
    
    public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException
    {
        Statement stmt = instance.prepareStatement(arg0, arg1, arg2, arg3);
        statements.add(stmt);
        return (PreparedStatement) stmt;
    }
    
    public CallableStatement prepareCall(String arg0) throws SQLException
    {
        Statement stmt = instance.prepareCall(arg0);
        statements.add(stmt);
        return (CallableStatement) stmt;
    }
    
    public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException
    {
        Statement stmt = instance.prepareCall(arg0, arg1, arg2, arg3);
        statements.add(stmt);
        return (CallableStatement) stmt;
    }
    
    public CallableStatement prepareCall(String arg0, int arg1, int arg2) throws SQLException
    {
        Statement stmt = instance.prepareCall(arg0, arg1, arg2);
        statements.add(stmt);
        return (CallableStatement) stmt;
    }
    
    public String nativeSQL(String arg0) throws SQLException
    {
        return instance.nativeSQL(arg0);
    }
    
    public void setAutoCommit(boolean arg0) throws SQLException
    {
        instance.setAutoCommit(arg0);
    }
    
    public boolean getAutoCommit() throws SQLException
    {
        return instance.getAutoCommit();
    }
    
    public void commit() throws SQLException
    {
        instance.commit();
    }
    
    public void rollback() throws SQLException
    {
        instance.rollback();
    }
    
    public void rollback(Savepoint arg0) throws SQLException
    {
        instance.rollback(arg0);
    }
    
    public String getSessionVariable(String var_nm) throws SQLException
    {
        return (String) sessionVariableMap.get(var_nm);
    }
    
    public DatabaseMetaData getMetaData() throws SQLException
    {
        return instance.getMetaData();
    }
    
    public void setCatalog(String arg0) throws SQLException
    {
        instance.setCatalog(arg0);
    }
    
    public String getCatalog() throws SQLException
    {
        return instance.getCatalog();
    }
    
    public void setTransactionIsolation(int arg0) throws SQLException
    {
        instance.setTransactionIsolation(arg0);
    }
    
    public int getTransactionIsolation() throws SQLException
    {
        return instance.getTransactionIsolation();
    }
    
    public SQLWarning getWarnings() throws SQLException
    {
        return instance.getWarnings();
    }
    
    public void clearWarnings() throws SQLException
    {
        instance.clearWarnings();
    }
    
    public Map getTypeMap() throws SQLException
    {
        return instance.getTypeMap();
    }
    
    public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException
    {
        instance.setTypeMap(arg0);
    }
    
    public void setHoldability(int arg0) throws SQLException
    {
        instance.setHoldability(arg0);
    }
    
    public int getHoldability() throws SQLException
    {
        return instance.getHoldability();
    }
    
    public Savepoint setSavepoint() throws SQLException
    {
        return instance.setSavepoint();
    }
    
    public Savepoint setSavepoint(String arg0) throws SQLException
    {
        return instance.setSavepoint(arg0);
    }
    
    public void releaseSavepoint(Savepoint arg0) throws SQLException
    {
        instance.releaseSavepoint(arg0);
    }
    
    public void setSessionVariable(String var_nm, String var_val)
    {
        sessionVariableMap.put(var_nm, var_val);
    }
    
    public Object getSessionObject(String var_nm)
    {
        return sessionObjectMap.get(var_nm);
    }
    
    public void setSessionObject(String var_nm, Object var_val)
    {
        sessionObjectMap.put(var_nm, var_val);
    }
    
    public Array createArrayOf(String arg0, Object[] arg1) throws SQLException
    {
        return null;
    }
    
    public Blob createBlob() throws SQLException
    {
        return null;
    }
    
    public Clob createClob() throws SQLException
    {
        return null;
    }
    
    public Struct createStruct(String arg0, Object[] arg1) throws SQLException
    {
        return null;
    }
    
    public Properties getClientInfo() throws SQLException
    {
        return null;
    }
    
    public String getClientInfo(String arg0) throws SQLException
    {
        return null;
    }
    
    public boolean isValid(int arg0) throws SQLException
    {
        return false;
    }
    
    public boolean isWrapperFor(Class<?> arg0) throws SQLException
    {
        return false;
    }
    
    public <T> T unwrap(Class<T> arg0) throws SQLException
    {
        return null;
    }
    
    public NClob createNClob() throws SQLException
    {
        return null;
    }
    
    public SQLXML createSQLXML() throws SQLException
    {
        return null;
    }
    
    public void setClientInfo(Properties arg0) throws SQLClientInfoException
    {
    }
    
    public void setClientInfo(String arg0, String arg1)
    {
    }

    @Override
    public void setSchema(String schema) throws SQLException
    {
    }

    @Override
    public String getSchema() throws SQLException
    {
        return null;
    }

    @Override
    public void abort(Executor executor) throws SQLException
    {
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException
    {
    }

    @Override
    public int getNetworkTimeout() throws SQLException
    {
        return 0;
    }
}