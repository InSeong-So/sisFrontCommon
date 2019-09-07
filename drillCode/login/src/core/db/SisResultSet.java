package core.db;

import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import core.util.StringUtil;

public class SisResultSet
{
    Logger log = Logger.getRootLogger();
    
    public static SisResultSet make(ResultSet rs) throws SQLException
    {
        return new SisResultSet(rs);
    }
    
    int columnCount = 0;
    
    int rowCount = 0;
    
    int currRow = 0;
    
    HashMap mapColNmIdx = null;
    
    String[] columnNames = null;
    
    int[] columnTypes = null;
    
    ArrayList listRec = null;
    
    boolean ingnoreColumnError = false;
    
    public SisResultSet()
    {
    }
    
    public void setIgnoreColumnError(boolean b)
    {
        this.ingnoreColumnError = b;
    }
    
    public SisResultSet(ResultSet rs, boolean bCloseAfterParse) throws SQLException
    {
        parse(rs, bCloseAfterParse);
    }
    
    public SisResultSet(ResultSet rs) throws SQLException
    {
        parse(rs, true);
    }
    
    public void parse(ResultSet rs, boolean bCloseAfterParse) throws SQLException
    {
        try
        {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            HashMap mapColNmIdx = new HashMap();
            String[] columnNames = new String[columnCount];
            int[] columnTypes = new int[columnCount];
            
            for (int i = 1; i <= columnCount; i++)
            {
                
                String columnName = rsmd.getColumnName(i).toUpperCase();
                
                columnNames[i - 1] = columnName;
                columnTypes[i - 1] = rsmd.getColumnType(i);
                mapColNmIdx.put(columnName, String.valueOf(i));
            }
            
            ArrayList listRec = new ArrayList();
            
            while (rs.next())
            {
                
                Object[] rowData = new Object[columnCount];
                for (int col = 1; col <= columnCount; col++)
                {
                    
                    if (columnTypes[col - 1] == 2005)
                    {
                        
                        rowData[col - 1] = readClobData(rs.getCharacterStream(col));
                        
                    }
                    else
                    {
                        
                        rowData[col - 1] = rs.getObject(col);
                    }
                }
                
                listRec.add(rowData);
            }
            
            this.columnNames = columnNames;
            this.columnTypes = columnTypes;
            this.columnCount = columnCount;
            this.mapColNmIdx = mapColNmIdx;
            this.listRec = listRec;
            this.rowCount = this.listRec.size();
        }
        catch (IOException e)
        {
            
            throw new SQLException(e.getMessage());
        }
        finally
        {
            
            if (bCloseAfterParse)
            {
                try
                {
                    rs.close();
                }
                catch (Exception exception)
                {
                }
            }
        }
    }
    
    public String getColumnName(int col)
    {
        return this.columnNames[col - 1];
    }
    
    public String[] getColumnNameArray()
    {
        return this.columnNames;
    }
    
    public int getRowCount()
    {
        return this.rowCount;
    }
    
    public int getColumnCount()
    {
        return this.columnCount;
    }
    
    public Object[] getRowData(int row)
    {
        if (row - 1 < 0 || row - 1 > this.listRec.size() - 1)
            throw new RuntimeException("row is not in range, maybe execute next()...");
        return (Object[]) this.listRec.get(row - 1);
    }
    
    public Object[] getRowData()
    {
        return getRowData(this.currRow);
    }
    
    public Object getObject(int row, int col)
    {
        return getRowData(row)[col - 1];
    }
    
    public Object getObject(int row, String colNm)
    {
        if (this.ingnoreColumnError)
        {
            try
            {
                
                return getObject(row, findColumn(colNm));
            }
            catch (Exception e)
            {
                
                if (row == 1)
                {
                    log.debug("Column Not Found : -------------> " + colNm);
                }
                return null;
            }
        }
        
        return getObject(row, findColumn(colNm));
    }
    
    public Object getObject(int col)
    {
        return getObject(this.currRow, col);
    }
    
    public Object getObject(String colNm)
    {
        return getObject(this.currRow, colNm);
    }
    
    public String getString(int row, int col)
    {
        Object o = getObject(row, col);
        return (o == null) ? null : o.toString();
    }
    
