<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>aContent.jsp</title>
  <%@ include file="/WEB-INF/views/include/bs.jsp" %>
</head>
<body>
<div class="container">
  <p><br/></p>
  <h2>이곳은 관리자 메뉴 화면입니다.</h2>
  <hr/>
  <h4>최근 새로 올라온 글입니다.</h4>
  <hr/>
  <p>방명록(최근5일) : ______건</p>
  <hr/>
  <p>새로 가입한 회원(준회원) : <a href="${ctp}/aMList.ad?level=1">${newMemberCount}</a>명</p>
  <hr/>
  <p>탈퇴 신청한 회원 : ${delMemberCount}명</p>
  <hr/>
  <p><br/></p>
</div>
</body>
</html>