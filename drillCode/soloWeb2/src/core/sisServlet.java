package core;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public abstract class sisServlet extends HttpServlet
{
    // 직렬화 인터페이스
    private static final long serialVersionUID = 1L;
    
    protected Logger log = Logger.getRootLogger();
    
    protected Map uriMap = new HashMap();
    
    private void loadProperties(String path)
    {
        ResourceBundle rbHome = ResourceBundle.getBundle(path);
        Enumeration<String> actionEnumHome = rbHome.getKeys();
        while (actionEnumHome.hasMoreElements())
        {
            String command = actionEnumHome.nextElement();
            String className = rbHome.getString(command);
            
            try
            {
                Class commandClass = Class.forName(className); // 해당 문자열을 클래스로 만든다
                Object commandInstance = commandClass.newInstance(); // 해당 클래스의 객체를 생성
                uriMap.put(command, commandInstance); // Map 객체인 commandMap에 객체 저장
            }
            catch (ClassNotFoundException e)
            {
                continue; // error,  throw new ServletException(e);
                
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void init()
    {
        log.debug(String.valueOf(getClass().getName()) + ".init()==================================================");
        String prefix = getServletContext().getRealPath("/").replaceAll("\\\\", "/");
        
        char tmp = prefix.charAt(prefix.length() - 1);
        if (tmp != '/' && tmp != '\\')
            prefix = String.valueOf(prefix) + "/";
        
        PropertyConfigurator.configure(String.valueOf(prefix) + getInitParameter("log4j-init"));
        
        try
        {
            String dir_base = (new File(prefix)).getParentFile().getCanonicalPath().replaceAll("\\\\", "/");
            char lastChar = dir_base.charAt(dir_base.length() - 1);
            if (lastChar != '/')
                dir_base = String.valueOf(dir_base) + "/";
            
            CommonProperties.setDefaultProperty("DIR_BASE", dir_base);
        }
        catch (Exception e)
        {
            
            log.error("Exception", e);
        }
        
        CommonProperties.setConfFilePath(String.valueOf(prefix) + getInitParameter("common-conf"));
        
        CommonProperties prop = CommonProperties.getInstance();
        
        log.debug(prop.get("list.do"));
        
        loadProperties((String)prop.get("list.do"));
        
        log.debug(String.valueOf(getClass().getName()) + ".init() End==============================================");
    }
    
    protected abstract void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException;
    
    protected abstract void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException;
    
    public void destroy()
    {
        log.debug(String.valueOf(getClass().getName()) + ".destroy()==================================================");
        log.debug(String.valueOf(getClass().getName()) + ".destroy() End==============================================");
    }
}
