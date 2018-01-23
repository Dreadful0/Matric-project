<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setBundle basename="text" var="rb" />
    <c:import url="components/dependencies.jsp"/>
    <title><fmt:message key="loginPage.title" bundle="${rb}"/></title>
</head>
<body>
    <c:import url="components/navigation.jsp"/>
    <div class="container">
    <div class="row">
        <h1><fmt:message key="loginPage.loginPage" bundle="${rb}"/></h1>
        <div class="col-sm-6 col-lg-6 col-lg-offset-3" data-effect="slide-right">
            <form class="form-horizontal" action="/Main" method="post">

                <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">
                        <fmt:message key="loginPage.email" bundle="${rb}"/>
                    </label>
                    <div class="col-lg-10">
                        <input type="text" name="inputEmail" class="form-control"
                               id="inputEmail" placeholder="<fmt:message key="loginPage.email" bundle="${rb}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-lg-2 control-label">
                        <fmt:message key="loginPage.password" bundle="${rb}"/>
                    </label>
                    <div class="col-lg-10">
                        <input type="password" name="inputPassword" class="form-control"
                               id="inputPassword" placeholder="<fmt:message key="loginPage.password" bundle="${rb}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="inputRemember">
                                <fmt:message key="loginPage.remember" bundle="${rb}"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="submit" class="btn btn-default"
                                name="command" value="AUTHORIZATION">
                            <fmt:message key="navigation.login" bundle="${rb}"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <h1><fmt:message key="loginPage.dontHaveAccount" bundle="${rb}"/>
            <a href="/jsp/registration.jsp">
                <fmt:message key="loginPage.signUp" bundle="${rb}"/>
            </a>
        </h1>
    </div>
    </div>
    <c:import url="components/footer.jsp"/>
    <c:import url="components/scripts.jsp"/>
</body>
</html>
