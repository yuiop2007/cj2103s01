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
						<img src="${ctp}/resources/pMainImages/${vo.pImage}"/>
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
									<th scope="row"><span style="font-size:12px;color:#333333;">${vo.pInfo}</span></th>
								</tr>
								</tbody>
							</table>
						</div>
						<table border="1" summary>
							<tbody class="infotable">
								<tr>
									<th scope="row">COLOR</th>
									<td>
										<select option_product_no="153" option_select_element="ec-option-select-finder" option_sort_no="1" option_type="T" item_listing_type="S" option_title="COLOR" product_type="product_option" product_option_area="product_option_153_0" name="option1" id="product_option_id1" class="ProductOption0" option_style="select" required="true">
											<option value="*" selected="" link_image="">- [필수] 옵션을 선택해 주세요 -</option>
											<option value="**" disabled="" link_image="">-------------------</option>
											<c:forEach var="vo" items="${vos}">
												<option value="${vo.pColor}" link_image="">${vo.pColor}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th scope="row">SIZE</th>
									<td>
										<select option_product_no="153" option_select_element="ec-option-select-finder" option_sort_no="1" option_type="T" item_listing_type="S" option_title="COLOR" product_type="product_option" product_option_area="product_option_153_0" name="option1" id="product_option_id1" class="ProductOption0" option_style="select" required="true">
											<option value="*" selected="" link_image="">- [필수] 옵션을 선택해 주세요 -</option>
											<option value="**" disabled="" link_image="">-------------------</option>
											<c:forEach var="vo" items="${vos}">
												<option value="${vo.pSize}" link_image="">${vo.pSize}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</tbody>
						</table>
						<div id="totalProducts" class="">
	
							<table border="1" summary="">
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
										<tr class="option_product " data-option-index="1" target-key="153">
											<td>
												<p class="product">
													화이트 와이드 데님 팬츠<br> - <span>화이트/M</span>
												</p>
											</td>
											<td><span class="quantity" style="width: 65px;"><input style="padding: 1px; border: 0px; width: 20px;" type="text" name="quantity_opt[]" class="pbtn" value="1" product-no="153"><a href="#none" class="up eProductQuantityUpClass" "="" data-target="option_box1_up"><img src="//img.echosting.cafe24.com/design/skin/default/product/btn_count_up.gif" id="option_box1_up" class="option_box_up" alt="수량증가"></a><a href="#none" class="down eProductQuantityDownClass" data-target="option_box1_down"><img src="//img.echosting.cafe24.com/design/skin/default/product/btn_count_down.gif" id="option_box1_down" class="option_box_down" alt="수량감소"></a></span><a href="#none" class="delete"><img src="//img.echosting.cafe24.com/design/skin/default/product/btn_price_delete.gif" alt="삭제" id="option_box1_del" class="option_box_del"></a></td>
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