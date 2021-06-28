<%@page import="java.util.List"%>
<%@page import="com.spring.cj2103s01.vo.ProductVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp" />
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
			
		<div class="mainProduct">
			<ul class="proList column4">
					<c:forEach var="vo" items="${vos}">
					<c:if test="${vo.pCate eq cate}">
					<li class="item" style="width: 33%;">
						<div class="box">
							<p class="proImg">
								<a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}"><img class="pImg" src="${ctp}/resources/pMainImages/${vo.pImage}"/></a>
							<p> 
							<p class="proName">
								<a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${vo.pName}</a>
							<p>
							<ul class="listitem">
								<li>
									<span style="font-size:11px;color:#000000;">${vo.pPrice}원</span>
								</li>
								<li>
									<div class="proColor">
											<c:if test="${fn:contains(vo.pColor,'블랙')}"><span class="chips" style="background-color:#000000" color_no displaygroup="1"></span></c:if>
										 	<c:if test="${fn:contains(vo.pColor,'화이트')}"><span class="chips" style="background-color:#FFFFFF" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'차콜')}"><span class="chips" style="background-color:#555555" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'그레이')}"><span class="chips" style="background-color:#808080" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'아이보리')}"><span class="chips" style="background-color:#e8d5ac" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'베이지')}"><span class="chips" style="background-color:#b6a070" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'블루')}"><span class="chips" style="background-color:#0000ff" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'스카이블루')}"><span class="chips" style="background-color:#87ceeb" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'핑크')}"><span class="chips" style="background-color:#ffc0cb" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'그린')}"><span class="chips" style="background-color:#008000" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'레드')}"><span class="chips" style="background-color:#ff0000" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'옐로우')}"><span class="chips" style="background-color:#ffff00" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'오렌지')}"><span class="chips" style="background-color:#ffa500" color_no displaygroup="1"></span></c:if>
									</div>
								</li>
							</ul>
						</div>
					</li>
					</c:if>
					<c:if test="${cate eq 'ALL'}">
					<li class="item" style="width: 33%;">
						<div class="box">
							<p class="proImg">
								<a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}"><img class="pImg" src="${ctp}/resources/pMainImages/${vo.pImage}"/></a>
							<p> 
							<p class="proName">
								<a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${vo.pName}</a>
							<p>
							<ul class="listitem">
								<li>
									<span style="font-size:11px;color:#000000;">${vo.pPrice}원</span>
								</li>
								<li>
									<div class="proColor">
											<c:if test="${fn:contains(vo.pColor,'블랙')}"><span class="chips" style="background-color:#000000" color_no displaygroup="1"></span></c:if>
										 	<c:if test="${fn:contains(vo.pColor,'화이트')}"><span class="chips" style="background-color:#FFFFFF" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'차콜')}"><span class="chips" style="background-color:#555555" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'그레이')}"><span class="chips" style="background-color:#808080" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'아이보리')}"><span class="chips" style="background-color:#e8d5ac" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'베이지')}"><span class="chips" style="background-color:#b6a070" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'블루')}"><span class="chips" style="background-color:#0000ff" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'스카이블루')}"><span class="chips" style="background-color:#87ceeb" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'핑크')}"><span class="chips" style="background-color:#ffc0cb" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'그린')}"><span class="chips" style="background-color:#008000" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'레드')}"><span class="chips" style="background-color:#ff0000" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'옐로우')}"><span class="chips" style="background-color:#ffff00" color_no displaygroup="1"></span></c:if>
											<c:if test="${fn:contains(vo.pColor,'오렌지')}"><span class="chips" style="background-color:#ffa500" color_no displaygroup="1"></span></c:if>
									</div>
								</li>
							</ul>
						</div>
					</li>
					</c:if>
					</c:forEach>
			</ul>
		</div>
	</div>
		<!-- 블록페이징처리 시작 -->
		<div style="text-align:center;">
		  <ul class="pagination justify-content-center">
			  <c:set var="startPageNum" value="${pageVO.pag - (pageVO.pag-1)%pageVO.blockSize}" />  <!-- 해당블록의 시작페이지 구하기 -->
			  <c:if test="${pag != 1}">
			    <li class="page-item"><a href="${ctp}/product/proShow?cate=${cate}&pag=1&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
			    <li class="page-item"><a href="${ctp}/product/proShow?cate=${cate}&pag=${pageVO.pag-1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◀</a></li>
			  </c:if>
			  <c:forEach var="i" begin="0" end="${pageVO.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
			    <c:if test="${(startPageNum+i) <= pageVO.totPage}">
				  	<c:if test="${pageVO.pag == (startPageNum+i)}">
				  	  <li class="page-item active"><a href="${ctp}/product/proShow?cate=${cate}&pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
				  	</c:if>
				  	<c:if test="${pageVO.pag != (startPageNum+i)}">
				  	  <li class="page-item"><a href="${ctp}/product/proShow?cate=${cate}&pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
				  	</c:if>
			  	</c:if>
			  </c:forEach>
			  <c:if test="${pageVO.pag != pageVO.totPage}">
			    <li class="page-item"><a href="${ctp}/product/proShow?cate=${cate}&pag=${pageVO.pag+1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▶</a></li>
			    <li class="page-item"><a href="${ctp}/product/proShow?cate=${cate}&pag=${pageVO.totPage}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
			  </c:if>
		  </ul>
		</div>
		<!-- 블록페이징처리 끝 -->
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>