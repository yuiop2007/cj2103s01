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
	$(document).ready(function(){
		$("#cDiscount").on('input', function() {
			var cDiscount = $("#cDiscount").val();
			var cPrice = $("#cPrice").val();
			if(cDiscount>0){
				$("#cPrice").attr("readonly", true);
			}
			else{
				$("#cPrice").removeAttr("readonly");
			}
		});
		
		$("#cPrice").on('input', function() {
			var cDiscount = $("#cDiscount").val();
			var cPrice = $("#cPrice").val();
			if(cPrice>0){
				$("#cDiscount").attr("readonly", true);
			}
			else{
				$("#cDiscount").removeAttr("readonly");
			}
		});
		
	});
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
	</div>
	<div class="container">
		<form name="cInputform" method="post" action="${ctp}/admin/cInput" enctype="multipart/form-data">
			<h6>쿠폰 등록</h6>
			<hr />
				<label>쿠폰 이름</label>
					<input type="text" id="cName" name="cName" maxlength="20" required><br/>
				<label>대상 회원</label>
					<select name="cmCate" required>
				    	<option value="5">모든회원</option>
				    	<option value="1">특별회원</option>
						<option value="2">우수회원</option>
						<option value="3">정회원</option>
						<option value="4">준회원</option>
				    </select><br/>
				<label>대상 품목</label>
					<select name="cpCate" required>
				    	<option value="ALL">ALL</option>
				    	<option value="OUTER">OUTER</option>
						<option value="TOP">TOP</option>
						<option value="BOTTOM">BOTTOM</option>
						<option value="ACC">ACC</option>
						<option value="MADE">MADE</option>
				    </select><br/><br/><br/>
				<label>할인율 / 할인가격</label><br/>
					<input type="number" id="cDiscount" name="cDiscount" value="0" min="0" max="100" style="width: 100px;">% &nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;
					<input type="number" id="cPrice" name="cPrice" value="0" min="0" style="width: 100px;">원<br/><br/><br/><br/>
				<label>시작 날짜</label>
					<input type="date" name="cRdate"/><br/><br/>
				<label>종료 날짜</label>
					<input type="date" name="cEnddate"/><br/><br/>
					
			<div class="jumbotron text-center">
				<button type="button" class="btn btn-outline-dark" onclick="location.href='${ctp}/admin/coupon'">취소</button>
				<button type="submit" class="btn btn-dark">등록하기</button>
			</div>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>