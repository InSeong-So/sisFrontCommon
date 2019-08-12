package biz.domain.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import core.db.DBConnector;

public class BoardDAO extends DBConnector
{
    private Logger log = Logger.getRootLogger();
    
    public static BoardDAO instance = new BoardDAO();
    
    public static BoardDAO getInstance()
    {
        return instance;
    }
    
    public ArrayList<Board> getBoardList() throws SQLException
    {
        String query = "SELECT * FROM BR0010 ORDER BY SEQ_NO DESC";
        log.debug("Excute Query : " + query);
        ResultSet rs = openConnection().executeQuery(query);
        
        ArrayList<Board> boardList = new ArrayList<Board>();
        
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
        
        closeConnection();
        
        return boardList;
    }
}
