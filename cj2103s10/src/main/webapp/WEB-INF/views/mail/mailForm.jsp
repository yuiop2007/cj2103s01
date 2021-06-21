<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <meta charset="UTF-8">
  <title>mailForm.jsp</title>
</head>
<body>
<%@ include file = "/WEB-INF/views/include/nav.jsp" %>
<%@ include file = "/WEB-INF/views/include/slide.jsp" %>
<p><br/></p>
<div class="container">
  <h2>메 일 보 내 기</h2>
  <p>받는 사람의 메일 주소를 정확히 입력하셔야 합니다.</p>
  <form name="myform" method="post" class="was-validated">
    <div class="form-group">
      <label for="toMail">받는사람 메일주소 : </label>
      <input type="text" class="form-control" id="toMail" name="toMail" placeholder="받는사람 메일주소를 입력하세요." required autofocus/>
      <div class="valid-feedback">정확한 메일주소를 입력하세요</div>
      <div class="invalid-feedback">받는사람 메일주소는 필수 입력항목입니다.</div>
    </div>
    <div class="form-group">
      <label for="title">메일 제목 : </label>
      <input type="text" class="form-control" id="title" name="title" placeholder="메일 제목을 입력하세요." required/>
      <div class="valid-feedback">메일 제목을 입력하세요</div>
      <div class="invalid-feedback">메일제목은 필수 입력항목입니다.</div>
    </div>
    <div class="form-group">
      <label for="content">메일 내용 : </label>
      <textarea rows="7" class="form-control" id="content" name="content" required></textarea>
      <div class="valid-feedback">메일 내용을 입력하세요</div>
      <div class="invalid-feedback">메일 내용은 필수 입력항목입니다.</div>
    </div>
    <button type="submit" class="btn btn-secondary">메일보내기</button>
    <button type="reset" class="btn btn-secondary">다시쓰기</button>
    <button type="button" class="btn btn-secondary" onclick="location.href='${ctp}/';">돌아가기</button>
  </form>
</div>
<%@ include file = "/WEB-INF/views/include/footer.jsp" %>
</body>
</html>