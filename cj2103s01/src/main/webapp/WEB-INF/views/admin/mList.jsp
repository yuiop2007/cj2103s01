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
	function levelCheck() {
		alert("회원정보를 변경하시려면, '정보변경'버튼을 클릭하세요...");
	}

	// 레벨별 검색처리
	function levelSearch() {
		var level = adminForm.level.value;
		location.href = "${ctp}/admin/mListLevel.ad?mLevel=" + level
				+ "&pag=${pageVO.pag}";
	}

	// 개별 아이디 검색
	function midSearch() {
		var mid = idForm.searchMid.value;
		if (mid == "") {
			alert("아이디를 입력하세요.");
			idForm.searchMid.focus();
		} else {
			location.href = "${ctp}/admin/mSearch.mem?mId=" + mid;
		}
	}

	// 레벨 변경하기
	function mLevelCheck(mid) {
		var level = $("#level" + mid).val();
		$.ajax({
			type : "get",
			url : "${ctp}/admin/mLevelCheck",
			data : {
				mId : mid,
				mLevel : level
			},
			success : function(data) {
				alert("정보변경 완료!");
			}
		});
	}

	// 개인 정보보기
	function newWin(mid) {
		var url = "${ctp}/admin/mInfor.ad?mId=" + mid;
		window.open(url, "mInfor", "width=600px, height=600px");
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

	// 선택항목 반전
	$(function() {
		$("#reverseAll").click(function() {
			$(".chk").prop("checked", function() {
				return !$(this).prop("checked");
			});
		});
	});

	// 선택항목 삭제하기
	function mSelectDelCheck() {
		var ans = confirm("선택된 모든 회원들을 삭제 하시겠습니까?");
		if (ans) {
			var delItems = "";
			for (var i = 0; i < $(".chk").length; i++) {
				if ($(".chk")[i].checked == true)
					delItems += $(".chk")[i].value + "/";
			}
			myform.delItems.value = delItems;
			myform.submit();
		}
	}
	
	function pointCheck() {
		var ans = confirm("선택된 모든 회원에게 포인트를 추가하시겠습니까?");
		if (ans) {
			var delItems = "";
			var point = adminForm.pnum.value;
			for (var i = 0; i < $(".chk").length; i++) {
				if ($(".chk")[i].checked == true)
					delItems += $(".chk")[i].value + "/";
			}
			
			$.ajax({
				type : "post",
				url : "${ctp}/admin/mPointCheck",
				data : {
					delItems : delItems,
					point : point
				},
				success : function(data) {
					alert("포인트추가 완료!");
				}
			});
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
		<h6>회원 목록</h6>
		</br> </br> </br> </br>
		<div style="padding: 10px 0px;">
			<form name="adminForm">
				<table class="table table-borderless" style="width: 100%; margin: 0px; padding: 0px;">
					<tr>
						<td style="text-align: left;"><input type="checkbox" id="checkAll" />전체선택/해제 &nbsp; 
						<input type="checkbox" id="reverseAll" />선택반전 &nbsp; 
						<input type="button" value="삭제" onclick="mSelectDelCheck()" class="btn btn-outline-secondary btn-sm" />&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
						<input type="number" min="0" id="pnum" value="0" class="btn btn-outline-secondary btn-sm" style="width: 80px; padding: 3px; border: 1px solid #ccc;"/>
						<input type="button" value="포인트 추가" onclick="pointCheck()" class="btn btn-outline-secondary btn-sm" /></td>
						<td style="text-align: right">
						<c:if test="${slevel == 0}">
						      회원등급
						    <select name="level" onchange="levelSearch()" style="width: 100px;">
								<option value="" <c:if test="${empty stringLevel}">selected</c:if>>전체회원</option>
								<option value="1" <c:if test="${stringLevel=='1'}">selected</c:if>>특별회원</option>
								<option value="2" <c:if test="${stringLevel=='2'}">selected</c:if>>우수회원</option>
								<option value="3" <c:if test="${stringLevel=='3'}">selected</c:if>>정회회원</option>
								<option value="4" <c:if test="${stringLevel=='4'}">selected</c:if>>준회원</option>
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
					<th>선택</th>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>성별</th>
					<c:if test="${slevel == 0}">
						<th style="width: 20%;">등급</th>
						<th>탈퇴유무</th>
					</c:if>
				</tr>
				<c:set var="curScrStartNo" value="${pageVO.curScrStartNo}" />
				<c:forEach var="vo" items="${vos}">
					<tr>
						<td><input type="checkbox" name="chk" value="${vo.mId}" class="chk" /></td>
						<td>${curScrStartNo}</td>
						<td><a href="javascript:newWin('${vo.mId}');">${vo.mId}</a></td>
						<td><c:if test="${slevel == 0}">
								<a href="javascript:newWin('${vo.mId}');">${vo.mName}</a>
							</c:if> <c:if test="${slevel != 0}">${vo.mName}</c:if></td>
						<td>${vo.mGender}</td>
						<c:if test="${slevel == 0}">
							<td>
									<select name="mLevel" id="level${vo.mId}" onchange="levelCheck()" style="width: 80px; padding: 3px; border: 1px solid #ccc;">
										<option value="1" <c:if test="${vo.mLevel==1}">selected</c:if>>특별회원</option>
										<option value="2" <c:if test="${vo.mLevel==2}">selected</c:if>>우수회원</option>
										<option value="3" <c:if test="${vo.mLevel==3}">selected</c:if>>정회원</option>
										<option value="4" <c:if test="${vo.mLevel==4}">selected</c:if>>준회원</option>
									</select> <input type="button" value="정보변경" onclick="mLevelCheck('${vo.mId}')" /> 
									<input type="hidden" name="mId" value="${vo.mId}" /> 
									<input type="hidden" name="pag" value="${pageVO.pag}" />
							</td>
							<td><c:if test="${vo.mDrop == 'YES'}">
									<font color="red">${vo.mDrop}</font>
								</c:if> <c:if test="${vo.mDrop != 'YES'}">${vo.mDrop}</c:if></td>
						</c:if>
					</tr>
					<c:set var="curScrStartNo" value="${curScrStartNo-1}" />
				</c:forEach>
			</table>
			
		</form>
		<!-- 페이징처리 시작 -->
		<c:if test="${pageVO.totPage != 0}">

			<div style="text-align: center">
				<c:if test="${pageVO.pag != 1}">[<a href="${ctp}/admin/aMList.ad?pag=1">1페이지</a>]....</c:if>
				<c:if test="${pageVO.pag > 1}">[<a href="${ctp}/admin/aMList.ad?pag=${pageVO.pag-1}">이전페이지</a>]</c:if>
				${pageVO.pag}Page / ${pageVO.totPage}Pages
				<c:if test="${pageVO.pag < pageVO.totPage}">[<a href="${ctp}/admin/aMList.ad?pag=${pageVO.pag+1}">다음페이지</a>]</c:if>
				<c:if test="${pageVO.pag != pageVO.totPage}">....[<a href="${ctp}/admin/aMList.ad?pag=${pageVO.totPage}">마지막페이지</a>]</c:if>
			</div>

		</c:if>
		<!-- 페이징처리 끝 -->
		<p>
			<br />
		</p>
		<form name="idForm">
			<div style="text-align: center;">
				<%-- <input type="text" name="mid" <c:if test="${mid != ''}">value="${mid}"</c:if> placeholder="검색할아이디입력"/> --%>
				<input type="text" name="searchMid" value="${vo.mId}" placeholder="검색할아이디입력" style="width: 150px;" /> 
				<input type="button" value="개별검색" onclick="midSearch()" /> 
				<input type="button" value="전체검색" onclick="location.href='${ctp}/admin/mList.ad';" />
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>