package biz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.board.Board;
import biz.domain.board.BoardDAO;

public class CountService implements MainAction
{
    static final int TEST_ENV = 1;
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String writer = request.getParameter("WRITER");
        int write_no = Integer.parseInt(request.getParameter("WRITE_NO"));
        String reg_ip = request.getRemoteAddr();
        
        Board board = new Board();
        
        board.setWriter(writer);
        board.setWrite_no(write_no);
        
        board = BoardDAO.getInstance().getContent(board);
        
        log.debug("Regist IP : " + board.getReg_ip());
        log.debug("Now IP : " + reg_ip);
        
        if (TEST_ENV == 1)
        {
            log.debug("TEST Enviroment. Local IP Checked.");
            int view_cnt = board.getView_cnt();
            view_cnt++;
            board.setView_cnt(view_cnt);
            BoardDAO.getInstance().setBoardViewCnt(board);
            
        }
        else if (!reg_ip.equals(board.getReg_ip()))
        {
            int view_cnt = board.getView_cnt();
            view_cnt++;
            board.setView_cnt(view_cnt);
            BoardDAO.getInstance().setBoardViewCnt(board);
        }
        
        request.setAttribute("url", "content.do?WRITE_NO=" + write_no + "&WRITER=" + writer);
        
        return "redirect2.jsp";
    }
}
