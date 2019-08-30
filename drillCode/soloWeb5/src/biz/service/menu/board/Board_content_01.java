package biz.service.menu.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.board.Board;

public class Board_content_01 implements MainAction
{
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    { 
        String writer = request.getParameter("WRITER");
        int write_no = Integer.parseInt(request.getParameter("WRITE_NO"));
        
        Board board = new Board();
        
        board.setWriter(writer);
        board.setWrite_no(write_no);
        
        return "content.jsp";
    }
}
