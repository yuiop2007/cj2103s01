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
</style>
<script>
	function inputCheck() {
		
		// 선택된 갯수
		var pColor = $("input:checkbox[name=pColor]:checked").length;	
		var pSize = $("input:checkbox[name=pSize]:checked").length;	
		var pName = $("#pName").val();
		var pPrice = $("#pPrice").val();
		
		if(pName==""){
			alert("상품명을 입력하세요.");
			return false;
		}
		else if(pColor < 1){
			alert("색상을 체크하세요.");
			return false;
		}
		else if(pSize < 1){
			alert("사이즈를 체크하세요.");
			return false;
		}
		else if(pPrice==""){
			alert("가격을 입력하세요.");
			return false;
		}
		else if($('#file').val() == ""){
			alert("대표이미지 첨부파일은 필수입니다.");
		    $("#file").focus();
		    return false;
		}
		else {
			pInputform.submit();	
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
		<form name="pInputform" method="post" action="${ctp}/product/pInput" enctype="multipart/form-data">
			<h6>상품 등록</h6>
			<hr />
				<label>상품 이름</label>
					<input type="text" id="pName" name="pName" maxlength="20" required><br/>
				<label>카테고리</label>
					<select name="pCate" required>
				    	<option value="OUTER">OUTER</option>
						<option value="TOP">TOP</option>
						<option value="BOTTOM">BOTTOM</option>
						<option value="ACC">ACC</option>
						<option value="MADE">MADE</option>
				    </select><br/>
				<div class="pin">
						<div class="color boardbtn">
							<label>색상</label><br/><br/>
							<input type="checkbox" name="pColor" value="블랙">&nbsp;블랙 &nbsp;
							<input type="checkbox" name="pColor" value="화이트">&nbsp;화이트 &nbsp;
							<input type="checkbox" name="pColor" value="차콜">&nbsp;차콜 &nbsp;
							<input type="checkbox" name="pColor" value="그레이">&nbsp;그레이 &nbsp;
							<input type="checkbox" name="pColor" value="아이보리">&nbsp;아이보리 &nbsp;
							<input type="checkbox" name="pColor" value="베이지">&nbsp;베이지 &nbsp;<br/><br/>
							<input type="checkbox" name="pColor" value="블루">&nbsp;블루 &nbsp;
							<input type="checkbox" name="pColor" value="스카이블루">&nbsp;스카이블루 &nbsp;
							<input type="checkbox" name="pColor" value="핑크">&nbsp;핑크 &nbsp;
							<input type="checkbox" name="pColor" value="그린">&nbsp;그린 &nbsp;
							<input type="checkbox" name="pColor" value="레드">&nbsp;레드 &nbsp;
							<input type="checkbox" name="pColor" value="옐로우">&nbsp;옐로우 &nbsp;
							<input type="checkbox" name="pColor" value="오렌지">&nbsp;오렌지 &nbsp;
						</div>
						<div class="size boardbtn">
							<label>사이즈</label><br/><br/>
							<input type="checkbox" name="pSize" value="XS">&nbsp;XS &nbsp;
							<input type="checkbox" name="pSize" value="S">&nbsp;S &nbsp;
							<input type="checkbox" name="pSize" value="M">&nbsp;M &nbsp;
							<input type="checkbox" name="pSize" value="L">&nbsp;L &nbsp;
							<input type="checkbox" name="pSize" value="XL">&nbsp;XL &nbsp;						
						</div>
				</div>	
				<label>상품 가격</label>
					<input type="number" id="pPrice" name="pPrice" required><br/>
				<label>재고</label>
					<input type="number" id="pStock" name="pStock" value="0"><br/>
				<label>노출 형태 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;❗&nbsp;&nbsp;<strong style="color: red; font-size: 8px;">기획상품은 메인화면에 가장 먼저 노출됩니다.</strong></label>
					<select name="pEvent">
						<option value="1">기본상품</option>
						<option value="2">기획상품</option>
					</select><br/><br/><br/><br/>
				<label>대표이미지</label><br/>
					<input type="file" id="file" name="file" accept=".gif,.jpg,.png,.jpeg,.bmp" required>
					❗&nbsp;&nbsp;<strong style="color: red;">대표이미지는 필수로 업로드 하십시오.</strong>
					<br/><br/><br/>
				<label>제품 정보</label>
					<textarea rows="3" cols="20" name="pInfo" required style="height: 200px;">제품 정보
상세사이즈
색상
소재
모델정보</textarea>
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
				<button type="button" class="btn btn-dark" onclick="inputCheck()">등록하기</button>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>