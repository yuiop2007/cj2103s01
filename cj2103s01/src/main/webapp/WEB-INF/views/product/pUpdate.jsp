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
<script>
	$(document).ready (function () {                
	    $('.btnAdd').click (function () {                                        
	        $('.color').append (                        
	            '<input type="text" name="pColor"><a href="#" class="btnRemove">삭제</a><br/>'                    
	        ); // end append                            
	        $('.btnRemove').on('click', function () { 
	            $(this).prev().remove (); // remove the textbox
	            $(this).next ().remove (); // remove the <br>
	            $(this).remove (); // remove the button
	        });
	    }); // end click                                            
	}); // end ready
	
	$(document).ready (function () {                
	    $('.btnAdd2').click (function () {                                        
	        $('.size').append (                        
	            '<input type="text" name="pSize"><a href="#" class="btnRemove2">삭제</a><br/>'                    
	        ); // end append                            
	        $('.btnRemove2').on('click', function () { 
	            $(this).prev().remove (); // remove the textbox
	            $(this).next ().remove (); // remove the <br>
	            $(this).remove (); // remove the button
	        });
	    }); // end click                                            
	}); // end ready
	
	$(document).ready (function () {                
	    $('.infoRemove').click (function () {                                        
            $(this).prev().remove (); // remove the textbox
            $(this).remove (); // remove the button
	    }); // end click                                            
	}); // end ready 
	
</script>
<style>
.color > input[type=text]{
	width: 100px;
}
.size > input[type=text]{
	width: 100px;
}
.color{
	width: 200px;
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
							<label>색상</label><br/>
							<input type="text" name="pColor"><a href="#" class="btnAdd">추가</a><br/>
						</div>
						<div class="size boardbtn">
							<label>사이즈</label><br/>
							<input type="text" name="pSize"><a href="#" class="btnAdd2">추가</a><br/>
						</div>
				</div>	
				<label>상품 가격</label>
					<input type="number" id="pPrice" name="pPrice" value="${vo.pPrice}" required><br/>
				<label>재고</label>
					<input type="number" id="pStock" name="pStock" value="${vo.pStock}"><br/><br/><br/><br/>
				<label>대표이미지</label><br/>
					<input type="file" id="file" name="file" accept=".gif,.jpg,.png" required>
					<div class="boardbtn">
						<img src="${ctp}/resources/pMainImages/${vo.pImage}"/><a href="#" class="infoRemove">삭제</a>
					</div>
					<br/><br/><br/>
				<label>제품 정보</label>
					<textarea rows="3" cols="20" name="pInfo" value="${vo.pInfo}" required></textarea>
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
				<button type="submit" class="btn btn-dark">등록하기</button>
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