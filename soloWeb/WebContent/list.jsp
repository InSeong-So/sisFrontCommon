<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" Initial-scale="1">
<link type="text/css" rel="stylesheet" href="common/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
  <h1>게시글</h1>
  <table>
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>내용</th>
      <th>작성자</th>
      <th>작성일</th>
      <th>조회수</th>
    </tr>
    <tr>
<%--       <td><%=idx%></td> --%>
<%--       <td><%=title%></td> --%>
<%--       <td><%=writer%></td> --%>
<%--       <td><%=regDate%></td> --%>
<%--       <td><%=content%></td> --%>
<%--       <td><%=count%></td> --%>
    </tr>
  </table>
  <a href="write.jsp">글쓰기</a>
</body>
</html>