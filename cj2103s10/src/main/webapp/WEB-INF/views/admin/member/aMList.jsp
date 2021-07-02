<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>aMList.jsp</title>
  <%@ include file="/WEB-INF/views/include/bs.jsp" %>
  <script>
    function levelCheck() {
    	alert("회원정보를 변경하시려면, '정보변경'버튼을 클릭하세요...");
    }
    
    // 레벨별 검색처리
    function levelSearch() {
    	var level = adminForm.level.value;
    	location.href = "${ctp}/admin/aMListLevel.ad?level="+level+"&pag=${pageVO.pag}";
    }
    
    // 개별 아이디 검색
    function midSearch() {
    	var mid = idForm.searchMid.value;
    	if(mid == "") {
    		alert("아이디를 입력하세요.");
    		idForm.searchMid.focus();
    	}
    	else {
    		location.href="${ctp}/admin/aSearch.mem?mid="+mid;
    	}
    }
    
    // 레벨 변경하기
    function aMLevelCheck(mid) {
    	var level = $("#level"+mid).val();
    	$.ajax({
    		type : "get",
    		url  : "${ctp}/admin/aMLevelCheck",
    		data : {
    			mid : mid,
    			level : level
    		},
    		success : function(data) {
    			alert("정보변경 완료!");
    		}
    	});
    }
    
    // 개인 정보보기
    function newWin(mid) {
    	var url = "${ctp}/admin/aMInfor.ad?mid="+mid;
    	window.open(url, "mInfor", "width=600px, height=600px");
    }
    
    // 전체선택
    $(function() {
    	$("#checkAll").click(function() {
    		if($("#checkAll").prop("checked")) {
    			$(".chk").prop("checked",true);
    		}
    		else {
    			$(".chk").prop("checked",false);
    		}
    	});
    });
    
    // 선택항목 반전
    $(function() {
    	$("#reverseAll").click(function() {
    		$(".chk").prop("checked", function() {
    			return !$(this).prop("checked");
    		});
    	});
    });
    
    // 선택항목 삭제하기
    function mSelectDelCheck() {
    	var ans = confirm("선택된 모든 회원들을 삭제 하시겠습니까?");
    	if(ans) {
    		var delItems = "";
    		for(var i=0; i<myform.chk.length; i++) {
    			if(myform.chk[i].checked == true) delItems += myform.chk[i].value + "/";
    		}
	    	myform.delItems.value = delItems;
	    	myform.submit();
    	}
    }
  </script>
  <style>
    th, td {text-align: center;}
  </style>
