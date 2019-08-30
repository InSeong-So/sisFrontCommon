<%@page import="core.util.CommonProperties"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="biz.domain.board.Board"%>
<%@page import="org.apache.log4j.Logger"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
Logger log = Logger.getRootLogger();
CommonProperties commProp = CommonProperties.getInstance();
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String httpVersion = request.getProtocol();
if (httpVersion.equals("HTTP/1.1"))
{
  response.setHeader("cache-control","no-cache,no-store");
  response.setHeader("X-Frame-Options", "SAMEORIGIN");
  response.setHeader("X-Content-Type-Options","nosniff");
  response.setHeader("X-XSS-Protection", "1; mode=block");
}
else if(httpVersion.equals("HTTP/1.0"))
{
  response.setHeader("expires","-1");
  response.setHeader("Pragma","no-cache,no-store");
  response.setHeader("X-Frame-Options", "SAMEORIGIN");
  response.setHeader("X-Content-Type-Options","nosniff");
  response.setHeader("X-XSS-Protection", "1; mode=block");
}
String TO_YMD = FormatUtil.getYmd(request);
String TO_YY = TO_YMD.substring(0, 4);
String TO_YM = TO_YMD.substring(0, 6);
String TO_MM = TO_YMD.substring(4, 6);
String TO_DD = TO_YMD.substring(6, 8);
String RD_WEBROOT = commProp.getProperty("RD_WEBROOT");

log.debug("requestURI: "+request.getRequestURI());

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" Initial-scale="1">
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="../css/common.css">
<title>::::</title>
</head>
<body>
  <!-- 네비게이션  -->
  <nav class="navbar navbar-default">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expaned="false">
        <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="main.jsp">JSP 게시판</a>
    </div>
    <div class="collapse navbar-collapse" id="#bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="main.jsp">메인</a></li>
        <li><a href="#" onclick="javascript:page_move('/list.do', 'foobar');">게시판</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">접속하기<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li class="active"><a href="login.jsp">로그인</a></li>
            <li><a href="join.jsp">회원가입</a></li>
          </ul></li>
      </ul>
    </div>
  </nav>
  <!-- Jquery -->
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <!-- bootstrap.min.js  -->
  <script src="common/lib/js/bootstrap.min.js"></script>
  <!-- common.js  -->
  <script src="common/js/common.js"></script>
</body>
</html>