<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar sticky-top navbar-expand-lg bg-primary-subtle">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/ads">Adlister</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto">
                <c:choose>
                    <c:when test="${user == null}">
                        <%--                        if user is logged out--%>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a></li>

                    </c:when>
                    <c:otherwise>
<%--                    if user is logged in--%>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/profile">Profile</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ads/create">Create Ad</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </c:otherwise>
                </c:choose>
<%--                c: if user is not null then make true --%>


                </li>
            </ul>
        </div>
    </div>
</nav>
