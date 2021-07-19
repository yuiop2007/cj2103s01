<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>analysisPieChart.jsp</title>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
		google.charts.load('current', {'packages':['bar']});
    google.charts.setOnLoadCallback(drawChart);
		
		function drawChart() {
			var pVo = "${pVo.subTitle}".split("/");
			var data = google.visualization.arrayToDataTable([
        ['항목', '투표수','지역별','연령별'],
        [pVo[0], Number("${vos.get(0).voteSum}"), 4, 2],
        [pVo[1], Number("${vos.get(1).voteSum}"),  4, 2],
        [pVo[2], Number("${vos.get(2).voteSum}"), 11, 3],
        [pVo[3], Number("${vos.get(3).voteSum}"),  5, 3],
        [pVo[4], Number("${vos.get(4).voteSum}"),  5, 3]
      ]);
			var options = {'title':'주제 : ${pVo.title}', 'width':640, 'height':350};
			var chart = new google.charts.Bar(document.getElementById('chartView'));
      chart.draw(data, google.charts.Bar.convertOptions(options));
		}
		
		function changeChart() {
			var chartFlag = myform.choiceChart.value;
			location.href="${ctp}/vote/chartAnalysis?idx=${vos.get(0).popularIdx}&chartFlag="+chartFlag;
		}
	</script>
</head>
<body>
<div class="container">
  - 주제명 : ${pVo.title}<br/>
  - 투표 참여인원 : 총 ${voteTotCnt}명
  <form name="myform">
    <p>선택 :
    <select name="choiceChart" onchange="changeChart()">
      <option value="pie" <c:if test="${chartFlag=='pie'}"> selected </c:if>>원형차트보기</option>
      <option value="bar" <c:if test="${chartFlag=='bar'}"> selected </c:if>>막대차트보기</option>
    </select>
  </form>
  <div id="chartView"></div>
</div>
</body>
</html>