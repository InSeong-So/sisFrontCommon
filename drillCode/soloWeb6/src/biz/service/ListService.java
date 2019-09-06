package biz.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.board.Board;
import biz.domain.board.BoardDAO;

public class ListService implements MainAction
{
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        int page = 0;
        
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        
//        log.debug("page : " + page);
        
        ArrayList<Board> boardList = BoardDAO.getInstance().getBoardList(page);
        
        request.setAttribute("boardList", boardList);
        request.setAttribute("page", page);
        return "list.jsp";
    }
}
