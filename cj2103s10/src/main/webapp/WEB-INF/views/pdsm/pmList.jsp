<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>pmList.jsp</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
	  function partCheck() {
	  	var part = partForm.part.value;
	  	location.href="${ctp}/pdsm/pmList?pag=${pageVO.pag}&part="+part;
	  }
	  
		$(function() {
			$(".downCheck").on("click",function(){
		  	var idx = $(this).attr("value");
		    	var query = {idx : idx}
		    	$.ajax({
		    		url   : "${ctp}/pdsm/downCheck",
		    		type  : "get",
		    		data  : query,
		    		success : function(data) {
	    				location.reload();
		    		}
		    	});
			});
		});
		
		function newWindow(idx) {
			var url = "${ctp}/pdsm/pmContent?idx="+idx;
			window.open(url,"winOpen","width=450px,height=600px");
		}
  </script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<p><br/></p>
<div class="container" id="container">
  <table class="table">
    <tr>
      <td colspan="2" style="text-align:center;border:none;"><h2>멀 티 자 료 실 리 스 트</h2></td>
    </tr>
    <tr>
  		<td style="text-align:left;border:none;width:15%;">
  		  <form name="partForm">
  		    <select name="part" onchange="partCheck()" class="form-control">
  		      <option value="전체" ${part=='전체' ? 'selected' : ''}>전체</option>
  		      <option value="학습" ${part=='학습' ? 'selected' : ''}>학습</option>
  		      <option value="여행" ${part=='여행' ? 'selected' : ''}>여행</option>
  		      <option value="음식" ${part=='음식' ? 'selected' : ''}>음식</option>
  		      <option value="기타" ${part=='기타' ? 'selected' : ''}>기타</option>
  		    </select>
  		  </form>
  		</td>
  		<td style="text-align:right;border:none;">
  		  <button type="button" onclick="location.href='${ctp}/pdsm/input/pmInput';" class="btn btn-secondary">자료올리기</button>
  		</td>
    </tr>
  </table>
  <table class="table table-hover">
    <tr style="text-align:center;background-color:#ccc;">
      <th>번호</th>
      <th>자료제목</th>
      <th>올린이</th>
      <th>올린날자</th>
      <th>파일명(용량)</th>
      <th>분류</th>
      <th>다운횟수</th>
      <th>다운로드</th>
    </tr>
    <c:set var="curScrStartNo" value="${pageVO.curScrStartNo}"/>
    <c:forEach var="vo" items="${vos}">
	    <tr style="text-align:center;">
	      <td>${curScrStartNo}</td>
	      <td><a href="javascript:newWindow(${vo.idx});">${vo.title}</a></td>
	      <td>${vo.nickName}</td>
	      <td>${fn:substring(vo.fDate,0,10)}</td>
	      <td>
	        <c:if test="${vo.openSw == '공개' || smid == vo.mid || slevel == 0}">
		        <c:set var="fNames" value="${fn:split(vo.fName,'/')}"/>
		        <c:set var="rfNames" value="${fn:split(vo.rfName,'/')}"/>
		        <c:forEach var="fName" items="${fNames}" varStatus="st">
		          <a href="${ctp}/resources/pdsm/${rfNames[st.index]}" class="downCheck" value="${vo.idx}" download>${fName}</a> /
		        </c:forEach>
		        <br/>(<fmt:formatNumber value="${vo.fSize / 1024}" pattern="#,###.#"/>KB)
		      </c:if>
		      <c:if test="${vo.openSw != '공개' && smid != vo.mid && slevel != 0}">
		        :비공개:
		      </c:if>
	      </td>
	      <td>${vo.part}</td>
	      <td>${vo.downNum}</td>
	      <td>
	        <c:if test="${vo.openSw == '공개' || smid == vo.mid || slevel == 0}">
		        <input type="button" value="다운" onclick="location.href='${ctp}/pdsm/pmDown?idx=${vo.idx}&fName=${vo.fName}&rfName=${vo.rfName}&title=${vo.title}';"/>
		      </c:if>
		      <c:if test="${vo.openSw != '공개' && smid != vo.mid && slevel != 0}">
		        :비공개:
		      </c:if>
	      </td>
	    </tr>
	    <c:set var="curScrStartNo" value="${curScrStartNo-1}"/>
    </c:forEach>
  </table>
</div>
<!-- 블록 페이징처리 시작 -->
<div class="container" style="text-align:center">
  <ul class="pagination justify-content-center">
	  <c:set var="startPageNum" value="${pageVO.pag - (pageVO.pag-1)%pageVO.blockSize}"/>
	  <c:if test="${pageVO.pag != 1}">
	    <li class="page-item"><a href="${ctp}/pdsm/pmList?pag=1&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
	    <li class="page-item"><a href="${ctp}/pdsm/pmList?pag=${pageVO.pag-1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">◀</a></li>
	  </c:if>
	  <c:forEach var="i" begin="0" end="2">
	    <c:if test="${(startPageNum+i) <= pageVO.totPage}">
	      <c:if test="${pageVO.pag == (startPageNum+i)}">
	        <li class="page-item active"><a href="${ctp}/pdsm/pmList?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff"><b>${startPageNum+i}</b></font></a></li>
	      </c:if>
	      <c:if test="${pageVO.pag != (startPageNum+i)}">
	        <li class="page-item"><a href="${ctp}/pdsm/pmList?pag=${startPageNum+i}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
	      </c:if>
	    </c:if>
	  </c:forEach>
	  <c:if test="${pageVO.pag != pageVO.totPage}">
	    <li class="page-item"><a href="${ctp}/pdsm/pmList?pag=${pageVO.pag+1}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▶</a></li>
	    <li class="page-item"><a href="${ctp}/pdsm/pmList?pag=${pageVO.totPage}&pageSize=${pageVO.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
	  </c:if>
	</ul>
</div>
<!-- 블록 페이징처리 끝 -->
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>