package biz.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.controller.MainAction;
import biz.domain.board.Board;
import biz.domain.board.BoardDAO;

public class DownloadAction implements MainAction
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
        
        File downFile = new File(uploadFileName);
        
        if (downFile.exists() && downFile.isFile())
        {
            try
            {
                long fileSize = downFile.length();
                response.setContentType("application/x-msdownload");
                response.setContentLength((int) fileSize);
                
                String strClient = request.getHeader("user-agent");
                
                if (strClient.indexOf("MSIE 5.5") != -1)
                    response.setHeader("content-Disposition", "filename=" + fileName + ";");
                else
                {
                    fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
                    response.setHeader("content-Disposition", "attachment;filename=" + fileName + ";");
                }
                
                response.setHeader("Content-Length", String.valueOf(fileSize));
                response.setHeader("Content-Transfer-Encoding", "binary");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "private");
                
                
                byte size[] = new byte[1024];
                BufferedInputStream fin = new BufferedInputStream(new FileInputStream(downFile));
                BufferedOutputStream fos = new BufferedOutputStream(response.getOutputStream());
                
                int read = 0;
                
                while((read = fin.read(size)) != -1)
                    fos.write(size, 0, read);
                
                fos.flush();
                fos.close();
                fin.close();
                
            }
            catch (Exception e)
            {
                log.debug("FileDownload Exception! : " + e.getMessage());
            }
        }
        else
        {
            log.debug("FileDownload Unknown Error! : " + downFile);
        }
        return null;
    }
    
}
