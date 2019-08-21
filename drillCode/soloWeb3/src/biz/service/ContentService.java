package biz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.board.Board;
import biz.domain.board.BoardDAO;

public class ContentService implements MainAction
{
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    { 
        String writer = request.getParameter("WRITER");
        int write_no = Integer.parseInt(request.getParameter("WRITE_NO"));
        
        Board board = new Board();
        
        board.setWriter(writer);
        board.setWrite_no(write_no);
        
        request.setAttribute("board", BoardDAO.getInstance().getContent(board));
        
        return "content.jsp";
    }
}
