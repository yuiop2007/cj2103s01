<%@page import="com.spring.cj2103s01.vo.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<style>
.infotable select{
	padding: 3px;
}

.proReview h3, .proQnA h3 {
    margin: 70px auto 0px;
    padding: 10px 30px 10px 30px;
    width: 130px;
    text-align: center;
    border: 1px solid #999;
    color: #444;
    font-size: 11px;
    font-family: 'Lato';
    font-weight: 600;
    text-transform: uppercase;
    letter-spacing: 1px;
}
.proReview, .proQnA{
	padding: 0 30px 0 30px;
}
</style>
<script>
	

  

		
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
	</div>
	<div class="container" style="padding: 0%;">
		<div class="proReview">
			<h3>review </h3>
			<form name="pageForm">
				<table class="table table-borderless">
					<thead>
						<tr>
							<th scope="col" style="width: 5%;">NO</th>
							<th scope="col" style="width: 10%; text-align: left;">평점</th>
							<th scope="col" style="width: 50%; text-align: left;">제목</th>
							<th scope="col" style="width: 10%;">작성자</th>
							<th scope="col" style="width: 10%;">
							</th>
						</tr>
					</thead>
					<tbody>
					<c:set var="curScrStartNo" value="${rpageVO.curScrStartNo}" />
					<c:forEach var="vo" items="${rvos}">
						<tr>
							<td class="td1" style="width: 5%;">${curScrStartNo}</td>
							<td class="td2" style="width: 10%;">
							<c:if test="${vo.rRating eq 1}">⭐</c:if>
							<c:if test="${vo.rRating eq 2}">⭐⭐</c:if>
							<c:if test="${vo.rRating eq 3}">⭐⭐⭐</c:if>
							<c:if test="${vo.rRating eq 4}">⭐⭐⭐⭐</c:if>
							<c:if test="${vo.rRating eq 5}">⭐⭐⭐⭐⭐</c:if>
							</td>
							<td class="td3" style="width: 50%; text-align: left;"><a href="${ctp}/board/rContent?rId=${vo.rId}&pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${vo.rTitle}</a></td>
							<td class="td3" style="width: 10%;">${vo.rName}</td>
							<td class="td4" style="width: 10%;">
								<c:if test="${vo.diffTime <= 24}">${fn:substring(vo.rRdate,11,19)}</c:if>
			        			<c:if test="${vo.diffTime > 24}">${fn:substring(vo.rRdate,0,10)}</c:if>
							</td>
						</tr>
						<c:set var="curScrStartNo" value="${curScrStartNo-1}" />
					</c:forEach>
					</tbody>
				</table>
			</form>
			<!-- 블록페이징처리 시작 -->
			<div class="container" style="text-align:center;">
			  <ul class="pagination justify-content-center">
				  <c:set var="startPageNum" value="${rpageVO.pag - (rpageVO.pag-1)%rpageVO.blockSize}" />  <!-- 해당블록의 시작페이지 구하기 -->
				  <c:if test="${rpageVO.pag != 1}">
				    <li class="page-item"><a href="${ctp}/member/myBoard?rpag=1&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
				    <li class="page-item"><a href="${ctp}/member/myBoard?rpag=${rpageVO.pag-1}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">◀</a></li>
				  </c:if>
				  <c:forEach var="i" begin="0" end="${rpageVO.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
				    <c:if test="${(startPageNum+i) <= rpageVO.totPage}">
					  	<c:if test="${rpageVO.pag == (startPageNum+i)}">
					  	  <li class="page-item active"><a href="${ctp}/member/myBoard?rpag=${startPageNum+i}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
					  	</c:if>
					  	<c:if test="${rpageVO.pag != (startPageNum+i)}">
					  	  <li class="page-item"><a href="${ctp}/member/myBoard?rpag=${startPageNum+i}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
					  	</c:if>
				  	</c:if>
				  </c:forEach>
				  <c:if test="${rpageVO.pag != rpageVO.totPage}">
				    <li class="page-item"><a href="${ctp}/member/myBoard?rpag=${rpageVO.pag+1}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">▶</a></li>
				    <li class="page-item"><a href="${ctp}/member/myBoard?rpag=${rpageVO.totPage}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
				  </c:if>
			  </ul>
			</div>
			<!-- 블록페이징처리 끝 -->
		</div>
		<div class="proQnA">
			<h3>Q &amp; A </h3>
			<form name="pageForm2">
				<table class="table table-borderless">
					<thead>
						<tr>
							<th scope="col" style="width: 5%;">NO</th>
							<th scope="col" style="width: 10%; text-align: left;">카테고리</th>
							<th scope="col" style="width: 52%; text-align: left;">제목</th>
							<th scope="col" style="width: 10%;">작성자</th>
							<th scope="col" style="width: 10%;">
							</th>
						</tr>
					</thead>
					<tbody>
					<c:set var="curScrStartNo" value="${qpageVO.curScrStartNo}" />
					<c:forEach var="vo" items="${qvos}">
						<tr>
							<td class="td1" style="width: 5%;">${curScrStartNo}</td>
							<td class="td2" style="width: 10%;">${vo.qCate }</td>
							<td class="td3" style="width: 52%; text-align: left;">
							<c:if test="${vo.qSecret eq 0}">
								<a href="${ctp}/board/qContent?qId=${vo.qId}&pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${vo.qTitle}</a>
							</c:if>
							<c:if test="${vo.qSecret eq 1}">
								<c:choose>
					                <c:when test="${smid eq vo.qWriter || slevel eq 0}">
					                    <a href="${ctp}/board/qContent?qId=${vo.qId}&pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}"><img src="${ctp}/images/lock.gif"/>${vo.qTitle}</a>
					                </c:when>
					                <c:otherwise><img src="${ctp}/images/lock.gif"/>${vo.qTitle}</c:otherwise>
					            </c:choose>
							</c:if>
							</td>
							<td class="td3" style="width: 10%;">${vo.qName}</td>
							<td class="td4" style="width: 10%;">
								<c:if test="${vo.diffTime <= 24}">${fn:substring(vo.qRdate,11,19)}</c:if>
			        			<c:if test="${vo.diffTime > 24}">${fn:substring(vo.qRdate,0,10)}</c:if>
							</td>
						</tr>
						<c:set var="curScrStartNo" value="${curScrStartNo-1}" />
					</c:forEach>
					</tbody>
				</table>
			</form>
			<!-- 블록페이징처리 시작 -->
			<div class="container" style="text-align:center;">
			  <ul class="pagination justify-content-center">
				  <c:set var="startPageNum" value="${qpageVO.pag - (qpageVO.pag-1)%qpageVO.blockSize}" />  <!-- 해당블록의 시작페이지 구하기 -->
				  <c:if test="${qpageVO.pag != 1}">
				    <li class="page-item"><a href="${ctp}/member/myBoard?rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=1&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
				    <li class="page-item"><a href="${ctp}/member/myBoard?rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag-1}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">◀</a></li>
				  </c:if>
				  <c:forEach var="i" begin="0" end="${qpageVO.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
				    <c:if test="${(startPageNum+i) <= qpageVO.totPage}">
					  	<c:if test="${qpageVO.pag == (startPageNum+i)}">
					  	  <li class="page-item active"><a href="${ctp}/member/myBoard?rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=${startPageNum+i}&qpageSize=${qpageVO.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
					  	</c:if>
					  	<c:if test="${qpageVO.pag != (startPageNum+i)}">
					  	  <li class="page-item"><a href="${ctp}/member/myBoard?rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=${startPageNum+i}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
					  	</c:if>
				  	</c:if>
				  </c:forEach>
				  <c:if test="${qpageVO.pag != qpageVO.totPage}">
				    <li class="page-item"><a href="${ctp}/member/myBoard?rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag+1}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">▶</a></li>
				    <li class="page-item"><a href="${ctp}/member/myBoard?rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.totPage}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
				  </c:if>
			  </ul>
			</div>
			<!-- 블록페이징처리 끝 -->
		</div>
	</div>
 	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>