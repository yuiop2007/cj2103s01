<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
</script>
<style>
.boardbtn{
	margin-right: 10px;
}
</style>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
		<h6>주문내역</h6>
		<br/><br/><br/><br/>
		<div class="xans-element- xans-order xans-order-dcinfo ec-base-box typeMember  ">
			<div class="information">
           		<h3 class="title">내역조회</h3>
	            <div class="description" style="display: flex;">
	                <div class=boardbtn>
						<a href="${ctp}/order/oList?time=7&status=${status}&change=${change}">1주일</a>
					</div>
	                <div class=boardbtn>
						<a href="${ctp}/order/oList?time=30&status=${status}&change=${change}">1개월</a>
					</div>
	                <div class=boardbtn>
						<a href="${ctp}/order/oList?time=90&status=${status}&change=${change}">3개월</a>
					</div>
	                <div class=boardbtn>
						<a href="${ctp}/order/oList?time=180&status=${status}&change=${change}">6개월</a>
					</div>
				</div>
			</div>
		</div>
		<form name="orderForm" method="post" action="${ctp}/order/orderInfo">

			<table class="table table-borderless">
				<thead>
					<tr>
						<th scope="col" style="width: 5%;">NO</th>
						<th scope="col" style="width: 10%;"></th>
						<th scope="col" style="width: 35%; text-align: left;">대표상품명/옵션</th>
						<th scope="col" style="width: 10%;">상품구매금액</th>
						<th scope="col" style="width: 10%;">배송상태</th>
						<th scope="col" style="width: 10%;">주문상태</th>
						<th scope="col" style="width: 10%;">주문일자</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="ovo" items="${ovos}">
					<tr>
						<td class="td1" style="width: 5%; padding-top: 55px;">${ovo.oId}</td>
						<c:forEach var="pvo" items="${pvos}">
						<c:forEach var="odvo" items="${odvos}">
							<c:if test="${ovo.oId eq odvo.oId}">
							<c:if test="${pvo.pId eq odvo.pId}">
								<td class="td1" style="width: 10%;">
									<img src="${ctp}/resources/pMainImages/${pvo.pImage}" style="width: 80px;"/>
								</td>
								<td class="td2" style="width: 35%; text-align: left; padding-top: 55px;">
									<a href="${ctp}/order/oContent?oId=${ovo.oId}">${pvo.pName}/${odvo.odOption}</a>
								</td>
								<td class="td1" style="width: 10%; padding-top: 55px;"><fmt:formatNumber value="${ovo.oPrice}" pattern="#,###" /></td>
								<td class="td1" style="width: 10%; padding-top: 55px;">
									${ovo.oStatus}
								</td>
								<td class="td1" style="width: 10%; padding-top: 55px;">${ovo.oChange}</td>
								<td class="td1" style="width: 10%; padding-top: 55px;">
									<c:if test="${ovo.diffTime <= 24}">${fn:substring(ovo.oRdate,11,19)}</c:if>
				        			<c:if test="${ovo.diffTime > 24}">${fn:substring(ovo.oRdate,0,10)}</c:if>
								</td>
							</c:if>
							</c:if>
						</c:forEach>
						</c:forEach>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<br/><br/><br/>
		</form>
	</div>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>