<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    request.setCharacterEncoding("UTF-8");
    String SEQ_NO = request.getParameter("SEQ_NO");
    String WRITER = request.getParameter("WRITER");
    
    Connection conn = null;
    try
    {
        String jdbcUrl = "jdbc:oracle:thin:@10.66.1.104:1522:GRSEHR";
        String dbId = "GRSEHR";
        String dbPass = "GRS$EHR!11";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
        out.println("제대로 연결되었습니다.");
        ResultSet rs = null;
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM BR0010 WHERE SEQ_NO=" + SEQ_NO + " AND WRITER='" + WRITER + "'";
        
        out.print("<br>실행 쿼리 >>>>>> " + query);
        
        rs = stmt.executeQuery(query);
        
        while (rs.next())
        {
            request.setAttribute("SEQ_NO", rs.getString("SEQ_NO"));
            request.setAttribute("WRITER", rs.getString("WRITER"));
            request.setAttribute("REG_DATE", rs.getString("REG_DATE"));
            request.setAttribute("VIEW_CNT", rs.getString("VIEW_CNT"));
            request.setAttribute("TITLE", rs.getString("TITLE"));
            request.setAttribute("CONTENT", rs.getString("CONTENT"));
        }
        conn.close();
    }
    catch (Exception e)
    {
        out.print("<br>Oracle Database Connection Failed! <hr>");
        out.print("<br>" + e.getMessage());
        e.printStackTrace();
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width" Initial-scale="1">
    <title>Insert title here</title>
</head>
<body>
  <h1>게시글 조회</h1>
  <table border="1">
    <tr>
      <th>번호</th>
      <td>${SEQ_NO}</td>
      <th>작성자</th>
      <td>${WRITER}%></td>
      <th>작성일</th>
      <td>${REG_DATE}%></td>
      <th>조회수</th>
      <td>${VIEW_CNT}</td>
    </tr>
    <tr>
      <th colspan="2">제목</th>
      <td colspan="6">${TITLE}</td>
    </tr>
    <tr>
      <th colspan="2">내용</th>
      <td colspan="6">${CONTENT}</td>
    </tr>
  </table>
  <a href="delete.jsp?SEQ_NO=${SEQ_NO}&WRITER=${WRITER}">게시글 삭제</a>
  <a href="modify_write.jsp?SEQ_NO=${SEQ_NO}&WRITER=${WRITER}">게시글 삭제</a>
  <a href="list.jsp">목록으로</a>
</body>
</html>