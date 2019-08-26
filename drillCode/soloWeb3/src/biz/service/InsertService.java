package biz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import biz.controller.MainAction;
import biz.domain.board.Board;
import biz.domain.board.BoardDAO;

public class InsertService implements MainAction
{
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        MultipartRequest mrequest = null;
        
        int size = 100 * 1024 * 1024;
        
        String savePath = request.getRealPath("/upload");
//        log.debug("savePath : " + savePath);
        
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String midPath = "upload/";
        
        log.debug("path >>>>>>>>>>>>>>>>>>>> " + rootPath + midPath);
        
        try
        {
            mrequest = new MultipartRequest(request, savePath, size, "UTF-8", new DefaultFileRenamePolicy());
        }
        catch (Exception e)
        {
            log.debug("mrequest Exception : " + e);
        }
        
        String file_nm = mrequest.getFilesystemName("input_file");
        
        String title = mrequest.getParameter("title");
        String writer = mrequest.getParameter("writer");
        String content = mrequest.getParameter("content");
        String reg_ip = request.getRemoteAddr();
        
//        log.debug("Insert IP : " + reg_ip);
        
        Board board = new Board();
        
        board.setTitle(title);
        board.setContent(content);
        board.setWriter(writer);
        board.setFile_nm(file_nm);
        board.setReg_ip(reg_ip);
        
        BoardDAO.getInstance().insertBoard(board);
        
        return "list.do";
    }
    
}
