<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<script>
	//게시글 수정처리를 위한 비밀번호 체크
	function updCheck() {
		var pwd = contentform.qPwd.value;
		if(pwd == "") {
			alert("비밀번호를 입력하세요")
			contentform.qPwd.focus();
		}
		else {
			contentform.submit();
		}
	}
	
	// 게시글 삭제처리
	function delCheck() {
		var ans = confirm("게시글을 삭제하시겠습니까?");
		var slevel = $("#slevel").val();
		if(ans) {
			if(slevel==0){
				$.ajax({
					type : "post",
					url  : "${ctp}/board/qDelete?qId=${vo.qId}&pId=${param.pId}&qPwd=admin&pag=${pag}&pageSize=${pageSize}",
					success: function(data) {
						alert("삭제처리 되었습니다.");
						location.href="${ctp}/board/qna";
					}
				});
			}
			else{
				var pwd = contentform.qPwd.value;
				if(pwd == "") {
		    		alert("비밀번호를 입력하세요")
		    		contentform.qPwd.focus();
		    		return false;
			    }
				else {
					$.ajax({
						type : "post",
						url  : "${ctp}/board/qDelete?qId=${vo.qId}&pId=${param.pId}&qPwd="+pwd+"&pag=${pag}&pageSize=${pageSize}",
						success: function(data) {
							alert("삭제처리 되었습니다.");
							location.href="${ctp}/board/qna";
						}
					});
				}
			}
			
		}
	}
	
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
	</div>
	<div class="container">
		<h6>리뷰</h6>
		<br/><br/><br/><br/>
		<form name=contentform method="post">
			<div class=board>
				<div class=boardview>
					<table class="boardtable">
						<tbody>
							<tr>
								<th scope="row">SUBJECT</th>
								<td class="subject">${vo.qTitle}</td>
							</tr>
							<tr>
								<th scope="row">NAME</th>
								<td class="subject2">${vo.qName}</td>
							</tr>
							<tr>
								<th scope="row">DATE</th>
								<td class="subject2">${fn:substring(vo.qRdate,0,fn:length(vo.qRdate)-2)}</td>
							</tr>
							<tr>
								<th scope="row">HIT</th>
								<td class="subject2">${vo.qHit}</td>
							</tr>
							<tr class="view">
								<td colspan="2">
									<div class="detail">
										<div style="text-align:left;">
											${fn:replace(vo.qContent,newLine,"<br/>")}
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<c:if test="${smid == vo.qWriter}">
								<th scope="row">비밀번호</th>
								<td class="subject2"><input type="password" name="qPwd" style="width: 100px; height: 20px;"/></td>
								</c:if>
							</tr>
						</tbody>
					</table>
				</div>
				<div class=boardbtn>
					<a href="${ctp}/product/pContent?pId=${param.pId}&pag=${pag}&pageSize=${pageSize}">목록</a>
					<c:if test="${slevel==0 }">
					<a href="${ctp}/board/qReply?qId=${vo.qId}&pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}">답변</a>
					</c:if>
					<c:if test="${smid == vo.qWriter || slevel == 0}">
					<a href="#" onclick="updCheck()">수정</a>
					<a href="#" onclick="delCheck()">삭제</a>
					</c:if>
				</div>
					  <input type="hidden" name="pag" value="${pag}"/>
					  <input type="hidden" name="pageSize" value="${pageSize}"/>
					  <input type="hidden" name="qId" value="${vo.qId}"/>
					  <input type="hidden" name="pId" value="${vo.pId}"/>
					  <input type="hidden" id="slevel" value="${slevel}"/>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>