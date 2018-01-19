<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <fmt:setBundle basename="text" var="rb" />
    <c:import url="../components/dependencies.jsp"/>
    <title>Title</title>
</head>
<body>
    <c:import url="../components/navigation.jsp"/>
    <h1 style="color:red">ACCESS</h1>
    <h1 style="color:red">DENIED</h1>
    <c:import url="../components/scripts.jsp"/>
</body>
</html>