</head>
<body>
<div class="container">
  <p><br/></p>
  <div style="padding:10px 0px;">
    <form name="adminForm">
      <table class="table table-borderless" style="width:100%;margin:0px;padding:0px;">
        <tr>
          <td colspan="2"><h2>회 원 리 스 트</h2></td>
        </tr>
        <tr>
          <td style="text-align:left;">
            <input type="checkbox" id="checkAll"/>전체선택/해제 &nbsp;
            <input type="checkbox" id="reverseAll"/>선택반전 &nbsp;
            <input type="button" value="선택항목 삭제" onclick="mSelectDelCheck()" class="btn btn-outline-secondary btn-sm"/>
          </td>
          <td style="text-align:right">
            <c:if test="${slevel == 0}">
				      회원등급
				      <select name="level" onchange="levelSearch()">
				      	<option value="" <c:if test="${empty stringLevel}">selected</c:if>>전체회원</option>
				      	<option value="1" <c:if test="${stringLevel=='1'}">selected</c:if>>특별회원</option>
				      	<option value="2" <c:if test="${stringLevel=='2'}">selected</c:if>>우수회원</option>
				      	<option value="3" <c:if test="${stringLevel=='3'}">selected</c:if>>정회회원</option>
				      	<option value="4" <c:if test="${stringLevel=='4'}">selected</c:if>>준회원</option>
				      </select>
			      </c:if>
			    </td>
	      </tr>
      </table>
    </form>
  </div>
  <form name="myform" method="post">
	  <table class="table table-hover">
	    <tr class="table-dark text-dark">
	      <th>선택</th>
	      <th>번호</th>
	      <th>아이디</th>
	      <th>별명</th>
	      <th>성명</th>
	      <th>성별</th>
				<c:if test="${slevel == 0}">
		      <th>정보공개</th>
		      <th>등급</th>
		      <th>탈퇴유무</th>
				</c:if>
	    </tr>
	    <c:set var="curScrStartNo" value="${pageVO.curScrStartNo}"/>
	    <c:forEach var="vo" items="${vos}">
		    <tr>
		      <td><input type="checkbox" name="chk" value="${vo.idx}" class="chk"/></td>
		      <td>${curScrStartNo}</td>
		      <td>${vo.userInfor == 'OK' || slevel == 0 ? vo.mid : '비공개'}</td>
		      <td>
		        <c:if test="${slevel == 0}"><a href="javascript:newWin('${vo.mid}');">${vo.nickName}</a></c:if>
		        <c:if test="${slevel != 0}">${vo.nickName}</c:if>
		      </td>
		      <td>${vo.userInfor=='OK' || slevel == 0 ? vo.name : '비공개'}</td>
		      <td>${vo.userInfor=='OK' || slevel == 0 ? vo.gender : '비공개'}</td>
	        <c:if test="${slevel == 0}">
			      <td>
			        <c:if test="${vo.userInfor == 'NO'}"><font color="red">${vo.userInfor}</font></c:if>
			        <c:if test="${vo.userInfor != 'NO'}">${vo.userInfor}</c:if>
			      </td>
			      <td>
			        <form name="levelForm">
			        	<select name="level" id="level${vo.mid}" onchange="levelCheck()">
			        	  <option value="1" <c:if test="${vo.level==1}">selected</c:if>>특별회원</option>
			        	  <option value="2" <c:if test="${vo.level==2}">selected</c:if>>우수회원</option>
			        	  <option value="3" <c:if test="${vo.level==3}">selected</c:if>>정회원</option>
			        	  <option value="4" <c:if test="${vo.level==4}">selected</c:if>>준회원</option>
			        	  <option value="0" <c:if test="${vo.level==0}">selected</c:if>>관리자</option>
			        	</select>
			        	<input type="button" value="정보변경" onclick="aMLevelCheck('${vo.mid}')"/>
			        	<input type="hidden" name="mid" value="${vo.mid}"/>
			        	<input type="hidden" name="pag" value="${pageVO.pag}"/>
			        </form>
			      </td>
			      <td>
			        <c:if test="${vo.userDel == 'OK'}"><font color="red">${vo.userDel}</font></c:if>
			        <c:if test="${vo.userDel != 'OK'}">${vo.userDel}</c:if>
			      </td>
					</c:if>
		    </tr>
		    <c:set var="curScrStartNo" value="${curScrStartNo-1}"/>
	    </c:forEach>
	  </table>
	  <input type="hidden" name="delItems"/>
  </form>
  <!-- 페이징처리 시작 -->
  <c:if test="${pageVO.totPage != 0}">
  
		<div style="text-align:center">
		 <c:if test="${pageVO.pag != 1}">[<a href="${ctp}/admin/aMList.ad?pag=1">1페이지</a>]....</c:if>
		 <c:if test="${pageVO.pag > 1}">[<a href="${ctp}/admin/aMList.ad?pag=${pageVO.pag-1}">이전페이지</a>]</c:if>
		 ${pageVO.pag}Page / ${pageVO.totPage}Pages
		 <c:if test="${pageVO.pag < pageVO.totPage}">[<a href="${ctp}/admin/aMList.ad?pag=${pageVO.pag+1}">다음페이지</a>]</c:if>
		 <c:if test="${pageVO.pag != pageVO.totPage}">....[<a href="${ctp}/admin/aMList.ad?pag=${pageVO.totPage}">마지막페이지</a>]</c:if>
		</div>
	
	</c:if>
	<!-- 페이징처리 끝 -->
	<p><br/></p>
	<form name="idForm">
	  <div style="text-align:center;">
	    <%-- <input type="text" name="mid" <c:if test="${mid != ''}">value="${mid}"</c:if> placeholder="검색할아이디입력"/> --%>
	    <input type="text" name="searchMid" value="${mid}" placeholder="검색할아이디입력"/>
	    <input type="button" value="개별검색" onclick="midSearch()"/>
	    <input type="button" value="전체검색" onclick="location.href='${ctp}/admin/aMList.ad';"/>
	  </div>
	 </form>
</div>
<p><br/></p>
</body>
</html>