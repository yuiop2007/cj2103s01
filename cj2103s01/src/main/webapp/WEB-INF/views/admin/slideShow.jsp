<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
int level = session.getAttribute("slevel") == null ? 99 : (int) session.getAttribute("slevel");
%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp" />
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<script>

	
	function delCheck(sId) {
		var ans = confirm("이미지를 삭제하시겠습니까?");
		
		if(ans) {
			location.href="${ctp}/admin/sDelete?sId="+sId;
		}
	}
	
	function cntCheck(sCnt) {
		if(sCnt==6){
			alert("슬라이드는 6개까지 등록 가능합니다.");
			return false; 
		}
		else{
			var ans = confirm("이미지는 최소 두개이상 등록하셔야합니다.");
			if(ans) {
				var url = "${ctp}/admin/sInput";
				window.open(url, "sInput", "width=400px, height=300px");
			}
			
		}
	}
	
	// 전체선택
	$(function() {
		$("#checkAll").click(function() {
			if ($("#checkAll").prop("checked")) {
				$(".chk").prop("checked", true);
			} else {
				$(".chk").prop("checked", false);
			}
		});
	});
	
	// 배송상태 변경하기
	function sNumCheck() {
		var ans = confirm("선택된 모든 이미지들의 순서를 변경하시겠습니까?");
		if (ans) {
			var delItems = "";
			var sNum = "";
			for (var i = 0; i < $(".chk").length; i++) {
				if ($(".chk")[i].checked == true){
					delItems += $(".chk")[i].value + "/";
					sNum += $("#sNum" + $(".chk")[i].value).val() + "/";
				}
			}
			$.ajax({
				type : "get",
				url : "${ctp}/admin/sNumCheck",
				data : {
					Num : sNum,
					delItems : delItems
				},
				success : function(data) {
					if(data=="false"){
						alert("실패하였습니다!");
					}
					else {
						alert("정보변경 완료!");
						location.href="${ctp}/admin/slideShow";
					}
				}
			});
		}
	}
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
		<h6>Slide Show</h6>
		<br/><br/><br/><br/>
		<form name="adminForm">
			<table class="table table-borderless" style="width: 100%; margin: 0px; padding: 0px;">
				<tr>
					<td style="text-align: left; padding-top: 22px;"><input type="checkbox" id="checkAll" />전체선택/해제 &nbsp; 
						<input type="button" value="정보변경" onclick="sNumCheck()" /> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
						<strong style="color: red;">❗ 가능한 순서는 겹치지 않게 하세요.</strong>
					</td>
					<td style="padding-left: 230px;">
						<div class=boardbtn>
								<a href="#" onclick="cntCheck(${sCnt})" style="color: #fff; background-color: black;" >등록하기</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
		<form name="pageForm">
			<table class="table table-borderless">
				<thead>
					<tr>
						<th scope="col" style="width: 5%;">선택</th>
						<th scope="col" style="width: 50%;">이미지</th>
						<th scope="col" style="width: 10%;">순서</th>
						<th scope="col" style="width: 20%;"></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="vo" items="${vos}">
					<tr>
						<td style="width: 5%; padding-top: 80px;"><input type="checkbox" name="chk" value="${vo.sId}" class="chk" /></td>
						<td class="td1" style="width: 50%;">
							<img src="${ctp}/resources/slideshow/${vo.sImage}" style="width: 300px;"/>
						</td>
						<td class="td1" style="width: 10%; padding-top: 80px;">
							<select name="sNum" id="sNum${vo.sId}" style="width: 50px;">
								<option value="1"<c:if test="${vo.sNum eq 1}">selected</c:if>>1</option>
								<option value="2"<c:if test="${vo.sNum eq 2}">selected</c:if>>2</option>
								<option value="3"<c:if test="${vo.sNum eq 3}">selected</c:if>>3</option>
								<option value="4"<c:if test="${vo.sNum eq 4}">selected</c:if>>4</option>
								<option value="5"<c:if test="${vo.sNum eq 5}">selected</c:if>>5</option>
								<option value="6"<c:if test="${vo.sNum eq 6}">selected</c:if>>6</option>
							</select>
						</td>
						<td style="width: 20%; padding-top: 80px;"><div class=boardbtn><a href="#" onclick="delCheck(${vo.sId})">삭제</a></div></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>