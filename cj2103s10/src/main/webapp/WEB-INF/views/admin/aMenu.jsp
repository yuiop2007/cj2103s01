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
    	parent.location.href="${ctp}/admin/aOut.ad";
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
  <p><a href="${ctp}/admin/aContent.ad" target="content">관리자메뉴</a></p>
  <hr/>
  <p><a href="${ctp}/admin/notify/nList" target="content">공지사항</a></p>
  <hr/>
  <p><a href="${ctp}/admin/aGuest.ad" target="content">방명록</a></p>
  <hr/>
  <p><a href="${ctp}/admin/aMList.ad" target="content">회원관리</a></p>
  <hr/>
  <p><a href="${ctp}/admin/aBoard.ad" target="content">게시판</a></p>
  <hr/>
  <p><a href="${ctp}/admin/reservation/roomList" target="content">객실현황</a></p>
  <p><a href="${ctp}/reservation/reservationStatusViewList" target="content">예약현황</a></p>
  <hr/>
  <p><a href="${ctp}/vote/aVoteList" target="content">인기투표관리</a></p>
  <hr/>
  <p><a href="${ctp}/admin/file/tempDelete" target="content">임시파일삭제</a></p>
  <hr/>
  <p><a href="javascript:logoutCheck()">홈으로</a></p>
  <p><br/></p>
</div>
</body>
</html>