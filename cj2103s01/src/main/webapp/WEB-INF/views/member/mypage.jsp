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
<script>
	function mDeleteCheck() {
		  var ans = confirm("정말 탈퇴 하시겠습니까?");
		  if(ans) {
			  ans = confirm("탈퇴하시게되면 1개원간 같은 아이디로 재가입 불가입니다.\n탈퇴 하시겠습니까?");
			  if(ans) location.href = "${ctp}/mDelete?mId=${smid}";
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
		<h6>마이페이지</h6>
		<hr />
		<div class="row orderstatus">
		    <div class="col">
		    	<div class="mytitle">
		    		<h3>ORDER STATUS
			    		<!-- <span class="desc">recent 3 months</span> -->
		    		</h3>
		    	</div>
		    	<div class="col state">
		    		<ul class = "order">
						<li>
							<strong>입금전</strong>
							<a href="${ctp}/order/oList?status=notPay" class="count"><span>${notPayCnt }</span></a>
						</li>
						<li>
							<strong>배송준비중</strong>
							<a href="${ctp}/order/oList?status=readyDel" class="count"><span>${delReadyCnt }</span></a>
						</li>
						<li>
							<strong>배송중</strong>
							<a href="${ctp}/order/oList?status=ingDel" class="count"><span>${delingCnt }</span></a>
						</li>
						<li>
							<strong>배송완료</strong>
							<a href="${ctp}/order/oList?status=endDel" class="count"><span>${delEndCnt }</span></a>
						</li>
					</ul>
					<ul class ="cs">
						<li>
							<span class="icoDot"></span>
							<strong>취소</strong>
							<a href="${ctp}/order/oList?change=cancel" class="count"><span>${cancelCnt }</span></a>
						</li>
						<li>
							<span class="icoDot"></span>
							<strong>교환</strong>
							<a href="${ctp}/order/oList?change=change" class="count"><span>${changeCnt }</span></a>
						</li>
						<li>
							<span class="icoDot"></span>
							<strong>반품</strong>
							<a href="${ctp}/order/oList?change=return" class="count"><span>${returnCnt }</span></a>
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
						<span class="data use">${vo.mPoint}원</span>
					</li>
					<li>
						<span class="title">총 적립금</span>
						<span class="data">${vo.mPoint + vo.mUsepoint}원</span>
					</li>
					<li>
						<span class="title">사용 적립금</span>
						<span class="data">${vo.mUsepoint}원</span>
					</li>
					<li class="etc">
						<span class="title">총 주문</span>
						<span class="data">${vo.mBuy}회</span>
					</li>
					<li class="etc">
						<span class="title">쿠폰</span>
						<span class="data">${couponCnt}<span>개</span></span>
						<a href="${ctp}/admin/coupon"><img src="${ctp}/images/btn_result.gif" alt="조회"></a>
					</li>
				
				</ul>
			</div>
	  	</div>
	  	
		<div class="row myshopMain">
		    <div class="col shopMain">
		    	<a href="${ctp}/order/oList">
		    		<strong>order</strong>
		    		고객님께서 주문하신 상품의 주문내역을 확인하실 수 있습니다.
		    		<br/>
		    	</a>
		    </div>
		    <div class="col shopMain">
		   		<a href="${ctp}/member/mupdate">
		    		<strong>modify</strong>
		    		회원이신 고객님의 개인정보를 관리하는 공간입니다.
		    		<br/>
		    		개인정보를 최신 정보로 유지하시면 보다 간편히 쇼핑을 즐기실 수 있습니다.
		    	</a>
		    </div>
	  	</div>
		<div class="row myshopMain">
		    <div class="col shopMain">
		    	<a href="${ctp}/wish/myWish">
		    		<strong>wish list</strong>
		    		관심상품으로 등록하신 상품의 목록을 보여드립니다.
		    		<br/>
		    	</a>
		    </div>
		    <div class="col shopMain">
		    	<a href="${ctp}/admin/coupon">
		    		<strong>coupon</strong>
		    		고객님이 보유하고 계신 쿠폰내역을 보여드립니다.
		    		<br/>
		    	</a>
		    </div>
	  	</div>
		<div class="row myshopMain">
		    <div class="col shopMain">
		    	<a href="${ctp}/member/myBoard">
		    		<strong>my board</strong>
		    		고객님께서 작성하신 게시물을 관리하는 공간입니다.
		    		<br/>
		    		고객님께서 작성하신 글을 한눈에 관리하실 수 있습니다.
		    	</a>
		    </div>
		    <div class="col shopMain">
		    	<% if(level<5){ %>
		    	<a href="javascript:mDeleteCheck();">
 		    		<strong>Delete</strong>
		    		회원 탈퇴를 하시게되면 동일한 아이디로 재가입 불가능합니다. 	
		    	</a>
		    	<%} %>
		    </div>
	  	</div>
		<div class="row myshopMain">
		    <%-- <div class="col shopMain">
		    	<a href="#">
		    		<strong>address</strong>
		    		자주 사용하는 배송지를 등록하고 관리하실 수 있습니다.
		    		<br/>
		    	</a>
		    </div>	
		    <div class="col shopMain">
		    	<% if(level<5){ %>
		    	<a href="javascript:mDeleteCheck();">
 		    		<strong>Delete</strong>
		    		회원 탈퇴
		    		
		    	</a>
		    	<%} %>
		    </div> --%>
	  	</div>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>