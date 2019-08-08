<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%
    int idx = 1;
    String title = request.getParameter("title");
    String writer = request.getParameter("writer");
    String regDate = request.getParameter("regDate");
    String content = request.getParameter("content");
    int count = 90;
    
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
        String query = "SELECT * FROM VR_PA1010 WHERE C_CD = '10' AND STAT_CD LIKE '1%'";
        stmt.executeUpdate(query);
        
        ResultSet rs = stmt.getResultSet();
        
        while (rs.next())
        {
            out.println(rs.getString(2) + "\n");
        }
        
        conn.close();
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width" Initial-scale="1">
    <link type="text/css" rel="stylesheet" href="common/css/bootstrap.min.css">
      <title>Insert title here</title>
</head>
<body>
</body>
</html>