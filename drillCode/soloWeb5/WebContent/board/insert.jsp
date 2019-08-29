<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%
    String title = request.getParameter("title");
    String writer = request.getParameter("writer");
    String content = request.getParameter("content");
    
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
        String query = "INSERT INTO BR0010(SEQ_NO, TITLE, CONTENT, WRITER, REG_DATE, VIEW_CNT) "
        + "VALUES ((SELECT NVL(MAX(SEQ_NO + 1), 1) FROM BR0010 WHERE WRITER = '"
        + writer + "'), '"
        + title + "', '"
        + content + "', '"
        + writer
        + "', SYSDATE, 0)";
        
        out.println(query);
        stmt.executeUpdate(query);
        conn.close();
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        out.println("<script>location.href='list.jsp';</script>");
    }
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width" Initial-scale="1">
      <title>Insert title here</title>
</head>
<body>
</body>
</html>