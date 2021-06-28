<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		      <label for="name">성명 :</label>${vo.name}
		    </div>
	    </td>
	  </tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="email">Email address:</label>${vo.email}
			  </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <div class="form-check-inline">
		        <span class="input-group-text">성별 :</span> &nbsp; &nbsp;${vo.gender}
					</div>
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="birthday">생일 :</label>${vo.birthday}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <div class="input-group mb-3">
			      <div class="input-group-prepend">
			        <span class="input-group-text">전화번호 :</span> &nbsp;&nbsp;${vo.tel}
			      </div>
			    </div> 
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <label for="address">주소 :</label>${vo.address}
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
			    <label for="homepage">Homepage address:</label>${vo.homePage}
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
		      <div class="form-check-inline">
		        <span class="input-group-text">취미 :</span> &nbsp; &nbsp;${vo.hobby}
					</div>
		    </div>
			</td>
		</tr>
		<tr>
		  <td>
		    <div class="form-group">
		      <div class="form-check-inline">
		        <span class="input-group-text">정보공개 :</span>  &nbsp; &nbsp;${vo.userInfor} 
					</div>
		    </div>
		  </td>
		</tr>
		<tr>
		  <td>
    		<button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
      </td>
    </tr>
  </table>
  <p><br/></p>
</div>
</body>
</html>