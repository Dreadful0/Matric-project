<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <fmt:setBundle basename="text" var="rb" />
    <c:import url="../components/dependencies.jsp"/>
    <title><fmt:message key="titles.403" bundle="${rb}"/></title>
</head>
<body>
    <c:import url="../components/navigation.jsp"/>
    <div class="container">
    <h1 style="color:red"><fmt:message key="errorPages.accessDenied1" bundle="${rb}"/></h1>
    <h1 style="color:red"><fmt:message key="errorPages.accessDenied2" bundle="${rb}"/></h1>
    </div>
    <c:import url="../components/error.jsp"/>
    <c:import url="../components/footer.jsp"/>
    <c:import url="../components/scripts.jsp"/>
</body>
</html>
