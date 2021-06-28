<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>aMenu.jsp</title>
  <%@ include file="/WEB-INF/views/include/bs.jsp" %>
  <script>
    function logoutCheck() {
    	parent.location.href="${ctp}/aOut.ad";
    }
  </script>
  <style>
    body {background-color:#eee}
  </style>
</head>
<body>
<div class="container">
  <p><br/></p>
  <h4>관리자메뉴</h4>
  <hr/>
  <p><a href="${ctp}/aContent.ad" target="content">관리자메뉴</a></p>
  <hr/>
  <p><a href="${ctp}/.ad" target="content">방명록</a></p>
  <hr/>
  <p><a href="${ctp}/aMList.ad" target="content">회원관리</a></p>
  <hr/>
  <p><a href="${ctp}/.ad" target="content">게시판</a></p>
  <hr/>
  <p><a href="javascript:logoutCheck()">로그아웃</a></p>
  <p><br/></p>
</div>
</body>
</html>