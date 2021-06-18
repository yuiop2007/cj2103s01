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
<style>
.mytitle {
    padding: 20px 20px 0;
    margin: 0;
    border-bottom: 1px solid #e9e9e9;
    background: #fff;
    font-size: 11px;
    color: #353535;
    letter-spacing: 1px;
}

.mytitle h3 {
    padding: 0;
    font-size: 12px;
    font-family: 'Lato','Nanum Gothic';
    font-weight: 800;
    color: #000;
    text-align: left;
}
.desc {
    padding: 0 0 0 4px;
    font-weight: normal;
    font-size: 11px;
    color: #8f8f8f;
}
.orderstatus{
	width: 99%;
    margin: 5px 0 0;
    border: 1px solid #ddd;
}
.state {
    overflow: hidden;
    padding: 22px 0px 18px 20px;
}
.order {
    float: left;
}
.order li {
    float: left;
    width: 100px;
    padding: 0 0 4px;
    margin: 0 -1px 0 0;
    border-right: 0px dotted #c9c7ca;
    text-align: left;
}
li {
    list-style: none;
}
.order li strong {
    display: inline-block;
    font-weight: normal;
    margin: 0px 0 3px;
    font-size: 11px;
    color: #111;
}
.order .count {
    display: inline-block;
    font-weight: bold;
    font-size: 14px;
    color: #333;
}
.cs {
    float: right;
    width: 27%;
}
.cs li {
    margin: 0 0 5px 10px;
    display: inline-block;
    letter-spacing: 0.04em;
    word-spacing: 4px;
}
.cs .icoDot {
    display: inline-block;
    width: 0px;
    height: 0px;
    margin: 6px 0 0 0;
    background: #fff;
    vertical-align: top;
}
.cs strong {
    font-weight: normal;
    font-size: 11px;
    color: #353535;
    letter-spacing: 1px;
}
.cs .count {
    display: inline-block;
    padding: 0 0 0 0px;
    font-size: 14px;
    font-weight: bold;
    color: #000;
}

.bankbook {
    position: relative;
    width: 100%;
    margin: 20px auto;
    padding: 8px 0 0 10px;
    background: #fff;
    border: 0px solid #eee;
}
.bankbook ul {
    display: table;
    font-size: 0;
    line-height: 0;
}
.bankbook li:nth-child(1) {
    display: inline-block;
    position: relative;
    margin: 1px 0px 2px 0;
    padding-bottom: 0px;
    font-size: 11px;
    color: #353535;
    line-height: 25px;
    border-bottom: 0px solid #eee;
    vertical-align: top;
    *display: inline;
    *zoom: 1;
}
.bankbook li {
    display: inline-block;
    position: relative;
    margin: 1px 0px 2px 0;
    padding-bottom: 0px;
    font-size: 11px;
    color: #353535;
    line-height: 25px;
    border-bottom: 0px solid #eee;
    vertical-align: top;
    *display: inline;
    *zoom: 1;
}

.bankbook li .title {
    float: left;
    display: inline-block;
    width: auto;
    padding: 0 0 0 10px;
    font-weight: normal;
    text-align: left;
    letter-spacing: -.5px;
}

.bankbook li:nth-child(1) .data {
    float: left;
    display: inline-block;
    width: auto;
    font-size: 13px;
    font-weight: bold;
    padding: 0 70px 0 10px;
    text-align: left;
    color: #000;
}

.bankbook li .data {
    float: left;
    display: inline-block;
    width: auto;
    font-size: 13px;
    font-weight: bold;
    margin-right: 20px;
    padding: 0 50px 0 10px;
    text-align: left;
    color: #000;
}

.bankbook li a {
    position: absolute;
    right: 25px;
    top: 0;
}

.shopMain {
    float: left;
    display: inline-block;
    width: 50%;
    margin: 0 0px 10px 0;
    background: #fff;
    line-height: 16px;
    border-right: 5px solid #fff;
    border-left: 5px solid #fff;
    box-sizing: border-box;
    color: #626262;
}

