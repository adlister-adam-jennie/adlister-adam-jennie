<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<jsp:include page="/WEB-INF/partials/head.jsp">
    <jsp:param name="title" value="Create a new Ad"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <div class="row">
        <h1 class="col-12 text-center my-3">Create a new Ad</h1>
        <form action="${pageContext.request.contextPath}/ads/create" method="post">
            <div class="row gy-3">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input id="title" name="title" class="form-control" type="text">
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="category_1" id="flexCheckChecked ">
                        <label class="form-check-label" for="flexCheckChecked">
                            Service
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="category_2" id="flexCheckChecked">
                        <label class="form-check-label" for="flexCheckChecked">
                            Product
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="category_3" id="flexCheckChecked">
                        <label class="form-check-label" for="flexCheckChecked">
                            Recruitment
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="category_4" id="flexCheckChecked">
                        <label class="form-check-label" for="flexCheckChecked">
                            Limited Time
                        </label>
                    </div>
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
