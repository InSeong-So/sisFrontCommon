<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" Initial-scale="1">
<title>bootstrap template</title>
<link rel="stylesheet" href="../common/lib/css/bootstrap.min.css">
<link rel="stylesheet" href="../common/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="../common/css/style.css"> -->
</head>
<body>
  <article class="container">
    <div class="col-md-12">
      <div class="page-header">
        <h1>회원가입 <small>horizontal form</small></h1>
      </div>
      <form class="form-horizontal" action="/main/resistration.do" method="post">
        <div class="form-group">
          <label class="col-sm-3 control-label" for="userId">아이디</label>
          <div class="col-sm-6">
            <input class="form-control" id="userId" name="userId" type="text" placeholder="아이디">
          </div>
        </div>
        <!--************************************************************************************************************************-->
        <div class="form-group">
          <label class="col-sm-3 control-label" for=userPassword>비밀번호</label>
          <div class="col-sm-6">
            <input class="form-control" id="userPassword" name="userPassword" type="password" placeholder="비밀번호">
            <p class="help-block">숫자, 특수문자 포함 8자 이상</p>
          </div>
        </div>
        <!--************************************************************************************************************************-->
        <div class="form-group">
          <label class="col-sm-3 control-label" for="userPasswordCheck">비밀번호  확인</label>
          <div class="col-sm-6">
            <input class="form-control" id="userPasswordCheck" name="userPasswordCheck" type="password" placeholder="비밀번호 확인">
            <p class="help-block">비밀번호를 한번 더 입력해주세요.</p>
          </div>
        </div>
        <!--************************************************************************************************************************-->
        <div class="form-group">
          <label class="col-sm-3 control-label" for="userName">이름</label>
          <div class="col-sm-6">
            <input class="form-control" id="userName" name="userName" type="text" placeholder="이름">
          </div>
        </div>
        <!--************************************************************************************************************************-->
        <div class="form-group">
          <label class="col-sm-3 control-label" for="userBirthday">생년월일</label>
          <div class="col-sm-6">
            <input class="form-control" id="userBirthday" name="userBirthday" type="text" placeholder="생년월일">
          </div>
        </div>
        <!--************************************************************************************************************************-->
        <div class="form-group">
          <label class="col-sm-3 control-label" for="userPhoneNumber">전화번호</label>
          <div class="col-sm-6">
            <input type="tel" class="form-control" id="userPhoneNumber" name="userPhoneNumber" placeholder="전화번호" />
          </div>
        </div>
        <!--************************************************************************************************************************-->
        <div class="form-group">
          <label class="col-sm-3 control-label" for="userEmail">이메일 주소</label>
          <div class="col-sm-6">
            <div class="input-group">
              <input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="이메일" /> <span class="input-group-btn">
                <button class="btn btn-success"> 인증번호 전송<i class="fa fa-mail-forward spaceLeft"></i>
                </button>
              </span>
            </div>
          </div>
        </div>
        <!--************************************************************************************************************************-->
        <div class="form-group">
          <label class="col-sm-3 control-label" for="userEmailCheck">인증번호  확인</label>
          <div class="col-sm-6">
            <div class="input-group">
              <input class="form-control" id="userEmailCheck" name="userEmailCheck" type="text" placeholder="인증번호"> <span
                class="input-group-btn">
                <button class="btn btn-success" type="button">인증번호 확인<i class="fa fa-edit spaceLeft"></i>
                </button>
              </span>
            </div>
            <p class="help-block">전송된 인증번호를 입력해주세요.</p>
          </div>
        </div>
        <!--************************************************************************************************************************-->
        <!--
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="inputAgree">약관 동의</label>
                    <div class="col-sm-6" data-toggle="buttons">
                        <label class="btn btn-warning active">
                            <input id="agree" type="checkbox" autocomplete="off" chacked>
                            <span class="fa fa-check"></span>
                        </label>
                    <a href="#">이용약관</a> 에 동의 합니다.
                    </div>
                </div>
-->
        <!--************************************************************************************************************************-->
        <div class="form-group">
          <div class="col-sm-12 text-center">
            <button class="btn btn-primary" type="submit">회원가입<i class="fa fa-check spaceLeft"></i>
            </button>
            <button class="btn btn-danger">가입취소<i class="fa fa-times spaceLeft"></i>
            </button>
          </div>
        </div>
      </form>
      <hr>
    </div>
  </article>
  <!-- 애니매이션 담당 JQUERY -->
  <script src="../common/lib/js/jquery-3.4.1.min.js"></script>
  <!-- 부트스트랩 JS  -->
  <script src="../common/lib/js/bootstrap.min.js"></script>
</body>
</html>