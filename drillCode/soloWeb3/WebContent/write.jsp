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
  <div class="container">
    <form action="insert.do" method="post" enctype="multipart/form-data" onsubmit="return formCheck();">
      <div class="form-group">
        <label for="title">제목</label>
        <input type="text" class="form-control" id="title" name="title">
      </div>
      <div class="form-group">
        <label for="writer">작성자</label>
        <input type="text" class="form-control" id="writer" name="writer">
      </div>
      <div class="form-group">
        <label for="content">내용</label>
        <textarea class="form-control" rows="5" id="content" name="content"></textarea>
      </div>
<!--       <div class="filebox form-group"> -->
<!--         <input class="upload-name" value="파일선택" disabled="disabled"> -->
<!--         <label for="ex_filename">업로드</label> -->
<!--         <input type="file" id="ex_filename" class="upload-hidden"> -->
<!--       </div> -->
      <div class="form-group filebox bs3-primary preview-image">
        <input type="text" id="input_file_nm" name="input_file_nm" class="form-control upload-name" value="파일명" disabled="disabled" style="width: 200px;">
        <label for="input_file">업로드</label> 
        <input type="file" id="input_file" name="input_file" class="form-control upload-hidden"> 
      </div>
      <div class="form-group">
        <div class="col-2">
          <button type="submit" class="btn btn-default">작성</button>
        </div>
      </div>
    </form>
  </div>
  <!-- Jquery -->
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <!-- bootstrap.min.js  -->
  <script src="common/lib/js/bootstrap.min.js"></script>
  <!-- common.js  -->
  <script src="common/js/common.js"></script>
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