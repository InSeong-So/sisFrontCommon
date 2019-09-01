package biz.service.menu.board;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.board.Board;
import core.db.ClearStatement;
import core.db.SQLUtil;
import core.db.SisResultSet;
import core.util.SisRequiredClass;

public class Board_List_01 extends SisRequiredClass implements MainAction
{
    
    public Board_List_01(Connection conn, HttpServletRequest request, HttpServletResponse response)
    {
        super(conn, request, response);
    }
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String query = xmlParsingQuery.getElement(this, "search", null);
        
        ArrayList<Board> boardList = new ArrayList<Board>();
        
        ClearStatement cstmt = new ClearStatement(conn, query);
        
        log.debug(cstmt.getQueryString());
        
        SisResultSet srs = SQLUtil.getResultSetWithClose(cstmt);
        
        while (srs.next())
        {
            Board board = new Board();
            
            board.setSeq_no(srs.getInt("SEQ_NO"));
            board.setTitle(srs.getString("TITLE"));
            board.setContent(srs.getString("CONTENT"));
            board.setWriter(srs.getString("WRITER"));
            board.setReg_date(srs.getString("REG_DATE"));
            board.setView_cnt(srs.getInt("VIEW_CNT"));
            boardList.add(board);
        }
        
        request.setAttribute("boardList", boardList);
        return "list.jsp";
    }
}
