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
        <h1>Login page</h1>
        <div class="col-sm-6 col-lg-6 col-lg-offset-3" data-effect="slide-right">
            <form class="form-horizontal" action="/Main" method="post">
                <input type="hidden" type="text" name="command" value="AUTHORIZATION">
                <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                    <div class="col-lg-10">
                        <input type="text" name="inputEmail" class="form-control" id="inputEmail" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                    <div class="col-lg-10">
                        <input type="password" name="inputPassword" class="form-control" id="inputPassword" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="inputRemember"> Remember me
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="submit" class="btn btn-default">Log in</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <h1>Don't have an account? <a href="/jsp/registration.jsp">Sign up</a></h1>
    </div>
    </div>
    <c:import url="components/scripts.jsp"/>
</body>
</html>
