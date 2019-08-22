package biz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.board.Board;
import biz.domain.board.BoardDAO;
import core.db.DBConnector;

public class InsertService extends DBConnector implements MainAction
{
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String title = request.getParameter("title");
        String writer = request.getParameter("writer");
        String content = request.getParameter("content");
        String reg_ip = request.getRemoteAddr();
        
        Board board = new Board();
        
        board.setTitle(title);
        board.setContent(content);
        board.setWriter(writer);
        board.setReg_ip(reg_ip);

        BoardDAO.getInstance().insertBoard(board);
        
        return "list.do";
    }
    
}
