<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>bInput.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs.jsp"/>
  <jsp:include page="/WEB-INF/views/include/slide.jsp"/>
  <script src="${ctp}/resources/ckeditor/ckeditor.js"></script>
  <style>
    th {
      text-align: center;
      background-color: #eee;
    }
  </style>
  <script>
    function fCheck() {
    	var name = myform.name.value;
    	var title = myform.title.value;
    	var pwd = myform.pwd.value;
    	
    	if(name=="") {
    		alert("글올린이 닉네임을 입력하세요");
    		myform.name.focus();
    	}
    	else if(title=="") {
    		alert("글제목을 입력하세요");
    		myform.title.focus();
    	}
    	else if(pwd=="") {
    		alert("비밀번호를 입력하세요");
    		myform.pwd.focus();
    	}
    	else {
    		myform.submit();
    	}
    }
  </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
<div class="container">
  <p><br/></p>
  <form name="myform" method="post">
    <table class="table table-borderless">
      <tr>
        <td><h2>게시판 글쓰기</h2></td>
      </tr>
    </table>
    <table class="table">
      <tr>
        <th>글쓴이</th>
        <td><input type="text" name="name" value="${snickname}" readonly/></td>
      </tr>
      <tr>
        <th>글제목</th>
        <td><input type="text" name="title" placeholder="글제목을 입력하세요." size="60" autofocus required/></td>
      </tr>
      <tr>
        <th>이메일</th>
        <td><input type="text" name="email" value="${vo.email}" size="60"/></td>
      </tr>
      <tr>
        <th>글내용</th>
        <td><textarea rows="6" cols="62" name="content" id="CKEDITOR" required></textarea></td>
        <script>
          CKEDITOR.replace("content",{
        	  uploadUrl:"${ctp}/imageUpload",									/* 여러개의 그림파일 드래그&드롭으로 처리 */ 
        	  filebrowserUploadUrl : "${ctp}/imageUpload",    /* 파일(이미지) 업로드처리 */
        	  height:460
          });
        </script>
      </tr>
      <tr>
        <th>비밀번호</th>
        <td><input type="password" name="pwd" size="20" placeholder="비밀번호를 입력하세요" required/></td>
      </tr>
      <tr>
        <td colspan="2" style="text-align:center">
          <input type="button" value="글올리기" onclick="fCheck()" class="btn btn-secondary"/> &nbsp;
          <input type="reset" value="다시쓰기" class="btn btn-secondary"/> &nbsp;
          <input type="button" value="돌아가기" onclick="location.href='${ctp}/board/bList';" class="btn btn-secondary"/>
        </td>
      </tr>
    </table>
    <input type="hidden" name="hostIp" value="${pageContext.request.remoteAddr}"/>
  </form>
  <p><br/></p>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>