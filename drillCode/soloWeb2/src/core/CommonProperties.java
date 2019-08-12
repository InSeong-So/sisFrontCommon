package core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class CommonProperties extends Properties
{
    static Logger log = Logger.getRootLogger();
    
    static String CONF_FILE_PATH;
    
    static File fileCONF_FILE;
    
    static long lastModified = 0L;
    
    static Pattern pattern = Pattern.compile("\\$\\{(\\w+)\\}");
    
    private static CommonProperties instance = new CommonProperties();
    
    public static HashMap defaultMap = new HashMap();
    
    public static CommonProperties getInstance()
    {
        if (fileCONF_FILE == null)
        {
            setConfFilePath(System.getProperty("biz.CONF_FILE_PATH"));
        }
        
        if (fileCONF_FILE == null)
            throw new RuntimeException("Conf file is not set.");
        
        lastModified = fileCONF_FILE.lastModified();
        
        try
        {
            instance.reload();
        }
        catch (Exception e)
        {
        }
        
        if (instance.lastModified != lastModified)
        {
            instance.lastModified = lastModified;
            log.info(instance);
        }
        
        return instance;
    }
    
    public void reload() throws Exception
    {
        FileInputStream fis = null;
        
        try
        {
            fis = new FileInputStream(CONF_FILE_PATH);
            
            load(fis);
            
            Iterator keyIter = defaultMap.keySet().iterator();
            while (keyIter.hasNext())
            {
                
                String key = (String) keyIter.next();
                
                String val = (String) defaultMap.get(key);
                
                if (!hasKey(key))
                    setProperty(key, val);
            }
            Enumeration enum1 = keys();
            while (enum1.hasMoreElements())
            {
                String key = (String) enum1.nextElement();
                remake(key);
            }
        }
        finally
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (Exception exception)
                {
                }
            }
        }
    }
    
    private boolean hasKey(String compKey)
    {
        Enumeration enum1 = keys();
        
        while (enum1.hasMoreElements())
        {
            
            String key = (String) enum1.nextElement();
            if (compKey.equals(key))
                return true;
        }
        return false;
    }
    
    private String remake(String key)
    {
        String val = getProperty(key);
        Matcher matcher = pattern.matcher(val);
        StringBuffer sb = new StringBuffer();
        
        int changedCnt = 0;
        while (matcher.find())
        {
            
            changedCnt++;
            String key2 = matcher.group(1);
            String val2 = getProperty(key2);
            if (val2 != null && pattern.matcher(val2).find())
                val2 = remake(key2);
            matcher.appendReplacement(sb, Util.nvl(val2));
        }
        matcher.appendTail(sb);
        String changedVal = sb.toString().trim();
        if (changedCnt > 0)
        {
            
            setProperty(key, changedVal);
            return changedVal;
        }
        return val;
    }
    
    public static void setConfFilePath(String conf_file_path)
    {
        CONF_FILE_PATH = conf_file_path;
        fileCONF_FILE = new File(CONF_FILE_PATH);
        System.setProperty("biz.CONF_FILE_PATH", CONF_FILE_PATH);
    }
    
    public static void setDefaultProperty(String key, String val)
    {
        defaultMap.put(key, val);
    }
}
