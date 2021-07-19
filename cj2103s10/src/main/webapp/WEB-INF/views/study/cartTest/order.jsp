<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <meta charset="UTF-8">
  <title>order.jsp(주문/결재)</title>
  <script type="text/javascript" src="//code.jquery.com/jquery-3.1.1.js"></script> 
  <script>
	  $(document).ready(function(){
		  $(".nav-tabs a").click(function(){
		    $(this).tab('show');
		  });
		  $('.nav-tabs a').on('shown.bs.tab', function(event){
		    var x = $(event.target).text();         // active tab
		    var y = $(event.relatedTarget).text();  // previous tab
		  });
		});
  
    function order() {
    	var payMethodCard = document.getElementById("payMethodCard").value;
    	var payMethodBox = document.getElementById("payMethodBox").value;
    	if(payMethodCard == "" && payMethodBox == "") {
    		alert("결제방식과 결제번호를 입력하세요.");
    		return false;
    	}
    	var ans = confirm("결재하시겠습니까?");
    	if(ans) {
	    	//myform.action = "${ctp}/study/cartTest/orderInput";
	    	//myform.submit();
	    	alert("결재 완료 되었습니다.");
    	}
    }
  </script>
  <style>
	  td {
	    text-align: center;
	  }
	</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<p><br/></p>
<div class="container">
	<div><h2>주문 / 결제</h2></div>
	<p><br/></p> 
	
	<table class="table table-bordered">
	  <tr class="table-dark text-dark">
	    <td colspan="2">상품(주문번호 : <font color="#00f">${orderIdx}</font>)</td>
	    <td>총상품금액</td>
	  </tr>
	    
	  <!-- 주문서 목록출력 -->
	  <c:set var="orderTotalPrice" value="0"/>
	  <c:forEach var="vo" items="${vos}">
	    <tbody>
	    <tr align="center">
	      <td><img src="${ctp}/resources/images/${vo.thumbImg}" width="120px"/></td>
	      <td align="left">
	        <p>
	          <br/>모델명 : <span style="color:orange;font-weight:bold;">${vo.product}</span>
	          <br/>금액 : <fmt:formatNumber value="${vo.price}"/>원
	          <br/>수량 : ${vo.su}
	        </p><br/>
	      </td>
	      <td>
	        <b>총 : <fmt:formatNumber value="${vo.price * vo.su}" pattern='#,###원'/></b><br/><br/>
	      </td>
	    </tr>
	    </tbody>
		  <c:set var="orderTotalPrice" value="${orderTotalPrice + (vo.price * vo.su)}"/>
	  </c:forEach>
	</table>
	<div>
	  <div style="width:100%; background-color:#eee;text-align:center;">
	    <h3 class="p-3">총 주문(결재) 금액 : <font color="orange"><fmt:formatNumber value="${orderTotalPrice}" pattern='#,###원'/></font></h3>
	  </div>
	</div>
	<p><br/></p>
	<form name="myform" method="post">
		<div class="head"><h2>배송지 정보 / 결재수단</h2></div>
		<p>이름 : <input type="text" name="name" value="${memberVO.name}"/></p>
		<p>주소 : <input type="text" name="address" value="${memberVO.address}"/></p>
		<p>연락처 : <input type="text" name="tel" value="${memberVO.tel}"/></p>
		<p>배송시 요청사항 : <input type="text" name="message"/></p>
		<hr/>
		
	  <!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
      <li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#card">카드결재</a></li>
	    <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#bank">은행결재</a></li>
	    <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#telCheck">상담사연결</a></li>
	  </ul>
	
	  <!-- Tab panes -->
	  <div class="tab-content">
	    <div id="card" class="container tab-pane active"><br>
	      <h3>카드결재</h3>
	      <p>
	        <select name="payment">
	          <option value="국민">국민</option>
	          <option value="현대">현대</option>
	          <option value="신한">신한</option>
	          <option value="농협">농협</option>
	          <option value="BC">BC</option>
	          <option value="롯데">롯데</option>
	          <option value="삼성">삼성</option>
	          <option value="LG">LG</option>
	        </select>
	      </p>
				<p>카드번호 : <input type="text" name="payMethod" id="payMethodCard"/></p>
	    </div>
	    <div id="bank" class="container tab-pane fade"><br>
	      <h3>은행결재</h3>
	      <p>
	        <select name="payment">
	          <option value="국민">국민(111-111-111)</option>
	          <option value="신한">신한(222-222-222)</option>
	          <option value="우리">우리(333-333-333)</option>
	          <option value="농협">농협(444-444-444)</option>
	          <option value="신협">신협(555-555-555)</option>
	        </select>
	      </p>
				<p>입금자명 : <input type="text" name="payMethod" id="payMethodBox"/></p>
	    </div>
	    <div id="telCheck" class="container tab-pane fade"><br>
	      <h3>전화상담</h3>
	      <p>콜센터(☎) : 02-1234-1234</p>
	    </div>
	  </div>
		<hr/>
		<div align="center">
		  <button type="button" class="btn btn-secondary" onClick="order()">결제하기</button>
		  <a href="${ctp}/study/dbshop/dbShopList" class="btn btn-secondary">계속쇼핑하기</a>
		</div>
		<input type="hidden" name="orderVos" value="${orderVos}"/>
	  <input type="hidden" name="orderIdx" value="${orderIdx}"/>  <!-- 주문번호 -->
	  <input type="hidden" name="orderTotalPrice" value="${orderTotalPrice}"/>
	  <input type="hidden" name="mid" value="${smid}"/>
	</form>
</div>
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>