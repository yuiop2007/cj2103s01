<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
							<a href="${ctp}/admin/oaList?status=입금전" class="count"><span>${notPayCnt }</span></a>
						</li>
						<li>
							<strong>배송준비중</strong>
							<a href="${ctp}/admin/oaList?status=배송준비중" class="count"><span>${delReadyCnt }</span></a>
						</li>
						<li>
							<strong>배송중</strong>
							<a href="${ctp}/admin/oaList?status=배송중" class="count"><span>${delingCnt }</span></a>
						</li>
						<li>
							<strong>배송완료</strong>
							<a href="${ctp}/admin/oaList?status=배송완료" class="count"><span>${delEndCnt }</span></a>
						</li>
					</ul>
					<ul class ="cs">
						<li>
							<span class="icoDot"></span>
							<strong>취소</strong>
							<a href="${ctp}/admin/oaList?change=취소" class="count"><span>${cancelCnt }</span></a>
						</li>
						<li>
							<span class="icoDot"></span>
							<strong>교환</strong>
							<a href="${ctp}/admin/oaList?change=교환" class="count"><span>${changeCnt }</span></a>
						</li>
						<li>
							<span class="icoDot"></span>
							<strong>반품</strong>
							<a href="${ctp}/admin/oaList?change=반품" class="count"><span>${returnCnt }</span></a>
						</li>
					</ul>
		    	</div>
		    </div>
	  	</div>
	  	<div class=boardbtn style="margin-left: 30px;">
			<a href="${ctp}/admin/oaList" style="color: #fff; background-color: black;" >주문전체조회</a>
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
						<span class="data">${totSell} 회</span>
					</li>
					<li class="etc">
						<span class="title">총 매출액</span>
						<span class="data"><fmt:formatNumber value="${totSellMoney}" pattern="#,###" /><span>원</span></span>
						<a href="${ctp}/admin/charts"><img src="${ctp}/images/btn_result.gif" alt="조회"></a>
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
		     <div class="col shopMain">
		    	<a href="${ctp}/admin/slideShow">
		    		<strong>슬라이드쇼</strong>
		    		메인 화면에 표시될 슬라이드 쇼를 등록 및 관리 할 수 있습니다.
		    		<br/>
		    	</a>
		    </div>
		    <div class="col shopMain">
		    	<a href="${ctp}/admin/fileDel">
		    		<strong>임시 파일 삭제</strong>
		    		사용되지 않는 임시 데이터를 삭제합니다.
		    		<br/>
		    	</a>
		    </div>
	  	</div>
		<!-- <div class="row myshopMain">
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
		    </div>
	  	</div> -->
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>