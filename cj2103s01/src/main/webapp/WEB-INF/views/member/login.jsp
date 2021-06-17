<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<link rel="stylesheet" type="text/css" href="resources/css/css.css">
<jsp:include page="/WEB-INF/views/include/bs.jsp"/>
<script>
	function LoginAction(){
		
		loginform.submit();
	}
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
	</div>
	<div class="container">
		<form name="loginform" method="post" action="${ctp}/login">
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
				<button type="button" class="btn btn-outline-dark" action="">로그인취소</button>
				<button type="button" class="btn btn-dark" onclick="LoginAction()">로그인</button>
			</div>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>