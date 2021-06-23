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
		location.href = "${ctp}/board/event?pag=${pageVO.pag}&pageSize="+ pageSize;
	}
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
		<h6>이벤트</h6>
		</br>
		</br>
		</br>
		</br>
		<form name="pageForm">
			<table class="table table-borderless">
				<thead>
					<tr>
						<th scope="col">NO</th>
						<th scope="col"></th>
						<th scope="col">
							<% if (level == 0) { %>
								<a href="${ctp}/board/eInput">작성하기</a>
							<% } %>
						</th>
						<th scope="col" style="padding-bottom: 5px;">
						
						<select name="pageSize" onchange="pageCheck()" style="text-align: left; padding: 5px 0px; margin: 0;">
								<option value="10" ${pageVO.pageSize==10 ? 'selected' : ''}>10건</option>
								<option value="15" ${pageVO.pageSize==15 ? 'selected' : ''}>15건</option>
								<option value="20" ${pageVO.pageSize==20 ? 'selected' : ''}>20건</option>
						</select></tr>
				</thead>
				<tbody>
				<c:set var="curScrStartNo" value="${pageVO.curScrStartNo}" />
				<c:forEach var="vo" items="${vos}">
					<tr>
						<td class="td1">${curScrStartNo}</td>
						<td class="td2">
							<a href="${ctp}/board/eContent?idx=${vo.eId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${vo.eTitle}</a>
						</td>
						<td class="td3">${vo.eName}</td>
						<td class="td4">
							<c:if test="${vo.diffTime <= 24}">${fn:substring(vo.eRdate,11,19)}</c:if>
		        			<c:if test="${vo.diffTime > 24}">${fn:substring(vo.eRdate,0,10)}</c:if>
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
			  <c:set var="startPageNum" value="${pageVO.pag - (pageVO.pag-1)%pageVO.blockSize}" />  <!-- 해당블록의 시작페이지 구하기 -->
			  <c:if test="${pag != 1}">
			    <li class="page-item"><a href="${ctp}/board/event?pag=1&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
			    <li class="page-item"><a href="${ctp}/board/event?pag=${pageVO.pag-1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◀</a></li>
			  </c:if>
			  <c:forEach var="i" begin="0" end="${pageVO.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
			    <c:if test="${(startPageNum+i) <= pageVO.totPage}">
				  	<c:if test="${pageVO.pag == (startPageNum+i)}">
				  	  <li class="page-item active"><a href="${ctp}/board/event?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
				  	</c:if>
				  	<c:if test="${pageVO.pag != (startPageNum+i)}">
				  	  <li class="page-item"><a href="${ctp}/board/event?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
				  	</c:if>
			  	</c:if>
			  </c:forEach>
			  <c:if test="${pageVO.pag != pageVO.totPage}">
			    <li class="page-item"><a href="${ctp}/board/event?pag=${pageVO.pag+1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▶</a></li>
			    <li class="page-item"><a href="${ctp}/board/event?pag=${pageVO.totPage}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
			  </c:if>
		  </ul>
		</div>
		<!-- 블록페이징처리 끝 -->
		<!-- 검색기 처리 시작 -->
		<br/>
		<div class="container" style="text-align:center">
		  <form name="searchForm" method="get" action="${ctp}/board/eSearch">
		    <select name="search" onchange="sChange()" style="width: 100px; height: 40px;">
		    	<option value="eTitle" selected>제목</option>
		    	<option value="eWriter">글쓴이</option>
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