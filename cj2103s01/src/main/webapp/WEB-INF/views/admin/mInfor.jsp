<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>aMInfor.jsp</title>
  <%@ include file="/WEB-INF/views/include/bs.jsp" %>
  <script>
  </script>
</head>
<body>
<div class="container">
  <p><br/></p>
  <table class="table table-bordered">
    <tr>
      <td><h2>회 원 정 보</h2></td>
    </tr>
    <tr>
      <td>
        <div class="form-group">
          <label for="mid">아이디 : &nbsp; &nbsp;</label>${vo.mId}
        </div>
      </td>
    </tr>

		<tr>
		  <td>
		    <div class="form-group">
		      <label for="name">성명 :</label> ${vo.mName}
		    </div>
	    </td>
	  </tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="email">이메일 :</label> ${vo.mEmail}
			  </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      포인트 : &nbsp; &nbsp;${vo.mPoint}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      성별 : &nbsp; &nbsp;${vo.mGender}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="birthday">생일 :</label> ${vo.mBirth} (${vo.mSolar})
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      전화번호 : &nbsp;&nbsp;${vo.mPhone}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="address">우편번호 :</label> ${vo.mPost}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="address">주소 :</label> ${vo.mAddress}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      구매 횟수 : &nbsp; &nbsp;${vo.mBuy} 
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      가입 날짜 : &nbsp; &nbsp;${vo.mRdate} 
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      탈퇴 여부 : &nbsp; &nbsp;${vo.mDrop} 
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
    		<button type="button" class="btn btn-secondary btn-sm" onclick="window.close()">창닫기</button>
      </td>
    </tr>
  </table>
  <p><br/></p>
</div>
</body>
</html>