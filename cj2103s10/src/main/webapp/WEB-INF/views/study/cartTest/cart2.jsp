<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>cart.jsp(장바구니)</title>
  <script>
  	function onTotal(){
      var total = 0;
      var maxIdx = document.getElementById("maxIdx").value;
      for(var i=1;i<=maxIdx;i++){
        if($("#totalPrice"+i).length != 0 && document.getElementById("idx"+i).checked){  
          total = total + parseInt(document.getElementById("totalPrice"+i).value); 
        }
      }
      var lastTotalPrice = total;
      document.getElementById("lastTotalPrice").value = numberWithCommas(lastTotalPrice);
    }
  
  	function onCheck(){
      var maxIdx = document.getElementById("maxIdx").value;

      var cnt=0;
      for(var i=1;i<=maxIdx;i++){
        if($("#idx"+i).length != 0 && document.getElementById("idx"+i).checked==false){
          cnt++;
          break;
        }
      }
      if(cnt!=0){
        document.getElementById("allcheck").checked=false;
      } 
      else {
        document.getElementById("allcheck").checked=true;
      }
      onTotal();
    }
  	
  	function allCheck(){
      var maxIdx = document.getElementById("maxIdx").value;
      if(document.getElementById("allcheck").checked){
        for(var i=1;i<=maxIdx;i++){
          if($("#idx"+i).length != 0){
            document.getElementById("idx"+i).checked=true;
          }
        }
      }
      else {
        for(var i=1;i<=maxIdx;i++){
          if($("#idx"+i).length != 0){
            document.getElementById("idx"+i).checked=false;
          }
        }
      }
      onTotal();
    }
  	
  	function order() {
  		if($("input:checkbox[name='idxChecked']").is(":checked")) {
  			var idxs = [];
  			$("input:checkbox[name='idxChecked']:checked").each(function() {
  				idxs.push(this.id);
  			});
  			location.href = "${ctp}/study/cartTest/order?idxs="+idxs;
  		}
  		else {
  			alert("주문할실 상품을 한개이상 선택하세요!");
  		}
  	}
  	
  	function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
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
	<div class="head"><h2>장바구니 테스트</h2></div>
	<br/>
	<form name="myform" method="post">
		<table class="table table-bordered table-hover">
		  <tr class="table-dark text-dark">
		    <td><input type="checkbox" id="allcheck" onClick="allCheck()"/> 전체</td>
		    <td colspan="2">상품</td>
		    <td>총상품금액</td>
		  </tr>
		    
		  <!-- 장바구니 목록출력 -->
		  <c:forEach var="vo" items="${vos}">
		    <tbody>
		    <tr>
		      <td><input type="checkbox" name="idxChecked" id="idx${vo.idx}" value="${vo.idx}" onClick="onCheck()" /></td>
		      <td><img src="${ctp}/resources/images/${vo.thumbImg}" width="120px"/></td>
		      <td align="left">
		        <p >
		          <br/>모델명 : <span style="color:orange;font-weight:bold;">${vo.product}</span>
		          <br/>금액 : <fmt:formatNumber value="${vo.price}"/>원
		          <br/>수량 : ${vo.su}
		        </p><br/>
		      </td>
		      <td><br/>
		        <b>총 : <fmt:formatNumber value="${vo.price * vo.su}" pattern='#,###원'/></b><br/><br/>
		        <input type="hidden" id="totalPrice${vo.idx}" value="${vo.price * vo.su}"/>
		      </td>
		    </tr>
		    </tbody>
		    <c:set var="maxIdx" value="${vo.idx}"/>
		  </c:forEach>
		</table>
    <input type="hidden" id="maxIdx" value="${maxIdx}"/>
	</form>
	<br/>
	<div align="center">
	  <b>실제 주문 총 금액</b>(구매하실 상품에 체크해 주세요. 총 주문금액이 산출됩니다.)
	</div>
	<div class="finalTot">
	  <div class="totBox text-center">
	    <br/><h4>총 주문한 상품금액 :
	    <input type="text" name="lastTotalPrice" id="lastTotalPrice" value="0" readonly class="p-2 text-right" style="color:brown;"/></h4>
	  </div>
	</div>
	 
	<p><br/></p>
	<div align="center">
	  <button class="btn btn-secondary" onClick="order()">주문하기</button>
	  <button class="btn btn-secondary" onClick="">계속 쇼핑하기</button>
	</div>
</div>
<p><br/></p>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>