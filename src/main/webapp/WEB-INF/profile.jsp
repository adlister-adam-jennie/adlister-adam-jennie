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

</div>

<div class="container">
    <div class="row">
        <c:forEach var="ad" items="${ads}">
            <div class="ad col-md-6 col-12" data-ad-id="${ad.id}">
                <h2 class="ad-title">${ad.title}</h2>
                <p style="white-space: pre-line" class="ad-description">${ad.description}</p>
            </div>
        </c:forEach>
    </div>
</div>

<%@include file="partials/scripts.jsp" %>
</body>
</html>
