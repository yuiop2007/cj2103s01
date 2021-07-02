<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>scMenu.jsp</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script>
    $(document).ready(function() {
    	$("#scheduleListHidden").hide();
    	$("#scheduleInputHidden").hide();
    });
  
    // 일정조회
    function scheduleListView() {
    	var scheduleList = "";
    	scheduleList += "<c:forEach var='vo' items='${vos}'>";
    	scheduleList += "<table class='table table-bordered'>";
    	scheduleList += "<tr><th>일정 분류</th><td>${vo.memory}</td></tr>";
    	scheduleList += "<tr><th>제 목</th><td>${vo.title}</td></tr>";
    	scheduleList += "<tr><th>내 용</th><td>${fn:replace(vo.content,newLine,'<br/>')}</td></tr>";
    	scheduleList += "</table>";
    	scheduleList += "<div style='text-align:right;'><input type='button' value='일정삭제' class='btn btn-secondary btn-sm' onclick='scheduleDeleteOk(${vo.idx})'></div><hr/>";
    	scheduleList += "</c:forEach>";
    	
    	$("#scheduleListHidden").show();
    	$("#scheduleListView").hide();
    	$("#scheduleView").slideDown(500);
    	$("#scheduleView").html(scheduleList);
    	
    	$("#scheduleInputHidden").hide();
    	$("#scheduleInputView").show();
    }
    
    // 일정조회닫기
    function scheduleViewHidden() {
    	$("#scheduleView").slideUp(500);
    	$("#scheduleListHidden").hide();
    	$("#scheduleListView").show();
    	$("#scheduleInputHidden").hide();
    	$("#scheduleInputView").show();
    }
    
    // 일정 등록
    function scheduleInputView() {
    	var scheduleInput = "<form name='myform'>";
    	scheduleInput += "<table style='width:80%;' class='table table-bordered'>";
    	scheduleInput += "<tr><th>일정 분류</th><td>";
    	scheduleInput += "<div class='form-group'>";
    	scheduleInput += "<select name='memory' class='form-control'>";
    	scheduleInput += "<option value='모임' selected>모임</option>";
    	scheduleInput += "<option value='업무'>업무</option>";
    	scheduleInput += "<option value='학습'>학습</option>";
    	scheduleInput += "<option value='여행'>여행</option>";
    	scheduleInput += "<option value='기타'>기타</option>";
    	scheduleInput += "</select>";
    	scheduleInput += "</div>";
    	scheduleInput += "</td></tr>";
    	scheduleInput += "<tr><th>제 목</th><td><input type='text' name='title' class='form-control' autofocus></td></tr>";
    	scheduleInput += "<tr><th>내 용</th><td><textarea name='content' rows='3' class='form-control'></textarea></td></tr>";
    	scheduleInput += "</table>";
    	scheduleInput += "<div style='text-align:right;width:80%;'><input type='button' value='일정등록' class='btn btn-secondary' onclick='scheduleInputOk()'></div>";
    	scheduleInput += "</form>";
    	
    	$("#scheduleInputHidden").show();
    	$("#scheduleInputView").hide();
    	$("#scheduleView").slideDown(500);
    	$("#scheduleView").html(scheduleInput);
    	
    	$("#scheduleListHidden").hide();
    	$("#scheduleListView").show();
    }
    
    // 일정 등록
    function scheduleInputOk() {
    	var memory = myform.memory.value;
    	var title = myform.title.value;
    	var content = myform.content.value;
    	if(title == "") {
    		alert("일정 제목을 입력하세요")
    		myform.title.focus();
    		return false;
    	}
  		if(content == "") content = title;
    	var query = {
    		mid    : '${smid}',
    		ymd    : '${ymd}',
    		memory : memory,
    		title  : title,
    		content: content
    	}
    	
    	$.ajax({
    		type : "post",
    		url  : "${ctp}/schedule/scheduleInputOk",
    		data : query,
    		success : function(data) {
    			alert("일정이 등록되었습니다.");
    			location.reload();
    		}
    	});
    }
    
    // 일정삭제
    function scheduleDeleteOk(idx) {
    	var ans = confirm("현재 일정을 삭제처리할까요?");
    	if(ans) {
	    	$.ajax({
	    		type : "get",
	    		url  : "${ctp}/schedule/scheduleDeleteOk",
	    		data : {idx : idx},
	    		success: function(data) {
	    			alert("일정이 삭제되었습니다.");
	    			location.reload();
	    		}
	    	});
    	}
    }
  </script>
  <style>
    th {
      background-color : #eee;
      width : 18%;
    }
  </style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<br/>
<div class="container">
  <h4><font color="blue"><b>${ymd}</b></font> 일정 입니다.</h4><br/>
  - 오늘의 일정은 총 <font color="red"><b>${scheduleCnt}</b></font> 건 있습니다.
  <hr/>
  <c:if test="${scheduleCnt != 0}">
    <input type="button" value="일정조회" onclick="scheduleListView()" id="scheduleListView" class="btn btn-outline-secondary"/>
  </c:if>
  <input type="button" value="조회창닫기" onclick="scheduleViewHidden()" id="scheduleListHidden" class="btn btn-secondary"/>
  <input type="button" value="일정등록" onclick="scheduleInputView()" id="scheduleInputView" class="btn btn-outline-secondary"/>
  <input type="button" value="등록창닫기" onclick="scheduleViewHidden()" id="scheduleInputHidden" class="btn btn-outline-secondary"/>
  <input type="button" value="돌아가기" onclick="location.href='${ctp}/schedule/schedule';" class="btn btn-outline-secondary"/>
  <hr/>
  <div id="scheduleView"></div>
</div>
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>