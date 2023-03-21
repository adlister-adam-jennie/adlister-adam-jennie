<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<jsp:include page="partials/head.jsp">
    <jsp:param name="title" value="Register For Our Site!"/>
</jsp:include>
<body>
<jsp:include page="partials/navbar.jsp"/>
<div class="container">
    <div class="row">
        <h1 class="col-12 text-center my-3">Register Account</h1>
        <form class="container-fluid column-gap-md-2 col-md-8 column-gap-lg-3 col-lg-6 col-12 needs-validation"
              action="${pageContext.request.contextPath}/register" method="post" novalidate>
            <div class="row gy-3">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input id="username" name="username" class="form-control" type="text" required>
                    <div class="invalid-feedback">
                        Please enter a valid username.
                    </div>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input id="email" name="email" class="form-control" type="text" required>
                    <div class="invalid-feedback">
                        Please enter a valid email.
                    </div>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input id="password" name="password" class="form-control passwords-match-validation" type="password"
                           required>
                    <div class="invalid-feedback">
                        Please enter a valid password.
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirm_password">Confirm Password</label>
                    <input id="confirm_password" name="confirm_password" class="form-control passwords-match-validation"
                           type="password" required>
                    <div class="invalid-feedback">
                        Passwords must match.
                    </div>
                </div>
                <div class="form-group col-2">
                    <input type="submit" class="btn btn-primary btn-block">
                </div>
            </div>
        </form>
    </div>
</div>

<%@include file="partials/scripts.jsp" %>
</body>
</html>
