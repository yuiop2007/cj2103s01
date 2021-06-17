<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>adminLogin.jsp(관리자 인증창)</title>
  <%@ include file="/WEB-INF/views/include/bs.jsp" %>
  <script>
    function fCheck() {
    	// 인증폼 체크하기.....
    	
    	myform.submit();
    }
  </script>
  <style>
    th, td {
      border: 1px solid gray;
      padding: 35px;
    }
    th {
      background-color: #ccc;
      text-align: center;
    }
  </style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/slide.jsp" %>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<div class="container">
  <p><br/></p>
  <div style="text-align:center;">
    <h2>관 리 자 로 그 인</h2>
  </div>
  <br/>
  <form name="myform" method="post">
  	<table class="table table-borderless">
  	  <tr>
  	    <th>관리자 아이디</th>
  	    <td><input type="text" name="mid" autofocus/></td>
  	  </tr>
  	  <tr>
  	    <th>관리자 비밀번호</th>
  	    <td><input type="password" name="pwd"/></td>
  	  </tr>
  	  <tr>
  	    <td colspan="2" style="text-align:center">
  	      <input type="button" value="관리자 로그인" onclick="fCheck()"/>
  	      <input type="reset" value="다시입력"/>
  	      <input type="button" value="돌아가기" onclick="location.href='${ctp}/';"/>
  	    </td>
  	  </tr>
  	</table>
  </form>
  <p><br/></p>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>