<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>mPwdSearch.jsp</title>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<p><br/></p>
<div class="container">
  <h2>비밀번호 찾기</h2>
  <p>(아이디와 가입시에 등록한 메일주소를 입력하세요.)</p>
  <form method="post" class="was-validated">
    <div class="form-group">
      <label for="mid">회원 아이디 :</label>
      <input type="text" class="form-control" name="mid" id="mid" placeholder="회원 아이디를 입력하세요" required autofocus/>
      <div class="valid-feedback">정확한 아이디를 입력하세요.</div>
      <div class="invalid-feedback">회원 아이디는 필수 입력사항입니다.</div>
    </div>
    <div class="form-group">
      <label for="pwd">메일주소 :</label>
      <input type="text" class="form-control" name="toMail" id="toMail" placeholder="메일주소를 입력하세요" required/>
      <div class="valid-feedback">정확한 메일주소를 입력하세요.</div>
      <div class="invalid-feedback">메일주소는 필수 입력사항입니다.</div>
    </div>
    <div class="form-group">
	    <button type="submit" class="btn btn-secondary">임시비밀번호발급</button>&nbsp;
	    <button type="reset" class="btn btn-secondary">취소</button>&nbsp;
	    <button type="button" class="btn btn-secondary" onclick="location.href='${ctp}/';">돌아가기</button>&nbsp;
	  </div>
  </form>
</div>
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>