<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="kr">
<head>
    <jsp:include page="/includes/header.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="/includes/navigation.jsp"></jsp:include>

<div class="container" id="main">
   <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-default content-main">
          <form name="question" method="post" action="/user/update">
              <div class="form-group">
                  <label for="userId">사용자 아이디</label>
                  <input class="form-control" id="userId" name="userId" placeholder="User ID" value="${user.userId}">
              </div>
              <div class="form-group">
                  <label for="password">비밀번호</label>
                  <input type="password" class="form-control" id="password" name="password" placeholder="Password" value="${user.password}">
              </div>
              <div class="form-group">
                  <label for="name">이름</label>
                  <input class="form-control" id="name" name="name" placeholder="Name" value="${user.name}">
              </div>
              <div class="form-group">
                  <label for="email">이메일</label>
                  <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${user.email}">
              </div>
              <button type="submit" class="btn btn-success clearfix pull-right">수정</button>
              <div class="clearfix" />
          </form>
        </div>
    </div>
</div>

    <jsp:include page="/includes/footer.jsp"></jsp:include>
</body>
</html>