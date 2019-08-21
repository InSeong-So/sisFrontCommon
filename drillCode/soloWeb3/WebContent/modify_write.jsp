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
<body>
  <h1>modify This</h1>
  <form action="modify.do?WRITE_NO=<%=WRITE_NO%>" method="post" onsubmit="return formCheck();">
    제목: <input type="text" name="title" /> <br /> 작성자: <input type="text" name="writer" /> <br /> 내용:
    <textarea rows="10" cols="20" name="content" />
    </textarea>
    <br /> <input type="submit" />
  </form>
</body>
<script type="text/javascript">
  function formCheck()
  {

    var title = document.forms[0].title.value;
    var writer = document.forms[0].writer.value;
    var content = document.forms[0].content.value;

    if(title == null || title == "")
    {
      alert();
      document.forms[0].title.focus();
      return false;
    }
    if(writer == null || writer == "")
    {
      alert();
      document.forms[0].writer.focus();
      return false;
    }
    else if(writer.match(/^(\w+)@(\w+)[.](\w+)$/ig) == null)
    {
      alert();
      document.forms[0].writer.focus();
      return false;
    }
    if(content == null || content == "")
    {
      alert();
      document.forms[0].content.focus();
      return false;
    }
  }
</script>
</html>