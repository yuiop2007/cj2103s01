<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<%
  int level = session.getAttribute("slevel")==null ? 99 : (int) session.getAttribute("slevel");
%>
<head>
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">

</head>
<script>
$(document).ready(function () {
	var level = "<c:out value='${level}'/>";
	var menu = $('.nav1');
    var origOffsetY = menu.offset().top;
    function scroll() {
        if ($(window).scrollTop() >= origOffsetY) {
            $(menu).addClass('fixed-top');
        } else {
            $(menu).removeClass('fixed-top');
        }
    }
    document.onscroll = scroll;
});
</script>
	<nav class="nav1 navbar navbar-expand-sm bg-light justify-content-center">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="${ctp}/product/proShow?cate=ALL">ALL</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">NEW</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${ctp}/product/proShow?cate=MADE">MADE</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${ctp}/product/proShow?cate=OUTER">OUTER</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${ctp}/product/proShow?cate=TOP">TOP</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${ctp}/product/proShow?cate=BOTTOM">BOTTOM</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${ctp}/product/proShow?cate=ACC">ACC</a>
			</li>
		</ul>
	</nav>

	<nav class="nav2 navbar navbar-expand-sm bg-light justify-content-center">
		<ul class="navbar-nav">
<%    if(level == 99) { %>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/member/login">Login </a>
			</li>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/member/join">join </a>
			</li>
<%    } else { %>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/member/logout">Logout </a>
			</li>
<% } %>		
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/member/mypage">Mypage </a>
			</li>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/order/cartList">Cart 0 </a>
			</li>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/board/notice">Notice</a>
			</li>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/board/qna">Q&A</a>
			</li>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/board/review">Review</a>
			</li>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/board/event">Event</a>
			</li>      
			<% if(level == 0) { %>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/admin/admin">Manager</a>
			</li>      
			<% } %>
		</ul>
	</nav>