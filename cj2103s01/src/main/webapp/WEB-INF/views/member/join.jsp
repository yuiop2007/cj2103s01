<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MINIM</title>
<link rel="stylesheet" type="text/css" href="resources/css/css.css">
<jsp:include page="/WEB-INF/views/include/bs.jsp"/>
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
    
    function JoinAction(){
    	
    	joinform.submit();
    }
</script>
</head>
<body>
	<div class="jumbotron text-center">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
	</div>
	<div class="container">
		<form name="joinform" method="post" action="${ctp}/join">
			<h6>기본정보</h6>
			<hr />
			<table>
				<tr>
					<td class="ftd">아이디</td>
					<td colspan="3"><input type="text" name="mId" maxlength="16" required placeholder="(영문소문자/숫자, 4~16자)"></td>
				</tr>
				<tr>
					<td class="ftd">비밀번호</td>
					<td colspan="3"><input type="password" name="mPwd" autocomplete="off" maxlength="16" required placeholder="(영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 10자~16자)"></td>
				</tr>
				<tr>
					<td class="ftd">비밀번호 확인</td>
					<td colspan="3"><input type="password" name="mPwdCheck" autocomplete="off" maxlength="16" required></td>
				</tr>
				<tr>
					<td class="ftd">이름</td>
					<td colspan="3"><input type="text" name="mName" maxlength="30" required></td>
				</tr>
				<tr>
					<td class="ftd">주소</td>
					<td>
						<input type="text" id="sample6_postcode" name="mPost" placeholder="우편번호" readonly="readonly" maxlength="14">
					</td>
					<td>
						<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><input type="text" name="add1" id="sample6_address" placeholder="주소" readonly="readonly"><br></td>
				</tr>
				<tr>
					<td></td>
					<td class="adtd" colspan="2"><input type="text" id="sample6_extraAddress" name="add2" placeholder="기본주소" readonly="readonly"></td>
					<td><input type="text" id="sample6_detailAddress" name="add3" placeholder="상세주소"></td>
				</tr>
				<tr>
					<td class="ftd">휴대전화</td>
					<td class="tel1">
						<select name="tel1">
					    	<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
					    </select>
					</td>
					<td class="tel2"><input type="text" name="tel2" maxlength="4" required></td>
					<td class="tel3"><input type="text" name="tel3" maxlength="4" required></td>
				</tr>
				<tr>
					<td class="ftd">이메일</td>
					<td colspan="10"><input type="email" name="mEmail"></td>
				</tr>
			</table>
			<hr />
			<h6>추가정보</h6>
			<table>
				<tr>
					<td class="ftd">성별</td>
					<td class="radiotd" colspan="3">
						<input type=radio name="mGender" value="M" checked="checked">남자&nbsp;&nbsp;&nbsp; 
						<input type=radio name="mGender" value="F">여자
					</td>
				</tr>
				<tr>
					<td class="ftd">생년월일</td>
					<td class="birthtd"><input type="text" name="birth_year" maxlength="4"></td>
					<td>년</td>
					<td class="birthtd"><input type="text" name="birth_month" maxlength="2"></td>
					<td>월</td>
					<td class="birthtd"><input type="text" name="birth_day" maxlength="2"></td>
					<td>일&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td class="radiotd">
						<input type=radio name="is_solar_calendar" value="T" checked="checked">양력&nbsp;&nbsp;&nbsp; 
						<input type=radio name="is_solar_calendar" value="F">음력
					</td>
				</tr>
				<tr>
					<td class="ftd">추천인 아이디</td>
					<td colspan="10"><input type="text" name="mCmid"></td>
				</tr>
			</table>
			<div class="jumbotron text-center">
				<button type="button" class="btn btn-outline-dark">회원가입취소</button>
				<button type="button" class="btn btn-dark" onclick="JoinAction()">회원가입</button>
			</div>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>