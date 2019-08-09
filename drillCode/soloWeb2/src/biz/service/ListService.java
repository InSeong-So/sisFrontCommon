package biz.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import biz.controller.MainAction;
import biz.domain.board.Board;

public class ListService implements MainAction
{
    private Logger log = Logger.getRootLogger();
    
    @Override
    public String sisAction(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        Connection conn = null;
        try
        {
            String jdbcUrl = "jdbc:oracle:thin:@10.66.1.104:1522:GRSEHR";
            String dbId = "GRSEHR";
            String dbPass = "GRS$EHR!11";
            ResultSet rs = null;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
            System.out.println("제대로 연결되었습니다.");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM BR0010 ORDER BY SEQ_NO DESC";
            System.out.println("<br>실행쿼리 >>>>>>> " + query);
            rs = stmt.executeQuery(query);
            
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
            
            request.setAttribute("boardList", boardList);
            
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("<br>Oracle Database Connection Something Problem. <hr>");
            System.out.println("<br>" + e.getMessage());
            e.printStackTrace();
        }
        
        return "list.jsp";
    }
    
}
