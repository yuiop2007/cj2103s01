<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>bContent.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs.jsp"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <style>
    th, td {text-align: center;}
    th {background-color: #eee;}
  </style>
  <script>
	  //댓글 보이기/가리기
	  $(document).ready(function(){
	  	$("#reply").show();         // 처음 로딩시에 '전체 댓글'은 출력시킨다.
	  	$("#replyViewBtn").hide();  // '댓글보이기'버튼은 감춘다.
	  	//$(".replyBoxClose").hide(); // 모든 댓글 '닫기' 버튼 감춘다.
	  	
	  	$("#replyViewBtn").click(function(){  // 댓글 보이기 버튼 클릭시에...
	  		$("#reply").slideDown(500);           // 댓글 리스트를 출력한다.
	  		$("#replyViewBtn").hide();    // 댓글보이기 버튼은 감춘다.
	  		$("#replyHiddenBtn").show();  // 댓글감추기 버튼은 보여준다.
	  	});
	  	$("#replyHiddenBtn").click(function(){  // 댓글 감추기 버튼 클릭시에...
	  		$("#reply").slideUp(500);           // 댓글 리스트를 감춘다.
	  		$("#replyViewBtn").show();    // 댓글보이기 버튼은 보여준다.
	  		$("#replyHiddenBtn").hide();  // 댓글감추기 버튼은 감춘다.
	  	});
	  });
  
    // 좋아요 횟수 증가 처리
    function goodCheck() {
    	var query = {
    			idx : ${vo.idx}
    	};
    	
    	$.ajax({
    		type : "get",
    		url  : "${ctp}/board/bGood",
    		data : query,
    		success:function(data) {
    			location.reload();
    		}
    	});
    }
    
    
    // 게시글 수정처리를 위한 비밀번호 체크
    function updCheck() {
    	var pwd = myform.pwd.value;
    	if(pwd == "") {
    		alert("비밀번호를 입력하세요")
    		myform.pwd.focus();
    	}
    	else {
    		myform.submit();
    	}
    }
    
    // 게시글 삭제처리
    function delCheck() {
    	var ans = confirm("게시글을 삭제하시겠습니까?");
    	var pwd = myform.pwd.value;
    	
    	if(ans) {
    		if(pwd == "") {
        		alert("비밀번호를 입력하세요")
        		myform.pwd.focus();
        		return false;
        }
    		else {
    			location.href = "${ctp}/bDelete.bo?idx=${vo.idx}&pwd="+pwd+"&pag=${pag}";
    		}
    	}
    }
    
    // 댓글 입력처리
    function replyCheck() {
    	var boardIdx = "${vo.idx}";
    	var mid = "${smid}";
    	var nickName = "${snickname}";
    	var hostIp = "${pageContext.request.remoteAddr}";
    	var content = replyForm.content.value;
    	
    	if(content == "") {
    		alert("댓글을 입력하세요?");
    		replyForm.content.focus();
    		return false;
    	}
    	
    	var query = {
    			boardIdx : boardIdx,
    			mid      : mid,
    			nickName : nickName,
    			hostIp   : hostIp,
    			content  : content
    	}
    	
    	$.ajax({
    		type : "get",
    		url  : "${ctp}/board/bReplyInsert",
    		data : query,
    		success : function(data) {
    			if(data == "1") {
    				//alert("댓글 입력완료!!!");
    				location.reload();
    			}
    		}
    	});
    }
    
    // 댓글 삭제(aJax 처리)
    function replyDelCheck(replyIdx) {
    	var query = {replyIdx : replyIdx};
    	var ans = confirm("선택하신 댓글을 삭제하시겠습니까?");
    	if(!ans) return false;
    	
    	$.ajax({
    		type : "get",
    		url  : "${ctp}/board/bReplyDelete",
    		data : query,
    		success: function(data) {
    			//alert("삭제처리 되었습니다.");
    			location.reload();
    		}
    	});
    }
    
    // 대댓글(답변글) 입력처리
    function insertReply(idx,level,levelOrder,nickName) {
    	var insReply = "";
    	insReply += "<table style='width:90%'>";
    	insReply += "<tr>";
    	insReply += "<td>";
    	insReply += "<div class='form-group'>";
    	insReply += "<label for='content'>답변 댓글 달기:</label> &nbsp;";
    	insReply += "<input type='text' name='nickName' size='6' value='${snickname}' readonly/>";
    	insReply += "<textarea rows='3' class='form-control' name='content' id='content"+idx+"'>@"+nickName+"\n</textarea>";
    	insReply += "</div>";
    	insReply += "</td>";
    	insReply += "<td>";
    	insReply += "<input type='button' value='답글달기' onclick='replyCheck2("+idx+","+level+","+levelOrder+")'/>";
    	insReply += "</td>";
    	insReply += "</tr>";
    	insReply += "</table>";
    	insReply += "<hr style='margin:0px'/>";
    	
    	$("#replyBoxOpenBtn"+idx).hide();  // '답글'버튼 가리기
    	$("#replyBoxCloseBtn"+idx).show();  // '닫기'버튼 보이기
    	$("#replyBox"+idx).slideDown(500);
    	$("#replyBox"+idx).html(insReply);
    }
    
    // 대댓글 입력폼 닫기처리
    function closeReply(idx) {
    	$("#replyBoxOpenBtn"+idx).show();  // '답글'버튼 보이기
    	$("#replyBoxCloseBtn"+idx).hide();  // '닫기'버튼 가리기
    	$("#replyBox"+idx).slideUp(500);
    }
    
    // 대댓글(답변글) 입력처리
    function replyCheck2(idx, level, levelOrder) {
    	var boardIdx = "${vo.idx}";
    	var mid = "${smid}";
    	var nickName = "${snickname}";
    	var hostIp = "${pageContext.request.remoteAddr}";
    	var content = "content"+idx;
    	var contentVal = document.getElementById(content).value;
    	
    	if(contentVal == "") {
    		alert("답변글을 입력하세요?");
    		$("#"+content).focus();
    		return false;
    	}
    	else {
	    	var query = {
	    			boardIdx : boardIdx,
	    			mid      : mid,
	    			nickName : nickName,
	    			hostIp   : hostIp,
	    			content  : contentVal,
	    			level    : level,
	    			levelOrder : levelOrder
	    	}
	    	
	    	$.ajax({
	    		type : "get",
	    		url  : "${ctp}/board/bReplyInsert2",
	    		data : query,
	    		success : function(data) {
	    			location.reload();
	    		}
	    	});  // ajax종료
    	}
    }
  </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
<jsp:include page="/WEB-INF/views/include/slide.jsp"/>
<div class="container">
  <p><br/></p>
  <h2>글 내 용 보 기</h2>
  <br/>
  <form name="myform" method="post">
	  <table class="table table-bordered">
	    <tr>
	      <th>글쓴이</th>
	      <td>${vo.name}</td>
	      <th>글쓴날짜</th>
	      <td>${fn:substring(vo.wDate,0,fn:length(vo.wDate)-2)}</td>
	    </tr>
	    <tr>
	      <th>Email</th>
	      <td>${vo.email}</td>
	      <th>접속IP</th>
	      <td>${vo.hostIp}</td>
	    </tr>
	    <tr>
	      <th>조회수</th>
	      <td>${vo.readNum}</td>
	      <th>좋아요</th>
	      <td><!-- 이모지호출(윈도우키+'.')  -->
	        <input type="button" value="🧡" onclick="goodCheck()"/> &nbsp; &nbsp;<font color="red"><b>${vo.good}</b></font>
	      </td>
	    </tr>
	    <tr>
	      <th>글제목</th>
	      <td colspan="3" style="text-align:left;">${vo.title}</td>
	    </tr>
	    <tr>
	      <th>글내용</th>
	      <td colspan="3" style="height:200px; text-align:left;">${fn:replace(vo.content,newLine,"<br/>")}</td>
	    </tr>
	    <tr>
	      <th>비밀번호</th>
	      <td colspan="3" style="text-align:left;"><input type="password" name="pwd"/></td>
	    </tr>
	    <tr>
	      <td colspan="4" style="text-align:center;">
	        <input type="button" value="수정" onclick="updCheck()" class="btn btn-secondary"/> &nbsp;
	        <input type="button" value="삭제" onclick="delCheck()" class="btn btn-secondary"/> &nbsp;
	        <input type="button" value="돌아가기" onclick="location.href='${ctp}/board/bList.bo?pag=${pag}&pageSize=${pageSize}';" class="btn btn-secondary"/>
	      </td>
	    </tr>
	  </table>
	  <input type="hidden" name="pag" value="${pag}"/>
	  <input type="hidden" name="pageSize" value="${pageSize}"/>
	  <input type="hidden" name="idx" value="${vo.idx}"/>
  </form>
  <p></p>
</div>

<!-- 이전글/다음글 처리 -->
<div class="container">
  <table class="table table-borderless">
    <tr>
      <td style="text-align:left;">
        <c:if test="${nextVo.nextIdx != 0}">
        	👆 <a href="${ctp}/bContent.bo?idx=${nextVo.nextIdx}&pag=${pag}&pageSize=${pageSize}">다음글 : ${nextVo.nextTitle}</a><br/>
        </c:if>
        <c:if test="${preVo.preIdx != 0}">
	        👇 <a href="${ctp}/bContent.bo?idx=${preVo.preIdx}&pag=${pag}&pageSize=${pageSize}">이전글 : ${preVo.preTitle}</a><br/>
        </c:if>
      </td>
    </tr>
  </table>
</div>
<p></p>

<!-- 아래로 댓글 처리(출력/입력) -->
<div class="container">
  <div>
    <input type="button" value="댓글보이기" id="replyViewBtn" class="btn btn-secondary">
    <input type="button" value="댓글가리기" id="replyHiddenBtn" class="btn btn-secondary">
  </div>
  <!-- 댓글 출력처리 -->
  <div id="reply">
	  <table class="table table-borderless table-striped table-hover">
	    <tr class="table-dark text-dark">
	      <th>작성자</th>
	      <th>댓글내용</th>
	      <th>작성일자</th>
	      <th>접속IP</th>
	      <th>답글</th>
	    </tr>
	    <c:forEach var="cVo" items="${cVos}">
	      <c:if test="${cVo.level <= 0}">  <!-- 가장 상위 부모댓글(level=0) -->
		    	<tr style="background-color:#eee;text-align:left;">
		    </c:if>
	      <c:if test="${cVo.level > 0}">  <!-- 일반댓글(대댓글) -->
		    	<tr style="background-color:#eef;text-align:left;">
		    </c:if>
		    <c:if test="${cVo.level <= 0}">  <!-- 부모댓글은 들여쓰기 안한다. -->
		      <td style="text-align:left;">${cVo.nickName}
		        <c:if test="${cVo.mid == smid}">
		          <a href="javascript:replyDelCheck(${cVo.idx});"><i class="glyphicon glyphicon-remove"></i></a>
		        </c:if>
		      </td>
		    </c:if>
		    <c:if test="${cVo.level > 0}">   <!-- 자식댓글(대댓글)은 들여쓰기 모두 한다. -->
		      <td style="text-align:left;">
		        <c:forEach var="i" begin="1" end="${cVo.level}">&nbsp;&nbsp; </c:forEach>
		          └ ${cVo.nickName}
		        <c:if test="${cVo.mid == smid}">
		          <a href="javascript:replyDelCheck(${cVo.idx});"><i class="glyphicon glyphicon-remove"></i></a>
		        </c:if>
		      </td>
		    </c:if>
		      <td style="text-align:left;">${fn:replace(cVo.content,newLine,"<br/>")}</td>
		      <td>
		        <c:if test="${vo.diffTime > 24}">${fn:substring(cVo.wDate,0,10)}</c:if>
	          <c:if test="${vo.diffTime <= 24}">${fn:substring(cVo.wDate,11,19)}</c:if>
		      </td>
		      <td>${cVo.hostIp}</td>
		      <td>
		        <input type="button" value="답글" id="replyBoxOpenBtn${cVo.idx}" onclick="insertReply('${cVo.idx}','${cVo.level}','${cVo.levelOrder}','${cVo.nickName}')"/>
		        <input type="button" value="닫기" id="replyBoxCloseBtn${cVo.idx}" onclick="closeReply('${cVo.idx}')" class="replyBoxClose" style="display:none;"/>
		      </td>
		    </tr>
		    <tr>
		      <td colspan="5"><div id="replyBox${cVo.idx}"></div></td>
		    </tr>
	    </c:forEach>
	  </table>
	</div>
  
  <!-- 댓글 입력처리 -->
  <form name="replyForm">
    <table class="table">
      <tr>
        <td style="text-align:left; width:90%">
          <label for="content">글내용 : </label>
          <textarea rows="5" name="content" id="content" class="form-control"></textarea>
        </td>
        <td style="">
        	<br/><br/>
        	<p><input type="text" name="nickName" size="6" value="${snickname}" readonly/></p>
        	<p><input type="button" value="댓글달기" onclick="replyCheck()"/></p>
        	<%-- <p><input type="button" value="돌아가기" onclick="location.href='${ctp}/bList.bo';"/> --%>
        </td>
      </tr>
    </table>
    <input type="hidden" name="boardIdx" value="${vo.idx}"/>
    <input type="hidden" name="mid" value="${smid}"/>
    <input type="hidden" name="pag" value="${pag}"/>
    <input type="hidden" name="pageSize" value="${pageSize}"/>
    <input type="hidden" name="hostIp" value="${pageContext.request.remoteAddr}"/>
  </form>
</div>
<!-- 이곳까지 댓글처리 -->
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>