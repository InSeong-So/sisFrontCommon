package core.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import core.util.CommonProperties;

public abstract class SisAheadServlet extends HttpServlet
{
    // 직렬화 인터페이스
    private static final long serialVersionUID = 1L;
    
    protected Logger log = Logger.getRootLogger();
    
    protected HashMap<String, String> pathMap = new HashMap<String, String>();
    
    public void init()
    {
        log.debug(String.valueOf(getClass().getName()) + ".init() Start ============================================");
        
        setClassPath();
        
        if (pathMap == null)
            throw new RuntimeException("ClassPath is Not Set.");
        
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
        
        log.debug(String.valueOf(getClass().getName()) + ".init() End ==============================================");
    }
    
    protected abstract void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException;
    
    protected abstract void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException;
    
    public void destroy()
    {
        log.debug(String.valueOf(getClass().getName()) + ".destroy() Start ============================================");
        log.debug(String.valueOf(getClass().getName()) + ".destroy() End ==============================================");
    }
    
    private void setClassPath()
    {
        log.debug("ClassPath Set Start!");
        pathMap.put("/board/list.do", "biz.service.menu.board.Board_List_01");
        pathMap.put("/board/insert.do", "biz.service.menu.board.Board_insert_01");
        pathMap.put("/board/content.do", "biz.service.menu.board.Board_content_01");
        pathMap.put("/board/count.do", "biz.service.menu.board.Board_count_01");
        pathMap.put("/board/delete.do", "biz.service.menu.board.Board_delete_01");
        pathMap.put("/board/modify.do", "biz.service.menu.board.Board_modify_01");
        log.debug("ClassPath Set Finish!");
    }
    
    public String getClassPath(String key)
    {
        return pathMap.get(key);
    }
}
