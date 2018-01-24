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
<div class="container">
    <h1 style="color:red">An error occured</h1>
    <h2 style="color:red">Please contact the support</h2>
</div>
<c:import url="../components/error.jsp"/>
<c:import url="../components/footer.jsp"/>
<c:import url="../components/scripts.jsp"/>
</body>
</html>
