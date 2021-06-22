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
		<form name="pInputform" method="post" action="${ctp}/product/pInput" enctype="multipart/form-data">
			<h6>상품 등록</h6>
			<hr />
				<label>상품 이름</label>
					<input type="text" id="pName" name="pName" maxlength="20" required>
				<label>카테고리</label>
					<select name="pCate">
				    	<option value="OUTER">OUTER</option>
						<option value="TOP">TOP</option>
						<option value="BOTTOM">BOTTOM</option>
						<option value="ACC">ACC</option>
						<option value="MADE">MADE</option>
				    </select>
				<label>상품 가격</label>
					<input type="number" id="pPrice" name="pPrice" required>
				<label>재고</label>
					<input type="number" id="pStock" name="pStock" value="0"><br/><br/><br/>
				<label>대표이미지</label><br/>
					<input type="file" id="file" name="file" accept=".gif,.jpg,.png" required><br/><br/><br/>
				<label>상세 내용</label>
					<textarea rows="5" cols="50" name="pContent" id="CKEDITOR" required></textarea>
					<script>
						CKEDITOR.replace("pContent",{
							uploadUrl:"${ctp}/imageUpload",
							filebrowserUploadUrl : "${ctp}/imageUpload", /* 파일(이미지) 업로드처리 */
				        	height : 400
				        });
					</script>
			<div class="jumbotron text-center">
				<button type="button" class="btn btn-outline-dark" onclick="location.href='${ctp}/admin/admin'">취소</button>
				<button type="submit" class="btn btn-dark">등록하기</button>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>