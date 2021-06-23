<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<jsp:include page="/WEB-INF/views/include/bs.jsp"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${ctp}/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
	</div>
	<div class="container">
		<form name="nInputform" method="post" action="${ctp}/board/nInput" enctype="multipart/form-data">
			<h6>공지 등록</h6>
			<hr />
				<input type="hidden" name="nWriter" value="${smid}">
				<input type="hidden" name="nName" value="${sname}">
				<label>제목</label>
					<input type="text" id="nTitle" name="nTitle" maxlength="50" required>
				<label>첨부 파일</label><br/>
					<input type="file" id="file" name="file"><br/><br/><br/>
				<label>상세 내용</label>
					<textarea rows="5" cols="50" name="nContent" id="CKEDITOR" required></textarea>
					<script>
						CKEDITOR.replace("nContent",{
							uploadUrl:"${ctp}/imageUpload",
							filebrowserUploadUrl : "${ctp}/imageUpload", /* 파일(이미지) 업로드처리 */
				        	height : 400
				        });
					</script>
				<label>비밀번호</label>
					<input type="password" id="nPwd" name="nPwd" required>
			<div class="jumbotron text-center">
				<button type="button" class="btn btn-outline-dark" onclick="location.href='${ctp}/board/notice'">취소</button>
				<button type="submit" class="btn btn-dark">등록하기</button>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>