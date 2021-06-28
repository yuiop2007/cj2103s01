<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>Kakao 지도 시작하기</title>
	<%@ include file="/WEB-INF/views/include/nav.jsp" %>
	<%@ include file="/WEB-INF/views/include/slide.jsp" %>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>KaKao Maps API 연습(기본지도)</h2>
  
	<div id="map" style="width:100%;height:500px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=158c673636c9a17a27b67c95f2c6be5c"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(36.63508797975421, 127.45959376343134),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
	</script>
	
	<hr/>
	<%@ include file="/WEB-INF/views/study/kakaomap/kakaoMenu.jsp" %>
</div>
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>
