<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<%
  int level = session.getAttribute("slevel")==null ? 99 : (int) session.getAttribute("slevel");
	//String atom = request.getParameter("atom");
%>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/css.css">

</head>
<script>
$(document).ready(function () {
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
				<a class="nav-link" href="#">ALL</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">NEW</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">MADE</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">OUTER</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">TOP</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">BOTTOM</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">ACC</a>
			</li>
		</ul>
	</nav>
	<nav class="nav2 navbar navbar-expand-sm bg-light justify-content-center">
		<ul class="navbar-nav">
<%    if(level == 99) { %>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/login">Login </a>
			</li>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/join">join </a>
			</li>
<%    } else { %>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/logout">Logout </a>
			</li>
<% } %>		
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/mypage">Mypage </a>
			</li>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/notice">Notice</a>
			</li>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/qna">Q&A</a>
			</li>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/review">Review</a>
			</li>
			<li class="nav-item2">
				<a class="nav-link" href="${ctp}/event">Event</a>
			</li>      
		</ul>
	</nav>