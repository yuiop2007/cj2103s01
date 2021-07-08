<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
int level = session.getAttribute("slevel") == null ? 99 : (int) session.getAttribute("slevel");
%>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp" />
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function sample6_execDaumPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var addr = ''; // 주소 변수
	            var extraAddr = ''; // 참고항목 변수
	            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                addr = data.roadAddress;
	            } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                addr = data.jibunAddress;
	            }
	            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	            if(data.userSelectedType === 'R'){
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraAddr !== ''){
	                    extraAddr = ' (' + extraAddr + ')';
	                }
	                // 조합된 참고항목을 해당 필드에 넣는다.
	                document.getElementById("sample6_extraAddress").value = extraAddr;
	            
	            } else {
	                document.getElementById("sample6_extraAddress").value = '';
	            }
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('sample6_postcode').value = data.zonecode;
	            document.getElementById("sample6_address").value = addr;
	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById("sample6_detailAddress").focus();
	        }
	    }).open();
	}
	
	function checkExistData(value, dataName){
    	if(value == ''){
    		alert(dataName + ' 입력해주세요.');
    		return false;
    	}
    	return true;
    }
    
    function checkAll(){
    	if(!checkName(orderForm.oName.value)){
    		return false;
    	}
    	else if(!checkPhone(orderForm.tel2.value, orderForm.tel3.value)){
    		return false;
    	}
    	else if(!checkMail(orderForm.oEmail.value)){
    		return false;
    	}
		return true;
    }
    
	function checkName(name){
    	if(!checkExistData(name, '이름을')){
    		orderForm.oName.focus();
    		return false;	
    	}
    	
    	var nameRegExp = /^[가-힣]{2,4}$/;
    	if(!nameRegExp.test(name)){
    		alert('이름이 올바르지 않습니다.');
    		orderForm.oName.value = '';
    		orderForm.oName.focus();
    		return false;
    	}
    	return true;
    }
	function checkMail(mail){
    	if(!checkExistData(mail, '이메일을')){
    		orderForm.oEmail.focus();    		
    		return false;
    	}
    	
    	var emailRegExp = /^([\w\.\_\-])*[a-zA-Z0-9]+([\w\.\_\-])*([a-zA-Z0-9])+([\w\.\_\-])+@([a-zA-Z0-9]+\.)+[a-zA-Z0-9]{2,8}$/;
    	if(!emailRegExp.test(mail)){
    		alert('이메일 형식이 올바르지 않습니다.');
    		orderForm.oEmail.value = '';
    		orderForm.oEmail.focus();
    		return false;
    	}
    	return true
    }
	function checkPhone(tel2, tel3){
    	if(!checkExistData(tel2, '휴대폰 번호를')){
    		orderForm.tel2.focus();
    		return false;
    	}
    	if(!checkExistData(tel3, '휴대폰 번호를')){
    		orderForm.tel3.focus();
    		return false;
    	}
    	
    	var tel2RegExp = /^[0-9]{3,4}$/;
    	var tel3RegExp = /^[0-9]{4}$/;
    	
    	if(!tel2RegExp.test(tel2)){
    		alert('휴대폰 형식이 올바르지 않습니다.')
    		orderForm.tel2.value = '';
    		orderForm.tel2.focus();
    		return false;
    	}
    	if(!tel3RegExp.test(tel3)){
    		alert('휴대폰 형식이 올바르지 않습니다.')
    		orderForm.tel3.value = '';
    		orderForm.tel3.focus();
    		return false;
    	}
    	return true;
    }

	function orderCheck() {
		var ans = confirm("상품을 결제하시겠습니까?");
		if (ans) {
			var check = checkAll();
			
			if(check){
				orderForm.submit();
			}
			
		}
	}
	
	$(document).ready(function(){
		$("#mile").change(function () {
			var oPrice = $("#oPricemilecheck").val();
			var pSale = $("#salecheck").val();
			var mPoint = $("#mPoint").val();
			var mile = $("#mile").val();
			var mileRegExp = /^[0-9]{0,9}$/;
			
			if(!mileRegExp.test(mile)){
				alert("적립금을 올바로 입력하시오.");
				orderForm.mile.value = '';
				orderForm.mile.focus();
			}
			
			oPrice = parseInt(oPrice);
			mPoint = parseInt(mPoint);
			mile = parseInt(mile);
			pSale = parseInt(pSale);
			
			if(mile > mPoint){
				alert("사용 가능한 적립금을 입력해주세요.");
				orderForm.mile.value = '';
				orderForm.mile.focus();
			}
			else{
				oPrice = oPrice - mile;
				pSale = pSale + mile;
				orderForm.oPrice.value = oPrice;
				orderForm.pSale.value = pSale;
			}
		});
	});
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
	</div>
	<div class="container">
		<h6>주문</h6>
		<br/><br/><br/><br/>
		<div class="xans-element- xans-order xans-order-dcinfo ec-base-box typeMember  ">
			<div class="information">
           		<h3 class="title">혜택정보</h3>
	            <div class="description">
	                <div class="member ">
	                    <p><strong>${sname}</strong> 님은, [
	                    <c:if test="${slevel eq 4}">준회원</c:if>
	                    <c:if test="${slevel eq 3}">정회원</c:if>
	                    <c:if test="${slevel eq 2}">우수회원</c:if>
	                    <c:if test="${slevel eq 1}">특별회원</c:if>
	                    ] 이십니다.</p>
					</div>
	                <ul class="mileage">
						<li><a href="#">가용적립금 : <strong style="color: #f76560;">${mvo.mPoint}원</strong></a></li>
	                    <li><a href="${ctp}/admin/coupon">쿠폰 : <strong style="color: #f76560;">${cpCnt}개</strong></a></li>
	                </ul>
				</div>
			</div>
		</div>
		<form name="orderForm" method="post" action="${ctp}/order/orderInfo">

			<table class="table table-borderless">
				<thead>
					<tr>
						<th scope="col" style="width: 5%;">NO</th>
						<th scope="col" style="width: 10%;"></th>
						<th scope="col" style="width: 35%; text-align: left;">상품명/옵션</th>
						<th scope="col" style="width: 5%;">수량</th>
						<th scope="col" style="width: 10%;">적립금</th>
						<th scope="col" style="width: 10%;">할인율(원)</th>
						<th scope="col" style="width: 10%;">가격</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="totPrice" value="0"/>
				<c:set var="totPoint" value="0"/>
				<c:set var="pSale" value="0"/>
				<c:set var="curScrStartNo" value="${csize}" />
				<c:set var="Delivery" value="3000"/>
				<c:forEach var="cvo" items="${cvos}">
					<tr>
						<td class="td1" style="width: 5%; padding-top: 55px;">${curScrStartNo}</td>
						<c:forEach var="pvo" items="${pvos}">
							<c:if test="${pvo.pId eq cvo.pId}">
								<td class="td1" style="width: 10%;">
									<a href="${ctp}/product/pContent?pId=${cvo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}"><img src="${ctp}/resources/pMainImages/${pvo.pImage}" style="width: 80px;"/></a>
								</td>
								<td class="td2" style="width: 35%; text-align: left; padding-top: 55px;">
									<a href="${ctp}/product/pContent?pId=${cvo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${pvo.pName}/${cvo.pOption}</a>
								</td>
								<td class="td1" style="width: 5%; padding-top: 55px;">${cvo.pCnt}</td>
								<td class="td1" style="width: 10%; padding-top: 55px;">
									<fmt:parseNumber var ="point" value="${(cvo.pPrice-pvo.pSale)*0.01}" integerOnly="true"/>
									<c:set var="totPoint" value="${totPoint + point}"/>
									<img src="${ctp}/resources/images/point.gif"/>${point}원
								</td>
								<td class="td1" style="width: 10%; padding-top: 55px;">${pvo.pSale}</td>
								<c:set var="pSale" value="${pSale + pvo.pSale }"/>
								<c:choose>
									<c:when test="${pvo.pSale>0}">
										<c:set var="salePrice" value="${cvo.pPrice-(pvo.pSale*cvo.pCnt)}" />
										<td class="td1" style="width: 10%; padding-top: 55px;"><span style="font-size:11px;color:#000000;">&nbsp;${salePrice}원</span></td>
									</c:when>
									<c:otherwise>
										<c:set var="salePrice" value="${cvo.pPrice}" />
										<td class="td1" style="width: 10%; padding-top: 55px;"><span style="font-size:11px;color:#000000;">${salePrice}원</span></td>
									</c:otherwise>
								</c:choose>
								<c:set var="totPrice" value="${totPrice + salePrice}"/>
							</c:if>
						</c:forEach>
					</tr>
					<c:set var="curScrStartNo" value="${curScrStartNo-1}" />
				</c:forEach>
				<c:if test="${totPrice>60000}"><c:set var="Delivery" value="0"/></c:if>
				<c:set var="orderPrice" value="${totPrice + Delivery}"/>
					<tr>
						<td style="text-align: right;" colspan="7">
							<span style="font-size: 12px;">상품구매금액 ${totPrice} + 배송비 ${Delivery} <c:if test="${Delivery eq 0}">(무료)</c:if> = 합계 : </span><strong><font color="#f76560" size="4px">${orderPrice}</font><font color="#f76560" size="4px">원</font></strong>
						</td>
					</tr>
				</tbody>
			</table>
			
			
			
			<table class="table table-borderless" style="width: 100%; margin: 0px; padding: 0px;">
				<tr>
					<td style="text-align: left; padding-top: 25px;"><span class="warn"><img src="${ctp}/resources/images/warn.gif"/>상품의 옵션 및 수량 변경은 상품상세 또는 장바구니에서 가능합니다.</span></td>
					<td style="text-align: right;">
						<div class=boardbtn>
							<a href="${ctp}/">쇼핑계속하기</a>
						</div>
					</td>
				</tr>
			</table>
			<br/><br/><br/>
			<hr/>
			<h6>배송정보</h6>
			<br/><br/><br/>
			<table>
				<tr>
					<td class="ftd">받으시는 분<img src="https://img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수"></td>
					<td colspan="3"><input type="text" name="oName" id="oName" maxlength="30" required value="${mvo.mName}"></td>
				</tr>
				<tr>
					<td class="ftd">주소<img src="https://img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수"></td>
					<td colspan="2">
						<input type="text" id="sample6_postcode" name="oPost" placeholder="우편번호" readonly="readonly" required maxlength="14" value="${mvo.mPost}">
					</td>
					<td class="btninput">
						<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><input type="text" name="add1" id="sample6_address" placeholder="주소" readonly="readonly" value="${add1}"><br></td>
				</tr>
				<tr>
					<td></td>
					<td class="adtd" colspan="2"><input type="text" id="sample6_extraAddress" name="add2" placeholder="기본주소" readonly="readonly" value="${add2}"></td>
					<td><input type="text" id="sample6_detailAddress" name="add3" placeholder="상세주소" value="${add3}"></td>
				</tr>
				<tr>
					<td class="ftd">휴대전화<img src="https://img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수"></td>
					<td class="tel1">
						<select name="tel1">
					    	<option value="010" <c:if test="${phone1 eq '010'}">selected</c:if>>010</option>
							<option value="011" <c:if test="${phone1 eq '011'}">selected</c:if>>011</option>
							<option value="016" <c:if test="${phone1 eq '016'}">selected</c:if>>016</option>
							<option value="017" <c:if test="${phone1 eq '017'}">selected</c:if>>017</option>
							<option value="018" <c:if test="${phone1 eq '018'}">selected</c:if>>018</option>
							<option value="019" <c:if test="${phone1 eq '019'}">selected</c:if>>019</option>
					    </select>
					</td>
					<td class="tel2"><input type="text" name="tel2" maxlength="4" required value="${phone2}"></td>
					<td class="tel3"><input type="text" name="tel3" maxlength="4" required value="${phone3}"></td>
				</tr>
				<tr>
					<td class="ftd">이메일<img src="https://img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수"></td>
					<td colspan="3"><input type="email" name="oEmail" id="oEmail" value="${mvo.mEmail}" required></td>
				</tr>
				<tr class="">
					<td>배송메시지</td>
                    <td colspan="3">
                   		<textarea id="oMessage" name="oMessage" maxlength="255" cols="70" style="margin: 0px; height: 60px;"></textarea>
      		        </td>
                </tr>
			</table>
			<hr/>
			<h6>결제정보</h6>
			<br/><br/><br/><br/><br/>
			<div class="totalsummary">
				<table border="1" >
					<colgroup>
						<col style="width:23%;">
						<col style="width:24%;">
						<col style="width:23%;" class="displaynone">
						<col style="width:auto;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><span>총 상품금액</span></th>
		                  	    <th scope="col">총 배송비</th>
		                    <th scope="col">총 할인율(원)</th>
		                    <th scope="col">결제예정금액</th>
					   	 </tr>
		               </thead>
		               <tbody>
		                <tr> 
							<td class="price"><div class="box"><strong>${totPrice + pSale}</strong>원 <span class="tail displaynone"></span></div></td>
							<td class="option"><div class="box"><strong>+</strong>
							<strong>${Delivery}</strong>원 <span class="tail displaynone"></span></div></td>
							<td class="discount displaynone"><strong>-</strong><strong><input id="pSale" name="pSale" type="text" value="${pSale}" readonly style="border: 0px; margin: 0; padding: 0; width: 17%; font-weight: bold;"></strong>원</td>
							<td class="total"><div class="box"><strong>=</strong><strong><input id="oPrice" name="oPrice" type="text" value="${orderPrice}" readonly style="border: 0px; margin: 0; padding: 0; width: 20%; font-weight: bold;"></strong>원 <span class="tail displaynone"></span></div></td>
		                </tr>
		               </tbody>
				</table>
			</div>
			<table border="1" class="order">
	            <colgroup>
					<col style="width:139px">
					<col style="width:auto">
				</colgroup>
				<tbody>
				<tr class="sum txt14">
					<th scope="row"><strong>총 할인결제금액</strong></th>
	                <td><strong id="total_addpay_price_view">0</strong>원</td>
	            </tr>
	            </tbody>
				<!-- 적립금 -->
				<tbody class="">
					<tr class="txt15">
						<th scope="row">적립금</th>
				        <td><p><input id="mile" name="mile" size="10" value="" type="text" style="width: 190px;"> 원 (총 사용가능 적립금 : <strong class="txtWarn" style="color: #f76560;">${mvo.mPoint}</strong>원)</p>
						</td>
		            </tr>
					<tr class="txt15">
						<th scope="row">결제수단</th>
				        <td>
				        	<input type=radio name="oPayment" value="무통장" checked="checked">무통장&nbsp;&nbsp;&nbsp; 
							<input type=radio name="oPayment" value="카드">카드
						</td>
						<td style="text-align: right;">
							<div class=boardbtn>
								<a href="#" onclick="orderCheck()" style="color: #fff; background-color: black;" >결제하기</a>
							</div>
						</td>
		            </tr>
		        </tbody>
			</table>
			<c:set var="milecheck" value="${orderPrice}"/>
			<c:set var="salecheck" value="${pSale}"/>
			<input type="hidden" id="salecheck" value="${salecheck}"/>
			<input type="hidden" id="oPricemilecheck" value="${milecheck}"/>
			<input type="hidden" id="delItems" name="delItems" value="${delItems}"/>
			<input type="hidden" name="point" value="${totPoint}"/>
			<input type="hidden" name="mId" value="${smid}"/>
			<input type="hidden" name="oDelivery" value="${Delivery}"/>
		</form>
	</div>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>