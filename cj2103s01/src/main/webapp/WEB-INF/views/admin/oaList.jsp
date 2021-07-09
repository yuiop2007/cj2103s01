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
<style>
.table td, .table th {
	padding: .75rem;
	vertical-align: middle;
	border-top: 1px solid #dee2e6;
}
</style>
<script>

	function statusSearch() {
		var status = adminForm.status.value;
		location.href = "${ctp}/admin/oaList?status=" + status
				+ "&change=${change}&pag=${pageVO.pag}";
	}
	
	function changeSearch() {
		var change = adminForm.change.value;
		location.href = "${ctp}/admin/oaList?status=${status}&change=" + change
				+ "&pag=${pageVO.pag}";
	}

	// 개별 아이디 검색
	function midSearch() {
		var mid = idForm.searchMid.value;
		if (mid == "") {
			alert("아이디를 입력하세요.");
			idForm.searchMid.focus();
		} else {
			location.href = "${ctp}/admin/oSearch?mId=" + mid;
		}
	}

	// 배송상태 변경하기
	function oStatusCheck() {
		var ans = confirm("선택된 모든 주문들의 상태를 변경하시겠습니까?");
		if (ans) {
			var delItems = "";
			var oStatus = "";
			for (var i = 0; i < $(".chk").length; i++) {
				if ($(".chk")[i].checked == true){
					delItems += $(".chk")[i].value + "/";
					oStatus += $("#oStatus" + $(".chk")[i].value).val() + "/";
				}
			}
			$.ajax({
				type : "get",
				url : "${ctp}/admin/oStatusCheck",
				data : {
					status : oStatus,
					delItems : delItems
				},
				success : function(data) {
					if(data=="false"){
						alert("실패하였습니다!");
					}
					else {
						alert("정보변경 완료!");
						location.href="${ctp}/admin/oaList";
					}
				}
			});
		}
	}

	// 주문 정보보기
	function newWin(mId, oId) {
		var url = "${ctp}/admin/oInfo?mId="+mId+"&oId=" + oId;
		window.open(url, "oInfo", "width=800px, height=600px");
	}

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


