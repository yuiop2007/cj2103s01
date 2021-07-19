<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>mnList.jsp(공지사항 전체보기)</title>
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
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<p><br/></p>
<div class="container">
  <table class="table table-borderless">
    <tr>
      <td align="center" colspan=2><font size=5><b>공 지 사 항 리 스 트</b></font></td>
    </tr>
  </table>
  <div id="notifyContent">
	  <form name="myform" method="get">
	    <ul>
	      <c:set var="num" value="${num}"/>
	      <c:forEach var="vo" items="${vos}">
	        <c:if test="${vo.popupSw == 'Y'}">
		        <li><hr>
		          <span>
		            <div class="container mt-3">
		              <div class="d-flex justify-content-between mb-3">
		                <div class="p-2">
		                  <h5><b>[공지${num}] ${vo.title}</b></h5>&nbsp; &nbsp;
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
	        </c:if>
	      </c:forEach>
	    </ul>
	  </form>
	  <br/><hr/>
	  <h3>-공 지 사 항-</h3><br/>
	    <table class="table table-hover">
	      <tr class="table-dark text-dark">
	        <th>번호</th>
	        <th>제목</th>
	        <th>게시기간</th>
	      </tr>
	      <c:set var="cnt" value="${fn:length(vos)}"/>
			  <c:forEach var="vo" items="${vos}">
					<tr>
					  <td>${cnt}</td>
					  <td><a href="javascript:window.open('${ctp}/member/notify/mNotifyView?idx=${vo.idx}','mn','width=560px,height=600px')">${vo.title}</a></td>
					  <td>${fn:substring(vo.startDate,0,10)} ~ ${fn:substring(vo.endDate,0,10)}</td>
					</tr>
					<c:set var="cnt" value="${cnt - 1}"/>
		    </c:forEach>
	    </table>
  </div>
</div>
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>