package core.db;

import java.io.FileReader;
import java.io.Reader;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public final class IBatisDBConnector
{
    static Logger log = Logger.getRootLogger();
    
    private static SqlMapClient sqlMapClient;
    
    static
    {
        try
        {
//            String resource = "C:/EHR_PROJECT/soloWeb3/WebContent//WEB-INF/sql-config.xml";
            log.debug("1");
            String path = ClassLoader.getSystemResource("").getPath() + "sql-config.xml";
            log.debug("path : " + path);
//            Reader reader = new FileReader(path.substring(1));
            Reader reader = new FileReader("C:/EHR_PROJECT/soloWeb3/WebContent/WEB-INF/sql-config.xml");
            log.debug("2");
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
            log.debug("3");
        }
        catch (Exception e)
        {
            log.debug("IbatisDBConnector has Exception!!");
            e.printStackTrace();
        }
    }
    
    public static SqlMapClient getSqlMapInstance()
    {
        return sqlMapClient;
    }
}
