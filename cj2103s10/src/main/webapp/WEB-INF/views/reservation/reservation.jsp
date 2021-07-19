<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>reservation.jsp</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script>
    // 방의 정보를 보여주는 새창띄우기
    function newWin(room) {
    	var url = "${ctp}/reservation/roomInformation?room="+room;
    	window.open(url,"roomView","width=850,height=800");
    }
  </script>
  <style>
    /*달력의 매주 일요일 텍스트 컬러 적용 부분*/
    #td1,#td8,#td15,#td22,#td29,#td36 {
      color: red;
    }

    /*달력의 매주 토요일 텍스트 컬러 적용 부분*/
    #td7,#td14,#td21,#td28,#td35,#td42 {
      color: blue;
    }

    /* 오늘날짜에 배경색 처리 */
    .today {
      background-color: #f94e3f;
      font-size: 1.5em;
      /* font-family: cursive; */
      /* color: #fff; */
    }
	</style>
</head>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<p></p>
<div class="container" style="margin-top: 30px;margin-bottom: 100px;">
  <div class="row">
    <div class="col-sm-12" style="font-size:1.8em;text-align:center;">
		  - 객 실 예 약 관 리 -
    </div>
    <div class="col-sm-12">
      <br/>  <!-- 한줄 공백 삽입 -->
    </div>
    <div class="col-sm-12" style="font-size: 1.4em;text-align: center;">
      <c:if test="${slevel != 0}"><button class="btn btn-success btn-sm" onclick="location.href='${ctp}/reservation/reservationView'">예약현황보기</button></c:if>
      <%-- <c:if test="${slevel == 0}"><button class="btn btn-success btn-sm" onclick="location.href='${ctp}/reservation/reservationStatusView'">예약현황보기</button></c:if> --%>
      &nbsp; &nbsp;
      <button class="btn btn-secondary btn-sm" onclick="location.href='${ctp}/reservation/reservation?yy=${yy-1}&mm=${mm}'" title="이전년도">◁◁</button>
      <button class="btn btn-secondary btn-sm" onclick="location.href='${ctp}/reservation/reservation?yy=${yy}&mm=${mm-1}'" title="이전월">◀</button>
      &nbsp; <font size="5">${yy}년 ${mm+1}월 </font> &nbsp;
      <button class="btn btn-secondary btn-sm" onclick="location.href='${ctp}/reservation/reservation?yy=${yy}&mm=${mm+1}'" title="다음월">▶</button>
      <button class="btn btn-secondary btn-sm" onclick="location.href='${ctp}/reservation/reservation?yy=${yy+1}&mm=${mm}'" title="다음년도">▷▷</button>
      &nbsp; &nbsp;
      <button class="btn btn-success" onclick="location.href='${ctp}/reservation/reservation'" title="오늘날짜"><i class="fa fa-home"></i></button>
    </div>
  </div>
  <div class="col-sm-12">
    <br/>
  </div>
  <div class="row">
    
    <div class="col-sm-12" style="text-align:left;height:430px;">
      <table class="table table-bordered" style="height:100%">
        <tr style="text-align:center;font-size:1em;background-color:#eee;height:10%">
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

          <!-- 이전월의 마지막 날짜를 출력하는 부분 -->
          <c:forEach var="preDay" begin="${preLastDay - (startWeek - 2) }" end="${preLastDay}">
            <td id="td${cnt}" style="color: #ddd;font-size:0.6em">
              ${preYear}-${preMonth+1}-${preDay}
            </td>
            <c:set var="cnt" value="${cnt=cnt+1}"/>
          </c:forEach>

