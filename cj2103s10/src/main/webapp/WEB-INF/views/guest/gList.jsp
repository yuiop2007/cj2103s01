<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>gList.jsp(방명록 리스트)-일반</title>
  <%@ include file="/WEB-INF/views/include/bs.jsp" %>
  <%@ include file="/WEB-INF/views/include/slide.jsp" %>
  <style>
    th {
      background-color: #ccc;
      text-align: center;
    }
  </style>
  <script>
    // 게시글 삭제함수 : 해당 게시글의 고유번호(idx)를 받아서 삭제서블릿(gDelete_comment)호출
    function delCheck(idx) {
    	var ans = confirm("게시글을 삭제 하시겠습니까?");
    	if(ans) location.href="<%=request.getContextPath()%>/gDelete_comment?idx="+idx;
    }
  </script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<div class="container">
  <p><br/></p>
  <table class="table table-borderless">
    <tr>
      <th colspan="2" style="text-align:center; background-color:#fff"><h2>방 명 록 리 스 트</h2></th>
    </tr>
    <tr>
      <td>
        <c:if test="${smid eq 'admin'}">
          <a href='${ctp}/guest/adminLogout' class='btn btn-outline-secondary btn-sm'>관리자로그아웃</a>
        </c:if>
        <c:if test="${smid ne 'admin'}">
          <a href='${ctp}/guest/adminLogin' class='btn btn-outline-secondary btn-sm'>관리자</a>
        </c:if>
      </td>
      <td style="text-align:right;">
        <a href="${ctp}/guest/gInput" class="btn btn-outline-secondary btn-sm">글쓰기</a>
      </td>
    </tr>
  </table>
  <c:forEach var="vo" items="${vos}">
	  <table class="table table-borderless">
	    <tr>
	      <td>
	        방문번호 : ${vo.idx} &nbsp;
	        <c:if test="${smid eq 'admin'}">
		        <a href='javascript:delCheck(${vo.idx})' class='btn btn-outline-secondary btn-sm'>삭제</a>	
	        </c:if>
	      </td>
	      <td style="text-align:right;">방문IP : ${vo.hostip }</td>
	    </tr>
	  </table>
	  <table class="table table-bordered">
	  	<tr>
	  	  <th style="width:10%;">성명</th>
	  	  <td style="width:35%;">${vo.name}</td>
	  	  <th style="width:10%;">전자우편</th>
	  	  <td style="width:45%;">${vo.email}</td>
	  	</tr>
	  	<tr>
	  	  <th>방문일자</th>
	  	  <td>${vo.vdate}</td>
	  	  <th>홈페이지</th>
	  	  <td>
	        <a href="${vo.homepage}" target='_blank'>${vo.homepage}</a>
				</td>
	  	</tr>
	  	<tr>
	  	  <th>방문소감</th>
	  	  <td colspan="3">${fn:replace(vo.content, newLine, '<br/>')}</td>
	  	</tr>
	  </table>
	  <br/>
  </c:forEach>
</div>
<br/>
<!-- 블록페이징처리 시작 -->
<div class="container" style="text-align:center;">
  <ul class="pagination justify-content-center">
	  <c:set var="startPageNum" value="${pageVo.pag - (pageVo.pag-1)%pageVo.blockSize}"/>  <!-- 해당블록의 시작페이지 구하기 -->
	  <c:if test="${pag != 1}">
	    <li class="page-item"><a href="${ctp}/guest/gList?pag=1&pageSize=${pageVo.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
	    <li class="page-item"><a href="${ctp}/guest/gList?pag=${pageVo.pag-1}&pageSize=${pageVo.pageSize}" class="page-link" style="color:#666">◀</a></li>
	  </c:if>
	  <c:forEach var="i" begin="0" end="${pageVo.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
	    <c:if test="${(startPageNum+i) <= pageVo.totPage}">
		  	<c:if test="${pageVo.pag == (startPageNum+i)}">
		  	  <li class="page-item active"><a href="${ctp}/guest/gList?pag=${startPageNum+i}&pageSize=${pageVo.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
		  	</c:if>
		  	<c:if test="${pageVo.pag != (startPageNum+i)}">
		  	  <li class="page-item"><a href="${ctp}/guest/gList?pag=${startPageNum+i}&pageSize=${pageVo.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
		  	</c:if>
	  	</c:if>
	  </c:forEach>
	  <c:if test="${pageVo.pag != pageVo.totPage}">
	    <li class="page-item"><a href="${ctp}/guest/gList?pag=${pageVo.pag+1}&pageSize=${pageVo.pageSize}" class="page-link" style="color:#666">▶</a></li>
	    <li class="page-item"><a href="${ctp}/guest/gList?pag=${pageVo.totPage}&pageSize=${pageVo.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
	  </c:if>
  </ul>
</div>
<!-- 블록페이징처리 끝 -->
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>