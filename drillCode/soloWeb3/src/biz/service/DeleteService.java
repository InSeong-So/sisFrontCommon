package biz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.board.Board;
import biz.domain.board.BoardDAO;

public class DeleteService implements MainAction
{

    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String writer = request.getParameter("WRITER");
        int write_no = Integer.parseInt(request.getParameter("WRITE_NO"));
        
        Board board = new Board();
        
        board.setWriter(writer);
        board.setWrite_no(write_no);
        
        BoardDAO.getInstance().deleteBoard(board);
        
        return "list.do";
    }
}
