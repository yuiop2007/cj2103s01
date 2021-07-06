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
<style>


</style>
<script>
	var slideIndex = 0; //slide index
	
	//HTML 로드가 끝난 후 동작
	window.onload=function(){
	showSlides(slideIndex);
	
	// Auto Move Slide
	var sec = 3000;
	setInterval(function(){
	 slideIndex++;
	 showSlides(slideIndex);
	
	}, sec);
	}
	
	
	//Next/previous controls
	function moveSlides(n) {
	slideIndex = slideIndex + n
	showSlides(slideIndex);
	}
	
	//Thumbnail image controls
	function currentSlide(n) {
	slideIndex = n;
	showSlides(slideIndex);
	}
	
	function showSlides(n) {
	
	var slides = document.getElementsByClassName("mySlides");
	var dots = document.getElementsByClassName("dot");
	var size = slides.length;
	
	if ((n+1) > size) {
	 slideIndex = 0; n = 0;
	}else if (n < 0) {
	 slideIndex = (size-1);
	 n = (size-1);
	}
	
	for (i = 0; i < slides.length; i++) {
	   slides[i].style.display = "none";
	}
	for (i = 0; i < dots.length; i++) {
	   dots[i].className = dots[i].className.replace(" active", "");
	}
	
	slides[n].style.display = "block";
	dots[n].className += " active";
	}
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
		<div class="slideshow-container">

			<!-- Full-width images with number and caption text -->
			<div class="mySlides fade">
				<img src="${ctp}/resources/slideshow/banner11101.jpg" style="width: 100%; height: 500px;">
				<div class="text">MINIM</div>
			</div>

			<div class="mySlides fade">
				<img src="${ctp}/resources/slideshow/banner11101.jpg" style="width: 100%; height: 500px;">
				<div class="text">MINIM</div>
			</div>

			<div class="mySlides fade">
				<img src="${ctp}/resources/slideshow/banner11101.jpg" style="width: 100%; height: 500px;">
				<div class="text">MINIM</div>
			</div>

			<div class="mySlides fade">
				<img src="${ctp}/resources/slideshow/banner11101.jpg" style="width: 100%; height: 500px;">
				<div class="text">MINIM</div>
			</div>

			<div class="mySlides fade">
				<img src="${ctp}/resources/slideshow/banner11101.jpg" style="width: 100%; height: 500px;">
				<div class="text">MINIM</div>
			</div>

			<div class="mySlides fade">
				<img src="${ctp}/resources/slideshow/banner11101.jpg" style="width: 100%; height: 500px;">
				<div class="text">MINIM</div>
			</div>

			<!-- Next and previous buttons -->
			<a class="prev" onclick="moveSlides(-1)">&#10094;</a> <a class="next" onclick="moveSlides(1)">&#10095;</a>
		</div>
		<br />
		<!-- The dots/circles -->
		<div style="text-align: center">
			<span class="dot" onclick="currentSlide(0)"></span> <span class="dot" onclick="currentSlide(1)"></span> <span class="dot" onclick="currentSlide(2)"></span>
			<span class="dot" onclick="currentSlide(3)"></span> <span class="dot" onclick="currentSlide(4)"></span> <span class="dot" onclick="currentSlide(5)"></span>
		</div>
		
		<div class="mainProduct">
			<ul class="proList column4">
					<c:forEach var="vo" items="${vos}">
					<li class="item">
						<div class="box">
							<p class="proImg">
								<a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}"><img class="pImg" src="${ctp}/resources/pMainImages/${vo.pImage}"/></a>
							<p> 
							<p class="proName">
								<a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${vo.pName}</a>
							<p>
							<ul class="listitem">
								<li>
									<c:choose>
										<c:when test="${vo.pSale>0}">
											<c:set var="salePrice" value="${vo.pPrice-vo.pSale}" />
											<span style="font-size:11px;color:#000000; text-decoration: line-through;">${vo.pPrice}원</span>
											<span style="font-size:11px;color:#000000;">&nbsp;${salePrice}원</span>
										</c:when>
										<c:otherwise>
											<span style="font-size:11px;color:#000000;">${vo.pPrice}원</span>	
										</c:otherwise>
									</c:choose>
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
					</c:forEach>
			</ul>
		</div>
	</div>
		<!-- 블록페이징처리 시작 -->
		<div style="text-align:center;">
		  <ul class="pagination justify-content-center">
			  <c:set var="startPageNum" value="${pageVO.pag - (pageVO.pag-1)%pageVO.blockSize}" />  <!-- 해당블록의 시작페이지 구하기 -->
			  <c:if test="${pageVO.pag != 1}">
			    <li class="page-item"><a href="${ctp}/main?pag=1&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
			    <li class="page-item"><a href="${ctp}/main?pag=${pageVO.pag-1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◀</a></li>
			  </c:if>
			  <c:forEach var="i" begin="0" end="${pageVO.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
			    <c:if test="${(startPageNum+i) <= pageVO.totPage}">
				  	<c:if test="${pageVO.pag == (startPageNum+i)}">
				  	  <li class="page-item active"><a href="${ctp}/main?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
				  	</c:if>
				  	<c:if test="${pageVO.pag != (startPageNum+i)}">
				  	  <li class="page-item"><a href="${ctp}/main?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
				  	</c:if>
			  	</c:if>
			  </c:forEach>
			  <c:if test="${pageVO.pag != pageVO.totPage}">
			    <li class="page-item"><a href="${ctp}/main?pag=${pageVO.pag+1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▶</a></li>
			    <li class="page-item"><a href="${ctp}/main?pag=${pageVO.totPage}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
			  </c:if>
		  </ul>
		</div>
		<!-- 블록페이징처리 끝 -->
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>