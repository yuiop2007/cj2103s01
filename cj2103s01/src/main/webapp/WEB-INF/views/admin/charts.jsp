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
		var NowMonthPrice = parseInt($("#NowMonthPrice").val());
		var Pre1MonthPrice = parseInt($("#Pre1MonthPrice").val());
		var Pre2MonthPrice = parseInt($("#Pre2MonthPrice").val());
		var Pre3MonthPrice = parseInt($("#Pre3MonthPrice").val());
		var Pre4MonthPrice = parseInt($("#Pre4MonthPrice").val());
		var NowMadePrice = parseInt($("#NowMadePrice").val());
		var Pre1MadePrice = parseInt($("#Pre1MadePrice").val());
		var Pre2MadePrice = parseInt($("#Pre2MadePrice").val());
		var Pre3MadePrice = parseInt($("#Pre3MadePrice").val());
		var Pre4MadePrice = parseInt($("#Pre4MadePrice").val());
		var NowOuterPrice = parseInt($("#NowOuterPrice").val());
		var Pre1OuterPrice = parseInt($("#Pre1OuterPrice").val());
		var Pre2OuterPrice = parseInt($("#Pre2OuterPrice").val());
		var Pre3OuterPrice = parseInt($("#Pre3OuterPrice").val());
		var Pre4OuterPrice = parseInt($("#Pre4OuterPrice").val());
		var NowTopPrice = parseInt($("#NowTopPrice").val());
		var Pre1TopPrice = parseInt($("#Pre1TopPrice").val());
		var Pre2TopPrice = parseInt($("#Pre2TopPrice").val());
		var Pre3TopPrice = parseInt($("#Pre3TopPrice").val());
		var Pre4TopPrice = parseInt($("#Pre4TopPrice").val());
		var NowBottomPrice = parseInt($("#NowBottomPrice").val());
		var Pre1BottomPrice = parseInt($("#Pre1BottomPrice").val());
		var Pre2BottomPrice = parseInt($("#Pre2BottomPrice").val());
		var Pre3BottomPrice = parseInt($("#Pre3BottomPrice").val());
		var Pre4BottomPrice = parseInt($("#Pre4BottomPrice").val());
		var NowAccPrice = parseInt($("#NowAccPrice").val());
		var Pre1AccPrice = parseInt($("#Pre1AccPrice").val());
		var Pre2AccPrice = parseInt($("#Pre2AccPrice").val());
		var Pre3AccPrice = parseInt($("#Pre3AccPrice").val());
		var Pre4AccPrice = parseInt($("#Pre4AccPrice").val());
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
          ['Month', 'MADE', 'OUTER', 'TOP', 'BOTTOM', 'ACC', 'Average'],
          [Pre4Month,  Pre4MadePrice,      Pre4OuterPrice,         Pre4TopPrice,             Pre4BottomPrice,           Pre4AccPrice,      Pre4MonthPrice],
          [Pre3Month,  Pre3MadePrice,      Pre3OuterPrice,        Pre3TopPrice,             Pre3BottomPrice,          Pre3AccPrice,      Pre3MonthPrice],
          [Pre2Month,  Pre2MadePrice,      Pre2OuterPrice,        Pre2TopPrice,             Pre2BottomPrice,           Pre2AccPrice,      Pre2MonthPrice],
          [Pre1Month,  Pre1MadePrice,      Pre1OuterPrice,        Pre1TopPrice,             Pre1BottomPrice,           Pre1AccPrice,      Pre1MonthPrice],
          [NowMonth,  NowMadePrice,      NowOuterPrice,         NowTopPrice,             NowBottomPrice,          NowAccPrice,      NowMonthPrice]
        ]);

        var options = {
          title : '월별 총 판매량',
          vAxis: {title: '단위(원)',
        	  viewWindowMode:'explicit',
              viewWindow: {
                max:1000000,
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
			<input type="hidden" value="${NowMonthPrice}" id="NowMonthPrice">
			<input type="hidden" value="${Pre1MonthPrice}" id="Pre1MonthPrice">
			<input type="hidden" value="${Pre2MonthPrice}" id="Pre2MonthPrice">
			<input type="hidden" value="${Pre3MonthPrice}" id="Pre3MonthPrice">
			<input type="hidden" value="${Pre4MonthPrice}" id="Pre4MonthPrice">
			<input type="hidden" value="${NowMadePrice}" id="NowMadePrice">
			<input type="hidden" value="${Pre1MadePrice}" id="Pre1MadePrice">
			<input type="hidden" value="${Pre2MadePrice}" id="Pre2MadePrice">
			<input type="hidden" value="${Pre3MadePrice}" id="Pre3MadePrice">
			<input type="hidden" value="${Pre4MadePrice}" id="Pre4MadePrice">
			<input type="hidden" value="${NowOuterPrice}" id="NowOuterPrice">
			<input type="hidden" value="${Pre1OuterPrice}" id="Pre1OuterPrice">
			<input type="hidden" value="${Pre2OuterPrice}" id="Pre2OuterPrice">
			<input type="hidden" value="${Pre3OuterPrice}" id="Pre3OuterPrice">
			<input type="hidden" value="${Pre4OuterPrice}" id="Pre4OuterPrice">
			<input type="hidden" value="${NowTopPrice}" id="NowTopPrice">
			<input type="hidden" value="${Pre1TopPrice}" id="Pre1TopPrice">
			<input type="hidden" value="${Pre2TopPrice}" id="Pre2TopPrice">
			<input type="hidden" value="${Pre3TopPrice}" id="Pre3TopPrice">
			<input type="hidden" value="${Pre4TopPrice}" id="Pre4TopPrice">
			<input type="hidden" value="${NowBottomPrice}" id="NowBottomPrice">
			<input type="hidden" value="${Pre1BottomPrice}" id="Pre1BottomPrice">
			<input type="hidden" value="${Pre2BottomPrice}" id="Pre2BottomPrice">
			<input type="hidden" value="${Pre3BottomPrice}" id="Pre3BottomPrice">
			<input type="hidden" value="${Pre4BottomPrice}" id="Pre4BottomPrice">
			<input type="hidden" value="${NowAccPrice}" id="NowAccPrice">
			<input type="hidden" value="${Pre1AccPrice}" id="Pre1AccPrice">
			<input type="hidden" value="${Pre2AccPrice}" id="Pre2AccPrice">
			<input type="hidden" value="${Pre3AccPrice}" id="Pre3AccPrice">
			<input type="hidden" value="${Pre4AccPrice}" id="Pre4AccPrice">
		</div>
	</div>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>