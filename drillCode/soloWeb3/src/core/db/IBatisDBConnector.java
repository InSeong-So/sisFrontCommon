package core.db;

import com.ibatis.sqlmap.client.SqlMapClient;

public final class IBatisDBConnector
{
    private static SqlMapClient sqlMapClient;
    
    static {
        try
        {
            String resource = "biz/board/";
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
