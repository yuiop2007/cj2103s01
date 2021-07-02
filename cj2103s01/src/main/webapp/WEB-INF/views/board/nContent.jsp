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
		var pwd = contentform.nPwd.value;
		if(pwd == "") {
			alert("비밀번호를 입력하세요")
			contentform.nPwd.focus();
		}
		else {
			contentform.submit();
		}
	}
	
	// 게시글 삭제처리
	function delCheck() {
		var ans = confirm("게시글을 삭제하시겠습니까?");
		var pwd = contentform.nPwd.value;
		
		if(ans) {
			if(pwd == "") {
	    		alert("비밀번호를 입력하세요")
	    		contentform.nPwd.focus();
	    		return false;
	    }
			else {
				$.ajax({
					type : "post",
					url  : "${ctp}/board/nDelete?idx=${vo.nId}&nPwd="+pwd+"&pag=${pag}&pageSize=${pageSize}",
					success: function(data) {
						alert("삭제처리 되었습니다.");
						location.href="${ctp}/board/notice";
					}
				});
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
		<h6>공지사항</h6>
		</br>
		</br>
		</br>
		</br>
		<form name=contentform method="post">
			<div class=board>
				<div class=boardview>
					<table class="boardtable">
						<tbody>
							<tr>
								<th scope="row">SUBJECT</th>
								<td class="subject">${vo.nTitle}</td>
							</tr>
							<tr>
								<th scope="row">NAME</th>
								<td class="subject2">${vo.nName}</td>
							</tr>
							<tr>
								<th scope="row">DATE</th>
								<td class="subject2">${fn:substring(vo.nRdate,0,fn:length(vo.nRdate)-2)}</td>
							</tr>
							<tr>
								<th scope="row">HIT</th>
								<td class="subject2">${vo.nHit}</td>
							</tr>
							<tr class="view">
								<td colspan="2">
									<div class="detail">
										<div style="text-align:left;">
											${fn:replace(vo.nContent,newLine,"<br/>")}
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<c:if test="${smid == vo.nWriter}">
								<th scope="row">비밀번호</th>
								<td class="subject2"><input type="password" name="nPwd" style="width: 100px; height: 20px;"/></td>
								</c:if>
							</tr>
						</tbody>
					</table>
				</div>
				<div class=boardbtn>
					<a href="#" onclick="location.href='${ctp}/board/notice?pag=${pag}&pageSize=${pageSize}';">목록</a>
					<c:if test="${smid == vo.nWriter}">
					<a href="#" onclick="updCheck()">수정</a>
					<a href="#" onclick="delCheck()">삭제</a>
					</c:if>
				</div>
					  <input type="hidden" name="pag" value="${pag}"/>
					  <input type="hidden" name="pageSize" value="${pageSize}"/>
					  <input type="hidden" name="idx" value="${vo.nId}"/>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>