<!-- 아래로 이번달 달력처리부분 : 객실은 예약이 되지 않은 방은 오늘날짜부터 이후로 예약 받을수 있게 처리하고, 오늘이 지난객실의 예약유무는 출력하지 않는다. -->
					<c:set var="now" value="<%=new java.util.Date() %>"/>			<!-- 아래쪽에서 오늘날짜를 비교하기위해 날짜 객체로 오늘 날짜를 생성... -->
					<c:set var="now"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:set>			<!-- '년-월-일' -->
          <!-- 해당월에 대한 날짜를 반복 출력 -->
          <c:forEach begin="1" end="${lastDay}" varStatus="st">
            <c:set var="todaySw" value="${yy == toYear && mm == toMonth && st.count == toDay ? 1 : 0}"/>	     				<!-- 오늘날짜가 맞으면 todaySw변수에는 1을 셋팅한다. -->
            <td id="td${cnt}" ${todaySw == 1 ? 'class=today' : ''} style="font-size:0.9em;padding:8px; height:20%;">	<!-- 오늘이 맞으면 class로 셀의 배경색을 변경시켰다. -->
              <c:set var="ymd" value="${yy}-${mm+1}-${st.count}"/>  									<!-- jstl에는 concat가 없으므로 날짜를 직접 결합하면된다. -->
              <c:if test="${(mm+1)<10}"><c:set var="mmTemp" value="0${mm+1}"/></c:if> <!-- 날짜비교를 위해 '월'과 '일' 앞에 '0'을 붙여서 두자리로 만들었다.(00월 00일) -->
              <c:if test="${(mm+1)>=10}"><c:set var="mmTemp" value="${mm+1}"/></c:if>
              <c:if test="${st.count<10}"><c:set var="ddTemp" value="0${st.count}"/></c:if>
              <c:if test="${st.count>=10}"><c:set var="ddTemp" value="${st.count}"/></c:if>
              <c:set var="ymdTemp" value="${yy}-${mmTemp}-${ddTemp}"/>								<!-- ymdTemp변수는 '2021-07-20' 과 같은 형식으로 기억하고 있다. -->
              ${st.count}<br/>				<!-- 화면의 달력란에 해당 일자가 출력된다. -->
              <!-- 아래는, 오늘날짜 부터 이후로 준비된 모든 객실을 찍어준다.('예약가능/불가능' 객실들 모두 찍는다.) -->
              <c:if test="${now <= ymdTemp}">  <!-- 오늘날짜 이전까지는 이곳을 통과하지 않는다.(즉, 오늘 날짜 이후부터만 객실을 찍는다.) -->
	              <c:forEach var="rVo" items="${roomVos}">  <!-- 객실 개수만큼 반복하여 해당 객실의 여부를 체크한다. -->
	                <c:if test="${rVo.available == 'OK'}">
		              	<span style="color:brown;font-size:0.8em;"><a href="javascript:newWin('${rVo.room}')">${rVo.room}</a></span> <!-- 객실명을 먼저 출력시켜서 클릭하면 정보를 볼 수 있게한다. -->
			              <c:set var="ymdSw" value="true"/>				<!-- break문이 없기에 ymdSw변수를 이용하여, 초기값은 true로 주고, 예약불가일때 ymdSw변수는 false로 처리시켜 115번행쪽을 처리하지 못하게 했다. -->
		                <c:set var="statusMsg" value="YES"/>								<!-- 예약가능메세지변수(statusMsg)의 초기값을 'YES'로 지정하여 115라인을 무조건 통과하게 처리했다. -> 그곳에서 이미예약되었다는 사실을 알게되면 바로 startMsg변수는 'NO'로 셋팅하여 해당날짜가 예약되었기에, 다시 들어오지 못하게 처리함. -->
		              	<c:forEach var="statusVo" items="${statusVos}">  		<!-- 해당 객실이 예약인지 아닌지를 체크한다. -->
		              	  <c:set var="statusYMD" value="${fn:substring(statusVo.reservationDate,0,10)}"/>
		              	  <c:if test="${ymdSw}">			<!-- 초기값이 true이기에 무조건 아래를 수행하고, 해당일자가 예약불가일때는 false값을 만들어서 이곳을 더이상 통과시키지 않게한다. 그래서 122번을 만나서 다시 다음날짜체크를 위해 113번행으로 가서 다시 초기값을 True로 지고 반복처리한다. -->
			              		<c:if test="${rVo.room eq statusVo.reservationRoom and statusYMD eq ymdTemp}">  <!-- 달력 표시날짜 해당객실에 예약된자료가 있으면 '예약불가'준비한다. -->
			              		  <c:set var="statusMsg" value="NO"/>		<!-- 예약불가 -->
			              		  <c:set var="ymdSw" value="false"/>		<!-- 더이상 이곳을 들어오지 못하게 한다.(break문이 없기에..ㅜㅜ..) -->
			              		  <c:set var="statusIdx" value="${statusVo.idx}"/>	<!-- 관리자가 예약고객 정보 보게하기위해서 예약상태 테이블의 idx를 저장했다. -->
			              		</c:if>
			              	</c:if>
		              	</c:forEach>
		              	<c:if test="${statusMsg eq 'NO'}">		<!-- 이미 예약되어있기에 '예약불가'를 출력한다. 이때 관리자는 예약된 상황을 볼 수 있게 처리한다. -->
		              	  <c:if test="${slevel == 0}"><span class="badge badge-danger"><a href="reservationContent?idx=${statusIdx}&ymd=${ymd}">예약불가</a></span></c:if>
		              	  <c:if test="${slevel != 0}"><span class="badge badge-danger">예약불가</span></c:if>
		              	</c:if>
		              	<c:if test="${statusMsg ne 'NO'}"><a href="reservationInput?ymd=${ymd}&room=${rVo.room}" class="btn badge badge-secondary">예약가능</a></c:if><br/>
		              </c:if>
	              </c:forEach>
              </c:if>
            </td>
            <c:if test="${cnt % 7 == 0}">   <!-- 토요일이면 줄 바꾸시오. -->
              </tr><tr>
            </c:if>
            <c:set var="cnt" value="${cnt=cnt+1}"/>  <!-- 각 테이블 셀마다 고유 아이디를 주기위해 cnt변수사용. -->
          </c:forEach>

          <!-- 비어있는 td안에 다음월의 날짜를 출력한다. -->
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