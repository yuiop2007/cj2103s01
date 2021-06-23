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
	function sChange() {
		searchForm.searchString.focus();
	}
	
	function sCheck() {
		var searchString = searchForm.searchString.value;
		if (searchString == "") {
			alert("검색어를 입력하세요");
			searchForm.searchString.focus();
		} else {
			searchForm.submit();
		}
	}
	
	// 페이지사이즈 처리
	function pageCheck() {
		var pageSize = pageForm.pageSize.value;
		location.href = "${ctp}/product/pList?pag=${pageVO.pag}&pageSize="+ pageSize;
	}
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
		<h6>상품 목록</h6>
		</br></br></br></br>
		<table class="table table-borderless">
			<thead>
				<tr>
					<th scope="col">NO</th>
					<th scope="col">제목</th>
					<th scope="col">카테고리</th>
					<th scope="col">가격</th>
					<th scope="col">재고</th>
					<th scope="col">등록일</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="vo" items="${vos}">
				<tr>
					<td class="ptd1">${vo.pId}</td>
					<td class="ptd2"><a href="${ctp}/product/pContent?pId=${vo.pId}">${vo.pName}</a></td>
					<td class="ptd3">${vo.pCate}</td>
					<td class="ptd4">${vo.pPrice}</td>
					<td class="ptd5">${vo.pStock}</td>
					<td class="ptd6">${vo.pRdate}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<!-- 블록페이징처리 시작 -->
		<div class="container" style="text-align:center;">
		  <ul class="pagination justify-content-center">
			  <c:set var="startPageNum" value="${pageVO.pag - (pageVO.pag-1)%pageVO.blockSize}"/>  <!-- 해당블록의 시작페이지 구하기 -->
			  <c:if test="${pag != 1}">
			    <li class="page-item"><a href="${ctp}/product/pList?pag=1&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
			    <li class="page-item"><a href="${ctp}/product/pList?pag=${pageVO.pag-1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◀</a></li>
			  </c:if>
			  <c:forEach var="i" begin="0" end="${pageVO.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
			    <c:if test="${(startPageNum+i) <= pageVO.totPage}">
				  	<c:if test="${pageVO.pag == (startPageNum+i)}">
				  	  <li class="page-item active"><a href="${ctp}/product/pList?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
				  	</c:if>
				  	<c:if test="${pageVO.pag != (startPageNum+i)}">
				  	  <li class="page-item"><a href="${ctp}/product/pList?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
				  	</c:if>
			  	</c:if>
			  </c:forEach>
			  <c:if test="${pageVO.pag != pageVO.totPage}">
			    <li class="page-item"><a href="${ctp}/product/pList?pag=${pageVO.pag+1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▶</a></li>
			    <li class="page-item"><a href="${ctp}/product/pList?pag=${pageVO.totPage}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
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