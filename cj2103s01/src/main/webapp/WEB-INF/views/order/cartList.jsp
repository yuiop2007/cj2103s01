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
<script>

	function delCheck(cartId) {
		var ans = confirm("장바구니에서 삭제하시겠습니까?");
		
		if(ans) {
			location.href="${ctp}/order/cartDelete?cartId="+cartId;
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
		<h6>장바구니</h6>
		</br>
		</br>
		</br>
		</br>
		<form name="pageForm">
			<table class="table table-borderless">
				<thead>
					<tr>
						<th scope="col" style="width: 5%;">NO</th>
						<th scope="col" style="width: 20%;"></th>
						<th scope="col" style="width: 40%; text-align: left;">상품명/옵션</th>
						<th scope="col" style="width: 15%;">수량</th>
						<th scope="col" style="width: 5%;">가격</th>
						<th scope="col" style="padding-bottom: 5px;">
					</tr>
				</thead>
				<tbody>
				<c:set var="curScrStartNo" value="${pageVO.curScrStartNo}" />
				<c:forEach var="vo" items="${vos}">
					<tr>
						<td class="td1" style="width: 5%; padding-top: 70px;">${curScrStartNo}</td>
						<c:forEach var="pvo" items="${pvos}">
							<c:if test="${pvo.pId eq vo.pId}">
								<td class="td1" style="width: 20%;">
									<a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}"><img src="${ctp}/resources/pMainImages/${pvo.pImage}" style="width: 100px;"/></a>
								</td>
								<td class="td2" style="width: 40%; text-align: left; padding-top: 70px;">
									<a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${pvo.pName}/${vo.pOption}</a>
								</td>
							</c:if>
						</c:forEach>
						<td class="td1" style="width: 15%; padding-top: 70px;">${vo.pCnt}</td>
						<td class="td1" style="width: 5%; padding-top: 70px;">${vo.pPrice}</td>
						<td style="padding-top: 55px;"><div class=boardbtn><a href="#" onclick="delCheck(${vo.cartId})">삭제</a></div></td>
					</tr>
					<c:set var="curScrStartNo" value="${curScrStartNo-1}" />
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