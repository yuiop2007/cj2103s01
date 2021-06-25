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
	$(document).ready(function(){
    	$("#pSize").prop("disabled", true)
	    	$("#pColor").on("change", function(){
					$("#pSize").removeAttr("disabled").on("change", function(){
						var color = $("#pColor").val();
						var size = $("#pSize").val();
						var content = "<thead><tr><th scope='col'></th><th scope='col'></th><th scope='col'></th></tr></thead><tbody class='option_products'><tr class='option_product '><td><p class='product'><span>"+color+"/"+size+"</span></p></td><td><span class='quantity' style='width: 65px;'><input type='number' min='1' max='10' style='padding: 1px; border: 0px; width: 30px;' type='text' name='quantity_opt[]' class='pbtn' value='1'><a href='#none' class='up eProductQuantityUpClass' '='' data-target='option_box1_up'></span><a href='#none' class='delete'><img src='//img.echosting.cafe24.com/design/skin/default/product/btn_price_delete.gif' alt='삭제' id='option_box1_del' class='option_box_del'></a></td><td class='right'><span id='option_box1_price'><input type='hidden' class='option_box_price' value='39000' product-no='153' item_code='P00000FX000B'><span class='ec-front-product-item-price' code='P00000FX000B' product-no='153'>39,000원</span></span></td></tr></tbody><tbody class='add_products'></tbody><tfoot><tr><td colspan='3'><strong>TOTAL </strong><span class='total'><strong><em>39,000원</em></strong> (1개)</span></td></tr></tfoot>";

						$("#pOpt").append(content);

					});
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
											<option value="" selected="">- [필수] 옵션을 선택해 주세요 -</option>
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
								<c:if test="">
								<thead>
									<tr>
										<th scope="col"></th>
										<th scope="col"></th>
										<th scope="col"></th>
									</tr>
								</thead>
									<tbody class="option_products">
										<tr class="option_product ">
											<td>
												<p class="product">
													<span></span>
												</p>
											</td>
											<td><span class="quantity" style="width: 65px;"><input style="padding: 1px; border: 0px; width: 20px;" type="text" name="quantity_opt[]" class="pbtn" value="1"><a href="#none" class="up eProductQuantityUpClass" "="" data-target="option_box1_up"></span><a href="#none" class="delete"><img src="//img.echosting.cafe24.com/design/skin/default/product/btn_price_delete.gif" alt="삭제" id="option_box1_del" class="option_box_del"></a></td>
											<td class="right"><span id="option_box1_price"><input type="hidden" class="option_box_price" value="39000" product-no="153" item_code="P00000FX000B"><span class="ec-front-product-item-price" code="P00000FX000B" product-no="153">39,000원</span></span></td>
										</tr>
									</tbody>
									<tbody class="add_products"></tbody>
									<tfoot>
										<tr>
											<td colspan="3"><strong>TOTAL </strong><span class="total"><strong><em>39,000원</em></strong> (1개)</span></td>
										</tr>
									</tfoot>
								</c:if>
							</table>
						</div>
						<div class="productAct">
							<div class="btnArea">
			                    <a href="#" class="first " onclick="product_submit(1, '/exec/front/order/basket/', this)" onmousedown="eval('try{ _trk_clickTrace( \'SCI\', _TRK_PN ); }catch(_e){ }');">buy now</a>
			                    <a href="#" class="" onclick="" onmousedown="eval('try{ _trk_clickTrace( \'SCI\', _TRK_PN ); }catch(_e){ }');">add  to cart</a>
			                    <a href="#" onclick="" class="">wishlist</a>
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
					
					</div>
					<div class="proQnA">
					
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