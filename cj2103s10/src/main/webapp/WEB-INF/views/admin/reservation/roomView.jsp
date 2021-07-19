<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>펜션정보 보기</title>
</head>
<body>
<%@ include file="/WEB-INF/views/include/bs.jsp" %>
<p><br/></p>
<div style="margin:0 20px;">
  <div><h3>객실명 : ${vo.room}</h3></div>
  <hr/>
  <div>
    <h4>객실 상세 설명</h4>
    <div>${vo.content}</div>
  </div>
  <hr/>
  <div style="text-align:center;"><input type="button" value="창닫기" onclick="window.close()" class="btn btn-outline-secondary btn-sm"/></div>
</div>
<p><br/></p>
</body>
</html>