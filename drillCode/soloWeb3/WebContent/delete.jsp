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
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
        out.println("제대로 연결되었습니다.");
        Statement stmt = conn.createStatement();
        String query = "DELETE FROM BR0010 WHERE SEQ_NO=" + SEQ_NO + " AND WRITER='" + WRITER + "'";
        out.print("<br>실행 쿼리 >>>>>> " + query);
        stmt.executeQuery(query);
        
        conn.close();
%>
<script type="text/javascript">
console.log("<%=query%>");
console.log("<%=SEQ_NO%>");
console.log("<%=WRITER%>");
  alert("게시글이 삭제되었습니다.");
  location.href = "redirect.jsp";
</script>
<%
    }
    catch (Exception e)
    {
        out.print("<br>Oracle Database Connection Failed! <hr>");
%>
<script type="text/javascript">
  alert("오류가 발생하였습니다.");
  location.href = "redirect.jsp";
</script>
<%
    out.print("<br>" + e.getMessage());
        e.printStackTrace();
    }
%>
<body>
</body>
</html>