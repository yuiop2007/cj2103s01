<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원정보보기</title>
  <style>
    th {background-color:#ddd;}
  </style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/bs.jsp" %>
<p><br/></p>
<div class="container">
  <h2>회원 정보</h2>
  <table class="table">
    <tr>
      <th>아이디</th>
      <td>${vo.mid}</td>
      <th>닉네임</th>
      <td>${vo.nickName}</td>
    </tr>
    <tr>
      <th>성명</th>
      <td>${vo.name}</td>
      <th>연락처</th>
      <td>${vo.tel}</td>
    </tr>
    <tr>
      <th>주소</th>
      <td colspan="3">${vo.address}</td>
    </tr>
    <tr>
      <th>생일</th>
      <td>${vo.birthday}</td>
      <th>이메일</th>
      <td>${vo.email}</td>
    </tr>
    <tr>
      <th>홈페이지</th>
      <td colspan="3">${vo.homepage}</td>
    </tr>
    <tr>
      <th>직업</th>
      <td>${vo.job}</td>
      <th>취미</th>
      <td>${vo.hobby}</td>
    </tr>
    <tr>
      <th>자기소개</th>
      <td colspan="3">${vo.content}</td>
    </tr>
    <tr>
      <th>사진</th>
      <td colspan="3"><img src="${ctp}/resources/member/${vo.photo}"/></td>
    </tr>
  </table>
  <hr/>
  <input type="button" value="창닫기" onclick="window.close()"/>
</div>
<p><br/></p>
</body>
</html>