<%@page import="com.spring.cj2103s01.vo.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<script>
	$(document).ready (function () {                
	    $('.infoRemove').click (function () {                                        
            $(this).prev().remove (); // remove the textbox
            $(this).remove (); // remove the button
	    }); // end click                                            
	}); // end ready 
	
</script>
<style>
.color{
	width: 450px;
}
.pin{
	display: flex;
}
label{
	margin-bottom: 0;
	margin-top: 15px;
}
.btnAdd, .btnAdd2, .btnRemove, .btnRemove2, .infoRemove{
	margin-left: 10px;
}

</style>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
	</div>
	<div class="container">
		<form name="pUpdateform" method="post" action="${ctp}/product/pUpdate" enctype="multipart/form-data">
			<h6>상품 등록</h6>
			<hr />
				<label>상품 이름</label>
					<input type="text" id="pName" name="pName" maxlength="20" value="${vo.pName}" required><br/>
				<label>카테고리</label>
					<select name="pCate">
				    	<option value="OUTER" <c:if test="${vo.pCate eq 'OUTER'}">selected</c:if>>OUTER</option>
						<option value="TOP" <c:if test="${vo.pCate eq 'TOP'}">selected</c:if>>TOP</option>
						<option value="BOTTOM" <c:if test="${vo.pCate eq 'BOTTOM'}">selected</c:if>>BOTTOM</option>
						<option value="ACC" <c:if test="${vo.pCate eq 'ACC'}">selected</c:if>>ACC</option>
						<option value="MADE" <c:if test="${vo.pCate eq 'MADE'}">selected</c:if>>MADE</option>
				    </select><br/>
				<div class="pin">
						<div class="color boardbtn">
							<label>색상</label><br/><br/>
							<input type="checkbox" name="pColor" value="블랙" <c:if test="${fn:contains(vo.pColor,'블랙')}">checked</c:if>>&nbsp;블랙 &nbsp;
							<input type="checkbox" name="pColor" value="화이트" <c:if test="${fn:contains(vo.pColor,'화이트')}">checked</c:if>>&nbsp;화이트 &nbsp;
							<input type="checkbox" name="pColor" value="차콜" <c:if test="${fn:contains(vo.pColor,'차콜')}">checked</c:if>>&nbsp;차콜 &nbsp;
							<input type="checkbox" name="pColor" value="그레이" <c:if test="${fn:contains(vo.pColor,'그레이')}">checked</c:if>>&nbsp;그레이 &nbsp;
							<input type="checkbox" name="pColor" value="아이보리" <c:if test="${fn:contains(vo.pColor,'아이보리')}">checked</c:if>>&nbsp;아이보리 &nbsp;
							<input type="checkbox" name="pColor" value="베이지" <c:if test="${fn:contains(vo.pColor,'베이지')}">checked</c:if>>&nbsp;베이지 &nbsp;<br/><br/>
							<input type="checkbox" name="pColor" value="블루" <c:if test="${fn:contains(vo.pColor,'블루')}">checked</c:if>>&nbsp;블루 &nbsp;
							<input type="checkbox" name="pColor" value="스카이블루" <c:if test="${fn:contains(vo.pColor,'스카이블루')}">checked</c:if>>&nbsp;스카이블루 &nbsp;
							<input type="checkbox" name="pColor" value="핑크" <c:if test="${fn:contains(vo.pColor,'핑크')}">checked</c:if>>&nbsp;핑크 &nbsp;
							<input type="checkbox" name="pColor" value="그린" <c:if test="${fn:contains(vo.pColor,'그린')}">checked</c:if>>&nbsp;그린 &nbsp;
							<input type="checkbox" name="pColor" value="레드" <c:if test="${fn:contains(vo.pColor,'레드')}">checked</c:if>>&nbsp;레드 &nbsp;
							<input type="checkbox" name="pColor" value="옐로우" <c:if test="${fn:contains(vo.pColor,'옐로우')}">checked</c:if>>&nbsp;옐로우 &nbsp;
							<input type="checkbox" name="pColor" value="오렌지" <c:if test="${fn:contains(vo.pColor,'오렌지')}">checked</c:if>>&nbsp;오렌지 &nbsp;
						</div>
						<div class="size boardbtn">
							<label>사이즈</label><br/><br/>
							<input type="checkbox" name="pSize" value="XS" <c:if test="${fn:contains(vo.pSize,'XS')}">checked</c:if>>&nbsp;XS &nbsp;
							<input type="checkbox" name="pSize" value="S" <c:if test="${fn:contains(vo.pSize,'S')}">checked</c:if>>&nbsp;S &nbsp;
							<input type="checkbox" name="pSize" value="M" <c:if test="${fn:contains(vo.pSize,'M')}">checked</c:if>>&nbsp;M &nbsp;
							<input type="checkbox" name="pSize" value="L" <c:if test="${fn:contains(vo.pSize,'L')}">checked</c:if>>&nbsp;L &nbsp;
							<input type="checkbox" name="pSize" value="XL" <c:if test="${fn:contains(vo.pSize,'XL')}">checked</c:if>>&nbsp;XL &nbsp;	
						</div>
				</div>	
				<label>상품 가격</label>
					<input type="number" id="pPrice" name="pPrice" value="${vo.pPrice}" required><br/>
				<label>재고</label>
					<input type="number" id="pStock" name="pStock" value="${vo.pStock}"><br/><br/><br/><br/>
				<label>대표이미지</label><br/>
					<input type="file" id="file" name="file" accept=".gif,.jpg,.png" required>
					<div class="boardbtn">
						<img src="${ctp}/resources/pMainImages/${vo.pImage}" width="300px;"/><a href="#" class="infoRemove">삭제</a>
					</div>
					<br/><br/><br/>
				<label>제품 정보</label>
					<textarea rows="3" cols="20" name="pInfo" required style="height: 200px;">${vo.pInfo}</textarea>
				<label>상세 내용</label>
					<textarea rows="5" cols="50" name="pContent" id="CKEDITOR" required>${vo.pContent}</textarea>
					<script>
						CKEDITOR.replace("pContent",{
							uploadUrl:"${ctp}/imageUpload",
							filebrowserUploadUrl : "${ctp}/imageUpload", /* 파일(이미지) 업로드처리 */
				        	height : 400
				        });
					</script>
			<div class="jumbotron text-center">
				<button type="button" class="btn btn-outline-dark" onclick="location.href='${ctp}/product/pContent?pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}'">취소</button>
				<button type="submit" class="btn btn-dark">수정하기</button>
				<input type="hidden" name="pId" value="${vo.pId}"/>
			    <input type="hidden" name="pag" value="${pageVO.pag}"/>
			    <input type="hidden" name="pageSize" value="${pageVO.pageSize}"/>
				<input type="hidden" name="oriContent"/>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>