    public String getString(int row, String colNm)
    {
        Object o = getObject(row, colNm);
        return (o == null) ? null : o.toString();
    }
    
    public String getString(int col)
    {
        Object o = getObject(col);
        return (o == null) ? null : o.toString();
    }
    
    public String getString(String colNm)
    {
        Object o = getObject(colNm);
        return (o == null) ? null : o.toString();
    }
    
    public int getInt(String colNm)
    {
        String val = getString(colNm);
        return (val == null) ? 0 : Integer.parseInt(val);
    }
    
    public int getInt(int col)
    {
        String val = getString(col);
        return (val == null) ? 0 : Integer.parseInt(val);
    }
    
    public double getDouble(String colNm)
    {
        String val = getString(colNm);
        return (val == null) ? 0.0D : Double.parseDouble(val);
    }
    
    public double getDouble(int col)
    {
        String val = getString(col);
        return (val == null) ? 0.0D : Double.parseDouble(val);
    }
    
    public boolean absolute(int row)
    {
        if (row >= 0 && row <= this.rowCount + 1)
        {
            this.currRow = row;
        }
        
        return isDataRow(row);
    }
    
    public void afterLast()
    {
        this.currRow = this.rowCount + 1;
    }
    
    public void beforeFirst()
    {
        this.currRow = 0;
    }
    
    public void close()
    {
    }
    
    public int findColumn(String columnName)
    {
        try
        {
            return Integer.parseInt(this.mapColNmIdx.get(columnName.toUpperCase()).toString());
        }
        catch (Exception e)
        {
            
            throw new RuntimeException("findColumn Error: " + columnName);
        }
    }
    
    public boolean first()
    {
        return absolute(1);
    }
    
    public int getRow()
    {
        return this.currRow;
    }
    
    public boolean isAfterLast()
    {
        return (this.currRow == this.rowCount + 1);
    }
    
    public boolean isBeforeFirst()
    {
        return (this.currRow == 0);
    }
    
    public boolean isFirst()
    {
        return (this.rowCount > 0 && this.currRow == 1);
    }
    
    public boolean isLast()
    {
        return (this.rowCount > 0 && this.currRow == this.rowCount);
    }
    
    public boolean last()
    {
        return absolute(this.rowCount);
    }
    
    public boolean next()
    {
        return absolute(this.currRow + 1);
    }
    
    public boolean previous()
    {
        return absolute(this.currRow - 1);
    }
    
    public boolean relative(int rows)
    {
        return absolute(this.currRow + rows);
    }
    
    public boolean isDataRow(int row)
    {
        return (this.rowCount > 0 && row >= 1 && row <= this.rowCount);
    }
    
    public String makeResult(String delimCol, String delimRow)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(makeHeaderString(delimCol));
        sb.append(delimRow);
        
        for (int row = 0; next(); row++)
        {
            
            makeRowDataString(delimCol);
            sb.append(delimRow);
        }
        beforeFirst();
        return sb.toString();
    }
    
    public String makeHeaderString(String dCol)
    {
        StringBuffer sb = new StringBuffer();
        int cntCol = getColumnCount();
        
        for (int col = 0; col < cntCol; col++)
        {
            
            if (col > 0)
                sb.append(dCol);
            sb.append(getColumnName(col + 1));
        }
        return sb.toString();
    }
    
    public String makeRowDataString(String dCol)
    {
        StringBuffer sb = new StringBuffer();
        int cntCol = getColumnCount();
        
        for (int col = 0; col < cntCol; col++)
        {
            
            if (col > 0)
                sb.append(dCol);
            sb.append(StringUtil.nvl(getString(col + 1)));
        }
        return sb.toString();
    }
    
    public String[] getColumnNames()
    {
        return this.columnNames;
    }
    
    public static String readClobData(Reader reader) throws IOException
    {
        StringBuffer data = new StringBuffer();
        char[] buf = new char[1024];
        int cnt = 0;
        if (reader != null)
        {
            while ((cnt = reader.read(buf)) != -1)
            {
                data.append(buf, 0, cnt);
            }
        }
        return data.toString();
    }
    
    public void setString(int row, String colNm, String setData)
    {
        Object[] rowData = getRowData(row);
        rowData[findColumn(colNm) - 1] = setData;
        this.listRec.set(row - 1, rowData);
    }
}
