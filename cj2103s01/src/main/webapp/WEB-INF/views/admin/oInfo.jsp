<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

	
</script>
</head>
<body>
	<div class="container">
		<br/><br/><br/><br/>
		<form name=contentform method="post">
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
								<td class="subject2"><font color="#f76560"><b>${vo.oPhone}</b></font></td>
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
								<td class="subject2"><font color="#f76560"><b>${vo.oPrice}</b></font></td>
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
																<td class="td1" style="width: 15%; padding-top: 50px;">${pvo.pSale}</td>
																<c:set var="pSale" value="${pSale + pvo.pSale }"/>
																<%-- <td class="td1" style="width: 5%; padding-top: 50px;">${vo.pPrice}</td> --%>
																<c:choose>
																	<c:when test="${pvo.pSale>0}">
																		<c:set var="salePrice" value="${odvo.odPrice-(pvo.pSale*odvo.odCnt)}" />
																		<td class="td1" style="width: 10%; padding-top: 50px;"><span style="font-size:11px;color:#000000;">&nbsp;${salePrice}원</span></td>
																	</c:when>
																	<c:otherwise>
																		<c:set var="salePrice" value="${odvo.odPrice}" />
																		<td class="td1" style="width: 10%; padding-top: 50px;"><span style="font-size:11px;color:#000000;">${salePrice}원</span></td>
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
					<a href="#" onclick="window.close()">창닫기</a>
					<a href="#" onclick="cancleCheck()">주문취소</a>
					<a href="#" onclick="changeCheck()">교환신청</a>
					<a href="#" onclick="returnCheck()">반품신청</a>
					<a href="#" onclick="buyOkCheck()">구매확정</a>
				</div>
				<br/><br/><br/><br/><br/><br/><br/><br/>
			</div>
		</form>
	</div>
</body>
</html>