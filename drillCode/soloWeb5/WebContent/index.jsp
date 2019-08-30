<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" Initial-scale="1">
<link type="text/css" rel="stylesheet" href="common/lib/css/bootstrap.min.css">
<title>Jsp 게시판</title>
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
        <li><a href="#" onclick="javascript:page_move('/board/list.do', '001');">게시판</a></li>
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
  <!-- 로긴폼 -->
  <div class="container">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
      <!-- 점보트론 -->
      <div class="jumbotron" style="padding-top: 20px;">
        <!-- 로그인 정보를 숨기면서 전송post -->
        <form method="post" action="loginAction.jsp">
          <h3 style="text-align: center;">로그인화면</h3>
          <div class="form-group">
            <input type="text" class="form-control" placeholder="아이디" name="userId" maxlength="20">
          </div>
          <div class="form-group">
            <input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">
          </div>
          <input type="submit" class="btn btn-primary form-control" value="로그인">
        </form>
      </div>
    </div>
  </div>
  <!-- 애니매이션 담당 JQUERY -->
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <!-- 부트스트랩 JS  -->
  <script src="common/lib/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  function page_move(url, some_data)
  {
    var form = document.createElement("form");
    var param = new Array();
    var input = new Array();

    var cnt = 0;
    
    form.action = url;
    form.method = "post";

    param.push( ['some_key1', 'some_value1'] );
    param.push( ['some_key2', 'some_value2'] );
    param.push( ['some_data', some_data] );

    for (var i = 0; i < param.length; i++) {
        input[i] = document.createElement("input");
        input[i].setAttribute("type", "hidden");
        input[i].setAttribute("name", param[i][0]);
        input[i].setAttribute("value", param[i][1]);
        form.appendChild(input[i]);
        cnt++;
    }
    document.body.appendChild(form);
    
    form.submit();
  }
  </script>
</body>
</html>