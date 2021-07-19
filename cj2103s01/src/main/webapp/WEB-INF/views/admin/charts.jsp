<%@page import="java.util.List"%>
<%@page import="com.spring.cj2103s01.vo.ProductVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp" />
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load("current", {packages:["corechart"]});
	google.charts.setOnLoadCallback(drawChart);
	function drawChart() {
		var MadeSell = parseInt($("#MadeSell").val());
		var OuterSell = parseInt($("#OuterSell").val());
		var TopSell = parseInt($("#TopSell").val());
		var BottomSell = parseInt($("#BottomSell").val());
		var AccSell = parseInt($("#AccSell").val());
		
		var data = google.visualization.arrayToDataTable([
		  ['Task', 'Hours per Day'],
		  ['MADE',     MadeSell],
		  ['OUTER',      OuterSell],
		  ['TOP',  TopSell],
		  ['BOTTOM', BottomSell],
		  ['ACC',    AccSell]
		]);
		
		var options = {
		  title: '카테고리 별 판매량',
		  pieHole: 0.4,
		};
		
		var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
		chart.draw(data, options);
	}
	
	google.charts.setOnLoadCallback(drawVisualization);
	function drawVisualization() {
		var NowMonth = $("#NowMonth").val();
		var Pre1Month = $("#Pre1Month").val();
		var Pre2Month = $("#Pre2Month").val();
		var Pre3Month = $("#Pre3Month").val();
		var Pre4Month = $("#Pre4Month").val();
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
          ['Month', 'MADE', 'OUTER', 'TOP', 'BOTTOM', 'ACC', 'Average'],
          [Pre4Month,  100,      200,         100,             200,           100,      614.6],
          [Pre3Month,  135,      1120,        599,             1268,          288,      682],
          [Pre2Month,  157,      1167,        587,             807,           397,      623],
          [Pre1Month,  139,      1110,        615,             968,           215,      609.4],
          [NowMonth,  136,      691,         629,             1026,          366,      569.6]
        ]);

        var options = {
          title : '월별 총 판매량',
          vAxis: {title: '단위(만원)',
        	  viewWindowMode:'explicit',
              viewWindow: {
                max:500,
                min:0
              }  
          },
          hAxis: {title: 'Month'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}
        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
	<h6>총 판매량</h6>
		<br/><br/><br/><br/>
		❗&nbsp;&nbsp;<strong style="color: red; font-size: 8px;">모든 매출액은 배송완료된 상품의 구매확정 상태만 집계됩니다.</strong>
		<br/><br/><br/><br/>
		<div id="donutchart" style="width: 600px; height: 300px;">
			<input type="hidden" value="${MadeSell}" id="MadeSell">
			<input type="hidden" value="${OuterSell}" id="OuterSell">
			<input type="hidden" value="${TopSell}" id="TopSell">
			<input type="hidden" value="${BottomSell}" id="BottomSell">
			<input type="hidden" value="${AccSell}" id="AccSell">
		</div>
		
		<div id="chart_div" style="width: 900px; height: 500px;">
			<input type="hidden" value="${NowMonth}" id="NowMonth">
			<input type="hidden" value="${Pre1Month}" id="Pre1Month">
			<input type="hidden" value="${Pre2Month}" id="Pre2Month">
			<input type="hidden" value="${Pre3Month}" id="Pre3Month">
			<input type="hidden" value="${Pre4Month}" id="Pre4Month">
		</div>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>