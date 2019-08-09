<%@page import="domain.board.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" Initial-scale="1">
<title>Insert title here</title>
</head>
<%
    Connection conn = null;
    try
    {
        String jdbcUrl = "jdbc:oracle:thin:@10.66.1.104:1522:GRSEHR";
        String dbId = "GRSEHR";
        String dbPass = "GRS$EHR!11";
        ResultSet rs = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
        out.println("제대로 연결되었습니다.");
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM BR0010 ORDER BY SEQ_NO DESC";
        out.println("<br>실행쿼리 >>>>>>> " + query);
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
        out.println("<br>Oracle Database Connection Something Problem. <hr>");
        out.println("<br>" + e.getMessage());
        e.printStackTrace();
    }
%>
<body>
  <h1>게시글</h1>
  <table>
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>내용</th>
      <th>작성자</th>
      <th>작성일</th>
      <th>조회수</th>
    </tr>
    <c:forEach items="${boardList }" var="board">
      <tr>
        <td>${board.seq_no }</td>
        <td><a href="content.jsp?SEQ_NO=${board.seq_no}&WRITER=${board.writer}">${board.title }</a></td>
        <td>${board.content }</td>
        <td>${board.writer }</td>
        <td>${board.reg_date }</td>
        <td>${board.view_cnt }</td>
      </tr>
    </c:forEach>
  </table>
  <a href="write.jsp">글쓰기</a>
</body>
</html>