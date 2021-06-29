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
		<form name="qUpdateform" method="post" action="${ctp}/board/qUpdate" enctype="multipart/form-data">
			<h6>Q&A 등록</h6>
			<hr />
				<input type="hidden" name="qWriter" value="${smid}">
				<input type="hidden" name="qName" value="${sname}">
				<input type="hidden" name="pId" value="${param.pId}">
				<input type="hidden" name="oriContent"/>
				<input type="hidden" name="qId" value="${vo.qId}"/>
			    <input type="hidden" name="pag" value="${pageVO.pag}"/>
			    <input type="hidden" name="pageSize" value="${pageVO.pageSize}"/>
				<label>제목</label>
					<input type="text" id="qTitle" name="qTitle" maxlength="50" required value="문의합니다.">
					<br/><br/><br/>
				<label>카테고리</label><br/>
					<select name="qCate" style="width: 20%;">
						<option value="상품관련" <c:if test="${vo.qCate eq '상품관련'}">selected</c:if>>상품관련</option> 
						<option value="배송관련" <c:if test="${vo.qCate eq '배송관련'}">selected</c:if>>배송관련</option>
						<option value="입금관련" <c:if test="${vo.qCate eq '입금관련'}">selected</c:if>>입금관련</option>
						<option value="기타" <c:if test="${vo.qCate eq '기타'}">selected</c:if>>기타</option>
					</select>
					<br/><br/><br/>
				<label>상세 내용</label>
					<textarea rows="5" cols="50" name="qContent" id="CKEDITOR" required>${vo.qContent}</textarea>
					<script>
						CKEDITOR.replace("qContent",{
							uploadUrl:"${ctp}/imageUpload",
							filebrowserUploadUrl : "${ctp}/imageUpload", /* 파일(이미지) 업로드처리 */
				        	height : 400
				        });
					</script>
				<br/><br/><br/>
				<label>공개여부</label><br/>
					<input type=radio name="qSecret" value="0" <c:if test="${vo.qSecret eq 0}">checked</c:if>>공개 &nbsp;&nbsp;&nbsp;
					<input type=radio name="qSecret" value="1" <c:if test="${vo.qSecret eq 1}">checked</c:if>>비공개
				<br/><br/><br/>
				<label>비밀번호</label>
					<input type="password" id="qPwd" name="qPwd" required>
			<div class="jumbotron text-center">
				<button type="button" class="btn btn-outline-dark" onclick="location.href='${ctp}/'">취소</button>
				<button type="submit" class="btn btn-dark">등록하기</button>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>