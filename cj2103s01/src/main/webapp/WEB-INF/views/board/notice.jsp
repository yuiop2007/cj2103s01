<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp" />
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<script>

</script>
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
			<c:forEach var="vo" items="${vos}">
				<tr>
					<td class="td1">${vo.nId}</td>
					<td class="td2"><a href="#">${vo.nTitle}</a></td>
					<td class="td3">${vo.nWriter}</td>
					<td class="td4">${vo.nRdate}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<!-- 블록페이징처리 시작 -->
		<div class="container" style="text-align:center;">
		  <ul class="pagination justify-content-center">
			  <c:set var="startPageNum" value="${pageVo.pag - (pageVo.pag-1)%pageVo.blockSize}"/>  <!-- 해당블록의 시작페이지 구하기 -->
			  <c:if test="${pag != 1}">
			    <li class="page-item"><a href="${ctp}/board/notice?pag=1&pageSize=${pageVo.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
			    <li class="page-item"><a href="${ctp}/board/notice?pag=${pageVo.pag-1}&pageSize=${pageVo.pageSize}" class="page-link" style="color:#666">◀</a></li>
			  </c:if>
			  <c:forEach var="i" begin="0" end="${pageVo.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
			    <c:if test="${(startPageNum+i) <= pageVo.totPage}">
				  	<c:if test="${pageVo.pag == (startPageNum+i)}">
				  	  <li class="page-item active"><a href="${ctp}/board/notice?pag=${startPageNum+i}&pageSize=${pageVo.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
				  	</c:if>
				  	<c:if test="${pageVo.pag != (startPageNum+i)}">
				  	  <li class="page-item"><a href="${ctp}/board/notice?pag=${startPageNum+i}&pageSize=${pageVo.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
				  	</c:if>
			  	</c:if>
			  </c:forEach>
			  <c:if test="${pageVo.pag != pageVo.totPage}">
			    <li class="page-item"><a href="${ctp}/board/notice?pag=${pageVo.pag+1}&pageSize=${pageVo.pageSize}" class="page-link" style="color:#666">▶</a></li>
			    <li class="page-item"><a href="${ctp}/board/notice?pag=${pageVo.totPage}&pageSize=${pageVo.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
			  </c:if>
		  </ul>
		</div>
		<!-- 블록페이징처리 끝 -->
		
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>