<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>reservationUpdate.jsp(예약 내용 수정하기)</title>
  <script>
    function newWin(room) {
    	var url = "${ctp}/reservation/roomInformation?room="+room;
    	window.open(url,"roomView","width=850,height=800");
    }
    
    function reservationMessage() {
    	alert("예약일자를 수정하시려면 '예약취소'후 다시 예약하세요.\n이곳에서는 기본 내역만 수정하실수 있습니다.");
    	myform.content.focus();
    }
  </script>
  <style>
    th {background-color:#eee; text-align:center;}
  </style>
</head>
<body onload="reservationMessage()">
<c:if test="${slevel != 0}">
	<%@ include file="/WEB-INF/views/include/nav.jsp" %>
	<%@ include file="/WEB-INF/views/include/slide.jsp" %>
</c:if>
<c:if test="${slevel == 0}"><%@ include file="/WEB-INF/views/include/bs.jsp" %></c:if>
<p><br/></p>
<div class="container">
  <form name="myform" method="post">
    <c:set var="ymd" value="${fn:substring(vo.reservationDate,0,10)}"/>
    <h3>객실 예약 내역(예약일자 : <font color="red"><b>${ymd}</b></font>)</h3>
    <hr/>
    <table class="table table-bordered">
      <tr>
        <th>객실명</th>
        <td><a href="javascript:newWin(${vo.room})">${vo.room}</a></td>
      </tr>
      <tr>
        <th>예약자명</th>
        <td>${vo.nickname}</td>
      </tr>
      <tr>
        <th>인원수</th>
        <td>
          <select name="person" class="form-control">
            <option value="1" <c:if test="${vo.person==1}">selected</c:if>>1명</option>
            <option value="2" <c:if test="${vo.person==2}">selected</c:if>>2명</option>
            <option value="3" <c:if test="${vo.person==3}">selected</c:if>>3명</option>
            <option value="4" <c:if test="${vo.person==4}">selected</c:if>>4명</option>
          </select>
        </td>
      </tr>
      <tr>
        <th>기타사항</th>
        <td><textarea rows="4" name="content" class="form-control">${vo.content}</textarea></td>
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
        <td style="text-align:left;">
			    <input type="submit" value="내용수정" class="btn btn-secondary"/>
        </td>
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