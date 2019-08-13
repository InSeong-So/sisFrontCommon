package biz.domain.board;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import core.db.DBConnector;

public class BoardDAO extends DBConnector
{
    private Logger log = Logger.getRootLogger();
    
    public static BoardDAO getInstance()
    {
        BoardDAO instance = new BoardDAO();
        instance.setDb();
        return instance;
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList<Board> getBoardList(int page) throws SQLException
    {
        log.debug("excute : getBoardList");
        return (ArrayList<Board>) getDb().queryForList("getBoardList", null, page, 10);
    }
}
