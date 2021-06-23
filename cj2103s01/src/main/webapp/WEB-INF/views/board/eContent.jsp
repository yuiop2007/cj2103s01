<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp"/>
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<script>
	//게시글 수정처리를 위한 비밀번호 체크
	function updCheck() {
		var pwd = myform.pwd.value;
		if(pwd == "") {
			alert("비밀번호를 입력하세요")
			myform.pwd.focus();
		}
		else {
			location.href="${ctp}/bUpdate.bo?idx=${vo.eId}&pwd="+pwd+"&pag=${pag}&pageSize=${pageSize}";
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
				location.href = "${ctp}/bDelete.bo?idx=${vo.eId}&pwd="+pwd+"&pag=${pag}";
			}
		}
	}
	
	// 댓글 입력처리
	function replyCheck() {
		var content = replyForm.content.value;
		if(content == "") {
			alert("댓글을 입력하세요?");
			replyForm.content.focus();
		}
		else {
			replyForm.submit();
		}
	}
	
	// 댓글 삭제(aJax 처리)
	function replyDelCheck(replyIdx) {
		var query = {replyIdx : replyIdx};
		var ans = confirm("선택하신 댓글을 삭제하시겠습니까?");
		if(!ans) return false;
		
		$.ajax({
			type : "get",
			url  : "${ctp}/bReplyDelete",
			data : query,
			success: function(data) {
				alert("삭제처리 되었습니다.");
				location.reload();
			}
		});
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
		<form name=contentform>
			<div class=board>
				<div class=boardview>
					<table class="boardtable">
						<tbody>
							<tr>
								<th scope="row">SUBJECT</th>
								<td class="subject">${vo.eTitle}</td>
							</tr>
							<tr>
								<th scope="row">NAME</th>
								<td class="subject2">${vo.eName}</td>
							</tr>
							<tr>
								<th scope="row">DATE</th>
								<td class="subject2">${fn:substring(vo.eRdate,0,fn:length(vo.eRdate)-2)}</td>
							</tr>
							<tr>
								<th scope="row">HIT</th>
								<td class="subject2">${vo.eHit}</td>
							</tr>
							<tr class="view">
								<td colspan="2">
									<div class="detail">
										<div style="text-align:left;">
											${fn:replace(vo.eContent,newLine,"<br/>")}
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<c:if test="${smid == vo.eWriter}">
								<th scope="row">비밀번호</th>
								<td class="subject2"><input type="password" name="ePwd" style="width: 100px; height: 20px;"/></td>
								</c:if>
							</tr>
						</tbody>
					</table>
				</div>
				<div class=boardbtn>
					<a href="#" onclick="location.href='${ctp}/board/event?pag=${pag}&pageSize=${pageSize}';">목록</a>
					<c:if test="${smid == vo.eWriter}">
					<a href="#" onclick="updCheck()">수정</a>
					<a href="#" onclick="delCheck()">삭제</a>
					</c:if>
				</div>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>