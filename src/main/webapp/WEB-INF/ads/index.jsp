<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<jsp:include page="/WEB-INF/partials/head.jsp">
    <jsp:param name="title" value="Viewing All The Ads"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <div class="d-flex align-items-center row my-3 mx-0">
        <div class="fs-1 col-12 col-md-8 p-0">Here Are all the ads!</div>
        <label class="col-12 col-md-4 p-0">
            <input class="form-control" type="text" placeholder="Search Ads" id="ad-search">
        </label>
    </div>
    <div class="row">
        <c:forEach var="ad" items="${ads}">
            <div class="ad col-md-6 col-12" data-ad-id="${ad.id}">
                <h2 class="ad-title">${ad.title}</h2>
                <p style="white-space: pre-line" class="ad-description">${ad.description}</p>
                <a href="/ads/detail?id=${ad.id}">Details</a>
            </div>
        </c:forEach>
    </div>
</div>

<%@include file="../partials/scripts.jsp" %>
</body>
</html>
