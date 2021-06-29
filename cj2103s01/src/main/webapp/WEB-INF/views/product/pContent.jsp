<%@page import="com.spring.cj2103s01.vo.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINIM</title>
<jsp:include page="/WEB-INF/views/include/bs.jsp"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctp}/resources/css/css.css">
<style>
.infotable select{
	padding: 3px;
}

.proReview h3, .proQnA h3 {
    margin: 70px auto 0px;
    padding: 10px 30px 10px 30px;
    width: 130px;
    text-align: center;
    border: 1px solid #999;
    color: #444;
    font-size: 11px;
    font-family: 'Lato';
    font-weight: 600;
    text-transform: uppercase;
    letter-spacing: 1px;
}
.proReview, .proQnA{
	padding: 0 30px 0 30px;
}
#totalPrice { display: none; }
</style>
<script>
	//게시글 수정처리를 위한 비밀번호 체크
	function updCheck() {
		contentform.submit();
	}
	
	// 게시글 삭제처리
	function delCheck() {
		var ans = confirm("게시글을 삭제하시겠습니까?");
		
		if(ans) {
			$.ajax({
				type : "post",
				url  : "${ctp}/product/pDelete?pag=${pag}&pageSize=${pageSize}",
				success: function(data) {
					alert("삭제처리 되었습니다.");
					location.href="${ctp}/product/pList?pag=${pag}&pageSize=${pageSize}";
				}
			});
		}
	}
	var colsiz = new Array();
	var color = "";
	var size = "";
	var content = "";
			
	function appendChoiceDiv(colsiz){  
        			
				content += "<tr id='tr_"+colsiz.goodsId+"'>";
				content += "<td>";
				content += "<p class='product'><span>"+colsiz.goodsId+"</span></p>";
				content += "</td>";
				content += "<td>";
				content += "<span class='quantity' style='width: 65px;'>";
				content += "<input type='text' id='ordernum' style='padding: 1px; border: 0px; width: 30px;' name='' class='pbtn' value='1'>";
				content += "<button type='button' class='add' onclick='goods.minus(\'"+colsiz.goodsId+"\')'>-</button>";
				content += "<button type='button' class='add' onclick='goods.plus(\'"+colsiz.goodsId+"\')'>+</button>";
				content += "<a href='#' class='delete' onclick='goods.deselect(\'"+colsiz.goodsId+"\')'><img src='//img.echosting.cafe24.com/design/skin/default/product/btn_price_delete.gif' alt='삭제' id='option_box1_del' class='option_box_del'></a>";
				content += "</td>";
				content += "<td class='right'>";
				content += "<span id='option_box1_price'>";
				content += "<input type='hidden' class='option_box_price' value='"+colsiz.goodsPrice+"'>";
				content += "<span class='ec-front-product-item-price'>"+colsiz.goodsPrice+"원</span>";
				content += "</span>";
				content += "</td>";
				content += "</tr>";
				$("#orderbody").append(content);
				color = "";
				size = "";
				$("#pColor").val("*").prop("selected", true);
				$("#pSize").val("*").prop("selected", true);
				$("#pSize").prop("disabled", true);
			
		}


			
	$(document).ready(function(){
	   	
    	$("#pSize").prop("disabled", true)
    	$("#pColor").on("change", function(){
				$("#pSize").removeAttr("disabled")
				color = $("#pColor").val();
				
			});

			$("#pSize").on("change", function(){
				size = $("#pSize").val();
				
				if(colsiz.length==0){
					colsiz.push({goodsId:color + "/" + size, goodsPrice:'${vo.pPrice}' ,cnt:1});
					appendChoiceDiv(colsiz[0]);
				}
				else if(colsiz.length!=0){
					for(var i=0; i<colsiz.length; i++){
						var sw = 0;
						if(colsiz[i].goodsId==(color+"/"+size)){
							sw = 1;
							alert("이미 추가한 상품입니다.");
							color = "";
							size = "";
							$("#pColor").val("*").prop("selected", true);
							$("#pSize").val("*").prop("selected", true);
							$("#pSize").prop("disabled", true);
						}
					}
					if(sw==0){
						colsiz.push({goodsId:color + "/" + size, goodsPrice:'${vo.pPrice}' ,cnt:1});
						appendChoiceDiv(colsiz[colsiz.length-1]);
					}
				}
			});

	});

	
  

		
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
	</div>
	<div class="container" style="padding: 0%;">
	<form name="contentform" method="post">
		<div class="sub">
			<div class = "detail">
				<div class="detailArea">
				<div class=boardbtn style="margin-left: 40px;">
					<c:if test="${slevel==0}">
					<a href="#" onclick="location.href='${ctp}/product/pList?pag=${pag}&pageSize=${pageSize}';">목록</a>
					<a href="#" onclick="updCheck()">수정</a>
					<a href="#" onclick="delCheck()">삭제</a>
					  <input type="hidden" name="pag" value="${pag}"/>
					  <input type="hidden" name="pageSize" value="${pageSize}"/>
					  <input type="hidden" name="pId" value="${vo.pId}"/>
					</c:if>
				</div>
					<div class="imgArea">
						<img src="${ctp}/resources/pMainImages/${vo.pImage}" style="width: 500px;"/>
					</div>
					<div class="infoArea">
						<div class="xans-element- xans-product xans-product-detaildesign">
							<table border="1" summary="">
								<tbody>
								<tr class=" xans-record-">
									<th scope="row"><span style="font-size:16px;color:#000000;font-weight:bold;">${vo.pName}</span></th>
								</tr>
								<tr class=" xans-record-">
									<th scope="row"><span style="font-size:13px;color:#000000;font-weight:bold;">${vo.pPrice}</span></th>
								</tr>
								<tr class=" xans-record-">
									<th scope="row"><span style="font-size:13px;color:#333333;"><pre>${vo.pInfo}</pre></span></th>
								</tr>
								</tbody>
							</table>
						</div>
						<table border="1" summary>
							<tbody class="infotable">
								<tr>
									<th scope="row">COLOR</th>
									<td>
										<select name="pColor" id="pColor" class="ProductOption0" option_style="select" required="true">
											<option value="*" selected="">- [필수] 옵션을 선택해 주세요 -</option>
											<option value="**" disabled="" link_image="">-------------------</option>
												<c:if test="${fn:contains(vo.pColor,'블랙')}"><option value="블랙" link_image="">블랙</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'화이트')}"><option value="화이트" link_image="">화이트</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'차콜')}"><option value="차콜" link_image="">차콜</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'그레이')}"><option value="그레이" link_image="">그레이</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'아이보리')}"><option value="아이보리" link_image="">아이보리</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'베이지')}"><option value="베이지" link_image="">베이지</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'블루')}"><option value="블루" link_image="">블루</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'스카이블루')}"><option value="스카이블루" link_image="">스카이블루</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'핑크')}"><option value="핑크" link_image="">핑크</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'그린')}"><option value="그린" link_image="">그린</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'레드')}"><option value="레드" link_image="">레드</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'옐로우')}"><option value="옐로우" link_image="">옐로우</option></c:if>
												<c:if test="${fn:contains(vo.pColor,'오렌지')}"><option value="오렌지" link_image="">오렌지</option></c:if>
										</select>
									</td>
								</tr>
								<tr>
									<th scope="row">SIZE</th>
									<td>
										<select name="pSize" id="pSize" class="ProductOption0" option_style="select" required="true">
											<option value="*" selected="" link_image="">- [필수] 옵션을 선택해 주세요 -</option>
											<option value="**" disabled="" link_image="">-------------------</option>
												<c:if test="${fn:contains(vo.pSize,'XS')}"><option value="XS" link_image="">XS</option></c:if>
												<c:if test="${fn:contains(vo.pSize,'S')}"><option value="S" link_image="">S</option></c:if>
												<c:if test="${fn:contains(vo.pSize,'M')}"><option value="M" link_image="">M</option></c:if>
												<c:if test="${fn:contains(vo.pSize,'L')}"><option value="L" link_image="">L</option></c:if>
												<c:if test="${fn:contains(vo.pSize,'XL')}"><option value="XL" link_image="">XL</option></c:if>
										</select>
									</td>
								</tr>
							</tbody>
						</table>
						<div id="totalProducts" class="">
	
							<table id="pOpt" border="1" summary="">
								<colgroup>
									<col style="width: 284px;">
									<col style="width: 80px;">
									<col style="width: 110px;">
								</colgroup>
								<thead>
									<tr>
										<th scope='col'></th>
										<th scope='col'></th>
										<th scope='col'></th>
									</tr>
								</thead>
								<tbody id="orderbody">
									<!-- <tr class='option_product'>
										<td>
											<p class='product'><span>컬러/사이즈</span></p>
										</td>
										<td>
											<span class='quantity' style='width: 65px;'>
												<input type='number' id='ordernum' min='1' max='10' style='padding: 1px; border: 0px; width: 30px;' name='orderitem[]' class='pbtn' value='1'>
												</span><a href='#none' class='delete'><img src='//img.echosting.cafe24.com/design/skin/default/product/btn_price_delete.gif' alt='삭제' id='option_box1_del' class='option_box_del'></a>
										</td>
										<td class='right'>
											<span id='option_box1_price'>
												<input type='hidden' class='option_box_price' value='"+price+"'>
												<span class='ec-front-product-item-price'>가격원</span>
											</span>
										</td>
									</tr> -->

								</tbody>
								<tfoot id="totalPrice"><tr><td colspan='3'><strong>TOTAL </strong><span class='total'><strong><em>${vo.pPrice}원</em></strong> (1개)</span></td></tr></tfoot>
							</table>
						</div>
						<div class="productAct">
							<div class="btnArea">
			                    <a href="#" class="first " onclick="">buy now</a>
			                    <a href="#" onclick="">add  to cart</a>
			                    <a href="#" onclick="">wishlist</a>
			                </div>
			                
						</div>
						
					</div>
				</div>
				<div class="additional">
					<div class="proDetail">
						${fn:replace(vo.pContent,newLine,"<br/>")}
					</div>
					<div class="proInfo">
					
					</div>
					<div class="proReview">
						<h3>review </h3>
						<form name="pageForm">
							<table class="table table-borderless">
								<thead>
									<tr>
										<th scope="col" style="width: 5%;">NO</th>
										<th scope="col" style="width: 10%; text-align: left;">평점</th>
										<th scope="col" style="width: 50%; text-align: left;">제목</th>
										<th scope="col" style="width: 10%;">작성자</th>
										<th scope="col" style="width: 10%;">
												<a href="${ctp}/board/rInput?pId=${vo.pId}">리뷰쓰기</a>
										</th>
									</tr>
								</thead>
								<tbody>
								<c:set var="curScrStartNo" value="${rpageVO.curScrStartNo}" />
								<c:forEach var="vo" items="${rvos}">
									<tr>
										<td class="td1" style="width: 5%;">${curScrStartNo}</td>
										<td class="td2" style="width: 10%;">
										<c:if test="${vo.rRating eq 1}">⭐</c:if>
										<c:if test="${vo.rRating eq 2}">⭐⭐</c:if>
										<c:if test="${vo.rRating eq 3}">⭐⭐⭐</c:if>
										<c:if test="${vo.rRating eq 4}">⭐⭐⭐⭐</c:if>
										<c:if test="${vo.rRating eq 5}">⭐⭐⭐⭐⭐</c:if>
										</td>
										<td class="td3" style="width: 50%; text-align: left;"><a href="${ctp}/board/rContent?rId=${vo.rId}&pId=${param.pId}&pag=${pag}&pageSize=${pageSize}">${vo.rTitle}</a></td>
										<td class="td3" style="width: 10%;">${vo.rName}</td>
										<td class="td4" style="width: 10%;">
											<c:if test="${vo.diffTime <= 24}">${fn:substring(vo.rRdate,11,19)}</c:if>
						        			<c:if test="${vo.diffTime > 24}">${fn:substring(vo.rRdate,0,10)}</c:if>
										</td>
									</tr>
									<c:set var="curScrStartNo" value="${curScrStartNo-1}" />
								</c:forEach>
								</tbody>
							</table>
						</form>
						<!-- 블록페이징처리 시작 -->
						<div class="container" style="text-align:center;">
						  <ul class="pagination justify-content-center">
							  <c:set var="startPageNum" value="${rpageVO.pag - (rpageVO.pag-1)%rpageVO.blockSize}" />  <!-- 해당블록의 시작페이지 구하기 -->
							  <c:if test="${rpageVO.pag != 1}">
							    <li class="page-item"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=1&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pag}" class="page-link" style="color:#666">◁◁</a></li>
							    <li class="page-item"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=${rpageVO.pag-1}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pag}" class="page-link" style="color:#666">◀</a></li>
							  </c:if>
							  <c:forEach var="i" begin="0" end="${rpageVO.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
							    <c:if test="${(startPageNum+i) <= rpageVO.totPage}">
								  	<c:if test="${rpageVO.pag == (startPageNum+i)}">
								  	  <li class="page-item active"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=${startPageNum+i}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pag}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
								  	</c:if>
								  	<c:if test="${rpageVO.pag != (startPageNum+i)}">
								  	  <li class="page-item"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=${startPageNum+i}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pag}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
								  	</c:if>
							  	</c:if>
							  </c:forEach>
							  <c:if test="${rpageVO.pag != rpageVO.totPage}">
							    <li class="page-item"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=${rpageVO.pag+1}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pag}" class="page-link" style="color:#666">▶</a></li>
							    <li class="page-item"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=${rpageVO.totPage}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag}&qpageSize=${qpageVO.pag}" class="page-link" style="color:#666">▷▷</a></li>
							  </c:if>
						  </ul>
						</div>
						<!-- 블록페이징처리 끝 -->
					</div>
					<div class="proQnA">
						<h3>Q &amp; A </h3>
						<form name="pageForm">
							<table class="table table-borderless">
								<thead>
									<tr>
										<th scope="col" style="width: 5%;">NO</th>
										<th scope="col" style="width: 10%; text-align: left;">카테고리</th>
										<th scope="col" style="width: 52%; text-align: left;">제목</th>
										<th scope="col" style="width: 10%;">작성자</th>
										<th scope="col" style="width: 10%;">
												<a href="${ctp}/board/qInput?pId=${vo.pId}">QnA쓰기</a>
										</th>
									</tr>
								</thead>
								<tbody>
								<c:set var="curScrStartNo" value="${qpageVO.curScrStartNo}" />
								<c:forEach var="vo" items="${qvos}">
									<tr>
										<td class="td1" style="width: 5%;">${curScrStartNo}</td>
										<td class="td2" style="width: 10%;">${vo.qCate }</td>
										<td class="td3" style="width: 52%; text-align: left;">
										<c:if test="${vo.qSecret eq 0}">
											<a href="${ctp}/board/qContent?qId=${vo.qId}&pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}">${vo.qTitle}</a>
										</c:if>
										<c:if test="${vo.qSecret eq 1}">
											<c:choose>
								                <c:when test="${smid eq vo.qWriter || slevel eq 0}">
								                    <a href="${ctp}/board/qContent?qId=${vo.qId}&pId=${vo.pId}&pag=${pageVO.pag}&pageSize=${pageVO.pageSize}"><img src="${ctp}/images/lock.gif"/>${vo.qTitle}</a>
								                </c:when>
								                <c:otherwise><img src="${ctp}/images/lock.gif"/>${vo.qTitle}</c:otherwise>
								            </c:choose>
										</c:if>
										</td>
										<td class="td3" style="width: 10%;">${vo.qName}</td>
										<td class="td4" style="width: 10%;">
											<c:if test="${vo.diffTime <= 24}">${fn:substring(vo.qRdate,11,19)}</c:if>
						        			<c:if test="${vo.diffTime > 24}">${fn:substring(vo.qRdate,0,10)}</c:if>
										</td>
									</tr>
									<c:set var="curScrStartNo" value="${curScrStartNo-1}" />
								</c:forEach>
								</tbody>
							</table>
						</form>
						<!-- 블록페이징처리 시작 -->
						<div class="container" style="text-align:center;">
						  <ul class="pagination justify-content-center">
							  <c:set var="startPageNum" value="${qpageVO.pag - (qpageVO.pag-1)%qpageVO.blockSize}" />  <!-- 해당블록의 시작페이지 구하기 -->
							  <c:if test="${qpageVO.pag != 1}">
							    <li class="page-item"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=1&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">◁◁</a></li>
							    <li class="page-item"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag-1}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">◀</a></li>
							  </c:if>
							  <c:forEach var="i" begin="0" end="${qpageVO.blockSize-1}"> <!-- 블록의 크기만큼 돌려준다. -->
							    <c:if test="${(startPageNum+i) <= qpageVO.totPage}">
								  	<c:if test="${qpageVO.pag == (startPageNum+i)}">
								  	  <li class="page-item active"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=${startPageNum+i}&qpageSize=${qpageVO.pageSize}" class="page-link btn btn-secondary active" style="color:#666"><font color="#fff">${startPageNum+i}</font></a></li>
								  	</c:if>
								  	<c:if test="${qpageVO.pag != (startPageNum+i)}">
								  	  <li class="page-item"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=${startPageNum+i}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">${startPageNum+i}</a></li>
								  	</c:if>
							  	</c:if>
							  </c:forEach>
							  <c:if test="${qpageVO.pag != qpageVO.totPage}">
							    <li class="page-item"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.pag+1}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">▶</a></li>
							    <li class="page-item"><a href="${ctp}/product/pContent?pId=${vo.pId}&pag=${pag}&pageSize=${pageSize}&rpag=${rpageVO.pag}&rpageSize=${rpageVO.pageSize}&qpag=${qpageVO.totPage}&qpageSize=${qpageVO.pageSize}" class="page-link" style="color:#666">▷▷</a></li>
							  </c:if>
						  </ul>
						</div>
						<!-- 블록페이징처리 끝 -->
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
 	<div class="jumbotron">
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>