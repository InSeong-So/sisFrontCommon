package biz.service.menu.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import biz.controller.MainAction;
import biz.domain.board.Board;
import biz.domain.board.BoardDAO;

public class ListService implements MainAction
{
    private Logger log = Logger.getRootLogger();
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        ArrayList<Board> boardList = BoardDAO.getBoardList();
        request.setAttribute("boardList", boardList);
        return "board/list.jsp";
    }
}
