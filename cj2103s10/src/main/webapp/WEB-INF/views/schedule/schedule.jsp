<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>schedule.jsp</title>
  <style>
    #td1,#td8,#td15,#td22,#td29,#td36 {
      color: red;
    }
    #td7,#td14,#td21,#td28,#td35,#td42 {
      color: blue;
    }
    .today {
      font-size: 1.5em;
      background-color: pink;
      color: #fff;
      /* text-align: center; */
    }
  </style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<p><br/></p>
<div class="container">
  <div class="col-sm-12" style="margin:30px 0px;font-size:1.4em;text-align:center">
    - 일 정 관 리 -
  </div>
  <div class="col-sm-12" style="margin:30px 0px;font-size:1.4em;text-align:center">
    <button class="btn btn-secondary btn-sm" onclick="location.href='${ctp}/schedule/schedule?yy=${yy-1}&mm=${mm}'" title="이전년도">◁◁</button>
    <button class="btn btn-secondary btn-sm" onclick="location.href='${ctp}/schedule/schedule?yy=${yy}&mm=${mm-1}'" title="이전월">◀</button>
    &nbsp; <font size="5">${yy}년 ${mm+1}</font> &nbsp;
    <button class="btn btn-secondary btn-sm" onclick="location.href='${ctp}/schedule/schedule?yy=${yy}&mm=${mm+1}'" title="다음월">▶</button>
    <button class="btn btn-secondary btn-sm" onclick="location.href='${ctp}/schedule/schedule?yy=${yy+1}&mm=${mm}'" title="다음년도">▷▷</button>
    &nbsp; &nbsp;
    <button class="btn btn-secondary btn-sm" onclick="location.href='${ctp}/schedule/schedule'" title="오늘날짜"><i class="fa fa-home"></i></button>
  </div>
  <br/>
  <div class="row">
    <div class="col-sm-12" style="height:450px;">
      <table class="table table-bordered" style="height:100%">
        <tr style="text-align:center;font-size:1em;background-color:#eee;height:10%;">
          <th style="color:red;width:13%;vertical-align:middle;">일</th>
          <th style="width:13%;vertical-align:middle;">월</th>
          <th style="width:13%;vertical-align:middle;">화</th>
          <th style="width:13%;vertical-align:middle;">수</th>
          <th style="width:13%;vertical-align:middle;">목</th>
          <th style="width:13%;vertical-align:middle;">금</th>
          <th style="color:blue;width:13%;vertical-align:middle;">토</th>
        </tr>
        <tr>
          <c:set var="cnt" value="1"/>
          <!-- 이전월의 마지막 날짜를 이용하여 앞쪽의 빈 공백을 채워준다. -->
          <c:forEach var="preDay" begin="${preLastDay - (startWeek - 2) }" end="${preLastDay}">
            <td id="td${cnt}" style="color: #ddd;font-size:0.6em">
              ${preYear}-${preMonth+1}-${preDay}
            </td>
            <c:set var="cnt" value="${cnt=cnt+1}"/>
          </c:forEach>
          
          <!-- 해당월에 대한 날짜를 반복 출력한다. -->
          <c:forEach begin="1" end="${lastDay}" varStatus="st">
            <c:set var="todaySw" value="${yy == toYear && mm == toMonth && st.count == toDay ? 1 : 0}"/>
            <td id="td${cnt}" ${todaySw == 1 ? 'class=today' : '' } style="font-size:0.9em;padding:8px;height:18%;">
            
              <c:set var="ymd" value="${yy}-${mm+1}-${st.count}"/>
              <a href="scMenu?mid=${smid}&ymd=${ymd}">
	              ${st.count}<br/>
	              
	              <!-- memory를 찍는중 같은 memory면 memory의 갯수로 대체해서 찍어준다. -->
	              <c:set var="tempMemory" value=""/>
	              <c:set var="tempCnt" value="0"/>
	              <c:set var="tempSw" value="0"/>
	              
	              <c:forEach var="vo" items="${vos}">
	                <c:if test="${fn:substring(vo.scDate,8,10) == st.count}">
	                  <c:if test="${vo.memory != tempMemory}">
	                    <c:if test="${tempSw != 0}">  <!-- 처음에 1번만 내용를 찍지않고 skip처리한다. -->
	                      - ${tempMemory}(${tempCnt}건)<br/>
	                      <c:set var="tempCnt" value="0"/>
	                    </c:if>
	                    <c:set var="tempMemory" value="${vo.memory}"/>
	                  </c:if>
	              		<c:set var="tempSw" value="1"/>
	                  <c:set var="tempCnt" value="${tempCnt + 1}"/>
	                </c:if>
	              </c:forEach>
	              <c:if test="${tempCnt != 0}">- ${tempMemory}(${tempCnt}건)</c:if>
              </a>
            </td>
            <c:if test="${cnt % 7 == 0}">
              </tr><tr>
            </c:if>
            <c:set var="cnt" value="${cnt=cnt+1}"/>
          </c:forEach>
          
          <!-- 다음월의 날짜를 출력한다. -->
          <c:forEach begin="${nextStartWeek}" end="7" varStatus="nextDay">
            <td id="td${cnt}" style="color: #ddd;font-size:0.6em">
              ${nextYear}-${nextMonth+1}-${nextDay.count}
            </td>
            <c:set var="cnt" value="${cnt=cnt+1}"/>
          </c:forEach>
        </tr>
      </table>
    </div>
  </div>
</div>
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>