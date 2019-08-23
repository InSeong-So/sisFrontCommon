package biz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import biz.controller.MainAction;
import biz.domain.board.Board;
import biz.domain.board.BoardDAO;

public class ModifyService implements MainAction
{
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        MultipartRequest mrequest = null;
        
        int size = 100 * 1024 * 1024;
        
        String savePath = request.getRealPath("/upload");
        
        try
        {
            mrequest = new MultipartRequest(request, savePath, size, "UTF-8", new DefaultFileRenamePolicy());
        }
        catch (Exception e)
        {
            log.debug("mrequest Exception : " + e);
        }
        
        Board board = new Board();
        
        String title = mrequest.getParameter("title");
        String content = mrequest.getParameter("content");
        String file_nm = mrequest.getFilesystemName("input_file");
        String writer = request.getParameter("WRITER");
        int write_no = Integer.parseInt(mrequest.getParameter("write_no"));
        
        board.setTitle(title);
        board.setWriter(writer);
        board.setWrite_no(write_no);
        board.setContent(content);
        board.setFile_nm(file_nm);
        
        BoardDAO.getInstance().updateBoard(board);
        
        return "content.do?WRITE_NO=" + write_no + "&WRITER=" + writer;
    }
}
