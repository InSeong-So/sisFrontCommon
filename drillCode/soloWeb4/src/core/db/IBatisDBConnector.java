package core.db;

import java.io.FileReader;
import java.io.Reader;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public final class IBatisDBConnector
{
    public static Logger log = Logger.getRootLogger();
    
    private static SqlMapClient sqlMapClient;
    
    static
    {
        try
        {
            //            String path = ClassLoader.getSystemResource("").getPath() + "sql-config.xml";
            //            Reader reader = new FileReader(path.substring(1));
            Reader reader = new FileReader("C:/EHR_PROJECT/soloWeb3/WebContent/WEB-INF/sql-config.xml");
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        }
        catch (Exception e)
        {
//            log.debug("IbatisDBConnector has Exception!!");
            e.printStackTrace();
        }
    }
    
    public static SqlMapClient getSqlMapInstance()
    {
        return sqlMapClient;
    }
}
