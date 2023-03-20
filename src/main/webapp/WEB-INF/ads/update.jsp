<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<jsp:include page="/WEB-INF/partials/head.jsp">
    <jsp:param name="title" value="Update Ads"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <div class="row">
        <h1 class="col-12 text-center my-3">Update a new Ad</h1>
        <form action="${pageContext.request.contextPath}/ads/update" method="post">
            <div class="row gy-3">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input id="title" name="title" class="form-control" type="text">
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" class="form-control"></textarea>
                </div>
                <div class="form-group col-2">
                    <input type="submit" class="btn btn-block btn-primary">
                </div>
            </div>
        </form>
    </div>
</div>

<%@include file="../partials/scripts.jsp" %>
</body>
</html>