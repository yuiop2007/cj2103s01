<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>pmContent.jsp</title>
	<%@ include file="/WEB-INF/views/include/bs.jsp" %>
</head>
<body>
<div class="container">
  <p><br/></p>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">${vo.title} (분류 : ${vo.part})</h4>
        <button type="button" class="close"  onclick="window.close()">&times;</button>
      </div>
      <div class="modal-body">
        <hr/>
        - <b>올린이</b> : ${vo.nickName}
        <hr/>
        <c:if test="${vo.openSw == '공개' || smid == vo.mid || slevel == 0}">
          - <b>아이디</b> : ${vo.mid}<br/>
          - <b>파일명</b> : ${vo.fName}<br/>
          - <b>자료설명</b> : <br/>
          <p>${fn:replace(vo.content,newLine,"<br/>")}</p>
        </c:if>
        - <b>공개여부</b> : ${vo.openSw}
        <hr/>
        <!--
          0         1         2
          012345678901234567890 
          a.jpg/b.zip/c.gif/     : fSName
        -->
        <c:set var="fNames" value="${fn:split(vo.fName,'/')}"/>
        <c:set var="rfNames" value="${fn:split(vo.rfName,'/')}"/>
        <c:forEach var="rfName" items="${rfNames}" varStatus="st">
          ${st.count}. ${fNames[st.index]}<br/>
          <c:set var="lenRFName" value="${fn:length(rfName)}"/>
          <c:set var="extRFName" value="${fn:substring(rfName,lenrfName-3,lenRFName)}"/>
          <c:set var="extRFName" value="${fn:toUpperCase(extRFName)}"/>
          <c:if test="${extRFName=='JPG' || extRFName=='GIF' || extRFName=='PNG' || extRFName=='JPEG'}">
            <img src="${ctp}/resources/pdsm/${rfName}" width="350px"/><br/><br/>
          </c:if>
        </c:forEach>
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-danger" onclick="window.close()">Close</button>
      </div>
    </div>
  </div>
  <p><br/></p>
</div>
</body>
</html>