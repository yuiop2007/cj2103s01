<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>roomAvailable.jsp(객실사용유무관리)</title>
	<%@ include file="/WEB-INF/views/include/bs.jsp" %>
	<script src="${ctp}/resources/ckeditor/ckeditor.js"></script>
	<script>
    // 객실 1개씩 새창으로 보기
    function windowView(room) {
    	var url = "${ctp}/admin/reservation/roomView?room="+room;
    	window.open(url,"roomView","width=850,height=800");
    }
    
    // 객실정보 삭제
    function roomDelete(room) {
    	var ans = confirm("현재 객실정보를 삭제처리할까요?");
    	if(ans) {
    		location.href="${ctp}/admin/reservation/roomDelete?room="+room;
    	}
    }
    
    // 객실 사용여부 변경
    function roomModify(room) {
    	var ans = confirm("사용여부를 변경하시겠습니까?");
    	if(!ans) return false;
    	var available = document.getElementById("available"+room).value;
    	$.ajax({
    		type  : "get",
    		url   : "roomModify",
    		data  : {
    			room      : room,
    			available : available
    		},
    		success : function(data) {
    			alert("수정되었습니다.");
  				location.reload();
  			}
  		});
    }
	</script>
	<style>
	  th {background-color: #eee;}
	  td {text-align: center;}
	</style>
</head>
<body>
<p><br/></p>
<div class="container">
  <h3>객실 사용 유무 선택</h3>
  <hr/>
  <form name="myform" method="post">
    <div>
      <table class="table">
        <tr style="text-align:center;">
          <th>번호</th>
          <th>객실명</th>
          <th>사용여부</th>
          <th>비고</th>
        </tr>
        <c:set var="roomCnt" value="${roomCnt}"/>
        <c:forEach var="vo" items="${vos}">
          <tr>
            <td>${roomCnt}</td>
            <td><a href="javascript:windowView('${vo.room}')">${vo.room}</a></td>
            <td>
               <select name="available${vo.room}" id="available${vo.room}">
                 <option value="OK" <c:if test="${vo.available=='OK'}">selected</c:if>>사용가능</option>
                 <option value="NO" <c:if test="${vo.available=='NO'}">selected</c:if>>수리중</option>
               </select>
            </td>
            <td>
              <a href="javascript:roomModify('${vo.room}')" class="btn btn-outline-secondary btn-sm">${vo.room}정보변경</a> &nbsp;
              <a href="javascript:roomDelete('${vo.room}')" class="btn btn-outline-secondary btn-sm">삭제</a>
            </td>
          </tr>
          <c:set var="roomCnt" value="${roomCnt - 1}"/>
        </c:forEach>
      </table>
    </div>
    <hr/>
  	<input type="button" value="돌아가기" onclick="location.href='roomList';" class="btn btn-secondary btn-sm"/>
  </form>
  <hr/>
</div>
<p><br/></p>
</body>
</html>