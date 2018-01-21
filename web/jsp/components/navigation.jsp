<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="text" var="rb" />
<nav class="navbar navbar-inverse" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/jsp/index.jsp">Home</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <li><a href="/Main?command=ADMIN_PAGE_INFO">
                <fmt:message key="navigation.adminPage" bundle="${rb}"/>
            </a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                    <fmt:message key="navigation.language" bundle="${rb}"/><b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/Main?command=LOCALIZATION">English</a></li>
                    <li><a href="/Main?command=LOCALIZATION&language=uk_UA">Ukrainian</a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/Main?command=PERSONAL_INFO">
                <c:out value="${currentUser.firstName}"/>
                <c:out value="${currentUser.secondName}"/>
            </a></li>
            <li><a href="/jsp/login.jsp">
                <fmt:message key="navigation.login" bundle="${rb}"/>
            </a></li>
            <li><a href="/Main?command=LOGOUT">Logout</a></li>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>
