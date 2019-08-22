<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width" Initial-scale="1">
    <link type="text/css" rel="stylesheet" href="common/lib/css/bootstrap.min.css">
      <link type="text/css" rel="stylesheet" href="common/css/common.css">
<title>게시판</title>
</head>
<%
String WRITE_NO = request.getParameter("WRITE_NO");
String WRITER = request.getParameter("WRITER");
%>
<body>
  <div class="container">
    <h1>글 수정</h1>
    <form action="modify.do?WRITE_NO=<%=WRITE_NO%>&WRITER=<%=WRITER %>" method="post" onsubmit="return formCheck();">
      <div class="form-group">
        <label for="title">제목</label> <input type="text" class="form-control" id="title" name="title">
      </div>
      <div class="form-group">
        <label for="writer">작성자</label> <input type="text" class="form-control" id="writer" name="writer" value="<%=WRITER %>" disabled>
      </div>
      <div class="form-group">
        <label for="content">내용</label>
        <textarea class="form-control" rows="5" id="content" name="content"></textarea>
      </div>
      <input type="hidden" name="write_no" value="<%=WRITE_NO %>"/>
      <input type="submit" />
    </form>
  </div>
</body>
<script type="text/javascript">
  function formCheck()
  {
    var title = document.forms[0].title.value;
    var writer = document.forms[0].writer.value;
    var content = document.forms[0].content.value;

    if(title == null || title == "")
    {
      alert("제목을 입력해주세요.");
      document.forms[0].title.focus();
      return false;
    }
//     if(writer == null || writer == "")
//     {
//       alert();
//       document.forms[0].writer.focus();
//       return false;
//     }
//     else if(writer.match(/^(\w+)@(\w+)[.](\w+)$/ig) == null)
//     {
//       alert();
//       document.forms[0].writer.focus();
//       return false;
//     }
    if(content == null || content == "")
    {
      alert("내용을 입력해주세요.");
      document.forms[0].content.focus();
      return false;
    }
  }
</script>
</html>