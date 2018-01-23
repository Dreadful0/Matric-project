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
    <div class="container">
    <h1>
        <fmt:message key="userPage.hello" bundle="${rb}"/>
        <c:out value="${user.firstName}"/>
        <fmt:message key="userPage.hello2" bundle="${rb}"/>
    </h1>
    <h2><fmt:message key="userPage.examResults" bundle="${rb}"/></h2>
    <table id="examResultsTable" class="table table-striped" data-effect="fade">
        <thead>
        <tr>
            <th><fmt:message key="universal.number" bundle="${rb}"/></th>
            <th><fmt:message key="userPage.examResultsTable.courseName" bundle="${rb}"/></th>
            <th><fmt:message key="userPage.examResultsTable.mark" bundle="${rb}"/></th>
            <th><fmt:message key="userPage.examResultsTable.date" bundle="${rb}"/></th>
            <th><fmt:message key="universal.select" bundle="${rb}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usersExamResultList}" var="examResult" varStatus="loop">
            <tr>
                <td><c:out value="${loop.index+1}"/></td>
                <td><c:out value="${examResult.course.name}"/></td>
                <td><c:out value="${examResult.mark}"/></td>
                <td><fmt:formatDate value="${examResult.date}" pattern="yyyy-MM-dd" /></td>
                <td>
                    <input type="radio" name="chosenExamResult"
                           autocomplete="on" required="required"
                           value="${examResult.id}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h2><fmt:message key="userPage.specialityRequests" bundle="${rb}"/></h2>
    <table id="specialityRequestsTable" class="table table-striped" data-effect="fade">
        <thead>
        <tr>
            <th><fmt:message key="universal.number" bundle="${rb}"/></th>
            <th><fmt:message key="userPage.specialityRequestsTable.specialityName" bundle="${rb}"/></th>
            <th><fmt:message key="userPage.specialityRequestsTable.finalMark" bundle="${rb}"/></th>
            <th><fmt:message key="userPage.specialityRequestsTable.confirmed" bundle="${rb}"/></th>
            <th><fmt:message key="universal.select" bundle="${rb}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usersSpecialityRequestList}" var="specialityRequest" varStatus="loop">
            <tr>
                <td><c:out value="${loop.index+1}"/></td>
                <td><c:out value="${specialityRequest.speciality.name}"/></td>
                <td><c:out value="${specialityRequest.finalMark}"/></td>
                <td><c:out value="${specialityRequest.confirmed}"/></td>
                <td>
                    <input type="radio" name="chosenSpecialityRequest"
                           autocomplete="on" required="required"
                           value="${specialityRequest.id}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
    <c:import url="components/error.jsp"/>
    <c:import url="components/footer.jsp"/>
    <c:import url="components/scripts.jsp"/>
</body>
</html>
