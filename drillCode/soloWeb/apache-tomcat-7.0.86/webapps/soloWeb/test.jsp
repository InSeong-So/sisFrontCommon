<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<h2>JDBC드라이버 테스트</h2>
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
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
%>