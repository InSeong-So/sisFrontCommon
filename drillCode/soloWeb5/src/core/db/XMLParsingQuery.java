package core.db;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import core.util.CommonProperties;
import core.util.StringUtil;

public class XMLParsingQuery
{
    private Logger log = Logger.getRootLogger();
    
    protected ConcurrentHashMap cctHashMap = new ConcurrentHashMap();
    
    protected static XMLParsingQuery instance = new XMLParsingQuery();
    
    public static XMLParsingQuery getInstance()
    {
        return instance;
    }
    
    public String getElement(Object object, String queryName, Object replace)
    {
        String className = object instanceof String ? object.toString() : object instanceof Class ? ((Class) object).getName() : object.getClass().getName();
        return getElementWithClassName(className, queryName, replace);
        
    }
    
    public String getElementWithClassName(String className, String queryName, Object replace)
    {
        String filePath = new StringBuilder(String.valueOf(CommonProperties.getInstance().getProperty("SQL_XML_PATH"))).append(className.replace('.', '/')).append(".xml").toString();
        
        if (log.isDebugEnabled())
            log.debug("XML Query ClassName = [" + className + "], QueryName = [" + queryName + "]");
        
        return getElementWithPath(filePath, queryName, replace);
    }
    
    public String getElementWithPath(String filePath, String queryName, Object replace)
    {
        String str = null;
        try
        {
            Document document = getFromMemory(filePath);
            if (document == null)
            {
                document = saveDocument(filePath);
            }
            Element element = document.getRootElement();
            List list = element.getChildren();
            Iterator iterator = list.iterator();
            while (iterator.hasNext())
            {
                
                Element child = (Element) iterator.next();
                List ChildList = child.getAttributes();
                Iterator iter = ChildList.iterator();
                while (iter.hasNext())
                {
                    
                    Attribute attribute = (Attribute) iter.next();
                    if (attribute.getValue().equals(queryName))
                        return replaceBlock(child.getText(), replace);
                }
            }
            log.debug("< Query was not Defined! >");
            return null;
        }
        catch (Exception e)
        {
            log.error(e);
        }
        return str;
    }
    
    private String replaceBlock(String str, Object replace)
    {
        if (replace == null)
        {
            return str.replaceAll("@DYNAMIC@", "");
        }
        if (replace instanceof String)
        {
            return str.replaceAll("@DYNAMIC@", replace.toString());
        }
        if (replace instanceof HashMap)
        {
            
            String query = str;
            HashMap map = (HashMap) replace;
            Iterator iter = map.keySet().iterator();
            
            while (iter.hasNext())
            {
                
                String key = (String) iter.next();
                query = query.replaceAll(key, StringUtil.nvl((String) map.get(key), ""));
            }
            return query;
        }
        
        return str.replaceAll("@DYNAMIC@", "");
    }
    
    private Document saveDocument(String filePath) throws JDOMException, IOException
    {
        SAXBuilder builder = new SAXBuilder();
        File file = new File(filePath);
        Document document = builder.build(file);
        this.cctHashMap.put(filePath, new Object[] { document, String.valueOf(file.lastModified()) });
        return document;
    }
    
    private Document getFromMemory(String filePath)
    {
        Object[] objectArray = (Object[]) this.cctHashMap.get(filePath);
        if (objectArray != null)
        {
            if (objectArray[1].toString().equals(String.valueOf((new File(filePath)).lastModified())))
                return (Document) objectArray[0];
        }
        return null;
    }
}
