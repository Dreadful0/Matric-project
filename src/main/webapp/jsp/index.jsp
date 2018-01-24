<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setBundle basename="text" var="rb" />
    <c:import url="components/dependencies.jsp"/>
    <title><fmt:message key="titles.index" bundle="${rb}"/></title>
</head>
<body>
<div class="container">
    <c:import url="components/navigation.jsp"/>
    <h1><fmt:message key="index.welcome" bundle="${rb}"/></h1>
    <h2><fmt:message key="index.slogan" bundle="${rb}"/></h2>
    <h2><fmt:message key="loginPage.dontHaveAccount" bundle="${rb}"/>
        <a href="/jsp/registration.jsp">
            <fmt:message key="loginPage.signUp" bundle="${rb}"/>
        </a>
    </h2>
</div>
    <c:import url="components/error.jsp"/>
    <c:import url="components/footer.jsp"/>
    <c:import url="components/scripts.jsp"/>
</body>
</html>
