package biz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.board.Board;
import biz.domain.board.BoardDAO;

public class ModifyService implements MainAction
{
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        Board board = new Board();
        
        String title = request.getParameter("title");
        String writer = request.getParameter("writer");
        int write_no = Integer.parseInt(request.getParameter("write_no"));
        String content = request.getParameter("content");
        
        board.setTitle(title);
        board.setWriter(writer);
        board.setWrite_no(write_no);
        board.setContent(content);
        
        BoardDAO.getInstance().updateBoard(board);
        
        return "content.do?WRITE_NO=" + write_no + "&WRITER=" + writer;
    }
}
