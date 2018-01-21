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
                <p class="lead text-muted">Tabs</p>
                <div class="tabbable">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab11" data-toggle="tab" aria-expanded="true">
                            <fmt:message key="adminPage.tabNames.marks" bundle="${rb}"/>
                        </a></li>
                        <li class=""><a href="#tab12" data-toggle="tab" aria-expanded="false">
                            <fmt:message key="adminPage.tabNames.courses" bundle="${rb}"/>
                        </a></li>
                        <li class=""><a href="#tab13" data-toggle="tab" aria-expanded="false">
                            <fmt:message key="adminPage.tabNames.universities" bundle="${rb}"/>
                        </a></li>
                        <li class=""><a href="#tab14" data-toggle="tab" aria-expanded="false">
                            <fmt:message key="adminPage.tabNames.specialities" bundle="${rb}"/>
                        </a></li>
                        <li class=""><a href="#tab15" data-toggle="tab" aria-expanded="false">
                            <fmt:message key="adminPage.tabNames.users" bundle="${rb}"/>
                        </a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab11">
                            <p>I'm in Section 1. Donec vulputate tristique elit ut molestie. Suspendisse faucibus bibendum ipsum. </p>
                        </div>
                        <div class="tab-pane" id="tab12">
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
                                                <input type="radio" name="choosenCourse"
                                                       autocomplete="on" required="required"
                                                       value="${course.id}"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <button type="button" class="btn btn-default" data-toggle="modal" href="#addCourseModal">
                                    <fmt:message key="adminPage.coursesTab.addButtonName" bundle="${rb}"/>
                                </button>
                                <button type="submit" class="btn btn-default"
                                        name="command" value="DELETE_COURSE">
                                    <fmt:message key="adminPage.coursesTab.deleteButtonName" bundle="${rb}"/>
                                </button>
                                <button type="button" class="btn btn-default" data-toggle="modal" href="#modifyCourseModal">
                                    <fmt:message key="adminPage.coursesTab.modifyButtonName" bundle="${rb}"/>
                                </button>
                                <div id="modifyCourseModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modifyCourseModalLabel" aria-hidden="true" style="display: none;">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="form-horizontal">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                    <h4 class="modal-title text-center" id="modifyCourseModalLabel">
                                                        <fmt:message key="adminPage.coursesTab.modifyButtonName" bundle="${rb}"/>
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <label for="inputModifiedCourseName" class="col-lg-3 control-label">
                                                            <fmt:message key="adminPage.coursesTab.courseName" bundle="${rb}"/>
                                                        </label>
                                                        <div class="col-lg-9">
                                                            <input type="text" name="inputCourseName" class="form-control"
                                                                   id="inputModifiedCourseName" autocomplete="on"
                                                                   placeholder="<fmt:message key="adminPage.coursesTab.courseName" bundle="${rb}"/>">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <fmt:message key="universal.close" bundle="${rb}"/>
                                                    </button>
                                                    <button type="submit" class="btn btn-primary"
                                                            name="command" value="MODIFY_COURSE">
                                                        <fmt:message key="adminPage.coursesTab.modifyButtonName" bundle="${rb}"/>
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
                        <div class="tab-pane" id="tab13">
                            <form class="form-horizontal" action="/Main" method="post">
                                <table id="universitiesTable" class="table table-striped" data-effect="fade">
                                    <thead>
                                    <tr>
                                        <th><fmt:message key="universal.number" bundle="${rb}"/></th>
                                        <th><fmt:message key="adminPage.universitiesTab.universityName" bundle="${rb}"/></th>
                                        <th><fmt:message key="universal.select" bundle="${rb}"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${universitiesList}" var="university" varStatus="loop">
                                        <tr>
                                            <td><c:out value="${loop.index+1}"/></td>
                                            <td><c:out value="${university.name}"/></td>
                                            <td>
                                                <input type="radio" name="chosenUniversity"
                                                       autocomplete="on" required="required"
                                                       value="${university.id}"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <button type="button" class="btn btn-default" data-toggle="modal" href="#addUniversityModal">
                                    <fmt:message key="adminPage.universitiesTab.addButtonName" bundle="${rb}"/>
                                </button>
                                <button type="submit" class="btn btn-default"
                                        name="command" value="DELETE_UNIVERSITY">
                                    <fmt:message key="adminPage.universitiesTab.deleteButtonName" bundle="${rb}"/>
                                </button>
                                <button type="button" class="btn btn-default" data-toggle="modal" href="#modifyUniversityModal">
                                    <fmt:message key="adminPage.universitiesTab.modifyButtonName" bundle="${rb}"/>
                                </button>
                                <div id="modifyUniversityModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modifyUniversityModalLabel" aria-hidden="true" style="display: none;">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="form-horizontal">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                    <h4 class="modal-title text-center" id="modifyUniversityModalLabel">
                                                        <fmt:message key="adminPage.universitiesTab.modifyButtonName" bundle="${rb}"/>
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <label for="inputModifiedUniversityName" class="col-lg-3 control-label">
                                                            <fmt:message key="adminPage.universitiesTab.universityName" bundle="${rb}"/>
                                                        </label>
                                                        <div class="col-lg-9">
                                                            <input type="text" name="inputUniversityName" class="form-control"
                                                                   id="inputModifiedUniversityName" autocomplete="on"
                                                                   placeholder="<fmt:message key="adminPage.universitiesTab.universityName" bundle="${rb}"/>">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <fmt:message key="universal.close" bundle="${rb}"/>
                                                    </button>
                                                    <button type="submit" class="btn btn-primary"
                                                            name="command" value="MODIFY_UNIVERSITY">
                                                        <fmt:message key="adminPage.universitiesTab.modifyButtonName" bundle="${rb}"/>
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
                        <div class="tab-pane" id="tab14">
                            <form class="form-horizontal" action="/Main" method="post">
                                <h3>Collapsible Panels</h3>
                                <div class="panel-group" id="accordion-panel">
                                    <c:forEach items="${universitiesList}" var="university" varStatus="loop">
                                    <div class="panel panel-default">
                                        <div class="accordion-heading specialityTabUniversityAcordHeading">
                                                <a class="accordion-toggle collapsed" data-toggle="collapse"
                                                   data-parent="#accordion-panel" href="#collapsePanel${loop.index}"
                                                   aria-expanded="false" >
                                                    <c:out value="${university.name}"/>
                                                    <input type="radio" name="chosenUniversity2"
                                                           autocomplete="on" required="required"
                                                           value="${university.id}"/>
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
                                                                <input type="radio" name="chosenSpeciality"
                                                                       autocomplete="on"
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
                                    <fmt:message key="adminPage.specialitiesTab.addButtonName" bundle="${rb}"/>
                                </button>
                                <button type="submit" class="btn btn-default"
                                        name="command" value="DELETE_SPECIALITY">
                                    <fmt:message key="adminPage.specialitiesTab.deleteButtonName" bundle="${rb}"/>
                                </button>
                                <div id="addSpecialityModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="addSpecialityModalLabel" aria-hidden="true" style="display: none;">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="form-horizontal">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                    <h4 class="modal-title text-center" id="addSpecialityModalLabel">
                                                        <fmt:message key="adminPage.specialitiesTab.addButtonName" bundle="${rb}"/>
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <label for="inputSpecialityName" class="col-lg-3 control-label">
                                                            <fmt:message key="adminPage.specialitiesTab.specialityName" bundle="${rb}"/>
                                                        </label>
                                                        <div class="col-lg-9">
                                                            <input type="text" name="inputSpecialityName" class="form-control"
                                                                   id="inputSpecialityName" autocomplete="on"
                                                                   placeholder="<fmt:message key="adminPage.specialitiesTab.specialityName" bundle="${rb}"/>">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="inputSpecialityStudentsNumber" class="col-lg-3 control-label">
                                                            <fmt:message key="adminPage.specialitiesTab.specialityStudentsNumber" bundle="${rb}"/>
                                                        </label>
                                                        <div class="col-lg-9">
                                                            <input type="number" name="inputSpecialityStudentsNumber" class="form-control"
                                                                   id="inputSpecialityStudentsNumber" autocomplete="on"
                                                                   placeholder="<fmt:message key="adminPage.specialitiesTab.specialityStudentsNumber" bundle="${rb}"/>">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <fmt:message key="universal.close" bundle="${rb}"/>
                                                    </button>
                                                    <button type="submit" class="btn btn-primary"
                                                            name="command" value="ADD_SPECIALITY">
                                                        <fmt:message key="adminPage.specialitiesTab.addButtonName" bundle="${rb}"/>
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
                        <div class="tab-pane" id="tab15">
                            <p>Howdy, I'm in Section 2.Morbi vel nibh et arcu pretium adipiscing. Ut vestibulum est eget justo facilisis ullamcorper. </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="addCourseModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="addCourseModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form class="form-horizontal" action="/Main" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title text-center" id="addCourseModalLabel">
                            <fmt:message key="adminPage.coursesTab.addButtonName" bundle="${rb}"/>
                        </h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="inputCourseName" class="col-lg-3 control-label">
                                <fmt:message key="adminPage.coursesTab.courseName" bundle="${rb}"/>
                            </label>
                            <div class="col-lg-9">
                                <input type="text" name="inputCourseName" class="form-control"
                                       id="inputCourseName" autocomplete="on" required="required"
                                       placeholder="<fmt:message key="adminPage.coursesTab.courseName" bundle="${rb}"/>">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <fmt:message key="universal.close" bundle="${rb}"/>
                        </button>
                        <button type="submit" class="btn btn-primary"
                                name="command" value="ADD_COURSE">
                            <fmt:message key="adminPage.coursesTab.addButtonName" bundle="${rb}"/>
                        </button>
                    </div>
                    </form>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
    <div id="addUniversityModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="addUniversityModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <form class="form-horizontal" action="/Main" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title text-center" id="addUniversityModalLabel">
                            <fmt:message key="adminPage.universitiesTab.addButtonName" bundle="${rb}"/>
                        </h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="inputUniversityName" class="col-lg-3 control-label">
                                <fmt:message key="adminPage.universitiesTab.universityName" bundle="${rb}"/>
                            </label>
                            <div class="col-lg-9">
                                <input type="text" name="inputUniversityName" class="form-control"
                                       id="inputUniversityName" autocomplete="on" required="required"
                                       placeholder="<fmt:message key="adminPage.universitiesTab.universityName" bundle="${rb}"/>">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <fmt:message key="universal.close" bundle="${rb}"/>
                        </button>
                        <button type="submit" class="btn btn-primary"
                                name="command" value="ADD_UNIVERSITY">
                            <fmt:message key="adminPage.universitiesTab.addButtonName" bundle="${rb}"/>
                        </button>
                    </div>
                </form>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <c:import url="components/footer.jsp"/>
    <c:import url="components/scripts.jsp"/>
    <script>
        $('#addCourseModal').appendTo("body");
        $(document).ready(function() {

            $('#coursesTable tr, #universitiesTable tr, #specialityTables tr, .specialityTabUniversityAcordHeading').click(function() {
                var radio = $(this).find("input");
                radio.prop("checked", true);
            });

        });
    </script>
</body>
</html>
