<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@page import="biz.domain.board.Board"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <div class="container">
    <h1>게시글 조회</h1>
    <table class="table table-striped table-condensed">
      <thead>
        <tr>
          <th class="col-md-2">게시글 번호</th>
          <th class="col-md-2">작성자</th>
          <th class="col-md-3">작성자 IP</th>
          <th class="col-md-3">작성일</th>
          <th class="col-md-2">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${board.seq_no}</td>
          <td>${board.writer}</td>
          <td>${board.reg_ip }</td>
          <td>${board.reg_date }</td>
          <td>${board.view_cnt }</td>
        </tr>
      </tbody>
    </table>
    <form action="insert.do" method="post" onsubmit="return formCheck();">
      <div class="form-group">
        <label for="title">제목</label> <input type="text" class="form-control" id="title" name="title" value="${board.title }" disabled />
      </div>
      <div class="form-group">
        <label for="content">내용</label>
        <textarea class="form-control" rows="5" id="content" name="content" disabled>${board.content }</textarea>
      </div>
      <div class="form-group">
        <label for="input_file_nm">첨부파일</label>
        <input type="text" class="form-control" id="input_file_nm" name="input_file_nm" value="${board.file_nm }" disabled />
        <input type="button" class="btn btn-info" value="다운로드" onclick="onDownload('${board.write_no}', '${board.writer}')"/>
      </div>
    </form>
    <iframe id="iframe01" style ="position:absolute;z-index:1;visibility:hidden;"></iframe>
    <a href="delete.do?WRITE_NO=${board.write_no}&WRITER=${board.writer}">게시글 삭제</a> <a href="modify_write.jsp?WRITE_NO=${board.write_no}&WRITER=${board.writer }&TITLE=${board.title }&CONTENT=${board.content }&FILE_NM=${board.file_nm }">게시글 수정</a> <a href="list.do">목록으로</a>
  </div>
</body>
<script type="text/javascript">
  function onDownload(write_no, writer)
  {
    var o = document.getElementById("iframe01");
    o.src = "download.do?WRITE_NO=" + write_no + "&WRITER=" + writer;
  }
</script>
</html>