<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<jsp:include page="/WEB-INF/views/include/bs.jsp"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
	</div>
	<div class="container">
		<form name="loginform" method="post" action="${ctp}/member/login">
			<h6>로그인</h6>
			<hr />
			<table>
				<tr>
					<td class="ftd">아이디</td>
					<td><input type="text" id="mid" name="mId" maxlength="16"></td>
				</tr>
				<tr>
					<td class="ftd">비밀번호</td>
					<td><input type="password" id="pwd" name="mPwd" maxlength="16" autocomplete="off"></td>
				</tr>
			</table>
			<div class="jumbotron text-center">
				<button type="button" class="btn btn-outline-dark" onclick="location.href='${ctp}/'">로그인취소</button>
				<button type="submit" class="btn btn-dark">로그인</button>
				<br/><br/>
				<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='${ctp}/member/mIdSearch';">아이디찾기</button>&nbsp;
	    		<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='${ctp}/member/mPwdSearch';">비밀번호찾기</button>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>