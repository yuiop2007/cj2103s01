<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>객실예약관리</title>
	<%@ include file="/WEB-INF/views/include/bs.jsp" %>
	<script src="${ctp}/resources/ckeditor/ckeditor.js"></script>
	<script>
	  $(document).ready(function() {
    	$("#roomListViewHidden").hide();
    	$("#roomInputViewHidden").hide();
    });
	  
	  // 객실조회
    function roomListView() {
    	var roomList = '';
    	roomList += '<h3>객실 리스트</h3>';
    	roomList += '<c:forEach var='vo' items="${vos}">';
    	roomList += '<table class="table table-bordered">';
    	roomList += '<tr><th>객실명</th><td>${vo.room}</td></tr>';
    	roomList += '<tr><th>객실내역</th><td>${vo.content}</td></tr>';
    	roomList += '</table>';
    	roomList += '<div style="text-align:right;"><input type="button" value="객실정보삭제" class="btn btn-secondary btn-sm" onclick=roomDelete("${vo.room}")></div><hr/>';
    	roomList += '</c:forEach>';
    	
    	$("#roomListViewHidden").show();
    	$("#roomListView").hide();
    	$("#roomView").slideDown(500);
    	$("#roomView").html(roomList);
    	
    	$("#roomInputViewHidden").hide();
    	$("#roomInputView").show();
    }
    
    // 일정조회닫기
    function roomListViewHidden() {
    	$("#roomView").slideUp(500);
    	$("#roomListViewHidden").hide();
    	$("#roomListView").show();
    	$("#roomInputViewHidden").hide();
    	$("#roomInputView").show();
    }
    
    // 일정 등록
    function roomInputView() {
    	var roomInput = "";
    	roomInput += "<h3>객실 등록</h3>";
    	roomInput += "<form name='myform' method='post' enctype='multipart/form-data' action='${ctp}/admin/reservation/roomInputOk'>";
    	roomInput += "<table style='width:80%;' class='table table-bordered'>";
    	roomInput += "<tr><th>객실명</th><td><input type='text' name='room' class='form-control' autofocus></td></tr>";
    	roomInput += "<tr><th>객실정보</th><td><textarea name='content' rows='5' id='CKEDITOR' class='form-control'></textarea></td></tr>";
    	roomInput += "</table>";
    	roomInput += "<div style='text-align:right;width:80%;'><input type='button' value='객실등록' class='btn btn-secondary' onclick='roomInputOk()'></div>";
    	roomInput += "<script>";
    	roomInput += "CKEDITOR.replace('content',{";
    	roomInput += "uploadUrl:'${ctp}/reservation/imageUpload',";     				
    	roomInput += "filebrowserUploadUrl: '${ctp}/reservation/imageUpload',";
    	roomInput += "height:400";
    	roomInput += "});";
    	roomInput += "<\/script>";
    	roomInput += "</form>";
    	
    	$("#roomInputViewHidden").show();
    	$("#roomInputView").hide();
    	$("#roomView").slideDown(500);
    	$("#roomView").html(roomInput);
    	
    	$("#roomListViewHidden").hide();
    	$("#roomListView").show();
    }
    
    // 객실 등록
    function roomInputOk() {
    	var room = myform.room.value;
    	if(room == "") {
    		alert("객실명을 입력하세요")
    		myform.room.focus();
    		return false;
    	}
    	else {
    		myform.submit();
    	}
    }
    
    // 객실 등록창 닫기
    function roomInputViewHidden() {
    	$("#roomView").slideUp(500);
    	$("#roomInputViewHidden").hide();
    	$("#roomInputView").show();
    	$("#roomListViewHidden").hide();
    	$("#roomListView").show();
    }
    
    // 객실정보 삭제
    function roomDelete(room) {
    	var ans = confirm("현재 객실정보를 삭제처리할까요?");
    	if(ans) {
    		location.href="${ctp}/admin/reservation/roomDelete?room="+room;
    	}
    }
    
    // 객실명만 검색해서 보여주기
    function roomItem() {
    	var roomItem = '';
    	roomItem += '<h3>객실 리스트</h3>';
    	roomItem += '<c:forEach var='vo' items="${vos}">';
    	roomItem += '<table class="table table-bordered">';
    	roomItem += '<tr>';
    	roomItem += '<th>객실명</th><td>${vo.room}</td>';
    	roomItem += '<td><a href=javascript:windowView("${vo.room}")>상세보기</a></td>';
    	roomItem += '</tr>';
    	roomItem += '</table>';
    	roomItem += '</c:forEach>';
    	
    	$("#roomView").slideDown(500);
    	$("#roomView").html(roomItem);
    }
    
    // 객실 1개씩 새창으로 보기
    function windowView(room) {
    	var url = "${ctp}/admin/reservation/roomView?room="+room;
    	window.open(url,"roomView","width=850,height=800");
    }
	</script>
	<style>
	  th {background-color: #eee;}
	</style>
</head>
<body>
<p><br/></p>
<div class="container">
  <h3>객실 현황 리스트(총 <font color="blue"><b><a href="javascript:roomItem()">${roomCnt}</a></b></font>건의 객실이 있습니다.)</h3>
  <hr/>
  <input type="button" value="객실사용여부"  onclick="location.href='roomAvailable';" id="roomAvailable" class="btn btn-outline-secondary btn-sm"/>
  <input type="button" value="객실전체조회"  onclick="roomListView()" id="roomListView" class="btn btn-outline-secondary btn-sm"/>
  <input type="button" value="객실조회창닫기" onclick="roomListViewHidden()" id="roomListViewHidden" class="btn btn-secondary btn-sm"/>
  <input type="button" value="객실등록"  onclick="roomInputView()" id="roomInputView" class="btn btn-outline-secondary btn-sm"/>
  <input type="button" value="등록창닫기" onclick="roomInputViewHidden()" id="roomInputViewHidden" class="btn btn-secondary btn-sm"/>
  <hr/>
  <div id="roomView"></div>
  <hr/>
</div>
<p><br/></p>
</body>
</html>