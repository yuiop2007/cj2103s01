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
          <label for="mid">아이디 : &nbsp; &nbsp;</label>${vo.mid}
        </div>
      </td>
    </tr>
    <tr>
      <td>
		    <div class="form-group">
		      <label for="nickname">닉네임 : &nbsp; &nbsp;</label>${vo.nickName}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="name">성명 :</label> ${vo.name}
		    </div>
	    </td>
	  </tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="email">Email address:</label> ${vo.email}
			  </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      성별 : &nbsp; &nbsp;${vo.gender}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="birthday">생일 :</label> ${vo.birthday}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      전화번호 : &nbsp;&nbsp;${vo.tel}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="address">주소 :</label> ${vo.address}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
			    <label for="homepage">Homepage address:</label> ${vo.homepage}
			  </div>
			</td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="name">직업 :</label> ${vo.job}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      취미 : &nbsp; &nbsp;${vo.hobby}
		    </div>
			</td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      정보공개 : &nbsp; &nbsp;${vo.userInfor} 
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <img src="${ctp}/resources/member/${vo.photo}" width="300px"/> 
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