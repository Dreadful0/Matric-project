<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="auth" %>
<fmt:setBundle basename="text" var="rb" />
<nav class="navbar-fixed-top navbar-inverse" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/jsp/index.jsp">
            <fmt:message key="navigation.home" bundle="${rb}"/>
        </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                    <fmt:message key="navigation.language" bundle="${rb}"/><b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/Main?command=LOCALIZATION">English</a></li>
                    <li><a href="/Main?command=LOCALIZATION&language=uk_UA">Українська</a></li>
                </ul>
            </li>
            <my:if-user role="admin">
            <li><a href="/Main?command=ADMIN_PAGE_INFO">
                <fmt:message key="navigation.adminPage" bundle="${rb}"/>
            </a></li>
            </my:if-user>
            <my:if-user role="user">
            <li><a href="/Main?command=COURSE_REGISTRATION_PAGE_INFO">
                <fmt:message key="navigation.courseRegistration" bundle="${rb}"/>
            </a></li>
            </my:if-user>
            <my:if-user role="user">
            <li><a href="/Main?command=SPECIALITY_REGISTRATION_PAGE_INFO">
                <fmt:message key="navigation.specialityRegistration" bundle="${rb}"/>
            </a></li>
            </my:if-user>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <my:if-user role="user">
            <li><a href="/Main?command=PERSONAL_INFO">
                <c:out value="${currentUser.firstName}"/>
                <c:out value="${currentUser.secondName}"/>
            </a></li>
            </my:if-user>
            <my:if-user role="none">
            <li><a href="/jsp/login.jsp">
                <fmt:message key="navigation.login" bundle="${rb}"/>
            </a></li>
            </my:if-user>
            <my:if-user role="user">
            <li><a href="/Main?command=LOGOUT">
                <fmt:message key="navigation.logout" bundle="${rb}"/>
            </a></li>
            </my:if-user>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>
