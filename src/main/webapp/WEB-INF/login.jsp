<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<jsp:include page="/WEB-INF/partials/head.jsp">
    <jsp:param name="title" value="Please Log In"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <div class="row">
        <h1 class="col-12 text-center my-3">Please Log In</h1>
        <form class="container-fluid column-gap-md-2 col-md-8 column-gap-lg-3 col-lg-6 col-12"
              action="${pageContext.request.contextPath}/login"
              method="POST">
            <div class="row gy-3">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input id="username" name="username" class="form-control" type="text">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input id="password" name="password" class="form-control" type="password">
                </div>
                <div class="form-group col-2">
                    <input type="submit" class="btn btn-primary btn-block" value="Log In">
                </div>
                <div class="form-group">
                    <p>Don't have an account? <a class="nav-link link-primary" href="${pageContext.request.contextPath}/register">Click here to register!</a></p>
                </div>
            </div>
        </form>
    </div>
</div>

<%@include file="partials/scripts.jsp" %>
</body>
</html>
