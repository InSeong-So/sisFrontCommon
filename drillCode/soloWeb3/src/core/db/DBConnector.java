package core.db;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DBConnector
{
    public static Logger log = Logger.getRootLogger();
    
    private SqlMapClient myDb;
    
    public void setDb()
    {
//        log.debug("DBConnector setDb : 시작");
        myDb = IBatisDBConnector.getSqlMapInstance();
//        log.debug("DBConnector setDb : 종료");
    }
    
    protected SqlMapClient getDb()
    {
//        log.debug("DBConnector getDb : DB 정보 반환");
        return myDb;
    }
}
