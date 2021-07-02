<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp" />
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<script>
	function sChange() {
		searchForm.searchString.focus();
	}
	
	function sCheck() {
		var searchString = searchForm.searchString.value;
		if (searchString == "") {
			alert("검색어를 입력하세요");
			searchForm.searchString.focus();
		} else {
			searchForm.submit();
		}
	}
	
	
	// 페이지사이즈 처리
	function pageCheck() {
		var pageSize = pageForm.pageSize.value;
		location.href = "${ctp}/product/pList?pag=${pageVO.pag}&pageSize="+ pageSize;
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
	function SelectDelCheck() {
		var ans = confirm("선택된 모든 상품들을 삭제 하시겠습니까?");
		if (ans) {
			var delItems = "";
			for (var i = 0; i < myform.chk.length; i++) {
				if (myform.chk[i].checked == true)
					delItems += myform.chk[i].value + "/";
			}
			myform.delItems.value = delItems;
			myform.submit();
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
		<h6>상품 목록</h6>
		</br></br></br></br>
		<div style="padding: 10px 0px;">
			<form name="pForm">
				<table class="table table-borderless" style="width: 100%; margin: 0px; padding: 0px;">
					<tr>
						<td style="text-align: left;"><input type="checkbox" id="checkAll" />전체선택/해제 &nbsp; 
						<input type="checkbox" id="reverseAll" />선택반전 &nbsp; 
						<input type="button" value="선택항목 삭제" onclick="SelectDelCheck()" class="btn btn-outline-secondary btn-sm" />
						</td>
						<td style="text-align:right">
						<form name="pageForm">
							<select name="pageSize" onchange="pageCheck()" style="width:130px; float:right; text-align: left; padding: 5px 0px; margin: 0;">
									<option value="10" ${pageVO.pageSize==10 ? 'selected' : ''}>10건</option>
									<option value="15" ${pageVO.pageSize==15 ? 'selected' : ''}>15건</option>
									<option value="20" ${pageVO.pageSize==20 ? 'selected' : ''}>20건</option>
							</select>
						</form>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<form name="myform" method="post">
		<input type="hidden" name="delItems" />
		<table class="table table-borderless">
			<thead>
				<tr class="table-dark text-dark">
					<th scope="col">선택</th>
					<th scope="col">NO</th>
					<th scope="col">상품이름</th>
					<th scope="col">카테고리</th>
					<th scope="col">가격</th>
					<th scope="col">재고</th>
					<th scope="col">등록일</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="curScrStartNo" value="${pageVO.curScrStartNo}" />
			<c:forEach var="vo" items="${vos}">
				<tr>
					<td><input type="checkbox" name="chk" value="${vo.pId}" class="chk" /></td>
					<td class="ptd1">${curScrStartNo}</td>
					<td class="ptd2"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${vo.pName}</a></td>
					<td class="ptd3">${vo.pCate}</td>
					<td class="ptd4">${vo.pPrice}</td>
					<td class="ptd5">${vo.pStock}</td>
					<td class="ptd6">
						<c:if test="${vo.diffTime <= 24}">${fn:substring(vo.pRdate,11,19)}</c:if>
		        		<c:if test="${vo.diffTime > 24}">${fn:substring(vo.pRdate,0,10)}</c:if>
					</td>
				</tr>
				<c:set var="curScrStartNo" value="${curScrStartNo-1}" />
			</c:forEach>
			</tbody>
		</table>
		</form>
		<!-- 블록페이징처리 시작 -->
		<div class="container" style="text-align:center;">
		  <ul class="pagination justify-content-center">
			  <c:set var="startPageNum" value="${pageVO.pag - (pageVO.pag-1)%pageVO.blockSize}"/>  <!-- 해당블록의 시작페이지 구하기 -->
			  <c:if test="${pag != 1}">
			    <li class="page-item"><a href="${ctp}/product/pList?pag=1&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
			    <li class="page-item"><a href="${ctp}/product/pList?pag=${pageVO.pag-1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◀</a></li>
			  </c:if>
			  <c:forEach var="i" begin="0" end="${pageVO.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
			    <c:if test="${(startPageNum+i) <= pageVO.totPage}">
				  	<c:if test="${pageVO.pag == (startPageNum+i)}">
				  	  <li class="page-item active"><a href="${ctp}/product/pList?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
				  	</c:if>
				  	<c:if test="${pageVO.pag != (startPageNum+i)}">
				  	  <li class="page-item"><a href="${ctp}/product/pList?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
				  	</c:if>
			  	</c:if>
			  </c:forEach>
			  <c:if test="${pageVO.pag != pageVO.totPage}">
			    <li class="page-item"><a href="${ctp}/product/pList?pag=${pageVO.pag+1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▶</a></li>
			    <li class="page-item"><a href="${ctp}/product/pList?pag=${pageVO.totPage}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
			  </c:if>
		  </ul>
		</div>
		<!-- 블록페이징처리 끝 -->
		<!-- 검색기 처리 시작 -->
		<br/>
		<div class="container" style="text-align:center">
		  <form name="searchForm" method="get" action="${ctp}/product/pSearch">
		    <select name="search" onchange="sChange()" style="width: 120px; height: 40px;">
		    	<option value="pName" selected>상품이름</option>
		    	<option value="pCate">카테고리</option>
		    </select>
		    <input type="text" name="searchString" style="width: 250px; height: 40px;"/>
		    <a href="#" onclick="sCheck()">SEARCH</a>
		    <input type="hidden" name="pag" value="${pageVO.pag}"/>
		    <input type="hidden" name="pageSize" value="${pageVO.pageSize}"/>
		  </form>
		</div>
		<!-- 검색기 처리 끝 -->
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>