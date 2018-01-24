<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setBundle basename="text" var="rb" />
    <c:import url="components/dependencies.jsp"/>
    <title><fmt:message key="titles.courseRegistration" bundle="${rb}"/></title>
</head>
<body>
<c:import url="components/navigation.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-lg-12">
            <form class="form-horizontal" action="/Main" method="post">
            <table id="coursesTable" class="table table-striped" data-effect="fade">
                <thead>
                <tr>
                    <th><fmt:message key="universal.number" bundle="${rb}"/></th>
                    <th><fmt:message key="adminPage.coursesTab.courseName" bundle="${rb}"/></th>
                    <th><fmt:message key="universal.select" bundle="${rb}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${coursesList}" var="course" varStatus="loop">
                    <tr>
                        <td><c:out value="${loop.index+1}"/></td>
                        <td><c:out value="${course.name}"/></td>
                        <td>
                            <div class="checkbox">
                                <label>
                                    <input class="checkbox" type="checkbox" name="chosenCourses"
                                           value="${course.id}">
                                </label>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-default"
                    name="command" value="REGISTER_FOR_EXAM">
                <fmt:message key="courseRegistrationPage.registerForCourse" bundle="${rb}"/>
            </button>
            </form>
        </div>
    </div>
</div>
<c:import url="components/error.jsp"/>
<c:import url="components/footer.jsp"/>
<c:import url="components/scripts.jsp"/>
<script>
    var limit = ${currentUser.examAttempts};
    $(document).ready(function() {

        $('#coursesTable tr').click(function() {
            var radio = $(this).find("input");
            if(!radio.prop("checked") && $('input.checkbox:checked').length < limit){
                radio.prop("checked", true);
            } else{
                radio.prop("checked", false);
            }
        });

    });
</script>
</body>
</html>
