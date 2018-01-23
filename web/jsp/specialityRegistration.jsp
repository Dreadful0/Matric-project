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
    <div class="row">
        <div class="col-sm-12 col-lg-12">
            <form class="form-horizontal" action="/Main" method="post">
                <div class="panel-group" id="accordion-panel">
                    <c:forEach items="${universitiesList}" var="university" varStatus="loop">
                        <div class="panel panel-default">
                            <div class="accordion-heading specialityTabUniversityAcordHeading">
                                <a class="accordion-toggle collapsed" data-toggle="collapse"
                                   data-parent="#accordion-panel" href="#collapsePanel${loop.index}"
                                   aria-expanded="false" >
                                    <c:out value="${university.name}"/>
                                </a>
                            </div>
                            <div id="collapsePanel${loop.index}" class="panel-collapse collapse" aria-expanded="false"
                                 style="height: 0px;">
                                <div class="panel-body">
                                    <table id="specialityTables" class="table table-striped" data-effect="fade">
                                        <thead>
                                        <tr>
                                            <th><fmt:message key="universal.number" bundle="${rb}"/></th>
                                            <th><fmt:message key="adminPage.specialitiesTab.specialityName" bundle="${rb}"/></th>
                                            <th><fmt:message key="adminPage.specialitiesTab.specialityStudentsNumber" bundle="${rb}"/></th>
                                            <th><fmt:message key="adminPage.specialitiesTab.requiredCourses" bundle="${rb}"/></th>
                                            <th><fmt:message key="universal.select" bundle="${rb}"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${university.specialityList}" var="speciality" varStatus="loop">
                                            <tr>
                                                <td><c:out value="${loop.index+1}"/></td>
                                                <td><c:out value="${speciality.name}"/></td>
                                                <td><c:out value="${speciality.studentsNumber}"/></td>
                                                <td>
                                                    <table>
                                                        <c:forEach items="${speciality.requiredCourses}" var="course">
                                                            <tr>
                                                                <td><c:out value="${course.name}"/></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>
                                                </td>
                                                <td>
                                                    <input type="radio" name="chosenSpeciality"
                                                           autocomplete="on" required="required"
                                                           value="${speciality.id}"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <button type="button" class="btn btn-default" data-toggle="modal" href="#addSpecialityModal">
                    <fmt:message key="specialityRegistrationPage.registerForSpeciality" bundle="${rb}"/>
                </button>
                <div id="addSpecialityModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="addSpecialityModalLabel" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="form-horizontal">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h4 class="modal-title text-center" id="addSpecialityModalLabel">
                                        <fmt:message key="specialityRegistrationPage.registerForSpeciality" bundle="${rb}"/>
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label class="col-lg-3 control-label">
                                            <fmt:message key="specialityRegistrationPage.examResults" bundle="${rb}"/>
                                        </label>
                                        <div class="col-lg-9">
                                            <c:forEach items="${userExamResults}" var="examResult" varStatus="loop">
                                                <div class="checkbox">
                                                    <label>
                                                        <input class="examResultCheckbox" type="checkbox" name="inputExamResultsId" value="${examResult.id}">
                                                        <c:out value="${examResult.course.name}"/>
                                                        <c:out value="${examResult.mark}"/>
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                        <fmt:message key="universal.close" bundle="${rb}"/>
                                    </button>
                                    <button type="submit" class="btn btn-primary"
                                            name="command" value="APPLY_FOR_SPECIALITY">
                                        <fmt:message key="specialityRegistrationPage.registerForSpecialityShort" bundle="${rb}"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
            </form>
        </div>
    </div>
</div>
<c:import url="components/footer.jsp"/>
<c:import url="components/scripts.jsp"/>
<script>
    $(document).ready(function() {

        $('#specialityTables tr').click(function() {
            var radio = $(this).find("input");
            radio.prop("checked", true);
        });

    });
</script>
</body>
</html>
