<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>reservationStatusView.jsp(관리자 예약현황 상세보기)</title>
  <script>
    function newWin(room) {
    	var url = "${ctp}/reservation/roomInformation?room="+room;
    	window.open(url,"roomView","width=850,height=800");
    }
    
    function roomSearch() {
    	var room = document.getElementById("room").value;
    	//location.href = "roomStatusSearch?room="+room;
    	location.href = "reservationStatusViewList?room="+room;
    }
  </script>
  <style>
    td {text-align: center;}
  </style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/bs.jsp" %>
<p><br/></p>
<div class="container">
  <c:if test="${empty room}"><c:set var="room" value="전체"/></c:if>
  <h3 style="text-align:center;"><font color="blue">${room}</font> 예약 현황 리스트</h3>
  <div>
    <div style="float:left; margin:15px 0;">객실선택
      <select name="room" id="room" onchange="roomSearch()">
        <option value="전체">전체</option>
        <c:forEach var="vo" items="${roomVos}">
	        <option value="${vo.room}" <c:if test="${vo.room == room}">selected</c:if>>${vo.room}</option>
        </c:forEach>
      </select>
      &nbsp; &nbsp; &nbsp; 최근 예약순으로 출력됩니다.
    </div>
    <c:if test="${slevel != 0}"><div style="float:right; margin:15px 0;"><input type="button" value="돌아가기" onclick="location.href='reservation';"/></div></c:if>
    <c:set var="cnt" value="${fn:length(vos)}"/>
    <div style="clear:both;">
	    <!-- <table class="table table-bordered table-hover"> -->
	    <table class="table table-borderless table-hover table-sm">
	      <tr class="table-dark text-dark" style="text-align:center;">
	        <th>번호</th>
	        <th>예약일자</th>
	        <th>객실명</th>
	        <th>상세보기</th>
	        <th>총 <font color="blue">${cnt}</font> 건</th>
	      </tr>
	      <c:set var="curScrStartNo" value="${pageVO.curScrStartNo}"/>
	      <c:forEach var="vo" items="${vos}" varStatus="st">
		      <tr>
		        <td>${curScrStartNo}</td>
		        <td>${fn:substring(vo.reservationDate,0,10)}</td>
		        <td><a href="javascript:newWin('${vo.room}')">${vo.room}</a></td>
		        <td colspan="2"><a href="reservationAdminContent?idx=${vo.idx}" class="btn btn-outline-secondary btn-sm">상세보기</a></td>
		      </tr>
		      <c:if test="${fn:substring(vo.reservationDate,0,7) != fn:substring(vos[st.count].reservationDate,0,7)}">
		        <tr><td colspan="5"><hr/></td></tr>
		      </c:if>
		      <c:set var="cnt" value="${cnt -1}"/>
		      <c:set var="curScrStartNo" value="${curScrStartNo - 1}"/>
	      </c:forEach>
	    </table>
    </div>
    <c:if test="${slevel != 0}"><input type="button" value="돌아가기" onclick="location.href='reservation';"/></c:if>
  </div>
</div>
<br/>
<!-- 블록 페이징처리 시작 -->
<div class="container" style="text-align:center">
  <ul class="pagination justify-content-center">
	  <c:set var="startPageNum" value="${pageVO.pag - (pageVO.pag-1)%pageVO.blockSize}"/>
	  <c:if test="${pageVO.pag != 1}">
	    <li class="page-item"><a href="reservationStatusViewList?pag=1&pageSize=${pageVO.pageSize}&room=${room}" class="page-link" style="color:#666">◁◁</a></li>
	    <li class="page-item"><a href="reservationStatusViewList?pag=${pageVO.pag-1}&pageSize=${pageVO.pageSize}&room=${room}" class="page-link" style="color:#666">◀</a></li>
	  </c:if>
	  <c:forEach var="i" begin="0" end="2">
	    <c:if test="${(startPageNum+i) <= pageVO.totPage}">
	      <c:if test="${pageVO.pag == (startPageNum+i)}">
	        <li class="page-item active"><a href="reservationStatusViewList?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}&room=${room}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff"><b>${startPageNum+i}</b></font></a></li>
	      </c:if>
	      <c:if test="${pageVO.pag != (startPageNum+i)}">
	        <li class="page-item"><a href="reservationStatusViewList?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}&room=${room}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
	      </c:if>
	    </c:if>
	  </c:forEach>
	  <c:if test="${pageVO.pag != pageVO.totPage}">
	    <li class="page-item"><a href="reservationStatusViewList?pag=${pageVO.pag+1}&pageSize=${pageVO.pageSize}&room=${room}" class="page-link" style="color:#666">▶</a></li>
	    <li class="page-item"><a href="reservationStatusViewList?pag=${pageVO.totPage}&pageSize=${pageVO.pageSize}&room=${room}" class="page-link" style="color:#666">▷▷</a></li>
	  </c:if>
	</ul>
</div>
<!-- 블록 페이징처리 끝 -->
<p><br/></p>
</body>
</html>