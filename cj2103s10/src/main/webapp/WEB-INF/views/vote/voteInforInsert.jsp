<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <meta charset="EUC-KR">
  <title>voteInforInsert.jsp</title>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		  function fCheck() {
			  var gender = myform.gender.value;
			  var age = myform.age.value;
			  var job = document.getElementById("job").value;
			  var area = myform.area.value;
			  if(gender == "") {
				  alert("성별을 선택하세요!");
			  }
			  else if(age == "") {
				  alert("나이를 선택하세요!");
			  }
			  else if(job == "") {
				  alert("직업을 선택하세요!");
			  }
			  else if(area == "") {
				  alert("지역을 선택하세요!");
			  }
			  else {
				  myform.submit();
			  }
		  }
	  //});
	</script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<p><br/></p>
<div class="container">
  <form name="myform" method="post" class="w3-container w3-card-4 w3-light-grey w3-text-black w3-margin">
		<h2 class="w3-center">투표 정보 등록</h2>
		<div class="w3-row w3-section">
		  <div class="w3-col" style="width:60px">성 별</div>
      <input class="w3-radio" type="radio" name="gender" value="남자" <c:if test="${gender=='남자'}">checked</c:if>><label>남자</label>&nbsp;
		  <input class="w3-radio" type="radio" name="gender" value="여자" <c:if test="${gender=='여자'}">checked</c:if>><label>여자</label>
		</div>
		<div class="w3-row w3-section">
		  <div class="w3-col" style="width:60px">연 령</div>
      <input class="w3-radio" type="radio" name="age" value="10대"><label>10대</label>&nbsp;
      <input class="w3-radio" type="radio" name="age" value="20대"><label>20대</label>&nbsp;
      <input class="w3-radio" type="radio" name="age" value="30대"><label>30대</label>&nbsp;
      <input class="w3-radio" type="radio" name="age" value="40대"><label>40대</label>&nbsp;
      <input class="w3-radio" type="radio" name="age" value="50대"><label>50대</label>&nbsp;
		  <input class="w3-radio" type="radio" name="age" value="60대"><label>60대</label>
		</div>
		<div class="w3-row w3-section">
		  <div class="w3-col" style="width:60px">직 업</div>
      <select class="w3-select w3-border" name="job" id="job">
		    <option value="" disabled selected>직업선택</option>
		    <option value="학생">학생</option>
		    <option value="회사원">회사원</option>
		    <option value="공무원">공무원</option>
		    <option value="군인">군인</option>
		    <option value="자영업">자영업</option>
		    <option value="프리랜서">프리랜서</option>
		    <option value="기타">기타</option>
		  </select>
		</div>
		<div class="w3-row w3-section">
		  <div class="w3-col" style="width:60px">지 역</div>
      <select class="w3-select w3-border" name="area">
		    <option value="" disabled selected>지역선택</option>
		    <option value="서울">서울</option>
		    <option value="경기도">경기도</option>
		    <option value="강원도">강원도</option>
		    <option value="충청도">충청도</option>
		    <option value="전라도">전라도</option>
		    <option value="경상도">경상도</option>
		    <option value="제주도">제주도</option>
		  </select>
		</div>
		<button type="button" class="w3-button w3-block w3-section w3-gray w3-ripple w3-padding" onclick="fCheck()">전 송</button>
	</form>
</div>
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>