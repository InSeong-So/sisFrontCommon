package biz.service.menu.board;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.board.Board;
import core.db.ClearStatement;
import core.db.SQLUtil;

public class Board_List_01 implements MainAction
{
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String query = xmlParsingQuery.getElement(this, "search", null);
        
        ArrayList<Board> boardList = new ArrayList<Board>();
        
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
        
        log.debug(cstmt.getQueryString());
        
        //        try
        //        {
        //            CallableStatement cs = SQLUtil.getConnection("GRSEHR").prepareCall(query);
        ResultSet rs = cstmt.executeQuery();
        
        while (rs.next())
        {
            Board board = new Board();
            
            board.setSeq_no(rs.getInt("SEQ_NO"));
            board.setTitle(rs.getString("TITLE"));
            board.setContent(rs.getString("CONTENT"));
            board.setWriter(rs.getString("WRITER"));
            board.setReg_date(rs.getString("REG_DATE"));
            board.setView_cnt(rs.getInt("VIEW_CNT"));
            boardList.add(board);
        }
        //            
        cstmt.close();
        //        }
        //        catch (Exception e)
        //        {
        //            log.debug("에러ㅠㅠ : " + e);
        //            throw new SQLException();
        //        }
        
        //        request.setAttribute("boardList", SQLUtil.getResultSetWithClose(cstmt));
        request.setAttribute("boardList", boardList);
        return "list.jsp";
    }
}
