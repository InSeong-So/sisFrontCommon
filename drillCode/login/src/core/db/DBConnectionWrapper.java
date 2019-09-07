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
    
    protected ArrayList stmt;
    
    protected HashMap sessionVariableMap;
    
    protected HashMap sessionObjectMap;
    
    public DBConnectionWrapper(Connection instance)
    {
        this.stmt = new ArrayList();
        this.sessionVariableMap = new HashMap();
        this.sessionObjectMap = new HashMap();
        this.instance = instance;
    }
    
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException
    {
        return null;
    }
    
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException
    {
        return false;
    }
    
    @Override
    public Statement createStatement() throws SQLException
    {
        Statement createStmt = this.instance.createStatement();
        this.stmt.add(createStmt);
        return createStmt;
    }
    
    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException
    {
        Statement prepareStmt = this.instance.prepareStatement(sql);
        this.stmt.add(prepareStmt);
        return (PreparedStatement) prepareStmt;
    }
    
    @Override
    public CallableStatement prepareCall(String sql) throws SQLException
    {
        Statement callabelStmt = this.instance.prepareCall(sql);
        this.stmt.add(callabelStmt);
        return (CallableStatement) callabelStmt;
    }
    
    @Override
    public String nativeSQL(String sql) throws SQLException
    {
        return this.instance.nativeSQL(sql);
    }
    
    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException
    {
        this.instance.setAutoCommit(autoCommit);
    }
    
    @Override
    public boolean getAutoCommit() throws SQLException
    {
        return this.instance.getAutoCommit();
    }
    
    @Override
    public void commit() throws SQLException
    {
        this.instance.commit();
    }
    
    @Override
    public void rollback() throws SQLException
    {
        this.instance.rollback();
    }
    
    @Override
    public void close() throws SQLException
    {
        int size = this.stmt.size();
        for (int i = 0; i < size; i++)
        {
            try
            {
                ((Statement) this.stmt.get(i)).close();
            }
            catch (Exception e)
            {
            }
        }
        this.instance.close();
    }
    
    @Override
    public boolean isClosed() throws SQLException
    {
        return this.instance.isClosed();
    }
    
    @Override
    public DatabaseMetaData getMetaData() throws SQLException
    {
        return this.instance.getMetaData();
    }
    
    @Override
    public void setReadOnly(boolean readOnly) throws SQLException
    {
        this.instance.setReadOnly(readOnly);
    }
    
    @Override
    public boolean isReadOnly() throws SQLException
    {
        return this.instance.isReadOnly();
    }
    
    @Override
    public void setCatalog(String catalog) throws SQLException
    {
        this.instance.setCatalog(catalog);
    }
    
    @Override
    public String getCatalog() throws SQLException
    {
        return this.instance.getCatalog();
    }
    
    @Override
    public void setTransactionIsolation(int level) throws SQLException
    {
        this.instance.setTransactionIsolation(level);
    }
    
    @Override
    public int getTransactionIsolation() throws SQLException
    {
        return this.instance.getTransactionIsolation();
    }
    
    @Override
    public SQLWarning getWarnings() throws SQLException
    {
        return this.instance.getWarnings();
    }
    
    @Override
    public void clearWarnings() throws SQLException
    {
        this.instance.clearWarnings();
    }
    
    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException
    {
        Statement createStmt = this.instance.createStatement(resultSetType, resultSetConcurrency);
        this.stmt.add(createStmt);
        return createStmt;
    }
    
    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
    {
        Statement prepareStmt = this.instance.prepareStatement(sql, resultSetType, resultSetConcurrency);
        this.stmt.add(prepareStmt);
        return (PreparedStatement) prepareStmt;
    }
    
    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
    {
        Statement callabelStmt = this.instance.prepareCall(sql, resultSetType, resultSetConcurrency);
        this.stmt.add(callabelStmt);
        return (CallableStatement) callabelStmt;
    }
    
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException
    {
        return this.instance.getTypeMap();
    }
    
    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException
    {
        this.instance.setTypeMap(map);
    }
    
    @Override
    public void setHoldability(int holdability) throws SQLException
    {
        this.instance.setHoldability(holdability);
    }
    
    @Override
    public int getHoldability() throws SQLException
    {
        return this.instance.getHoldability();
    }
    
    @Override
    public Savepoint setSavepoint() throws SQLException
    {
        return this.instance.setSavepoint();
    }
    
    @Override
    public Savepoint setSavepoint(String name) throws SQLException
    {
        return this.instance.setSavepoint(name);
    }
    
    @Override
    public void rollback(Savepoint savepoint) throws SQLException
    {
        this.instance.rollback(savepoint);
    }
    
    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException
    {
        this.instance.releaseSavepoint(savepoint);
    }
    
    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
    {
        Statement createStmt = this.instance.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        this.stmt.add(createStmt);
        return createStmt;
    }
    
    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
    {
        Statement prepareStmt = this.instance.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        this.stmt.add(prepareStmt);
        return (PreparedStatement) prepareStmt;
    }
    
    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
    {
        Statement callabelStmt = this.instance.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        this.stmt.add(callabelStmt);
        return (CallableStatement) callabelStmt;
    }
    
    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException
    {
        Statement prepareStmt = this.instance.prepareStatement(sql, autoGeneratedKeys);
        this.stmt.add(prepareStmt);
        return (PreparedStatement) prepareStmt;
    }
    
    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException
    {
        Statement prepareStmt = this.instance.prepareStatement(sql, columnIndexes);
        this.stmt.add(prepareStmt);
        return (PreparedStatement) prepareStmt;
    }
    
    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException
    {
        Statement prepareStmt = this.instance.prepareStatement(sql, columnNames);
        this.stmt.add(prepareStmt);
        return (PreparedStatement) prepareStmt;
    }
    
    @Override
    public Clob createClob() throws SQLException
    {
        return null;
    }
    
    @Override
    public Blob createBlob() throws SQLException
    {
        return null;
    }
    
    @Override
    public NClob createNClob() throws SQLException
    {
        return null;
    }
    
    @Override
    public SQLXML createSQLXML() throws SQLException
    {
        return null;
    }
    
    @Override
    public boolean isValid(int timeout) throws SQLException
    {
        return false;
    }
    
    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException
    {
    }
    
    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException
    {
    }
    
    @Override
    public String getClientInfo(String name) throws SQLException
    {
        return null;
    }
    
    @Override
    public Properties getClientInfo() throws SQLException
    {
        return null;
    }
    
    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException
    {
        return null;
    }
    
    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException
    {
        return null;
    }
    
    public void saveStatement(Statement stmt)
    {
        this.stmt.add(stmt);
    }
    
    public String getSessionVariable(String variableName) throws SQLException
    {
        return (String) this.sessionVariableMap.get(variableName);
    }
    
    public void setSessionVariable(String variableName, String variableValue) throws SQLException
    {
        this.sessionVariableMap.put(variableName, variableValue);
    }
    
    public Object getSessionObject(String variableName) throws SQLException
    {
        return this.sessionObjectMap.get(variableName);
    }
    
    public void setSessionObject(String variableName, String variableValue) throws SQLException
    {
        this.sessionObjectMap.put(variableName, variableValue);
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
