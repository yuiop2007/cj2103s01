<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
function inputCheck(sCnt) {
	if(sCnt==6){
		alert("슬라이드는 6개까지 등록 가능합니다.");
		return false; 
	}
	else{
		contentform.submit();
	}
}
</script>
</head>
<body>
	<div class="container">
		<br/><br/><br/><br/>
		<form name=contentform method="post" action="${ctp}/admin/sInput" enctype="multipart/form-data">
			<div class=board>
				<div class=boardview>
					<input type="file" id="file" name="file" accept=".gif,.jpg,.png,.jpeg,.bmp" required><br/><br/><br/><br/>
					순서 <select name="sNum" style="width: 100px;">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
							</select>
					<br/><br/><br/>
				</div>
				<div class=boardbtn>
					<a href="#" onclick="window.close()">창닫기</a>
					<a href="#" onclick="inputCheck(${sCnt})" style="color: white; background-color: black;">등록</a>
				</div>
				<br/><br/><br/><br/><br/><br/><br/><br/>
			</div>
		</form>
	</div>
</body>
</html>