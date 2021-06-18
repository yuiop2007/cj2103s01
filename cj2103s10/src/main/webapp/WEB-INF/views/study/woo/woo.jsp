<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>woo.jsp(우편번호검색)</title>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="${ctp}/resources/js/woo.js"></script>
  <script>
  
  </script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<p><br/></p>
<div class="container">
  <h2>다음 우편번호 API</h2>
  <hr/>
  <form name="myform" method="post">
    <input type="text" id="sample6_postcode" placeholder="우편번호">
		<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
		<input type="text" id="sample6_address" placeholder="주소"><br>
		<input type="text" id="sample6_detailAddress" placeholder="상세주소">
		<input type="text" id="sample6_extraAddress" placeholder="참고항목">
  </form> 
</div>
<p><br/></p>

</body>
</html>