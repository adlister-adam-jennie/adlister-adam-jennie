<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<jsp:include page="/WEB-INF/partials/head.jsp">
    <jsp:param name="title" value="Your Profile"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <h1>Welcome, ${sessionScope.user.username}!</h1>
    <p><a href="/ads/information?userid=${user.id}">Update profile information</a></p>
</div>
<hr>
<div class="container">
    <div class="row">
        <c:forEach var="ad" items="${ads}">
            <div class="ad col-md-6 col-12" data-ad-id="${ad.id}">
                <h2 class="ad-title"><c:out value="${ad.title}"></c:out></h2>
                <p style="white-space: pre-line" class="ad-description"><c:out value="${ad.description}"></c:out></p>
                <p style="color: #278ED5">
                    <c:forEach var="categories" items="${ad.categories}">
                        <c:out value="${categories.name}"></c:out>
                    </c:forEach>
                </p>
                <form action="${pageContext.request.contextPath}/profile" method="post">
                    <a href="/ads/detail?id=${ad.id}&userId=${ad.userId}">Details</a>
                    <a href="/ads/update?id=${ad.id}">Update</a>
                    <a href="/ads/delete?id=${ad.id}">Delete</a>
                </form>
            </div>
        </c:forEach>
    </div>

</div>

<%@include file="partials/scripts.jsp" %>
</body>
</html>
