<%--
  Created by IntelliJ IDEA.
  User: Dreadful
  Date: 17.01.2018
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Index jsp test</h1>
    <form action="Main" method="get">
        <input type="submit" value="Submit!">
    </form>
    <h1>test1</h1>
    <c:out value="${test_data}"/>
</body>
</html>
