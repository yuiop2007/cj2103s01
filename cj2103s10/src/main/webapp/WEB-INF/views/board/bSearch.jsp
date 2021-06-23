<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>bSearch.jsp(검색프로그램)</title>
  <jsp:include page="/WEB-INF/views/include/bs.jsp"/>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
  <script>
    function sChange() {
    	searchForm.searchString.focus();
    }
    
    function sCheck() {
    	var searchString = searchForm.searchString.value;
    	if(searchString == "") {
    		alert("검색어를 입력하세요");
    		searchForm.searchString.focus();
    	}
    	else {
    		searchForm.submit();
    	}
    }
  </script>
  <style>
    th, td {text-align: center};
  </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
<jsp:include page="/WEB-INF/views/include/slide.jsp"/>
<div class="container">
  <p><br/></p>
  <table class="table table-borderless">
    <tr>
      <td colspan="2">
        <h2>게 시 판 검 색 리 스 트</h2>
        <br/>
        (<font color="blue"><b>${searchTitle}</b></font>(으)로 <font color="red"><b>${searchString}</b></font>(을)를 검색한 결과 <font color="blue"><b>${searchCount}</b></font>건이 검색되었습니다.)
      </td>
    </tr>
    <tr>
      <td style="text-align:left;margin:0px;padding:0px;">
        <input type="button" value="글올리기" onclick="location.href='${ctp}/board/bInput';" class="btn btn-secondary"/>
      </td>
      <td style="text-align:right;margin:0px;padding:0px;">
        &nbsp;
      </td>
    </tr>
  </table>
  <table class="table table-hover">
    <tr class="table-dark text-dark">
      <th>글번호</th>
      <th>글제목</th>
      <th>글쓴이</th>
      <th>글쓴날자</th>
      <th>조회수</th>
      <th>좋아요</th>
    </tr>
    <c:set var="curScrStartNo" value="${pageVO.curScrStartNo}"/>
    <c:forEach var="vo" items="${vos}">
		    <tr>
		      <td>${curScrStartNo}</td>
		      <td style="text-align:left;">
		        <a href="${ctp}/board/bContent?idx=${vo.idx}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${vo.title}</a>
		        <c:if test="${vo.diffTime <= 24}"><img src="${ctp}/images/new.gif"/></c:if>
		        <%-- <c:if test="${vo.replyCount != 0}">(${vo.replyCount})</c:if> --%>
		      </td>
		      <td>${vo.name}</td>
		      <td>
		        <c:if test="${vo.diffTime <= 24}">${fn:substring(vo.wDate,11,19)}</c:if>
		        <c:if test="${vo.diffTime > 24}">${fn:substring(vo.wDate,0,10)}</c:if>
		      </td>
		      <td>${vo.readNum}</td>
		      <td>${vo.good}</td>
		    </tr>
		    <c:set var="curScrStartNo" value="${curScrStartNo-1}"/>
	    </c:forEach>
  </table>
  <br/>
</div>
<!-- 블록페이징처리 시작 -->
<div class="container" style="text-align:center;">
  <ul class="pagination justify-content-center">
	  <c:set var="startPageNum" value="${pageVO.pag - (pageVO.pag-1)%pageVO.blockSize}"/>  <!-- 해당블록의 시작페이지 구하기 -->
	  <c:if test="${pageVO.pag != 1}">
	    <li class="page-item"><a href="${ctp}/board/bSearch?pag=1&pageSize=${pageVO.pageSize}&search=${search}&searchString=${searchString}" class="page-link" style="color:#666">◁◁</a></li>
	    <li class="page-item"><a href="${ctp}/board/bSearch?pag=${pageVO.pag-1}&pageSize=${pageVO.pageSize}&search=${search}&searchString=${searchString}" class="page-link" style="color:#666">◀</a></li>
	  </c:if>
	  <c:forEach var="i" begin="0" end="${pageVO.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
	    <c:if test="${(startPageNum+i) <= pageVO.totPage}">
		  	<c:if test="${pageVO.pag == (startPageNum+i)}">
		  	  <li class="page-item active"><a href="${ctp}/board/bSearch?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}&search=${search}&searchString=${searchString}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
		  	</c:if>
		  	<c:if test="${pageVO.pag != (startPageNum+i)}">
		  	  <li class="page-item"><a href="${ctp}/board/bSearch?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}&search=${search}&searchString=${searchString}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
		  	</c:if>
	  	</c:if>
	  </c:forEach>
	  <c:if test="${pageVO.pag != pageVO.totPage}">
	    <li class="page-item"><a href="${ctp}/board/bSearch?pag=${pageVO.pag+1}&pageSize=${pageVO.pageSize}&search=${search}&searchString=${searchString}" class="page-link" style="color:#666">▶</a></li>
	    <li class="page-item"><a href="${ctp}/board/bSearch?pag=${pageVO.totPage}&pageSize=${pageVO.pageSize}&search=${search}&searchString=${searchString}" class="page-link" style="color:#666">▷▷</a></li>
	  </c:if>
  </ul>
</div>
<!-- 블록페이징처리 끝 -->
<br/>
<!-- 검색기 처리 시작 -->
<div class="container" style="text-align:center">
  <form name="searchForm" method="get" action="${ctp}/board/bSearch">
    <b>검색 : </b>
    <select name="search" onchange="sChange()">
    	<option value="title" selected>글제목</option>
    	<option value="name">글쓴이</option>
    	<option value="content">글내용</option>
    </select>
    <input type="text" name="searchString"/>
    <input type="button" value="검 색" onclick="sCheck()"/>
    <input type="hidden" name="pag" value="${pageVO.pag}"/>
    <input type="hidden" name="pageSize" value="${pageVO.pageSize}"/>
  </form>
</div>
<!-- 검색기 처리 끝 -->
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>