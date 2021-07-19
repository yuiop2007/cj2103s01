<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>nList.jsp</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <%@ include file="/WEB-INF/views/include/bs.jsp" %>
  <script>
    $(document).ready(function() {
    	// 초기화면 팝업스위치 처리
    	$(".popupSw").click(function() {
    		var popupCheck = $(this).is(":checked");  // 결과가 True가 발생했는지, False가 발생했는지를 알아온다.
    		var popupSw = popupCheck ? "Y" : "N";
    		var idx = $(this).next().val();
    		var query = {
    				idx : idx,
    				popupSw : popupSw
    		};
    		
    		$.ajax({
    			type : "get",
    			url  : "${ctp}/admin/notify/popupCheck",
    			data : query,
    			success : function(data) {
    				if(popupSw == "Y") {
    					alert("공지사항을 초기화면에 팝업창으로 띄웁니다.");
    				}
    				else {
    					alert("공지사항을 초기화면에서 팝업창을 닫습니다.");
    				}
    				location.reload();
    			}
    		});
    	});
    });
    // 삭제처리
  	function nDelCheck(idx) {
  		var ans = confirm("공지사항을 삭제하시겠습니까?");
  		if(!ans) return false;
  		
  		$.ajax({
  			type : "get",
  			url  : "${ctp}/admin/notify/nDelete",
  			data : {idx : idx},
  			success : function() {
  				alert("삭제 완료!!");
  				location.reload();
  			}
  		});
  	}
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <table class="table table-borderless">
    <tr>
      <td align="center" colspan=2><font size=5><b>공 지 사 항 리 스 트</b></font></td>
    </tr>
    <tr>
      <td style="width:90%;border:0px;" class="tblr">
        <button type="button" class="btn btn-secondary" onClick="location.href='${ctp}/admin/notify/nInput'">공지사항작성</button>
      </td>
    </tr>
  </table>
  <div id="notifyContent">
	  <form name="myform" method="get">
	    <ul>
	      <c:set var="num" value="${num}"/>
	      <c:forEach var="vo" items="${vos}">
	        <li><hr>
	          <span>
	            <div class="container mt-3">
	              <div class="d-flex justify-content-between mb-3">
	                <div class="p-2">
	                  <h5><b>[공지${num}] ${vo.title}</b></h5>&nbsp; &nbsp;
	                </div>
	                <div class="p-2">
	                  &nbsp; &nbsp;
	                  <button type="button" class="btn btn-secondary" onclick="location.href='${ctp}/admin/notify/nUpdate?idx=${vo.idx}';">수정</button>
	                  <button type="button" class="btn btn-secondary" onclick="nDelCheck(${vo.idx})">삭제</button>
	                  <br/><br/>☞ 초기화면 표시 유뮤 :
	                  <input type="checkbox" name="popupSw" id="popupSw${num}" class="popupSw" <c:if test="${vo.popupSw == 'Y'}">checked</c:if>/>
	                  <input type="hidden" name="idx" id="idx" value="${vo.idx}" />
	                </div>
	              </div>
	            </div>
	          </span>
	          <ul style="background-color:#fff5ee">
	            <c:set var="content" value="${fn:replace(vo.content,newLine,'<br/>')}"/>
	            <li>${content}</li><br/>
	            <li>게시일 : ${fn:substring(vo.startDate,0,10)} ~ ${fn:substring(vo.endDate,0,10)}</li>
	          </ul>
	        </li>
	        <c:set var="num" value="${num-1}"/>
	      </c:forEach>
	    </ul>
	  </form>
  </div>
</div>
</body>
</html>