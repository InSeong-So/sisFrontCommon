package biz.domain.board;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import core.db.SQLUtil;

public class BoardDAO
{
    private static Logger log = Logger.getRootLogger();
    
    public static ArrayList<Board> getBoardList() throws SQLException
    {
        String query = "SELECT * FROM BR0010 ORDER BY SEQ_NO DESC";
        
        ArrayList<Board> boardList = new ArrayList<Board>();
        
        log.debug("Excute Query : " + query);
        
        try
        {
            CallableStatement cs = SQLUtil.getConnection("GRSEHR").prepareCall(query);
            ResultSet rs = cs.executeQuery();
            
            while (rs.next())
            {
                Board board = new Board();
                
                board.setSeq_no(rs.getInt("SEQ_NO"));
                board.setTitle(rs.getString("TITLE"));
                board.setContent(rs.getString("CONTENT"));
                board.setWriter(rs.getString("WRITER"));
                board.setReg_date(rs.getString("REG_DATE"));
                board.setView_cnt(rs.getInt("VIEW_CNT"));
                boardList.add(board);
            }
            
            cs.close();
        }
        catch (Exception e)
        {
            log.debug("에러ㅠㅠ : " + e);
            throw new SQLException();
        }
        
        return boardList;
    }
}
