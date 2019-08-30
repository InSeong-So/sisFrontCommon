<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="biz.domain.board.Board"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    request.setCharacterEncoding("UTF-8");
    String VALUE1 = request.getParameter("some_data");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" Initial-scale="1">
<link type="text/css" rel="stylesheet" href="/common/lib/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="/common/css/common.css">
<title>Insert title here</title>
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
  <div class="container">
  <h1>게시글</h1>
  <table class="table table-striped table-hover table-condensed">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>내용</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
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
    </tbody>
  </table>
  <a href="write.jsp">글쓰기</a>
  </div>
  <script type="text/javascript">
  </script>
</body>
</html>