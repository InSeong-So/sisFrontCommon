<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="biz.domain.board.Board"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" Initial-scale="1">
<link type="text/css" rel="stylesheet" href="common/lib/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="common/css/common.css">
<title>Insert title here</title>
</head>
<body>
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
            <td><a href="count.do?WRITE_NO=${board.write_no}&WRITER=${board.writer}">${board.title }</a></td>
            <td>${board.content }</td>
            <td>${board.writer }</td>
            <td>${board.reg_date }</td>
            <td>${board.view_cnt }</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <input type="hidden" name="page" id="page" value="${page}">
    <a href="#" onclick="loadNextPage()">더보기</a>
    <a href="write.jsp">글쓰기</a>
  </div>
  <!-- Jquery -->
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</body>
<script type="text/javascript">
  function loadNextPage()
  {
    var page = $('#page').val();
    page = parseInt(page);
    page += 10;
    $.ajax({
      type : 'post',
      url : 'ajaxList.do',
      data : ({
        page : page
      }),
      success : function(data)
      {
        $('tbody').append(data);
        $('#page').val(page);
      }
    });
  }
</script>
</html>