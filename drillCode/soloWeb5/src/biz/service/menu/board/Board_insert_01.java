package biz.service.menu.board;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import biz.controller.MainAction;
import biz.domain.board.Board;
import core.db.ClearStatement;
import core.db.SQLUtil;

public class Board_insert_01 implements MainAction
{
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String query = xmlParsingQuery.getElement(this, "insert", null);
        
        log.debug("check : " + query);
        
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
        
        String FILE_NM = mrequest.getFilesystemName("input_file");
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
        
        ClearStatement cstmt = new ClearStatement(conn, query);
        
        cstmt.setParameter("FILE_NM", FILE_NM);
        cstmt.setParameter("TITLE", TITLE);
        cstmt.setParameter("WRITER", WRITER);
        cstmt.setParameter("CONTENT", CONTENT);
        cstmt.setParameter("REG_IP", REG_IP);
        
        log.debug(cstmt.getQueryString());
        
        cstmt.executeQuery();
        cstmt.close();
        
        return "/board/list.do";
    }
    
}
