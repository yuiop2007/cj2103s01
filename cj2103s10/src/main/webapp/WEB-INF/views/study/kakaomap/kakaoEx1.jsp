<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>클릭한 위치에 마커 표시하기</title>
	<%@ include file="/WEB-INF/views/include/nav.jsp" %>
	<%@ include file="/WEB-INF/views/include/slide.jsp" %>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>클릭한 위치에 마커 표시하기</h2>
  <hr/>
  
	<div id="map" style="width:100%;height:400px;"></div>
	<p><em>지도를 클릭해주세요!</em></p> 
	<div id="clickLatlng"></div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=158c673636c9a17a27b67c95f2c6be5c"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(36.63508797975421, 127.45959376343134), // 지도의 중심좌표
		        level: 2 // 지도의 확대 레벨
		    };
		
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		// 지도를 클릭한 위치에 표출할 마커입니다
		var marker = new kakao.maps.Marker({ 
		    // 지도 중심좌표에 마커를 생성합니다 
		    position: map.getCenter() 
		}); 
		// 지도에 마커를 표시합니다
		marker.setMap(map);
		
		// 지도에 클릭 이벤트를 등록합니다
		// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
		    
		    // 클릭한 위도, 경도 정보를 가져옵니다 
		    var latlng = mouseEvent.latLng; 
		    
		    // 마커 위치를 클릭한 위치로 옮깁니다
		    marker.setPosition(latlng);
		    
		    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
		    message += '경도는 ' + latlng.getLng() + ' 입니다';
		    
		    var resultDiv = document.getElementById('clickLatlng'); 
		    resultDiv.innerHTML = message;
		    
		});
	</script>
	
	<hr/>
	<div>
	  <p>
	    <a href="${ctp}/study/kakaoEx1" class="btn btn-secondary">마커표시</a>&nbsp;
	    <a href="${ctp}/study/kakaoEx2" class="btn btn-secondary">지명검색</a>&nbsp;
	    <a href="${ctp}/study/kakaoEx3" class="btn btn-secondary">지명검색2</a>&nbsp;
	  </p>
	</div>
	
	</div>
  <p><br/></p>
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>
