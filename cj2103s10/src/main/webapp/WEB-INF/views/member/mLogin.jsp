<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>mLogin.jsp(로그인화면)</title>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<p><br/></p>
<div class="container">
  <h2>회원 로그인</h2>
  <form method="post" class="was-validated">
    <div class="form-group">
      <label for="mid">회원 아이디 :</label>
      <input type="text" class="form-control" name="mid" id="mid" value="${mid}" placeholder="회원 아이디를 입력하세요" required autofocus/>
      <div class="valid-feedback">정확한 아이디를 입력하세요.</div>
      <div class="invalid-feedback">회원 아이디는 필수 입력사항입니다.</div>
    </div>
    <div class="form-group">
      <label for="pwd">비밀번호 :</label>
      <input type="password" class="form-control" name="pwd" id="pwd" placeholder="비밀번호를 입력하세요" required/>
      <div class="valid-feedback">정확한 비밀번호를 입력하세요.</div>
      <div class="invalid-feedback">비밀번호는 필수 입력사항입니다.</div>
    </div>
    <div class="form-group">
	    <button type="submit" class="btn btn-secondary">인증하기</button>&nbsp;
	    <button type="reset" class="btn btn-secondary">취소</button>&nbsp;
	    <button type="button" class="btn btn-secondary" onclick="location.href='${ctp}/';">돌아가기</button>&nbsp;
	    <button type="button" class="btn btn-secondary" onclick="location.href='${ctp}/member/mJoin';">회원가입</button>
	  </div>
  </form>
</div>
<p><br/></p>

</body>
</html>