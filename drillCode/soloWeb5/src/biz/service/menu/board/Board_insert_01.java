package biz.service.menu.board;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import biz.controller.MainAction;
import core.db.IUDSQL;
import core.db.SQLUtil;
import core.util.StringUtil;

public class Board_insert_01 implements MainAction
{
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        MultipartRequest mrequest = null;
        
        int size = 100 * 1024 * 1024;
        
        String savePath = request.getRealPath("/upload");
        //        log.debug("savePath : " + savePath);
        
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String midPath = "upload/";
        
        try
        {
            mrequest = new MultipartRequest(request, savePath, size, "UTF-8", new DefaultFileRenamePolicy());
        }
        catch (Exception e)
        {
            log.debug("mrequest Exception : " + e);
        }
        
        String FILE_NM = StringUtil.nvl(mrequest.getFilesystemName("input_file"));
        String TITLE = mrequest.getParameter("title");
        String WRITER = mrequest.getParameter("writer");
        String CONTENT = mrequest.getParameter("content");
        String REG_IP = request.getRemoteAddr();
        
        log.debug("Insert IP : " + REG_IP);
        
        Connection conn = null;
        
        try
        {
            conn = SQLUtil.getConnection(request, prop.getProperty("DEFAULT_JNDINAME"));
        }
        catch (Exception e)
        {
            log.debug("Exception : " + e);
        }
        
        IUDSQL iud = new IUDSQL(conn, request);
        
        iud.setTable("BR0010");
        iud.addKey("WRITER", WRITER);
        iud.addKeyRaw("WRITE_NO", "(SELECT NVL(MAX(WRITE_NO + 1), 1) FROM BR0010 WHERE WRITER = :K0)");
        iud.addFieldRaw("SEQ_NO", "(SELECT NVL(MAX(SEQ_NO + 1), 1) FROM BR0010)");
        iud.addField("FILE_NM", FILE_NM);
        iud.addField("TITLE", TITLE);
        iud.addField("CONTENT", CONTENT);
        iud.addField("REG_IP", REG_IP);
        iud.addFieldRaw("REG_DATE", "CURRENT_TIMESTAMP");
        iud.addFieldRaw("MOD_DATE", "CURRENT_TIMESTAMP");
        
        iud.insert();
        
        return "/board/list.do";
    }
    
}
