<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp" />
<link rel="stylesheet" type="text/css" href="resources/css/css.css">

</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
		<h6>공지사항</h6>
		</br></br></br></br>
		<table class="table table-borderless">
			<thead>
				<tr>
					<th scope="col">NO</th>
					<th scope="col"></th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="td1">3</td>
					<td class="td2">Doe</td>
					<td class="td3">미님</td>
					<td class="td4">날짜</td>
				</tr>
				<tr>
					<td class="td1">2</td>
					<td class="td2">Moe</td>
					<td class="td3">미님</td>
					<td class="td4">날짜</td>
				</tr>
				<tr>
					<td class="td1">1</td>
					<td class="td2">Dooley</td>
					<td class="td3">미님</td>
					<td class="td4">날짜</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>