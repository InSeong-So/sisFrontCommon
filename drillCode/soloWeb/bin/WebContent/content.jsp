<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
  request.setCharacterEncoding("UTF-8");
  String SEQ_NO = request.getParameter("SEQ_NO");
  String WRITER = request.getParameter("WRITER");
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
    Class.forName("oracle.jdbc.driver.OracleDriver");
    conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
    out.println("제대로 연결되었습니다.");
    ResultSet rs = null;
    Statement stmt = conn.createStatement();
    String query = "SELECT * FROM BR0010 WHERE SEQ_NO=" + SEQ_NO + " AND WRITER='" + WRITER + "'";
    
    out.print("<br>실행 쿼리 >>>>>> " + query);
    
    rs = stmt.executeQuery(query);
    
    while(rs.next())
    {
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width" Initial-scale="1">
<title>Insert title here</title>
</head>
<body>
  <h1>게시글</h1>
  <table border="1">
    <tr>
      <th>번호</th>
      <td><%=rs.getString("SEQ_NO") %></td>
      <th>작성자</th>
      <td><%=rs.getString("WRITER") %></td>
      <th>작성일</th>
      <td><%=rs.getString("INS_YMDHMS") %></td>
      <th>조회수</th>
      <td><%=rs.getString("VIEW_CNT") %></td>
    </tr>
    <tr>
      <th colspan="2">제목</th>
      <td colspan="6"><%=rs.getString("TITLE") %></td>
    </tr>
    <tr>
      <th colspan="2">내용</th>
      <td colspan="6"><%=rs.getString("CONTENT") %></td>
    </tr>
  </table>
  <a href="delete.jsp?SEQ_NO=<%=rs.getString("SEQ_NO") %>&WRITER=<%=rs.getString("WRITER") %>">게시글 삭제</a>
  <a href="list.jsp">목록으로</a>
</body>
<%
      }
      conn.close();
    }
    catch(Exception e)
    {
        out.print("<br>Oracle Database Connection Failed! <hr>");
        out.print("<br>"+e.getMessage());
        e.printStackTrace();
    }
%>
</html>