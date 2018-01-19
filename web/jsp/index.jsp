<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setBundle basename="text" var="rb" />
    <c:import url="components/dependencies.jsp"/>
    <title>Title</title>
</head>
<body>
    <c:import url="components/navigation.jsp"/>
    <fmt:message key="navigation.login" bundle="${rb}"/>
    <h1>Index jsp test</h1>
    <form action="/Main" method="post">
        <input type="hidden" type="text" name="command" value="REGISTRATION">
        <input type="submit" value="Submit!">
    </form>
    <h1>test1</h1>
    <c:out value="${test_data}"/>
    <c:import url="components/scripts.jsp"/>
</body>
</html>
