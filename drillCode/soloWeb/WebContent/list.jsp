<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
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
        out.println("<br>실행쿼리 >>>>>>> "+query);
        rs = stmt.executeQuery(query);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" Initial-scale="1">
<title>Insert title here</title>
</head>
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
    <%
        while (rs.next())
            {
                out.println("<tr>");
                out.println("<td>" + rs.getString("SEQ_NO") + "</td>");
                out.println("<td>" + rs.getString("TITLE") + "</td>");
                out.println("<td><a href='content.jsp?SEQ_NO=" + rs.getString("SEQ_NO") + "&WRITER=" + rs.getString("WRITER") + "'>" + rs.getString("CONTENT") + "</a></td>");
                out.println("<td>" + rs.getString("WRITER") + "</td>");
                out.println("<td>" + rs.getString("INS_YMDHMS") + "</td>");
                out.println("<td>" + rs.getString("VIEW_CNT") + "</td>");
                out.println("</tr>");
            }
    %>
  </table>
  <a href="write.jsp">글쓰기</a>
  <%
      conn.close();
      }
      catch (Exception e)
      {
          out.println("<br>Oracle Database Connection Something Problem. <hr>");
          out.println("<br>"+e.getMessage());
          e.printStackTrace();
      }
  %>
</body>
</html>