<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/views/include/bs.jsp" %>
<script>
  function mDeleteCheck() {
	  var ans = confirm("정말 탈퇴 하시겠습니까?");
	  if(ans) {
		  ans = confirm("탈퇴하시게되면 1개원간 같은 아이디로 재가입 불가입니다.\n탈퇴 하시겠습니까?");
		  if(ans) location.href = "${ctp}/member/mDelete?mid=${smid}";
	  }
  }
</script>
<!-- Navbar -->
<div class="w3-top">
  <div class="w3-bar w3-black w3-card">
    <a class="w3-bar-item w3-button w3-padding-large w3-hide-medium w3-hide-large w3-right" href="javascript:void(0)" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    <a href="${ctp}/" class="w3-bar-item w3-button w3-padding-large">HOME</a>
    <a href="${ctp}/guest/gList" class="w3-bar-item w3-button w3-padding-large w3-hide-small">Guest</a>
    <a href="${ctp}/board/bList" class="w3-bar-item w3-button w3-padding-large w3-hide-small">Board</a>
    <c:if test="${slevel <= 4}">
	    <a href="${ctp}/pds/pList" class="w3-bar-item w3-button w3-padding-large w3-hide-small">Pds</a>
	    <div class="w3-dropdown-hover w3-hide-small">
	      <button class="w3-padding-large w3-button" title="More">Study <i class="fa fa-caret-down"></i></button>     
	      <div class="w3-dropdown-content w3-bar-block w3-card-4">
	        <a href="#" class="w3-bar-item w3-button">Calendar</a>
	        <a href="${ctp}/study/woo" class="w3-bar-item w3-button">우편번호API</a>
	        <a href="${ctp}/study/kakao" class="w3-bar-item w3-button">카카오지도API</a>
	        <a href="#" class="w3-bar-item w3-button">미니쇼핑몰</a>
	      </div>
	    </div>
	    <div class="w3-dropdown-hover w3-hide-small">
	      <button class="w3-padding-large w3-button" title="More">${snickname} <i class="fa fa-caret-down"></i></button>     
	      <div class="w3-dropdown-content w3-bar-block w3-card-4">
	        <a href="${ctp}/mail/mailForm" class="w3-bar-item w3-button">메일보내기</a>
	        <a href="${ctp}/member/mUpdateCheck" class="w3-bar-item w3-button">정보수정</a>
	        <a href="javascript:mDeleteCheck();" class="w3-bar-item w3-button">회원탈퇴</a>
	        <c:if test="${slevel == 0}">
	          <a href="${ctp}/admin/aCheck" class="w3-bar-item w3-button">관리자메뉴</a>
	        </c:if>
	      </div>
	    </div>
	  </c:if>
    <c:if test="${empty slevel}">
      <a href="${ctp}/member/mLogin" class="w3-bar-item w3-button w3-padding-large w3-hide-small">Login</a>
      <a href="${ctp}/member/mJoin" class="w3-bar-item w3-button w3-padding-large w3-hide-small">Join</a>
    </c:if>
    <c:if test="${!empty slevel}">
      <a href="${ctp}/member/mLogout" class="w3-bar-item w3-button w3-padding-large w3-hide-small">Logout</a>
    </c:if>
    <a href="javascript:void(0)" class="w3-padding-large w3-hover-red w3-hide-small w3-right"><i class="fa fa-search"></i></a>
  </div>
</div>

<!-- Navbar on small screens (remove the onclick attribute if you want the navbar to always show on top of the content when clicking on the links) -->
<div id="navDemo" class="w3-bar-block w3-black w3-hide w3-hide-large w3-hide-medium w3-top" style="margin-top:46px">
  <a href="${ctp}/guest/gList" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">Guest</a>
  <a href="${ctp}/board/bList" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">Board</a>
  <a href="${ctp}/pds/pList" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">Pds</a>
  <a href="#" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">Study</a>
</div>
