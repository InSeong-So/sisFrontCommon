package biz.service;

import java.io.File;

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
        
        board = BoardDAO.getInstance().getContent(board);
        
        String fileName = board.getFile_nm();
        
        String uploadFileName = request.getRealPath("/upload/" + fileName);
        
        File uploadFile = new File(uploadFileName);
        
        if (uploadFile.exists() && uploadFile.isFile())
            uploadFile.delete();
        
        BoardDAO.getInstance().deleteBoard(board);
        
        return "list.do";
    }
}
