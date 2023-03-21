<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<jsp:include page="/WEB-INF/partials/head.jsp">
    <jsp:param name="title" value="Create a new Ad"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
        <h2 class="ad-title">${ad.title}</h2>
        <h4>Made by ${user.username}</h4>
        <p style="white-space: pre-line" class="ad-description">${category.name}</p>
        <p style="white-space: pre-line" class="ad-description">${ad.description}</p>
</div>

<%@include file="../partials/scripts.jsp" %>
</body>
</html>