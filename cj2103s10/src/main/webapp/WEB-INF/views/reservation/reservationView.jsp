<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>reservationView.jsp(예약현황 상세보기)</title>
  <script>
    function cancelCheck(idx) {
    	var ans = confirm("일정을 취소하시겠습니까?");
    	if(ans)	location.href = "reservationCancel?idx="+idx;
    }

    function newWin(room) {
    	var url = "${ctp}/reservation/roomInformation?room="+room;
    	window.open(url,"roomView","width=850,height=800");
    }
  </script>
  <style>
    td {text-align: center;}
  </style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<p><br/></p>
<div class="container">
  <h3 style="text-align:center;"><font color="blue">${snickname}</font>님 예약현황</h3>
  <div>
    <div style="float:left; margin:15px 0;">최근 예약순으로 출력됩니다.</div>
    <div style="float:right; margin:15px 0;"><input type="button" value="돌아가기" onclick="location.href='reservation';"/></div>
    <c:set var="cnt" value="${fn:length(vos)}"/>
    <div style="clear:both;">
	    <!-- <table class="table table-bordered table-hover"> -->
	    <table class="table table-borderless table-hover">
	      <tr class="table-dark text-dark" style="text-align:center;">
	        <th>번호</th>
	        <th>예약일자</th>
	        <th>객실명</th>
	        <th>상세보기</th>
	        <th>총 <font color="blue">${cnt}</font> 건</th>
	      </tr>
	      <c:forEach var="vo" items="${vos}" varStatus="st">
		      <tr>
		        <td>${cnt}</td>
		        <td>${fn:substring(vo.reservationDate,0,10)}</td>
		        <td><a href="javascript:newWin('${vo.room}')">${vo.room}</a></td>
		        <td><a href="reservationContent?idx=${vo.idx}" class="btn btn-outline-secondary btn-sm">상세보기</a></td>
		        <td><a href="javascript:cancelCheck(${vo.idx})" class="btn btn-outline-secondary btn-sm">취소하기</a></td>
		      </tr>
		      <c:set var="cnt" value="${cnt -1}"/>
		      <c:if test="${fn:substring(vo.reservationDate,0,7) != fn:substring(vos[st.count].reservationDate,0,7)}">
		        <tr><td colspan="5"><hr/></td></tr>
		      </c:if>
	      </c:forEach>
	    </table>
    </div>
    <input type="button" value="돌아가기" onclick="location.href='reservation';"/>
  </div>
</div>
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>