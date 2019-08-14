<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" Initial-scale="1">
<title>Insert title here</title>
</head>
<body>
  <form action="insert.do" method="post" onsubmit="return formCheck();">
    <span>제목</span><input type="text" name="title" /><br>
    <span>작성자</span><input type="text" name="writer" /><br>
    <span>내용</span><textarea rows="10" cols="20" name="content"></textarea><br>
    <input type="submit"/>
  </form>
</body>
<script type="text/javascript">
function formCheck()
{
  var title = document.forms[0].title.value;
//   var writer = document.forms[0].writer.value;
  var content = document.forms[0].content.value;
  
  if(title == null || title == "")
  {
    alert("제목을 입력해주세요.");
    document.forms[0].writer.focus();
    return false;
  }
  
  if(content == null || content == "")
  {
    alert("내용을 입력해주세요.");
    document.forms[0].content.focus();
    return false;
  }
}

</script>
</html>