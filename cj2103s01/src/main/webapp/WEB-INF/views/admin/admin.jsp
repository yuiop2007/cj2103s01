<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  int level = session.getAttribute("slevel")==null ? 99 : (int) session.getAttribute("slevel");
%>
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
		<h6>관리자 페이지</h6>
		<hr />
		<div class="row orderstatus">
		    <div class="col">
		    	<div class="mytitle">
		    		<h3>ORDER STATUS</h3>
		    	</div>
		    	<div class="col state">
		    		<ul class = "order">
						<li>
							<strong>입금전</strong>
							<a href="${ctp}/order/notPayList" class="count"><span>${notPayCnt}</span></a>
						</li>
						<li>
							<strong>배송준비중</strong>
							<a href="${ctp}/order/readyList" class="count"><span>${delReadyCnt}</span></a>
						</li>
						<li>
							<strong>배송중</strong>
							<a href="${ctp}/order/ingList" class="count"><span>${delingCnt}</span></a>
						</li>
						<li>
							<strong>배송완료</strong>
							<a href="${ctp}/order/endList" class="count"><span>${delEndCnt}</span></a>
						</li>
					</ul>
					<ul class ="cs">
						<li>
							<span class="icoDot"></span>
							<strong>취소</strong>
							<a href="${ctp}/order/cancelList" class="count"><span>${cancelCnt}</span></a>
						</li>
						<li>
							<span class="icoDot"></span>
							<strong>교환</strong>
							<a href="${ctp}/order/changeList" class="count"><span>${changeCnt}</span></a>
						</li>
						<li>
							<span class="icoDot"></span>
							<strong>반품</strong>
							<a href="${ctp}/order/returnCnt" class="count"><span>${returnCnt}</span></a>
						</li>
					</ul>
		    	</div>
		    </div>
	  	</div>
	  	
		<div class="row bankbook">
		    <div class="col">
				<ul>
					<li class=" ">
						<span class="title">총 회원수</span>
						<span class="data use">${totMember}명</span>
					</li>
					<li>
						<span class="title">탈퇴 회원수</span>
						<span class="data">${dropMember}명</span>
					</li>
					<li>
						<span class="title">총 상품수</span>
						<span class="data">${totProduct}개</span>
					</li>
					<li class="etc">
						<span class="title">총 판매량</span>
						<span class="data">0 회</span>
					</li>
					<li class="etc">
						<span class="title">총 매출액</span>
						<span class="data">0<span>개</span></span>
						<a href="#"><img src="${ctp}/images/btn_result.gif" alt="조회"></a>
					</li>
				
				</ul>
			</div>
	  	</div>
	  	
		<div class="row myshopMain">
		    <div class="col shopMain">
		    	<a href="${ctp}/product/pInput">
		    		<strong>상품 등록</strong>
		    		상품을 빠르게 등록 할 수 있습니다.
		    	</a>
		    </div>
		    <div class="col shopMain">
		   		<a href="${ctp}/product/pList">
		    		<strong>상품 목록</strong>
		    		총 상품 목록 : ${totProduct}
		    	</a>
		    </div>
	  	</div>
		<div class="row myshopMain">
		    <div class="col shopMain">
		    	<a href="${ctp}/admin/mList">
		    		<strong>회원 관리</strong>
		    		전체 회원을 관리 할 수 있습니다.
		    		<br/>
		    	</a>
		    </div>
		    <div class="col shopMain">
		    	<a href="${ctp}/admin/coupon">
		    		<strong>쿠폰 관리</strong>
		    		쿠폰을 등록하고 관리 할 수 있습니다.
		    		<br/>
		    	</a>
		    </div>
	  	</div>
		<div class="row myshopMain">
		<!--     <div class="col shopMain">
		    	<a href="#">
		    		<strong>coupon</strong>
		    		고객님이 보유하고 계신 쿠폰내역을 보여드립니다.
		    		<br/>
		    	</a>
		    </div>
		    <div class="col shopMain">
		    	<a href="#">
		    		<strong>my board</strong>
		    		고객님께서 작성하신 게시물을 관리하는 공간입니다.
		    		<br/>
		    		고객님께서 작성하신 글을 한눈에 관리하실 수 있습니다.
		    	</a>
		    </div>
	  	</div>
		<div class="row myshopMain">
		    <div class="col shopMain">
		    	<a href="#">
		    		<strong>address</strong>
		    		자주 사용하는 배송지를 등록하고 관리하실 수 있습니다.
		    		<br/>
		    	</a>
		    </div>	
		    <div class="col shopMain">
		    	<a href="#">
 		    		<strong>Delete</strong>
		    		회원 탈퇴
		    		
		    	</a>
		    </div> -->
	  	</div>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>