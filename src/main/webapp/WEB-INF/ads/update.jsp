<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <input id="title" name="title" class="form-control" type="text" value="${ad.title}" required>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" class="form-control"><c:out value="${ad.description}"/></textarea>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="1" name="category" id="service" <c:out value="${category1}"/>>
                        <label class="form-check-label" for="service">
                            Service
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="2" name="category" id="product" <c:out value="${category2}"/>>
                        <label class="form-check-label" for="product">
                            Product
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="3" name="category" id="recruitment" <c:out value="${category3}"/>>
                        <label class="form-check-label" for="recruitment">
                            Recruitment
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="4" name="category" id="limited_time" <c:out value="${category4}"/>>
                        <label class="form-check-label" for="limited_time">
                            Limited Time
                        </label>
                    </div>
                </div>
                <div class="form-group col-2">
                    <input type="submit" class="btn btn-block btn-primary">
                    <input name="id" value="${ad.id}" class="d-none">
                    <c:if test="${error}">
                        <div class="alert alert-warning">This ad already exists, please change the title or description</div>
                    </c:if>
                </div>
            </div>
        </form>
    </div>
</div>

<%@include file="../partials/scripts.jsp" %>
</body>
</html>