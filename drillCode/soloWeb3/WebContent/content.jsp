<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@page import="biz.domain.board.Board"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width" Initial-scale="1">
  <link type="text/css" rel="stylesheet" href="common/lib/css/bootstrap.min.css">
  <link type="text/css" rel="stylesheet" href="common/css/common.css">
  <title>게시판</title>
</head>
<body>
  <h1>게시글 조회</h1>
  <table class="table table-striped table-hover table-condensed">
    <thead>
      <tr>
        <th>번호</th>
        <td>${board.seq_no}</td>
        <th>작성자</th>
        <td>${board.writer}</td>
        <th>작성일</th>
        <td>${board.reg_date }</td>
        <th>조회수</th>
        <td>${board.view_cnt }</td>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th colspan="2">제목</th>
        <td colspan="6">${board.title }</td>
      </tr>
      <tr>
        <th colspan="2">내용</th>
        <td colspan="6">${board.content}</td>
      </tr>
    </tbody>
  </table>
  <a href="delete.do?WRITE_NO=${board.write_no}&WRITER=${board.writer}">게시글 삭제</a>
  <a href="modify_write.do?WRITE_NO=${board.write_no}&WRITER=${board.writer}">게시글 수정</a>
  <a href="list.do">목록으로</a>
</body>
</html>