<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<jsp:include page="/WEB-INF/partials/head.jsp">
  <jsp:param name="title" value="Profile Information"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
  <div class="row">
    <h1 class="col-12 text-center my-3">Profile Information for ${sessionScope.user.username}</h1>
    <form action="${pageContext.request.contextPath}/ads/information" method="post">
      <div class="row gy-3">
        <div class="form-group">
          <label for="email">Email</label>
          <input id="email" name="email" class="form-control register" type="text" value="${user.email}">
          <div class="invalid-feedback"></div>
        </div>
        <div class="form-group col-2">
          <input type="submit" class="btn btn-block btn-primary">
          <input name="userid" value="${user.id}" class="d-none">
        </div>
      </div>
    </form>
  </div>
</div>

<%@include file="../partials/scripts.jsp" %>
</body>
</html>