.shopMain a {
    display: block;
    font-size: 11px;
    padding: 15px 20px;
    height: 100px;
    border: 1px solid #ddd;
}
.shopMain strong {
    display: block;
    font-size: 11px;
    font-family: 'Lato','Nanum Gothic';
    font-weight: 800;
    padding: 0 0 7px;
    text-transform: uppercase;
    color: #000;
    letter-spacing: 1px;
}

.myshopMain {
    display: block;
    margin: 20px 0 50px -5px;
    width: 99.8%;
    clear: both;
}
</style>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
		<h6>마이페이지</h6>
		<hr />
		<div class="row orderstatus">
		    <div class="col">
		    	<div class="mytitle">
		    		<h3>ORDER STATUS
			    		<span class="desc">recent 3 months</span>
		    		</h3>
		    	</div>
		    	<div class="col state">
		    		<ul class = "order">
						<li>
							<strong>입금전</strong>
							<a href="#" class="count"><span>0</span></a>
						</li>
						<li>
							<strong>배송준비중</strong>
							<a href="#" class="count"><span>0</span></a>
						</li>
						<li>
							<strong>배송중</strong>
							<a href="#" class="count"><span>0</span></a>
						</li>
						<li>
							<strong>배송완료</strong>
							<a href="#" class="count"><span>0</span></a>
						</li>
					</ul>
					<ul class ="cs">
						<li>
							<span class="icoDot"></span>
							<strong>취소</strong>
							<a href="#" class="count"><span>0</span></a>
						</li>
						<li>
							<span class="icoDot"></span>
							<strong>교환</strong>
							<a href="#" class="count"><span>0</span></a>
						</li>
						<li>
							<span class="icoDot"></span>
							<strong>반품</strong>
							<a href="#" class="count"><span>0</span></a>
						</li>
					</ul>
		    	</div>
		    </div>
	  	</div>
	  	
		<div class="row bankbook">
		    <div class="col">
				<ul>
					<li class=" ">
						<span class="title">가용 적립금</span>
						<span class="data use">0원</span>
						<a href="#"><img src="${ctp}/images/btn_result.gif" alt="조회"></a>
					</li>
					<li class>
						<span class="title">총 적립금</span>
						<span class="data">0원</span>
					</li>
					<li class>
						<span class="title">사용 적립금</span>
						<span class="data">0원</span>
					</li>
					<li class="etc">
						<span class="title">총 주문</span>
						<span class="data">0 (0 회)</span>
					</li>
					<li class="etc">
						<span class="title">쿠폰</span>
						<span class="data">0<span>개</span></span>
						<a href="#"><img src="${ctp}/images/btn_result.gif" alt="조회"></a>
					</li>
				
				</ul>
			</div>
	  	</div>
	  	
		<div class="row myshopMain">
		    <div class="col shopMain">
		    	<a href="#">
		    		<strong>order</strong>
		    		고객님께서 주문하신 상품의 주문내역을 확인하실 수 있습니다.
		    		<br/>
		    		비회원의 경우, 주문서의 주문번호와 비밀번호로 주문조회가 가능합니다.
		    	</a>
		    </div>
		    <div class="col shopMain">
		   		<a href="#">
		    		<strong>modify</strong>
		    		회원이신 고객님의 개인정보를 관리하는 공간입니다.
		    		<br/>
		    		개인정보를 최신 정보로 유지하시면 보다 간편히 쇼핑을 즐기실 수 있습니다.
		    	</a>
		    </div>
	  	</div>
		<div class="row myshopMain">
		    <div class="col shopMain">
		    	<a href="#">
		    		<strong>wish list</strong>
		    		관심상품으로 등록하신 상품의 목록을 보여드립니다.
		    		<br/>
		    	</a>
		    </div>
		    <div class="col shopMain">
		    	<a href="#">
		    		<strong>point</strong>
		    		적립금은 상품 구매 시 사용하실 수 있습니다.
		    		<br/>
		    		적립된 금액은 현금으로 환불되지 않습니다.
		    	</a>
		    </div>
	  	</div>
		<div class="row myshopMain">
		    <div class="col shopMain">
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
<!-- 		    		<strong>my </strong>
		    		고객님께서 작성하신 게시물을 관리하는 공간입니다.
		    		<br/>
		    		고객님께서 작성하신 글을 한눈에 관리하실 수 있습니다. -->
		    	</a>
		    </div>
	  	</div>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>