</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
		<h6>주문 목록</h6>
		<br/><br/><br/><br/>
		<div style="padding: 10px 0px;">
			<form name="adminForm">
				<table class="table table-borderless" style="width: 100%; margin: 0px; padding: 0px;">
					<tr>
						<td style="text-align: left;"><input type="checkbox" id="checkAll" />전체선택/해제 &nbsp; 
						<input type="button" value="정보변경" onclick="oStatusCheck()" /> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
						<td style="text-align: right">
						<c:if test="${slevel == 0}">
						      배송상태
						    <select name="status" onchange="statusSearch()" style="width: 100px;">
								<option value="All" <c:if test="${status eq 'All'}">selected</c:if>>전체</option>
								<option value="입금전" <c:if test="${status eq '입금전'}">selected</c:if>>입금전</option>
								<option value="배송준비중" <c:if test="${status eq  '배송준비중'}">selected</c:if>>배송준비중</option>
								<option value="배송중" <c:if test="${status eq '배송중'}">selected</c:if>>배송중</option>
								<option value="배송완료" <c:if test="${status eq '배송완료'}">selected</c:if>>배송완료</option>
							</select>
						</c:if>
						<c:if test="${slevel == 0}">
						      주문상태
						    <select name="change" onchange="changeSearch()" style="width: 100px;">
								<option value="All" <c:if test="${change eq 'All'}">selected</c:if>>전체</option>
								<option value="취소" <c:if test="${change eq '취소'}">selected</c:if>>취소</option>
								<option value="교환" <c:if test="${change eq '교환'}">selected</c:if>>교환</option>
								<option value="반품" <c:if test="${change eq '반품'}">selected</c:if>>반품</option>
								<option value="구매확정" <c:if test="${change eq '구매확정'}">selected</c:if>>구매확정</option>
							</select>
						</c:if></td>
					</tr>
				</table>
			</form>
		</div>
		<form name="myform" method="post">
			<input type="hidden" name="delItems" />
			<input type="hidden" name="point" />
			<table class="table table-hover">
				<tr class="table-dark text-dark">
					<th style="width: 5%;">선택</th>
					<th style="width: 8%;">주문번호</th>
					<th style="width: 10%;">아이디</th>
					<th>대표상품명/옵션</th>
					<th style="width: 10%;">상품구매금액</th>
					<th scope="col" style="width: 8%;">배송상태</th>
					<th scope="col" style="width: 8%;">주문상태</th>
					<th scope="col" style="width: 9%;">주문일자</th>
				</tr>
				<c:forEach var="ovo" items="${ovos}">
					<tr>
						<td style="width: 5%;"><input type="checkbox" name="chk" value="${ovo.oId}" class="chk" /></td>
						<td style="width: 8%;">${ovo.oId}</td>
						<c:forEach var="pvo" items="${pvos}">
						<c:forEach var="odvo" items="${odvos}">
							<c:if test="${ovo.oId eq odvo.oId}">
							<c:if test="${pvo.pId eq odvo.pId}">
								<td style="width: 10%;">${ovo.mId}</td>
								<td><a href="javascript:newWin('${ovo.mId}','${ovo.oId}');">${pvo.pName}/${odvo.odOption}</a></td>
								<td style="width: 10%;">${ovo.oPrice}</td>
								<td style="width: 8%;">
									<select name="oStatus" id="oStatus${ovo.oId}" style="width: 90px; padding: 3px; border: 1px solid #ccc;">
										<option value="입금전" <c:if test="${ovo.oStatus eq '입금전'}">selected</c:if>>입금전</option>
										<option value="배송준비중" <c:if test="${ovo.oStatus eq  '배송준비중'}">selected</c:if>>배송준비중</option>
										<option value="배송중" <c:if test="${ovo.oStatus eq '배송중'}">selected</c:if>>배송중</option>
										<option value="배송완료" <c:if test="${ovo.oStatus eq '배송완료'}">selected</c:if>>배송완료</option>
									</select>
									<input type="hidden" name="mId" value="${vo.mId}" /> 
									<input type="hidden" name="pag" value="${pageVO.pag}" />
								</td>
								<td style="width: 8%;">${ovo.oChange}</td>
								<td style="width: 9%;">
									<c:if test="${ovo.diffTime <= 24}">${fn:substring(ovo.oRdate,11,19)}</c:if>
				        			<c:if test="${ovo.diffTime > 24}">${fn:substring(ovo.oRdate,0,10)}</c:if>
								</td>
							</c:if>
							</c:if>
						</c:forEach>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
			
		</form>
		<!-- 페이징처리 시작 -->
		<c:if test="${pageVO.totPage != 0}">

			<div style="text-align: center">
				<c:if test="${pageVO.pag != 1}">[<a href="${ctp}/admin/aaList?status=${status}&change=${change}&pag=1">1페이지</a>]....</c:if>
				<c:if test="${pageVO.pag > 1}">[<a href="${ctp}/admin/aaList?status=${status}&change=${change}&pag=${pageVO.pag-1}">이전페이지</a>]</c:if>
				${pageVO.pag}Page / ${pageVO.totPage}Pages
				<c:if test="${pageVO.pag < pageVO.totPage}">[<a href="${ctp}/admin/aaList?status=${status}&change=${change}&pag=${pageVO.pag+1}">다음페이지</a>]</c:if>
				<c:if test="${pageVO.pag != pageVO.totPage}">....[<a href="${ctp}/admin/aaList?status=${status}&change=${change}&pag=${pageVO.totPage}">마지막페이지</a>]</c:if>
			</div>

		</c:if>
		<!-- 페이징처리 끝 -->
		<p>
			<br />
		</p>
		<form name="idForm">
			<div style="text-align: center;">
				<input type="text" name="searchMid" value="${ovo.mId}" placeholder="검색할아이디입력" style="width: 150px;" /> 
				<input type="button" value="검색" onclick="midSearch()" /> 
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>