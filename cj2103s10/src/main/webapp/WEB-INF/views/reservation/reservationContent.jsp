<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>reservationContent.jsp(예약 내용 상세보기)</title>
  <script>
    // 객실 정보보기
    function newWin(room) {
    	var url = "${ctp}/reservation/roomInformation?room="+room;
    	window.open(url,"roomView","width=850,height=800");
    }
    
    // 회원 정보보기
    function newMemberWin(mid) {
    	var url = "memberView?mid="+mid;
    	window.open(url,"memberView","width=780,height=600");
    }
  </script>
  <style>
    th {background-color:#eee; text-align:center;}
  </style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<p><br/></p>
<div class="container">
  <form name="myform" method="post">
    <c:set var="ymd" value="${fn:substring(vo.reservationDate,0,10)}"/>
    <h3>객실 예약 내역(예약일자 : <font color="red"><b>${ymd}</b></font>)</h3>
    <hr/>
    <table class="table table-bordered">
      <tr>
        <th>객실명</th>
        <td><a href="javascript:newWin('${vo.room}')">${vo.room}</a></td>
      </tr>
      <tr>
        <th>예약자명</th>
        <td>
	        <c:if test="${slevel != 0}">${vo.nickName}</c:if>
	        <c:if test="${slevel == 0}"><a href="javascript:newMemberWin('${vo.mid}')">${vo.nickname}</a></c:if>
        </td>
      </tr>
      <tr>
        <th>인원수</th>
        <td>${vo.person}</td>
      </tr>
      <tr>
        <th>기타사항</th>
        <td><textarea rows="4" name="content" class="form-control" readonly>${vo.content}</textarea></td>
      </tr>
    </table>
    <table class="table table-bordered">
      <tr>
        <th style="text-align:center;">공 지 사 항</th>
      </tr>
      <tr>
        <th>
          <textarea rows="7" class="form-control" readonly>
          
            - 사용기간은 다음날 11시까지 입니다.
            - 각 객실에는 에어컨 등이 마련되어 있습니다.
            - 일부 객실에는 발코니가 딸려 있습니다.
            - 모든 숙박 옵션에는 전자레인지 등이 마련되어 있습니다.
          </textarea>
        </th>
      </tr>
    </table>
    <table class="table table-borderless">
      <tr>
        <c:if test="${slevel != 0}">
	        <td style="text-align:left;">
				    <input type="button" value="내용수정" onclick="location.href='reservationUpdate?idx=${vo.idx}&ymd=${ymd}';" class="btn btn-secondary"/>
	        </td>
        </c:if>
        <td style="text-align:right;">
			    <input type="button" value="돌아가기" onclick="history.back();" class="btn btn-secondary"/>
        </td>
      </tr>
    </table>
    <input type="hidden" name="reservationDate" value="${ymd}"/>
    <input type="hidden" name="reservationRoom" value="${vo.room}"/>
    <input type="hidden" name="mid" value="${smid}"/>
  </form>
</div>
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>