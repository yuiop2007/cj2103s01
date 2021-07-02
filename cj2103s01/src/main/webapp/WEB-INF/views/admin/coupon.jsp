<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
int level = session.getAttribute("slevel") == null ? 99 : (int) session.getAttribute("slevel");
%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp" />
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<style>
.table th{
    vertical-align: middle;
}
.table td{
	text-align: center;
	vertical-align: middle;
}
</style>
<script>


	function delCheck(cId) {
		var ans = confirm("쿠폰을 삭제하시겠습니까?");
		
		if(ans) {
			location.href="${ctp}/admin/cDelete?cId="+cId;
		}
	}
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
		<h6>쿠폰</h6>
		</br>
		</br>
		</br>
		</br>
		<form name="pageForm">
			<table class="table table-borderless">
				<thead>
					<tr class="table-dark text-dark">
						<th scope="col" style="width: 5%;">NO</th>
						<th scope="col" style="width: 30%; text-align: left;">쿠폰명</th>
						<th scope="col" style="width: 10%;">쿠폰대상</th>
						<th scope="col" style="width: 10%;">할인품목</th>
						<th scope="col" style="width: 15%;">할인율/할인가격</th>
						<th scope="col" style="width: 10%;">시작일</th>
						<th scope="col" style="width: 10%;">종료일</th>
						<th><c:if test="${slevel eq 0}"><input type="button" value="등록하기" onclick="location.href='${ctp}/admin/cInput'" /></c:if></th>
					</tr>
				</thead>
				<tbody>
				<c:set var="curScrStartNo" value="${pageVO.curScrStartNo}" />
				<c:forEach var="vo" items="${vos}">
				<c:if test="${smid eq vo.mId}">
					<tr>
						<td class="" style="width: 5%;">${curScrStartNo}</td>
						<td class="" style="width: 30%; text-align: left;">${vo.cName}</td>
						<td class="" style="width: 10%;">
							<c:if test="${vo.cmCate eq 1}">특별회원</c:if>
							<c:if test="${vo.cmCate eq 2}">우수회원</c:if>
							<c:if test="${vo.cmCate eq 3}">정회원</c:if>
							<c:if test="${vo.cmCate eq 4}">준회원</c:if>
							<c:if test="${vo.cmCate eq 5}">모든회원</c:if>
						</td>
						<td class="" style="width: 10%;">${vo.cpCate}</td>
						<td class="" style="width: 15%;">${vo.cDiscount}% / ${vo.cPrice}원</td>
						<td class="" style="width: 10%;">${vo.cRdate}</td>
						<td class="" style="width: 10%;">${vo.cEnddate}</td>
						<td><c:if test="${slevel eq 0}"><div class=boardbtn><a href="#" onclick="delCheck(${vo.cId})">삭제</a></div></c:if></td>
					</tr>
					<c:set var="curScrStartNo" value="${curScrStartNo-1}" />
				</c:if>
				</c:forEach>
				<c:set var="curScrStartNo" value="${pageVO.curScrStartNo}" />
				<c:forEach var="vo" items="${avos}">
				<c:if test="${slevel eq 0}">
					<tr>
						<td class="" style="width: 5%;">${curScrStartNo}</td>
						<td class="" style="width: 30%; text-align: left;">${vo.cName}</td>
						<td class="" style="width: 10%;">
							<c:if test="${vo.cmCate eq 1}">특별회원</c:if>
							<c:if test="${vo.cmCate eq 2}">우수회원</c:if>
							<c:if test="${vo.cmCate eq 3}">정회원</c:if>
							<c:if test="${vo.cmCate eq 4}">준회원</c:if>
							<c:if test="${vo.cmCate eq 5}">모든회원</c:if>
						</td>
						<td class="" style="width: 10%;">${vo.cpCate}</td>
						<td class="" style="width: 15%;">${vo.cDiscount}% / ${vo.cPrice}원</td>
						<td class="" style="width: 10%;">${vo.cRdate}</td>
						<td class="" style="width: 10%;">${vo.cEnddate}</td>
						<td><c:if test="${slevel eq 0}"><div class=boardbtn><a href="#" onclick="delCheck(${vo.cId})">삭제</a></div></c:if></td>
					</tr>
					<c:set var="curScrStartNo" value="${curScrStartNo-1}" />
				</c:if>
				</c:forEach>
				</tbody>
			</table>
		</form>
		<!-- 블록페이징처리 시작 -->
		<div class="container" style="text-align:center;">
		  <ul class="pagination justify-content-center">
			  <c:set var="startPageNum" value="${pageVO.pag - (pageVO.pag-1)%pageVO.blockSize}" />  <!-- 해당블록의 시작페이지 구하기 -->
			  <c:if test="${pageVO.pag != 1}">
			    <li class="page-item"><a href="${ctp}/wish/myWish?pag=1&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
			    <li class="page-item"><a href="${ctp}/wish/myWish?pag=${pageVO.pag-1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◀</a></li>
			  </c:if>
			  <c:forEach var="i" begin="0" end="${pageVO.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
			    <c:if test="${(startPageNum+i) <= pageVO.totPage}">
				  	<c:if test="${pageVO.pag == (startPageNum+i)}">
				  	  <li class="page-item active"><a href="${ctp}/wish/myWish?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
				  	</c:if>
				  	<c:if test="${pageVO.pag != (startPageNum+i)}">
				  	  <li class="page-item"><a href="${ctp}/wish/myWish?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
				  	</c:if>
			  	</c:if>
			  </c:forEach>
			  <c:if test="${pageVO.pag != pageVO.totPage}">
			    <li class="page-item"><a href="${ctp}/wish/myWish?pag=${pageVO.pag+1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▶</a></li>
			    <li class="page-item"><a href="${ctp}/wish/myWish?pag=${pageVO.totPage}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
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