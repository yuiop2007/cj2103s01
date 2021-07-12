<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<script>
	function cancelCheck(oId) {
		var oStatus = $("#oStatus").val();
		var ans = confirm("취소 신청 하시겠습니까?");
		if(ans) {
			if(oStatus=="배송준비중" || oStatus=="입금전"){
				$.ajax({
					type : "get",
					url : "${ctp}/order/cancelOk",
					data : {
						oId : oId
					},
					success : function(data) {
						alert("취소 신청 되었습니다.");
						location.href="${ctp}/order/oContent?oId=" + oId;
					}
				});
			}
			else{
				alert("입금 전이거나 배송준비중일때만 가능합니다.");
			}
		}
	}
	
	function changeCheck(oId) {
		var ans = confirm("교환 신청 하시겠습니까?");
		if(ans) {
			$.ajax({
				type : "get",
				url : "${ctp}/order/changeOk",
				data : {
					oId : oId
				},
				success : function(data) {
					alert("교환 신청 되었습니다.");
					location.href="${ctp}/order/oContent?oId=" + oId;
				}
			});
		}
	}
	
	function returnCheck(oId) {
		var ans = confirm("반품 신청 하시겠습니까?");
		if(ans) {
			$.ajax({
				type : "get",
				url : "${ctp}/order/returnOk",
				data : {
					oId : oId
				},
				success : function(data) {
					alert("반품 신청 되었습니다.");
					location.href="${ctp}/order/oContent?oId=" + oId;
				}
			});
		}
	}
	
	function buyOkCheck(oId) {
		var ans = confirm("구매 확정 하시겠습니까?");
		if(ans) {
			$.ajax({
				type : "get",
				url : "${ctp}/order/buyOk",
				data : {
					oId : oId
				},
				success : function(data) {
					alert("구매가 확정되었습니다.");
					location.href="${ctp}/order/oContent?oId=" + oId;
				}
			});
		}
	}
	
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
	</div>
	<div class="container">
		<h6>주문 상세</h6>
		<br/><br/><br/><br/>
		<form name=contentform method="post">
			<input type="hidden" id="oStatus" value="${vo.oStatus}"/>
			<div class=board>
				<div class=boardview>
					<table class="boardtable">
						<tbody>
							<tr>
								<th scope="row">ORDER NUMBER</th>
								<td class="subject">${vo.oId}</td>
							</tr>
							<tr>
								<th scope="row">NAME</th>
								<td class="subject2">${vo.oName}</td>
							</tr>
							<tr>
								<th scope="row">DATE</th>
								<td class="subject2">${fn:substring(vo.oRdate,0,fn:length(vo.oRdate)-2)}</td>
							</tr>
							<tr>
								<th scope="row">PHONE</th>
								<td class="subject2">${vo.oPhone}</td>
							</tr>
							<tr>
								<th scope="row">POST</th>
								<td class="subject2">${vo.oPost}</td>
							</tr>
							<tr>
								<th scope="row">ADDRESS</th>
								<td class="subject2">${vo.oAddress}</td>
							</tr>
							<tr>
								<th scope="row">EMAIL</th>
								<td class="subject2">${vo.oEmail}</td>
							</tr>
							<tr>
								<th scope="row">PRICE</th>
								<td class="subject2"><font color="#f76560"><b><fmt:formatNumber value="${vo.oPrice}" pattern="#,###" /></b></font></td>
							</tr>
							<tr>
								<th scope="row">PAYMENT</th>
								<td class="subject2">${vo.oPayment}</td>
							</tr>
							<tr>
								<th scope="row">DELIVERY</th>
								<td class="subject2">${vo.oDelivery}</td>
							</tr>
							<tr>
								<th scope="row">PROGRESS</th>
								<td class="subject2"><font color="#f76560"><b>${vo.oChange}</b></font></td>
							</tr>
							<tr>
								<th scope="row">STATUS</th>
								<td class="subject2"><font color="#f76560"><b>${vo.oStatus}</b></font></td>
							</tr>
							<tr>
								<th scope="row">MESSAGE</th>
								<td class="subject2"><pre>${vo.oMessage}</pre></td>
							</tr>
							<tr class="view">
								<td colspan="2">
									<div class="detail">
										<div style="text-align:left;">
											<table class="table table-borderless" style="width: 500px;">
												<thead>
													<tr>
														<th scope="col" style="width: 10%; padding-left: 50px;">상품</th>
														<th scope="col" style="width: 20%;">상품명/옵션</th>
														<th scope="col" style="width: 5%; padding-left: 8px;">수량</th>
														<th scope="col" style="width: 15%; padding-left: 33px;">할인율(원)</th>
														<th scope="col" style="width: 10%; padding-left: 26px;">가격</th>
													</tr>
												</thead>
												<tbody>
												<c:set var="totPrice" value="0"></c:set>
												<c:set var="pSale" value="0"/>		
												<c:forEach var="odvo" items="${odvos}">
													<tr>
														<c:forEach var="pvo" items="${pvos}">
															<c:if test="${pvo.pId eq odvo.pId}">
																<td class="td1" style="width: 10%;">
																	<a href="${ctp}/product/pContent?pId=${odvo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}"><img src="${ctp}/resources/pMainImages/${pvo.pImage}" style="width: 100px;"/></a>
																</td>
																<td class="td2" style="width: 20%; padding-top: 50px;">
																	<a href="${ctp}/product/pContent?pId=${odvo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${pvo.pName}/${odvo.odOption}</a>
																</td>
																<td class="td1" style="width: 5%; padding-top: 50px;">${odvo.odCnt}</td>
																<td class="td1" style="width: 15%; padding-top: 50px;"><fmt:formatNumber value="${pvo.pSale}" pattern="#,###" /></td>
																<c:set var="pSale" value="${pSale + pvo.pSale }"/>
																<%-- <td class="td1" style="width: 5%; padding-top: 50px;">${vo.pPrice}</td> --%>
																<c:choose>
																	<c:when test="${pvo.pSale>0}">
																		<c:set var="salePrice" value="${odvo.odPrice-(pvo.pSale*odvo.odCnt)}" />
																		<td class="td1" style="width: 10%; padding-top: 50px;"><span style="font-size:11px;color:#000000;">&nbsp;<fmt:formatNumber value="${salePrice}" pattern="#,###" />원</span></td>
																	</c:when>
																	<c:otherwise>
																		<c:set var="salePrice" value="${odvo.odPrice}" />
																		<td class="td1" style="width: 10%; padding-top: 50px;"><span style="font-size:11px;color:#000000;"><fmt:formatNumber value="${salePrice}" pattern="#,###" />원</span></td>
																	</c:otherwise>
																</c:choose>
																<c:set var="totPrice" value="${totPrice + salePrice}"></c:set>
															</c:if>
														</c:forEach>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class=boardbtn>
					<a href="${ctp}/order/oList">목록</a>
					<a href="#" onclick="cancelCheck(${vo.oId})">주문취소</a>
					<a href="#" onclick="changeCheck(${vo.oId})">교환신청</a>
					<a href="#" onclick="returnCheck(${vo.oId})">반품신청</a>
					<a href="#" onclick="buyOkCheck(${vo.oId})">구매확정</a>
				</div>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>