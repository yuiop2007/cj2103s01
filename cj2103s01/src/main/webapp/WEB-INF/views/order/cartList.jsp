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
	// 전체선택
	$(function() {
		$("#checkAll").click(function() {
			if ($("#checkAll").prop("checked")) {
				$(".chk").prop("checked", true);
			} else {
				$(".chk").prop("checked", false);
			}
		});
	});
	
	function cartDelete() {
		var ans = confirm("장바구니에서 삭제하시겠습니까?");
		if (ans) {
			var delItems = "";
			for (var i = 0; i < $(".chk").length; i++) {
				if ($(".chk")[i].checked == true)
					delItems += $(".chk")[i].value + "/";
			}
			if(delItems==""){
				alert("상품을 선택하세요.");
				location.href="${ctp}/order/cartList";
			}
			else {
				$.ajax({
					type : "post",
					url : "${ctp}/order/cartDelete",
					data : {
						delItems : delItems,
					},
					success : function(data) {
						alert("삭제되었습니다!");
						location.href="${ctp}/order/cartList";
					}
				});
			}
		}
	}
	
	function cartOrder() {
		var ans = confirm("선택한 상품을 주문하시겠습니까?");
		if (ans) {
			var delItems = "";
			for (var i = 0; i < $(".chk").length; i++) {
				if ($(".chk")[i].checked == true)
					delItems += $(".chk")[i].value + "/";
			}
			if(delItems==""){
				alert("상품을 선택하세요.");
			}
			else{
				orderForm.delItems.value = delItems;
				orderForm.submit();
			}
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
		<br/><br/><br/><br/>
		<form name="orderForm" method="get" action="${ctp}/order/orderInfo">
			<input type="hidden" name="delItems"/>
			<table class="table table-borderless">
				<thead>
					<tr>
						<th scope="col" style="width: 10%;">ALL&nbsp;<input type="checkbox" id="checkAll" /></th>
						<th scope="col" style="width: 20%;"></th>
						<th scope="col" style="width: 35%; text-align: left;">상품명/옵션</th>
						<th scope="col" style="width: 5%;">수량</th>
						<th scope="col" style="width: 10%;">할인율(원)</th>
						<th scope="col" style="width: 10%;">가격</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="totPrice" value="0"></c:set>
				<c:set var="pSale" value="0"/>
				<c:forEach var="vo" items="${vos}">
					<tr>
						<td class="td1" style="width: 10%; padding-top: 70px;"><input type="checkbox" name="chk" value="${vo.cartId}" class="chk" /></td>
						<c:forEach var="pvo" items="${pvos}">
							<c:if test="${pvo.pId eq vo.pId}">
								<td class="td1" style="width: 20%;">
									<a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}"><img src="${ctp}/resources/pMainImages/${pvo.pImage}" style="width: 100px;"/></a>
								</td>
								<td class="td2" style="width: 35%; text-align: left; padding-top: 70px;">
									<a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${pvo.pName}/${vo.pOption}</a>
								</td>
								<td class="td1" style="width: 15%; padding-top: 70px;">${vo.pCnt}</td>
								<td class="td1" style="width: 10%; padding-top: 70px;">${pvo.pSale}</td>
								<c:set var="pSale" value="${pSale + pvo.pSale }"/>
								<%-- <td class="td1" style="width: 5%; padding-top: 70px;">${vo.pPrice}</td> --%>
								<c:choose>
									<c:when test="${pvo.pSale>0}">
										<c:set var="salePrice" value="${vo.pPrice-(pvo.pSale*vo.pCnt)}" />
										<td class="td1" style="width: 10%; padding-top: 70px;"><span style="font-size:11px;color:#000000;">&nbsp;${salePrice}원</span></td>
									</c:when>
									<c:otherwise>
										<c:set var="salePrice" value="${vo.pPrice}" />
										<td class="td1" style="width: 10%; padding-top: 70px;"><span style="font-size:11px;color:#000000;">${salePrice}원</span></td>
									</c:otherwise>
								</c:choose>
								<c:set var="totPrice" value="${totPrice + salePrice}"></c:set>
							</c:if>
						</c:forEach>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<table class="table table-borderless" style="width: 100%; margin: 0px; padding: 0px;">
				<tr>
					<td style="text-align: right;">
						<div class=boardbtn>
							<a href="#" onclick="cartOrder()" style="background-color: #111; color: #fff;">선택상품주문</a>&nbsp;
							<a href="#" onclick="cartDelete()">장바구니비우기</a>
							<a href="${ctp}/">쇼핑계속하기</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
		<br/><br/><br/>
		<div class="totalsummary">
			<table border="1" surmmary>
				<colgroup>
					<col style="width:23%;">
					<col style="width:24%;">
					<col style="width:23%;" class="displaynone">
					<col style="width:auto;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><span>총 상품금액</span></th>
                   	    <th scope="col">총 배송비</th>
	                    <th scope="col">총 할인율(원)</th>
	                    <th scope="col">결제예정금액</th>
				   	 </tr>
                </thead>
                <tbody>
	                <tr> 
						<td class="price"><div class="box"><strong>${totPrice + pSale}</strong>원 <span class="tail displaynone"></span></div></td>
						<td class="option"><div class="box"><strong>+</strong>
						<strong>
						<c:set var="Delivery" value="3000"/>
						<c:if test="${totPrice>60000}"><c:set var="Delivery" value="0"/></c:if>
						${Delivery}
						</strong>원 <span class="tail displaynone"></span></div></td>
						<td class="discount displaynone"><strong>-</strong><strong>${pSale}</strong>원</td>
						<c:set var="orderPrice" value="${totPrice + Delivery}"/>
						<td class="total"><div class="box"><strong>=</strong><strong>${orderPrice}</strong>원 <span class="tail displaynone"></span></div></td>
	                </tr>
                </tbody>
			</table>
		</div>
		
		<div class="cart">
		        <h4>장바구니 이용안내</h4>
		        <ul>
					<li class="item1">- 전체상품 주문은 [ALL]체크버튼 선택 후 [선택상품주문]버튼으로 구매하시길 바랍니다.</li>
		            <li class="item2">- 장바구니를 비우시려면 원하시는 상품의 체크버튼을 체크하신 후 [장바구니비우기] 버튼을 누르시면 됩니다.</li>
		            <li class="item3">- 선택하신 상품의 수량을 변경하시려면 제품을 누르신뒤 원하시는 옵션을 선택후 다시 등록하시면 됩니다.</li>
		            <li class="item4">- [쇼핑계속하기] 버튼을 누르시면 쇼핑을 계속 하실 수 있습니다.</li>
		            <li class="item5">- 장바구니와 관심상품을 이용하여 원하시는 상품만 주문하거나 관심상품으로 등록하실 수 있습니다.</li>
		        </ul>
		</div>
	</div>
	
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>