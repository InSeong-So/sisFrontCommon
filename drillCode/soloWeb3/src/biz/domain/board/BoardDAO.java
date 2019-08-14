package biz.domain.board;

import java.sql.SQLException;
import java.util.ArrayList;

import core.db.DBConnector;

public class BoardDAO extends DBConnector
{
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
    
    public Board getContent(Board board) throws SQLException
    {
        return (Board) getDb().queryForObject("getContent", board);
    }
    
    public void insertBoard(Board board) throws SQLException
    {
        log.debug("excute : insertBoard");
        getDb().insert("insertBoard", board);
    }
    
    public void deleteBoard(Board board) throws SQLException
    {
        getDb().delete("insertBoard", board);
    }
}
