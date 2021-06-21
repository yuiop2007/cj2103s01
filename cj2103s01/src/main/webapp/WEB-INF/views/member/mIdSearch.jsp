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
		<form name="loginform" method="post" action="${ctp}/member/mIdSearch">
			<h6>아이디 찾기</h6>
			<hr />
			<table>
				<tr>
					<td class="ftd">이름</td>
					<td><input type="text" name="mName" id="mName" maxlength="30" required></td>
				</tr>
				<tr>
					<td class="ftd">이메일</td>
					<td><input type="email" name="mEmail" id="mEmail" required></td>
				</tr>
			</table>
			<div class="jumbotron text-center">
				<button type="button" class="btn btn-outline-dark" onclick="location.href='${ctp}/member/login'">취소</button>
				<button type="submit" class="btn btn-dark">확인</button>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>